package mx.edu.tecnologicodecoacalco.tescocontrolapi.rest;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Laboratorio;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.LaboratorioDao;

@Slf4j
@RestController
@RequestMapping(value = "/tesco-control-api")
@RequiredArgsConstructor
public class LaboratorioRestController {

	private final LaboratorioDao laboratorioDao;

	@RequestMapping(value = "/laboratorios", method = RequestMethod.POST)
	public String guardarLaboratorio(@RequestBody Laboratorio laboratorio) {
		String respuesta;
		try {
			laboratorioDao.save(laboratorio);
			respuesta = "Laboratorio registrado exitosamente";
		} catch (Exception exception) {
			respuesta = "No se pudo registrar el laboratorio";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/laboratorios/{idLaboratorio}", method = RequestMethod.PUT)
	public String modificarLaboratorio(@PathVariable("idLaboratorio") Integer idLaboratorio,
			@RequestBody Laboratorio laboratorio) {
		String respuesta;
		try {
			if (laboratorioDao.existsById(idLaboratorio)) {
				laboratorio.setIdLaboratorio(idLaboratorio);
				laboratorioDao.save(laboratorio);
				respuesta = "Se ha actualizado correctamente el laboratorio.";
			} else {
				respuesta = "No se encontró el laboratorio.";
			}
		} catch (Exception exception) {
			respuesta = "No se pudo actualizar el laboratorio.";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/laboratorios/{idLaboratorio}", method = RequestMethod.DELETE)
	public String eliminarLaboratorioPorId(@PathVariable("idLaboratorio") Integer idLaboratorio) {
		String respuesta;
		try {
			laboratorioDao.deleteById(idLaboratorio);
			respuesta = "El laboratorio se eliminó correctamente";
		} catch (Exception exception) {
			respuesta = "No se pudo eliminar el laboratorio";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/laboratorios/{idLaboratorio}", method = RequestMethod.GET)
	public Laboratorio recuperarLaboratorio(@PathVariable("idLaboratorio") Integer idLaboratorio) {
		return laboratorioDao.findById(idLaboratorio).orElse(null);
	}

	@RequestMapping(value = "/laboratorios", method = RequestMethod.GET)
	public List<Laboratorio> recuperarLaboratorios() {
		List<Laboratorio> liLaboratorios;
		try {
			liLaboratorios = laboratorioDao.findAll();
		} catch (Exception exception) {
			liLaboratorios = null;
			log.error("No se pudo recuperar la lista de laboratorios", exception);
		}
		return liLaboratorios;
	}
}
