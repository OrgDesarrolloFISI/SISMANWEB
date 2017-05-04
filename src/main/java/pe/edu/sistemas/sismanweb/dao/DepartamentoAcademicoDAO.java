package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.entidades.DepartamentoAcademico;

public interface DepartamentoAcademicoDAO {

	/*public void insertarDepartamentoAcademico(DepartamentoAcademico departamentoAcademico);

	public void actualizarDepartamentoAcademico(DepartamentoAcademico departamentoAcademico);

	public void eliminarDepartamentoAcademico(DepartamentoAcademico departamentoAcademico);*/

	public List<DepartamentoAcademico> obtenerTodoDepartamentoAcademico();

	public List<DepartamentoAcademico> obtenerDepartamentoAcademicoxID(Integer idDepartamentoAcademico);
	

}
