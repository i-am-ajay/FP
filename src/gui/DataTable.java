package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DataTable {
    static TableView <RuleClass> view;
    static ObservableList<RuleClass> list;

    public static TableView createTable(double widthCol1,double widthCol2){
        if (widthCol1 == 0)
            widthCol1 = 30;
        if (widthCol2 == 0)
            widthCol2 = 250;
        view = new TableView<>();
        list = FXCollections.observableArrayList();
        TableColumn<RuleClass,Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<RuleClass, Integer>("id"));
        idCol.setPrefWidth(widthCol1);

        TableColumn <RuleClass,String> ruleCol = new TableColumn<>("Rule");
        ruleCol.setCellValueFactory(new PropertyValueFactory<RuleClass, String>("rule"));
        ruleCol.setPrefWidth(widthCol2);
        view.getColumns().addAll(idCol, ruleCol);
        String id = idCol.idProperty().getValue();
        view.setItems(list);
        return view;
    }
    public static void insertToList(RuleClass rule){
        list.add(rule);
    }

}
