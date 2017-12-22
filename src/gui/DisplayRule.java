package gui;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import AddEntriesToDB.GetRuleFromDB;
import gui.conf.ProjectLables;
import gui.conf.ProjectMainLayout;
import gui.conf.SearchRules;
import javafx.geometry.Insets;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

// display rules form the database in a table.
public class DisplayRule {
	public void displayRulesScreen(GetRuleFromDB getRuleFromDB ){
		TableView rulesTable = DataTable.createTable(30,400);
		String title = "Rules";
		ProjectLables.stringForSubLabel.setValue(title);
		
		BorderPane pane = new BorderPane();
		pane.setCenter(rulesTable);
        pane.setPadding(new Insets(5));
        SearchRules searchRules = new SearchRules(getRuleFromDB);
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.submit(searchRules);
		
        ProjectMainLayout.mainPane().setCenter(pane);
		
	}
}
