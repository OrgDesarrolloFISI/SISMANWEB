package pe.edu.sistemas.sismanweb.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.PersonaDAO;
import pe.edu.sistemas.sismanweb.domain.Persona;

@Repository
public class PersonaDAOImpl extends AbstractDAOImpl<Persona, Integer> implements PersonaDAO {

	protected PersonaDAOImpl() {
		super(Persona.class);
	}

	@Override
	public Persona findPersonaByCodigo(String codigo) {
		Persona persona = null;
		Query query = null;
		try{
			query = getCurrentSession().createQuery("from Persona where personaCodigo = :codigo").setMaxResults(1);	
			query.setParameter("codigo", codigo);
			persona = (Persona)query.uniqueResult();
		}catch(HibernateException he){
			he.printStackTrace();
		}
		return persona;
	}
	

}
