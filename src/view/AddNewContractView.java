package view;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Contract;
import model.ReductionFactor;
import model.RoomType;

public class AddNewContractView {
	private static LocalDate beginningContract;
	private static LocalDate endContract;

	/**
	 * @param primaryStage
	 */
	public static void newContract(Stage primaryStage) {

		DatePicker dataPickerBeginingContract = new DatePicker();
		DatePicker dataPickerEndContract = new DatePicker();

		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.isBefore(dataPickerBeginingContract.getValue().plusDays(1))) {
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
					}
				};
			}
		};
		dataPickerEndContract.setDayCellFactory(dayCellFactory);

		dataPickerBeginingContract.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				beginningContract = dataPickerBeginingContract.getValue();
			}
		});

		dataPickerEndContract.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				endContract = dataPickerEndContract.getValue();
			}
		});

		GridPane gridpane = new GridPane();
		gridpane.getColumnConstraints().add(new ColumnConstraints(200));
		gridpane.getColumnConstraints().add(new ColumnConstraints(250));
		gridpane.setPadding(new Insets(10));
		gridpane.setHgap(5);
		gridpane.setVgap(5);

		final Label labelOrganization = new Label("�����������");
		final Label labelLegalAddress = new Label("����������� �����");
		final Label labelContactPerson = new Label("���������� ����");
		final Label labelPhone = new Label("�������");
		final Label labelContractNumber = new Label("����� ��������");
		final Label labelBeginningContract = new Label("���� ������ ������");
		final Label labelEndContract = new Label("���� ��������� ������");
		final Label labelRentalAddress = new Label("����� ����������� ���������");
		final Label labelRentalArea = new Label("������� ����������� ���������");
		final Label labelRoomType = new Label("��� ���������");
		final Label labelReductionFactor = new Label("���������� �����������");
		final Label labelDueDate = new Label("���� ������");

		gridpane.addColumn(0, labelOrganization, labelLegalAddress, labelContactPerson, labelPhone, labelContractNumber,
				labelBeginningContract, labelEndContract, labelRentalAddress, labelRentalArea, labelRoomType,
				labelReductionFactor, labelDueDate);

		TextField textOrganization = new TextField();
		TextField textLegalAddress = new TextField();
		TextField textContactPerson = new TextField();
		TextField textPhone = new TextField();
		TextField textContractNumber = new TextField();
		TextField textRentalAddress = new TextField();
		TextField textRentalArea = new TextField();
		TextField textDueDate = new TextField();

		ComboBox<RoomType> boxRoomType = new ComboBox<>();
		boxRoomType.getItems().addAll(RoomType.values());

		ComboBox<ReductionFactor> boxReductionFactor = new ComboBox<>();
		boxReductionFactor.getItems().addAll(ReductionFactor.values());

		Button addButton = new Button("��������");
		addButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (textOrganization.getText().isEmpty() || textLegalAddress.getText().isEmpty()
						|| textContactPerson.getText().isEmpty() || textPhone.getText().isEmpty()
						|| textContractNumber.getText().isEmpty() || beginningContract.toString().isEmpty()
						|| endContract.toString().isEmpty() || textRentalAddress.getText().isEmpty()
						|| textRentalArea.getText().isEmpty() || boxRoomType.getValue().name().isEmpty()
						|| boxReductionFactor.getValue().name().isEmpty() || textDueDate.getText().isEmpty()) {
					messangh("���� �� ���������");

				} else if (endContract.isBefore(beginningContract)) {
					messangh("�� ����� �������� ����");
				} else if (!textRentalArea.getText().matches("\\d+[\\.]*\\d*")) {
					System.out.println(textRentalArea.getText().matches("\\d+(\\.\\d+)"));
					messangh("�� ���������� �������� � ����: ������� ����������� ���������");
				} else {
					Contract newContract = new Contract();
					newContract = new Contract.Builder().organization(textOrganization.getText())
							.legalAddress(textLegalAddress.getText()).contactPerson(textContactPerson.getText())
							.phone(textPhone.getText()).contractNumber(textContractNumber.getText())
							.beginningContract(beginningContract).endContract(endContract)
							.rentalAddress(textRentalAddress.getText())
							.rentalArea(Double.parseDouble(textRentalArea.getText())).roomType(boxRoomType.getValue())
							.reductionFactor(boxReductionFactor.getValue()).dueDate(textDueDate.getText()).build();
					FirstWindow.dataOrganization.getDataOrganization().add(newContract);
					FirstWindow.newStart(primaryStage);
				}
			}
		});

		Button exitButton = new Button("������");
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				FirstWindow.newStart(primaryStage);
			}
		});

		gridpane.addColumn(1, textOrganization, textLegalAddress, textContactPerson, textPhone, textContractNumber,
				dataPickerBeginingContract, dataPickerEndContract, textRentalAddress, textRentalArea, boxRoomType,
				boxReductionFactor, textDueDate);

		gridpane.add(exitButton, 0, 13);
		GridPane.setHalignment(exitButton, HPos.CENTER);
		gridpane.add(addButton, 1, 13);
		GridPane.setHalignment(addButton, HPos.CENTER);

		Scene scene = new Scene(gridpane);
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.setTitle("���� ������ ��������");
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public static void messangh(String string) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setHeaderText(null);
		alert.setContentText(string);
		alert.showAndWait();

	}
}
