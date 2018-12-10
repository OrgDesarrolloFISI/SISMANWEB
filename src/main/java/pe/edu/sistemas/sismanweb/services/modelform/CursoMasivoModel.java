package pe.edu.sistemas.sismanweb.services.modelform;

import java.sql.Date;

public class CursoMasivoModel {
	String horaInicio; // 16:00:00
	String horaFinal; // 18:00:00

	// CursoConjunto (codCurso + nombrePlan)
	String codCurso; // 2010802
	String nombrePlan; // 2014-Sistemas
	String descCurso; // PROYECTO DE TESIS

	// CursoPeriodo (CursoConjunto + periodoNombre)
	String periodoNombre; // 20182

	// Grupo (CursoPeriodo + grupoNumero)
	int grupoNumero; // 1

	String aula; // 210
	int dia; // 1

	// Docente (docenteApPaterno + docenteApMaterno + docenteNombre)
	String docenteApPaterno;// MAURICIO
	String docenteApMaterno;// SANCHEZ
	String docenteNombre; // DAVID SANTOS

	String tipoClase; // TEORÍA
	String nombrePeriodo; // 2018-II

	String motivoError;

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String getCodCurso() {
		return codCurso;
	}

	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}

	public String getNombrePlan() {
		return nombrePlan;
	}

	public void setNombrePlan(String nombrePlan) {
		this.nombrePlan = nombrePlan;
	}

	public String getDescCurso() {
		return descCurso;
	}

	public void setDescCurso(String descCurso) {
		this.descCurso = descCurso;
	}

	public String getPeriodoNombre() {
		return periodoNombre;
	}

	public void setPeriodoNombre(String periodoNombre) {
		this.periodoNombre = periodoNombre;
	}

	public int getGrupoNumero() {
		return grupoNumero;
	}

	public void setGrupoNumero(int grupoNumero) {
		this.grupoNumero = grupoNumero;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getDocenteApPaterno() {
		return docenteApPaterno;
	}

	public void setDocenteApPaterno(String docenteApPaterno) {
		this.docenteApPaterno = docenteApPaterno;
	}

	public String getDocenteApMaterno() {
		return docenteApMaterno;
	}

	public void setDocenteApMaterno(String docenteApMaterno) {
		this.docenteApMaterno = docenteApMaterno;
	}

	public String getDocenteNombre() {
		return docenteNombre;
	}

	public void setDocenteNombre(String docenteNombre) {
		this.docenteNombre = docenteNombre;
	}

	public String getTipoClase() {
		return tipoClase;
	}

	public void setTipoClase(String tipoClase) {
		this.tipoClase = tipoClase;
	}

	public String getNombrePeriodo() {
		return nombrePeriodo;
	}

	public void setNombrePeriodo(String nombrePeriodo) {
		this.nombrePeriodo = nombrePeriodo;
	}

	public String getMotivoError() {
		return motivoError;
	}

	public void setMotivoError(String motivoError) {
		this.motivoError = motivoError;
	}

	@Override
	public String toString() {
		return "CursoMasivoModel [horaInicio=" + horaInicio + ", horaFinal=" + horaFinal + ", codCurso=" + codCurso
				+ ", nombrePlan=" + nombrePlan + ", descCurso=" + descCurso + ", periodoNombre=" + periodoNombre
				+ ", grupoNumero=" + grupoNumero + ", aula=" + aula + ", dia=" + dia + ", docenteApPaterno="
				+ docenteApPaterno + ", docenteApMaterno=" + docenteApMaterno + ", docenteNombre=" + docenteNombre
				+ ", tipoClase=" + tipoClase + ", nombrePeriodo=" + nombrePeriodo + "]";
	}

}
