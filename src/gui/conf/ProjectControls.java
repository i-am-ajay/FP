package gui.conf;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * Created by ajay on 4/22/2016.
 */
public class ProjectControls {
    /**
     * Method to create Button. Will take the following parameter and will return a button control.
     * @param title
     * @param prefHeight
     * @param prefWidth
     * @return Button
     */
    public static Button createButton(String title,double prefHeight,double prefWidth){
        Button button = new Button(title);
        button.setPrefWidth(prefWidth);
        button.setPrefHeight(prefHeight);
        return button;
    }

    /** Method to create toggle button. Will take the following parameter and will return the toggle button control.
     *
     * @param title
     * @param group
     * @param prefHeight
     * @param prefWidth
     * @return ToggleButton
     */
    public static ToggleButton createToggleButton(String title,ToggleGroup group,Double prefHeight,Double prefWidth){
        ToggleButton toggleButton = new ToggleButton(title);
        toggleButton.setFont(ProjectFonts.buttonFont());
        toggleButton.setTextFill(ProjectFontColors.buttonFontColor());
        toggleButton.setPrefWidth(prefWidth);
        toggleButton.setPrefHeight(prefHeight);
        toggleButton.setToggleGroup(group);
        return toggleButton;
    }
    public static Button createButton(String title){
        Button button = new Button(title);
        button.setFont(ProjectFonts.buttonFont());
        button.setTextFill(ProjectFontColors.buttonFontColor());
        return button;
    }
    public static CheckBox createCheckBox(String title){
        CheckBox box = new CheckBox(title);
        box.setFont(ProjectFonts.checkBoxFont());
        box.setTextFill(ProjectFontColors.checkBoxFontColors());
        return box;
    }
}
