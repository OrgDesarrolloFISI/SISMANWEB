package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismanweb.dao.CursoBaseDAO;
import pe.edu.sistemas.sismanweb.dao.CursoConjuntoDAO;
import pe.edu.sistemas.sismanweb.dao.PlanDAO;
import pe.edu.sistemas.sismanweb.entidades.CursoBase;
import pe.edu.sistemas.sismanweb.entidades.CursoConjunto;
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
		CursoBase cursoBaseExiste;
		
		cursoBaseExiste = cursoBaseDao.obtenerCursoBasexCodigoxPlan(cursoBase.getCursobCodigo(), cursoBase.getPlan().getIdplan());
		
		if(cursoBaseExiste != null){
			System.out.println("Ya existe un curso con el mismo codigo y plan");
		}else{
			CursoConjunto cursoNuevo = new CursoConjunto();
			CursoConjunto cursoConjuntoExiste;
			
			cursoNuevo.setCursoBase(cursoBase);
			cursoNuevo.setCursocNombre(cursoBase.getCursobNombre());
			//busca un codigo comun
			//si existe lo usa sino crea uno nuevo aumentado en uno al mayor codigo existente
			cursoConjuntoExiste = cursoConjuntoDao.obtenerCursoConjuntoxNombre(cursoBase.getCursobNombre());
			if(cursoConjuntoExiste != null){				
				cursoNuevo.setCursocCodcomun(cursoConjuntoExiste.getCursocCodcomun());
			}else{
				cursoNuevo.setCursocCodcomun(cursoConjuntoDao.obtenerCodigoMaximo()+1);
			}
			Integer idNuevoCurso = cursoConjuntoDao.agregarCursoConjunto(cursoNuevo);
			System.out.println("--NUEVO CURSO AGREGADO-- "+idNuevoCurso);
		}
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
