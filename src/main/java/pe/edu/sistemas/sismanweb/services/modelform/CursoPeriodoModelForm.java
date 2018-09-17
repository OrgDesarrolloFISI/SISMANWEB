package pe.edu.sistemas.sismanweb.services.modelform;

public class CursoPeriodoModelForm {
	String codCurso;			//2010802
	String cursoPeriodoNombre;	//PROYECTO DE TESIS
	String Periodo;				//20182
	String planNombre;			//2014-Sistemas
	
	public CursoPeriodoModelForm(){};
	
	
	public CursoPeriodoModelForm(String codCurso, String cursoPeriodoNombre, String periodo, String planNombre) {
		this.codCurso = codCurso;
		this.cursoPeriodoNombre = cursoPeriodoNombre;
		Periodo = periodo;
		this.planNombre = planNombre;
	}


	public String getCodCurso() {
		return codCurso;
	}
	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}
	public String getCursoPeriodoNombre() {
		return cursoPeriodoNombre;
	}
	public void setCursoPeriodoNombre(String cursoPeriodoNombre) {
		this.cursoPeriodoNombre = cursoPeriodoNombre;
	}
	public String getPeriodo() {
		return Periodo;
	}
	public void setPeriodo(String periodo) {
		Periodo = periodo;
	}
	public String getPlanNombre() {
		return planNombre;
	}
	public void setPlanNombre(String planNombre) {
		this.planNombre = planNombre;
	}
	
	
}
