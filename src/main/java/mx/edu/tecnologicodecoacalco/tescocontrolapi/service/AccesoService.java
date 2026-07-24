package mx.edu.tecnologicodecoacalco.tescocontrolapi.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Horario;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Registro;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Tarjeta;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Usuario;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.UsuarioMateria;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.UsuarioTarjeta;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.HorarioDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.RegistroDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.TarjetaDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.UsuarioDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.UsuarioMateriaDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.UsuarioTarjetaDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.rest.dto.AccesoResponse;

/**
 * Valida si una tarjeta NFC puede abrir un laboratorio: la tarjeta debe estar
 * activa, asignada a un maestro activo que imparta una materia con horario en
 * ese laboratorio para el día actual, dentro de un margen de 10 minutos antes
 * o después de la hora programada.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccesoService {

	private static final long MARGEN_MINUTOS = 10;

	private final TarjetaDao tarjetaDao;
	private final UsuarioTarjetaDao usuarioTarjetaDao;
	private final UsuarioDao usuarioDao;
	private final UsuarioMateriaDao usuarioMateriaDao;
	private final HorarioDao horarioDao;
	private final RegistroDao registroDao;

	public AccesoResponse validarAcceso(String idTarjeta, Integer idLaboratorio) {
		LocalDateTime ahora = LocalDateTime.now();
		boolean abrio = false;
		String mensaje;
		Usuario usuario = null;

		Optional<Tarjeta> tarjetaOpt = tarjetaDao.findById(idTarjeta);
		if (tarjetaOpt.isEmpty()) {
			mensaje = "Tarjeta no reconocida";
		} else if (!Boolean.TRUE.equals(tarjetaOpt.get().getActiva())) {
			mensaje = "Tarjeta inactiva";
		} else {
			Optional<UsuarioTarjeta> asignacion = usuarioTarjetaDao.findByIdTarjeta(idTarjeta);
			if (asignacion.isEmpty()) {
				mensaje = "La tarjeta no está asignada a ningún usuario";
			} else {
				usuario = usuarioDao.findById(asignacion.get().getIdUsuario()).orElse(null);
				if (usuario == null || !Boolean.TRUE.equals(usuario.getActivo())) {
					mensaje = "Usuario no encontrado o inactivo";
				} else {
					mensaje = evaluarHorario(usuario, idLaboratorio, ahora);
					abrio = mensaje.startsWith("Acceso concedido");
				}
			}
		}

		registrarIntento(idTarjeta, idLaboratorio, ahora, abrio);

		String nombreUsuario = usuario != null ? usuario.getNombre() + " " + usuario.getApPaterno() : null;
		return new AccesoResponse(abrio, mensaje, nombreUsuario, ahora);
	}

	private String evaluarHorario(Usuario usuario, Integer idLaboratorio, LocalDateTime ahora) {
		List<Integer> idsMateria = usuarioMateriaDao.findByIdUsuario(usuario.getIdUsuario()).stream()
				.map(UsuarioMateria::getIdMateria).collect(Collectors.toList());

		if (idsMateria.isEmpty()) {
			return "El usuario no tiene materias asignadas";
		}

		String dia = nombreDiaActual(ahora);
		LocalTime horaActual = ahora.toLocalTime();

		Optional<Horario> horarioValido = horarioDao
				.findByIdLaboratorioAndDiaIgnoreCaseAndIdMateriaIn(idLaboratorio, dia, idsMateria).stream()
				.filter(horario -> dentroDelMargen(horario, horaActual)).findFirst();

		if (horarioValido.isPresent()) {
			return "Acceso concedido a " + usuario.getNombre() + " " + usuario.getApPaterno();
		}
		return "No hay una clase programada para este usuario en este laboratorio a esta hora";
	}

	private boolean dentroDelMargen(Horario horario, LocalTime horaActual) {
		LocalTime inicio = horario.getHoraInicio().minusMinutes(MARGEN_MINUTOS);
		LocalTime termino = horario.getHoraTermino().plusMinutes(MARGEN_MINUTOS);
		return !horaActual.isBefore(inicio) && !horaActual.isAfter(termino);
	}

	private String nombreDiaActual(LocalDateTime ahora) {
		DayOfWeek diaSemana = ahora.getDayOfWeek();
		String nombre = diaSemana.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es"));
		return nombre.substring(0, 1).toUpperCase(Locale.forLanguageTag("es")) + nombre.substring(1);
	}

	private void registrarIntento(String idTarjeta, Integer idLaboratorio, LocalDateTime fechaHora, boolean abrio) {
		try {
			Registro registro = new Registro();
			registro.setIdTarjeta(idTarjeta);
			registro.setIdLaboratorio(idLaboratorio);
			registro.setFechaHora(fechaHora);
			registro.setAbrio(abrio);
			registroDao.save(registro);
		} catch (Exception exception) {
			log.error("No se pudo registrar el intento de acceso de la tarjeta " + idTarjeta, exception);
		}
	}
}
