package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.domain.CursoBase;

public interface CursoBaseDAO extends AbstractDAO<CursoBase, Integer> {
	
	public List<CursoBase> findCursoBaseByNombre(String nombre);
	
	public CursoBase findCursoBaseByCodigoByPlan(String codigo, Integer idplan);
	
	public CursoBase findCursoBaseByNombreByPlanNombre(String nombre, String nombrePlan);
	
	public List<CursoBase> obtenerCursosxCod(String valor, String filtro);
	
	public List<CursoBase> findCursoBaseSinConjunto();

	public List<CursoBase> findCursoBaseSinConjuntoxParams(String valor, String filtro);
}
