package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import kontroller.SendData;

public class MenuBarOrganization {
	public static MenuBar menuBar(Stage primaryStage) {

		MenuBar menuBar = new MenuBar();

		Menu fileMenu = new Menu("File");
		Menu editMenu = new Menu("Edit");
		Menu helpMenu = new Menu("Help");

		MenuItem newItem = new MenuItem("New");
		MenuItem openFileItem = new MenuItem("Open File");

		MenuItem saveFileItem = new MenuItem("Save");
		saveFileItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
		saveFileItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					SendData.sendData(FirstWindow.dataOrganization);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		MenuItem saveAsFileItem = new MenuItem("Save as...");

		MenuItem exitItem = new MenuItem("Exit");
		exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
		exitItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					SendData.sendData(FirstWindow.dataOrganization);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		});

		MenuItem organizationEditing = new MenuItem("Редактировать организацию");

		MenuItem createOrganization = new MenuItem("Новый договор...");
		createOrganization.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				AddNewContractView.newContract(primaryStage);
			}
		});

		fileMenu.getItems().addAll(newItem, openFileItem, saveFileItem, saveAsFileItem, exitItem);
		editMenu.getItems().addAll(organizationEditing, createOrganization);

		menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);

		return menuBar;

	}
}
