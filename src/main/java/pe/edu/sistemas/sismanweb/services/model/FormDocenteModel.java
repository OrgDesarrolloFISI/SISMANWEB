package pe.edu.sistemas.sismanweb.services.model;

import java.util.Date;

public class FormDocenteModel {
	
	private short idClase;
	private short idCategoria;
	private int idDepAcad;
	private String codigo;
	private String nombre;
	private String apPaterno;
	private String apMaterno;	
	private Date fechaNacimiento;
	private String sexo;
	private String dni;
	private String telefono;
	private String correo;
	private String direccion;
	
	public FormDocenteModel(){
		
	}

	public FormDocenteModel(short idClase, short idCategoria, int idDepAcad, String codigo, String nombre,
			String apPaterno, String apMaterno, Date fechaNacimiento, String sexo, String dni, String telefono,
			String correo, String direccion) {
		super();
		this.idClase = idClase;
		this.idCategoria = idCategoria;
		this.idDepAcad = idDepAcad;
		this.codigo = codigo;
		this.nombre = nombre;
		this.apPaterno = apPaterno;
		this.apMaterno = apMaterno;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.dni = dni;
		this.telefono = telefono;
		this.correo = correo;
		this.direccion = direccion;
	}

	public short getIdClase() {
		return idClase;
	}

	public void setIdClase(short idClase) {
		this.idClase = idClase;
	}

	public short getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(short idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getIdDepAcad() {
		return idDepAcad;
	}

	public void setIdDepAcad(int idDepAcad) {
		this.idDepAcad = idDepAcad;
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

	public String getApPaterno() {
		return apPaterno;
	}

	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}

	public String getApMaterno() {
		return apMaterno;
	}

	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}	
	

}
