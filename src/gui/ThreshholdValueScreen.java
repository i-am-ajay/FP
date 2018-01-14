package gui;

import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import AddEntriesToDB.ParametersToDB;
import gui.conf.ProjectFontColors;
import gui.conf.ProjectFonts;
import gui.conf.ProjectLables;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ThreshholdValueScreen {
	public static void threasholdTable() throws URISyntaxException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL resource = loader.getResource("b3.jpg");
		
		Label label = new Label("Parameters Threshold");
		label.setFont(ProjectFonts.mainLabelFont());
		label.setTextFill(ProjectFontColors.mainLabelFontColor());
		label.setUnderline(true);
		label.setAlignment(Pos.CENTER);
		label.setPadding(new Insets(10));
		BorderPane.setAlignment(label, Pos.CENTER);
		
		HBox titleBox = getTitleBox();
		
		BorderPane pane = new BorderPane();
		pane.setCenter(getCenterBox());
		pane.setStyle("-fx-background-color: lightblue; -fx-background-image: url("+resource+")");
		
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
	
	public static Pane getCenterBox(){
		ResultSet parameterSet = getParameterResultSet();
		Collection<HBox> parameterDisplay = new ArrayList<>();

		
		
		/*while(parameterSet.next()){
			String parameter = parameterSet.getString(1);
			String [] paramValues = ParametersToDB.getParameterValues(parameter);
			
			
			Label parameterLabel = new Label(parameter);
			TextField weightField = new TextField();
			TextField remarks = new TextField();
			
			if (paramValues[0] != null || paramValues[0] != ""){
				
			}
		}*/
		
		VBox box = new VBox();
		box.getChildren().addAll(titleBox);
		return box;
	}
	
	public static ResultSet getParameterResultSet(){
		return null;
	}
	public HBox getThresholdBox(String str){
		return null;
	}
	public static HBox getTitleBox(){
		Label paramTitle = ProjectLables.labelForTitles("Parameter");
		paramTitle.setMinWidth(150);
		paramTitle.setMaxWidth(150);
		paramTitle.setAlignment(Pos.CENTER);
		
		
		Label weightTitle = ProjectLables.labelForTitles("Weight");
		weightTitle.setMinWidth(150);
		weightTitle.setMaxWidth(150);
		weightTitle.setAlignment(Pos.CENTER);
		
		Label remarkTitle = ProjectLables.labelForTitles("Remarks");
		remarkTitle.setMinWidth(150);
		remarkTitle.setMaxWidth(150);
		remarkTitle.setAlignment(Pos.CENTER);
		
		HBox titleBox = new HBox();
		titleBox.getChildren().addAll(paramTitle, weightTitle, remarkTitle);
		titleBox.setPadding(new Insets(10));
		return titleBox;
	}
}
