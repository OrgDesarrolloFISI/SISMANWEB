package pe.edu.sistemas.sismanweb.services;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismanweb.dao.CategoriaDocenteDAO;
import pe.edu.sistemas.sismanweb.dao.ClaseDocenteDAO;
import pe.edu.sistemas.sismanweb.dao.DepartamentoAcademicoDAO;
import pe.edu.sistemas.sismanweb.dao.DocenteDAO;
import pe.edu.sistemas.sismanweb.entidades.Docente;
import pe.edu.sistemas.sismanweb.entidades.Persona;
<<<<<<< HEAD
import pe.edu.sistemas.sismanweb.services.modelform.DocenteModelForm;
=======
import pe.edu.sistemas.sismanweb.services.model.FormDocenteModel;
>>>>>>> ramajose

@Service
public class DocenteService {
	

	@Autowired private DocenteDAO docenteDao;	
	@Autowired private ClaseDocenteDAO claseDao;	
	@Autowired private CategoriaDocenteDAO categoriaDocenteDao;	
	@Autowired private DepartamentoAcademicoDAO departamentoAcademicoDao;
	
	
	public void insertarDocente(Docente docente){
		docenteDao.insertarDocente(docente);
	}
	
	public void actualizarDocente(Docente docente){
		docenteDao.actualizarDocente(docente);
	}
	
	public void eliminarDocente(Docente docente){
		docenteDao.eliminarDocente(docente);
	}
	
	public List<Docente> obtenerDocentes(){
		return docenteDao.obtenerTodoDocente();
	}
	
	public Docente obtenerDocenteXID(Integer idDocente){
		return docenteDao.obtenerDocentexID(idDocente);
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
		docente.setClase(claseDao.obtenerClasexID(formDocenteModel.getIdClase()));
		docente.setCategoriaDocente(categoriaDocenteDao.obtenerCategoriaDocentexID(formDocenteModel.getIdCategoria()));
		docente.setDepartamentoAcademico(departamentoAcademicoDao.obtenerDepartamentoAcademicoxID(formDocenteModel.getIdDepAcad()));	
		
		return docente;		
	}
	
	
}
