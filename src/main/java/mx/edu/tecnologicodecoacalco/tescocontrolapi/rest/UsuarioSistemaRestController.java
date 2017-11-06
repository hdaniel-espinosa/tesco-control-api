package mx.edu.tecnologicodecoacalco.tescocontrolapi.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.UsuarioSistema;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.UsuarioSistemaDao;

@Log4j
@RestController
@RequestMapping(value = "/tesco-control-api")
public class UsuarioSistemaRestController {

	@Autowired
	private UsuarioSistemaDao usuarioSistemaDao;

	@RequestMapping(value = "/usuarios-sistema", method = RequestMethod.POST)
	public String guardarUsuario(@RequestBody UsuarioSistema usuarioSistema) {
		String respuesta = null;
		try {
			usuarioSistemaDao.save(usuarioSistema);
			respuesta = "Usuario creado exitosamente";
		} catch (Exception exception) {
			respuesta = "No se pudo crear el usuario";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/usuarios-sistema/{idUsuarioSistema}", method = RequestMethod.DELETE)
	public String eliminarUsuarioSistemaPorId(@PathVariable("idUsuarioSistema") Integer idUsuarioSistema) {
		String respuesta = null;
		try {
			usuarioSistemaDao.delete(idUsuarioSistema);
			respuesta = "El usuario se eliminó correctamente";
		} catch (Exception exception) {
			respuesta = "No se pudo eliminar el usuario";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/usuarios-sistema", method = RequestMethod.GET)
	public List<UsuarioSistema> recuperarUsuariosSistema() {
		log.debug("inicio");
		List<UsuarioSistema> liUsuarioSistemas = null;
		try {
			liUsuarioSistemas = usuarioSistemaDao.findAll();
		} catch (Exception exception) {
			liUsuarioSistemas = null;
			log.error(exception);
		}
		log.debug("fin");
		return liUsuarioSistemas;
	}
}
