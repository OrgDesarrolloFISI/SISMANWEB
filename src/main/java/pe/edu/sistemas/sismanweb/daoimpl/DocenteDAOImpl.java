package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.DocenteDAO;
import pe.edu.sistemas.sismanweb.entidades.Alumno;
import pe.edu.sistemas.sismanweb.entidades.Docente;

@Repository
public class DocenteDAOImpl implements DocenteDAO {
	
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction tx;
	
	@Autowired
	public DocenteDAOImpl(SessionFactory sessionFactory){
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
	public void insertarDocente(Docente docente) {
		try{
			iniciaOperacion();
			session.save(docente);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();
		}		
	}

	@Override
	public void actualizarDocente(Docente docente) {
		try{
			iniciaOperacion();
			session.update(docente);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();
		}		
	}

	@Override
	public void eliminarDocente(Docente docente) {
		try{
			iniciaOperacion();
			session.delete(docente);
			tx.commit();		
		}catch(HibernateException he){
			manejaExcepcion(he);			
		}finally{
			session.close();
		}
		
	}

	@Override
	public List<Docente> obtenerTodoDocente() {
		List<Docente> listaDocente = null;
		try{
			iniciaOperacion();
			listaDocente = (List<Docente>)session.createQuery("from Docente").setMaxResults(10).list();	
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			//session.close();			
		}			
		return listaDocente;
	}

	@Override
	public Docente obtenerDocentexID(Integer idDocente) {
		Docente docente = null;
		try{
			iniciaOperacion();
			docente = session.get(Docente.class, idDocente);
		}catch (HibernateException he) {
			manejaExcepcion(he);
		}finally {
			session.close();
		}
		return docente;
	}
}
