package pe.edu.sistemas.sismanweb.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.HorarioClaseDAO;
import pe.edu.sistemas.sismanweb.domain.HorarioClase;

@Repository
public class HorarioClaseDAOImpl extends AbstractDAOImpl<HorarioClase,Integer> implements HorarioClaseDAO {

	protected HorarioClaseDAOImpl() {
		super(HorarioClase.class);
	}

	@Override
	public List<HorarioClase> findHorarioClaseByGrupo(int grupoNumero) {
		List<HorarioClase> listHorarioClase=null;
		Query query=null;
		
		try {
			query=getCurrentSession().createQuery(" FROM HorarioClase "
					+ "WHERE grupo.grupoNumero= :grupoNumero");
			query.setParameter("grupoNumero", grupoNumero);
			listHorarioClase=(List<HorarioClase>)query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		
		return listHorarioClase;
	}
	
}
