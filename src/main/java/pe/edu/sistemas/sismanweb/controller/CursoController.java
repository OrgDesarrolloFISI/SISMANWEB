package pe.edu.sistemas.sismanweb.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.sistemas.sismanweb.domain.CursoBase;
import pe.edu.sistemas.sismanweb.domain.CursoConjunto;
//import pe.edu.sistemas.sismanweb.domain.CursoPeriodo;
import pe.edu.sistemas.sismanweb.domain.Plan;
import pe.edu.sistemas.sismanweb.services.AulaService;
import pe.edu.sistemas.sismanweb.services.CursoBaseService;
import pe.edu.sistemas.sismanweb.services.CursoPeriodoService;
import pe.edu.sistemas.sismanweb.services.CursoService;
import pe.edu.sistemas.sismanweb.services.PlanService;
import pe.edu.sistemas.sismanweb.services.modelform.CursoBCModelForm;
import pe.edu.sistemas.sismanweb.services.modelform.CursoMasivoModel;
import pe.edu.sistemas.sismanweb.services.modelform.CursoModelForm;
//import pe.edu.sistemas.sismanweb.services.modelform.CursoPeriodoModelForm;
import pe.edu.sistemas.sismanweb.util.DeserealizarJSON;
import pe.edu.sistemas.sismanweb.util.Search;

@Controller
@RequestMapping("/curso")
public class CursoController {
	
	private static final Log logger = LogFactory.getLog(CursoController.class);
	
	@Autowired CursoService cursoService;	
	@Autowired PlanService  planService;
	@Autowired CursoPeriodoService cursoPeriodoService;
	@Autowired CursoBaseService cursoBaseService;
	@Autowired AulaService aulaService;
	
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
		CursoConjunto cursoC = null;
		CursoBase cursoB = cursoService.findCursoBById(Integer.parseInt(idBase));
		
