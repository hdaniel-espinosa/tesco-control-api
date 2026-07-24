package mx.edu.tecnologicodecoacalco.tescocontrolapi.rest;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Usuario;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.UsuarioDao;

@Slf4j
@RestController
@RequestMapping(value = "/tesco-control-api")
@RequiredArgsConstructor
public class UsuarioRestController {

	private final UsuarioDao usuarioDao;

	@RequestMapping(value = "/usuarios", method = RequestMethod.POST)
	public String guardarUsuario(@RequestBody Usuario usuario) {
		String respuesta;
		try {
			usuarioDao.save(usuario);
			respuesta = "Maestro registrado exitosamente";
		} catch (Exception exception) {
			respuesta = "No se pudo registrar el maestro";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/usuarios/{idUsuario}", method = RequestMethod.PUT)
	public String modificarUsuario(@PathVariable("idUsuario") Integer idUsuario, @RequestBody Usuario usuario) {
		String respuesta;
		try {
			if (usuarioDao.existsById(idUsuario)) {
				usuario.setIdUsuario(idUsuario);
				usuarioDao.save(usuario);
				respuesta = "Se ha actualizado correctamente el maestro.";
			} else {
				respuesta = "No se encontró el maestro.";
			}
		} catch (Exception exception) {
			respuesta = "No se pudo actualizar el maestro.";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/usuarios/{idUsuario}", method = RequestMethod.DELETE)
	public String eliminarUsuarioPorId(@PathVariable("idUsuario") Integer idUsuario) {
		String respuesta;
		try {
			usuarioDao.deleteById(idUsuario);
			respuesta = "El maestro se eliminó correctamente";
		} catch (Exception exception) {
			respuesta = "No se pudo eliminar el maestro";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/usuarios/{idUsuario}", method = RequestMethod.GET)
	public Usuario recuperarUsuario(@PathVariable("idUsuario") Integer idUsuario) {
		return usuarioDao.findById(idUsuario).orElse(null);
	}

	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public List<Usuario> recuperarUsuarios() {
		List<Usuario> liUsuarios;
		try {
			liUsuarios = usuarioDao.findAll();
		} catch (Exception exception) {
			liUsuarios = null;
			log.error("No se pudo recuperar la lista de maestros", exception);
		}
		return liUsuarios;
	}
}
