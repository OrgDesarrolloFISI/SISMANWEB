package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.domain.CursoConjunto;

public interface CursoConjuntoDAO extends AbstractDAO<CursoConjunto, Integer>{
	
	public CursoConjunto findCursoConjuntoByNombreYCodigoEscuela(String nombre,int codigoEscuela);
	
	public Integer findCodigoMaximo();
	
	public List<CursoConjunto> findCursosConjuntos();

	public CursoConjunto findCursoConjuntoByCodigoCursoByNombrePlan(String codigoCurso, String nombreplan);
	
	public List<CursoConjunto> findCursosConjuntosxParams(String valor, String filtro);

	public CursoConjunto buscarBaseConjuntoRepetida(Integer idBase);

}
