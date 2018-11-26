package sk.upjs.paz1c.persistent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import sk.upjs.paz1c.entities.Note;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.User;

public class MysqlNoteDAOTest {
	
	@Test
	void testGetAll() {
		List<Note> notes = DAOfactory.INSTANCE.getNoteDAO().getAll();
		assertNotNull(notes);
		assertTrue(notes.size() > 0);
	}
	
	@Test
	void addDeleteTest() {
		User testUser = new User();
		testUser.setName("tester");
		testUser.setPassword("1234");
		testUser.setEmail("tester.testovaci@test.com");
		UserDAO userDAO = DAOfactory.INSTANCE.getUserDAO();
		userDAO.addUser(testUser);
		
		Project project = new Project();
		project.setName("testovaci_projekt");
		project.setActive(true);
		project.setDateFrom(LocalDate.now());
		project.setEachItemAvailable(false);
		ProjectDAO projectDAO = DAOfactory.INSTANCE.getProjectDAO();
		projectDAO.addProject(project);
		
		Note note = new Note();
		note.setText("testovaci text");
		note.setTimestamp(LocalDate.now());
		note.setAuthor(testUser);
		note.setProject(project);
		NoteDAO noteDAO = DAOfactory.INSTANCE.getNoteDAO();
		noteDAO.addNote(note);
		boolean succesfullyAdded = false;
		List<Note> all = noteDAO.getAll();
		for (Note n : all) {
			if (n.getNoteID() == note.getNoteID()) {
				succesfullyAdded = true;
			}
		}
		assertTrue(succesfullyAdded);

		noteDAO.deleteNote(note);
		all = noteDAO.getAll();
		boolean successfullyDeleted = true;
		for (Note n : all) {
			if (n.getNoteID() == note.getNoteID()) {
				successfullyDeleted = false;
			}
		}
		userDAO.deleteUser(testUser);
		projectDAO.deleteProject(project);
		assertTrue(successfullyDeleted);
	}
	
	@Test
	void testSave() {
		User testUser = new User();
		testUser.setName("tester");
		testUser.setPassword("1234");
		testUser.setEmail("tester.testovaci@test.com");
		UserDAO userDAO = DAOfactory.INSTANCE.getUserDAO();
		userDAO.addUser(testUser);
		
		Project project = new Project();
		project.setName("testovaci_projekt");
		project.setActive(true);
		project.setDateFrom(LocalDate.now());
		project.setEachItemAvailable(false);
		ProjectDAO projectDAO = DAOfactory.INSTANCE.getProjectDAO();
		projectDAO.addProject(project);
		
		Note note = new Note();
		note.setText("testovaci text");
		note.setTimestamp(LocalDate.now());
		note.setAuthor(testUser);
		note.setProject(project);
		NoteDAO noteDAO = DAOfactory.INSTANCE.getNoteDAO();
		// create
		noteDAO.saveNote(note);
		assertNotNull(note.getNoteID());
		note.setText("testovaci_task_new");
		// update
		noteDAO.saveNote(note);
		List<Note> all = noteDAO.getAll();
		for (Note n : all) {
			if (n.getNoteID() == note.getNoteID()) {
				assertEquals("testovaci_task_new", n.getText());
				noteDAO.deleteNote(n);
				return;
			}
		}
		userDAO.deleteUser(testUser);
		projectDAO.deleteProject(project);
		assertTrue(false, "update sa nepodaril");
	}

}
