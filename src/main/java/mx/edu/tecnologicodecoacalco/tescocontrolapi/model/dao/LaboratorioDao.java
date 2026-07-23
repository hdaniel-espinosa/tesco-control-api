package mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Laboratorio;

@Transactional
@Repository
public interface LaboratorioDao extends CrudRepository<Laboratorio, Integer> {

	List<Laboratorio> findAll();
}
