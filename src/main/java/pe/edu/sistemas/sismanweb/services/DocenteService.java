package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismanweb.dao.DocenteDAO;
import pe.edu.sistemas.sismanweb.entidades.Docente;

@Service
public class DocenteService {
	
	@Autowired
	DocenteDAO docenteDao;

	public void insertarDocente(Docente docente){
		docenteDao.insertarDocente(docente);
	}
	
	public void actualizarDocente(Docente docente){
		docenteDao.actualizarDocente(docente);
	}
	
	public void eliminarDocente(Docente docente){
		docenteDao.eliminarDocente(docente);
	}
	
	public List<Docente> obtenerDocentes(){
		return docenteDao.obtenerTodoDocente();
	}
	
	public Docente obtenerDocenteXID(Integer idDocente){
		return docenteDao.obtenerDocentexID(idDocente);
	}
	
	
}
