package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.entidades.TipoAlumno;

public interface TipoAlumnoDAO {
	
	/*public void insertarTipoAlumno(TipoAlumno tipoAlumno);

	public void actualizarTipoAlumno(TipoAlumno tipoAlumno);

	public void eliminarTipoAlumno(TipoAlumno tipoAlumno);*/

	public List<TipoAlumno> obtenerTodoTipoAlumno();

	public TipoAlumno obtenerTipoAlumnoxID(Integer idTipoAlumno);

}
