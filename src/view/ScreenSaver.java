package view;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * displays a splash screen when the program starts
 *
 */
public class ScreenSaver {

	public static void screenSaver(Stage primaryStage) {
		HBox hbox = new HBox();
		Scene scene = new Scene(hbox);
		ImageView image = new ImageView("view/renting.png");
		image.setFitHeight(650);
		image.setFitWidth(650);

		hbox.getChildren().add(image);

		primaryStage.setScene(scene);
		primaryStage.setTitle("For rent 1.0");
		primaryStage.show();

		PauseTransition delay = new PauseTransition(Duration.millis(1500));
		delay.play();
		delay.setOnFinished(event -> {
			primaryStage.close();
			FirstWindow.newStart(primaryStage);
		});

	}
}
