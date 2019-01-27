package sk.upjs.paz1c.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sk.upjs.paz1c.entities.Laboratory;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.LaboratoryDAO;

@RestController
public class LaboratoryRestController {

	LaboratoryDAO laboratoryDao = DAOfactory.INSTANCE.getLaboratoryDAO();

	@CrossOrigin
	@RequestMapping("/laboratories")
	public List<Laboratory> getAll() {
		return laboratoryDao.getAll();
	}

    @RequestMapping(value="/laboratory", method = RequestMethod.POST)
	public Laboratory addLaboratory(@RequestBody Laboratory laboratory) throws DaoException{
		try {
			laboratoryDao.addLaboratory(laboratory);
			return laboratory;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@RequestMapping(value="/laboratory", method = RequestMethod.PUT)
	public Laboratory saveLaboratory(@RequestBody Laboratory laboratory) throws DaoException {
		try {
			laboratoryDao.saveLaboratory(laboratory);
			return laboratory;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@RequestMapping(value="/laboratory/{id}", method = RequestMethod.DELETE)
	public Laboratory deleteLaboratory(@PathVariable long id) throws DaoException {
        Laboratory lab = laboratoryDao.getLaboratoryByID(id);

		laboratoryDao.deleteLaboratory(lab);

		return lab;
	}
}
