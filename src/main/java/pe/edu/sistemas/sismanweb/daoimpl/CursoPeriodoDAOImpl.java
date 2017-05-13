package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.CursoPeriodoDAO;
import pe.edu.sistemas.sismanweb.entidades.CursoPeriodo;

@Repository
public class CursoPeriodoDAOImpl implements CursoPeriodoDAO{
	
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction tx;
	
	@Autowired
	public CursoPeriodoDAOImpl(SessionFactory sessionFactory){
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
	public void insertarCursoPeriodo(CursoPeriodo cursoPeriodo) {
		try{
			iniciaOperacion();
			session.save(cursoPeriodo);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();
		}
		
	}

	@Override
	public void actualizarCursoPeriodo(CursoPeriodo cursoPeriodo) {
		try{
			iniciaOperacion();
			session.update(cursoPeriodo);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();
		}		
	}

	@Override
	public List<CursoPeriodo> obtenerTodoCursoPeriodo() {
		List<CursoPeriodo> listaCursoPeriodo = null;
		try{
			iniciaOperacion();
			listaCursoPeriodo = (List<CursoPeriodo>)session.createQuery("from CursoPeriodo").setMaxResults(10).list();	
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();			
		}		
		
		return listaCursoPeriodo;
	}

	@Override
	public CursoPeriodo obtenerCursoPeriodoxID(Integer idcursoPeriodo) {
		CursoPeriodo cursoPeriodo = null;
		try{
			iniciaOperacion();
			cursoPeriodo = session.get(CursoPeriodo.class, idcursoPeriodo);
		}catch (HibernateException he) {
			manejaExcepcion(he);
		}finally {
			session.close();
		}
		return cursoPeriodo;
	}



}
