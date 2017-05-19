package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.ClaseDAO;
import pe.edu.sistemas.sismanweb.entidades.Clase;

@Repository
public class ClaseDAOImpl implements ClaseDAO{
	
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction tx;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;		
	}
	
	private void iniciaOperacion() throws HibernateException {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he){
		tx.rollback();
		he.printStackTrace();
		throw new HibernateException("Ocurrió un error en el acceso a datos", he);
	}

	@Override
	public List<Clase> obtenerTodoClase() {
		List<Clase> listaClase= null;
		try{
			iniciaOperacion();
			listaClase = (List<Clase>)session.createQuery("from Clase").setMaxResults(10).list();	
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();			
		}		
		
		return listaClase;
	}

	@Override
	public Clase obtenerClasexID(Integer idClase) {
		Clase clase = null;
		try{
			iniciaOperacion();
			clase = session.get(Clase.class, idClase);
		}catch (HibernateException he) {
			manejaExcepcion(he);
		}finally {
			session.close();
		}
		return clase;
	}

	

}
