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
	private Session sesion;
	private Transaction tx;
	
	@Autowired
	public PersonaDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
		System.out.println("llego");
		
	}
	
	private void iniciaOperacion() throws HibernateException {
		sesion = sessionFactory.openSession();
		tx = sesion.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("Ocurrió un error en el acceso a datos", he);
	}
	
	@Override
	public void insertarPersona(Persona persona) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizarPersona(Persona persona) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarPersona(Persona persona) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Persona> obtenerTodoPersona() {
		List<Persona> listaPersonas = null;
		try{
			iniciaOperacion();
			listaPersonas = (List<Persona>)sesion.createQuery("from Persona").setMaxResults(10).list();	
		}finally{
			//sesion.close();			
		}		
		
		return listaPersonas;
	}

	@Override
	public List<Persona> obtenerPersonaxID(Integer idPersona) {
		// TODO Auto-generated method stub
		return null;		
	}
	

}
