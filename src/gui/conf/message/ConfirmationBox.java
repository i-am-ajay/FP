package gui.conf.message;

import gui.conf.ProjectControls;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by ajay on 6/5/2016.
 */
public class ConfirmationBox extends MessageBox {
    private Button okButton;
    private Button cancelButton;
    @Override
    public void messageBox(String user){
        super.messageBox("Are you sure you want to create ".concat(user).concat("?"));
        //String msg = "Are you sure you want to create ".concat(user).concat("?");

        okButton = ProjectControls.createButton("OK");
        okButton.setDefaultButton(true);
        okButton.setPrefWidth(70);

        cancelButton = ProjectControls.createButton("Cancle");
        cancelButton.setCancelButton(true);
        cancelButton.setPrefWidth(70);
        cancelButton.setOnAction(cancelEvent -> {
            stage.close();
        });
        Region region = new Region();
        region.setPrefWidth(30);

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(okButton, region, cancelButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(buttonBox, Priority.ALWAYS);

        box.getChildren().add(buttonBox);
        box.setPadding(new Insets(10));
        box.setMaxWidth(400);


    }
    public Button getOkButton(){
        return okButton;
    }
}
