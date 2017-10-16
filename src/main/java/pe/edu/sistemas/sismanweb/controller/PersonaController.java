package pe.edu.sistemas.sismanweb.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.sistemas.sismanweb.domain.Persona;
import pe.edu.sistemas.sismanweb.services.PersonaService;
import pe.edu.sistemas.sismanweb.services.modelform.AlumnoModelForm;

@Controller
@RequestMapping("/pruebas")
public class PersonaController {
	
	@Autowired
	PersonaService personaService;
	List<Persona> listaPersonas;
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	//@RequestMapping(value="/hola",method=RequestMethod.GET)
	/*@GetMapping("/listarPersonas")
	public ModelAndView handleRequest() {
		listaPersonas = personaService.obtenerPersonas();
		logger.info("Retornando modelo y vista "+ " -- Datos: "+ listaPersonas.size());
		ModelAndView mav = new ModelAndView("personas");		
		mav.addObject("listaPersonas", listaPersonas);        
        return mav;
    }	*/
	
	
	@ModelAttribute("modulo")
	public String modulo(){
		return "personas";
	}
	
	@ModelAttribute("fragmento")
	public String fragmento(){
		return "contentAlumnoIndividual";
	}
	
	@ModelAttribute("alumno")
	public AlumnoModelForm alumnoModel(){
		return new AlumnoModelForm();
	}
	
	@GetMapping("/layout")
	public String layout(){
		return "layout";
	}

	@GetMapping("/frag")
	public String frag(){
		return "layout/alumnoTest :: contentAlumno";
	}
}
