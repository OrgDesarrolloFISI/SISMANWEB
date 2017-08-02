package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismanweb.dao.CursoBaseDAO;
import pe.edu.sistemas.sismanweb.dao.CursoConjuntoDAO;
import pe.edu.sistemas.sismanweb.dao.CursoPeriodoDAO;
import pe.edu.sistemas.sismanweb.entidades.CursoBase;
import pe.edu.sistemas.sismanweb.entidades.CursoConjunto;

@Service
public class CursoService {
	
	@Autowired CursoBaseDAO cursoBaseDao;
	@Autowired CursoConjuntoDAO cursoConjuntoDao;
	@Autowired CursoPeriodoDAO cursoPeriodoDao;
	
	
	public List<CursoBase> obtenerCursos(){
		return cursoBaseDao.obtenerTodoCursoBase();
	}
	
	public void insertarCurso(CursoBase cursoBase){
		Integer id = null;
		id = cursoBaseDao.insertarRetornarCursoBase(cursoBase);
		CursoConjunto cursoConjunto = new CursoConjunto();
		cursoConjuntoDao.insertarCursoConjunto(cursoConjunto);
	}	

}
