package mx.edu.tecnologicodecoacalco.tescocontrolapi.rest;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Tarjeta;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.UsuarioTarjeta;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.TarjetaDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.UsuarioTarjetaDao;

@Slf4j
@RestController
@RequestMapping(value = "/tesco-control-api")
@RequiredArgsConstructor
public class TarjetaRestController {

	private final TarjetaDao tarjetaDao;
	private final UsuarioTarjetaDao usuarioTarjetaDao;

	@RequestMapping(value = "/tarjetas", method = RequestMethod.POST)
	public String guardarTarjeta(@RequestBody Tarjeta tarjeta) {
		String respuesta;
		try {
			tarjetaDao.save(tarjeta);
			respuesta = "Tarjeta registrada exitosamente";
		} catch (Exception exception) {
			respuesta = "No se pudo registrar la tarjeta";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/tarjetas/{idTarjeta}", method = RequestMethod.PUT)
	public String modificarTarjeta(@PathVariable("idTarjeta") String idTarjeta, @RequestBody Tarjeta tarjeta) {
		String respuesta;
		try {
			if (tarjetaDao.existsById(idTarjeta)) {
				tarjeta.setIdTarjeta(idTarjeta);
				tarjetaDao.save(tarjeta);
				respuesta = "Se ha actualizado correctamente la tarjeta.";
			} else {
				respuesta = "No se encontró la tarjeta.";
			}
		} catch (Exception exception) {
			respuesta = "No se pudo actualizar la tarjeta.";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/tarjetas/{idTarjeta}", method = RequestMethod.DELETE)
	public String eliminarTarjetaPorId(@PathVariable("idTarjeta") String idTarjeta) {
		String respuesta;
		try {
			tarjetaDao.deleteById(idTarjeta);
			respuesta = "La tarjeta se eliminó correctamente";
		} catch (Exception exception) {
			respuesta = "No se pudo eliminar la tarjeta";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/tarjetas", method = RequestMethod.GET)
	public List<Tarjeta> recuperarTarjetas() {
		List<Tarjeta> liTarjetas;
		try {
			liTarjetas = tarjetaDao.findAll();
		} catch (Exception exception) {
			liTarjetas = null;
			log.error("No se pudo recuperar la lista de tarjetas", exception);
		}
		return liTarjetas;
	}

	@RequestMapping(value = "/tarjetas/{idTarjeta}/asignar/{idUsuario}", method = RequestMethod.POST)
	public String asignarTarjetaAUsuario(@PathVariable("idTarjeta") String idTarjeta,
			@PathVariable("idUsuario") Integer idUsuario) {
		String respuesta;
		try {
			usuarioTarjetaDao.save(new UsuarioTarjeta(idUsuario, idTarjeta));
			respuesta = "Tarjeta asignada correctamente al usuario";
		} catch (Exception exception) {
			respuesta = "No se pudo asignar la tarjeta al usuario";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/tarjetas/{idTarjeta}/asignar", method = RequestMethod.DELETE)
	public String desasignarTarjeta(@PathVariable("idTarjeta") String idTarjeta) {
		String respuesta;
		try {
			usuarioTarjetaDao.findByIdTarjeta(idTarjeta).ifPresent(usuarioTarjetaDao::delete);
			respuesta = "Tarjeta desasignada correctamente";
		} catch (Exception exception) {
			respuesta = "No se pudo desasignar la tarjeta";
			log.error(respuesta, exception);
		}
		return respuesta;
	}
}
