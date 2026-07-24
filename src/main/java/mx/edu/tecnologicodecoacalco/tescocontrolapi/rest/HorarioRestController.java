package mx.edu.tecnologicodecoacalco.tescocontrolapi.rest;

import java.util.Comparator;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Horario;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.UsuarioMateria;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.HorarioDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.UsuarioMateriaDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.rest.dto.HorarioDetalleDto;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.service.HorarioDetalleService;

@Slf4j
@RestController
@RequestMapping(value = "/tesco-control-api")
@RequiredArgsConstructor
public class HorarioRestController {

	private final HorarioDao horarioDao;
	private final UsuarioMateriaDao usuarioMateriaDao;
	private final HorarioDetalleService horarioDetalleService;

	@RequestMapping(value = "/horarios", method = RequestMethod.POST)
	public String guardarHorario(@RequestBody Horario horario) {
		String respuesta;
		try {
			horarioDao.save(horario);
			respuesta = "Horario registrado exitosamente";
		} catch (Exception exception) {
			respuesta = "No se pudo registrar el horario";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/horarios/{idHorario}", method = RequestMethod.PUT)
	public String modificarHorario(@PathVariable("idHorario") Integer idHorario, @RequestBody Horario horario) {
		String respuesta;
		try {
			if (horarioDao.existsById(idHorario)) {
				horario.setIdHorario(idHorario);
				horarioDao.save(horario);
				respuesta = "Se ha actualizado correctamente el horario.";
			} else {
				respuesta = "No se encontró el horario.";
			}
		} catch (Exception exception) {
			respuesta = "No se pudo actualizar el horario.";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/horarios/{idHorario}", method = RequestMethod.DELETE)
	public String eliminarHorarioPorId(@PathVariable("idHorario") Integer idHorario) {
		String respuesta;
		try {
			horarioDao.deleteById(idHorario);
			respuesta = "El horario se eliminó correctamente";
		} catch (Exception exception) {
			respuesta = "No se pudo eliminar el horario";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/horarios", method = RequestMethod.GET)
	public List<Horario> recuperarHorarios() {
		List<Horario> liHorarios;
		try {
			liHorarios = horarioDao.findAll();
		} catch (Exception exception) {
			liHorarios = null;
			log.error("No se pudo recuperar la lista de horarios", exception);
		}
		return liHorarios;
	}

	@RequestMapping(value = "/laboratorios/{idLaboratorio}/horarios", method = RequestMethod.GET)
	public List<Horario> recuperarHorariosDeLaboratorio(@PathVariable("idLaboratorio") Integer idLaboratorio) {
		return horarioDao.findByIdLaboratorio(idLaboratorio);
	}

	@RequestMapping(value = "/usuarios/{idUsuario}/horarios", method = RequestMethod.GET)
	public List<HorarioDetalleDto> recuperarHorariosDeUsuario(@PathVariable("idUsuario") Integer idUsuario) {
		List<Integer> idsMateria = usuarioMateriaDao.findByIdUsuario(idUsuario).stream()
				.map(UsuarioMateria::getIdMateria).toList();
		if (idsMateria.isEmpty()) {
			return List.of();
		}

		return horarioDetalleService.aDetalle(horarioDao.findByIdMateriaIn(idsMateria)).stream()
				.sorted(Comparator.comparing(HorarioDetalleDto::getDia).thenComparing(HorarioDetalleDto::getHoraInicio))
				.toList();
	}
}
