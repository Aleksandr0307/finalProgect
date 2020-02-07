package view;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kontroller.GetData;
import kontroller.SendData;

public class MenuBarOrganization {

	final static FileChooser fileChooser = new FileChooser();

	/**
	 * @param primaryStage
	 * @return drop-down menu from the file positions (new directory, open the
	 *         file, save as, save, exit), edit (create a new contract), help
	 *         (information)
	 *         when exiting, it saves the working version
	 */
	public static MenuBar menuBar(Stage primaryStage) {

		configureFileChooser(fileChooser);

		MenuBar menuBar = new MenuBar();

		Menu fileMenu = new Menu("File");
		Menu editMenu = new Menu("Edit");
		Menu helpMenu = new Menu("Help");

		MenuItem helpItem = new MenuItem("Information");
		helpItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				MessageInformation
						.messangh("Leasing contract accounting program " + "with the ability to track contract "
								+ "expiration dates, storing data on closed contracts.\nBobruisk2020");
			}
		});

		// creation of a new contract directory
		MenuItem newItem = new MenuItem("New");
		newItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				File file = new File("d://NewFile.fr");
				FirstWindow.setFile(file);
				FirstWindow.setDataOrganization(GetData.findings(file));
				FirstWindow.newStart(primaryStage);
			}
		});

		// menu open file from a given location
		MenuItem openFileItem = new MenuItem("Open File");
		openFileItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				fileChooser.setTitle("Open file");
				File file = fileChooser.showOpenDialog(primaryStage);
				if (file != null) {
					FirstWindow.setDataOrganization(GetData.findings(file));
					FirstWindow.newStart(primaryStage);

				}
			}
		});

		MenuItem saveFileItem = new MenuItem("Save");
		saveFileItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
		saveFileItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				SendData.sendData(FirstWindow.getDataOrganization(), FirstWindow.getFile());
			}
		});

		// Save menu to the specified directory
		MenuItem saveAsFileItem = new MenuItem("Save as...");
		saveAsFileItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				fileChooser.setTitle("Save file");
				File file = fileChooser.showSaveDialog(primaryStage);
				if (file != null) {
					SendData.sendData(FirstWindow.getDataOrganization(), file);
				}
			}
		});

		// Menu exit wich save
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
		exitItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				SendData.sendData(FirstWindow.getDataOrganization(), FirstWindow.getFile());
				System.exit(0);
			}
		});

		// Menu create a new contract
		MenuItem createOrganization = new MenuItem("Новый договор...");
		createOrganization.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				AddNewContractView.newContract(primaryStage);
			}
		});

		fileMenu.getItems().addAll(newItem, openFileItem, saveFileItem, saveAsFileItem, exitItem);
		editMenu.getItems().addAll(createOrganization);
		helpMenu.getItems().add(helpItem);

		menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);

		return menuBar;
	}

	// setting file extension
	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("fr", "*.fr"));
	}
}
