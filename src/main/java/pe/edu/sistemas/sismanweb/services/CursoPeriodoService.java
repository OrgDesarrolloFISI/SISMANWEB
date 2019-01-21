package pe.edu.sistemas.sismanweb.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.edu.sistemas.sismanweb.dao.AulaDAO;
import pe.edu.sistemas.sismanweb.dao.CursoBaseDAO;
import pe.edu.sistemas.sismanweb.dao.CursoConjuntoDAO;
import pe.edu.sistemas.sismanweb.dao.CursoPeriodoDAO;
import pe.edu.sistemas.sismanweb.dao.DocenteDAO;
import pe.edu.sistemas.sismanweb.dao.GrupoDAO;
import pe.edu.sistemas.sismanweb.dao.HorarioClaseDAO;
import pe.edu.sistemas.sismanweb.dao.PeriodoDAO;
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
public class CursoPeriodoService {

	protected final Log logger = LogFactory.getLog(AlumnoService.class);
	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();

	@Autowired
	private AulaDAO aulaDAO;
	@Autowired
	private CursoConjuntoDAO cursoConjuntoDAO;
	@Autowired
	private CursoPeriodoDAO cursoPeriodoDAO;
	@Autowired
	private GrupoDAO grupoDAO;
	@Autowired
	private HorarioClaseDAO horarioClaseDAO;
	@Autowired
	private PeriodoDAO periodoDAO;
	@Autowired
	private CursoBaseDAO cursoBaseDAO;
	@Autowired
	private CursoService cursoServ;
	@Autowired
	private DocenteService docenteServ;
	@Autowired
	private DocenteDAO docenteDAO;

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
						seIngreso = cursoServ.insertarCursoConjunto(cursoBase, aux.getIdcursoConjunto());

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
		System.out.println("La cantidad de grupos antes es " + cantidadGruposAntes);
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

					String nombreAula = cmm.getAula();
					if (nombreAula.equals("0"))
						nombreAula = "NO_ASIGNADO";

					Aula aula = aulaDAO.findByNombreAula(nombreAula);

					List<HorarioClase> horarioClases = horarioClaseDAO
							.findHorarioClaseByIdCursoperiodoByGrupo(cp.getIdcursoPeriodo(), cmm.getGrupoNumero());

					Date horaInicio = convertirStringADate(cmm.getHoraInicio());
					Date horaFin = convertirStringADate(cmm.getHoraFinal());

					Docente docente = docenteServ.buscarDocenteXNombresApPaternoApMaterno(cmm.getDocenteNombre(),
							cmm.getDocenteApPaterno(), cmm.getDocenteApMaterno());

