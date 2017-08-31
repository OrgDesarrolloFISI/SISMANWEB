package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismanweb.dao.TipoAlumnoDAO;
import pe.edu.sistemas.sismanweb.domain.TipoAlumno;

@Service
public class TipoAlumnoService {
	
	@Autowired
	TipoAlumnoDAO tipoAlumnoDao;
	
	public List<TipoAlumno> obtenerTiposDeAlumno(){
		return tipoAlumnoDao.findAll();
	}

	public TipoAlumno obtenerTipoAlumnoXID(Integer idTipoAlumno){
		return tipoAlumnoDao.findById(idTipoAlumno);
	}
	
}
