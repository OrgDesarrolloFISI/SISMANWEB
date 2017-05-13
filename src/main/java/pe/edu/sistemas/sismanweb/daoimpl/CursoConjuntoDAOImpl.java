package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.CursoConjuntoDAO;
import pe.edu.sistemas.sismanweb.entidades.CursoConjunto;

@Repository
public class CursoConjuntoDAOImpl implements CursoConjuntoDAO{
	
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction tx;
	
	@Autowired
	public CursoConjuntoDAOImpl(SessionFactory sessionFactory){
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
	public void insertarCursoConjunto(CursoConjunto cursoConjunto) {
		try{
			iniciaOperacion();
			session.save(cursoConjunto);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();
		}		
	}

	@Override
	public void actualizarCursoConjunto(CursoConjunto cursoConjunto) {
		try{
			iniciaOperacion();
			session.update(cursoConjunto);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();
		}		
	}

	@Override
	public List<CursoConjunto> obtenerTodoCursoConjunto() {
		List<CursoConjunto> listaCursoConjunto = null;
		try{
			iniciaOperacion();
			listaCursoConjunto = (List<CursoConjunto>)session.createQuery("from CursoConjunto").setMaxResults(10).list();	
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();			
		}		
		
		return listaCursoConjunto;
	}

	@Override
	public CursoConjunto obtenerCursoConjuntoxID(Integer idCursoConjunto) {
		CursoConjunto cursoConjunto = null;
		try{
			iniciaOperacion();
			cursoConjunto = session.get(CursoConjunto.class, idCursoConjunto);
		}catch (HibernateException he) {
			manejaExcepcion(he);
		}finally {
			session.close();
		}
		return cursoConjunto;
	}

	

}
