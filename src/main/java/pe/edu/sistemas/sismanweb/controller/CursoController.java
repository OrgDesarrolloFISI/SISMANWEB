package pe.edu.sistemas.sismanweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.sistemas.sismanweb.services.CursoService;

@Controller
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	CursoService cursoService;
	
	@GetMapping("/all")
	public ModelAndView verCursos(){
		ModelAndView mav = new ModelAndView("/curso/curso_Ver");
		return mav;
	}

	@GetMapping("/form")
	public ModelAndView agregarCurso(){
		ModelAndView mav = new ModelAndView("/curso/curso_Form");
		return mav;
	}
	

	
}
