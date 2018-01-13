package gui.conf;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;

/**
 * Created by ajay on 4/19/2016.
 */
public class ProjectLables {

    public static SimpleStringProperty stringForSubLabel = new SimpleStringProperty();

    /**
     * Creates mainLabel, this method will create the main label of the project.
     *
     * @return VBox
     */
    public static VBox getLabel() {
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
    	URL url = loader.getResource("financial.jpg");
        String labelName = "Datamining For Financial Datasets";
        ImageView logo = new ImageView(new Image(url.toString()));
        logo.setFitHeight(100);
        logo.setFitWidth(150);
        Label label = new Label(labelName);
        label.setFont(ProjectFonts.mainLabelFont());
        label.setTextFill(ProjectFontColors.mainLabelFontColor());
        label.setGraphic(logo);
        label.setGraphicTextGap(20);
        label.setWrapText(true);
        label.setUnderline(true);

        Label subLabel = new Label();
        subLabel.textProperty().bind(stringForSubLabel);
        subLabel.setFont(ProjectFonts.subLabelFont());

        VBox box = new VBox(20, label, subLabel);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    public static DoubleProperty labelWidthProperty = new SimpleDoubleProperty(0);
    static Label label;

    /**
     * Create label. This method creates label for all the fields in the project, we just need to pass the content of the
     * label.
     *
     * @param name
     * @return
     */
    public static Label labelForFields(String name) {
        label = new Label(name);
        label.setFont(ProjectFonts.fieldsLabelFont());
        label.setTextFill(ProjectFontColors.fieldLabelColors());
        //System.out.println(label.getWidth());
       /* DoubleBinding binding = new DoubleBinding(){
            {
                super.bind(label.widthProperty());
            }
            @Override
            protected double computeValue() {
                double labelwidth = label.getWidth();
                System.out.println(labelwidth);
                if(labelwidth > labelWidthProperty.getValue()){
                    labelWidthProperty.setValue(labelwidth);
                }
                return labelWidthProperty.getValue();
            }
        };*/
        return label;
    }
    
    public static Label labelForTitles(String name) {
        label = new Label(name);
        label.setFont(ProjectFonts.subLabelFont());
        label.setTextFill(ProjectFontColors.mainLabelFontColor());
        //System.out.println(label.getWidth());
        return label;
    }
    public static Label createLabel(String labelContent) {
        Label label = new Label(labelContent);
        label.setFont(ProjectFonts.subLabelFont());
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);
        return label;
    }
    
    public static Label message(String msg){
    	Label label = new Label(msg);
    	label.setFont(ProjectFonts.msgFont());
    	return label;
    }
}