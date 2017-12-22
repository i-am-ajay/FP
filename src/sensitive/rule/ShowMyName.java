package sensitive.rule;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * Created by ajay on 2/7/2016.
 */
public class ShowMyName extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Reflection ref = new Reflection();
        ref.setFraction(2);
        ref.setTopOffset(1);
        ref.setTopOpacity(2);

        Text text = new Text(50,50, "Shikha Chandel");
        text.setFill(Color.RED);
        text.setFont(Font.font("Gabriola", FontWeight.BOLD, FontPosture.ITALIC, 40));
        text.setEffect(ref);

        Group root = new Group();
        root.getChildren().addAll(text);

        Scene scene = new Scene(root,500,200,Color.LIGHTBLUE);
        primaryStage.setScene(scene);
        primaryStage.show();



    }
    public static void main(String [] args){
        launch(args);
    }
}
