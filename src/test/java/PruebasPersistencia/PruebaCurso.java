package PruebasPersistencia;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.CursoBaseDAO;
import pe.edu.sistemas.sismanweb.dao.CursoConjuntoDAO;
import pe.edu.sistemas.sismanweb.dao.CursoPeriodoDAO;
import pe.edu.sistemas.sismanweb.dao.PlanDAO;
import pe.edu.sistemas.sismanweb.domain.CursoBase;
import pe.edu.sistemas.sismanweb.domain.CursoConjunto;
import pe.edu.sistemas.sismanweb.domain.CursoPeriodo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PruebaCurso {
	
	@Autowired private CursoBaseDAO cursoBaseDao;
	
	@Autowired private CursoConjuntoDAO cursoConjuntoDao;
	
	@Autowired private PlanDAO planDao;
		
	@Autowired private CursoPeriodoDAO cursoPeriodoDao;
	

	@Test
	@Ignore
	@Transactional
	public void seMuestraCursoBase(){
		List<CursoBase> cursoBase = cursoBaseDao.findCursoBaseByNombre("curso");
		
		System.out.println(cursoBase.size());
		
		for(CursoBase cb: cursoBase){
			System.out.print(cb.getIdcursoGeneral()+" -- "+cb.getCursobCodigo()+" -- "+cb.getCursobNombre()+" -- "+cb.getCursobCreditos()+" -- "+cb.getPlan().getPlanNombre());
			System.out.println(" -- " +cb.getCursoConjuntos().size());			
		}	
	}
	
	
	@Test
	@Ignore
	@Transactional()
	public void agregaCurso(){

		CursoBase cb = new CursoBase();
		cb.setCursobCodigo("405");
		cb.setCursobNombre("miCurso");
		cb.setCursobCiclo(1);
		cb.setCursobCreditos(4);
		cb.setPlan(planDao.findById(1));
		
		CursoConjunto cc = new CursoConjunto();
		cc.setCursoBase(cb);
		cc.setCursocNombre(cb.getCursobNombre());
		cc.setCursocCodcomun(1);
		
		int id = cursoConjuntoDao.saveWithReturnId(cc);
		//int id = cursoBaseDao.agregarCursoBase(cb);
		System.out.println(id);		
		
	}
	
	@Test
	@Ignore
	@Transactional
	public void muestraUnCursoBase(){
		CursoBase cb = cursoBaseDao.findCursoBaseByCodigoByPlan("201001",1);
		if(cb != null){
			System.out.print(cb.getIdcursoGeneral()+" -- "+cb.getCursobCodigo()+" -- "+cb.getCursobNombre()+" -- "+cb.getCursobCreditos()+" -- "+cb.getPlan().getPlanNombre());
			System.out.println(" -- " +cb.getCursoConjuntos().size());
		}else System.out.println("Es nulo");
		
	}
	
	@Test
	@Ignore
	@Transactional
	public void muestraUnCursoConjunto(){
		CursoConjunto cc = cursoConjuntoDao.findCursoConjuntoByNombre("base de daTOs");
		if(cc != null){
			System.out.print(cc.getIdcursoConjunto()+" -- "+cc.getCursocNombre()+" -- ["+cc.getCursoBase().getIdcursoGeneral()+" :: "+cc.getCursoBase().getCursobNombre()+" :: "
			+cc.getCursoBase().getCursobCodigo()+" :: "+cc.getCursoBase().getPlan().getIdplan()+"] -- "+cc.getCursocCodcomun());
		}else System.out.println("Es nulo");		
		
	}
	
	@Test
	@Ignore
	@Transactional
	public void mostrarMaximoCodigo(){
		Integer codmax = cursoConjuntoDao.findCodigoMaximo();
		System.out.println("CODIGO MAXIMO --> "+codmax);
	}
	
	@Test
	@Ignore
	@Transactional
	public void muestraCursosSinConjunto(){
		List<CursoBase> cc = cursoBaseDao.findCursoBaseSinConjunto();
		if(cc != null){
			for(CursoBase c: cc){
				System.out.println(c.getIdcursoGeneral() + ", nombre del curso: " + c.getCursobNombre() + " -- ");
			}
			
			
		}else System.out.println("Es nulo");		
		
	}
	
	@Test
	@Ignore
	@Transactional
	public void muestraCursosConjunto(){
		List<CursoConjunto> cc = cursoConjuntoDao.findCursosConjuntos();
		if(cc != null){
			for(CursoConjunto c: cc){
				System.out.println(c.getIdcursoConjunto() + ", nombre del curso: " + c.getCursocNombre() + " -- ");
			}
			
			
		}else System.out.println("Es nulo");		
		
	}
	
	@Test
	@Ignore
	@Transactional
	public void muestraCursosPlan(){
		List<CursoBase> cb = cursoBaseDao.obtenerCursosxCod("2009", "plan.planNombre" );
		if(cb != null){
			for(CursoBase c: cb){
				System.out.println(c.getIdcursoGeneral() + ", nombre del curso: " + c.getCursobNombre() + " -- ");
			}
			
			
		}else System.out.println("Es nulo");		
		
	}
	
	@Test
	@Ignore
	@Transactional
	public void muestraCodigoCursoConjunto(){
		String codigoCurso="201210";
		String nombrePlan="2009-Sistemas";
		CursoConjunto cc=cursoConjuntoDao.findCursoConjuntoByCodigoCursoByNombrePlan(codigoCurso, nombrePlan);
		if(cc==null)
			System.out.println("No se encontró el cursoconjunto");
		else
			System.out.println("El codigo del cursoconjunto es "+cc.getCursocCodcomun());
		
	}

	@Test
	@Ignore
	@Transactional
	public void muestraCodigoCursoPeriodo(){
		String codigoCurso="201210";
		String nombreplan="2009-Sistemas";
		String periodoNombre="20172";
		CursoPeriodo cp=cursoPeriodoDao.findCursoPeriodoByAll(codigoCurso, nombreplan, periodoNombre);
		if(cp==null)
			System.out.println("No se encontró el cursoconjunto");
		else
			System.out.println("El codigo del cursoconjunto es "+cp.getIdcursoPeriodo());
	}
}
