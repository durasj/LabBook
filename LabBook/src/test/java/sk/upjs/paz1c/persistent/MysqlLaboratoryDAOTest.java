package sk.upjs.paz1c.persistent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import sk.upjs.paz1c.entities.Laboratory;

public class MysqlLaboratoryDAOTest {
	
	@Test
	void testGetAll() {
		List<Laboratory> laboratories = DAOfactory.INSTANCE.getLaboratoryDAO().getAll();
		assertNotNull(laboratories);
		assertTrue(laboratories.size() > 0);
	}
	
	@Test
	void addDeleteTest() {
		Laboratory testLaboratory = new Laboratory();
		testLaboratory.setName("tester");
		testLaboratory.setLocation("testovacia");
		LaboratoryDAO laboratoryDAO = DAOfactory.INSTANCE.getLaboratoryDAO();
		boolean notThere = true;
		List<Laboratory> all = laboratoryDAO.getAll();
		for (Laboratory l : all) {
			if (l.getLaboratoryID() == testLaboratory.getLaboratoryID()) {
				notThere = false;
			}
		}
		assertTrue(notThere);

		laboratoryDAO.addLaboratory(testLaboratory);
		all = laboratoryDAO.getAll();
		boolean succesfullyAdded = false;
		for (Laboratory l : all) {
			if (l.getLaboratoryID() == testLaboratory.getLaboratoryID()) {
				succesfullyAdded = true;
			}
		}
		assertTrue(succesfullyAdded);

		laboratoryDAO.deleteLaboratory(testLaboratory);
		all = laboratoryDAO.getAll();
		boolean successfullyDeleted = true;
		for (Laboratory l : all) {
			if (l.getLaboratoryID() == testLaboratory.getLaboratoryID()) {
				successfullyDeleted = false;
			}
		}
		assertTrue(successfullyDeleted);
	}
	
	@Test
	void testSave() {
		Laboratory testLaboratory = new Laboratory();
		testLaboratory.setName("tester");
		testLaboratory.setLocation("testovacia");
		LaboratoryDAO laboratoryDAO = DAOfactory.INSTANCE.getLaboratoryDAO();
		// create
		laboratoryDAO.saveLaboratory(testLaboratory);
		assertNotNull(testLaboratory.getLaboratoryID());
		testLaboratory.setName("tester_new");
		// update
		laboratoryDAO.saveLaboratory(testLaboratory);
		List<Laboratory> all = laboratoryDAO.getAll();
		for (Laboratory l : all) {
			if (l.getLaboratoryID() == testLaboratory.getLaboratoryID()) {
				assertEquals("tester_new", l.getName());
				laboratoryDAO.deleteLaboratory(l);
				return;
			}
		}
		assertTrue(false, "update sa nepodaril");
	}

}
