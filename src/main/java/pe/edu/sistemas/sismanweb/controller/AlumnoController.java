package pe.edu.sistemas.sismanweb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.edu.sistemas.sismanweb.domain.Alumno;
import pe.edu.sistemas.sismanweb.domain.Plan;
import pe.edu.sistemas.sismanweb.services.AlumnoService;
import pe.edu.sistemas.sismanweb.services.PersonaService;
import pe.edu.sistemas.sismanweb.services.PlanService;
import pe.edu.sistemas.sismanweb.services.modelform.AlumnoModelForm;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	
	
	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	
	protected final Log logger = LogFactory.getLog(AlumnoController.class);
	
	@Autowired AlumnoService alumnoService;		
	@Autowired PersonaService personaService;	
	@Autowired PlanService planService;
			
	@GetMapping("/all")
	@Transactional
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
	
	@RequestMapping("/masivo")
	public boolean agregarAlumnos(@RequestBody String listAlumno ){
		System.out.println(listAlumno);
		JSONArray ajson = new JSONArray(listAlumno);
		ArrayList<AlumnoModelForm> aAlumno = new ArrayList<>();
		
		for(int i=0; i< ajson.length(); i++)
		{
			JSONObject s = ajson.getJSONObject(i);
			AlumnoModelForm alumno;
			try {
				alumno = JSON_MAPPER.readValue(s.toString(), AlumnoModelForm.class);
				System.out.println(alumno.getApMaterno());
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		
			
			
			
		
		
		
		return true;
		
	}	
	
	
	

}
