package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismanweb.dao.CursoBaseDAO;
import pe.edu.sistemas.sismanweb.dao.CursoConjuntoDAO;
import pe.edu.sistemas.sismanweb.dao.PlanDAO;
import pe.edu.sistemas.sismanweb.entidades.CursoBase;
import pe.edu.sistemas.sismanweb.services.modelform.CursoModelForm;

@Service
public class CursoService {
	
	@Autowired private CursoBaseDAO cursoBaseDao;
	@Autowired private CursoConjuntoDAO cursoConjuntoDao;
	@Autowired private PlanDAO planDao;
	
	
	public List<CursoBase> obtenerCursos(){
		return cursoBaseDao.obtenerTodoCursoBase();
	}
	
	public void insertarCurso(CursoBase cursoBase){
		//verificar cursobase unico
		//buscar codigo comun del curso
		/*Integer id = null;
		id = cursoBaseDao.agregarCursoBase(cursoBase);
		CursoConjunto cursoConjunto = new CursoConjunto();
		cursoConjuntoDao.insertarCursoConjunto(cursoConjunto);*/
	}	
	
	public CursoBase coverterToCurso(CursoModelForm cursoModelForm){
		CursoBase cursoBase = new CursoBase();
		cursoBase.setCursobCodigo(cursoModelForm.getCodigo());
		cursoBase.setCursobNombre(cursoModelForm.getNombre());
		cursoBase.setCursobCiclo(cursoModelForm.getCiclo());
		cursoBase.setCursobCreditos(cursoModelForm.getCreditos());
		cursoBase.setPlan(planDao.obtenerPlanxID(cursoModelForm.getIdPlan()));
		
		return cursoBase;
	}

}
