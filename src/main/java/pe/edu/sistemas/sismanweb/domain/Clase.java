package pe.edu.sistemas.sismanweb.domain;
// Generated 15-sep-2018 12:40:27 by Hibernate Tools 4.3.5.Final

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
 * Clase generated by hbm2java
 */
@Entity
@Table(name = "clase", catalog = "modelogeneralfisi")
public class Clase implements java.io.Serializable {

	private Short idclase;
	private String claseNombre;
	private Set<Docente> docentes = new HashSet<Docente>(0);

	public Clase() {
	}

	public Clase(String claseNombre) {
		this.claseNombre = claseNombre;
	}

	public Clase(String claseNombre, Set<Docente> docentes) {
		this.claseNombre = claseNombre;
		this.docentes = docentes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "IDCLASE", unique = true, nullable = false)
	public Short getIdclase() {
		return this.idclase;
	}

	public void setIdclase(Short idclase) {
		this.idclase = idclase;
	}

	@Column(name = "CLASE_NOMBRE", nullable = false, length = 30)
	public String getClaseNombre() {
		return this.claseNombre;
	}

	public void setClaseNombre(String claseNombre) {
		this.claseNombre = claseNombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clase")
	public Set<Docente> getDocentes() {
		return this.docentes;
	}

	public void setDocentes(Set<Docente> docentes) {
		this.docentes = docentes;
	}

}
