package pe.edu.sistemas.sismanweb.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	private static final Log logger = LogFactory.getLog(LoginController.class);

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView handleRequest(){
		ModelAndView mav = new ModelAndView("login");
		logger.info("-- Retornando vista Login --");
		return mav;
	}

	
	
}
