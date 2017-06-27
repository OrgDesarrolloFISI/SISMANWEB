package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismanweb.dao.AlumnoDAO;
import pe.edu.sistemas.sismanweb.dao.PlanDAO;
import pe.edu.sistemas.sismanweb.dao.TipoAlumnoDAO;
import pe.edu.sistemas.sismanweb.entidades.Alumno;
import pe.edu.sistemas.sismanweb.entidades.Persona;
import pe.edu.sistemas.sismanweb.entidades.Plan;
import pe.edu.sistemas.sismanweb.model.AlumnoPersonaModel;

@Service
public class AlumnoService {
	
	@Autowired
	AlumnoDAO alumnoDao;
	
	@Autowired
	PlanDAO planDao;
	
	@Autowired
	TipoAlumnoDAO tipoAlumnoDao;
	
	public void insertarAlumno(Alumno alumno){
		alumnoDao.insertarAlumno(alumno);
	}
	
	public void actualizarAlumno(Alumno alumno){
		alumnoDao.actualizarAlumno(alumno);
	}
	
	public void eliminarAlumno(Alumno alumno){
		alumnoDao.actualizarAlumno(alumno);
	}

	public List<Alumno> obtenerAlumnos(){
		return alumnoDao.obtenerTodoAlumno();
	}
	
	public Alumno obtenerAlumnoxID(Integer idAlumno){
		return alumnoDao.obtenerAlumnoxID(idAlumno);
	}
	
	public Alumno convertirAlumnoPersonaModelAalumno(AlumnoPersonaModel alumnoPersonaModel){
		Alumno alumno = new Alumno();
		Persona persona = new Persona();
		persona.setPersonaCodigo(alumnoPersonaModel.getCodigo());
		persona.setPersonaAppaterno(alumnoPersonaModel.getApPaterno());
		persona.setPersonaApmaterno(alumnoPersonaModel.getApMaterno());
		persona.setPersonaNombre(alumnoPersonaModel.getNombre());
		persona.setPersonaFechaNacimiento(alumnoPersonaModel.getFechaNacimiento());
		persona.setPersonaSexo(alumnoPersonaModel.getSexo());
		persona.setPersonaDni(alumnoPersonaModel.getDni());
		persona.setPersonaTelefono(alumnoPersonaModel.getTelefono());
		persona.setPersonaCorreo(alumnoPersonaModel.getCorreo());
		persona.setPersonaDireccion(alumnoPersonaModel.getDireccion());
		alumno.setPersona(persona);
		alumno.setTipoAlumno(tipoAlumnoDao.obtenerTipoAlumnoxID(1));
		alumno.setPlan(planDao.obtenerPlanxID(alumnoPersonaModel.getIdPlan()));		
		return alumno;
	}
	
	
}
