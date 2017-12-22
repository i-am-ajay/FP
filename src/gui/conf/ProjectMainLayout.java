package gui.conf;

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
        String path = "file:///G:/projects/meenakshi%20ma\\'am/files/images/b3.jpg";
        pane.setStyle("-fx-background-color: lightblue;-fx-background-image: url("+path+")");
        return pane;
    }

    /**
     * init is the class to intialize the borderpane whenever it is called first time at project statup.
     */
    private static void init(){
        pane = new BorderPane();
    }
}
