package pe.edu.sistemas.sismanweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/example")
public class PersonaController {
	
	@GetMapping("/prueba")
	public String exampleString(){
		return "example";
	}
	

}
