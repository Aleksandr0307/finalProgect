package view;

import javafx.scene.control.Alert;

public class MessageError {

	/**
	 * error message
	 * 
	 * @param string
	 */
	public static void messangh(String string) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setHeaderText(null);
		alert.setContentText(string);
		alert.showAndWait();
	}
}
