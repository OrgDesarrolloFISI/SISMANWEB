package pe.edu.sistemas.sismanweb.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.CursoPeriodoDAO;
import pe.edu.sistemas.sismanweb.domain.CursoPeriodo;

@Repository
public class CursoPeriodoDAOImpl extends AbstractDAOImpl<CursoPeriodo, Integer> implements CursoPeriodoDAO {

	protected CursoPeriodoDAOImpl() {
		super(CursoPeriodo.class);
	}

	@Override
	public CursoPeriodo findCursoPeriodoByAll(String codigoCurso, String nombreplan, String periodoNombre) {
		CursoPeriodo cursoPeriodo = null;
		Query query = null;
		try {
			query = getCurrentSession().createQuery(
					"SELECT cp from CursoPeriodo as cp "
							+ " WHERE cursoConjunto.cursoBase.cursobCodigo= :codigoCurso"
							+ " AND cursoConjunto.cursoBase.plan.planNombre= :planNombre"
							+ " AND periodo.idperiodo= :periodoNombre")
					.setMaxResults(1);
			query.setParameter("codigoCurso", codigoCurso);
			query.setParameter("planNombre", nombreplan);
			query.setParameter("periodoNombre", Integer.parseInt(periodoNombre));
			cursoPeriodo = (CursoPeriodo) query.uniqueResult();

		} catch (HibernateException he) {
			he.printStackTrace();
		}
		return cursoPeriodo;
	}

	@Override
	public boolean existsCursoPeriodoByAll(String codigoCurso, String nombreplan, String periodoNombre) {
		boolean existe = false;
		CursoPeriodo cp = findCursoPeriodoByAll(codigoCurso, nombreplan, periodoNombre);
		existe = (cp == null) ? false : true;
		return existe;
	}
}
