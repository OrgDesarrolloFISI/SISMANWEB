package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.domain.HorarioClase;

public interface HorarioClaseDAO extends AbstractDAO<HorarioClase,Integer>{

	public List<HorarioClase> findHorarioClaseByGrupo(int grupoNumero);
	
}
