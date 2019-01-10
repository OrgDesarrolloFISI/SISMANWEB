package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.CursoConjuntoDAO;
import pe.edu.sistemas.sismanweb.domain.CursoBase;
import pe.edu.sistemas.sismanweb.domain.CursoConjunto;

@Repository
public class CursoConjuntoDAOImpl extends AbstractDAOImpl<CursoConjunto, Integer> implements CursoConjuntoDAO{
		
	protected CursoConjuntoDAOImpl() {
		super(CursoConjunto.class);
	}

	@Override
	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.MANDATORY)
	public CursoConjunto findCursoConjuntoByNombre(String nombre) {
		CursoConjunto cursoConjunto = null;
		Query query = null;
		try{
			query = getCurrentSession().createQuery("from CursoConjunto where cursocNombre = :nombre").setMaxResults(1);
			query.setParameter("nombre", nombre);
			cursoConjunto = (CursoConjunto)query.uniqueResult();
		}catch (HibernateException he) {
			he.printStackTrace();
		}
		return cursoConjunto;	
	}

	@Override
	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.MANDATORY)
	public Integer findCodigoMaximo() {
		Integer codigo = null;
		try{
			codigo = (Integer)getCurrentSession().createQuery("select max(cursocCodcomun)from CursoConjunto").uniqueResult();
		}catch (HibernateException he) {
			he.printStackTrace();
		}
		return codigo;
	}

	@Override
	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.MANDATORY)
	public List<CursoConjunto> findCursosConjuntos() {
		List<CursoConjunto> listConjunto= null;
		Query query= null;
		try{
			query =getCurrentSession().createQuery("from CursoConjunto group by cursocCodcomun");
			listConjunto = (List<CursoConjunto>)query.list();	
		}catch (HibernateException he) {
			he.printStackTrace();
		}
		return listConjunto;
	}

	@Override
	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.MANDATORY)
	public List<CursoConjunto> findCursosConjuntosxParams(String valor, String filtro) {
		List<CursoConjunto> listConjunto= null;
		Query query = null;
		try{
		query = getCurrentSession().createQuery("select c FROM CursoConjunto c "
				+ " WHERE c." + filtro + " LIKE '%"+valor+"%' group by cursocCodcomun ");
		
			listConjunto = (List<CursoConjunto>)query.list();	
		}catch(HibernateException he){
			he.printStackTrace();
		}
		return listConjunto;
	
	}

	@Override
	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.MANDATORY)
	public CursoConjunto buscarBaseConjuntoRepetida(Integer idBase) {
		CursoConjunto cursoConjunto = null;
		Query query = null;
		try{
			query = getCurrentSession().createQuery("from CursoConjunto where cursoBase.idcursoGeneral = :idBase").setMaxResults(1);
			query.setParameter("idBase", idBase);
			cursoConjunto = (CursoConjunto)query.uniqueResult();
		}catch (HibernateException he) {
			he.printStackTrace();
		}
		return cursoConjunto;	
	}
	
	@Override
	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.MANDATORY)
	public CursoConjunto findCursoConjuntoByCodigoCursoByNombrePlan(String codigoCurso, String nombreplan) {
		CursoConjunto cursoConjunto = null;
		Query query = null;
		try{
			query = getCurrentSession()
					.createQuery(" from CursoConjunto"
								+" WHERE cursoBase.cursobCodigo= :codigocurso"
								+" AND cursoBase.plan.planNombre= :nombreplan")
					.setMaxResults(1);
			query.setParameter("codigocurso", codigoCurso);
			query.setParameter("nombreplan", nombreplan);
			cursoConjunto = (CursoConjunto)query.uniqueResult();
		}catch (HibernateException he) {
			he.printStackTrace();
		}
		return cursoConjunto;
	}

}
