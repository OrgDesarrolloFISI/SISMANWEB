package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.PeriodoDAO;
import pe.edu.sistemas.sismanweb.entidades.Periodo;

@Repository
public class PeriodoDAOImpl implements PeriodoDAO {
	
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
	public List<Periodo> obtenerTodoPeriodo() {
		List<Periodo> listaPeriodo = null;
		try{
			iniciaOperacion();
			listaPeriodo = (List<Periodo>)session.createQuery("from Periodo").setMaxResults(10).list();	
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();			
		}		
		
		return listaPeriodo;
	}

	@Override
	public Periodo obtenerPeriodoxID(Integer idPeriodo) {
		Periodo periodo = null;
		try{
			iniciaOperacion();
			periodo = session.get(Periodo.class, idPeriodo);
		}catch (HibernateException he) {
			manejaExcepcion(he);
		}finally {
			session.close();
		}
		return periodo;
	}	

}
