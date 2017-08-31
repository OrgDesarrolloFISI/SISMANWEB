package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismanweb.dao.DocenteDAO;
import pe.edu.sistemas.sismanweb.domain.Docente;
import pe.edu.sistemas.sismanweb.domain.Persona;
import pe.edu.sistemas.sismanweb.services.modelform.DocenteModelForm;

@Service
public class DocenteService {
	

	@Autowired private DocenteDAO docenteDao;	
	@Autowired private PersonaService personaService;
	@Autowired private ClaseDocenteService claseDocenteService;	
	@Autowired private CategoriaDocenteService categoriaDocenteService;	
	@Autowired private DepartamentoAcademicoService departamentoAcademicoService;
	
	
	public boolean insertarDocente(Docente docente){
		Persona persona = personaService.obtenerPersonaxCodigo(docente.getPersona().getPersonaCodigo());
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
		return docenteDao.findAll();
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
		docente.setClase(claseDocenteService.obtenerClaseDeDocenteXID(formDocenteModel.getIdClase()));
		docente.setCategoriaDocente(categoriaDocenteService.obtenerCategoriaDocXID(formDocenteModel.getIdCategoria()));
		docente.setDepartamentoAcademico(departamentoAcademicoService.obtenerDepAcadXID(formDocenteModel.getIdDepAcad()));	
		
		return docente;		
	}
	
	
}
