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
import pe.edu.sistemas.sismanweb.dao.CursoConjuntoDAO;
import pe.edu.sistemas.sismanweb.dao.CursoPeriodoDAO;
import pe.edu.sistemas.sismanweb.dao.DocenteDAO;
import pe.edu.sistemas.sismanweb.dao.GrupoDAO;
import pe.edu.sistemas.sismanweb.dao.HorarioClaseDAO;
import pe.edu.sistemas.sismanweb.dao.PeriodoDAO;
import pe.edu.sistemas.sismanweb.domain.Aula;
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
	
	@Autowired private AulaDAO aulaDAO;
	@Autowired private CursoConjuntoDAO cursoConjuntoDAO;
	@Autowired private CursoPeriodoDAO cursoPeriodoDAO;
	@Autowired private GrupoDAO grupoDAO;
	@Autowired private HorarioClaseDAO horarioClaseDAO;
	@Autowired private PeriodoDAO periodoDAO;
	
	@Autowired private DocenteDAO docenteDAO;
	
	public /*List<CursoPeriodo>*/void saveBulk(List<CursoMasivoModel> listacursoMasivoModel){
		List<CursoPeriodo> cursosExistentes = new ArrayList<CursoPeriodo>();
		
		
		Boolean existe;
		for(int i=0; i< listacursoMasivoModel.size(); i++){		//AGREGO LOS CURSOPERIODO
			CursoMasivoModel cmm = listacursoMasivoModel.get(i);
			CursoPeriodo cursoPeriodo = null;
			
			System.out.println(cmm.toString());
			//Puede ocurrir error si es que el modelo recibido es nulo
			CursoPeriodoModelForm cpmf=new CursoPeriodoModelForm(cmm.getCodCurso(), cmm.getDescCurso(), cmm.getPeriodoNombre(), cmm.getNombrePlan());
			if(!cursoPeriodoDAO.existsCursoPeriodoBy(cmm.getDescCurso(), Integer.parseInt(cmm.getPeriodoNombre()))) {
				cursoPeriodo = converterToCursoPeriodo(cpmf);
				/*existe = */insertarCursoPeriodo(cursoPeriodo);
				System.out.println("Se agregó 1 curso");
			}
			//Faltaría cambiar la estructura de salida
			//if(existe){				//Esto se usaba para devolver los que ya existían
				//cursosExistentes.add(cursoPeriodo);		
			//}			
		}
		
		/*
		for (int i = 0; i < listacursoMasivoModel.size(); i++) {//AGREGO LOS GRUPOS (Necesito de que ya exista CursoPeriodo)
			Grupo grupo=new Grupo();
			CursoMasivoModel cmm = listacursoMasivoModel.get(i);
			List<HorarioClase> horarioClases= horarioClaseDAO.findHorarioClaseByGrupo(cmm.getGrupoNumero());
			CursoPeriodo cp=cursoPeriodoDAO.findCursoPeriodoByAll(cmm.getCodCurso(), cmm.getNombrePlan(), cmm.getPeriodoNombre());
			grupo.setCursoPeriodo(cp);
			grupo.setGrupoNumero(cmm.getGrupoNumero());
			grupo.setHorarioClases(new HashSet<HorarioClase>(horarioClases));
			grupoDAO.save(grupo);
			
		}
		
		for (int i = 0; i < listacursoMasivoModel.size(); i++) {//AGREGO HORARIOCLASE (Necesito de que ya exista grupo)
			HorarioClase horarioClase=null;
			CursoMasivoModel cmm = listacursoMasivoModel.get(i);
			Aula aula= aulaDAO.findByNombreAula(cmm.getAula());
			Docente docente = docenteDAO.findDocenteByNombreByApellidoPatByApellidoMat
					(cmm.getDocenteNombre(), cmm.getDocenteApPaterno(), cmm.getDocenteApMaterno());
			//Reusar con el agregar grupos
			CursoPeriodo cp=cursoPeriodoDAO.findCursoPeriodoByAll(cmm.getCodCurso(), cmm.getNombrePlan(), cmm.getPeriodoNombre());
			Grupo grupo=grupoDAO.findByidcursoPeriodoBygrupoNumero(cp.getIdcursoPeriodo(), cmm.getGrupoNumero());
			Date horaInicio=null;	//Buscar convertir de String a Date
			Date horaFin=null;
			
			//horarioClase.setIdhorarioClase(0); Se pone automático
			horarioClase.setAula(aula);
			horarioClase.setDia(cmm.getDia());
			horarioClase.setDocente(docente);
			horarioClase.setGrupo(grupo);
			horarioClase.setHoraInicio(horaInicio);
			horarioClase.setHoraFin(horaFin);
			horarioClase.setHorarioClasePeriodo(cmm.getNombrePeriodo());
			horarioClase.setHorarioClaseTipo(cmm.getTipoClase());
			horarioClase.setnombreAula(cmm.getAula());
			
			
			horarioClaseDAO.save(horarioClase);
			
		}*/
		
		for (int i = 0; i < listacursoMasivoModel.size(); i++) {//AGREGO HORARIOCLASE Y GRUPO
			Grupo grupo=new Grupo();
			HorarioClase horarioClase=new HorarioClase();
			CursoMasivoModel cmm = listacursoMasivoModel.get(i);
			
			List<HorarioClase> horarioClases= horarioClaseDAO.findHorarioClaseByGrupo(cmm.getGrupoNumero());
			
			CursoPeriodo cp=cursoPeriodoDAO.findCursoPeriodoByAll(cmm.getCodCurso(), cmm.getNombrePlan(), cmm.getPeriodoNombre());
			
			Aula aula= aulaDAO.findByNombreAula(cmm.getAula());
			
			Docente docente = docenteDAO.findDocenteByNombreByApellidoPatByApellidoMat
					(cmm.getDocenteNombre(), cmm.getDocenteApPaterno(), cmm.getDocenteApMaterno());
			
			Date horaInicio=convertirStringADate(cmm.getHoraInicio());	
			Date horaFin=convertirStringADate(cmm.getHoraFinal());
			
			grupo.setCursoPeriodo(cp);
			grupo.setGrupoNumero(cmm.getGrupoNumero());
			grupo.setHorarioClases(new HashSet<HorarioClase>(horarioClases));
			grupoDAO.save(grupo);
			
			
			//horarioClase.setIdhorarioClase(0); Se pone automático
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
			
		}
		
		//return cursosExistentes;	//Debería ser void
	}
	
	public CursoPeriodo converterToCursoPeriodo(CursoPeriodoModelForm formCursoPeriodoModel){
		CursoPeriodo cursoPeriodo = new CursoPeriodo();
		CursoConjunto cc=cursoConjuntoDAO.findCursoConjuntoByCodigoCursoByNombrePlan(formCursoPeriodoModel.getCodCurso(), formCursoPeriodoModel.getPlanNombre());
		Periodo p = periodoDAO.findById(Integer.parseInt(formCursoPeriodoModel.getPeriodo()));
		
		cursoPeriodo.setPeriodo(p);
		cursoPeriodo.setCursoConjunto(cc);
		cursoPeriodo.setCursoPeriodoNombre(formCursoPeriodoModel.getCursoPeriodoNombre());
		
		return cursoPeriodo;
	}
	public boolean insertarCursoPeriodo(CursoPeriodo cursoPeriodo){
		if(cursoPeriodo==null) {
			System.out.println("Ocurrió un error al ingresar cursoPeriodo");
			return true;
		}
		else
			cursoPeriodoDAO.save(cursoPeriodo);
		return false;
	}
	
	public Date convertirStringADate(String fecha) {
		Date hora=new Date(70,0,1);
		System.out.print("Antes de convertir: "+fecha);
		String[] seccionesHora = fecha.split(":");	//Suponiendo que la entrada es HH:MM:SS solo usaremos HH y MM
		hora.setHours(Integer.parseInt(seccionesHora[0]));
		hora.setMinutes(Integer.parseInt(seccionesHora[1]));
		hora.setSeconds(0);	//Esto se puede cambiar y recibir seccionesHora[2]
		System.out.println("\tDespues de convertir: "+hora.toString());
		return hora;
	}
}
