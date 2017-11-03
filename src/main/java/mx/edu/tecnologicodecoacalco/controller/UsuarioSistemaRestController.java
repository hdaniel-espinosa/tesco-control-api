package mx.edu.tecnologicodecoacalco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.tecnologicodecoacalco.dao.UsuarioSistemaDao;
import mx.edu.tecnologicodecoacalco.dto.UsuarioSistemaDto;

@RestController
public class UsuarioSistemaRestController {
	
	@Autowired
	private UsuarioSistemaDao usuarioSistemaDao;
	
	@RequestMapping("/usuarios/crear")
	public String crearUsuarioSistema(Integer idUsuarioSistema, String nombre, String apPaterno, String apMaterno,
			String contrasena, Byte activo, String tipo) {
		
		UsuarioSistemaDto usuarioSistemaDto = null;
		
		try {
			usuarioSistemaDto = new UsuarioSistemaDto(idUsuarioSistema, nombre, apPaterno, apMaterno, contrasena, activo, tipo);
			usuarioSistemaDao.save(usuarioSistemaDto);
		}catch (Exception e) {
			return "Error al crear usuario: " + e.toString();
		}
		return "Usuario creado con éxito: id = " + usuarioSistemaDto.getIdUsuarioSistema();
	}
}
