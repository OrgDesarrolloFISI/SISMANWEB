package pe.edu.sistemas.sismanweb.dao;

import pe.edu.sistemas.sismanweb.domain.CursoPeriodo;

public interface CursoPeriodoDAO extends AbstractDAO<CursoPeriodo, Integer> {

	public CursoPeriodo findCursoPeriodoByAll(String codigoCurso, String nombreplan, String periodoNombre);

	public boolean existsCursoPeriodoByAll(String codigoCurso, String nombreplan, String periodoNombre);

	//public CursoPeriodoBus findCursoPeriodoBusByAll(String codigoCurso, String nombreplan, String periodoNombre);

	//public boolean existsCursoPeriodoBusByAll(String codigoCurso, String nombreplan, String periodoNombre);
}
