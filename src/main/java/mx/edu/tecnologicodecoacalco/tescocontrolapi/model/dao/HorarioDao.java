package mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.Horario;

@Transactional
@Repository
public interface HorarioDao extends CrudRepository<Horario, Integer> {

	List<Horario> findAll();

	List<Horario> findByIdLaboratorio(Integer idLaboratorio);

	List<Horario> findByIdLaboratorioAndDiaIgnoreCaseAndIdMateriaIn(Integer idLaboratorio, String dia,
			List<Integer> idsMateria);
}
