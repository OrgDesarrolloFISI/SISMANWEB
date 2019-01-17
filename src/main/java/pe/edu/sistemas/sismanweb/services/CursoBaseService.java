package pe.edu.sistemas.sismanweb.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.CursoBaseDAO;
import pe.edu.sistemas.sismanweb.dao.CursoConjuntoDAO;
import pe.edu.sistemas.sismanweb.domain.Aula;
import pe.edu.sistemas.sismanweb.domain.CursoBase;
import pe.edu.sistemas.sismanweb.domain.CursoConjunto;
import pe.edu.sistemas.sismanweb.domain.CursoPeriodo;
import pe.edu.sistemas.sismanweb.domain.Docente;
import pe.edu.sistemas.sismanweb.domain.Grupo;
import pe.edu.sistemas.sismanweb.domain.HorarioClase;
import pe.edu.sistemas.sismanweb.domain.Periodo;
import pe.edu.sistemas.sismanweb.services.modelform.CursoMasivoModel;
import pe.edu.sistemas.sismanweb.services.modelform.CursoPeriodoModelForm;

@Service
@Transactional
public class CursoBaseService {

	@Autowired
	private CursoBaseDAO cursoBaseDAO;
	@Autowired
	private CursoConjuntoDAO cursoConjuntoDAO;
	public List<CursoMasivoModel> saveBulk(List<CursoMasivoModel> listacursoMasivoModel) {
		List<CursoMasivoModel> cursosConProblemas = new ArrayList<CursoMasivoModel>();
		boolean seAgrego = false;

		for (int i = 0; i < listacursoMasivoModel.size(); i++) { // AGREGO TODOS
																	// LOS
																	// CURSOCONJUNTO
																	// QUE NO
																	// EXISTAN A
																	// TODOS LOS
																	// CURSOSMODEL
			CursoMasivoModel cmm = listacursoMasivoModel.get(i);
			System.out.println(cmm);
			CursoConjunto cc = cursoConjuntoDAO.findCursoConjuntoByCodigoCursoByNombrePlan(cmm.getCodCurso(),
					cmm.getNombrePlan());
			if (cc == null) {
				CursoBase cursoBase = cursoBaseDAO.findCursoBaseByNombreByPlanNombre(cmm.getDescCurso(),
						cmm.getNombrePlan());
				if (cursoBase != null) {
					CursoConjunto aux = cursoConjuntoDAO.findCursoConjuntoByNombre(cmm.getDescCurso()); // Obtener
																										// el
																										// CURSOC_CODCOMUN
																										// del
																										// cursoConjunto
					boolean seIngreso;

					// Crear nuevo CursoConjunto
					if (aux == null)
						seIngreso = cursoServ.insertarCursoConjunto(cursoBase, 0);
					else
						seIngreso = cursoServ.insertarCursoConjunto(cursoBase, aux.getCursocCodcomun());

					System.out
							.println((seIngreso) ? "Se ingresó el curso conjunto " : "No se ingresó el curso conjunto");
					cc = cursoConjuntoDAO.findCursoConjuntoByCodigoCursoByNombrePlan(cmm.getCodCurso(),
							cmm.getNombrePlan());
					System.out.println("Se agregó el curso " + cursoBase.getCursobNombre());
				} else {
					cmm.setConError(true);
					cmm.setMotivoError("No existe el Curso Base");
					listacursoMasivoModel.set(i, cmm);
					cursosConProblemas.add(cmm);
					System.out.println("Debería crear el CursoBase con nombre " + cmm.getDescCurso() + " y el plan "
							+ cmm.getNombrePlan());
				}
			}
		} // Después de este método, no debería haber problema con ningún
			// CursoConjunto

		for (int i = 0; i < listacursoMasivoModel.size(); i++) { // AGREGO LOS
																	// CURSOPERIODO
			CursoMasivoModel cmm = listacursoMasivoModel.get(i);

			if (!cmm.isConError()) {
				CursoPeriodo cursoPeriodo = null;

				System.out.println(cmm.toString());
				// Puede ocurrir error si es que el modelo recibido es nulo
				CursoPeriodoModelForm cpmf = new CursoPeriodoModelForm(cmm.getCodCurso(), cmm.getDescCurso(),
						cmm.getPeriodoNombre(), cmm.getNombrePlan());

				if (!cursoPeriodoDAO.existsCursoPeriodoByAll(cmm.getCodCurso(), cmm.getNombrePlan(),
						cmm.getPeriodoNombre())) {
					cursoPeriodo = converterToCursoPeriodo(cpmf);
					seAgrego = insertarCursoPeriodo(cursoPeriodo);

					if (!seAgrego) { // Podría haber problemas con la BD
						cmm.setConError(true);
						cmm.setMotivoError("Problemas al agregar el CursoPeriodo");
						listacursoMasivoModel.set(i, cmm);
						cursosConProblemas.add(cmm);
						System.out.println("No se agregó el cursoPeriodo en " + (i + 1));
					} else {
						System.out.println("Se agregó 1 cursoPeriodo en " + (i + 1));
					}
				} else { // Si no es el primer cursoPeriodo de la carga en
							// agregarse lo cual no sería un problema
					System.out.println("Ya existía cursoPeriodo en " + (i + 1));
				}
			}
		}

		int cantidadGruposAntes = grupoDAO.getUltimoIdGrupo();

		for (int i = 0; i < listacursoMasivoModel.size(); i++) {// AGREGO
																// HORARIOCLASE
																// Y GRUPO
			CursoMasivoModel cmm = listacursoMasivoModel.get(i);
			if (!cmm.isConError()) {
				Grupo grupo = new Grupo();
				HorarioClase horarioClase = new HorarioClase();

				CursoPeriodo cp = cursoPeriodoDAO.findCursoPeriodoByAll(cmm.getCodCurso(), cmm.getNombrePlan(),
						cmm.getPeriodoNombre());

				if (cp == null) { // No debería haber error acá dado que ya se
									// agregó en el "for" anterior
					cmm.setMotivoError("El Curso del Periodo no existía");
					cmm.setConError(true);
					listacursoMasivoModel.set(i, cmm);
					cursosConProblemas.add(cmm);
					System.out.println("Problemas con CursoPeriodo en " + (i + 1));
				} else {

					String nombreAula=cmm.getAula();
					if (nombreAula.compareTo("0")==0)	nombreAula="NO_ASIGNADO";
					Aula aula = aulaDAO.findByNombreAula(nombreAula);

					List<HorarioClase> horarioClases = horarioClaseDAO
							.findHorarioClaseByIdCursoperiodoByGrupo(cp.getIdcursoPeriodo(), cmm.getGrupoNumero());

					Date horaInicio = convertirStringADate(cmm.getHoraInicio());
					Date horaFin = convertirStringADate(cmm.getHoraFinal());

					String nombres = cmm.getDocenteNombre();
					Docente docente = docenteDAO.findDocenteByNombreByApellidoPatByApellidoMat(nombres,
							cmm.getDocenteApPaterno(), cmm.getDocenteApMaterno());

					if (docente == null) { // Si no se encuentra por sus dos
											// nombres, quizá está solo por 1
											// nombre
						if (nombres != null) {
							String[] cadaNombre = nombres.split(" "); // Separamos
																		// por
																		// cada
																		// nombre
							int j = 0;
							for (j = 0; j < cadaNombre.length; j++) { // Buscamos
																		// esto
																		// por
																		// cada
																		// nombre
								docente = docenteDAO.findDocenteByNombreByApellidoPatByApellidoMat(cadaNombre[j],
										cmm.getDocenteApPaterno(), cmm.getDocenteApMaterno());
								if (docente != null) // Cuando encuentro el
														// docente, salgo de la
														// iteración (for j)
									break;
							}
							if (j >= cadaNombre.length) { // Si buscó con todos
															// los nombres y no
															// lo encontró
								cmm.setMotivoError("El docente no existe");
								cmm.setConError(true);
								listacursoMasivoModel.set(i, cmm);
								cursosConProblemas.add(cmm);
								System.out.println("El docente no existe en " + (i + 1));

							}
						} else { // Docente que no tiene nombres
							cmm.setMotivoError("El docente no existe");
							cmm.setConError(true);
							listacursoMasivoModel.set(i, cmm);
							cursosConProblemas.add(cmm);
							System.out.println("El docente no existe en " + (i + 1));
						}
					}

					if (docente != null && cp != null) {
						Grupo aux = grupoDAO.findByidcursoPeriodoBygrupoNumero(cp.getIdcursoPeriodo(),
								cmm.getGrupoNumero());
						if (aux == null) { // Si no existe ningun grupo aún
							grupo.setCursoPeriodo(cp);
							grupo.setGrupoNumero(cmm.getGrupoNumero());
							grupo.setHorarioClases(new HashSet<HorarioClase>(horarioClases));
							grupoDAO.save(grupo);
							System.out.print("Se agregó un grupo en " + (i + 1) + ". ");

							// horarioClase.setIdhorarioClase(0); Se pone
							// automático
							horarioClase.setAula(aula);
							horarioClase.setDia(cmm.getDia());
							horarioClase.setDocente(docente);
							horarioClase.setGrupo(grupo);
							horarioClase.setHoraInicio(horaInicio);
							horarioClase.setHoraFin(horaFin);
							horarioClase.setHorarioClasePeriodo(cmm.getNombrePeriodo());
							horarioClase.setHorarioClaseTipo(cmm.getTipoClase());
							horarioClase.setNombreAula(cmm.getAula());
							horarioClaseDAO.save(horarioClase);
							System.out.println("Se agregó un horarioClase en " + (i + 1));
						} else { // Si ya existe un grupo
							if (aux.getIdgrupo() <= cantidadGruposAntes) { // Si se repite un grupo con uno ya en la bd
								cmm.setMotivoError("Ya existía ese curso y grupo");
								cmm.setConError(true);
								listacursoMasivoModel.set(i, cmm);
								cursosConProblemas.add(cmm);
								System.out.println("Ya existía ese curso y grupo " + (i + 1));
							} else { // Si en la carga se repite el grupo (diferentes planes o mismo curso pero otro
										// tipo: TEORIA, PRACTICA o LABORATORIO ó Diferente día horario)
								boolean existe=horarioClaseDAO.existsHorarioClaseByIdGrupoByDiaByHorIniByHorFinByTipoClase(
										cmm.getGrupoNumero(), cmm.getDia(), horaInicio, horaFin	, cmm.getTipoClase());
								if(!existe) {	//Si no existe, puede ser que sea otro tipoClase u otro día, entonces se agrega
									horarioClase.setAula(aula);
									horarioClase.setDia(cmm.getDia());
									horarioClase.setDocente(docente);
									horarioClase.setGrupo(aux);
									horarioClase.setHoraInicio(horaInicio);
									horarioClase.setHoraFin(horaFin);
									horarioClase.setHorarioClasePeriodo(cmm.getNombrePeriodo());
									horarioClase.setHorarioClaseTipo(cmm.getTipoClase());
									horarioClase.setNombreAula(cmm.getAula());
									horarioClaseDAO.save(horarioClase);
								}
								else {//Se quiere subir lo mismo pero de otro plan probablemente
									System.out.println("Se está repitiendo el curso " + cp.getCursoPeriodoNombre()
											+ " en diferente plan en " + (i + 1) + ".");
								}
								
							}

						}
					}

				}
			}
		}
		return cursosConProblemas;
	}

	public CursoPeriodo converterToCursoPeriodo(CursoPeriodoModelForm formCursoPeriodoModel) {
		CursoPeriodo cursoPeriodo = new CursoPeriodo();
		CursoConjunto cc = cursoConjuntoDAO.findCursoConjuntoByCodigoCursoByNombrePlan(
				formCursoPeriodoModel.getCodCurso(), formCursoPeriodoModel.getPlanNombre());

		Periodo p = periodoDAO.findById(Integer.parseInt(formCursoPeriodoModel.getPeriodo()));

		cursoPeriodo.setPeriodo(p);
		cursoPeriodo.setCursoConjunto(cc);
		cursoPeriodo.setCursoPeriodoNombre(formCursoPeriodoModel.getCursoPeriodoNombre());

		return cursoPeriodo;
	}

	public boolean insertarCursoPeriodo(CursoPeriodo cursoPeriodo) {
		if (cursoPeriodo == null) {
			System.out.println("Ocurrió un error al ingresar cursoPeriodo");
			return false;
		} else {
			try {
				cursoPeriodoDAO.save(cursoPeriodo);
			} catch (Exception e) {
				logger.warn(e);
				System.out.println("Error: " + e);
				return false;
			}
			return true;
		}

	}
	
}
