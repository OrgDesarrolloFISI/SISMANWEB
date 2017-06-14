package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.CursoBaseDAO;
import pe.edu.sistemas.sismanweb.entidades.CursoBase;

@Repository
public class CursoBaseDAOImpl implements CursoBaseDAO{
	
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
	public void insertarCursoBase(CursoBase cursoBase) {
		try{
			iniciaOperacion();
			session.save(cursoBase);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();
		}		
	}

	@Override
	public void actualizarCursoBase(CursoBase cursoBase) {
		try{
			iniciaOperacion();
			session.update(cursoBase);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();
		}		
	}

	@Override
	public List<CursoBase> obtenerTodoCursoBase() {
		List<CursoBase> listaCursoBase = null;
		try{
			iniciaOperacion();
			listaCursoBase = (List<CursoBase>)session.createQuery("from CursoBase").setMaxResults(10).list();	
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();			
		}		
		
		return listaCursoBase;
	}

	@Override
	public CursoBase obtenerCursoBasexID(Integer idCursoBase) {
		CursoBase cursoBase = null;
		try{
			iniciaOperacion();
			cursoBase = session.get(CursoBase.class, idCursoBase);
		}catch (HibernateException he) {
			manejaExcepcion(he);
		}finally {
			session.close();
		}
		return cursoBase;
	}

	

}
