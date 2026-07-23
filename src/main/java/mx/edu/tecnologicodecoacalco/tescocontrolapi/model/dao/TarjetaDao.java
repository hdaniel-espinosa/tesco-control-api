package mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Tarjeta;

@Transactional
@Repository
public interface TarjetaDao extends CrudRepository<Tarjeta, String> {

	List<Tarjeta> findAll();
}
