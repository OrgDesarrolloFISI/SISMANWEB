package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismanweb.dao.ClaseDocenteDAO;
import pe.edu.sistemas.sismanweb.entidades.Clase;

@Service
public class ClaseDocenteService {
	
	@Autowired
	ClaseDocenteDAO claseDocenteDao;

	public List<Clase> obtenerClasesDeDocentes(){
		return claseDocenteDao.obtenerTodoClase();
	}
	
	public Clase obtenerClaseDeDocenteXID(Integer idClaseDocente){
		return claseDocenteDao.obtenerClasexID(idClaseDocente);
	}
	
}
