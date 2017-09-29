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
		List<CursoBase> resultado = cursoBaseDao.findAll();
		for(CursoBase cb : resultado){
			cb.getPlan().getPlanNombre();
		}
		return resultado;
	}
	
	public boolean insertarCurso(CursoBase cursoBase){
		CursoBase cursoBaseExiste;
		
		cursoBaseExiste = cursoBaseDao.findCursoBaseByCodigoByPlan(cursoBase.getCursobCodigo(), cursoBase.getPlan().getIdplan());
		
		if(cursoBaseExiste != null){
			logger.info("YA EXISTE UN CURSO CON EL MISMO CODIGO Y PLAN");
			return true;
		}else{
			
			cursoBaseDao.save(cursoBase);
			logger.info("--NUEVO CURSO AGREGADO-- ");
			return false;
		}
	}
	
	public boolean insertarCursoConjunto(CursoBase cursoBase, Integer idConjunto){
			CursoConjunto cursoConjunto = null;
			if(idConjunto==0){
				cursoConjunto = new CursoConjunto();
				cursoConjunto.setCursocCodcomun(findCodigoMaximo()+1);
				cursoConjunto.setCursoBase(cursoBase);
				cursoConjunto.setCursocNombre(cursoBase.getCursobNombre());
			return true;
			}else{
				cursoConjunto = findCursoCById(idConjunto);
				cursoConjunto.setCursoBase(cursoBase);
				cursoConjunto.setIdcursoConjunto(null);
			return false;
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
	
	
	public List<CursoBase> findCursoBaseSinConjunto(){
		List<CursoBase> listBase = cursoBaseDao.findCursoBaseSinConjunto();
		for(CursoBase b: listBase){
			b.getPlan().getPlanNombre();
		}
		return listBase;
	}
	
	public List<CursoConjunto> findCursosConjuntos(){
		List<CursoConjunto> listConjunto = cursoConjuntoDao.findCursosConjuntos();
		for(CursoConjunto b: listConjunto){
			b.getCursoBase().getPlan().getPlanNombre();
		}
		return listConjunto;
	}
	
	public CursoBase findCursoBById(Integer idcurso){
		CursoBase cursob = cursoBaseDao.findById(idcurso);
		return cursob;
	}
	
	public CursoConjunto findCursoCById(Integer idcurso){
		CursoConjunto cursoc = cursoConjuntoDao.findById(idcurso);
		return cursoc;
	}
	
	public Integer findCodigoMaximo(){
		return cursoConjuntoDao.findCodigoMaximo();
	}
}
