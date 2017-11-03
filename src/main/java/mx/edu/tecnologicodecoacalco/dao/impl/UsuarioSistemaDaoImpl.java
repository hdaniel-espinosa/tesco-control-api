//package mx.edu.tecnologicodecoacalco.dao.impl;
//
//import java.util.List;
//
//import org.hibernate.Criteria;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.orm.hibernate5.HibernateCallback;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import lombok.extern.log4j.Log4j;
//import mx.edu.tecnologicodecoacalco.dao.UsuarioSistemaDao;
//import mx.edu.tecnologicodecoacalco.dto.UsuarioSistemaDto;
//import mx.edu.tecnologicodecoacalco.tescocontrolapi.support.GenericDaoImpl;;
//
//@Log4j
//@Service("UsuarioSistemaDao")
//@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
//public class UsuarioSistemaDaoImpl extends GenericDaoImpl<UsuarioSistemaDto, String> implements UsuarioSistemaDao {
//
//	public UsuarioSistemaDaoImpl() {
//		super(UsuarioSistemaDto.class);
//		log.debug("inicio");
//		log.debug("fin");
//	}
//
//	@Override
//	public List<UsuarioSistemaDto> recuperarUsuarioSistemaPorFiltros(String nombre, String apPaterno, String apMaterno,
//			String password, Byte activo, String tipo) throws RuntimeException {
//		log.debug("inicio");
//		List<UsuarioSistemaDto> listUsuarioSistemaDtos = null;
//		String mensajeExcepcion = null;
//
//		try {
//			listUsuarioSistemaDtos = getHibernateTemplate().execute(new HibernateCallback<List<UsuarioSistemaDto>>() {
//				@SuppressWarnings({ "deprecation", "unchecked" })
//				public List<UsuarioSistemaDto> doInHibernate(Session session) throws HibernateException {
//					log.debug("inicio");
//					List<UsuarioSistemaDto> listUsuarioSistemaAux = null;
//					Criteria criteria = session.createCriteria(UsuarioSistemaDto.class)
//							.addOrder(Order.desc("apPaterno"));
//
//					{ // VALIDACION DE PARAMETROS NULOS
//						if ((nombre != null) && (!nombre.trim().isEmpty())) {
//							log.debug("nombre: " + nombre);
//							criteria.add(Restrictions.like("nombre", "%" + nombre + "%"));
//						}
//						if ((apPaterno != null) && (!apPaterno.trim().isEmpty())) {
//							log.debug("apPaterno: " + apPaterno);
//							criteria.add(Restrictions.like("apPaterno", "%" + apPaterno + "%"));
//						}
//						if ((apMaterno != null) && (!apMaterno.trim().isEmpty())) {
//							log.debug("apMaterno: " + apMaterno);
//							criteria.add(Restrictions.like("apMaterno", "%" + apMaterno + "%"));
//						}
//						if ((password != null) && (!password.trim().isEmpty())) {
//							log.debug("password: " + password);
//							criteria.add(Restrictions.like("password", "%" + password + "%"));
//						}
//						if (activo != null) {
//							log.debug("activo: " + activo);
//							criteria.add(Restrictions.eq("activo", activo));
//						}
//						if ((tipo != null) && (!tipo.trim().isEmpty())) {
//							log.debug("tipo: " + tipo);
//							criteria.add(Restrictions.like("tipo", "%" + tipo + "%"));
//						}
//					}
//
//					{ // RECUPERAR LISTA DE USUARIOSISTEMADTO
//						listUsuarioSistemaAux = (List<UsuarioSistemaDto>) criteria.list();
//						log.debug("fin");
//						return listUsuarioSistemaAux;
//					}
//				}
//			});
//		} catch (RuntimeException runtimeException) {
//			throw new RuntimeException(runtimeException.getMessage(), runtimeException);
//		} catch (Exception exception) {
//			mensajeExcepcion = "Ocurrió un error inesperado en el sistema a nivel [DAO] en el método [recuperarUsuarioSistemaPorFiltros].";
//			log.error(mensajeExcepcion + " " + exception.getMessage());
//		}
//
//		log.debug("fin");
//		return listUsuarioSistemaDtos;
//	}
//
//	@Override
//	public <S extends UsuarioSistemaDto> Iterable<S> save(Iterable<S> entities) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public UsuarioSistemaDto findOne(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean exists(Integer id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public Iterable<UsuarioSistemaDto> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Iterable<UsuarioSistemaDto> findAll(Iterable<Integer> ids) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public long count() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void delete(Integer id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delete(Iterable<? extends UsuarioSistemaDto> entities) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAll() {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
