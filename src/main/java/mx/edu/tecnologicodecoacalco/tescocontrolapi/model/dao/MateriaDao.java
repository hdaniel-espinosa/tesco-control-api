package mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Materia;

@Transactional
@Repository
public interface MateriaDao extends CrudRepository<Materia, Integer> {

	List<Materia> findAll();

	List<Materia> findByIdMateriaIn(List<Integer> idsMateria);
}
