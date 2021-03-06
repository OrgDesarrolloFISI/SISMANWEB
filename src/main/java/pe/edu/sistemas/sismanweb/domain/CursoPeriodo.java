package pe.edu.sistemas.sismanweb.domain;
// Generated 18/09/2018 05:06:44 PM by Hibernate Tools 4.3.1.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CursoPeriodo generated by hbm2java
 */
@Entity
@Table(name = "CURSO_PERIODO", catalog = "modeloGeneralFisi")
public class CursoPeriodo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idcursoPeriodo;
	private CursoConjunto cursoConjunto;
	private Periodo periodo;
	private String cursoPeriodoNombre;
	private Set<Grupo> grupos = new HashSet<Grupo>(0);

	public CursoPeriodo() {
	}

	public CursoPeriodo(CursoConjunto cursoConjunto, Periodo periodo) {
		this.cursoConjunto = cursoConjunto;
		this.periodo = periodo;
	}

	public CursoPeriodo(CursoConjunto cursoConjunto, Periodo periodo, String cursoPeriodoNombre, Set<Grupo> grupos) {
		this.cursoConjunto = cursoConjunto;
		this.periodo = periodo;
		this.cursoPeriodoNombre = cursoPeriodoNombre;
		this.grupos = grupos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "IDCURSO_PERIODO", unique = true, nullable = false)
	public Integer getIdcursoPeriodo() {
		return this.idcursoPeriodo;
	}

	public void setIdcursoPeriodo(Integer idcursoPeriodo) {
		this.idcursoPeriodo = idcursoPeriodo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURSO_PERIODO_CURSOC_CODCOMUN", nullable = false, referencedColumnName = "CURSOC_CODCOMUN")
	public CursoConjunto getCursoConjunto() {
		return this.cursoConjunto;
	}

	public void setCursoConjunto(CursoConjunto cursoConjunto) {
		this.cursoConjunto = cursoConjunto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERIODO_IDPERIODO", nullable = false)
	public Periodo getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	@Column(name = "CURSO_PERIODO_NOMBRE", length = 100)
	public String getCursoPeriodoNombre() {
		return this.cursoPeriodoNombre;
	}

	public void setCursoPeriodoNombre(String cursoPeriodoNombre) {
		this.cursoPeriodoNombre = cursoPeriodoNombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cursoPeriodo")
	public Set<Grupo> getGrupos() {
		return this.grupos;
	}

	public void setGrupos(Set<Grupo> grupos) {
		this.grupos = grupos;
	}

	@Override
	public String toString() {
		return "CursoPeriodo [idcursoPeriodo=" + idcursoPeriodo + ", cursoConjunto=" + cursoConjunto + ", periodo="
				+ periodo + ", cursoPeriodoNombre=" + cursoPeriodoNombre + ", grupos=" + grupos + "]";
	}

}
