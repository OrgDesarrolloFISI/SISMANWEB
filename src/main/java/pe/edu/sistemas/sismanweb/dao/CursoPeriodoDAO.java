package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.entidades.CursoPeriodo;

public interface CursoPeriodoDAO {

	public void insertarCursoPeriodo(CursoPeriodo cursoPeriodo);

	public void actualizarCursoPeriodo(CursoPeriodo cursoPeriodo);

//	public void eliminarCursoPeriodo (CursoPeriodo cursoPeriodo);

	public List<CursoPeriodo> obtenerTodoCursoPeriodo();

	public List<CursoPeriodo> obtenerCursoPeriodoxID(Integer idcursoPeriodo);

}
