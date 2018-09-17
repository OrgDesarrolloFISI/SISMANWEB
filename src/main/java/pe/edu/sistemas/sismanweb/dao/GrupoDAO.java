package pe.edu.sistemas.sismanweb.dao;

import pe.edu.sistemas.sismanweb.domain.Grupo;

public interface GrupoDAO extends AbstractDAO<Grupo,Integer> {
	public Grupo findByidcursoPeriodoBygrupoNumero(Integer idcursoPeriodo, int grupoNumero);
}
