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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.sistemas.sismanweb.domain.CursoBase;
import pe.edu.sistemas.sismanweb.domain.CursoConjunto;
import pe.edu.sistemas.sismanweb.domain.Plan;
import pe.edu.sistemas.sismanweb.services.CursoService;
import pe.edu.sistemas.sismanweb.services.PlanService;
import pe.edu.sistemas.sismanweb.services.modelform.CursoBCModelForm;
import pe.edu.sistemas.sismanweb.services.modelform.CursoModelForm;
import pe.edu.sistemas.sismanweb.util.Search;

@Controller
@RequestMapping("/curso")
public class CursoController {
	
	private static final Log logger = LogFactory.getLog(CursoController.class);
	
	@Autowired CursoService cursoService;	
	@Autowired PlanService  planService;
	
	boolean flagB = false;
	boolean flagC = false;
	
	List<CursoModelForm> cursos = new ArrayList<CursoModelForm>();
	List<CursoBase> cursosb = new ArrayList<CursoBase>();
	List<CursoConjunto> cursosc = new ArrayList<CursoConjunto>();
	
	@ModelAttribute("titulo")
	public String titulo(){
		return "Curso";
	}
	@GetMapping("/all")
	public String verCursos(Model model){
		model.addAttribute("search", new Search());
		model.addAttribute("listaCurso", cursos);
		logger.info("SE DEVUELVEN CURSOS : " + cursos.size());
		cursos=new ArrayList<CursoModelForm>();
		return "curso/buscador";	
	}
	
	@GetMapping("/allCursoB")
	public String verCursosBaseSinConjunto(Model model){
		model.addAttribute("search", new Search());
		
		if(!flagB){
			cursosb = cursoService.findCursoBaseSinConjunto();
		}
		flagB = false;
		model.addAttribute("listaCursoB", cursosb);
		logger.info("SE DEVUELVEN CURSOS SIN CONJUNTO : " + cursosb.size());
		cursosb=new ArrayList<CursoBase>();
		return "curso/cursosBase";	
	}
	
	@GetMapping("/allCursoC/{idB}")
	public String verCursosConjunto(Model model, @PathVariable(name="idB",required=true)String idBase){
		model.addAttribute("search", new Search());
		
		if(!flagC){
		cursosc = cursoService.findCursosConjuntos();
		}
		flagC = false;
		CursoBase cursoB = cursoService.findCursoBById(Integer.parseInt(idBase));
		model.addAttribute("cursoB", cursoB );
		model.addAttribute("listaCursoC", cursosc);
		logger.info("SE DEVUELVEN CURSOS : " + cursosc.size());
		cursosc=new ArrayList<CursoConjunto>();
		return "curso/cursosConjunto";	
	}
	
	@GetMapping("/confirmEq/{idB}/{idC}")  //Confirmar equivalencia
	public String confirmarEquivalencia(Model model, @PathVariable(name="idB",required=true)String idBase,
			@PathVariable(name="idC",required=true)String idConjunto){
		CursoConjunto cursoC;
		CursoBase cursoB = cursoService.findCursoBById(Integer.parseInt(idBase));
		
		if(Integer.parseInt(idConjunto) != 0)
		 cursoC = cursoService.findCursoCById(Integer.parseInt(idConjunto));
		else{
		 cursoC = new CursoConjunto(null, "NUEVO CURSO", 0);
		 cursoC.setIdcursoConjunto(0);
		}
			
		model.addAttribute("cursobc",new CursoBCModelForm(cursoB.getIdcursoGeneral(), cursoC.getIdcursoConjunto()));
		model.addAttribute("cursoB", cursoB);
		model.addAttribute("cursoC", cursoC);
		logger.info("SE MUESTRA INFORMACION DE CURSO base y conjunto  " + cursoB.getCursobNombre() + " " + cursoC.getCursocNombre());
		
		return "curso/confirmacionEq";	
	}
	
	
	
