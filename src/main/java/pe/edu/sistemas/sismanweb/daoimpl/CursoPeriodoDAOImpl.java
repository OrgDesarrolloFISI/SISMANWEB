package pe.edu.sistemas.sismanweb.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.CursoPeriodoDAO;
import pe.edu.sistemas.sismanweb.domain.CursoConjunto;
import pe.edu.sistemas.sismanweb.domain.CursoPeriodo;

@Repository
public class CursoPeriodoDAOImpl extends AbstractDAOImpl<CursoPeriodo, Integer> implements CursoPeriodoDAO{

	protected CursoPeriodoDAOImpl() {
		super(CursoPeriodo.class);
	}
	
	public CursoPeriodo findCursoPeriodoByAll(String codigoCurso, String nombreplan, String periodoNombre){
		CursoPeriodo cursoPeriodo = null;
		Query query = null;
		try{
			query = getCurrentSession()
					.createQuery(" from CursoPeriodo"
								+" WHERE cursoConjunto.cursoBase.cursobCodigo= :codigocurso"
								+" AND cursoConjunto.cursoBase.plan.planNombre= :nombreplan"
								+" AND periodo.idperiodo= :periodonombre")
					.setMaxResults(1);
			query.setParameter("codigocurso", codigoCurso);
			query.setParameter("nombreplan", nombreplan);
			query.setParameter("periodonombre", Integer.parseInt(periodoNombre));
			
			cursoPeriodo = (CursoPeriodo)query.uniqueResult();
		}catch (HibernateException he) {
			he.printStackTrace();
		}
		return cursoPeriodo;
	}

	@Override
	public boolean existsCursoPeriodoBy(String nombreCurso, int idPeriodo) {
		boolean existe= false;
		
		Query query = null;
		try{
			query = getCurrentSession()
					.createQuery(" from CursoPeriodo"
								+" WHERE cursoPeriodoNombre= :nombreCurso"
								+" AND periodo.idperiodo= :idPeriodo")
					.setMaxResults(1);
			query.setParameter("nombreCurso", nombreCurso);
			query.setParameter("idPeriodo", idPeriodo);
			
			existe=((CursoPeriodo)query.uniqueResult()==null)?false:true;
		}catch (HibernateException he) {
			he.printStackTrace();
		}
		return existe;
	}
}
