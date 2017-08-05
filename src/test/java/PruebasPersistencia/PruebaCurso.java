package PruebasPersistencia;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.edu.sistemas.sismanweb.dao.CursoBaseDAO;
import pe.edu.sistemas.sismanweb.dao.CursoConjuntoDAO;
import pe.edu.sistemas.sismanweb.dao.PlanDAO;
import pe.edu.sistemas.sismanweb.entidades.CursoBase;
import pe.edu.sistemas.sismanweb.entidades.CursoConjunto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PruebaCurso {
	
	@Autowired private CursoBaseDAO cursoBaseDao;
	
	@Autowired private CursoConjuntoDAO cursoConjuntoDao;
	
	@Autowired private PlanDAO planDao;
		

	@Test
	@Ignore
	public void seMuestraCursoBase(){
		List<CursoBase> cursoBase = cursoBaseDao.obtenerTodoCursoBasexNombre("curso");
		
		System.out.println(cursoBase.size());
		
		for(CursoBase cb: cursoBase){
			System.out.print(cb.getIdcursoGeneral()+" -- "+cb.getCursobCodigo()+" -- "+cb.getCursobNombre()+" -- "+cb.getCursobCreditos()+" -- "+cb.getPlan().getPlanNombre());
			System.out.println(" -- " +cb.getCursoConjuntos().size());			
		}	
	}
	
	
	@Test
	@Ignore
	public void agregaCurso(){

		CursoBase cb = new CursoBase();
		cb.setCursobCodigo("405");
		cb.setCursobNombre("miCurso");
		cb.setCursobCiclo(1);
		cb.setCursobCreditos(4);
		cb.setPlan(planDao.obtenerPlanxID(1));
		
		CursoConjunto cc = new CursoConjunto();
		cc.setCursoBase(cb);
		cc.setCursocNombre(cb.getCursobNombre());
		cc.setCursocCodcomun(1);
		
		int id = cursoConjuntoDao.agregarCursoConjunto(cc);
		//int id = cursoBaseDao.agregarCursoBase(cb);
		System.out.println(id);		
		
	}
	
	@Test
	//@Ignore
	public void muestraUnCursoBase(){
		CursoBase cb = cursoBaseDao.obtenerCursoBasexCodigoxPlan("201001",1);
		if(cb != null){
			System.out.print(cb.getIdcursoGeneral()+" -- "+cb.getCursobCodigo()+" -- "+cb.getCursobNombre()+" -- "+cb.getCursobCreditos()+" -- "+cb.getPlan().getPlanNombre());
			System.out.println(" -- " +cb.getCursoConjuntos().size());
		}else System.out.println("Es nulo");
		
	}
	
	@Test
	@Ignore
	public void muestraUnCursoConjunto(){
		CursoConjunto cc = cursoConjuntoDao.obtenerCursoConjuntoxNombre("base de daTOs");
		if(cc != null){
			System.out.print(cc.getIdcursoConjunto()+" -- "+cc.getCursocNombre()+" -- ["+cc.getCursoBase().getIdcursoGeneral()+" :: "+cc.getCursoBase().getCursobNombre()+" :: "
			+cc.getCursoBase().getCursobCodigo()+" :: "+cc.getCursoBase().getPlan().getIdplan()+"] -- "+cc.getCursocCodcomun());
		}else System.out.println("Es nulo");		
		
	}
	
	@Test
	@Ignore
	public void mostrarMaximoCodigo(){
		Integer codmax = cursoConjuntoDao.obtenerCodigoMaximo();
		System.out.println("CODIGO MAXIMO --> "+codmax);
	}
	
	

}
