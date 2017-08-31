package pe.edu.sistemas.sismanweb.daoimpl;

import org.springframework.stereotype.Repository;

import pe.edu.sistemas.sismanweb.dao.AlumnoDAO;
import pe.edu.sistemas.sismanweb.domain.Alumno;

@Repository
public class AlumnoDAOImpl extends AbstractDAOImpl<Alumno, Integer> implements AlumnoDAO {

	protected AlumnoDAOImpl() {
		super(Alumno.class);
	}

}
