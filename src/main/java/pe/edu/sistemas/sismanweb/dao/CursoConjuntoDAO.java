package pe.edu.sistemas.sismanweb.dao;

import pe.edu.sistemas.sismanweb.domain.CursoConjunto;

public interface CursoConjuntoDAO extends AbstractDAO<CursoConjunto, Integer>{
	
	public CursoConjunto findCursoConjuntoByNombre(String nombre);
	
	public Integer findCodigoMaximo();

}
