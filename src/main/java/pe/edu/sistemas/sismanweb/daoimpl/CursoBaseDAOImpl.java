package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
		throw new HibernateException("Ocurrio un error en el acceso a datos", he);
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
			listaCursoBase = (List<CursoBase>)session.createQuery("from CursoBase").setMaxResults(10).getResultList();	
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			//session.close();			
		}		
		
		return listaCursoBase;
	}
	
	@Override
	public Integer agregarCursoBase(CursoBase cursoBase) {
		Integer id = null;
		try{
			iniciaOperacion();
			id = (Integer) session.save(cursoBase);
			tx.commit();
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();
		}	
		return id;
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

	@Override
	public List<CursoBase> obtenerTodoCursoBasexNombre(String nombre) {
		List<CursoBase> listaCursoBase = null;
		Query query = null;
		try{
			iniciaOperacion();
			query = session.createQuery("from CursoBase where cursobNombre like '%"+nombre+"%'");
			listaCursoBase = (List<CursoBase>)query.getResultList();					
		}catch (HibernateException he) {
			manejaExcepcion(he);
		}finally {
			//session.close();
		}
		return listaCursoBase;
	}

	@Override
	public CursoBase obtenerCursoBasexCodigoxPlan(String codigo,Integer idplan) {
		CursoBase cursoBase = null;
		Query query = null;
		try{
			iniciaOperacion();
			query = session.createQuery("from CursoBase where cursobCodigo = :codigo and plan.idplan = :idplan").setMaxResults(1);
			query.setParameter("codigo", codigo);
			query.setParameter("idplan", idplan);
			cursoBase = (CursoBase)query.getSingleResult();
		}catch (HibernateException he) {
			manejaExcepcion(he);
		}finally {
			//session.close();
		}
		return cursoBase;
	}

}
