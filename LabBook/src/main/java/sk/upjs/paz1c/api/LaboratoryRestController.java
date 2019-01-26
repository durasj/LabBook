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

    @RequestMapping(value="/laboratories", method = RequestMethod.POST)
	public Laboratory addLaboratory(@RequestBody Laboratory participant) throws DaoException{
		try {
			laboratoryDao.addLaboratory(participant);
			return participant;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@RequestMapping(value="/laboratories", method = RequestMethod.PUT)
	public void saveLaboratory(@RequestBody Laboratory participant) throws DaoException {
		try {
			laboratoryDao.saveLaboratory(participant);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@RequestMapping(value="/laboratories/{id}", method = RequestMethod.DELETE)
	public void deleteLaboratory(@PathVariable long id) throws DaoException {
        Laboratory lab = laboratoryDao.getLaboratoryByID(id);

        laboratoryDao.deleteLaboratory(lab);
	}
}
