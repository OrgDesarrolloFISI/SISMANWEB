package pe.edu.sistemas.sismanweb.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.sistemas.sismanweb.domain.Alumno;
import pe.edu.sistemas.sismanweb.domain.Plan;
import pe.edu.sistemas.sismanweb.services.AlumnoService;
import pe.edu.sistemas.sismanweb.services.PersonaService;
import pe.edu.sistemas.sismanweb.services.PlanService;
import pe.edu.sistemas.sismanweb.services.modelform.AlumnoModelForm;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	
	protected final Log logger = LogFactory.getLog(AlumnoController.class);
	
	@Autowired AlumnoService alumnoService;		
	@Autowired PersonaService personaService;	
	@Autowired PlanService planService;
			
	@GetMapping("/all")
	@Transactional
	public ModelAndView verAlumnos(){		
		ModelAndView mav = new ModelAndView("/alumno/alumno_Ver");
		List<Alumno> alumnos = alumnoService.obtenerAlumnos();
		logger.info("Busqueda -- Retornando modelo y vista "+ " -- Datos: "+ alumnos.size());
		mav.addObject("listaAlumno", alumnos);
		return mav;		
	}
	
	@GetMapping("/form")
	public ModelAndView formularioAlumno(@RequestParam(name="existe",required=false) String existe){
		ModelAndView mav = new ModelAndView("/alumno/alumno_Form");
		List<Plan> planesDeEstudio = planService.obtenerPlanes();
		mav.addObject("listaPlan", planesDeEstudio);
		mav.addObject("alumno", new AlumnoModelForm());
		mav.addObject("existe", existe);
		logger.info("RETORNANDO FORMULARIO ALUMNO");
		return mav;
	}
	
	@PostMapping("/add")
	public String agregarAlumno(@ModelAttribute("alumno") AlumnoModelForm alumnoPersonaModel){
		Alumno alumno = alumnoService.converterToAlumno(alumnoPersonaModel);
		logger.info("AGREGANDO DATOS DE : "+ alumnoPersonaModel.getCodigo()+" -- "+alumnoPersonaModel.getIdPlan());
		boolean existe = alumnoService.insertarAlumno(alumno);
		if(existe){
			logger.info("AGREGAR ALUMNO --- Codigo ya existente");
			return "redirect:/alumno/form?existe";
		}
		return "redirect:/alumno/all";
	}		
	
	

}
