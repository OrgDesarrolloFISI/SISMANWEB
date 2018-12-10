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
	private DocenteDAO docenteDAO;

	public List<CursoMasivoModel> /* void */ saveBulk(List<CursoMasivoModel> listacursoMasivoModel) {
		List<CursoMasivoModel> cursosConProblemas = new ArrayList<CursoMasivoModel>();
		boolean seAgrego = false;

		for (int i = 0; i < listacursoMasivoModel.size(); i++) { // AGREGO TODOS LOS CURSOCONJUNTO QUE NO EXISTAN A TODOS LOS CURSOSMODEL
			CursoMasivoModel cmm = listacursoMasivoModel.get(i);
			System.out.println(cmm);
			CursoConjunto cc = cursoConjuntoDAO.findCursoConjuntoByCodigoCursoByNombrePlan(cmm.getCodCurso(), cmm.getNombrePlan());
			if (cc == null) {
				CursoBase cursoBase = cursoBaseDAO.findCursoBaseByNombreByPlanNombre(cmm.getDescCurso(), cmm.getNombrePlan());
				if (cursoBase != null) {
					// Crear nuevo CursoConjunto
					//Obtener el CURSOC_CODCOMUN del cursoConjunto
					CursoConjunto aux = cursoConjuntoDAO.findCursoConjuntoByNombre(cmm.getDescCurso());
					boolean seIngreso;
					if(aux==null)
						seIngreso=cursoServ.insertarCursoConjunto(cursoBase, 0);
					else
						seIngreso= cursoServ.insertarCursoConjunto(cursoBase, aux.getCursocCodcomun());
					
					System.out.println((seIngreso)?"Se ingresó el curso conjunto ":"No se ingresó el curso conjunto");
					cc = cursoConjuntoDAO.findCursoConjuntoByCodigoCursoByNombrePlan(cmm.getCodCurso(), cmm.getNombrePlan());
					System.out.println("Se agregó el curso "+cursoBase.getCursobNombre());
				}
				else {
					System.out.println("Debería crear el CursoBase con nombre "+cmm.getDescCurso()+" y el plan "+cmm.getNombrePlan());
				}
			}
		}		//Después de este método, no debería haber problema con ningun cursoConjunto
		
		for (int i = 0; i < listacursoMasivoModel.size(); i++) { // AGREGO LOS CURSOPERIODO
			CursoMasivoModel cmm = listacursoMasivoModel.get(i);
			CursoPeriodo cursoPeriodo = null;

			System.out.println(cmm.toString());
			// Puede ocurrir error si es que el modelo recibido es nulo
			CursoPeriodoModelForm cpmf = new CursoPeriodoModelForm(cmm.getCodCurso(), cmm.getDescCurso(),
					cmm.getPeriodoNombre(), cmm.getNombrePlan());
			if (!cursoPeriodoDAO.existsCursoPeriodoByAll(cmm.getCodCurso(), cmm.getNombrePlan(), cmm.getPeriodoNombre())) {
				cursoPeriodo = converterToCursoPeriodo(cpmf);
				seAgrego = insertarCursoPeriodo(cursoPeriodo);
				if (!seAgrego) {
					cursosConProblemas.add(cmm);
					System.out.println("No se agregó el cursoPeriodo en " + (i + 1));
				} else {
					System.out.println("Se agregó 1 cursoPeriodo en " + (i + 1));
				}
			} else { // Si no es el primer cursoPeriodo de la carga en agregarse
				System.out.println("Ya existía cursoPeriodo en " + (i + 1));
			}

		}

		for (int i = 0; i < listacursoMasivoModel.size(); i++) {// AGREGO HORARIOCLASE Y GRUPO
			Grupo grupo = new Grupo();
			HorarioClase horarioClase = new HorarioClase();
			CursoMasivoModel cmm = listacursoMasivoModel.get(i);

			List<HorarioClase> horarioClases = horarioClaseDAO.findHorarioClaseByGrupo(cmm.getGrupoNumero());

			CursoPeriodo cp = cursoPeriodoDAO.findCursoPeriodoByAll(cmm.getCodCurso(), cmm.getNombrePlan(),
					cmm.getPeriodoNombre());

			Aula aula = aulaDAO.findByNombreAula(cmm.getAula());

			String nombres = cmm.getDocenteNombre();
			Docente docente = docenteDAO.findDocenteByNombreByApellidoPatByApellidoMat(nombres,
					cmm.getDocenteApPaterno(), cmm.getDocenteApMaterno());

			Date horaInicio = convertirStringADate(cmm.getHoraInicio());
			Date horaFin = convertirStringADate(cmm.getHoraFinal());
			if (docente == null) { // Si no se encuentra por sus dos nombres, quizá está solo por 1 nombre
				if (nombres != null) {
					String[] cadaNombre = nombres.split(" "); // Separamos por cada nombre
					int j = 0;
					for (j = 0; j < cadaNombre.length; j++) { // Hago esto por cada nombre
						docente = docenteDAO.findDocenteByNombreByApellidoPatByApellidoMat(cadaNombre[j],
								cmm.getDocenteApPaterno(), cmm.getDocenteApMaterno());
						if (docente != null) // Cuando encuentro el docente, salgo de la iteración
							break;
					}
					if (j >= cadaNombre.length) { // Si buscó con todos los nombres y no lo encontró
						cmm.setMotivoError("El docente no existe");
						cursosConProblemas.add(cmm);
						System.out.println("El docente no existe en " + (i + 1));
						
					}
				}else {	//Docente que no tiene nombres
					cmm.setMotivoError("El docente no existe");
					cursosConProblemas.add(cmm);
					System.out.println("El docente no existe en " + (i + 1));
				}
			}
			if (cp == null) {
				cmm.setMotivoError("El Curso del Periodo no existe");
				cursosConProblemas.add(cmm);
				System.out.println("Problemas con CursoPeriodo en " + (i + 1));
			}
			if (docente != null && cp != null) {
				grupo.setCursoPeriodo(cp);
				grupo.setGrupoNumero(cmm.getGrupoNumero());
				grupo.setHorarioClases(new HashSet<HorarioClase>(horarioClases));
				grupoDAO.save(grupo);
				System.out.print("Se agregó un grupo en " + (i + 1) + ". ");

				// horarioClase.setIdhorarioClase(0); Se pone automático
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
			}

		}
		return cursosConProblemas;
	}

	public CursoPeriodo converterToCursoPeriodo(CursoPeriodoModelForm formCursoPeriodoModel) {
		CursoPeriodo cursoPeriodo = new CursoPeriodo();
		CursoConjunto cc = cursoConjuntoDAO.findCursoConjuntoByCodigoCursoByNombrePlan(
				formCursoPeriodoModel.getCodCurso(), formCursoPeriodoModel.getPlanNombre());
		/*if (cc == null) {		//No debería haber ningún problema porque se agregan todos los CursoConjunto al comienzo
			CursoBase cursoBase = cursoBaseDAO.findCursoBaseByNombreByPlanNombre(formCursoPeriodoModel.getCursoPeriodoNombre(), formCursoPeriodoModel.getPlanNombre());
			if (cursoBase != null) {
				// Crear nuevo CursoConjunto
				boolean seIngreso = cursoServ.insertarCursoConjunto(cursoBase, 0);
				System.out.println((seIngreso)?"Se ingresó el curso conjunto ":"No se ingresó el curso conjunto");
				cc = cursoConjuntoDAO.findCursoConjuntoByCodigoCursoByNombrePlan(formCursoPeriodoModel.getCodCurso(),
						formCursoPeriodoModel.getPlanNombre());
			}
			else {
				System.out.println("Debería crear el Curso Base con nombre "+formCursoPeriodoModel.getCursoPeriodoNombre()+" y el plan "+formCursoPeriodoModel.getPlanNombre());
			}
		}*/

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
		Date hora = new Date(70, 0, 1);
		// System.out.print("Antes de convertir: " + fecha);
		String[] seccionesHora = fecha.split(":"); // Suponiendo que la entrada es HH:MM:SS solo usaremos HH y MM
		hora.setHours(Integer.parseInt(seccionesHora[0]));
		hora.setMinutes(Integer.parseInt(seccionesHora[1]));
		hora.setSeconds(0); // Esto se puede cambiar y recibir seccionesHora[2]
		// System.out.println("\tDespues de convertir: " + hora.toString());
		return hora;
	}
}
