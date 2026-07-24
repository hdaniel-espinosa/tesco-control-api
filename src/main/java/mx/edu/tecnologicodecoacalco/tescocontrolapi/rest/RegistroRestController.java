package mx.edu.tecnologicodecoacalco.tescocontrolapi.rest;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Registro;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.RegistroDao;

/**
 * Bitácora de accesos: los registros los crea AccesoService, este
 * controlador solo expone su consulta.
 */
@Slf4j
@RestController
@RequestMapping(value = "/tesco-control-api")
@RequiredArgsConstructor
public class RegistroRestController {

	private final RegistroDao registroDao;

	@RequestMapping(value = "/registros", method = RequestMethod.GET)
	public List<Registro> recuperarRegistros() {
		List<Registro> liRegistros;
		try {
			liRegistros = registroDao.findAllByOrderByFechaHoraDesc();
		} catch (Exception exception) {
			liRegistros = null;
			log.error("No se pudo recuperar la bitácora de accesos", exception);
		}
		return liRegistros;
	}

	@RequestMapping(value = "/laboratorios/{idLaboratorio}/registros", method = RequestMethod.GET)
	public List<Registro> recuperarRegistrosDeLaboratorio(@PathVariable("idLaboratorio") Integer idLaboratorio) {
		return registroDao.findByIdLaboratorioOrderByFechaHoraDesc(idLaboratorio);
	}

	@RequestMapping(value = "/tarjetas/{idTarjeta}/registros", method = RequestMethod.GET)
	public List<Registro> recuperarRegistrosDeTarjeta(@PathVariable("idTarjeta") String idTarjeta) {
		return registroDao.findByIdTarjetaOrderByFechaHoraDesc(idTarjeta);
	}
}
