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
	
	public boolean saveBulk(List<CursoModelForm> listacursoMasivoModel) {
		List<CursoModelForm> cursosConProblemas = new ArrayList<CursoModelForm>();
		boolean resultado=false;
		for(int i = 0; i < listacursoMasivoModel.size(); i++) {
			CursoModelForm cmf = listacursoMasivoModel.get(i);
			CursoModelForm cpmf = new CursoModelForm(cmf.getIdPlan(),cmf.getPlanNombre(),cmf.getCodigo(),cmf.getNombre(),cmf.getCiclo(),cmf.getCreditos());
			System.out.println(cpmf.toString());
			CursoBase cursoBase = cursoService.coverterToCurso(cpmf);
			System.out.println("Un paso antes de guardar"+cursoBase.toString());
			resultado=cursoService.insertarCurso(cursoBase);	
			
			
		}
		return resultado;
	}

}
