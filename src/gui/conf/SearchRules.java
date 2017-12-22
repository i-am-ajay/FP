package gui.conf;

import AddEntriesToDB.GetRuleFromDB;
import javafx.concurrent.Task;

/**
 * Created by ajay on 6/12/2016.
 */
public class SearchRules extends Task<Boolean> {
    GetRuleFromDB ruleFromDB;
    public SearchRules(GetRuleFromDB object){
        ruleFromDB = object;
    }
    @Override
    protected Boolean call() throws Exception {
        ruleFromDB.readRules();
        return true;
    }
}
