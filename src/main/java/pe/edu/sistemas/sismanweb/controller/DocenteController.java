package pe.edu.sistemas.sismanweb.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.sistemas.sismanweb.Util.DeserealizarJSON;
import pe.edu.sistemas.sismanweb.Util.Search;
import pe.edu.sistemas.sismanweb.domain.Docente;
import pe.edu.sistemas.sismanweb.services.CategoriaDocenteService;
import pe.edu.sistemas.sismanweb.services.ClaseDocenteService;
import pe.edu.sistemas.sismanweb.services.DepartamentoAcademicoService;
import pe.edu.sistemas.sismanweb.services.DocenteService;
import pe.edu.sistemas.sismanweb.services.modelform.DocenteModelForm;

@Controller
@RequestMapping("/docente")
public class DocenteController {
	
	protected final Log logger = LogFactory.getLog(DocenteController.class);
	
	@Autowired DocenteService docenteService;		
	@Autowired CategoriaDocenteService categoriaDocenteService;	
	@Autowired ClaseDocenteService claseService;	
	@Autowired DepartamentoAcademicoService departamentoAcademicoService;	
	
	List<DocenteModelForm> docentes = new ArrayList<DocenteModelForm>();
	
	@GetMapping("/all")
	public ModelAndView verDocentes(){
		ModelAndView mav = new ModelAndView("/docente/docente_Ver");
		mav.addObject("search", new Search());
		mav.addObject("listaDocente", docentes);
		docentes=new ArrayList<DocenteModelForm>();
		logger.info("SE DEVUELVEN DOCENTES : " + docentes.size());
	return mav;	
	}
	
	@GetMapping("/form")
	public ModelAndView formularioDocente(@RequestParam(name="existe",required=false) String existe){
		ModelAndView mav = new ModelAndView("/docente/docente_Form");
		mav.addObject("clasesDoc",claseService.obtenerClasesDeDocentes());
		mav.addObject("categoriasDoc",categoriaDocenteService.obtenerCategorias());
		mav.addObject("depAcadDoc",departamentoAcademicoService.obtenerDepAcademicos());
		mav.addObject("docente",new DocenteModelForm());
		mav.addObject("existe", existe);
		logger.info("RETORNANDO FORMULARIO DOCENTE");
		return mav;
	}
	
	@PostMapping("/add")
	public String agregarDocente(@ModelAttribute("docente") DocenteModelForm docentePersonaModel){	
		Docente docente = docenteService.converterToDocente(docentePersonaModel);
		logger.info("AGREGANDO DATOS DE: "+ docentePersonaModel.getCodigo()+" -- "+docentePersonaModel.getApPaterno()+" -- "+docentePersonaModel.getApMaterno());
		boolean existe = docenteService.insertarDocente(docente);
		if(existe){
			logger.info("AGREGAR DOCENTE --- Codigo ya existente");
			return "redirect:/docente/form?existe";
		}
		return "redirect:/docente/all";			
	}	
	
	@PostMapping("/addBulk")
	public String agregarDocentes(@RequestBody String listDocente ){
		ModelAndView mav = new ModelAndView("/docente/docente_Form");
		logger.info("CADENA RECIBIDA: "+listDocente);		
		JSONArray jsonArrayDocente = new JSONArray(listDocente);
		DeserealizarJSON<DocenteModelForm> deserealizador = new DeserealizarJSON<DocenteModelForm>(DocenteModelForm.class);
		List<DocenteModelForm> docentesModel = null;
		List<Docente> resultado = null;
		logger.info("CANTIDAD DE REGISTROS: "+jsonArrayDocente.length());
		
		docentesModel = deserealizador.deserealiza(jsonArrayDocente);
		
		if(jsonArrayDocente.length()!=docentesModel.size()){
			logger.error("ENVIANDO MENSAJE DE ERROR EN REGISTRO: "+(docentesModel.size()+1));
			//mav.addObject("errorRegistro", DocentesModel.size()+1);
		}else{
			resultado = docenteService.saveBulk(docentesModel);
			if(!resultado.isEmpty()){
				logger.warn("EXISTEN "+resultado.size()+" DOCENTES YA REGISTRADOS");
				//mav.addObject("errorExiste",resultado.size());
			}else{
				//mav.addObject("exito");
			}				
		}	
		
		return "redirect:/docente/form";
	}	
	
	
	@GetMapping("/search")
	public String BuscarDocentes(@ModelAttribute("search") Search search){
			
		docentes = docenteService.buscarDocentesxParam(search.getValor(),search.getFiltro());
		logger.info("SE ENCONTRO DocenteS: " + docentes.size());
		return "redirect:/docente/all";
	}

}
