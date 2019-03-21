package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.PlanDAO;
import pe.edu.sistemas.sismanweb.domain.Alumno;
import pe.edu.sistemas.sismanweb.domain.CursoConjunto;
import pe.edu.sistemas.sismanweb.domain.Plan;

@Repository
public class PlanDAOImpl extends AbstractDAOImpl<Plan, Integer> implements PlanDAO {

	protected PlanDAOImpl() {
		super(Plan.class);
	}

	@Override
	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.MANDATORY)
	public Plan obtenerPlanxNombrePlan(String nombrePlan) {
		Plan plan = null;
		Query query = null;
		try{
			query = getCurrentSession().createQuery("from Plan where planNombre = :nombrePlan").setMaxResults(1);
			query.setParameter("nombrePlan", nombrePlan).setMaxResults(1);
			plan = (Plan)query.uniqueResult();
		}catch (HibernateException he) {
			he.printStackTrace();
		}
		return plan;	
	}

		
}
