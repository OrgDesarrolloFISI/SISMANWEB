package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.entidades.CursoConjunto;

public interface CursoConjuntoDAO {

	public void insertarCursoConjunto(CursoConjunto cursoConjunto);

	public void actualizarCursoConjunto(CursoConjunto cursoConjunto);
	
	public Integer agregarCursoConjunto(CursoConjunto cursoConjunto);
	
	public CursoConjunto obtenerCursoConjuntoxNombre(String nombre);

//	public void eliminarCursoConjunto(CursoConjunto cursoConjunto);

	public List<CursoConjunto> obtenerTodoCursoConjunto();

	public CursoConjunto obtenerCursoConjuntoxID(Integer idCursoConjunto);
	
	public Integer obtenerCodigoMaximo();

}
