package sk.upjs.paz1c.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sk.upjs.paz1c.entities.Item;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.ItemDAO;

@RestController
public class ItemRestController {

	ItemDAO itemDao = DAOfactory.INSTANCE.getItemDAO();

	@CrossOrigin
	@RequestMapping("/items")
	public List<Item> getAll() {
		return itemDao.getAll();
	}

    @RequestMapping(value="/items", method = RequestMethod.POST)
	public Item addItem(@RequestBody Item participant) throws DaoException{
		try {
			itemDao.addItem(participant);
			return participant;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@RequestMapping(value="/items", method = RequestMethod.PUT)
	public void saveItem(@RequestBody Item participant) throws DaoException {
		try {
			itemDao.saveItem(participant);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@RequestMapping(value="/items/{id}", method = RequestMethod.DELETE)
	public void deleteItem(@PathVariable long id) throws DaoException {
        Item item = itemDao.getByID(id);

        itemDao.deleteItem(item);
	}
}
