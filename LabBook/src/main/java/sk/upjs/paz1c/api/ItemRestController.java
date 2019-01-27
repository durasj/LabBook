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

    @RequestMapping(value="/item", method = RequestMethod.POST)
	public Item addItem(@RequestBody Item item) throws DaoException{
		try {
			itemDao.addItem(item);
			return item;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@RequestMapping(value="/item", method = RequestMethod.PUT)
	public Item saveItem(@RequestBody Item item) throws DaoException {
		try {
			itemDao.saveItem(item);
			return item;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@RequestMapping(value="/item/{id}", method = RequestMethod.DELETE)
	public Item deleteItem(@PathVariable long id) throws DaoException {
        Item item = itemDao.getByID(id);

		itemDao.deleteItem(item);
		
		return item;
	}
}
