package sk.upjs.paz1c.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	// spustenie aplikacie
	@Override
	public void start(Stage primaryStage) throws Exception {
		FrontPageController mainController = new FrontPageController();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("frontPage.fxml"));
		fxmlLoader.setController(mainController);
		// môže hodiť IOException
		Parent rootPane = fxmlLoader.load();

		Scene scene = new Scene(rootPane);
		primaryStage.setTitle("LabBook login");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
