package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.DocenteDAO;
import pe.edu.sistemas.sismanweb.domain.Docente;

@Repository
public class DocenteDAOImpl extends AbstractDAOImpl<Docente, Integer> implements DocenteDAO {

	protected DocenteDAOImpl() {
		super(Docente.class);
	}
	
	@Override
	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.MANDATORY)
	public List<Docente> obtenerDocentesxCod(String valor, String filtro) {
		List<Docente> docente = null;
		Query query = null;
		try{
			//query = session.createQuery("select u from Usuario as u join u.persona as p where u.sistemaIdSistema=4 and p.personaCodigoSistema=:codigo");
			query = getCurrentSession().createQuery("select a from Docente as a where a.persona." + filtro + " LIKE '%"+valor+"%'");
			//query.setParameter("codigo", codigo);
			docente = (List<Docente>)query.list();	
		}catch(HibernateException he){
			he.printStackTrace();
		}		
		return docente;
	}

	@Override
	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.MANDATORY)
	public Docente findDocenteByNombreByApellidoPatByApellidoMat(String nombres, String apellidoPaterno,String apellidoMaterno) {
		Docente docente= null;
		Query query = null;
		try{
			String SQL="";
			SQL="FROM Docente WHERE persona.personaNombre like :nombre ";
			if(apellidoPaterno!=null)
				SQL+="AND persona.personaAppaterno like :apellidopaterno ";
			else if(apellidoMaterno!=null)
				SQL+="AND persona.personaApmaterno like :apellidomaterno";
			
			/*query=getCurrentSession().createQuery(" FROM Docente "
					+ "WHERE persona.personaNombre like :nombre "
					+ "AND persona.personaAppaterno like :apellidopaterno "
					+ "AND persona.personaApmaterno like :apellidomaterno").setMaxResults(1);*/
			query=getCurrentSession().createQuery(SQL).setMaxResults(1);
			
			
			query.setParameter("nombre", "%"+ nombres+"%");
			if(apellidoPaterno!=null)
				query.setParameter("apellidopaterno", "%"+apellidoPaterno+"%");
			else if(apellidoMaterno!=null)
				query.setParameter("apellidomaterno", "%"+apellidoMaterno+"%");
			
			docente=(Docente)query.uniqueResult();
		}catch(HibernateException he){
			he.printStackTrace();
		}
		return docente;
	}
	
	
}
