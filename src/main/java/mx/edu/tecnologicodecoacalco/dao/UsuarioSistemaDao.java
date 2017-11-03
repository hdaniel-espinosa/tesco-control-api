package mx.edu.tecnologicodecoacalco.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import mx.edu.tecnologicodecoacalco.dto.UsuarioSistemaDto;

@Component
public interface UsuarioSistemaDao extends CrudRepository<UsuarioSistemaDto, Integer>{
	
	public List<UsuarioSistemaDto> recuperarUsuarioSistemaPorFiltros(String nombre, String apPaterno, String apMaterno, String password, Byte activo, String tipo) throws RuntimeException;
}
