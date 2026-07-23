package mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.UsuarioMateria;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.UsuarioMateriaId;

@Transactional
@Repository
public interface UsuarioMateriaDao extends CrudRepository<UsuarioMateria, UsuarioMateriaId> {

	List<UsuarioMateria> findByIdUsuario(Integer idUsuario);

	List<UsuarioMateria> findByIdMateria(Integer idMateria);
}
