package mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.UsuarioSistema;

@Transactional
@Repository
public interface UsuarioSistemaDao extends CrudRepository<UsuarioSistema, Integer>{
	
	public List<UsuarioSistema> findAll();
	
	public UsuarioSistema findByIdUsuarioSistema(Integer idUsuarioSistema);
}
