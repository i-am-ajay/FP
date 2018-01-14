package gui;

import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
<<<<<<< HEAD
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
=======
import java.util.ArrayList;
import java.util.Collection;
>>>>>>> c4ec6868ebdfe60637c3376f198876e01b428747

import AddEntriesToDB.ParametersToDB;
import gui.conf.ProjectFontColors;
import gui.conf.ProjectFonts;
import gui.conf.ProjectLables;
<<<<<<< HEAD
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.StringProperty;
=======
>>>>>>> c4ec6868ebdfe60637c3376f198876e01b428747
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
<<<<<<< HEAD
import javafx.scene.control.ScrollPane;
=======
>>>>>>> c4ec6868ebdfe60637c3376f198876e01b428747
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
<<<<<<< HEAD
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
=======
>>>>>>> c4ec6868ebdfe60637c3376f198876e01b428747
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ThreshholdValueScreen {
	static HBox titleBox;
	static URL resource;
	public static void threasholdTable() throws URISyntaxException, SQLException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		resource = loader.getResource("b3.jpg");
		
		Label label = new Label("Parameters Threshold");
		label.setFont(ProjectFonts.mainLabelFont());
		label.setTextFill(ProjectFontColors.mainLabelFontColor());
		label.setUnderline(true);
		label.setAlignment(Pos.CENTER);
		label.setPadding(new Insets(10));
		BorderPane.setAlignment(label, Pos.CENTER);
		
<<<<<<< HEAD
=======
		HBox titleBox = getTitleBox();
>>>>>>> c4ec6868ebdfe60637c3376f198876e01b428747
		
		BorderPane pane = new BorderPane();
		pane.setCenter(getCenterBox());
		pane.setStyle("-fx-background-color: lightblue; -fx-background-image: url("+resource+")");
		
		Scene scene = new Scene(pane,530,500);
		
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
	
<<<<<<< HEAD
	public static Pane getCenterBox() throws SQLException{
		titleBox = getTitleBox();
		
		List<String> parameterList = ParametersToDB.getParameters();
		Collection<HBox> parameterDisplay = new ArrayList<>();

		for(String parameter : parameterList){
			String [] paramValues = ParametersToDB.getParameterValues(parameter);
			
			Label parameterLabel = new Label(parameter);
			parameterLabel.setMinWidth(150);
			parameterLabel.setMaxWidth(150);
			parameterLabel.setPadding(new Insets(5));
			parameterLabel.setAlignment(Pos.CENTER_LEFT);
			
			TextField weightField = new TextField();
			weightField.setText(paramValues[0]);
			weightField.setMinWidth(150);
			weightField.setMaxWidth(150);
			weightField.setPadding(new Insets(5));
			weightField.setAlignment(Pos.CENTER_LEFT);
			ReadOnlyBooleanProperty weightFocusProperty = weightField.focusedProperty();
			weightFocusProperty.addListener(
					(observableValue, oldValue, newValue) -> {
						if(weightFocusProperty.getValue() == false && weightField.getText() !=null) {
							if(oldValue != newValue) {
								ParametersToDB.updateWeight(parameter, weightField.getText());
							}
						}
					}
			);
			
			Region pRegion = new Region();
			pRegion.setPrefWidth(50);
			
			TextField remarks = new TextField();
			remarks.setText(paramValues[1]);
			remarks.setMinWidth(150);
			remarks.setMaxWidth(150);
			remarks.setPadding(new Insets(5));
			remarks.setAlignment(Pos.CENTER_RIGHT);
			ReadOnlyBooleanProperty remarkFocusProperty = remarks.focusedProperty();
			
			remarkFocusProperty.addListener(
					(observableValue, oldValue, newValue) ->{
						if(remarkFocusProperty.getValue()==false && remarks.getText() !=null) {
							if(oldValue != newValue) {
								ParametersToDB.updateRemarks(parameter, remarks.getText());
							}
						}
					}
			);
			
			HBox paramBox = new HBox();
			paramBox.getChildren().addAll(parameterLabel, weightField,pRegion, remarks);
			paramBox.setAlignment(Pos.CENTER);
			paramBox.setPadding(new Insets(10));
			HBox.setHgrow(pRegion, Priority.ALWAYS);
			
			parameterDisplay.add(paramBox);
		}
		
		VBox contentBox = new VBox();
		contentBox.getChildren().addAll(parameterDisplay);
		contentBox.setStyle("-fx-background-color: lightblue; -fx-background-image: url("+resource+")");
		ScrollPane scrollPane = new ScrollPane(contentBox);
		scrollPane.setStyle("-fx-background-color: lightblue; -fx-background-image: url("+resource+")");
		VBox box = new VBox();
		box.getChildren().addAll(titleBox);
		box.getChildren().add(scrollPane);
		
=======
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
>>>>>>> c4ec6868ebdfe60637c3376f198876e01b428747
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
<<<<<<< HEAD
		paramTitle.setAlignment(Pos.CENTER_LEFT);
		
		
		Label weightTitle = ProjectLables.labelForTitles("Critical Value");
=======
		paramTitle.setAlignment(Pos.CENTER);
		
		
		Label weightTitle = ProjectLables.labelForTitles("Weight");
>>>>>>> c4ec6868ebdfe60637c3376f198876e01b428747
		weightTitle.setMinWidth(150);
		weightTitle.setMaxWidth(150);
		weightTitle.setAlignment(Pos.CENTER);
		
<<<<<<< HEAD
		Region region = new Region();
		region.setPrefWidth(50);
		
		
=======
>>>>>>> c4ec6868ebdfe60637c3376f198876e01b428747
		Label remarkTitle = ProjectLables.labelForTitles("Remarks");
		remarkTitle.setMinWidth(150);
		remarkTitle.setMaxWidth(150);
		remarkTitle.setAlignment(Pos.CENTER);
		
		HBox titleBox = new HBox();
<<<<<<< HEAD
		HBox.setHgrow(region, Priority.ALWAYS);
		titleBox.getChildren().addAll(paramTitle, weightTitle,region, remarkTitle);
=======
		titleBox.getChildren().addAll(paramTitle, weightTitle, remarkTitle);
>>>>>>> c4ec6868ebdfe60637c3376f198876e01b428747
		titleBox.setPadding(new Insets(10));
		return titleBox;
	}
}
