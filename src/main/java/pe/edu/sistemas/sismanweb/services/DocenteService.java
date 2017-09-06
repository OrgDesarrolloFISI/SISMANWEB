package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.CategoriaDocenteDAO;
import pe.edu.sistemas.sismanweb.dao.ClaseDocenteDAO;
import pe.edu.sistemas.sismanweb.dao.DepartamentoAcademicoDAO;
import pe.edu.sistemas.sismanweb.dao.DocenteDAO;
import pe.edu.sistemas.sismanweb.dao.PersonaDAO;
import pe.edu.sistemas.sismanweb.domain.Alumno;
import pe.edu.sistemas.sismanweb.domain.Docente;
import pe.edu.sistemas.sismanweb.domain.Persona;
import pe.edu.sistemas.sismanweb.services.modelform.DocenteModelForm;

@Service
@Transactional
public class DocenteService {
	

	@Autowired private DocenteDAO docenteDao;	
	@Autowired private PersonaDAO personaDao;
	@Autowired private ClaseDocenteDAO claseDocenteDao;	
	@Autowired private CategoriaDocenteDAO categoriaDocenteDao;	
	@Autowired private DepartamentoAcademicoDAO departamentoAcademicoDao;
	
	
	public boolean insertarDocente(Docente docente){
		Persona persona = personaDao.findPersonaByCodigo(docente.getPersona().getPersonaCodigo());
		if(persona!=null){
			return true;
		}else{
			docenteDao.save(docente);
			return false;
		}
	}
	
	public void actualizarDocente(Docente docente){
		docenteDao.update(docente);
	}
	
	public void eliminarDocente(Docente docente){
		docenteDao.delete(docente);
	}
	
	public List<Docente> obtenerDocentes(){
		List<Docente> resultado = docenteDao.findAll();
		for(Docente doc: resultado){
			doc.getPersona().getPersonaNombre();
			if(doc.getDepartamentoAcademico()!=null){
				doc.getDepartamentoAcademico().getDepartamentoAcademicoNombre();
			}
			if(doc.getCategoriaDocente()!=null){
				doc.getCategoriaDocente().getCategoriaDocenteNombre();
			}
			if(doc.getClase()!=null){
				doc.getClase().getClaseNombre();
			}			
		}
		return resultado;
	}
	
	public Docente obtenerDocenteXID(Integer idDocente){
		return docenteDao.findById(idDocente);
	}
	
	public Docente converterToDocente(DocenteModelForm formDocenteModel){
		Docente docente = new Docente();
		Persona persona = new Persona();
		persona.setPersonaCodigo(formDocenteModel.getCodigo());
		persona.setPersonaAppaterno(formDocenteModel.getApPaterno());
		persona.setPersonaApmaterno(formDocenteModel.getApMaterno());
		persona.setPersonaNombre(formDocenteModel.getNombre());
		persona.setPersonaSexo(formDocenteModel.getSexo());
		persona.setPersonaDni(formDocenteModel.getDni());
		persona.setPersonaTelefono(formDocenteModel.getTelefono());
		persona.setPersonaCorreo(formDocenteModel.getCorreo());
		persona.setPersonaDireccion(formDocenteModel.getDireccion());
		persona.setPersonaCodigoSistema(formDocenteModel.getCodigo());
		persona.setPersonaPasswordSistema(formDocenteModel.getCodigo());
		persona.setPersonaPasswordSistema2(" ");
		docente.setPersona(persona);
		docente.setDocenteClave("");
		docente.setDocenteGrupoOcupacional("Profesional");
		docente.setDocenteRegular(0);		
		docente.setClase(claseDocenteDao.findById(formDocenteModel.getIdClase()));
		docente.setCategoriaDocente(categoriaDocenteDao.findById(formDocenteModel.getIdCategoria()));
		docente.setDepartamentoAcademico(departamentoAcademicoDao.findById(formDocenteModel.getIdDepAcad()));	
		
		return docente;		
	}
	
	
}
