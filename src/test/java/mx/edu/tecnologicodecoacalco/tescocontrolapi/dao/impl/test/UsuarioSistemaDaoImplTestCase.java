package mx.edu.tecnologicodecoacalco.tescocontrolapi.dao.impl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.log4j.Log4j;
import mx.edu.tecnologicodecoacalco.dao.UsuarioSistemaDao;
import mx.edu.tecnologicodecoacalco.dto.UsuarioSistemaDto;


@Log4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioSistemaDaoImplTestCase {
	
	@Autowired
	private UsuarioSistemaDao usuarioSistemaDao; 

	@Test
	public void testSave() {
		String nombre = "Hector";
		String apPaterno = "Espinosa";
		String apMaterno = "Moreno";
		Byte activo = 1;
		String contrasena = "daniel14081";
		String tipo = "Administrador";
		Integer idUsuarioSistema = 123;
		
		
		UsuarioSistemaDto usuarioSistemaDto = new UsuarioSistemaDto();
		
		usuarioSistemaDto.setIdUsuarioSistema(idUsuarioSistema);
		usuarioSistemaDto.setNombre(nombre);
		usuarioSistemaDto.setApPaterno(apPaterno);
		usuarioSistemaDto.setApMaterno(apMaterno);
		usuarioSistemaDto.setContrasena(contrasena);
		usuarioSistemaDto.setTipo(tipo);
		usuarioSistemaDto.setActivo(activo);
		
		try {
			usuarioSistemaDao.save(usuarioSistemaDto);
		}catch (Exception e) {
			log.debug(e.getMessage(), e);
		}
	}
}
