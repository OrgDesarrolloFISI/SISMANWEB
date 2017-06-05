package PruebasPersistencia;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.edu.sistemas.sismanweb.dao.DocenteDAO;
import pe.edu.sistemas.sismanweb.entidades.Docente;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PruebaDocente {
	@Autowired
	public DocenteDAO docenteDAO;
		

	@Test
	//@Ignore
	public void seMuestraPersonas() {	
		
		List<Docente> lista = docenteDAO.obtenerTodoDocente();
		
		for(Docente p : lista){
			System.out.print(p.getIddocente()+" : ");
			System.out.println(p.getPersona().getPersonaNombre());
		}		

	}

}
