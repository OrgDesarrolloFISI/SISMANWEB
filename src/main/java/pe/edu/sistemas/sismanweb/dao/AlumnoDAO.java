package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.entidades.Alumno;

public interface AlumnoDAO {
	
	public void insertarAlumno (Alumno alumno);
	
	public void actualizarAlumno (Alumno alumno);
	
	public void eliminarAlumno (Alumno alumno);
	
	public List<Alumno> obtenerTodoAlumno();
	
	public List<Alumno> obtenerAlumnoxID(Integer idAlumno);		

}
