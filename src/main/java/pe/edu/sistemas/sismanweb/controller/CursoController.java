package pe.edu.sistemas.sismanweb.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.sistemas.sismanweb.entidades.CursoBase;
import pe.edu.sistemas.sismanweb.entidades.Plan;
import pe.edu.sistemas.sismanweb.services.CursoService;
import pe.edu.sistemas.sismanweb.services.PlanService;

@Controller
@RequestMapping("/curso")
public class CursoController {
	
	protected final Log logger = LogFactory.getLog(CursoController.class);
	
	@Autowired
	CursoService cursoService;
	
	@Autowired
	PlanService  planService;
	
	@GetMapping("/all")
	public ModelAndView verCursos(){
		ModelAndView mav = new ModelAndView("/curso/curso_Ver");
		List<CursoBase> cursos = cursoService.obtenerCursos();
		logger.info("Busqueda -- Retornando modelo y vista "+ " -- Datos: "+ cursos.size());
		mav.addObject("listaCursos",cursos);
		return mav;
	}

	@GetMapping("/form")
	public ModelAndView agregarCurso(){
		ModelAndView mav = new ModelAndView("/curso/curso_Form");
		List<Plan> planesDeEstudio = planService.obtenerPlanes();
		mav.addObject("listaPlan",planesDeEstudio);
		
		return mav;
	}
	

	
}
