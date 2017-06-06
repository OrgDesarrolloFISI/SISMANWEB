package PruebasPersistencia;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.SystemPropertyUtils;

import org.junit.Assert;
import pe.edu.sistemas.sismanweb.entidades.CategoriaDocente;
import pe.edu.sistemas.sismanweb.services.CategoriaDocenteService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class PruebasNoTransaccion {
	
	@Autowired
	CategoriaDocenteService categoriaDocenteService;
	
	/**
	 * id: Clave unica de la categoria
	 * name: Nombre de la categoria
	 * Docentes: Añadir cd.getDocentes.size().
	 * 			 Para mostrar los docentes se tiene que evitar cerrar la sesion. 
	 */
	
	@Test
	public void categoriasDocente(){
		List<CategoriaDocente> resultado = categoriaDocenteService.obtenerCategorias();
		int i = 0;
		for(CategoriaDocente cd: resultado){
			System.out.println(cd.getIdecategoriaDocente()+" -- "+cd.getCategoriaDocenteNombre());
			i++;
		}
		
		Assert.assertEquals(i, resultado.size());
		
	}
	
	

}
