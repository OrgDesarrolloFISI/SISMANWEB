package pe.edu.sistemas.sismanweb.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.CursoConjuntoDAO;
import pe.edu.sistemas.sismanweb.domain.CursoConjunto;

@Repository
public class CursoConjuntoDAOImpl extends AbstractDAOImpl<CursoConjunto, Integer> implements CursoConjuntoDAO{
		
	protected CursoConjuntoDAOImpl() {
		super(CursoConjunto.class);
	}

	@Override
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
	public Integer findCodigoMaximo() {
		Integer codigo = null;
		try{
			codigo = (Integer)getCurrentSession().createQuery("select max(cursocCodcomun)from CursoConjunto").uniqueResult();
		}catch (HibernateException he) {
			he.printStackTrace();
		}
		return codigo;
	}

}
