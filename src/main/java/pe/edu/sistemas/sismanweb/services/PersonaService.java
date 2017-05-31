package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismanweb.dao.PersonaDAO;
import pe.edu.sistemas.sismanweb.entidades.Persona;

@Service
public class PersonaService {
	
	@Autowired
	PersonaDAO personaDao;
	
	public void insertarPersona(Persona persona){
		personaDao.insertarPersona(persona);
	}
	
	public void actualizarPersona(Persona persona){
		personaDao.actualizarPersona(persona);
	}
	
	public void eliminarPersona(Persona persona){
		personaDao.eliminarPersona(persona);
	}
	
	public List<Persona> obtenerPersonas(){
		return personaDao.obtenerTodoPersona();
	}
	
	public Persona obtenerPersonaXID(Integer idPersona){
		return personaDao.obtenerPersonaxID(idPersona);
	}
	
	

}
