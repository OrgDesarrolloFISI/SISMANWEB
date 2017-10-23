package pe.edu.sistemas.sismanweb.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.sistemas.sismanweb.domain.CursoBase;
import pe.edu.sistemas.sismanweb.domain.CursoConjunto;
import pe.edu.sistemas.sismanweb.domain.Plan;
import pe.edu.sistemas.sismanweb.services.CursoService;
import pe.edu.sistemas.sismanweb.services.PlanService;
import pe.edu.sistemas.sismanweb.services.modelform.CursoBCModelForm;
import pe.edu.sistemas.sismanweb.services.modelform.CursoModelForm;
import pe.edu.sistemas.sismanweb.util.Search;
import pe.edu.sistemas.sismanweb.util.VariablesGlobales;

@Controller
@RequestMapping("/curso")
public class CursoController {
	
	private static final Log logger = LogFactory.getLog(CursoController.class);
	
	@Autowired CursoService cursoService;	
	@Autowired PlanService  planService;
	
	List<CursoModelForm> cursos = new ArrayList<CursoModelForm>();
	
	@ModelAttribute("titulo")
	public String titulo(){
		return "Modulo curso";
	}
	
	@ModelAttribute("modulo")
	public String modulo(){
		return "curso/curso";
	}
	
	@GetMapping("/all")
	public String verCursos(Model model){
		model.addAttribute("fragmento","contentCursoBuscador");
		model.addAttribute("search", new Search());
		model.addAttribute("listaCurso", cursos);
		logger.info("SE DEVUELVEN CURSOS : " + cursos.size());
		cursos=new ArrayList<CursoModelForm>();
		return VariablesGlobales.LAYOUT;	
	}

	@GetMapping("/form")
	public String formularioCurso(Model model, @RequestParam(name="existe",required=false) String existe){
		ModelAndView mav = new ModelAndView(VariablesGlobales.CURSO_FORM);
		List<Plan> planesDeEstudio = planService.obtenerPlanes();
		model.addAttribute("fragmento","contentCursoRegistro");
		model.addAttribute("listaPlan",planesDeEstudio);
		model.addAttribute("curso", new CursoModelForm());
		model.addAttribute("existe", existe);
		System.out.println(existe);
		logger.info("Retornando formulario Curso");		
		return VariablesGlobales.LAYOUT;
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
	public String gestionarConjuntoCurso(Model model, @RequestParam(name="exito",required=false) String exito){
		List<CursoBase> listaBase = null;
		List<CursoConjunto> listaConjunto = null;
		listaBase = cursoService.findCursoBaseSinConjunto();
		logger.info("CANTIDAD DE CURSOS BASES SIN CONJUNTO: " + listaBase.size());
		listaConjunto = cursoService.findCursosConjuntos();
		model.addAttribute("fragmento","contentCursoEquivalencia");
		model.addAttribute("listaCursoB",listaBase);
		model.addAttribute("listaCursoC",listaConjunto);
		model.addAttribute("cursobc",new CursoBCModelForm());
		model.addAttribute("exito", exito);		
		logger.info("RETORNANDO VISTA CURSO CONJUNTO");
		return VariablesGlobales.LAYOUT;
	}
	
	@PostMapping("/addConjunto")
	public String agregarCursoAConjunto(@ModelAttribute("cursobc") CursoBCModelForm cursoBCModelForm){
		logger.info("RECIBIEND -- Conjunto : " + cursoBCModelForm.getIdCursoConjunto() + " Base: " + cursoBCModelForm.getIdCursoBase());
		Integer idBase = cursoBCModelForm.getIdCursoBase();
		Integer idConjunto = cursoBCModelForm.getIdCursoConjunto();
		CursoBase cursoBase = null;
		if(idBase !=null && idConjunto!=null){
			cursoBase = cursoService.findCursoBById(idBase);
			boolean exito = cursoService.insertarCursoConjunto(cursoBase, idConjunto);
			if(exito){
				logger.info("CREANDO NUEVO GRUPO PARA EL CURSO");
			}else{
				logger.info("AGREGANDO CURSO BASE AL CONJUNTO");
			}
			return "redirect:/curso/conjunto?exito";
		}else{
			logger.info("ALGUNO DE LOS VALORES ES NULO");
			return "redirect:/curso/conjunto";
		}
		
	}
	
	@GetMapping("/search")
	public String BuscarCursos(@ModelAttribute("search") Search search){			
		cursos = cursoService.buscarCursosxParam(search.getValor(),search.getFiltro());
		logger.info("SE ENCONTRO CURSOS: " + cursos.size());
		return "redirect:/curso/all";
	}
	
	
	

}
