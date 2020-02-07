package view;

import java.io.File;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kontroller.GetData;
import model.Contract;
import model.DataContract;

/**
 * This program is designed to account for leases for the duration of their
 * validity
 * 
 * @author Александр
 * @version 1.0
 */
public class FirstWindow extends Application {

	private static File file = new File("test.fr");
	private static DataContract dataOrganization = GetData.findings(file);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		ScreenSaver.screenSaver(primaryStage);
	}

	/**
	 * main working screen
	 * 
	 * @param primaryStage
	 */
	public static void newStart(Stage primaryStage) {

		// getting menu
		MenuBar menuBar = MenuBarOrganization.menuBar(primaryStage);

		// getting table
		TableView<Contract> table = CreateTable.tableView(dataOrganization);

		// get Button delete contract
		Button buttonDel = new Button("Удалить организацию");
		buttonDel.setOnAction(e -> {
			Contract selectedItem = table.getSelectionModel().getSelectedItem();
			if (!(selectedItem == null)) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Предупреждение!");
				alert.setHeaderText("Внимание удаленную организацию восстановить не возможно");
				alert.setContentText("Подтвердить удаление?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					table.getItems().remove(selectedItem);
					dataOrganization.getDataOrganization().remove(selectedItem);
				} else {
					FirstWindow.newStart(primaryStage);
				}
			} else
				MessageError.messangh("Не выбрана организация");
		});

		HBox hbox = new HBox(10);

		// getting field by date
		Label dateSearch = new Label("Договора действующие на дату...");
		HBox.setMargin(dateSearch, new Insets(5, 0, 0, 15));
		DatePicker datePicker = new DatePicker();
		Button okDate = new Button("OK");
		okDate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				LocalDate date = datePicker.getValue();
				table.getItems().clear();
				table.getItems()
						.addAll(dataOrganization.getDataOrganization().stream()
								.filter(p -> p.getBeginningContract().isBefore(date) & p.getEndContract().isAfter(date))
								.collect(Collectors.toList()));
			}
		});

		// getting a group of buttons by filter

		RadioButton allContracts = new RadioButton("1. Реестр договоров");
		RadioButton contractsEnding = new RadioButton("2. Реестр договоров со сроком окончания два месяца и менее");
		RadioButton currentAgreements = new RadioButton("3. Реестр действующих договора");
		RadioButton closedContracts = new RadioButton("4. Реестр закрытых договоров");

		ToggleGroup group = new ToggleGroup();
		allContracts.setToggleGroup(group);
		contractsEnding.setToggleGroup(group);
		currentAgreements.setToggleGroup(group);
		closedContracts.setToggleGroup(group);

		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> changed, Toggle oldValue, Toggle newValue) {

				if (allContracts.isSelected()) {
					table.getItems().clear();
					table.getItems().addAll(dataOrganization.getDataOrganization());
				} else if (contractsEnding.isSelected()) {
					table.getItems().clear();
					table.getItems()
							.addAll(dataOrganization.getDataOrganization().stream()
									.filter(p -> LocalDate.now().isBefore(p.getEndContract())
											& LocalDate.now().isAfter(p.getEndContract().minusMonths(2)))
									.collect(Collectors.toList()));
				} else if (currentAgreements.isSelected()) {
					table.getItems().clear();
					table.getItems()
							.addAll(dataOrganization.getDataOrganization().stream()
									.filter(p -> LocalDate.now().isBefore(p.getEndContract())
											& LocalDate.now().isAfter(p.getBeginningContract()))
									.collect(Collectors.toList()));
				} else if (closedContracts.isSelected()) {
					table.getItems().clear();
					table.getItems().addAll(dataOrganization.getDataOrganization().stream()
							.filter(p -> LocalDate.now().isAfter(p.getEndContract())).collect(Collectors.toList()));
				}
			}
		});

		FlowPane root = new FlowPane(Orientation.HORIZONTAL, 15, 5);
		root.getChildren().addAll(allContracts, contractsEnding, currentAgreements, closedContracts);
		root.setPadding(new Insets(15));

		hbox.getChildren().addAll(dateSearch, datePicker, okDate);

		VBox vboxRoot = new VBox();
		vboxRoot.setFillWidth(true);

		vboxRoot.getChildren().addAll(menuBar, root, hbox, table, buttonDel);
		VBox.setMargin(table, new Insets(15, 15, 15, 15));
		VBox.setMargin(buttonDel, new Insets(15, 15, 15, 15));
		Scene scene = new Scene(vboxRoot);

		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	static final DataContract getDataOrganization() {
		return dataOrganization;
	}

	public static final void setDataOrganization(DataContract dataOrganization) {
		FirstWindow.dataOrganization = dataOrganization;
	}

	public static final File getFile() {
		return file;
	}

	public static final void setFile(File file) {
		FirstWindow.file = file;
	}

}
