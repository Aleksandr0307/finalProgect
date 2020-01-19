package view;

import java.time.LocalDate;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
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

		RadioButton allContracts = new RadioButton("1. Реестр договоров");
		RadioButton contractsEnding = new RadioButton("2. Реестр договоров со сроком окончания два месяца и менее");
		RadioButton currentAgreements = new RadioButton("3. Реестр действующих договора");
		RadioButton closedСontracts = new RadioButton("4. Реестр закрытых договоров");

		ToggleGroup group = new ToggleGroup();
		allContracts.setToggleGroup(group);
		contractsEnding.setToggleGroup(group);
		currentAgreements.setToggleGroup(group);
		closedСontracts.setToggleGroup(group);

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
		root.getChildren().addAll(allContracts, contractsEnding, currentAgreements, closedСontracts);
		root.setPadding(new Insets(15));

		VBox vboxRoot = new VBox();
		vboxRoot.setFillWidth(true);

		vboxRoot.getChildren().addAll(menuBar, root, table);
		VBox.setMargin(table, new Insets(15, 15, 15, 15));

		Scene scene = new Scene(vboxRoot, 1130, 550);

		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Арендатор 1.0");
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

		TableColumn<Contract, String> organization = getColContract("Организация", "organization");
		editingOrganization(organization);
		TableColumn<Contract, String> legalAddress = getColContract("Юридический\n адрес", "legalAddress");
		editingLegalAddress(legalAddress);
		TableColumn<Contract, String> contactPerson = getColContract("Контактное\n лицо", "contactPerson");
		editingContactPerson(contactPerson);
		TableColumn<Contract, String> phone = getColContract("Телефон", "phone");
		editingPhone(phone);
		TableColumn<Contract, String> contractNumber = getColContract("Номер договора", "contractNumber");
		editingContractNumber(contractNumber);
		TableColumn<Contract, String> beginningContract = getColContract("Дата\n начала\n договора",
				"beginningContract");

		TableColumn<Contract, String> endContract = getColContract("Дата\n окончания\n договора", "endContract");

		TableColumn<Contract, String> rentalAddress = getColContract("Адрес\n арендуемого\n помещения",
				"rentalAddress");
		editingRentalAddress(rentalAddress);
		TableColumn<Contract, Integer> rentalArea = getColContractInt("Площадь\n аренды", "rentalArea");
		editingRentalArea(rentalArea);
		TableColumn<Contract, String> roomType = getColContract("Тип\n помещения", "roomType");

		TableColumn<Contract, String> validityContract = getColContract("Состояние\n договора", "validityContract");

		TableColumn<Contract, String> reductionFactor = getColContract("Понижающий\n коэффициент", "reductionFactor");

		TableColumn<Contract, String> dueDate = getColContract("Срок\n оплаты", "dueDate");
		editingDueDate(dueDate);

		table.getColumns().addAll(organization, legalAddress, contactPerson, phone, contractNumber, beginningContract,
				endContract, rentalAddress, rentalArea, roomType, validityContract, reductionFactor, dueDate);
		return table;
	}

	private static TableColumn<Contract, String> getColContract(String value, String key) {
		TableColumn<Contract, String> valueColum = new TableColumn<>(value);
		PropertyValueFactory<Contract, String> devaceCellValueFactory = new PropertyValueFactory<>(key);
		valueColum.setCellValueFactory(devaceCellValueFactory);
		return valueColum;

	}

	private static TableColumn<Contract, Integer> getColContractInt(String value, String key) {
		TableColumn<Contract, Integer> valueColum = new TableColumn<>(value);
		PropertyValueFactory<Contract, Integer> devaceCellValueFactory = new PropertyValueFactory<>(key);
		valueColum.setCellValueFactory(devaceCellValueFactory);
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

	private static void editingLegalAddress(TableColumn<Contract, String> parameter) {
		parameter.setCellFactory(TextFieldTableCell.<Contract>forTableColumn());
		parameter.setOnEditCommit(new EventHandler<CellEditEvent<Contract, String>>() {
			@Override
			public void handle(CellEditEvent<Contract, String> s) {
				s.getTableView().getItems().get(s.getTablePosition().getRow()).setLegalAddress(s.getNewValue());
			}
		});
	}

	private static void editingContactPerson(TableColumn<Contract, String> parameter) {
		parameter.setCellFactory(TextFieldTableCell.<Contract>forTableColumn());
		parameter.setOnEditCommit(new EventHandler<CellEditEvent<Contract, String>>() {
			@Override
			public void handle(CellEditEvent<Contract, String> s) {
				s.getTableView().getItems().get(s.getTablePosition().getRow()).setContactPerson(s.getNewValue());
			}
		});
	}

	private static void editingPhone(TableColumn<Contract, String> parameter) {
		parameter.setCellFactory(TextFieldTableCell.<Contract>forTableColumn());
		parameter.setOnEditCommit(new EventHandler<CellEditEvent<Contract, String>>() {
			@Override
			public void handle(CellEditEvent<Contract, String> s) {
				s.getTableView().getItems().get(s.getTablePosition().getRow()).setPhone(s.getNewValue());
			}
		});
	}

	private static void editingContractNumber(TableColumn<Contract, String> parameter) {
		parameter.setCellFactory(TextFieldTableCell.<Contract>forTableColumn());
		parameter.setOnEditCommit(new EventHandler<CellEditEvent<Contract, String>>() {
			@Override
			public void handle(CellEditEvent<Contract, String> s) {
				s.getTableView().getItems().get(s.getTablePosition().getRow()).setContractNumber(s.getNewValue());
			}
		});
	}

	private static void editingRentalArea(TableColumn<Contract, Integer> parameter) {
		parameter.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		parameter.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Contract, Integer>>() {
			@Override
			public void handle(CellEditEvent<Contract, Integer> s) {
				s.getTableView().getItems().get(s.getTablePosition().getRow()).setRentalArea(s.getNewValue());

			}
		});
	}

	private static void editingRentalAddress(TableColumn<Contract, String> parameter) {
		parameter.setCellFactory(TextFieldTableCell.<Contract>forTableColumn());
		parameter.setOnEditCommit(new EventHandler<CellEditEvent<Contract, String>>() {
			@Override
			public void handle(CellEditEvent<Contract, String> s) {
				s.getTableView().getItems().get(s.getTablePosition().getRow()).setRentalAddress(s.getNewValue());
			}
		});
	}

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
