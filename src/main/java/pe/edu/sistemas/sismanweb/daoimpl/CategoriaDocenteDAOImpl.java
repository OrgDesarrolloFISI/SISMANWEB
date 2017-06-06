package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.CategoriaDocenteDAO;
import pe.edu.sistemas.sismanweb.entidades.CategoriaDocente;

@Repository
public class CategoriaDocenteDAOImpl implements CategoriaDocenteDAO{
	
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
	public List<CategoriaDocente> obtenerTodoCategoriaDocente() {
		List<CategoriaDocente> listaCategoriaDocente = null;
		try{
			iniciaOperacion();
			listaCategoriaDocente = (List<CategoriaDocente>)session.createQuery("from CategoriaDocente").setMaxResults(10).getResultList();	
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();			
		}		
		
		return listaCategoriaDocente;
	}

	@Override
	public CategoriaDocente obtenerCategoriaDocentexID(Integer idCategoriaDocente) {
		CategoriaDocente categoriaDocente = null;
		try{
			iniciaOperacion();
			categoriaDocente = session.get(CategoriaDocente.class, idCategoriaDocente);
		}catch (HibernateException he) {
			manejaExcepcion(he);
		}finally {
			session.close();
		}
		return categoriaDocente;
	}

}
