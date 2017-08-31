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
	
	
	@GetMapping("/all")
	public ModelAndView verDocentes(){
		ModelAndView mav = new ModelAndView("/docente/docente_Ver");
		List<Docente> docentes = docenteService.obtenerDocentes();
		logger.info("Busqueda -- Retornando modelo y vista "+ " -- Datos: "+ docentes.size());
		mav.addObject("listaDocentes",docentes);
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
	

}
