package test;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * Created by ajay on 6/1/2016.
 */
public class FontTester extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Label fontType = new Label("This is just a font test.");
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(3);
        shadow.setOffsetX(3);
        shadow.setColor(Color.GRAY);

        Label label = new Label("Font Tester");
        label.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 20));
        label.setTextFill(Color.BLACK);

        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(Font.getFontNames());

        ObjectProperty<Font> fontProperty = new SimpleObjectProperty<>();
        fontProperty.setValue(Font.font("Tahoma",FontWeight.BOLD,FontPosture.REGULAR,20));
        ListView view = new ListView();
        view.getItems().addAll(list);
        view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        view.getSelectionModel().selectedItemProperty().addListener(e -> {
                String str = (String) view.getSelectionModel().getSelectedItem();
                System.out.println(str);
                StringConverter<Font> converter = new StringConverter<Font>() {
                    @Override
                    public String toString(Font font) {
                        return font.getFamily();
                    }

                    @Override
                    public Font fromString(String string) {
                        return Font.font(string, FontWeight.BOLD, FontPosture.REGULAR, 20);
                    }
                };

                fontProperty.setValue(converter.fromString(str));
            });
        /*view.setOnMouseClicked(e -> {
            String fontName = (String) view.getSelectionModel().getSelectedItem();


        });*/

        //fontProperty.bind(view.getSelectionModel().selectedItemProperty());
        fontType.fontProperty().bind(fontProperty);

        addContextMenuForList(view);
        fontType.setWrapText(true);
        fontType.setTextFill(Color.BLUE);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(view, fontType);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));

        VBox box = new VBox(10,label,hBox);
        box.setPadding(new Insets(10));
        box.setAlignment(Pos.TOP_CENTER);
        box.setEffect(shadow);
        box.setStyle("-fx-background-color: palegreen");
        Scene scene = new Scene(box,600,500);
        stage.setScene(scene);
        stage.show();
    }

    private void addContextMenuForList(ListView view) {
        ContextMenu menu = new ContextMenu();

        MenuItem item = new MenuItem();
        item.setText("Copy");
        item.setAccelerator(KeyCombination.keyCombination("Ctrl+c"));
        item.setOnAction(e -> {
            System.out.println(view.getSelectionModel().getSelectedItem().toString());
        });
        menu.getItems().add(item);
        view.setContextMenu(menu);
    }

    public static void main(String [] args){
        launch(args);
    }
}
