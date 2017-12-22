package gui;

import AddEntriesToDB.TableCreation;
import AddEntriesToDB.authenticate.UserAuth;
import globals.InitializeGlobals;
import gui.conf.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Created by ajay on 4/22/2016.
 */
public class LaunchApp extends Application {
    String subLevelTitle = "Welcome";
    public static Stage mainStage;
    @Override
    public void start(Stage stage) throws Exception {
        /*** set effects, labels and layouts. ***/
        mainStage = stage;
        DropShadow shadow = ProjectEffects.dropShadow(ProjectFontColors.dropShadowColor());
        ProjectLables.stringForSubLabel.setValue(subLevelTitle);
        BorderPane pane = ProjectMainLayout.mainPane();

        /*** Toogle group and toggle buttons for login. ***/
        ToggleGroup loginToggleGroup = new ToggleGroup();

        ToggleButton adminLoginToggle = ProjectControls.createToggleButton("Admin Login", loginToggleGroup, 40d, 100d);
        adminLoginToggle.setOnAction(e -> {
                    VBox loginPane = LoginScreenForAdmin.loginScene();
                    LoginScreenForAdmin.setPreviousScreen((Pane) pane.getCenter());
                    LoginScreenForAdmin.setPreviousTitle(subLevelTitle);
                    pane.setCenter(loginPane);
                    BorderPane.setAlignment(loginPane, Pos.CENTER);
                }
        );

        ToggleButton userLoginToggle = ProjectControls.createToggleButton("User Login", loginToggleGroup, 40d, 100d);
        userLoginToggle.setOnAction(e -> {
            VBox userLoginPane = LoginScreenForUser.loginScene();
            LoginScreenForUser.previousScreen = pane.getCenter();
            pane.setCenter(userLoginPane);
            BorderPane.setAlignment(userLoginPane,Pos.CENTER);
        });
        // Region to adjust login toggle button distance as frame size will increase or decrease.
        Region region = new Region();
        region.setPrefWidth(50);

        /*** Layout Toggle Button ***/
        HBox toggleBox = new HBox(20,adminLoginToggle,region,userLoginToggle);
        toggleBox.setEffect(shadow);
        toggleBox.setPadding(new Insets(10));
        toggleBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(region, Priority.ALWAYS);

        /*** Center work area ***/
        pane.setCenter(toggleBox);
        BorderPane.setAlignment(toggleBox, Pos.CENTER);

        /*** scence creation and stage setting ***/
        Scene scene = new Scene(pane,450,360);
        stage.setScene(scene);
        stage.setTitle("Data Mining App.");
        stage.show();
    }
    public static void main(String [] args){
        InitializeGlobals.init();
        //TableCreation.init();
        launch(args);
    }
}
