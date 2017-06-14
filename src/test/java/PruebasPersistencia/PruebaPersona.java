package PruebasPersistencia;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.edu.sistemas.sismanweb.dao.PersonaDAO;
import pe.edu.sistemas.sismanweb.entidades.Alumno;
import pe.edu.sistemas.sismanweb.entidades.Persona;
import pe.edu.sistemas.sismanweb.services.AlumnoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PruebaPersona {
	
	@Autowired
	public PersonaDAO personaDAO;
	
	@Autowired
	public AlumnoService alumnoService;
		
	@Test
	//@Ignore
	public void seMuestraPersonas() {	
		
		List<Persona> lista = personaDAO.obtenerTodoPersona();
		
		for(Persona p : lista){
			System.out.println(p.getPersonaCodigo()+" : "+p.getPersonaNombre());			
		}	
	}
	
	@Test
	@Ignore
	public void seAgregaPersona() {
		Persona persona = new Persona();
		persona.setPersonaCodigo("");
		persona.setPersonaNombre("");
		persona.setPersonaCodigoSistema("");
		persona.setPersonaPasswordSistema("");
		persona.setPersonaPasswordSistema2("");
		personaDAO.insertarPersona(persona);
		System.out.println("Persona Registrada");		
	}
	
	@Test
	@Ignore
	public void seMuestraPersonarxID() {	
		
		Persona persona = personaDAO.obtenerPersonaxID(5830);
		if(persona!=null) System.out.println("Se encontro a "+persona.getPersonaNombre()+" "+persona.getPersonaAppaterno());
		else System.out.println("No se encuentra a la persona");
		
	}
	
	@Test
	@Ignore
	public void seActualizaPersona(){
		
		Persona persona = personaDAO.obtenerPersonaxID(5831);
		persona.setPersonaAppaterno("");
		persona.setPersonaApmaterno("");
		personaDAO.actualizarPersona(persona);	
		
		System.out.println(persona.getPersonaCodigo()+" : "+persona.getPersonaNombre()+" : "+persona.getPersonaAppaterno()+" : "+persona.getPersonaApmaterno());		
		
	}
	
	@Test
	@Ignore
	public void seEliminaPersona(){
		Persona personaAntes = personaDAO.obtenerPersonaxID(5831);
		personaDAO.eliminarPersona(personaAntes);
		Persona personaDespues = personaDAO.obtenerPersonaxID(5831);
		if(personaDespues!=null) System.out.println("Se encontro a "+personaDespues.getPersonaNombre());
		else System.out.println("No se encuentra a la persona");
		
	}
	
	
	@Test
	public void mostrarAlumnos(){
		for(Alumno al:alumnoService.obtenerAlumnos()){
			System.out.println(al.getIdAlumno()+" -- "+al.getPersona().getPersonaNombre());
		}
	}
	

}
