package mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.EstadoLaboratorio;

@Transactional
@Repository
public interface EstadoLaboratorioDao extends CrudRepository<EstadoLaboratorio, Integer> {

	Optional<EstadoLaboratorio> findTopByIdLaboratorioOrderByFechaHoraDesc(Integer idLaboratorio);
}
