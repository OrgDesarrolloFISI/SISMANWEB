package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.domain.Plan;

public interface PlanDAO extends AbstractDAO<Plan, Integer> {
	
	public Plan obtenerPlanxNombrePlan(String nombrePlan);

}
