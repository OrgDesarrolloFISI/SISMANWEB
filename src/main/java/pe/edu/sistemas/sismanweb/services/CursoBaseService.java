package pe.edu.sistemas.sismanweb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.sismanweb.domain.CursoBase;
import pe.edu.sistemas.sismanweb.services.modelform.CursoMasivoModel;
import pe.edu.sistemas.sismanweb.services.modelform.CursoModelForm;

@Service
@Transactional
public class CursoBaseService {
	
	@Autowired CursoService cursoService;	
	
	public List<CursoModelForm> saveBulk(List<CursoModelForm> listacursoMasivoModel) {
		List<CursoModelForm> cursosConProblemas = new ArrayList<CursoModelForm>();
		boolean seAgrego = false;
		boolean resultado=false;
		for(int i = 0; i < listacursoMasivoModel.size(); i++) {
			CursoModelForm cmf = listacursoMasivoModel.get(i);
			CursoModelForm cpmf = new CursoModelForm(cmf.getIdPlan(),cmf.getPlanNombre(),cmf.getCodigo(),cmf.getNombre(),cmf.getCiclo(),cmf.getCreditos());
			CursoBase cursoBase = cursoService.coverterToCurso(cpmf);
			seAgrego = cursoService.insertarCurso(cursoBase);
			if (!seAgrego) {
				cursosConProblemas.add(cpmf);
				System.out.println("No se agregó el cursoBase en " + (i + 1));
			} else {
				System.out.println("Se agregó 1 cursoPeriodo en " + (i + 1));
			}
			
		}
		return cursosConProblemas;
	}

}
