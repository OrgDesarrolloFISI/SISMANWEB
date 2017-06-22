package pe.edu.sistemas.sismanweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.sistemas.sismanweb.entidades.Docente;
import pe.edu.sistemas.sismanweb.services.DocenteService;

@Controller
@RequestMapping("/docente")
public class DocenteController {
	
	@Autowired
	DocenteService docenteService;	
	
	@GetMapping("/all")
	public ModelAndView verDocentes(){
		ModelAndView mav = new ModelAndView("/docente/docente_Ver");
		List<Docente> docentes = docenteService.obtenerDocentes();
		mav.addObject("listaDocentes",docentes);
		return mav;
	}
	
	@GetMapping("/form")
	public ModelAndView agregarDocente(){
		ModelAndView mav = new ModelAndView("/docente/docente_Form");
		return mav;
	}
	
	
	
	
	
	

}
