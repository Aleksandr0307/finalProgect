package kontroller;

import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

public class DatePickerTableCell<S, T> extends TableCell<S, LocalDate> {

	private DatePicker datePicker;
	private StringConverter<LocalDate> converter = null;
	private boolean datePickerEditable = true;

	public DatePickerTableCell() {
		this.converter = new LocalDateStringConverter();
	}

	public DatePickerTableCell(boolean datePickerEditable) {
		this.converter = new LocalDateStringConverter();
		this.datePickerEditable = datePickerEditable;
	}

	public DatePickerTableCell(StringConverter<LocalDate> converter) {
		this.converter = converter;
	}

	public DatePickerTableCell(StringConverter<LocalDate> converter, boolean datePickerEditable) {
		this.converter = converter;
		this.datePickerEditable = datePickerEditable;
	}

	@Override
	public void startEdit() {

		if (!isEditable() || !getTableView().isEditable() || !getTableColumn().isEditable()) {
			return;
		}
		super.startEdit();

		if (datePicker == null) {
			this.createDatePicker();
		}

		this.setGraphic(datePicker);
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();
		this.setText(converter.toString(this.getItem()));
		this.setGraphic(null);
	}

	@Override
	public void updateItem(LocalDate item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			this.setText(null);
			this.setGraphic(null);
		} else {
			if (this.isEditing()) {
				if (datePicker != null) {
					datePicker.setValue(item);
				}
				this.setText(null);
				this.setGraphic(datePicker);
			} else {
				this.setText(converter.toString(item));
				this.setGraphic(null);
			}
		}
	}

	private void createDatePicker() {
		datePicker = new DatePicker();
		datePicker.setConverter(converter);

		// Set the current value in the cell to the DatePicker
		datePicker.setValue(this.getItem());

		// Configure the DatePicker properties
		datePicker.setPrefWidth(this.getWidth() - this.getGraphicTextGap() * 2);
		datePicker.setEditable(this.datePickerEditable);

		// Commit the new value when the user selects or enters a date
		datePicker.valueProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> prop, Object oldValue, Object newValue) {
				if (DatePickerTableCell.this.isEditing()) {
					DatePickerTableCell.this.commitEdit((LocalDate) newValue);
				}
			}
		});
	}

	public static <S> Callback<TableColumn<S, LocalDate>, TableCell<S, LocalDate>> forTableColumn() {
		return forTableColumn(true);
	}

	public static <S> Callback<TableColumn<S, LocalDate>, TableCell<S, LocalDate>> forTableColumn(
			boolean datePickerEditable) {
		return (col -> new DatePickerTableCell<>(datePickerEditable));
	}

	public static <S> Callback<TableColumn<S, LocalDate>, TableCell<S, LocalDate>> forTableColumn(
			StringConverter<LocalDate> converter) {
		return forTableColumn(converter, true);
	}

	public static <S> Callback<TableColumn<S, LocalDate>, TableCell<S, LocalDate>> forTableColumn(
			StringConverter<LocalDate> converter, boolean datePickerEditable) {
		return (col -> new DatePickerTableCell<>(converter, datePickerEditable));
	}
}
