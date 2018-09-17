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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Docente generated by hbm2java
 */
@Entity
@Table(name = "docente", catalog = "modelogeneralfisi")
public class Docente implements java.io.Serializable {

	private Integer iddocente;
	private CategoriaDocente categoriaDocente;
	private Clase clase;
	private DepartamentoAcademico departamentoAcademico;
	private Persona persona;
	private String docenteClave;
	private String docenteGrupoOcupacional;
	private String docenteNivel;
	private String docenteObservacionTiempoServicio;
	private String docenteObservacionLimiteEdad;
	private String docenteObservacionOtros;
	private String docenteDocumentos;
	private int docenteRegular;
	private Integer historialServicioIdhistorialServicio;
	private Set<HorarioClase> horarioClases = new HashSet<HorarioClase>(0);

	public Docente() {
	}

	public Docente(Persona persona, String docenteClave, int docenteRegular) {
		this.persona = persona;
		this.docenteClave = docenteClave;
		this.docenteRegular = docenteRegular;
	}

	public Docente(CategoriaDocente categoriaDocente, Clase clase, DepartamentoAcademico departamentoAcademico,
			Persona persona, String docenteClave, String docenteGrupoOcupacional, String docenteNivel,
			String docenteObservacionTiempoServicio, String docenteObservacionLimiteEdad,
			String docenteObservacionOtros, String docenteDocumentos, int docenteRegular,
			Integer historialServicioIdhistorialServicio, Set<HorarioClase> horarioClases) {
		this.categoriaDocente = categoriaDocente;
		this.clase = clase;
		this.departamentoAcademico = departamentoAcademico;
		this.persona = persona;
		this.docenteClave = docenteClave;
		this.docenteGrupoOcupacional = docenteGrupoOcupacional;
		this.docenteNivel = docenteNivel;
		this.docenteObservacionTiempoServicio = docenteObservacionTiempoServicio;
		this.docenteObservacionLimiteEdad = docenteObservacionLimiteEdad;
		this.docenteObservacionOtros = docenteObservacionOtros;
		this.docenteDocumentos = docenteDocumentos;
		this.docenteRegular = docenteRegular;
		this.historialServicioIdhistorialServicio = historialServicioIdhistorialServicio;
		this.horarioClases = horarioClases;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "IDDOCENTE", unique = true, nullable = false)
	public Integer getIddocente() {
		return this.iddocente;
	}

	public void setIddocente(Integer iddocente) {
		this.iddocente = iddocente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORIA_DOCENTE_IDECATEGORIA_DOCENTE")
	public CategoriaDocente getCategoriaDocente() {
		return this.categoriaDocente;
	}

	public void setCategoriaDocente(CategoriaDocente categoriaDocente) {
		this.categoriaDocente = categoriaDocente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLASE_IDCLASE")
	public Clase getClase() {
		return this.clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOCENTE_DEPARTAMENTO_ACADEMICO")
	public DepartamentoAcademico getDepartamentoAcademico() {
		return this.departamentoAcademico;
	}

	public void setDepartamentoAcademico(DepartamentoAcademico departamentoAcademico) {
		this.departamentoAcademico = departamentoAcademico;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSONA_ID_PERSONA", nullable = false)
	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Column(name = "DOCENTE_CLAVE", nullable = false, length = 8)
	public String getDocenteClave() {
		return this.docenteClave;
	}

	public void setDocenteClave(String docenteClave) {
		this.docenteClave = docenteClave;
	}

	@Column(name = "DOCENTE_GRUPO_OCUPACIONAL", length = 50)
	public String getDocenteGrupoOcupacional() {
		return this.docenteGrupoOcupacional;
	}

	public void setDocenteGrupoOcupacional(String docenteGrupoOcupacional) {
		this.docenteGrupoOcupacional = docenteGrupoOcupacional;
	}

	@Column(name = "DOCENTE_NIVEL", length = 50)
	public String getDocenteNivel() {
		return this.docenteNivel;
	}

	public void setDocenteNivel(String docenteNivel) {
		this.docenteNivel = docenteNivel;
	}

	@Column(name = "DOCENTE_OBSERVACION_TIEMPO_SERVICIO", length = 50)
	public String getDocenteObservacionTiempoServicio() {
		return this.docenteObservacionTiempoServicio;
	}

	public void setDocenteObservacionTiempoServicio(String docenteObservacionTiempoServicio) {
		this.docenteObservacionTiempoServicio = docenteObservacionTiempoServicio;
	}

	@Column(name = "DOCENTE_OBSERVACION_LIMITE_EDAD", length = 50)
	public String getDocenteObservacionLimiteEdad() {
		return this.docenteObservacionLimiteEdad;
	}

	public void setDocenteObservacionLimiteEdad(String docenteObservacionLimiteEdad) {
		this.docenteObservacionLimiteEdad = docenteObservacionLimiteEdad;
	}

	@Column(name = "DOCENTE_OBSERVACION_OTROS", length = 100)
	public String getDocenteObservacionOtros() {
		return this.docenteObservacionOtros;
	}

	public void setDocenteObservacionOtros(String docenteObservacionOtros) {
		this.docenteObservacionOtros = docenteObservacionOtros;
	}

	@Column(name = "DOCENTE_DOCUMENTOS", length = 4)
	public String getDocenteDocumentos() {
		return this.docenteDocumentos;
	}

	public void setDocenteDocumentos(String docenteDocumentos) {
		this.docenteDocumentos = docenteDocumentos;
	}

	@Column(name = "DOCENTE_REGULAR", nullable = false)
	public int getDocenteRegular() {
		return this.docenteRegular;
	}

	public void setDocenteRegular(int docenteRegular) {
		this.docenteRegular = docenteRegular;
	}

	@Column(name = "HISTORIAL_SERVICIO_IDHISTORIAL_SERVICIO")
	public Integer getHistorialServicioIdhistorialServicio() {
		return this.historialServicioIdhistorialServicio;
	}

	public void setHistorialServicioIdhistorialServicio(Integer historialServicioIdhistorialServicio) {
		this.historialServicioIdhistorialServicio = historialServicioIdhistorialServicio;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "docente")
	public Set<HorarioClase> getHorarioClases() {
		return this.horarioClases;
	}

	public void setHorarioClases(Set<HorarioClase> horarioClases) {
		this.horarioClases = horarioClases;
	}

}
