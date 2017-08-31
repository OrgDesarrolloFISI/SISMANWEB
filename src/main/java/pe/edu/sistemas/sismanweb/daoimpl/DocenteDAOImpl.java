package pe.edu.sistemas.sismanweb.daoimpl;

import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.DocenteDAO;
import pe.edu.sistemas.sismanweb.domain.Docente;

@Repository
public class DocenteDAOImpl extends AbstractDAOImpl<Docente, Integer> implements DocenteDAO {

	protected DocenteDAOImpl() {
		super(Docente.class);
		// TODO Auto-generated constructor stub
	}
	
}
