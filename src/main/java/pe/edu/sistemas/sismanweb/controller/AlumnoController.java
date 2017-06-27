package pe.edu.sistemas.sismanweb.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.sistemas.sismanweb.entidades.Alumno;
import pe.edu.sistemas.sismanweb.entidades.Plan;
import pe.edu.sistemas.sismanweb.model.AlumnoPersonaModel;
import pe.edu.sistemas.sismanweb.services.AlumnoService;
import pe.edu.sistemas.sismanweb.services.PersonaService;
import pe.edu.sistemas.sismanweb.services.PlanService;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	
	protected final Log logger = LogFactory.getLog(AlumnoController.class);
	
	@Autowired
	AlumnoService alumnoService;	
	
	@Autowired
	PersonaService personaService;
	
	@Autowired
	PlanService planService;
			
	@GetMapping("/all")
	public ModelAndView verAlumnos(){
		ModelAndView mav = new ModelAndView("/alumno/alumno_Ver");
		List<Alumno> alumnos = alumnoService.obtenerAlumnos();
		logger.info("Retornando modelo y vista "+ " -- Datos: "+ alumnos.size());
		mav.addObject("listaAlumno", alumnos);
		return mav;		
	}
	
	@GetMapping("/form")
	public ModelAndView formularioAlumno(){
		ModelAndView mav = new ModelAndView("/alumno/alumno_Form");
		List<Plan> planesDeEstudio = planService.obtenerPlanes();
		mav.addObject("listaPlan", planesDeEstudio);
		mav.addObject("alumno", new AlumnoPersonaModel());
		logger.info("Retornando formulario");
		return mav;
	}
	
	@PostMapping("/agregar")
	public String agregarAlumno(@ModelAttribute("alumno") AlumnoPersonaModel alumnoPersonaModel){

		logger.info("Agregando datos de: "+ alumnoPersonaModel.getCodigo()+" -- "+alumnoPersonaModel.getIdPlan());
		return "redirect:/alumno/form";
	}
		
	
	

}
