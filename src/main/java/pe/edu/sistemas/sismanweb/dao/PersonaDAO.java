package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.entidades.Persona;

public interface PersonaDAO {

	public void insertarPersona(Persona persona);

	public void actualizarPersona(Persona persona);

	public void eliminarPersona(Persona persona);

	public List<Persona> obtenerTodoPersona();

	public Persona obtenerPersonaxID(Integer idPersona);
	
	public Persona obtenerPersonaxCodigo(String codigo);

}
