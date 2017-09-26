package pe.edu.sistemas.sismanweb.domain;
// Generated 26/09/2017 04:31:47 PM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TipoAlumno generated by hbm2java
 */
@Entity
@Table(name = "TIPO_ALUMNO", catalog = "modeloGeneralFisi")
public class TipoAlumno implements java.io.Serializable {

	private Integer idTipoAlumno;
	private String tipoAlumnoNombre;
	private Set<Alumno> alumnos = new HashSet<Alumno>(0);

	public TipoAlumno() {
	}

	public TipoAlumno(String tipoAlumnoNombre) {
		this.tipoAlumnoNombre = tipoAlumnoNombre;
	}

	public TipoAlumno(String tipoAlumnoNombre, Set<Alumno> alumnos) {
		this.tipoAlumnoNombre = tipoAlumnoNombre;
		this.alumnos = alumnos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_TIPO_ALUMNO", unique = true, nullable = false)
	public Integer getIdTipoAlumno() {
		return this.idTipoAlumno;
	}

	public void setIdTipoAlumno(Integer idTipoAlumno) {
		this.idTipoAlumno = idTipoAlumno;
	}

	@Column(name = "TIPO_ALUMNO_NOMBRE", nullable = false, length = 45)
	public String getTipoAlumnoNombre() {
		return this.tipoAlumnoNombre;
	}

	public void setTipoAlumnoNombre(String tipoAlumnoNombre) {
		this.tipoAlumnoNombre = tipoAlumnoNombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoAlumno")
	public Set<Alumno> getAlumnos() {
		return this.alumnos;
	}

	public void setAlumnos(Set<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

}
