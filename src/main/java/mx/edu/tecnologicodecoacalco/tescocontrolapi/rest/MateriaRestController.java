package mx.edu.tecnologicodecoacalco.tescocontrolapi.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Materia;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Usuario;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.UsuarioMateria;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.UsuarioMateriaId;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.MateriaDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.UsuarioDao;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao.UsuarioMateriaDao;

@Slf4j
@RestController
@RequestMapping(value = "/tesco-control-api")
@RequiredArgsConstructor
public class MateriaRestController {

	private final MateriaDao materiaDao;
	private final UsuarioMateriaDao usuarioMateriaDao;
	private final UsuarioDao usuarioDao;

	@RequestMapping(value = "/materias", method = RequestMethod.POST)
	public String guardarMateria(@RequestBody Materia materia) {
		String respuesta;
		try {
			materiaDao.save(materia);
			respuesta = "Materia registrada exitosamente";
		} catch (Exception exception) {
			respuesta = "No se pudo registrar la materia";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/materias/{idMateria}", method = RequestMethod.PUT)
	public String modificarMateria(@PathVariable("idMateria") Integer idMateria, @RequestBody Materia materia) {
		String respuesta;
		try {
			if (materiaDao.existsById(idMateria)) {
				materia.setIdMateria(idMateria);
				materiaDao.save(materia);
				respuesta = "Se ha actualizado correctamente la materia.";
			} else {
				respuesta = "No se encontró la materia.";
			}
		} catch (Exception exception) {
			respuesta = "No se pudo actualizar la materia.";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/materias/{idMateria}", method = RequestMethod.DELETE)
	public String eliminarMateriaPorId(@PathVariable("idMateria") Integer idMateria) {
		String respuesta;
		try {
			materiaDao.deleteById(idMateria);
			respuesta = "La materia se eliminó correctamente";
		} catch (Exception exception) {
			respuesta = "No se pudo eliminar la materia";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/materias", method = RequestMethod.GET)
	public List<Materia> recuperarMaterias() {
		List<Materia> liMaterias;
		try {
			liMaterias = materiaDao.findAll();
		} catch (Exception exception) {
			liMaterias = null;
			log.error("No se pudo recuperar la lista de materias", exception);
		}
		return liMaterias;
	}

	@RequestMapping(value = "/materias/{idMateria}/maestros", method = RequestMethod.GET)
	public List<Usuario> recuperarMaestrosDeMateria(@PathVariable("idMateria") Integer idMateria) {
		return usuarioMateriaDao.findByIdMateria(idMateria).stream().map(UsuarioMateria::getIdUsuario)
				.map(usuarioDao::findById).filter(Optional::isPresent).map(Optional::get).toList();
	}

	@RequestMapping(value = "/usuarios/{idUsuario}/materias", method = RequestMethod.GET)
	public List<Integer> recuperarMateriasDeUsuario(@PathVariable("idUsuario") Integer idUsuario) {
		return usuarioMateriaDao.findByIdUsuario(idUsuario).stream().map(UsuarioMateria::getIdMateria).toList();
	}

	@RequestMapping(value = "/usuarios/{idUsuario}/materias/{idMateria}", method = RequestMethod.POST)
	public String asignarMateriaAUsuario(@PathVariable("idUsuario") Integer idUsuario,
			@PathVariable("idMateria") Integer idMateria) {
		String respuesta;
		try {
			usuarioMateriaDao.save(new UsuarioMateria(idUsuario, idMateria));
			respuesta = "Materia asignada correctamente al maestro";
		} catch (Exception exception) {
			respuesta = "No se pudo asignar la materia al maestro";
			log.error(respuesta, exception);
		}
		return respuesta;
	}

	@RequestMapping(value = "/usuarios/{idUsuario}/materias/{idMateria}", method = RequestMethod.DELETE)
	public String desasignarMateriaDeUsuario(@PathVariable("idUsuario") Integer idUsuario,
			@PathVariable("idMateria") Integer idMateria) {
		String respuesta;
		try {
			usuarioMateriaDao.deleteById(new UsuarioMateriaId(idUsuario, idMateria));
			respuesta = "Materia desasignada correctamente del maestro";
		} catch (Exception exception) {
			respuesta = "No se pudo desasignar la materia del maestro";
			log.error(respuesta, exception);
		}
		return respuesta;
	}
}
