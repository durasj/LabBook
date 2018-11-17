package sk.upjs.paz1c.gui;

import java.io.IOException;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.business.UserIdentificationManager;
import sk.upjs.paz1c.entities.User;
import sk.upjs.paz1c.fxmodels.SignInFxModel;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.MysqlUserDAO;
import sk.upjs.paz1c.persistent.UserDAO;

public class FrontPageController {

	private User user;
	private SignInFxModel signInFxModel = new SignInFxModel();
	private sk.upjs.paz1c.business.PasswordManager passwordManager = new sk.upjs.paz1c.business.PasswordManager();

	@FXML
	private PasswordField passwordTextField;

	@FXML
	private TextField loginTextField;

	@FXML
	private Button signInButton;

	@FXML
	private Button registerButton;

	@FXML
	private Hyperlink forgottenPasswordHyperlink;

	public FrontPageController() {

	}

	// public FrontPageController(User user) {
	// this.user = user;
	// this.userModel = new UserFxModel(user);
	// }

	@FXML
	void initialize() {

		registerButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				RegistrationController registrationController = new RegistrationController();
				showModalWindow(registrationController, "registration.fxml");
			}
		});

		/*
		 * signInButton.setOnAction(new EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent event) { SelectProjectController
		 * selectProjectController = new SelectProjectController();
		 * showModalWindow(selectProjectController, "selectProject.fxml"); } });
		 */

		forgottenPasswordHyperlink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ForgottenPasswordController forgottenPasswordController = new ForgottenPasswordController();
				showModalWindow(forgottenPasswordController, "forgottenPassword.fxml");
			}
		});

		signInButton.setOnAction(eh -> {
			// loginUser();
			// loginAdmin();

			String login = loginTextField.getText();
			String password = passwordTextField.getText();

			if (UserIdentificationManager.setUser(login, password) == 1) {
				loginUser();
			} else if (UserIdentificationManager.setUser(login, password) == 2) {
				loginAdmin();
			} else {
				showWrongDataWindow();
			}
		});

	}

	private void loginAdmin() {
		SelectProjectController controller = new SelectProjectController();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("deleteUserAdmin.fxml"));
			loader.setController(controller);

			Parent parentPane = loader.load();
			Scene scene = new Scene(parentPane);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Admin Page");
			stage.show();
			signInButton.getScene().getWindow().hide();

		} catch (IOException iOException) {
			iOException.printStackTrace();
		}
	}

	private void loginUser() {
		SelectProjectController controller = new SelectProjectController();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("selectProject.fxml"));
			loader.setController(controller);

			Parent parentPane = loader.load();
			Scene scene = new Scene(parentPane);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Projects");
			stage.show();
			signInButton.getScene().getWindow().hide();

		} catch (IOException iOException) {
			iOException.printStackTrace();
		}
	}

	private void showModalWindow(Object controller, String fxml) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
			fxmlLoader.setController(controller);
			Parent rootPane = fxmlLoader.load();
			Scene scene = new Scene(rootPane);

			Stage dialog = new Stage();
			dialog.setScene(scene);
			dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showWrongDataWindow() {
		AlertBoxFailToSignInController controller = new AlertBoxFailToSignInController();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("alertBoxFailToSignIn.fxml"));
			loader.setController(controller);

			Parent parentPane = loader.load();
			Scene scene = new Scene(parentPane);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setTitle("Fail to sign in");
			stage.show();

		} catch (IOException iOException) {
			iOException.printStackTrace();
		}
	}
}
