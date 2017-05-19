package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.TipoAlumnoDAO;
import pe.edu.sistemas.sismanweb.entidades.TipoAlumno;

@Repository
public class TipoAlumnoDAOImpl implements TipoAlumnoDAO {
	
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
	public List<TipoAlumno> obtenerTodoTipoAlumno() {
		List<TipoAlumno> listaTipoAlumno = null;
		try{
			iniciaOperacion();
			listaTipoAlumno = (List<TipoAlumno>)session.createQuery("from TipoAlumno").setMaxResults(10).list();	
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();			
		}		
		
		return listaTipoAlumno;
	}

	@Override
	public TipoAlumno obtenerTipoAlumnoxID(Integer idTipoAlumno) {
		TipoAlumno tipoAlumno = null;
		try{
			iniciaOperacion();
			tipoAlumno = session.get(TipoAlumno.class, idTipoAlumno);
		}catch (HibernateException he) {
			manejaExcepcion(he);
		}finally {
			session.close();
		}
		return tipoAlumno;
	}


}
