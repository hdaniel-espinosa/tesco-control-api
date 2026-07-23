package mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Registro;

@Transactional
@Repository
public interface RegistroDao extends CrudRepository<Registro, Integer> {

	List<Registro> findByIdLaboratorioOrderByFechaHoraDesc(Integer idLaboratorio);

	List<Registro> findByIdTarjetaOrderByFechaHoraDesc(String idTarjeta);

	List<Registro> findAllByOrderByFechaHoraDesc();
}
