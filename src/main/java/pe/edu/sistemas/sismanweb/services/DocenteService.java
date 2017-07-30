package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismanweb.dao.CategoriaDocenteDAO;
import pe.edu.sistemas.sismanweb.dao.ClaseDocenteDAO;
import pe.edu.sistemas.sismanweb.dao.DepartamentoAcademicoDAO;
import pe.edu.sistemas.sismanweb.dao.DocenteDAO;
import pe.edu.sistemas.sismanweb.entidades.Docente;
import pe.edu.sistemas.sismanweb.entidades.Persona;
import pe.edu.sistemas.sismanweb.services.model.FormDocenteModel;

@Service
public class DocenteService {
	
	@Autowired
	DocenteDAO docenteDao;
	
	@Autowired
	ClaseDocenteDAO claseDao;
	
	@Autowired
	CategoriaDocenteDAO categoriaDocenteDao;
	
	@Autowired
	DepartamentoAcademicoDAO departamentoAcademicoDao;
	

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
	
	public Docente converterToDocente(FormDocenteModel formDocenteModel){
		Docente docente = new Docente();
		Persona persona = new Persona();
		persona.setPersonaCodigo(formDocenteModel.getCodigo());
		persona.setPersonaAppaterno(formDocenteModel.getApPaterno());
		persona.setPersonaApmaterno(formDocenteModel.getApMaterno());
		persona.setPersonaNombre(formDocenteModel.getNombre());
		persona.setPersonaFechaNacimiento(formDocenteModel.getFechaNacimiento());
		persona.setPersonaSexo(formDocenteModel.getSexo());
		persona.setPersonaDni(formDocenteModel.getDni());
		persona.setPersonaTelefono(formDocenteModel.getTelefono());
		persona.setPersonaCorreo(formDocenteModel.getCorreo());
		persona.setPersonaDireccion(formDocenteModel.getDireccion());
		persona.setPersonaCodigoSistema(formDocenteModel.getCodigo());
		persona.setPersonaPasswordSistema(formDocenteModel.getCodigo());
		persona.setPersonaPasswordSistema2("d41d8cd98f00b204e9800998ecf8427e");
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
