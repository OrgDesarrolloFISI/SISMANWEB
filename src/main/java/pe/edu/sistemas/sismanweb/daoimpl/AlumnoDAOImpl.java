package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.AlumnoDAO;
import pe.edu.sistemas.sismanweb.entidades.Alumno;

@Repository
public class AlumnoDAOImpl implements AlumnoDAO {
	
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
	public void insertarAlumno(Alumno alumno) {
		try{
			iniciaOperacion();
			session.save(alumno);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();
		}
	}

	@Override
	public void actualizarAlumno(Alumno alumno) {
		try{
			iniciaOperacion();
			session.update(alumno);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();
		}
	}

	@Override
	public void eliminarAlumno(Alumno alumno) {
		try{
			iniciaOperacion();
			session.delete(alumno);
			tx.commit();		
		}catch(HibernateException he){
			manejaExcepcion(he);			
		}finally{
			session.close();
		}
	}

	@Override
	public List<Alumno> obtenerTodoAlumno() {
		List<Alumno> listaAlumno = null;
		try{
			iniciaOperacion();
			listaAlumno = (List<Alumno>)session.createQuery("from Alumno").setMaxResults(10).getResultList();	
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			//session.close();			
		}		
		
		return listaAlumno;
	}

	@Override
	public Alumno obtenerAlumnoxID(Integer idAlumno) {
		Alumno alumno = null;
		try{
			iniciaOperacion();
			alumno = session.get(Alumno.class, idAlumno);
		}catch (HibernateException he) {
			manejaExcepcion(he);
		}finally {
			session.close();
		}
		return alumno;
	}

}
