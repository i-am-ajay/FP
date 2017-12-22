package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.concurrent.Executors;

/**
 * Created by gaa8664 on 6/2/16.
 */
public class UseDataTable extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label("Data Table");
        label.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR,20));
        label.setTextFill(Color.AQUA);

        TableView view = DataTable.createTable(0,0);
        PopulateClass pc = new PopulateClass();
        Executors.newSingleThreadExecutor().submit(pc);
        VBox box = new VBox();
        box.getChildren().addAll(label,view);
        box.setSpacing(10);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-background-color:palegreen");

        Scene scene = new Scene(box,300,400);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String [] args){
        launch(args);
    }
}
