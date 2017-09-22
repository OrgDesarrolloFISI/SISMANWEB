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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.sistemas.sismanweb.domain.CursoBase;
import pe.edu.sistemas.sismanweb.domain.Plan;
import pe.edu.sistemas.sismanweb.services.CursoService;
import pe.edu.sistemas.sismanweb.services.PlanService;
import pe.edu.sistemas.sismanweb.services.modelform.AlumnoModelForm;
import pe.edu.sistemas.sismanweb.services.modelform.CursoModelForm;
import pe.edu.sistemas.sismanweb.util.VariablesGlobales;

@Controller
@RequestMapping("/curso")
public class CursoController {
	
	private static final Log logger = LogFactory.getLog(CursoController.class);
	
	@Autowired CursoService cursoService;	
	@Autowired PlanService  planService;
	
	@GetMapping("/all")
	public ModelAndView verCursos(){
		ModelAndView mav = new ModelAndView(VariablesGlobales.CURSO_VIEW);
		List<CursoBase> cursos = cursoService.obtenerCursos();
		logger.info("Busqueda -- Retornando modelo y vista "+ " -- Datos: "+ cursos.size());
		mav.addObject("listaCursos",cursos);
		return mav;
	}

	@GetMapping("/form")
	public ModelAndView formularioCurso(@RequestParam(name="existe",required=false) String existe){
		ModelAndView mav = new ModelAndView(VariablesGlobales.CURSO_FORM);
		List<Plan> planesDeEstudio = planService.obtenerPlanes();
		mav.addObject("listaPlan",planesDeEstudio);
		mav.addObject("curso", new CursoModelForm());
		mav.addObject("existe", existe);
		System.out.println(existe);
		logger.info("Retornando formulario Curso");		
		return mav;
	}
	
	@PostMapping("/add")
	public String agregarCurso(@ModelAttribute("curso") CursoModelForm cursoModelForm){
		logger.info("Agregando datos de: "+cursoModelForm.getCodigo()+" -- "+cursoModelForm.getNombre());
		CursoBase cursoBase = cursoService.coverterToCurso(cursoModelForm);
		boolean existe = cursoService.insertarCurso(cursoBase);	
		if(existe){
			logger.info("AGREGAR CURSO --- Codigo ya existente");
			return "redirect:/curso/form?existe";
		}
		return "redirect:/curso/form";
	}

	
	@GetMapping("/conjunto")
	public ModelAndView gestionarConjuntoCurso(){
		ModelAndView mav = new ModelAndView(VariablesGlobales.CURSO_CONJUNTO);
		logger.info("RETORNANDO VISTA CURSO CONJUNTO");
		return mav;
	}

}
