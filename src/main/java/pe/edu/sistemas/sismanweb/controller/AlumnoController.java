package pe.edu.sistemas.sismanweb.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.sistemas.sismanweb.entidades.Alumno;
import pe.edu.sistemas.sismanweb.services.AlumnoService;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	AlumnoService alumnoService;
	List<Alumno> listaAlumno;
	
	@GetMapping("")
	public String vistaAlumno(){
		return "alumnos";		
	}
	
	@GetMapping("/allAlumnos")
	public ModelAndView obtenerAlumnos(){
		ModelAndView mav = new ModelAndView("cursos_Eliminar");
		listaAlumno = alumnoService.obtenerAlumnos();
		logger.info("Retornando modelo y vista "+ " -- Datos: "+ listaAlumno.size());
		mav.addObject("listaAlumnos", listaAlumno);
		return mav;		
	}
	
	
	
	
	
	

}
