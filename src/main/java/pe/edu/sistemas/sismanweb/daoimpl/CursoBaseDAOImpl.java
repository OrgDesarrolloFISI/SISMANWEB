package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.CursoBaseDAO;
import pe.edu.sistemas.sismanweb.domain.CursoBase;

@Repository
public class CursoBaseDAOImpl extends AbstractDAOImpl<CursoBase, Integer> implements CursoBaseDAO{
		
	protected CursoBaseDAOImpl() {
		super(CursoBase.class);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CursoBase> findCursoBaseByNombre(String nombre) {
		List<CursoBase> listaCursoBase = null;
		Query query = null;
		try{
			query = getCurrentSession().createQuery("from CursoBase where cursobNombre like '%"+nombre+"%'");
			listaCursoBase = (List<CursoBase>)query.list();					
		}catch (HibernateException he) {
			he.printStackTrace();
		}
		return listaCursoBase;
	}

	@Override
	public CursoBase findCursoBaseByCodigoByPlan(String codigo, Integer idplan) {
		CursoBase cursoBase = null;
		Query query = null;
		try{
			query = getCurrentSession().createQuery("from CursoBase where cursobCodigo = :codigo and plan.idplan = :idplan").setMaxResults(1);
			query.setParameter("codigo", codigo);
			query.setParameter("idplan", idplan);
			cursoBase = (CursoBase)query.uniqueResult();
		}catch (HibernateException he) {
			he.printStackTrace();
		}
		return cursoBase;
	}

}
