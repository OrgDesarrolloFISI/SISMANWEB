package pe.edu.sistemas.sismanweb.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.GrupoDAO;
import pe.edu.sistemas.sismanweb.domain.Grupo;

@Repository
public class GrupoDAOImpl extends AbstractDAOImpl<Grupo,Integer> implements GrupoDAO {

	protected GrupoDAOImpl() {
		super(Grupo.class);
	}

	@Override
	public Grupo findByidcursoPeriodoBygrupoNumero(Integer idcursoPeriodo, int grupoNumero) {
		Grupo grupo = null;
		Query query = null;
		
		try {
			query=getCurrentSession()
					.createQuery(" FROM Grupo "
								+ "WHERE cursoPeriodo.idcursoPeriodo= :idcursoPeriodo "
								+ "AND grupoNumero= :grupoNumero").setMaxResults(1);
			query.setParameter("idcursoPeriodo", idcursoPeriodo);
			query.setParameter("grupoNumero", grupoNumero);
			grupo=(Grupo)query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return grupo;
	}

	@Override
	public Integer getUltimoIdGrupo() {
		Query query = null;
		
		try {
			query=getCurrentSession()
					.createQuery("SELECT idgrupo FROM Grupo "
							+ "ORDER BY idgrupo DESC").setMaxResults(1);			
			return (Integer) query.uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
