package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismanweb.dao.AlumnoDAO;
import pe.edu.sistemas.sismanweb.entidades.Alumno;

@Service
public class AlumnoService {
	
	@Autowired
	AlumnoDAO alumnoDao;
	
	public void insertarAlumno(Alumno alumno){
		alumnoDao.insertarAlumno(alumno);
	}
	
	public void actualizarAlumno(Alumno alumno){
		alumnoDao.actualizarAlumno(alumno);
	}
	
	public void eliminarAlumno(Alumno alumno){
		alumnoDao.actualizarAlumno(alumno);
	}

	public List<Alumno> obtenerAlumnos(){
		return alumnoDao.obtenerTodoAlumno();
	}
	
	public Alumno obtenerAlumnoxID(Integer idAlumno){
		return alumnoDao.obtenerAlumnoxID(idAlumno);
	}
	
	
}
