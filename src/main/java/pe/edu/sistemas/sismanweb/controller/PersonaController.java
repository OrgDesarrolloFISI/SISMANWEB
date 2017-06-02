package pe.edu.sistemas.sismanweb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.sistemas.sismanweb.services.PersonaService;

@Controller
public class PersonaController {
	
	@Autowired
	PersonaService personaService;
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping(value="/exam.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Returning hello view");
        return new ModelAndView("example.html");
    }	

}
