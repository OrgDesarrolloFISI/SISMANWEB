package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.entidades.CursoBase;

public interface CursoBaseDAO {
	
	public void insertarCursoBase (CursoBase cursoBase);
	
	public void actualizarCursoBase (CursoBase cursoBase);
	
//	public void eliminarCursoBase (CursoBase cursoBase);
	
	public List<CursoBase> obtenerTodoCursoBase();
	
	public CursoBase obtenerCursoBasexID(Integer idCursoBase);
}
