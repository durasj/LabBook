package sk.upjs.paz1c.gui;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.entities.User;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.UserDAO;

public class RegistrationController {

	@FXML
	private PasswordField confirmPasswordPasswordField;

	@FXML
	private PasswordField passwordPasswordField;

	@FXML
	private TextField nameTextField;

	@FXML
	private Button finishButton;

	@FXML
	private TextField emailAdressTextField;

	@FXML
	void initialize() {
		finishButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String name = nameTextField.getText();
				String email = emailAdressTextField.getText();
				String password1 = passwordPasswordField.getText();
				String password2 = confirmPasswordPasswordField.getText();
				//FIXME metodu stade skopirovat 
				if (name.isEmpty() || email.isEmpty() || password1.isEmpty()) {
					showWrongDataInputWindow();
				} else {
					if (password1.equals(password2)) {
						User user = new User(name, password1, email);
						UserDAO userDao = DAOfactory.INSTANCE.getUserDAO();
						userDao.addUser(user);
						finishButton.getScene().getWindow().hide();
					} else {
						showWrongDataInputWindow();
					}

				}
			}
		});
	}

	private void showWrongDataInputWindow() {
		WrongDataInputController controller = new WrongDataInputController();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("WrongDataInput.fxml"));
			loader.setController(controller);

			Parent parentPane = loader.load();
			Scene scene = new Scene(parentPane);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setTitle("Wrong data");
			stage.show();

		} catch (IOException iOException) {
			iOException.printStackTrace();
		}
	}

}
