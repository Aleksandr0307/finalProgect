package view;

import javafx.scene.control.Alert;

public class MessageInformation {
	public static void messangh(String string) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText(string);
		alert.showAndWait();
	}
}
