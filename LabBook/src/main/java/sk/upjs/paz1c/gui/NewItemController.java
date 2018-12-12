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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.entities.Admin;
import sk.upjs.paz1c.entities.Item;
import sk.upjs.paz1c.entities.Laboratory;
import sk.upjs.paz1c.fxmodels.LaboratoryFxModel;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.ItemDAO;
import sk.upjs.paz1c.persistent.ProjectDAO;

public class NewItemController {

	@FXML
	private Button saveButton;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField quantityTextField;

	private ItemDAO itemDao = DAOfactory.INSTANCE.getItemDAO();

	private LaboratoryFxModel laboratoryModel;

	public NewItemController(Laboratory laboratory) {
		this.laboratoryModel = new LaboratoryFxModel(laboratory);
	}
	//FIXME chyba instancia na laboratorium
	public NewItemController() {
		// TODO Auto-generated constructor stub
	}

	@FXML
	void initialize() {

		saveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String name = nameTextField.getText();
				String quantityString = quantityTextField.getText();
				int quantity = Integer.parseInt(quantityString);
				if (name.isEmpty() || quantityString == null) {
					showWrongDataInputWindow();
				} else if (!isAvailable(name)) {
					showTakenNameWindow();
				} else {
					Item item = new Item();
					item.setName(name);
					item.setQuantity(quantity);
					item.setLaboratory(laboratoryModel.getLaboratory());
					ItemDAO itemDao = DAOfactory.INSTANCE.getItemDAO();
					itemDao.addItem(item);
					saveButton.getScene().getWindow().hide();
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

	private void showTakenNameWindow() {
		TakenNameController controller = new TakenNameController();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("takenName.fxml"));
			loader.setController(controller);

			Parent parentPane = loader.load();
			Scene scene = new Scene(parentPane);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setTitle("Taken Name");
			stage.show();

		} catch (IOException iOException) {
			iOException.printStackTrace();
		}
	}

	private boolean isAvailable(String name) {
		List<Item> items = itemDao.getAll();
		for (Item i : items) {
			if (i.getName().equals(name)) {
				return false;
			}
		}
		return true;
	}
}
