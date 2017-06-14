package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismanweb.dao.DepartamentoAcademicoDAO;
import pe.edu.sistemas.sismanweb.entidades.DepartamentoAcademico;

@Service
public class DepartamentoAcademicoService {
	
	@Autowired
	DepartamentoAcademicoDAO departamentoAcademicoService;
	
	public List<DepartamentoAcademico> obtenerDepAcademicos(){
		return departamentoAcademicoService.obtenerTodoDepartamentoAcademico();
	}
	
	public DepartamentoAcademico obtenerDepAcadXID(Integer idDepartamentoAcademico){
		return departamentoAcademicoService.obtenerDepartamentoAcademicoxID(idDepartamentoAcademico);
	}	

}
