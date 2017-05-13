package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.entidades.Periodo;

public interface PeriodoDAO {

	/*public void insertarPeriodo(Periodo periodo);

	public void actualizarPeriodo(Periodo periodo);

	public void eliminarPeriodo(Periodo periodo);*/

	public List<Periodo> obtenerTodoPeriodo();

	public Periodo obtenerPeriodoxID(Integer idPeriodo);

}
