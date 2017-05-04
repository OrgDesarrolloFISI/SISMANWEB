package PruebasPersistencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pe.edu.sistemas.sismanweb.dao.PersonaDAO;
import pe.edu.sistemas.sismanweb.daoimpl.PersonaDAOImpl;
import pe.edu.sistemas.sismanweb.entidades.Persona;

public class PruebasDAO {
	
	
	public static PersonaDAO personaDAO;
	

	public static void main(String[] args) {		
		
		
		for(Persona p : personaDAO.obtenerTodoPersona()){
			System.out.println(p.getPersonaCodigo()+" : "+p.getPersonaNombre());			
		}

			

	}

}
