package pe.edu.sistemas.sismanweb.domain;
// Generated 18/09/2018 05:06:44 PM by Hibernate Tools 4.3.1.Final

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
 * CursoBase generated by hbm2java
 */
@Entity
@Table(name = "CURSO_BASE", catalog = "modeloGeneralFisi")
public class CursoBase implements java.io.Serializable {

	private Integer idcursoGeneral;
	private Plan plan;
	private String cursobCodigo;
	private String cursobNombre;
	private int cursobCiclo;
	private int cursobCreditos;
	private Set<CursoConjunto> cursoConjuntos = new HashSet<CursoConjunto>(0);

	public CursoBase() {
	}

	public CursoBase(Plan plan, String cursobCodigo, String cursobNombre, int cursobCiclo, int cursobCreditos) {
		this.plan = plan;
		this.cursobCodigo = cursobCodigo;
		this.cursobNombre = cursobNombre;
		this.cursobCiclo = cursobCiclo;
		this.cursobCreditos = cursobCreditos;
	}

	public CursoBase(Plan plan, String cursobCodigo, String cursobNombre, int cursobCiclo, int cursobCreditos,
			Set<CursoConjunto> cursoConjuntos) {
		this.plan = plan;
		this.cursobCodigo = cursobCodigo;
		this.cursobNombre = cursobNombre;
		this.cursobCiclo = cursobCiclo;
		this.cursobCreditos = cursobCreditos;
		this.cursoConjuntos = cursoConjuntos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "IDCURSO_GENERAL", unique = true, nullable = false)
	public Integer getIdcursoGeneral() {
		return this.idcursoGeneral;
	}

	public void setIdcursoGeneral(Integer idcursoGeneral) {
		this.idcursoGeneral = idcursoGeneral;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PLAN_ID_PLAN", nullable = false)
	public Plan getPlan() {
		return this.plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	@Column(name = "CURSOB_CODIGO", nullable = false, length = 45)
	public String getCursobCodigo() {
		return this.cursobCodigo;
	}

	public void setCursobCodigo(String cursobCodigo) {
		this.cursobCodigo = cursobCodigo;
	}

	@Column(name = "CURSOB_NOMBRE", nullable = false, length = 100)
	public String getCursobNombre() {
		return this.cursobNombre;
	}

	public void setCursobNombre(String cursobNombre) {
		this.cursobNombre = cursobNombre;
	}

	@Column(name = "CURSOB_CICLO", nullable = false)
	public int getCursobCiclo() {
		return this.cursobCiclo;
	}

	public void setCursobCiclo(int cursobCiclo) {
		this.cursobCiclo = cursobCiclo;
	}

	@Column(name = "CURSOB_CREDITOS", nullable = false)
	public int getCursobCreditos() {
		return this.cursobCreditos;
	}

	public void setCursobCreditos(int cursobCreditos) {
		this.cursobCreditos = cursobCreditos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cursoBase")
	public Set<CursoConjunto> getCursoConjuntos() {
		return this.cursoConjuntos;
	}

	public void setCursoConjuntos(Set<CursoConjunto> cursoConjuntos) {
		this.cursoConjuntos = cursoConjuntos;
	}

}
