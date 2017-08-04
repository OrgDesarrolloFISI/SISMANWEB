package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.entidades.CursoBase;

public interface CursoBaseDAO {
	
	public void insertarCursoBase (CursoBase cursoBase);
	
	public void actualizarCursoBase (CursoBase cursoBase);
	
	public Integer agregarCursoBase(CursoBase cursoBase);
	
//	public void eliminarCursoBase (CursoBase cursoBase);
	
	public List<CursoBase> obtenerTodoCursoBase();
	
	public CursoBase obtenerCursoBasexID(Integer idCursoBase);
	
	public List<CursoBase> obtenerTodoCursoBasexNombre(String nombre);
	
	public CursoBase obtenerCursoBasexCodigoxPlan(String codigo, Integer idplan);
}
