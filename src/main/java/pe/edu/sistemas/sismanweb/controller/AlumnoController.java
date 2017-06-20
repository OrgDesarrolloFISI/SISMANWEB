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
	
	protected final Log logger = LogFactory.getLog(AlumnoController.class);
	
	@Autowired
	AlumnoService alumnoService;	
			
	@GetMapping("/all")
	public ModelAndView verAlumnos(){
		ModelAndView mav = new ModelAndView("/alumno/alumno_Ver");
		List<Alumno> alumnos = alumnoService.obtenerAlumnos();
		logger.info("Retornando modelo y vista "+ " -- Datos: "+ alumnos.size());
		mav.addObject("listaAlumnos", alumnos);
		return mav;		
	}
	
	@GetMapping("/add")
	public ModelAndView agregarAlumno(){
		ModelAndView mav = new ModelAndView("/alumno/alumno_Agregar");
		return mav;
	}
	
	@GetMapping("/delete")
	public ModelAndView eliminarAlumno(){
		ModelAndView mav = new ModelAndView("/alumno/alumno_Eliminar");
		return mav;
	}
	
	
	
	
	

}
