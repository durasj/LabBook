package sk.upjs.paz1c.persistent;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import sk.upjs.paz1c.entities.Note;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.Task;
import sk.upjs.paz1c.entities.User;

public class MysqlProjectDAOTest {

	@Test
	void testGetAll() {
		List<Project> projects = DAOfactory.INSTANCE.getProjectDAO().getAll();
		assertNotNull(projects);
		assertTrue(projects.size() > 0);
	}

	@Test
	void addDeleteTest() {
		Project project = new Project();
		project.setName("testovaci_projekt");
		project.setActive(true);
		project.setDateFrom(LocalDate.now());
		project.setEachItemAvailable(false);
		ProjectDAO projectDAO = DAOfactory.INSTANCE.getProjectDAO();
		boolean notThere = true;
		List<Project> all = projectDAO.getAll();
		for (Project p : all) {
			if (p.getProjectID() == project.getProjectID()) {
				notThere = false;
			}
		}
		assertTrue(notThere);
		projectDAO.addProject(project);

		User testUser = new User();
		testUser.setName("tester");
		testUser.setPassword("1234");
		testUser.setEmail("tester.testovaci@test.com");
		UserDAO userDAO = DAOfactory.INSTANCE.getUserDAO();
		userDAO.addUser(testUser);
		
		Note note = new Note();
		note.setText("testovaci text");
		note.setTimestamp(LocalDate.now());
		note.setAuthor(testUser);
		note.setProject(project);
		NoteDAO noteDAO = DAOfactory.INSTANCE.getNoteDAO();
		noteDAO.addNote(note);
		
		Note note2 = new Note();
		note2.setText("testovaci text 2");
		note2.setTimestamp(LocalDate.now());
		note2.setAuthor(testUser);
		note2.setProject(project);
		noteDAO.addNote(note2);
		
		Task task = new Task();
		task.setProject(project);
		task.setName("task taskovity");
		task.setActive(true);
		task.setEachItemAvailable(true);
		TaskDAO taskDao = DAOfactory.INSTANCE.getTaskDAO();
		taskDao.addTask(task);
		
		all = projectDAO.getAll();
		boolean succesfullyAdded = false;
		for (Project p : all) {
			if (p.getProjectID() == project.getProjectID()) {
				succesfullyAdded = true;
			}
		}
		assertTrue(succesfullyAdded);
		projectDAO.deleteProject(project);
		userDAO.deleteUser(testUser);
		all = projectDAO.getAll();
		boolean successfullyDeleted = true;
		for (Project p : all) {
			if (p.getProjectID() == project.getProjectID()) {
				successfullyDeleted = false;
			}
		}
		assertTrue(successfullyDeleted);
	}

	@Test
	void testSave() {
		Project project = new Project();
		project.setName("testovaci_projekt");
		project.setActive(true);
		project.setDateFrom(LocalDate.now());
		project.setEachItemAvailable(false);
		ProjectDAO projectDAO = DAOfactory.INSTANCE.getProjectDAO();
		// create
		projectDAO.saveProject(project);
		assertNotNull(project.getProjectID());
		project.setName("testovaci_projekt_new");
		// update
		projectDAO.saveProject(project);
		List<Project> all = projectDAO.getAll();
		for (Project p : all) {
			if (p.getProjectID() == project.getProjectID()) {
				assertEquals("testovaci_projekt_new", p.getName());
				projectDAO.deleteProject(p);
				return;
			}
		}
		assertTrue(false, "update sa nepodaril");
	}
	
	@Test
	void testGetByID() {
		Project project = new Project();
		project.setName("testovaci_projekt");
		project.setActive(true);
		project.setDateFrom(LocalDate.now());
		project.setEachItemAvailable(false);
		ProjectDAO projectDAO = DAOfactory.INSTANCE.getProjectDAO();
		projectDAO.addProject(project);
		long id = project.getProjectID();
		assertTrue(id == projectDAO.getByID(id).getProjectID());
		projectDAO.deleteProject(project);
	}

}
