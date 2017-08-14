package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.UsuarioDAO;
import pe.edu.sistemas.sismanweb.entidades.Usuario;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO{

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
	public List<Usuario> obtenerUsuarioxCodSis(String codigo) {
		List<Usuario> usuario = null;
		Query query = null;
		try{
			iniciaOperacion();
			query = session.createQuery("select u from Usuario as u join u.persona as p where u.sistemaIdSistema=4 and p.personaCodigoSistema=:codigo");
			query.setParameter("codigo", codigo);
			usuario = (List<Usuario>)query.getResultList();	
		}catch(HibernateException he){
			manejaExcepcion(he);
		}finally{
			//session.close();			
		}		
		
		return usuario;
	}

}
