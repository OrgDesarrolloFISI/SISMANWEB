package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.entidades.Plan;

public interface PlanDAO {

	/*public void insertarPlan(Plan plan);

	public void actualizarPlan(Plan plan);

	public void eliminarPlan(Plan plan);*/

	public List<Plan> obtenerTodoPlan();

	public Plan obtenerPlanxID(Integer idPlan);

}
