package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.HorarioClaseDAO;
import pe.edu.sistemas.sismanweb.domain.HorarioClase;

@Repository
public class HorarioClaseDAOImpl extends AbstractDAOImpl<HorarioClase,Integer> implements HorarioClaseDAO {

	protected HorarioClaseDAOImpl() {
		super(HorarioClase.class);
	}

	@Override
	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.MANDATORY)
	public List<HorarioClase> findHorarioClaseByIdCursoperiodoByGrupo(int idCursoPeriodo, int grupoNumero) {
		List<HorarioClase> listHorarioClase=null;
		Query query=null;
		
		try {
			query=getCurrentSession().createQuery(" FROM HorarioClase "
					+ "WHERE grupo.cursoPeriodo.idcursoPeriodo= :idCursoPeriodo "
					+ "AND  grupo.grupoNumero= :grupoNumero");
			query.setParameter("idCursoPeriodo", idCursoPeriodo);
			query.setParameter("grupoNumero", grupoNumero);
			listHorarioClase=(List<HorarioClase>)query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return listHorarioClase;
	}

	@Override
	@SuppressWarnings("unchecked")	
	@Transactional(propagation=Propagation.MANDATORY)
	public boolean existsHorarioClaseByIdGrupoByDiaByHorIniByHorFinByTipoClase(int idGrupo, int dia, Date HoraInicio,
			Date HoraFin, String claseTipo) {
		
		boolean existe=false;
		Query query=null;
		
		try {
			query=getCurrentSession().createQuery(" FROM HorarioClase "
					+ "WHERE grupo.idgrupo = :idGrupo "
					+ "AND dia= :dia "
					+ "AND horaInicio= :horaInicio "
					+ "AND horaFin= :horaFin "
					+ "AND horarioClaseTipo= :claseTipo");
			query.setParameter("idGrupo", idGrupo);
			query.setParameter("dia", dia);
			query.setParameter("horaInicio", HoraInicio);
			query.setParameter("horaFin", HoraFin);
			query.setParameter("claseTipo", claseTipo).setMaxResults(1);
			HorarioClase horarioClase=(HorarioClase)query.uniqueResult();
			existe=(horarioClase!=null?true:false);
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return existe;
	}
	
	
}
