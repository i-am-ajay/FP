package gui.conf;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * Created by ajay on 4/24/2016.
 */
public class ProjectFonts {
    public static Font buttonFont(){
        return Font.font("Cambria", FontWeight.BOLD,12);
    }
    public static Font mainLabelFont(){
        return Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 20);
    }
    public static Font subLabelFont(){
        return Font.font("Blackadder ITC",FontPosture.REGULAR,20);
    }
    public static Font fieldsLabelFont(){
        return Font.font("Brush Script MT Italic",20);
    }
    public static Font checkBoxFont(){
        return Font.font("Calibri Light",12);
    }
    public static Font msgFont(){
    	return Font.font("Calibri", 12);
    }
}
