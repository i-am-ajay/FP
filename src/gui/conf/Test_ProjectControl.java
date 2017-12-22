package gui.conf;

import gui.conf.ProjectLables;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by ajay on 4/19/2016.
 */
public class Test_ProjectControl extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VBox vbox = ProjectLables.getLabel();
        vbox.setPadding(new Insets(0,3,10,3));

        Scene scene = new Scene(vbox,500,150);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String [] args){
        launch(args);
    }
}
