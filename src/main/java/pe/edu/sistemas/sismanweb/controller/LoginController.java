package pe.edu.sistemas.sismanweb.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.sistemas.sismanweb.entidades.Persona;
import pe.edu.sistemas.sismanweb.services.PersonaService;

@Controller
public class LoginController {
	
	private static final Log logger = LogFactory.getLog(LoginController.class);

	@Autowired
	PersonaService personaService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView responseLogin(){
		ModelAndView mav = new ModelAndView("login");
		logger.info("-- Retornando vista Login --");
		return mav;
	}

	@RequestMapping(value="/validar",method=RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("persona") Persona persona,BindingResult result){
		ModelAndView mav = new ModelAndView();
		Persona resultado = personaService.obtenerPersonaXID(5237);
		mav.addObject("persona",new Persona());
		if(resultado.getPersonaCodigoSistema()!=persona.getPersonaCodigoSistema()){
			mav.setViewName("/login");
			
		}else{
			mav.setViewName("/persona/lista");
		}
		return mav;		
	}
	
}
