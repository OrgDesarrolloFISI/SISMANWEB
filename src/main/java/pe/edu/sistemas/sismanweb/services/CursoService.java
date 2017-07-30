package pe.edu.sistemas.sismanweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismanweb.dao.CursoBaseDAO;
import pe.edu.sistemas.sismanweb.dao.CursoConjuntoDAO;
import pe.edu.sistemas.sismanweb.dao.CursoPeriodoDAO;

@Service
public class CursoService {
	
	@Autowired CursoBaseDAO cursoBaseDao;
	@Autowired CursoConjuntoDAO cursoConjuntoDao;
	@Autowired CursoPeriodoDAO cursoPeriodoDao;
	
	
	
	
	
	
	

}
