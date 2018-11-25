package sk.upjs.paz1c.persistent;

import java.util.List;

import sk.upjs.paz1c.entities.Project;

public interface ProjectDAO {

	// pridanie projektu do databazy
	void addProject(Project project);

	// zmena projektu v databaze
	void saveProject(Project project);

	// vrati zoznam projektov v databaze
	List<Project> getAll();

	// vrati projekt s danym menom ak sa nachadza v databaze
	Project getByName(String name);

	// zmaze projekt
	void deleteProject(Project project);

	// vrati projekt podla id
	Project getByID(Long id);

}
