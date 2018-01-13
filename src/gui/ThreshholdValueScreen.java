package gui;

import java.net.URL;

import gui.conf.ProjectFontColors;
import gui.conf.ProjectFonts;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ThreshholdValueScreen {
	public static void threasholdTable() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL resource = loader.getResource("b3.jpg");
		
		Label label = new Label("Parameters Threshold");
		label.setFont(ProjectFonts.mainLabelFont());
		label.setTextFill(ProjectFontColors.mainLabelFontColor());
		
		BorderPane pane = new BorderPane();
		pane.setTop(label);
		pane.setStyle("-fx-background-color: lightblue; -fx-background-image: URL('"+resource+"')");
		
		Scene scene = new Scene(pane,500,500);
		
		Stage thresholdTableStage = new Stage();
		thresholdTableStage.setScene(scene);
		thresholdTableStage.setTitle("Set Threshold");
		/*thresholdTableStage.setOnCloseRequest(
				e -> {
					e.consume();
				}
		);*/
		thresholdTableStage.initModality(Modality.APPLICATION_MODAL);
		thresholdTableStage.show();
	}
}
