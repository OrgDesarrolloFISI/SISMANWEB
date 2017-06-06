package PruebasPersistencia;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.edu.sistemas.sismanweb.entidades.Docente;
import pe.edu.sistemas.sismanweb.services.DocenteService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PruebaDocente {
	@Autowired
	public DocenteService docenteService;
		

	@Test
	//@Ignore
	public void seMuestraPersonas() {	
		
		List<Docente> lista = docenteService.obtenerDocentes();
		
		for(Docente p : lista){
			System.out.print(p.getIddocente()+" : ");
			System.out.println(p.getPersona().getPersonaNombre());
		}	
		
		Assert.assertEquals(10,lista.size());

	}

}
