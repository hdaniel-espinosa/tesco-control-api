package mx.edu.tecnologicodecoacalco.tescocontrolapi.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.UsuarioTarjeta;
import mx.edu.tecnologicodecoacalco.tescocontrolapi.model.UsuarioTarjetaId;

@Transactional
@Repository
public interface UsuarioTarjetaDao extends CrudRepository<UsuarioTarjeta, UsuarioTarjetaId> {

	Optional<UsuarioTarjeta> findByIdTarjeta(String idTarjeta);

	List<UsuarioTarjeta> findByIdUsuario(Integer idUsuario);
}
