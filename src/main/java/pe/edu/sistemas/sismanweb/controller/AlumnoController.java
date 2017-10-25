package pe.edu.sistemas.sismanweb.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@ModelAttribute("titulo")
	public String titulo(){
		return "Modulo alumno";
	}
	
	@ModelAttribute("modulo")
	public String modulo(){
		return "alumno/alumno";
	}
			
	@GetMapping("/all")
	public String verAlumnos(Model model){
		model.addAttribute("fragmento", "contentAlumnoBuscador");
		model.addAttribute("search", new Search());
		model.addAttribute("listaAlumno", alumnos);
		logger.info("SE DEVUELVEN ALUMNOS : " + alumnos.size());
		alumnos=new ArrayList<AlumnoModelForm>();
		return VariablesGlobales.LAYOUT;		
	}
	
	@GetMapping({"/form","/form/{id}"})
	public String formularioAlumno(Model model,@RequestParam(name="existe",required=false) String existe,
			@PathVariable(name="id",required=false)String id){
		model.addAttribute("fragmento", "contentAlumnoIndividual");
		List<Plan> planesDeEstudio = planService.obtenerPlanes();
		model.addAttribute("listaPlan", planesDeEstudio);
		if(id!=null){ // formulario con datos a editar
			AlumnoModelForm alumnoModel;
			logger.info("EDITAR ALUMNO CON ID: "+id);
			alumnoModel = alumnoService.converterToAlumnoModelForm((alumnoService.obtenerAlumnoxID(Integer.parseInt(id))));
			model.addAttribute("alumno", alumnoModel);
		}else{		  // formaulario vacio	
			model.addAttribute("alumno", new AlumnoModelForm());
		}
		model.addAttribute("existe", existe);		
		logger.info("RETORNANDO FORMULARIO ALUMNO");
		return VariablesGlobales.LAYOUT;
	}
	
	
	@PostMapping("/add")
	public String agregarAlumno(Model model, @ModelAttribute("alumno") AlumnoModelForm alumnoPersonaModel){		
		Alumno alumno = alumnoService.converterToAlumno(alumnoPersonaModel);
		logger.info("DATOS RECIBIDOS : "+ alumnoPersonaModel.getCodigo()+" -- IDALUMNO:"+alumnoPersonaModel.getIdAlumno() + " -- IDPERSONA:" +alumnoPersonaModel.getIdPersona() );
		boolean existe;
		if(alumnoPersonaModel.getIdAlumno()==0){ // agregar alumno
			existe = alumnoService.insertarAlumno(alumno);
			if(existe){
				logger.info("AGREGAR ALUMNO --- Codigo ya existente");
				return "redirect:/alumno/form?existe";
			}
			logger.info("ALUMNO AGREGADO");
			model.addAttribute("fragmento", "contentAlumnoAvisoExitoIndiv");
			return VariablesGlobales.LAYOUT;	
		}else{									// editar alumno
			Persona persona_codigo = personaService.obtenerPersonaxCodigo(alumno.getPersona().getPersonaCodigo());
			existe = alumnoService.actualizarAlumno(alumno, persona_codigo);
			if(existe){
				logger.info("LA ACTUALIZACION NO PROCEDE");
				return "redirect:/alumno/form/"+alumno.getIdAlumno()+"?existe";
			}else{
				logger.info("ALUMNO ACTUALIZADO");
				model.addAttribute("fragmento", "contentAlumnoAvisoEdicionIndiv");
				return VariablesGlobales.LAYOUT;
			}
		}				
	}	
	
	@GetMapping("/bulk")
	public String bulkAlumnos(Model model){
		model.addAttribute("fragmento", "contentAlumnoGrupal");
		model.addAttribute("listaPlanes", planService.obtenerPlanes());
		logger.info("RETORNANDO VISTA CARGA MASIVA -- ALUMNO");
		return VariablesGlobales.LAYOUT;		
	}

	@PostMapping("/addBulk")
	public String agregarAlumnos(Model model,@RequestBody String listAlumno ){
		logger.info("CADENA RECIBIDA: "+listAlumno);		
		JSONArray jsonArrayAlumno = new JSONArray(listAlumno);
		DeserealizarJSON<AlumnoModelForm> deserealizador = new DeserealizarJSON<AlumnoModelForm>(AlumnoModelForm.class);
		List<AlumnoModelForm> alumnosModel = null;
		List<Alumno> resultado = null;
		logger.info("CANTIDAD DE REGISTROS: "+jsonArrayAlumno.length());
		
		alumnosModel = deserealizador.deserealiza(jsonArrayAlumno);
		
		if(jsonArrayAlumno.length()!=alumnosModel.size()){
			logger.error("ENVIANDO MENSAJE DE ERROR EN REGISTRO NRO "+(alumnosModel.size()+1));
			return "alumno/alumno :: contentAlumnoAvisoErrorGrup";
		}else{
			try{
			resultado = alumnoService.saveBulk(alumnosModel);
			
			}catch(Exception e){
				logger.warn("ERROR AL REGISTRAR");
				return "alumno/alumno :: contentAlumnoAvisoErrorPlanGrup";
			}
			model.addAttribute("cantidadAlumnosGuardados",(jsonArrayAlumno.length()-resultado.size()));
			if(!resultado.isEmpty()){
				model.addAttribute("listaAlumnosRepetidos", resultado);
				logger.warn("EXISTEN "+resultado.size() +" ALUMNOS YA REGISTRADOS");
				return "alumno/alumno :: contentAlumnoAvisoExistenGrup";
				
			}else{
				logger.info("SE REGISTRO EXITOSAMENTE ALUMNOS");
				return "alumno/alumno :: contentAlumnoAvisoExitoGrup";
			}				
		}			
	}	
	
	
	@GetMapping("/search")
	public String BuscarAlumnos(@ModelAttribute("search") Search search){			
		alumnos = alumnoService.buscarAlumnosxParam(search.getValor(),search.getFiltro());
		logger.info("SE ENCONTRO ALUMNOS: " + alumnos.size());
		return "redirect:/alumno/all";
	}

}
