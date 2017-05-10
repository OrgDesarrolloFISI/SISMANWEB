package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.PersonaDAO;
import pe.edu.sistemas.sismanweb.entidades.Persona;

@Repository
public class PersonaDAOImpl implements PersonaDAO {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction tx;
	
	@Autowired
	public PersonaDAOImpl(SessionFactory sessionFactory){
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
	public void insertarPersona(Persona persona) throws HibernateException {
		try{
			iniciaOperacion();
			session.save(persona);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();
		}		
	}

	@Override
	public void actualizarPersona(Persona persona) {
		try{
			iniciaOperacion();
			session.update(persona);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();
		}
	}

	@Override
	public void eliminarPersona(Persona persona) {
		try{
			iniciaOperacion();
			session.delete(persona);
			tx.commit();		
		}catch(HibernateException he){
			manejaExcepcion(he);			
		}finally{
			session.close();
		}
	}

	@Override
	public List<Persona> obtenerTodoPersona() {
		List<Persona> listaPersonas = null;
		try{
			iniciaOperacion();
			listaPersonas = (List<Persona>)session.createQuery("from Persona").setMaxResults(10).list();	
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();			
		}		
		
		return listaPersonas;
	}

	@Override
	public Persona obtenerPersonaxID(Integer idPersona) {
		Persona persona = null;
		try{
			iniciaOperacion();
			persona = session.get(Persona.class, idPersona);
		}catch (HibernateException he) {
			manejaExcepcion(he);
		}finally {
			session.close();
		}
		return persona;		
	}
	

}