					if (docente != null && cp != null) {
						System.out.println(cp);
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
							if (!cmm.getAula().equalsIgnoreCase("0"))
								horarioClase.setNombreAula(cmm.getAula());
							else
								horarioClase.setNombreAula("");
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
								boolean existe = horarioClaseDAO
										.existsHorarioClaseByIdGrupoByDiaByHorIniByHorFinByTipoClase(
												cmm.getGrupoNumero(), cmm.getDia(), horaInicio, horaFin,
												cmm.getTipoClase());
								if (!existe) { // Si no existe, puede ser que sea otro tipoClase u otro día, entonces se
												// agrega
									horarioClase.setAula(aula);
									horarioClase.setDia(cmm.getDia());
									horarioClase.setDocente(docente);
									horarioClase.setGrupo(aux);
									horarioClase.setHoraInicio(horaInicio);
									horarioClase.setHoraFin(horaFin);
									horarioClase.setHorarioClasePeriodo(cmm.getNombrePeriodo());
									horarioClase.setHorarioClaseTipo(cmm.getTipoClase());
									if (!cmm.getAula().equalsIgnoreCase("0"))
										horarioClase.setNombreAula(cmm.getAula());
									else
										horarioClase.setNombreAula("");
									horarioClaseDAO.save(horarioClase);
								} else {// Se quiere subir lo mismo pero de otro plan probablemente
									System.out.println("Se está repitiendo el curso " + cp.getCursoPeriodoNombre()
											+ " en diferente plan en " + (i + 1) + ".");
								}

							}

						}
					} else { // Llegaría acá si es que CursoPeriodo es nulo, pero es muy poco probable ya que
								// se agrega en una anterior iteración
						cmm.setMotivoError("Docente no existe en la BD");
						cmm.setConError(true);
						listacursoMasivoModel.set(i, cmm);
						cursosConProblemas.add(cmm);
						System.out.println("Docente no existe en " + (i + 1));
					}

				}
			}
		}
		return cursosConProblemas;
	}

	public CursoMasivoModel updateBulk(List<CursoMasivoModel> listacursoMasivoModel) {
		CursoMasivoModel cursoConProblemas = null;
		int cantHorariosClase = 0;
		int contador = 0;
		List<HorarioClase> listHorariosClase = new ArrayList<>();
		for (int i = 0; i < listacursoMasivoModel.size(); i++) {
			CursoMasivoModel cmm = listacursoMasivoModel.get(i);
			System.out.println(cmm);
			if (contador == 0) {
				CursoPeriodo cp = cursoPeriodoDAO.findCursoPeriodoByAll(cmm.getCodCurso(), cmm.getNombrePlan(),
						cmm.getPeriodoNombre());
				if (cp != null) {
					listHorariosClase = horarioClaseDAO.findHorarioClaseByIdCursoperiodoByGrupo(cp.getIdcursoPeriodo(),
							cmm.getGrupoNumero());
					if (listHorariosClase != null) {
						cantHorariosClase = listHorariosClase.size();
						System.out.println("contador=" + contador + " y cantHorariosClase=" + cantHorariosClase);
						String nombreAula = cmm.getAula();
						Aula aula = aulaDAO.findByNombreAula((nombreAula.equals("0") ? "NO_ASIGNADO" : nombreAula));
						Docente docente = docenteServ.buscarDocenteXNombresApPaternoApMaterno(cmm.getDocenteNombre(),
								cmm.getDocenteApPaterno(), cmm.getDocenteApMaterno());
						if (aula != null && docente != null) {
							HorarioClase hc = listHorariosClase.get(contador);

							hc.setHoraInicio(convertirStringADate(cmm.getHoraInicio()));
							hc.setHoraFin(convertirStringADate(cmm.getHoraFinal()));
							hc.setAula(aula);
							hc.setNombreAula((nombreAula.equals("0") ? "" : nombreAula));
							hc.setDocente(docente);
							hc.setDia(cmm.getDia());

							horarioClaseDAO.update(hc);
							System.out.println(hc);
							contador++;
						} else {
							// El aula o docente que se quiere agregar no existen en la BD
							cursoConProblemas = cmm;
							break;
						}
					} else {
						// No tiene Horarios clase, no es la misma carga
						cursoConProblemas = cmm;
						break;
					}

				} else {
					// El curso que se quiere actualizar no existe
					cursoConProblemas = cmm;
					break;
				}
			} else { // Contador >=1
				System.out.println("contador=" + contador + " y cantHorariosClase=" + cantHorariosClase);
				String nombreAula = cmm.getAula();
				Aula aula = aulaDAO.findByNombreAula((nombreAula.equals("0") ? "NO_ASIGNADO" : nombreAula));
				Docente docente = docenteServ.buscarDocenteXNombresApPaternoApMaterno(cmm.getDocenteNombre(),
						cmm.getDocenteApPaterno(), cmm.getDocenteApMaterno());
				if (aula != null && docente != null) {
					HorarioClase hc = listHorariosClase.get(contador);

					hc.setHoraInicio(convertirStringADate(cmm.getHoraInicio()));
					hc.setHoraFin(convertirStringADate(cmm.getHoraFinal()));
					hc.setAula(aula);
					hc.setNombreAula((nombreAula.equals("0") ? "" : nombreAula));
					hc.setDocente(docente);
					System.out.println(hc);
					horarioClaseDAO.update(hc);
					contador++;
					if (contador >= cantHorariosClase)
						contador = 0;
				}
			}
			System.out.println("");
		}

		return cursoConProblemas;
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

	public Date convertirStringADate(String fecha) {
		Date hora = new Date(0, 0, 0);
		// System.out.print("Antes de convertir: " + fecha);
		String[] seccionesHora = fecha.split(":"); // Suponiendo que la entrada
													// es HH:MM:SS solo usaremos
													// HH y MM
		hora.setHours(Integer.parseInt(seccionesHora[0]));
		hora.setMinutes(Integer.parseInt(seccionesHora[1]));
		hora.setSeconds(0); // Esto se puede cambiar y recibir seccionesHora[2]
		// System.out.println("\tDespues de convertir: " + hora.toString());
		return hora;
	}
}
