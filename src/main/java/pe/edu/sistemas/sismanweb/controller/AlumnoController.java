package pe.edu.sistemas.sismanweb.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.sistemas.sismanweb.domain.Alumno;
import pe.edu.sistemas.sismanweb.domain.Persona;
import pe.edu.sistemas.sismanweb.domain.Plan;
import pe.edu.sistemas.sismanweb.services.AlumnoService;
import pe.edu.sistemas.sismanweb.services.PersonaService;
import pe.edu.sistemas.sismanweb.services.PlanService;
import pe.edu.sistemas.sismanweb.services.modelform.AlumnoModelForm;
import pe.edu.sistemas.sismanweb.util.DeserealizarJSON;
import pe.edu.sistemas.sismanweb.util.Search;
import pe.edu.sistemas.sismanweb.util.VariablesGlobales;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	
	
	protected final Log logger = LogFactory.getLog(AlumnoController.class);
	
	@Autowired AlumnoService alumnoService;		
	@Autowired PersonaService personaService;	
	@Autowired PlanService planService;
	
	List<AlumnoModelForm> alumnos = new ArrayList<AlumnoModelForm>();
			
	@GetMapping("/all")
	public ModelAndView verAlumnos(){		
		ModelAndView mav = new ModelAndView(VariablesGlobales.ALUMNO_VIEW);
		mav.addObject("search", new Search());
		mav.addObject("listaAlumno", alumnos);
		alumnos=new ArrayList<AlumnoModelForm>();
		logger.info("SE DEVUELVEN ALUMNOS : " + alumnos.size());
		return mav;		
	}
	
	@GetMapping({"/form","/form/{id}"})
	public ModelAndView formularioAlumno(@RequestParam(name="existe",required=false) String existe,
			@PathVariable(name="id",required=false)String id){
		ModelAndView mav = new ModelAndView(VariablesGlobales.ALUMNO_FORM);
		List<Plan> planesDeEstudio = planService.obtenerPlanes();
		mav.addObject("listaPlan", planesDeEstudio);
		if(id!=null){
			AlumnoModelForm alumnoModel;
			logger.info("EDITAR ALUMNO CON ID: "+id);
			alumnoModel = alumnoService.converterToAlumnoModelForm((alumnoService.obtenerAlumnoxID(Integer.parseInt(id))));
			mav.addObject("alumno", alumnoModel);
		}else{
			mav.addObject("alumno", new AlumnoModelForm());
		}
		mav.addObject("existe", existe);
		
		logger.info("RETORNANDO FORMULARIO ALUMNO");
		return mav;
	}
	
	
	@PostMapping("/add")
	public String agregarAlumno(@ModelAttribute("alumno") AlumnoModelForm alumnoPersonaModel){
		
		Alumno alumno = alumnoService.converterToAlumno(alumnoPersonaModel);
		logger.info("DATOS RECIBIDOS : "+ alumnoPersonaModel.getCodigo()+" -- IDALUMNO:"+alumnoPersonaModel.getIdAlumno() + " -- IDPERSONA:" +alumnoPersonaModel.getIdPersona() );
		boolean existe;
		if(alumnoPersonaModel.getIdAlumno()==0){
			existe = alumnoService.insertarAlumno(alumno);
			if(existe){
				logger.info("AGREGAR ALUMNO --- Codigo ya existente");
				return "redirect:/alumno/form?existe";
			}
			logger.info("ALUMNO AGREGADO");
		}else{
			Persona persona_codigo = personaService.obtenerPersonaxCodigo(alumno.getPersona().getPersonaCodigo());
			existe = alumnoService.actualizarAlumno(alumno, persona_codigo);
			if(existe){
				logger.info("LA ACTUALIZACION NO PROCEDE");
				return "redirect:/alumno/form/"+alumno.getIdAlumno()+"?existe";
			}else{
				logger.info("ALUMNO ACTUALIZADO");
				return "redirect:/alumno/form";
			}
		}		
		return "redirect:/alumno/all";	
	}	
	

	@PostMapping("/addBulk")
	public String agregarAlumnos(@RequestBody String listAlumno ){
		ModelAndView mav = new ModelAndView(VariablesGlobales.ALUMNO_FORM);
		logger.info("CADENA RECIBIDA: "+listAlumno);		
		JSONArray jsonArrayAlumno = new JSONArray(listAlumno);
		DeserealizarJSON<AlumnoModelForm> deserealizador = new DeserealizarJSON<AlumnoModelForm>(AlumnoModelForm.class);
		List<AlumnoModelForm> alumnosModel = null;
		List<Alumno> resultado = null;
		logger.info("CANTIDAD DE REGISTROS: "+jsonArrayAlumno.length());
		
		alumnosModel = deserealizador.deserealiza(jsonArrayAlumno);
		
		if(jsonArrayAlumno.length()!=alumnosModel.size()){
			logger.error("ENVIANDO MENSAJE DE ERROR EN REGISTRO: "+(alumnosModel.size()+1));
			//mav.addObject("errorRegistro", alumnosModel.size()+1);
		}else{
			resultado = alumnoService.saveBulk(alumnosModel);
			if(!resultado.isEmpty()){
				logger.warn("EXISTEN "+resultado.size()+" ALUMNOS YA REGISTRADOS");
				//mav.addObject("errorExiste",resultado.size());
			}else{
				logger.info("SE REGISTRO EXITOSAMENTE ALUMNOS");
				//mav.addObject("exito");
			}				
		}			
		return "redirect:/alumno/form";
	}	
	
	
	@GetMapping("/search")
	public String BuscarAlumnos(@ModelAttribute("search") Search search){
			
		alumnos = alumnoService.buscarAlumnosxParam(search.getValor(),search.getFiltro());
		logger.info("SE ENCONTRO ALUMNOS: " + alumnos.size());
		return "redirect:/alumno/all";
	}

}
