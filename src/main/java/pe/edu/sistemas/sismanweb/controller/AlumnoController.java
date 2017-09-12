package pe.edu.sistemas.sismanweb.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.edu.sistemas.sismanweb.Util.DeserealizarJSON;
import pe.edu.sistemas.sismanweb.domain.Alumno;
import pe.edu.sistemas.sismanweb.domain.Plan;
import pe.edu.sistemas.sismanweb.services.AlumnoService;
import pe.edu.sistemas.sismanweb.services.PersonaService;
import pe.edu.sistemas.sismanweb.services.PlanService;
import pe.edu.sistemas.sismanweb.services.modelform.AlumnoModelForm;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	
	
	protected final Log logger = LogFactory.getLog(AlumnoController.class);
	
	@Autowired AlumnoService alumnoService;		
	@Autowired PersonaService personaService;	
	@Autowired PlanService planService;
			
	@GetMapping("/all")
	public ModelAndView verAlumnos(){		
		ModelAndView mav = new ModelAndView("/alumno/alumno_Ver");
		List<Alumno> alumnos = alumnoService.obtenerAlumnos();
		logger.info("Busqueda -- Retornando modelo y vista "+ " -- Datos: "+ alumnos.size());
		mav.addObject("listaAlumno", alumnos);
		return mav;		
	}
	
	@GetMapping("/form")
	public ModelAndView formularioAlumno(@RequestParam(name="existe",required=false) String existe){
		ModelAndView mav = new ModelAndView("/alumno/alumno_Form");
		List<Plan> planesDeEstudio = planService.obtenerPlanes();
		mav.addObject("listaPlan", planesDeEstudio);
		mav.addObject("alumno", new AlumnoModelForm());
		mav.addObject("existe", existe);
		System.out.println(existe);
		logger.info("RETORNANDO FORMULARIO ALUMNO");
		return mav;
	}
	
	@PostMapping("/add")
	public String agregarAlumno(@ModelAttribute("alumno") AlumnoModelForm alumnoPersonaModel){
		Alumno alumno = alumnoService.converterToAlumno(alumnoPersonaModel);
		logger.info("AGREGANDO DATOS DE : "+ alumnoPersonaModel.getCodigo()+" -- "+alumnoPersonaModel.getIdPlan());
		boolean existe = alumnoService.insertarAlumno(alumno);
		if(existe){
			logger.info("AGREGAR ALUMNO --- Codigo ya existente");
			return "redirect:/alumno/form?existe";
		}
		return "redirect:/alumno/all";
	}	
	

	@PostMapping("/addBulk")
	public String agregarAlumnos(@RequestBody String listAlumno ){
		ModelAndView mav = new ModelAndView("/alumno/alumno_Form");
		logger.info("CADENA RECIBIDA: "+listAlumno);		
		JSONArray jsonArrayAlumno = new JSONArray(listAlumno);
		DeserealizarJSON<AlumnoModelForm> deserealizador = new DeserealizarJSON<AlumnoModelForm>(AlumnoModelForm.class);
		List<AlumnoModelForm> alumnosModel = null;
		List<Alumno> resultado = null;
		logger.info("CANTIDAD DE REGISTROS: "+jsonArrayAlumno.length());
		
		alumnosModel = deserealizador.deserealiza(jsonArrayAlumno);
		
		if(jsonArrayAlumno.length()!=alumnosModel.size()){
			logger.error("ENVIANDO MENSAJE DE ERROR EN REGISTRO: "+(alumnosModel.size()+1));
			//mav.addObject("errorRegistro", alumnosModel.size()+1);
		}else{
			resultado = alumnoService.saveBulk(alumnosModel);
			if(!resultado.isEmpty()){
				logger.warn("EXISTEN "+resultado.size()+" ALUMNOS YA REGISTRADOS");
				//mav.addObject("errorExiste",resultado.size());
			}else{
				//mav.addObject("exito");
			}				
		}	
		
		return "redirect:/alumno/form";
	}	
	
	
	@GetMapping("/search")
	public String BuscarAlumnos(@RequestParam(name="slt",required=false) int tipoFiltro,
			@RequestParam(name="value",required=false) String valorFiltro){
			
		//List<AlumnoModelForm> alumnos = AlumnoService.buscarAlumnosxCod(valorFiltro);
		
		return "redirect:/alumno/all";
	}
	/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            sltFiltro = Integer.parseInt(request.getParameter("slt"));
            inFiltro = request.getParameter("value");
            
            try {
                List<Alumno> listalumno = Servicios.listaAlumnoFiltro(sltFiltro, inFiltro) ;
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(listalumno);
                response.setContentType("application/json");
                response.getWriter().write(json);
                
                
            } catch (SQLException ex) {
                Logger.getLogger(alumnoSvt.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }*/

}
