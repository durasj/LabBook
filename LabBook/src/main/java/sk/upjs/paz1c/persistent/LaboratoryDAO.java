package sk.upjs.paz1c.persistent;

import java.util.List;

import sk.upjs.paz1c.entities.Laboratory;

public interface LaboratoryDAO {

	// pridanie laboratory do databazy
	void addLaboratory(Laboratory laboratory);

	// vrati vsetky laboratory z databazy
	List<Laboratory> getAll();

	// zmena laboratory v databaze
	void saveLaboratory(Laboratory laboratory);

	// vymazanie laboratory z databazy
	void deleteLaboratory(Laboratory laboratory);
	
	Laboratory getLaboratoryByID(Long id);

}
