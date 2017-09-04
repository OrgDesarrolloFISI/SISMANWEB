package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.CursoBaseDAO;
import pe.edu.sistemas.sismanweb.dao.CursoConjuntoDAO;
import pe.edu.sistemas.sismanweb.dao.PlanDAO;
import pe.edu.sistemas.sismanweb.domain.CursoBase;
import pe.edu.sistemas.sismanweb.domain.CursoConjunto;
import pe.edu.sistemas.sismanweb.services.modelform.CursoModelForm;

@Service
@Transactional
public class CursoService {
	
	@Autowired private CursoBaseDAO cursoBaseDao;
	@Autowired private CursoConjuntoDAO cursoConjuntoDao;
	@Autowired private PlanDAO planDao;
	
	private static final Log logger = LogFactory.getLog(CursoService.class);
	
	public List<CursoBase> obtenerCursos(){
		return cursoBaseDao.findAll();
	}
	
	public void insertarCurso(CursoBase cursoBase){
		CursoBase cursoBaseExiste;
		
		cursoBaseExiste = cursoBaseDao.findCursoBaseByCodigoByPlan(cursoBase.getCursobCodigo(), cursoBase.getPlan().getIdplan());
		
		if(cursoBaseExiste != null){
			logger.info("YA EXISTE UN CURSO CON EL MISMO CODIGO Y PLAN");
		}else{
			CursoConjunto cursoNuevo = new CursoConjunto();
			CursoConjunto cursoConjuntoExiste;
			
			cursoNuevo.setCursoBase(cursoBase);
			cursoNuevo.setCursocNombre(cursoBase.getCursobNombre());
			//busca un codigo comun
			//si existe lo usa sino crea uno nuevo aumentado en uno al mayor codigo existente
			cursoConjuntoExiste = cursoConjuntoDao.findCursoConjuntoByNombre(cursoBase.getCursobNombre());
			if(cursoConjuntoExiste != null){				
				cursoNuevo.setCursocCodcomun(cursoConjuntoExiste.getCursocCodcomun());
			}else{
				cursoNuevo.setCursocCodcomun(cursoConjuntoDao.findCodigoMaximo()+1);
			}
			Integer idNuevoCurso = cursoConjuntoDao.saveWithReturnId(cursoNuevo);
			logger.info("--NUEVO CURSO AGREGADO-- "+idNuevoCurso);
		}
	}	
	
	public CursoBase coverterToCurso(CursoModelForm cursoModelForm){
		CursoBase cursoBase = new CursoBase();
		cursoBase.setCursobCodigo(cursoModelForm.getCodigo());
		cursoBase.setCursobNombre(cursoModelForm.getNombre());
		cursoBase.setCursobCiclo(cursoModelForm.getCiclo());
		cursoBase.setCursobCreditos(cursoModelForm.getCreditos());
		cursoBase.setPlan(planDao.findById(cursoModelForm.getIdPlan()));
		
		return cursoBase;
	}

}
