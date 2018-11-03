package pe.edu.sistemas.sismanweb.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.AulaDAO;
import pe.edu.sistemas.sismanweb.domain.Aula;

@Repository
public class AulaDAOImpl extends AbstractDAOImpl<Aula,Integer> implements AulaDAO{

	protected AulaDAOImpl() {
		super(Aula.class);
	}

	@Override
	public Aula findByNombreAula(String nombreAula) {
		Aula aula=null;
		Query query=null;
		
		try {
			query=getCurrentSession().createQuery(" FROM Aula WHERE nombre = :nombreAula").setMaxResults(1);
			query.setParameter("nombreAula", nombreAula);
			aula = (Aula)query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return aula;
	}

	
}