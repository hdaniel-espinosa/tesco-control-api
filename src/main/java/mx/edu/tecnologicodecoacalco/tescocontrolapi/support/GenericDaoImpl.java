//package mx.edu.tecnologicodecoacalco.tescocontrolapi.support;
//
//import java.io.Serializable;
//
//import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
//
//public class GenericDaoImpl<T extends GenericDto,I extends Serializable> extends HibernateDaoSupport implements GenericDao<T, I>{
//	
//	private final transient Class<T> entityClass;
//	
//	protected GenericDaoImpl(final Class<T> entityClass) {
//		this.entityClass = entityClass;
//	}
//
//	@Override
//	public void save(T dto) throws RuntimeException {
//		getHibernateTemplate().save(dto);
//		getHibernateTemplate().flush();
//		
//	}
//
//	@Override
//	public void delete(T dto) throws RuntimeException {
//		getHibernateTemplate().delete(dto);
//		getHibernateTemplate().flush();
//		
//	}
//
//	@Override
//	public void update(T dto) throws RuntimeException {
//		getHibernateTemplate().update(dto);
//		getHibernateTemplate().flush();
//		
//	}
//
//	@Override
//	public T findByPk(I id) {
//		T resultado = null;
//		resultado = getHibernateTemplate().get(entityClass, id);
//		return resultado;
//	}
//
//	}
