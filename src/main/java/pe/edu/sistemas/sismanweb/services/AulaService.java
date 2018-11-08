package pe.edu.sistemas.sismanweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.dao.AulaDAO;
import pe.edu.sistemas.sismanweb.domain.Aula;

@Service
@Transactional
public class AulaService {
	@Autowired
	private AulaDAO aulaDao;

	public List<Aula> obtenerAulas(){
		List<Aula> aulas= aulaDao.findAll();
		return aulas;
	}
}
