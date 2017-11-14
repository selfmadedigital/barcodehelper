package barcodehelper;

import java.io.IOException;

import barcodehelper.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane mainPage;

	/**
	 * Constructor
	 */
	public MainApp() {

	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setFullScreen(true);
		this.primaryStage.setTitle("Veselica Klubovňa Barcode Helper");

		openInitPage();
	}

	/**
	 * Initializes the root layout.
	 */
	public void openInitPage() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MainPage.fxml"));
			mainPage = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(mainPage);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			MainController controller = loader.getController();
			controller.setProductsCsv("/home/pi/Barcodehelper/products.csv");
			
			scene.onKeyPressedProperty().bind(controller.getTextCode().onKeyPressedProperty());
			scene.onKeyReleasedProperty().bind(controller.getTextCode().onKeyReleasedProperty());
			
			scene.addEventFilter(KeyEvent.KEY_PRESSED,
	                event -> System.out.println("Pressed: " + event.getCode()));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("I have a great message for you!");

			alert.showAndWait();
		}
	}

	/**
	 * Returns the main stage.
	 *
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}