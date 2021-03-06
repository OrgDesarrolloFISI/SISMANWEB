package PruebasPersistencia;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.AulaDAO;
import pe.edu.sistemas.sismanweb.domain.Aula;
import pe.edu.sistemas.sismanweb.services.AulaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PruebaAula {

	@Autowired
	private AulaService aulaService;
	@Autowired
	private AulaDAO aulaDao;
	
	@Test
	@Ignore
	@Transactional
	public void obtenerAulas() {
		List<Aula> aulas=aulaService.obtenerAulas();
		for(Aula a:aulas){
			System.out.println(a.getNombre());
		}
	}
	
	@Test
	@Transactional
	public void buscarAula() {
		Aula aula= aulaDao.findByNombreAula("NO_ASIGNADO");
		System.out.println(aula.getNombre());
	}

}
