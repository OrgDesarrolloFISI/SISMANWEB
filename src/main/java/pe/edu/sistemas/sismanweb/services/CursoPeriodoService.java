package pe.edu.sistemas.sismanweb.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.edu.sistemas.sismanweb.dao.CursoBaseDAO;
import pe.edu.sistemas.sismanweb.dao.CursoConjuntoDAO;
import pe.edu.sistemas.sismanweb.dao.CursoPeriodoDAO;
import pe.edu.sistemas.sismanweb.domain.CursoConjunto;
import pe.edu.sistemas.sismanweb.domain.CursoPeriodo;
import pe.edu.sistemas.sismanweb.services.modelform.CursoPeriodoModelForm;

@Service
@Transactional
public class CursoPeriodoService {

	protected final Log logger = LogFactory.getLog(AlumnoService.class);
	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	
	@Autowired private CursoBaseDAO cursoBaseDAO;
	@Autowired private CursoConjuntoDAO cursoConjuntoDAO;
	@Autowired private CursoPeriodoDAO cursoPeriodoDAO;
	
	public List<CursoPeriodo> saveBulk(List<CursoPeriodoModelForm> listaCursoPeriodoModel){
		List<CursoPeriodo> cursosExistentes = new ArrayList<CursoPeriodo>();
		CursoPeriodo cursoPeriodo = null;
		Boolean existe;
		for(int i=0; i< listaCursoPeriodoModel.size(); i++){
			cursoPeriodo = converterToCursoPeriodo(listaCursoPeriodoModel.get(i));
			existe = insertarCursoPeriodo(cursoPeriodo);
			if(existe){
				cursosExistentes.add(cursoPeriodo);
			}			
		}
		return cursosExistentes;
	}
	
	public CursoPeriodo converterToCursoPeriodo(CursoPeriodoModelForm formCursoPeriodoModel){
		CursoPeriodo cursoPeriodo = new CursoPeriodo();
		
		cursoPeriodo.setCursoPeriodoNombre(formCursoPeriodoModel.getPeriodo());
		CursoConjunto cc=cursoConjuntoDAO
				.findCursoConjuntoByCodigoCursoByNombrePlan(formCursoPeriodoModel.getCodCurso(), formCursoPeriodoModel.getPeriodo());
		cursoPeriodo.setCursoConjunto(cc);
		cursoPeriodo.setCursoPeriodoNombre(formCursoPeriodoModel.getCursoPeriodoNombre());
		
		return cursoPeriodo;
	}
	public boolean insertarCursoPeriodo(CursoPeriodo cursoPeriodo){
		if(cursoPeriodo==null)
			return true;
		else
			cursoPeriodoDAO.save(cursoPeriodo);
		return false;
	}
	
}
