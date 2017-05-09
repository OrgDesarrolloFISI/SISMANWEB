package PruebasPersistencia;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.edu.sistemas.sismanweb.dao.PersonaDAO;
import pe.edu.sistemas.sismanweb.entidades.Persona;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PruebasDAO {
	
	@Autowired
	public PersonaDAO personaDAO;
	
	Persona persona;
	

	@Test
	public void mostrarPersonar() {	
		
		List<Persona> lista = personaDAO.obtenerTodoPersona();
		
		for(Persona p : lista){
			System.out.println(p.getPersonaCodigo()+" : "+p.getPersonaNombre());			
		}		

	}
	
	@Test
	public void insertarPersona() {
		persona = new Persona();
		persona.setPersonaCodigo("14200045");
		persona.setPersonaNombre("andherson");
		personaDAO.insertarPersona(persona);
		System.out.println("Persona Registrada");
	}

}
