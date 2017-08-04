package pe.edu.sistemas.sismanweb.services.modelform;

public class CursoModelForm {
	
	private int idPlan;
	private String codigo;
	private String nombre;
	private int ciclo;
	private int creditos;
	
	public CursoModelForm(){
		
	}
	
	public CursoModelForm(int idPlan, String codigo, String nombre, int ciclo, int creditos) {
		super();
		this.idPlan = idPlan;
		this.codigo = codigo;
		this.nombre = nombre;
		this.ciclo = ciclo;
		this.creditos = creditos;
	}	
	
	public int getIdPlan() {
		return idPlan;
	}
	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
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
	public int getCiclo() {
		return ciclo;
	}
	public void setCiclo(int ciclo) {
		this.ciclo = ciclo;
	}
	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	

}
