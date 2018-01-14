package gui.conf.message;

import java.sql.SQLException;

import AddEntriesToDB.cleaners.ClearTables;
import gui.conf.ProjectControls;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class DeletionConfirmation extends MessageBox{
	private Button okButton;
    private Button cancelButton;
    @Override
    public void messageBox(String user){
        super.messageBox("Are you sure you want to clear tables? ");
        //String msg = "Are you sure you want to create ".concat(user).concat("?");

        okButton = ProjectControls.createButton("OK");
        okButton.setDefaultButton(true);
        okButton.setPrefWidth(70);
        okButton.setOnAction(
        		e -> {
        			stage.close();
        			try {
                        new ClearTables().removeUserInfoFromTables();
                        new InfoMessage().messageBox("Tables Cleared.");
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
        		}
        );

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
}
