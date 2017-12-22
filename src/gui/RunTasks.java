package gui;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;

import java.io.FileNotFoundException;

/**
 * Created by ajay on 5/29/2016.
 */
public class RunTasks extends Task<Boolean> {
    Tasks task;
    volatile static BooleanProperty flag = AdminScreen.flag;

    public RunTasks(Tasks task){
        this.task = task;
    }

    @Override
    protected Boolean call() throws Exception {
        if(task == Tasks.RULE_MINING) {
            try {
                fpgrowth.Main.main(null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if (task == Tasks.SENSITIVE_RULES) {
            sensitive.rule.FindRulesWeight.main(null);
            flag.setValue(true);
        }

        return true;
    }
}
