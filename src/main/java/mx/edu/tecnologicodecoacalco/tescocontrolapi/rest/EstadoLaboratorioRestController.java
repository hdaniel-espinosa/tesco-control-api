package mx.edu.tecnologicodecoacalco.tescocontrolapi.rest;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.EstadoLaboratorio;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.EstadoLaboratorioDao;

/**
 * Recibe las lecturas de los sensores de temperatura/humedad de cada
 * laboratorio y expone la última lectura conocida.
 */
@Slf4j
@RestController
@RequestMapping(value = "/tesco-control-api")
@RequiredArgsConstructor
public class EstadoLaboratorioRestController {

	private final EstadoLaboratorioDao estadoLaboratorioDao;

	@RequestMapping(value = "/estados-laboratorio", method = RequestMethod.POST)
	public String registrarEstado(@RequestBody EstadoLaboratorio estado) {
		String respuesta;
		try {
			estado.setFechaHora(LocalDateTime.now());
			estadoLaboratorioDao.save(estado);
			respuesta = "Estado del laboratorio registrado exitosamente";
		} catch (Exception exception) {
			respuesta = "No se pudo registrar el estado del laboratorio";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/laboratorios/{idLaboratorio}/estado", method = RequestMethod.GET)
	public EstadoLaboratorio recuperarUltimoEstado(@PathVariable("idLaboratorio") Integer idLaboratorio) {
		return estadoLaboratorioDao.findTopByIdLaboratorioOrderByFechaHoraDesc(idLaboratorio).orElse(null);
	}
}
