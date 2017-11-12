package barcodehelper;

import java.io.IOException;

import barcodehelper.controller.MainController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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
		this.primaryStage.setFullScreen(false);
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
			controller.setProductsCsv(MainApp.class.getResource("resources/products.csv").getPath());
			
			scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					if(event.getCode().equals(KeyCode.ENTER)) {
						try {
							controller.addProduct();
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}else if (event.getCode().equals(KeyCode.BACK_SPACE)) {
						controller.clearTable();
					}
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
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