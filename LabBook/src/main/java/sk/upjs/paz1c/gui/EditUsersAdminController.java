package sk.upjs.paz1c.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import sk.upjs.paz1c.entities.User;

public class EditUsersAdminController {

	@FXML
    private TableView<User> usersTableView;

    @FXML
    private Button deleteButton;
    
    @FXML 
    void initialize() {
    	deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
    }

}
