package view;

import java.time.LocalDate;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import kontroller.GetData;
import model.Contract;
import model.DataContract;

public class FirstWindow extends Application {

	public static DataContract dataOrganization = GetData.findings();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		newStart(primaryStage);
	}

	/**
	 * @param primaryStage
	 */
	public static void newStart(Stage primaryStage) {

		MenuBar menuBar = MenuBarOrganization.menuBar(primaryStage);

		TableView<Contract> table = tableView(dataOrganization);

		HBox hbox = new HBox(10);
		Label dateSearch = new Label("�������� ����������� �� ����...");
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

		RadioButton allContracts = new RadioButton("1. ������ ���������");
		RadioButton contractsEnding = new RadioButton("2. ������ ��������� �� ������ ��������� ��� ������ � �����");
		RadioButton currentAgreements = new RadioButton("3. ������ ����������� ��������");
		RadioButton closed�ontracts = new RadioButton("4. ������ �������� ���������");

		ToggleGroup group = new ToggleGroup();
		allContracts.setToggleGroup(group);
		contractsEnding.setToggleGroup(group);
		currentAgreements.setToggleGroup(group);
		closed�ontracts.setToggleGroup(group);

		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> changed, Toggle oldValue, Toggle newValue) {

				RadioButton selectedBtn = (RadioButton) newValue;

				if (selectedBtn.getText().matches("1.+")) {
					table.getItems().clear();
					table.getItems().addAll(dataOrganization.getDataOrganization());
				} else if (selectedBtn.getText().matches("2.+")) {
					table.getItems().clear();
					table.getItems()
							.addAll(dataOrganization.getDataOrganization().stream()
									.filter(p -> LocalDate.now().isBefore(p.getEndContract())
											& LocalDate.now().isAfter(p.getEndContract().minusMonths(2)))
									.collect(Collectors.toList()));
				} else if (selectedBtn.getText().matches("3.+")) {
					table.getItems().clear();
					table.getItems()
							.addAll(dataOrganization.getDataOrganization().stream()
									.filter(p -> LocalDate.now().isBefore(p.getEndContract())
											& LocalDate.now().isAfter(p.getBeginningContract()))
									.collect(Collectors.toList()));
				} else if (selectedBtn.getText().matches("4.+")) {
					table.getItems().clear();
					table.getItems().addAll(dataOrganization.getDataOrganization().stream()
							.filter(p -> LocalDate.now().isAfter(p.getEndContract())).collect(Collectors.toList()));
				}
			}
		});

		FlowPane root = new FlowPane(Orientation.HORIZONTAL, 15, 5);
		root.getChildren().addAll(allContracts, contractsEnding, currentAgreements, closed�ontracts);
		root.setPadding(new Insets(15));

		hbox.getChildren().addAll(dateSearch, datePicker, okDate);

		VBox vboxRoot = new VBox();
		vboxRoot.setFillWidth(true);

		vboxRoot.getChildren().addAll(menuBar, root, hbox, table);
		VBox.setMargin(table, new Insets(15, 15, 15, 15));

		Scene scene = new Scene(vboxRoot, 1130, 550);

		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.setTitle("��������� 1.0");
		primaryStage.setResizable(true);
		primaryStage.centerOnScreen();

		primaryStage.show();

	}

	/**
	 * @param dataOrganization
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static TableView<Contract> tableView(DataContract dataOrganization) {

		TableView<Contract> table = new TableView<>();
		table.autosize();
		table.setEditable(true);

		table.getItems().addAll(dataOrganization.getDataOrganization());

		TableColumn<Contract, String> organization = getColContract("�����������", "organization");
		editingOrganization(organization);
		TableColumn<Contract, String> legalAddress = getColContract("�����������\n �����", "legalAddress");
		editingLegalAddress(legalAddress);
		TableColumn<Contract, String> contactPerson = getColContract("����������\n ����", "contactPerson");
		editingContactPerson(contactPerson);
		TableColumn<Contract, String> phone = getColContract("�������", "phone");
		editingPhone(phone);
		TableColumn<Contract, String> contractNumber = getColContract("����� ��������", "contractNumber");
		editingContractNumber(contractNumber);

		TableColumn<Contract, LocalDate> beginningContract = getColContractDate("����\n ������\n ��������",
				"beginningContract");
		editingBeginningContract(beginningContract);
		TableColumn<Contract, LocalDate> endContract = getColContractDate("����\n ���������\n ��������", "endContract");

		TableColumn<Contract, String> rentalAddress = getColContract("�����\n �����������\n ���������",
				"rentalAddress");
		editingRentalAddress(rentalAddress);
		TableColumn<Contract, Double> rentalArea = getColContractDouble("�������\n ������", "rentalArea");
		editingRentalArea(rentalArea);
		TableColumn<Contract, String> roomType = getColContract("���\n ���������", "roomType");

		TableColumn<Contract, String> reductionFactor = getColContract("����������\n �����������", "reductionFactor");

		TableColumn<Contract, String> dueDate = getColContract("����\n ������", "dueDate");
		editingDueDate(dueDate);

		table.getColumns().addAll(organization, legalAddress, contactPerson, phone, contractNumber, beginningContract,
				endContract, rentalAddress, rentalArea, roomType, reductionFactor, dueDate);
		return table;
	}

	private static TableColumn<Contract, String> getColContract(String value, String key) {
		TableColumn<Contract, String> valueColum = new TableColumn<>(value);
		PropertyValueFactory<Contract, String> devaceCellValueFactory = new PropertyValueFactory<>(key);
		valueColum.setCellValueFactory(devaceCellValueFactory);
		return valueColum;

	}

	private static TableColumn<Contract, Double> getColContractDouble(String value, String key) {
		TableColumn<Contract, Double> valueColum = new TableColumn<>(value);
		PropertyValueFactory<Contract, Double> devaceCellValueFactory = new PropertyValueFactory<>(key);
		valueColum.setCellValueFactory(devaceCellValueFactory);
		return valueColum;

	}

	private static TableColumn<Contract, LocalDate> getColContractDate(String value, String key) {
		TableColumn<Contract, LocalDate> valueColum = new TableColumn<>(value);
		PropertyValueFactory<Contract, LocalDate> devaceCellValueFactory = new PropertyValueFactory<>(key);
		valueColum.setCellValueFactory(devaceCellValueFactory);
		return valueColum;

	}

	/**
	 * @param beginningContract
	 * @return
	 */
	private static TableColumn<Contract, LocalDate> editingBeginningContract(
			TableColumn<Contract, LocalDate> parameter) {
		TableColumn<Contract, LocalDate> valueColum = new TableColumn<>();
		valueColum.setCellValueFactory(new PropertyValueFactory<>("beginningContract"));
		valueColum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Contract, LocalDate>>() {
			@Override
			public void handle(CellEditEvent<Contract, LocalDate> s) {
				s.getTableView().getItems().get(s.getTablePosition().getRow()).setBeginningContract(s.getNewValue());
			}
		});
		return valueColum;
	}

	/**
	 * @param parameter
	 */
	private static void editingOrganization(TableColumn<Contract, String> parameter) {
		parameter.setCellFactory(TextFieldTableCell.<Contract>forTableColumn());
		parameter.setOnEditCommit(new EventHandler<CellEditEvent<Contract, String>>() {
			@Override
			public void handle(CellEditEvent<Contract, String> s) {
				s.getTableView().getItems().get(s.getTablePosition().getRow()).setOrganization(s.getNewValue());
			}
		});
	}

	/**
	 * @param parameter
	 */
	private static void editingLegalAddress(TableColumn<Contract, String> parameter) {
		parameter.setCellFactory(TextFieldTableCell.<Contract>forTableColumn());
		parameter.setOnEditCommit(new EventHandler<CellEditEvent<Contract, String>>() {
			@Override
			public void handle(CellEditEvent<Contract, String> s) {
				s.getTableView().getItems().get(s.getTablePosition().getRow()).setLegalAddress(s.getNewValue());
			}
		});
	}

	/**
	 * @param parameter
	 */
	private static void editingContactPerson(TableColumn<Contract, String> parameter) {
		parameter.setCellFactory(TextFieldTableCell.<Contract>forTableColumn());
		parameter.setOnEditCommit(new EventHandler<CellEditEvent<Contract, String>>() {
			@Override
			public void handle(CellEditEvent<Contract, String> s) {
				s.getTableView().getItems().get(s.getTablePosition().getRow()).setContactPerson(s.getNewValue());
			}
		});
	}

	/**
	 * @param parameter
	 */
	private static void editingPhone(TableColumn<Contract, String> parameter) {
		parameter.setCellFactory(TextFieldTableCell.<Contract>forTableColumn());
		parameter.setOnEditCommit(new EventHandler<CellEditEvent<Contract, String>>() {
			@Override
			public void handle(CellEditEvent<Contract, String> s) {
				s.getTableView().getItems().get(s.getTablePosition().getRow()).setPhone(s.getNewValue());
			}
		});
	}

	/**
	 * @param parameter
	 */
	private static void editingContractNumber(TableColumn<Contract, String> parameter) {
		parameter.setCellFactory(TextFieldTableCell.<Contract>forTableColumn());
		parameter.setOnEditCommit(new EventHandler<CellEditEvent<Contract, String>>() {
			@Override
			public void handle(CellEditEvent<Contract, String> s) {
				s.getTableView().getItems().get(s.getTablePosition().getRow()).setContractNumber(s.getNewValue());
			}
		});
	}

	/**
	 * @param parameter
	 */
	private static void editingRentalArea(TableColumn<Contract, Double> parameter) {
		parameter.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		parameter.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Contract, Double>>() {
			@Override
			public void handle(CellEditEvent<Contract, Double> s) {
				s.getTableView().getItems().get(s.getTablePosition().getRow()).setRentalArea(s.getNewValue());

			}
		});
	}

	/**
	 * @param parameter
	 */
	private static void editingRentalAddress(TableColumn<Contract, String> parameter) {
		parameter.setCellFactory(TextFieldTableCell.<Contract>forTableColumn());
		parameter.setOnEditCommit(new EventHandler<CellEditEvent<Contract, String>>() {
			@Override
			public void handle(CellEditEvent<Contract, String> s) {
				s.getTableView().getItems().get(s.getTablePosition().getRow()).setRentalAddress(s.getNewValue());
			}
		});
	}

	/**
	 * @param parameter
	 */
	private static void editingDueDate(TableColumn<Contract, String> parameter) {
		parameter.setCellFactory(TextFieldTableCell.<Contract>forTableColumn());
		parameter.setOnEditCommit(new EventHandler<CellEditEvent<Contract, String>>() {
			@Override
			public void handle(CellEditEvent<Contract, String> s) {
				s.getTableView().getItems().get(s.getTablePosition().getRow()).setDueDate(s.getNewValue());
			}
		});
	}

}
