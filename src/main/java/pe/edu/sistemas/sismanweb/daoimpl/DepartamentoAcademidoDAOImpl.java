package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.DepartamentoAcademicoDAO;
import pe.edu.sistemas.sismanweb.entidades.DepartamentoAcademico;

@Repository
public class DepartamentoAcademidoDAOImpl implements DepartamentoAcademicoDAO {
	
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
	public List<DepartamentoAcademico> obtenerTodoDepartamentoAcademico() {
		List<DepartamentoAcademico> listaDepartamentoAcademico = null;
		try{
			iniciaOperacion();
			listaDepartamentoAcademico = (List<DepartamentoAcademico>)session.createQuery("from DepartamentoAcademico").setMaxResults(10).list();	
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			session.close();			
		}		
		
		return listaDepartamentoAcademico;
	}

	@Override
	public DepartamentoAcademico obtenerDepartamentoAcademicoxID(Integer idDepartamentoAcademico) {
		DepartamentoAcademico departamentoAcademico = null;
		try{
			iniciaOperacion();
			departamentoAcademico = session.get(DepartamentoAcademico.class, idDepartamentoAcademico);
		}catch (HibernateException he) {
			manejaExcepcion(he);
		}finally {
			session.close();
		}
		return departamentoAcademico;
	}	

}
