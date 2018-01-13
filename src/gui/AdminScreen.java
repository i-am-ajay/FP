package gui;

import AddEntriesToDB.cleaners.ClearTables;
import gui.conf.ProjectControls;
import gui.conf.ProjectLables;
import gui.conf.ProjectMainLayout;
import gui.conf.message.InfoMessage;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.concurrent.Executors;

public class AdminScreen {
    volatile static BooleanProperty flag = new SimpleBooleanProperty();
    public static StringBinding binding;
    public static String subLevelTitle;
    static Button mineRules;
    static Button setParameters;
    static Button sensitiveRules;
    static Button createUser;
    static Button clearTables;
    static Button back;
    static Region region;
    static Pane previousScreen;
    private static String previousTitle;
    static StringProperty miningLabelString;
    public static HBox box;
    private static HBox rightPane;

    public static void compileAdminScreen() {

        subLevelTitle = "Admin Screen";
        ProjectLables.stringForSubLabel.setValue(subLevelTitle);

        flag.setValue(false);
        // string binding for label that will show if rules are being mined or mining done.
        binding = new StringBinding() {
            {
                super.bind(flag);
            }

            @Override
            protected String computeValue() {
                String string = null;
                if (flag.getValue() == true) {
                    string = "Done";
                } else {
                    string = "Mining Rules";
                }
                return string;
            }
        };

        mineRules = ProjectControls.createButton("Mine Rules");
        mineRules.setOnAction(e -> {
            try {
                Label labe = ruleMiningProcess();
                // fade transition for label.
                FadeTransition tran = AdminScreen.createFradTransition(labe);
                tran.play();
                // add lable to the right side of screen.
                rightPane.getChildren().add(labe);
                rightPane.setAlignment(Pos.TOP_CENTER);
                rightPane.setMaxWidth(300);
                rightPane.setPadding(new Insets(10));
                // run a new thread and start mining rules.
                RunTasks task = new RunTasks(Tasks.RULE_MINING);
                task.setOnSucceeded(et -> {
                    flag.setValue(true);
                    tran.stop();
                });
                Executors.newSingleThreadExecutor().submit(task);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        mineRules.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


        sensitiveRules = ProjectControls.createButton("Find Sensitive Rules");
        sensitiveRules.setWrapText(true);
        sensitiveRules.setOnAction(e -> {
            TableView view = DataTable.createTable(0,0);
            rightPane.getChildren().add(view);
            rightPane.setAlignment(Pos.TOP_CENTER);
            rightPane.setMaxWidth(300);
            rightPane.setPadding(new Insets(10));

            // run a new thread and find sensitive rules.
            RunTasks task = new RunTasks(Tasks.SENSITIVE_RULES);
            try {
                Executors.newSingleThreadExecutor().submit(task);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        sensitiveRules.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        setParameters = ProjectControls.createButton("Parameter");
        setParameters.setOnAction(
        		e ->{
        			ThreshholdValueScreen.threasholdTable();
        		}
        );
        setParameters.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        createUser = ProjectControls.createButton("Create User");
        createUser.setOnAction( e -> {
            CreateUser.createUserOrAdmin();
        });
        createUser.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //region = new Region();
        clearTables = ProjectControls.createButton("Clear Database");
        clearTables.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        clearTables.setOnAction(e->{
            try {
                new ClearTables().removeUserInfoFromTables();
                new InfoMessage().messageBox("Tables Cleared.");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        back = ProjectControls.createButton("Back");
        back.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        back.setOnAction(e -> {
            ProjectLables.stringForSubLabel.setValue(previousTitle);
            ProjectMainLayout.mainPane().setCenter(getPreviousScreen());
        });

        TilePane pane = new TilePane();
        pane.getChildren().addAll(mineRules, sensitiveRules,setParameters, createUser,clearTables,back);
        pane.setPrefColumns(1);
        pane.setPrefRows(6);
        pane.setHgap(5);
        pane.setVgap(3);
        pane.setOrientation(Orientation.VERTICAL);
        pane.setMaxWidth(150);
        pane.setPadding(new Insets(10));

        rightPane = new HBox();
        rightPane.setMinWidth(300);
        rightPane.setMaxWidth(300);

        box = new HBox();
        box.getChildren().addAll(pane, rightPane);
        ProjectMainLayout.mainPane().setCenter(box);
    }

    public static void setPreviousScreen(Pane previousScreen) {
        AdminScreen.previousScreen = previousScreen;
    }

    public static Pane getPreviousScreen() {
        return previousScreen;
    }

    public static void setPreviousTitle(String title) {
        previousTitle = title;
    }

    public static String getPreviousTitle() {
        return previousTitle;
    }

    public static Label ruleMiningProcess() {

        Reflection reflection = new Reflection();
        reflection.setFraction(0.8);
        reflection.setTopOpacity(.5);
        reflection.setBottomOpacity(.2);
        reflection.setTopOffset(5);


        Label miningLabel = new Label();
        //miningLabel.setText("Mining Rules");
        miningLabel.setFont(Font.font("Colonna MT", FontWeight.BOLD, FontPosture.ITALIC, 30));
        miningLabel.setWrapText(true);
        miningLabel.setTextFill(Color.BLUE);
        miningLabel.setEffect(reflection);

        miningLabelString = new SimpleStringProperty();
        miningLabelString.bind(binding);
        miningLabel.textProperty().bind(miningLabelString);


        return miningLabel;
    }

    static FadeTransition createFradTransition(Node node) {
        FadeTransition tran = new FadeTransition();
        tran.setFromValue(0.2);
        tran.setToValue(1.0);
        tran.setDuration(Duration.seconds(2));
        tran.setNode(node);
        tran.setAutoReverse(true);
        tran.setCycleCount(Animation.INDEFINITE);
        return tran;
    }
}
