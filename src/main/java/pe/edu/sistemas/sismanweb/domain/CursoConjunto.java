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

import org.springframework.beans.BeanUtils;

/**
 * CursoConjunto generated by hbm2java
 */
@Entity
@Table(name = "CURSO_CONJUNTO", catalog = "modeloGeneralFisi")
public class CursoConjunto implements java.io.Serializable {

	private Integer idcursoConjunto;
	private CursoBase cursoBase;
	private String cursocNombre;
	private int cursocCodcomun;
	private Set<CursoPeriodo> cursoPeriodos = new HashSet<CursoPeriodo>(0);

	public CursoConjunto() {
	}

	public CursoConjunto(CursoBase cursoBase, String cursocNombre, int cursocCodcomun) {
		this.cursoBase = cursoBase;
		this.cursocNombre = cursocNombre;
		this.cursocCodcomun = cursocCodcomun;
	}

	public CursoConjunto(CursoBase cursoBase, String cursocNombre, int cursocCodcomun,
			Set<CursoPeriodo> cursoPeriodos) {
		this.cursoBase = cursoBase;
		this.cursocNombre = cursocNombre;
		this.cursocCodcomun = cursocCodcomun;
		this.cursoPeriodos = cursoPeriodos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "IDCURSO_CONJUNTO", unique = true, nullable = false)
	public Integer getIdcursoConjunto() {
		return this.idcursoConjunto;
	}

	public void setIdcursoConjunto(Integer idcursoConjunto) {
		this.idcursoConjunto = idcursoConjunto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CURSOC_IDCURSO_GENERAL", nullable = false)
	public CursoBase getCursoBase() {
		return this.cursoBase;
	}

	public void setCursoBase(CursoBase cursoBase) {
		this.cursoBase = cursoBase;
	}

	@Column(name = "CURSOC_NOMBRE", nullable = false, length = 100)
	public String getCursocNombre() {
		return this.cursocNombre;
	}

	public void setCursocNombre(String cursocNombre) {
		this.cursocNombre = cursocNombre;
	}

	@Column(name = "CURSOC_CODCOMUN", nullable = false)
	public int getCursocCodcomun() {
		return this.cursocCodcomun;
	}

	public void setCursocCodcomun(int cursocCodcomun) {
		this.cursocCodcomun = cursocCodcomun;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cursoConjunto")
	public Set<CursoPeriodo> getCursoPeriodos() {
		return this.cursoPeriodos;
	}

	public void setCursoPeriodos(Set<CursoPeriodo> cursoPeriodos) {
		//this.cursoPeriodos = cursoPeriodos;
		BeanUtils.copyProperties(cursoPeriodos, this.cursoPeriodos);
	}

}
