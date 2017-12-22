package gui.conf.message;

import java.net.URL;

import gui.conf.ProjectLables;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by ajay on 6/10/2016.
 */
public abstract class MessageBox {
    Label label;
    VBox box;
    protected Stage stage;
    public void messageBox(String msg){
        label = ProjectLables.createLabel(msg);
        
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL path = loader.getResource("b3.jpg");

        box = new VBox(10);
        box.getChildren().add(label);
        box.setAlignment(Pos.CENTER);
       // String path = "file:///G:/projects/meenakshi%20ma\\'am/files/images/b3.jpg";
        box.setStyle("-fx-background-color: lightblue;-fx-background-image: url(" + path + ")");
        box.setPadding(new Insets(10));
        box.setMaxWidth(400);
        box.setPrefHeight(100);

        Scene scene = new Scene(box);

        stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> {
            e.consume();
        });
        stage.setTitle("Confirmation Box");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }
}
