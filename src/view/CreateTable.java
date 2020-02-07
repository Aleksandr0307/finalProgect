package view;

import java.time.LocalDate;
import java.time.format.FormatStyle;

import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import kontroller.DatePickerTableCell;
import model.Contract;
import model.DataContract;
import model.ReductionFactor;
import model.RoomType;

public class CreateTable {

	/**
	 * @param dataOrganization
	 * @return TableView for Contract and edit colums
	 */
	@SuppressWarnings("unchecked")
	public static TableView<Contract> tableView(DataContract dataOrganization) {

		TableView<Contract> table = new TableView<>();
		table.autosize();
		table.setEditable(true);
		table.getItems().addAll(dataOrganization.getDataOrganization());

		TableColumn<Contract, String> organization = getColTable("Организация", "organization");
		organization.setOnEditCommit(
				s -> s.getTableView().getItems().get(s.getTablePosition().getRow()).setOrganization(s.getNewValue()));

		TableColumn<Contract, String> legalAddress = getColTable("Юридический адрес", "legalAddress");
		legalAddress.setOnEditCommit(
				s -> s.getTableView().getItems().get(s.getTablePosition().getRow()).setLegalAddress(s.getNewValue()));

		TableColumn<Contract, String> contactPerson = getColTable("Контактное лицо", "contactPerson");
		contactPerson.setOnEditCommit(
				s -> s.getTableView().getItems().get(s.getTablePosition().getRow()).setContactPerson(s.getNewValue()));

		TableColumn<Contract, String> phone = getColTable("Номер телефона", "phone");
		phone.setOnEditCommit(
				s -> s.getTableView().getItems().get(s.getTablePosition().getRow()).setPhone(s.getNewValue()));

		TableColumn<Contract, String> contractNumber = getColTable("№ Договора", "contractNumber");
		contractNumber.setOnEditCommit(
				s -> s.getTableView().getItems().get(s.getTablePosition().getRow()).setContractNumber(s.getNewValue()));

		TableColumn<Contract, LocalDate> beginningContract = getColTableDate("Дата начала договора",
				"beginningContract");
		beginningContract.setOnEditCommit(s -> s.getTableView().getItems().get(s.getTablePosition().getRow())
				.setBeginningContract(s.getNewValue()));

		TableColumn<Contract, LocalDate> endContract = getColTableDate("Дата окончания договора", "endContract");
		endContract.setOnEditCommit(
				s -> s.getTableView().getItems().get(s.getTablePosition().getRow()).setEndContract(s.getNewValue()));

		TableColumn<Contract, String> rentalAddress = getColTable("Адрес арендуемого\n помещения", "rentalAddress");
		rentalAddress.setOnEditCommit(
				s -> s.getTableView().getItems().get(s.getTablePosition().getRow()).setRentalAddress(s.getNewValue()));

		TableColumn<Contract, Double> rentalArea = getColTableDouble("Площадь\nfhtyls", "rentalArea");
		rentalArea.setOnEditCommit(
				s -> s.getTableView().getItems().get(s.getTablePosition().getRow()).setRentalArea(s.getNewValue()));

		TableColumn<Contract, RoomType> roomType = getColTypeRoom("Тип\n помещения", "roomType");

		TableColumn<Contract, ReductionFactor> reductionFactor = getColRedFactor("Понижающий\n коэффициент",
				"reductionFactor");

		TableColumn<Contract, String> dueDate = getColTable("Срок\nоплаты", "dueDate");
		dueDate.setOnEditCommit(
				s -> s.getTableView().getItems().get(s.getTablePosition().getRow()).setRentalAddress(s.getNewValue()));

		table.getColumns().addAll(organization, legalAddress, contactPerson, phone, contractNumber, beginningContract,
				endContract, rentalAddress, rentalArea, roomType, reductionFactor, dueDate);
		return table;
	}

	/**
	 * columns with type String
	 * 
	 * @param value-name
	 *            Colums
	 * @param key-field
	 *            value
	 * @see Contract
	 * @return TableColum for String
	 */
	private static TableColumn<Contract, String> getColTable(String value, String key) {
		TableColumn<Contract, String> valueColum = new TableColumn<>(value);
		valueColum.setCellFactory(TextFieldTableCell.<Contract>forTableColumn());
		PropertyValueFactory<Contract, String> cellValueFactory = new PropertyValueFactory<>(key);
		valueColum.setCellValueFactory(cellValueFactory);
		return valueColum;
	}

	/**
	 * @param value-name
	 *            Colums
	 * @param keykey-field
	 *            value
	 * @see Contract
	 * @return TableColum for Double
	 */
	private static TableColumn<Contract, Double> getColTableDouble(String value, String key) {
		TableColumn<Contract, Double> valueColum = new TableColumn<>(value);
		valueColum.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		PropertyValueFactory<Contract, Double> cellValueFactory = new PropertyValueFactory<>(key);
		valueColum.setCellValueFactory(cellValueFactory);
		return valueColum;
	}

	/**
	 * @param value-name
	 *            Colums
	 * @param key-field
	 *            value
	 * @see Contract
	 * @return TableColum for LocalDate
	 */
	public static TableColumn<Contract, LocalDate> getColTableDate(String value, String key) {
		TableColumn<Contract, LocalDate> valueColum = new TableColumn<>(value);
		StringConverter<LocalDate> converter = new LocalDateStringConverter(FormatStyle.MEDIUM);
		valueColum.setCellFactory(DatePickerTableCell.<Contract>forTableColumn(converter));
		PropertyValueFactory<Contract, LocalDate> cellValueFactory = new PropertyValueFactory<>(key);
		valueColum.setCellValueFactory(cellValueFactory);
		return valueColum;
	}

	/**
	 * @param value-name
	 *            Colums
	 * @param key-field
	 *            value
	 * @see Contract
	 * @return TableColum for RoomType
	 */
	public static TableColumn<Contract, RoomType> getColTypeRoom(String value, String key) {
		TableColumn<Contract, RoomType> valueColum = new TableColumn<>(value);
		valueColum.setCellFactory(ComboBoxTableCell.<Contract, RoomType>forTableColumn(RoomType.values()));
		valueColum.setCellValueFactory(new PropertyValueFactory<Contract, RoomType>(key));
		valueColum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Contract, RoomType>>() {
			@Override
			public void handle(CellEditEvent<Contract, RoomType> t) {
				t.getTableView().getItems().get(t.getTablePosition().getRow()).setRoomType(t.getNewValue());
			}
		});
		return valueColum;
	}

	/**
	 * @param value-name
	 *            Colums
	 * @param key-field
	 *            value
	 * @see Contract
	 * @return TableColum for ReductionFactor
	 */
	public static TableColumn<Contract, ReductionFactor> getColRedFactor(String value, String key) {
		TableColumn<Contract, ReductionFactor> valueColum = new TableColumn<>(value);
		valueColum
				.setCellFactory(ComboBoxTableCell.<Contract, ReductionFactor>forTableColumn(ReductionFactor.values()));
		valueColum.setCellValueFactory(new PropertyValueFactory<Contract, ReductionFactor>(key));
		valueColum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Contract, ReductionFactor>>() {
			@Override
			public void handle(CellEditEvent<Contract, ReductionFactor> t) {
				t.getTableView().getItems().get(t.getTablePosition().getRow()).setReductionFactor(t.getNewValue());
			}
		});
		return valueColum;
	}
}