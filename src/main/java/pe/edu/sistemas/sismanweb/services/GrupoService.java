package pe.edu.sistemas.sismanweb.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.edu.sistemas.sismanweb.dao.CursoConjuntoDAO;
import pe.edu.sistemas.sismanweb.dao.CursoPeriodoDAO;
import pe.edu.sistemas.sismanweb.dao.GrupoDAO;
import pe.edu.sistemas.sismanweb.domain.CursoPeriodo;
import pe.edu.sistemas.sismanweb.domain.Grupo;

@Service
@Transactional
public class GrupoService {

	protected final Log logger = LogFactory.getLog(AlumnoService.class);
	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	
	@Autowired GrupoDAO grupoDao;
	@Autowired CursoConjuntoDAO cursoConjuntoDao;
	@Autowired CursoPeriodoDAO cursoPeriodoDao;
	
	public Grupo obtenerGrupoPorTodo(String codigoCurso, String nombreplan, String periodoNombre, int grupoNumero) {
		Grupo grupo=null;
		
		CursoPeriodo cp = cursoPeriodoDao.findCursoPeriodoByAll(codigoCurso, nombreplan, periodoNombre);
		grupo=grupoDao.findByidcursoPeriodoBygrupoNumero(cp.getIdcursoPeriodo(), grupoNumero);
		
		return grupo;
	}
}
