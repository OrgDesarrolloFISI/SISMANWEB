package pe.edu.sistemas.sismanweb.services.modelform;

public class CursoModelFormBase {

	private String planNombre;
	private String codigo;
	private String nombre;
	private String ciclo;
	private String creditos;
	
	public CursoModelFormBase(){
		
	}
	
	public CursoModelFormBase(String planNombre, String codigo, String nombre, String ciclo, String creditos) {

		this.planNombre = planNombre;
		this.codigo = codigo;
		this.nombre = nombre;
		this.ciclo = ciclo;
		this.creditos = creditos;
	}	
	
	public String getPlanNombre() {
		return planNombre;
	}

	public void setPlanNombre(String planNombre) {
		this.planNombre = planNombre;
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
