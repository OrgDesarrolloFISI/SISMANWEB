package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.entidades.Docente;

public interface DocenteDAO {

	public void insertarDocente(Docente docente);

	public void actualizarDocente(Docente docente);

	public void eliminarDocente(Docente docente);

	public List<Docente> obtenerTodoDocente();

	public Docente obtenerDocentexID(Integer idDocente);

}
