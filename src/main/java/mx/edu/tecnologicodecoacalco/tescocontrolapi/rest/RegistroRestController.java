package mx.edu.tecnologicodecoacalco.tescocontrolapi.rest;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Registro;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.UsuarioTarjeta;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.RegistroDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.UsuarioDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.UsuarioTarjetaDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.rest.dto.RegistroDto;

/**
 * Bitácora de accesos: los registros los crea AccesoService, este
 * controlador solo expone su consulta, agregando el nombre del maestro
 * dueño de la tarjeta (si la tarjeta sigue asignada a alguien).
 */
@Slf4j
@RestController
@RequestMapping(value = "/tesco-control-api")
@RequiredArgsConstructor
public class RegistroRestController {

	private final RegistroDao registroDao;
	private final UsuarioTarjetaDao usuarioTarjetaDao;
	private final UsuarioDao usuarioDao;

	@RequestMapping(value = "/registros", method = RequestMethod.GET)
	public List<RegistroDto> recuperarRegistros() {
		List<RegistroDto> liRegistros;
		try {
			liRegistros = aDto(registroDao.findAllByOrderByFechaHoraDesc());
		} catch (Exception exception) {
			liRegistros = null;
			log.error("No se pudo recuperar la bitácora de accesos", exception);
		}
		return liRegistros;
	}

	@RequestMapping(value = "/laboratorios/{idLaboratorio}/registros", method = RequestMethod.GET)
	public List<RegistroDto> recuperarRegistrosDeLaboratorio(@PathVariable("idLaboratorio") Integer idLaboratorio) {
		return aDto(registroDao.findByIdLaboratorioOrderByFechaHoraDesc(idLaboratorio));
	}

	@RequestMapping(value = "/tarjetas/{idTarjeta}/registros", method = RequestMethod.GET)
	public List<RegistroDto> recuperarRegistrosDeTarjeta(@PathVariable("idTarjeta") String idTarjeta) {
		return aDto(registroDao.findByIdTarjetaOrderByFechaHoraDesc(idTarjeta));
	}

	private List<RegistroDto> aDto(List<Registro> registros) {
		return registros.stream().map(registro -> new RegistroDto(registro.getIdRegistro(), registro.getIdTarjeta(),
				registro.getIdLaboratorio(), registro.getFechaHora(), Boolean.TRUE.equals(registro.getAbrio()),
				nombreMaestro(registro.getIdTarjeta()))).toList();
	}

	private String nombreMaestro(String idTarjeta) {
		return usuarioTarjetaDao.findByIdTarjeta(idTarjeta).map(UsuarioTarjeta::getIdUsuario)
				.flatMap(usuarioDao::findById).map(usuario -> usuario.getNombre() + " " + usuario.getApPaterno())
				.orElse(null);
	}
}
