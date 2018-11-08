package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.domain.Aula;

public interface AulaDAO extends AbstractDAO<Aula,Integer>{

	public Aula findByNombreAula(String nombreAula);
	
	public List<Aula> findAll();
}
