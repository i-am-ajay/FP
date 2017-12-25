package gui;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import AddEntriesToDB.GetRuleFromDB;
import gui.conf.ProjectControls;
import gui.conf.ProjectLables;
import gui.conf.ProjectMainLayout;
import gui.conf.SearchRules;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
		
        /*** Back button ***/
        Button back = ProjectControls.createButton("Back");
        //back.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        back.setMaxWidth(150);
        back.setAlignment(Pos.CENTER);
        //back.setAlignment(Pos.CENTER_RIGHT);
        back.setOnAction( e->{
        	service.shutdownNow();
            ProjectMainLayout.mainPane().setCenter(UserScreen.masterNode);
            ProjectLables.stringForSubLabel.setValue(UserScreen.subLevelTitle);
        });
        
        pane.setBottom(back);
        BorderPane.setAlignment(back, Pos.CENTER);
        
        ProjectMainLayout.mainPane().setCenter(pane);
		
	}
}
