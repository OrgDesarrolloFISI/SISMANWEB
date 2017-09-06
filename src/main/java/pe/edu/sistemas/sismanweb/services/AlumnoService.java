package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.AlumnoDAO;
import pe.edu.sistemas.sismanweb.dao.PersonaDAO;
import pe.edu.sistemas.sismanweb.dao.PlanDAO;
import pe.edu.sistemas.sismanweb.dao.TipoAlumnoDAO;
import pe.edu.sistemas.sismanweb.domain.Alumno;
import pe.edu.sistemas.sismanweb.domain.Persona;
import pe.edu.sistemas.sismanweb.services.modelform.AlumnoModelForm;

@Service
@Transactional
public class AlumnoService {
	
	@Autowired private AlumnoDAO alumnoDao;	
	@Autowired private PlanDAO planDao;	
	@Autowired private TipoAlumnoDAO tipoAlumnoDao;
	@Autowired private PersonaDAO personaDao;
	
	
	public boolean insertarAlumno(Alumno alumno){
		Persona persona = personaDao.findPersonaByCodigo(alumno.getPersona().getPersonaCodigo());
		if(persona!=null){
			return true;
		}else{
			alumnoDao.save(alumno);
			return false;
		}		
	}
	
	
	public void actualizarAlumno(Alumno alumno){
		alumnoDao.save(alumno);
	}
	
	
	public void eliminarAlumno(Alumno alumno){
		alumnoDao.update(alumno);
	}

	
	public List<Alumno> obtenerAlumnos(){
		List<Alumno> resultado = alumnoDao.findAll();
		for(Alumno alu: resultado){
			alu.getPersona().getPersonaNombre();
		}		
		return resultado;
	}
	
	
	public Alumno obtenerAlumnoxID(Integer idAlumno){
		return alumnoDao.findById(idAlumno);
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
		alumno.setTipoAlumno(tipoAlumnoDao.findById(1));
		alumno.setPlan(planDao.findById(formAlumnoModel.getIdPlan()));	
		
		return alumno;
	}
	
	
}
