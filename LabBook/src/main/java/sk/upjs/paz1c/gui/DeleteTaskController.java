package sk.upjs.paz1c.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.Task;
import sk.upjs.paz1c.fxmodels.ProjectFxModel;
import sk.upjs.paz1c.fxmodels.TaskFxModel;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.TaskDAO;

public class DeleteTaskController {
	
	private TaskDAO taskDao = DAOfactory.INSTANCE.getTaskDAO();
	private TaskFxModel taskModel; 
	private Task task; 

	@FXML
    private Button yesButton;

    @FXML
    private Button noButton;
    
    public DeleteTaskController(Task task) {
		this.task = task; 
    	this.taskModel = new TaskFxModel(task);
	}
    
	@FXML
	void initialize() {
		
		
		yesButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				taskDao.deleteTask(taskModel.getTask());
				taskDao.saveTask(taskModel.getTask());
				yesButton.getScene().getWindow().hide();
			}
		});

		noButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				noButton.getScene().getWindow().hide();
			}
		});
	}
}
