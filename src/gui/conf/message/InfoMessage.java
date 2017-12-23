package gui.conf.message;

import gui.CreateUser;
import javafx.geometry.Insets;
import javafx.scene.control.Button;

/**
 * Created by ajay on 6/10/2016.
 */
public class InfoMessage extends MessageBox {
    @Override
    public void messageBox(String msg){
        super.messageBox(msg);
        Button button = new Button("OK");
        button.setOnAction( e ->{
            stage.close();
            CreateUser.reset();
        });
        box.getChildren().add(button);
        box.setPrefWidth(400);
        box.setPadding(new Insets(10));
    }
    
    public void messageBoxWithoutReset(String msg){
    	super.messageBox(msg);
    	Button button = new Button("OK");
    	button.setOnAction(
    		e -> {
    			stage.close();
    		}
    	);
    	box.getChildren().add(button);
    	box.setPrefWidth(400);
    	box.setPadding(new Insets(10));
    }
    public void setScreen(){

    }
}
