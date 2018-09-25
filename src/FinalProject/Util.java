package FinalProject;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Util {
	public static void failureAlert(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert!");
		alert.setHeaderText("Information Alert");
		alert.setContentText(message);
		alert.showAndWait();
	}

}

class Remove {
	public static void removeAlert(String message) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Alert!");
		alert.setHeaderText(message);
		alert.show(); 
	}
}