		if(Integer.parseInt(idConjunto) != 0){
		 cursoC = cursoService.findCursoCById(Integer.parseInt(idConjunto));
		 
			if(cursoC == null){
				model.addAttribute("fragmento", "contentCursoAvisoCursoCNoExiste");
				return "curso/avisosCurso";
			}
		}	
		else{ //NUEVO CURSO
		 cursoC = new CursoConjunto(null, "NUEVO CURSO", 0);
		 cursoC.setIdcursoConjunto(0);
		 Plan plan=new Plan();
		 plan.setPlanNombre(cursoB.getPlan().getPlanNombre());
		 CursoBase cursob2=new CursoBase();
		 cursob2.setPlan(plan);
		 cursoC.setCursoBase(cursob2);
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
				model.addAttribute("fragmento", "contentCursoAvisoBaseRepetida");
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
		CursoBase cursoBase = cursoService.coverterToCursoIndividual(cursoModelForm);
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
	 /*-----------*/
	/*Cursos base*/
   /*-----------*/
	@GetMapping("/bulkCursoBase")
	public String bulkCursosBase(Model model){
		model.addAttribute("listaPlanes", planService.obtenerPlanes());
		model.addAttribute("listaAulas", aulaService.obtenerAulas());
		/*logger.info("RETORNANDO VISTA CARGA MASIVA -- CURSOS");*/
		
		
		return "curso/registroGrupalBase";		
	}
	
	@PostMapping("/addBulkBase")
	public String agregarCursosBase(Model model, @RequestBody String listCursos ){
		List<CursoModelForm> resultado;
		//logger.info("CADENA RECIBIDA: "+listCursos);
		JSONArray jsonArrayCursoBase = new JSONArray(listCursos);
		DeserealizarJSON<CursoModelForm> deserealizador = new DeserealizarJSON<CursoModelForm>(CursoModelForm.class);
		List<CursoModelForm> cursoMasivoModel = null;
		//logger.info("CANTIDAD DE REGISTROS: "+jsonArrayCursoBase.length());
		cursoMasivoModel = deserealizador.deserealiza(jsonArrayCursoBase);
		//logger.info("Paso por aqui 1");
		//System.out.println("JSONARRAYCURSOBASE ES "+jsonArrayCursoBase.length());
		//System.out.println("cursoMasivoModel.size() ES "+cursoMasivoModel.size());
			//logger.info("Paso por aqui 3");

		try {		
				resultado = cursoBaseService.saveBulk(cursoMasivoModel);
				model.addAttribute("cantidadCursosGuardados",(cursoMasivoModel.size() -resultado.size()));
			if(!resultado.isEmpty()){
				logger.warn("NO SE PUDIERON REGISTRAR  "+resultado.size()+" Horarios");	//Error 3
				System.out.println("NO SE PUDIERON REGISTRAR  "+resultado.size()+" Horarios");
				model.addAttribute("listaCursosNoAgregados", resultado);
				return "curso/avisosGrupal :: contentCursoAvisoExistenProbBase";
			}else{
				logger.info("SE REGISTRO EXITOSAMENTE CURSOS BASE");		//Éxito
				System.out.println("SE REGISTRO EXITOSAMENTE DOCENTES");
				//return "curso/avisosGrupal :: contentCursoAvisoExitoGrup";
			}
				
			}catch(Exception e){	
					logger.warn("ERROR EN LOS ID's");
					return "curso/avisosGrupal :: contentCursoAvisoIdsGrup";
				}		
			return "curso/avisosGrupal :: contentCursoAvisoExitoGrup";
		}	
	
	
	/*--------------*/
   /*Cursos Periodo*/
  /*--------------*/
	@GetMapping("/bulk")
	public String bulkCursos(Model model){
		model.addAttribute("listaPlanes", planService.obtenerPlanes());
		model.addAttribute("listaAulas", aulaService.obtenerAulas());
		/*logger.info("RETORNANDO VISTA CARGA MASIVA -- CURSOS");*/
		return "curso/registroGrupal";		
	}
	
	@PostMapping("/addBulk")
	public String agregarCursos(Model model, @RequestBody String listCursos ){
		logger.info("CADENA RECIBIDA: "+listCursos);
		JSONArray jsonArrayCursoPeriodo = new JSONArray(listCursos);
		DeserealizarJSON<CursoMasivoModel> deserealizador = new DeserealizarJSON<CursoMasivoModel>(CursoMasivoModel.class);
		List<CursoMasivoModel> cursoMasivoModel = null;
		List<CursoMasivoModel> resultado = null;
		logger.info("CANTIDAD DE REGISTROS: "+jsonArrayCursoPeriodo.length());
		
		cursoMasivoModel = deserealizador.deserealiza(jsonArrayCursoPeriodo);
		if(jsonArrayCursoPeriodo.length()!=cursoMasivoModel.size()){	//Cada error que exista envía un fragmento para activarlo en la página
			logger.error("ENVIANDO MENSAJE DE ERROR EN REGISTRO: "+(cursoMasivoModel.size()+1));//Error 1
			System.out.println("ENVIANDO MENSAJE DE ERROR EN REGISTRO: "+(cursoMasivoModel.size()+1));
			return "curso/avisosGrupal :: contentCursoAvisoErrorGrup";
		}else{
				resultado = cursoPeriodoService.saveBulk(cursoMasivoModel);
				model.addAttribute("cantidadCursosGuardados",(jsonArrayCursoPeriodo.length()-resultado.size()));
			if(!resultado.isEmpty()){
				logger.warn("NO SE PUDIERON REGISTRAR  "+resultado.size()+" Horarios");	//Error 3
				System.out.println("NO SE PUDIERON REGISTRAR  "+resultado.size()+" Horarios");
				model.addAttribute("listaCursosNoAgregados", resultado);
				return "curso/avisosGrupal :: contentCursoAvisoExistenProb";
			}else{
				logger.info("SE REGISTRO EXITOSAMENTE DOCENTES");		//Éxito
				System.out.println("SE REGISTRO EXITOSAMENTE DOCENTES");
				return "curso/avisosGrupal :: contentCursoAvisoExitoGrup";
			}
		}
		
	}
	
	
}
