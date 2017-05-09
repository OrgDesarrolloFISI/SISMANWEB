package pe.edu.sistemas.sismanweb.dao;

import java.util.List;

import pe.edu.sistemas.sismanweb.entidades.CategoriaDocente;

public interface CategoriaDocenteDAO {
	
	/*public void insertarCategoriaDocente (CategoriaDocente categoriaDocente);
	
	public void actualizarCategoriaDocente (CategoriaDocente categoriaDocente);
	
	public void eliminarCategoriaDocente (CategoriaDocente categoriaDocente);*/
	
	public List<CategoriaDocente> obtenerTodoCategoriaDocente();
	
	public CategoriaDocente obtenerCategoriaDocentexID(Integer idCategoriaDocente);	

}
