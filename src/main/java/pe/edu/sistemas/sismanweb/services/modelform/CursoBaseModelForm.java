package pe.edu.sistemas.sismanweb.services.modelform;

public class CursoBaseModelForm {
	String codCurso;
	String nombre;
	String planNombre;
	String ciclo;
	String creditos;
	
	public CursoBaseModelForm(String codCurso, String nombre, String planNombre, String ciclo, String creditos) {
		this.codCurso = codCurso;
		this.nombre = nombre;
		this.planNombre = planNombre;
		this.ciclo = ciclo;
		this.creditos = creditos;
	}
	
	public String getCodCurso() {
		return codCurso;
	}
	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPlanNombre() {
		return planNombre;
	}
	public void setPlanNombre(String planNombre) {
		this.planNombre = planNombre;
	}
	public String getCiclo() {
		return ciclo;
	}
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}
	public String getCreditos() {
		return creditos;
	}
	public void setCreditos(String creditos) {
		this.creditos = creditos;
	}
	
}
