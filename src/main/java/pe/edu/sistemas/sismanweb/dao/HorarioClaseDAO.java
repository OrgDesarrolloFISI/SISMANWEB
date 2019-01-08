package pe.edu.sistemas.sismanweb.dao;

import java.util.Date;
import java.util.List;

import pe.edu.sistemas.sismanweb.domain.HorarioClase;

public interface HorarioClaseDAO extends AbstractDAO<HorarioClase,Integer>{

	public List<HorarioClase> findHorarioClaseByIdCursoperiodoByGrupo(int idCursoPeriodo, int grupoNumero);
	
	public boolean existsHorarioClaseByIdGrupoByDiaByHorIniByHorFinByTipoClase(int idGrupo, int dia, Date HoraInicio, Date HoraFin, String claseTipo);
}
