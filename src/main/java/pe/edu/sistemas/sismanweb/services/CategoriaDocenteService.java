package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.sismanweb.dao.CategoriaDocenteDAO;
import pe.edu.sistemas.sismanweb.entidades.CategoriaDocente;

@Service
public class CategoriaDocenteService {
	
	@Autowired
	CategoriaDocenteDAO categoriaDocenteDao;
	
	public List<CategoriaDocente> obtenerCategorias(){
		return categoriaDocenteDao.obtenerTodoCategoriaDocente();
	}
	
	public CategoriaDocente obtenerCategoriaDocXID(Short idCategoriaDocente){
		return categoriaDocenteDao.obtenerCategoriaDocentexID(idCategoriaDocente);
		
	}

}
