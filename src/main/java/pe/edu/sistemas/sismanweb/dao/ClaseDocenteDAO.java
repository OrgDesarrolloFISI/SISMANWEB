package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.entidades.Clase;

public interface ClaseDocenteDAO {
	
	/*public void insertarClase (Clase clase);
	
	public void actualizarClase (Clase clase);
	
	public void eliminarClase (Clase clase);*/
	
	public List<Clase> obtenerTodoClase();
	
	public Clase obtenerClasexID(Integer idClase);	

}
