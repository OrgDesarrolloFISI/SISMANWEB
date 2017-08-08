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

import pe.edu.sistemas.sismanweb.entidades.CursoBase;
import pe.edu.sistemas.sismanweb.entidades.Plan;
import pe.edu.sistemas.sismanweb.services.CursoService;
import pe.edu.sistemas.sismanweb.services.PlanService;
import pe.edu.sistemas.sismanweb.services.modelform.CursoModelForm;

@Controller
@RequestMapping("/curso")
public class CursoController {
	
	private static final Log logger = LogFactory.getLog(CursoController.class);
	
	@Autowired CursoService cursoService;	
	@Autowired PlanService  planService;
	
	@GetMapping("/all")
	public ModelAndView verCursos(){
		ModelAndView mav = new ModelAndView("/curso/curso_Ver");
		List<CursoBase> cursos = cursoService.obtenerCursos();
		logger.info("Busqueda -- Retornando modelo y vista "+ " -- Datos: "+ cursos.size());
		mav.addObject("listaCursos",cursos);
		return mav;
	}

	@GetMapping("/form")
	public ModelAndView formularioCurso(){
		ModelAndView mav = new ModelAndView("/curso/curso_Form");
		List<Plan> planesDeEstudio = planService.obtenerPlanes();
		mav.addObject("listaPlan",planesDeEstudio);
		mav.addObject("curso", new CursoModelForm());
		logger.info("Retornando formulario Curso");		
		return mav;
	}
	
	@PostMapping("/add")
	public String agregarCurso(@ModelAttribute("curso") CursoModelForm cursoModelForm){
		CursoBase cursoBase = cursoService.coverterToCurso(cursoModelForm);
		cursoService.insertarCurso(cursoBase);
		logger.info("Agregando datos de: "+cursoModelForm.getCodigo()+" -- "+cursoModelForm.getNombre());
		return "redirect:/curso/form";
	}
	

	
}
