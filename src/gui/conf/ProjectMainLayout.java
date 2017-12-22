package gui.conf;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * ProjectMainLayout class is main Layout. This class contains a default project title and center area changes as per calling class.
 */
public class ProjectMainLayout {
    static BorderPane pane;
    public static BorderPane mainPane(){
        if(pane == null){
            init();
        }
        VBox vBox = ProjectLables.getLabel();
        vBox.setEffect(ProjectEffects.dropShadow(Color.CADETBLUE));
        pane.setTop(vBox);
        pane.setPadding(new Insets(0, 10, 0, 0));
        
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource("b3.jpg");
        pane.setStyle("-fx-background-color: lightblue;-fx-background-image: url("+url+")");
        return pane;
    }

    /**
     * init is the class to intialize the borderpane whenever it is called first time at project statup.
     */
    private static void init(){
        pane = new BorderPane();
    }
}
