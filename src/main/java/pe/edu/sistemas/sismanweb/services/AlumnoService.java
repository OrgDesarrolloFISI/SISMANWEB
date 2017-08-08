package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismanweb.dao.AlumnoDAO;
import pe.edu.sistemas.sismanweb.dao.PlanDAO;
import pe.edu.sistemas.sismanweb.dao.TipoAlumnoDAO;
import pe.edu.sistemas.sismanweb.entidades.Alumno;
import pe.edu.sistemas.sismanweb.entidades.Persona;
import pe.edu.sistemas.sismanweb.services.modelform.AlumnoModelForm;

@Service
public class AlumnoService {
	
	@Autowired private AlumnoDAO alumnoDao;	
	@Autowired private PlanDAO planDao;	
	@Autowired private TipoAlumnoDAO tipoAlumnoDao;
	
	
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
	
	public Alumno converterToAlumno(AlumnoModelForm formAlumnoModel){
		Alumno alumno = new Alumno();
		Persona persona = new Persona();
		persona.setPersonaCodigo(formAlumnoModel.getCodigo());
		persona.setPersonaAppaterno(formAlumnoModel.getApPaterno());
		persona.setPersonaApmaterno(formAlumnoModel.getApMaterno());
		persona.setPersonaNombre(formAlumnoModel.getNombre());
		persona.setPersonaSexo(formAlumnoModel.getSexo());
		persona.setPersonaDni(formAlumnoModel.getDni());
		persona.setPersonaTelefono(formAlumnoModel.getTelefono());
		persona.setPersonaCorreo(formAlumnoModel.getCorreo());
		persona.setPersonaDireccion(formAlumnoModel.getDireccion());
		persona.setPersonaCodigoSistema(formAlumnoModel.getCodigo());
		persona.setPersonaPasswordSistema(formAlumnoModel.getCodigo());
		persona.setPersonaPasswordSistema2(" ");
		alumno.setPersona(persona);
		alumno.setAlumnoActivo(1);
		alumno.setTipoAlumno(tipoAlumnoDao.obtenerTipoAlumnoxID(1));
		alumno.setPlan(planDao.obtenerPlanxID(formAlumnoModel.getIdPlan()));	
		
		return alumno;
	}
	
	
}
