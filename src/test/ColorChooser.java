package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by ajay on 6/6/2016.
 */
public class ColorChooser extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label();
        ColorPicker picker = new ColorPicker();
        HBox box = new HBox(picker);
        Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String [] args){
        launch(args);
    }
}
