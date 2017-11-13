package barcodehelper;

import java.io.IOException;
import java.util.Optional;

import barcodehelper.controller.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
			controller.setProductsCsv("/home/pi/Barcodehelper/build/project/src/barcodehelper/resources/products.csv");
		} catch (IOException e) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(primaryStage);
            alert.initStyle(StageStyle.TRANSPARENT);
            alert.initModality(Modality.WINDOW_MODAL);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                Platform.exit();
            }
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