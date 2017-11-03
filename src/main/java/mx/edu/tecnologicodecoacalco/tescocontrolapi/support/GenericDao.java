package mx.edu.tecnologicodecoacalco.tescocontrolapi.support;

import java.io.Serializable;

public interface GenericDao <T extends GenericDto, I extends Serializable>{
	
	public void save(T dto) throws RuntimeException;
	public void delete(T dto) throws RuntimeException;
	public void update(T dto) throws RuntimeException;
	public T findByPk(I id);
}
