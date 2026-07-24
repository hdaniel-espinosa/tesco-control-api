package mx.edu.tecnologicodecoacalco.tescocontrolapi.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Horario;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Laboratorio;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.HorarioDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.LaboratorioDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.rest.dto.HorarioProximoDto;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.rest.dto.LaboratorioEstadoDto;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.util.DiaSemanaUtil;

/**
 * Calcula, a partir del horario programado (no de accesos reales), qué
 * laboratorios están ocupados ahora mismo y qué horarios están por
 * comenzar, con el mismo margen de 10 minutos que usa AccesoService.
 */
@Service
@RequiredArgsConstructor
public class DashboardService {

	private static final long MARGEN_MINUTOS = 10;

	private final LaboratorioDao laboratorioDao;
	private final HorarioDao horarioDao;
	private final HorarioDetalleService horarioDetalleService;

	public List<LaboratorioEstadoDto> obtenerEstadoLaboratorios() {
		LocalDateTime ahora = LocalDateTime.now();
		String dia = DiaSemanaUtil.nombreDia(ahora);
		LocalTime horaActual = ahora.toLocalTime();

		List<LaboratorioEstadoDto> resultado = new ArrayList<>();
		for (Laboratorio laboratorio : laboratorioDao.findAll()) {
			List<Horario> horariosHoy = horarioDao
					.findByIdLaboratorioAndDiaIgnoreCase(laboratorio.getIdLaboratorio(), dia).stream()
					.sorted(Comparator.comparing(Horario::getHoraInicio)).toList();

			Horario actual = horariosHoy.stream().filter(horario -> dentroDelMargen(horario, horaActual)).findFirst()
					.orElse(null);
			Horario proximo = horariosHoy.stream()
					.filter(horario -> horario.getHoraInicio().minusMinutes(MARGEN_MINUTOS).isAfter(horaActual))
					.findFirst().orElse(null);

			Long minutosParaProximo = proximo != null
					? Duration.between(horaActual, proximo.getHoraInicio()).toMinutes()
					: null;

			resultado.add(new LaboratorioEstadoDto(laboratorio.getIdLaboratorio(), laboratorio.getNombre(),
					laboratorio.getEdificio(), laboratorio.getNLugares(), actual != null,
					actual != null ? horarioDetalleService.aDetalle(actual) : null,
					proximo != null ? horarioDetalleService.aDetalle(proximo) : null, minutosParaProximo));
		}

		resultado.sort(Comparator.comparing(LaboratorioEstadoDto::isOcupado).reversed()
				.thenComparing(LaboratorioEstadoDto::getNombreLaboratorio));
		return resultado;
	}

	public List<HorarioProximoDto> obtenerHorariosProximos(int limite) {
		LocalDateTime ahora = LocalDateTime.now();
		String dia = DiaSemanaUtil.nombreDia(ahora);
		LocalTime horaActual = ahora.toLocalTime();

		return horarioDao.findByDiaIgnoreCase(dia).stream()
				.filter(horario -> horario.getHoraInicio().minusMinutes(MARGEN_MINUTOS).isAfter(horaActual))
				.sorted(Comparator.comparing(Horario::getHoraInicio)).limit(limite)
				.map(horario -> new HorarioProximoDto(horarioDetalleService.aDetalle(horario),
						Duration.between(horaActual, horario.getHoraInicio()).toMinutes()))
				.toList();
	}

	private boolean dentroDelMargen(Horario horario, LocalTime horaActual) {
		LocalTime inicio = horario.getHoraInicio().minusMinutes(MARGEN_MINUTOS);
		LocalTime termino = horario.getHoraTermino().plusMinutes(MARGEN_MINUTOS);
		return !horaActual.isBefore(inicio) && !horaActual.isAfter(termino);
	}
}