	@PostMapping("/addConjunto")
	public String agregarCursoAConjunto(Model model, @ModelAttribute("cursobc") CursoBCModelForm cursoBCModelForm){
		logger.info("RECIBIEND -- Conjunto : " + cursoBCModelForm.getIdCursoConjunto() + " Base: " + cursoBCModelForm.getIdCursoBase());
		Integer idBase = cursoBCModelForm.getIdCursoBase();
		Integer idConjunto = cursoBCModelForm.getIdCursoConjunto();
		CursoBase cursoBase = null;
		logger.info("iDBase: "+idBase);
		logger.info("iDConjunto: "+idConjunto);
		if(idBase !=null && idConjunto!=null){
			
			if (cursoService.verificarBaseConjuntoRepetido(idBase)){
				logger.info("Curso base con conjunto ya existente");
				model.addAttribute("fragmento", "contentCursoAvisoError");
			}
			else{	
				cursoBase = cursoService.findCursoBById(idBase);
				boolean exito = cursoService.insertarCursoConjunto(cursoBase, idConjunto);
				if(exito){
					logger.info("SE CREO NUEVO GRUPO PARA EL CURSO");
				}
				else{
					logger.info("SE AGREGO CURSO BASE A UN CONJUNTO");
				}
				model.addAttribute("fragmento", "contentCursoAvisoExitoEquiv");
			}
			return "curso/avisosCurso";
		}else{
			logger.info("ALGUNO DE LOS VALORES ES NULO");
			model.addAttribute("fragmento", "contentCursoAvisoError");
			return "curso/avisosCurso";
		}
		
	}
	

	@GetMapping("/form")
	public String formularioCurso(Model model, @RequestParam(name="existe",required=false) String existe){
		List<Plan> planesDeEstudio = planService.obtenerPlanes();
		model.addAttribute("listaPlan",planesDeEstudio);
		model.addAttribute("curso", new CursoModelForm());
		model.addAttribute("existe", existe);
		logger.info("Valor Parametro 'existe' : "+ existe);
		logger.info("Retornando formulario Curso");		
		return "curso/registroIndiv";
	}
	
	@PostMapping("/add")
	public String agregarCurso(Model model,@ModelAttribute("curso") CursoModelForm cursoModelForm){
		logger.info("Agregando datos de: "+cursoModelForm.getCodigo()+" -- "+cursoModelForm.getNombre());
		CursoBase cursoBase = cursoService.coverterToCurso(cursoModelForm);
		boolean existe = cursoService.insertarCurso(cursoBase);	
		if(existe){
			logger.info("AGREGAR CURSO --- Codigo ya existente");
			return "redirect:/curso/form?existe";
		}
		model.addAttribute("fragmento", "contentCursoAvisoExitoReg");
		return "curso/avisosCurso";
	}

	
	@GetMapping("/conjunto")
	public String gestionarConjuntoCurso(Model model){
		List<CursoBase> listaBase = null;
		List<CursoConjunto> listaConjunto = null;
		listaBase = cursoService.findCursoBaseSinConjunto();
		logger.info("CANTIDAD DE CURSOS BASES SIN CONJUNTO: " + listaBase.size());
		listaConjunto = cursoService.findCursosConjuntos();
		model.addAttribute("fragmento","contentCursoEquivalencia");
		model.addAttribute("listaCursoB",listaBase);
		model.addAttribute("listaCursoC",listaConjunto);
		model.addAttribute("cursobc",new CursoBCModelForm());
		logger.info("RETORNANDO VISTA CURSO -- CONJUNTO");
		return "curso/equivalencia";
	}
	

	
	@GetMapping("/search")
	public String BuscarCursos(@ModelAttribute("search") Search search){			
		cursos = cursoService.buscarCursosxParam(search.getValor(),search.getFiltro());
		logger.info("SE ENCONTRO CURSOS: " + cursos.size());
		return "redirect:/curso/all";
	}
	
	@GetMapping("/searchB")
	public String BuscarCursoBaseSinCnjunto(@ModelAttribute("search") Search search){	
		flagB = true;
		cursosb = cursoService.findCursoBaseSinConjuntoxParams(search.getValor(),search.getFiltro());
		logger.info("SE ENCONTRO CURSOS BASE SIN CONJUNTO: " + cursosb.size());
		return "redirect:/curso/allCursoB";
	}
	
	@GetMapping("/searchC/{idB}")
	public String BuscarCursoConjunto(@ModelAttribute("search") Search search, @PathVariable(name="idB",required=true)String idBase){		
		flagC = true;
		cursosc = cursoService.findCursosConjuntosxParams(search.getValor(),search.getFiltro());
		logger.info("SE ENCONTRO CURSOS BASE SIN CONJUNTO: " + cursosb.size());
		return "redirect:/curso/allCursoC/" + idBase;
	}
	
	

}
