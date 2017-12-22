package gui;

import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaa8664 on 6/2/16.
 */
public class PopulateClass extends Task<RuleClass> {
    List<RuleClass> list = new ArrayList<>();
    @Override
    protected RuleClass call() throws Exception {
        init();
        for(int i = 0;i<list.size();i++) {
            Thread.sleep(1000);
            DataTable.insertToList(list.get(i));
        }
        return null;
    }
    public void init(){
        list.add(new RuleClass("Hello"));
        list.add(new RuleClass("Good"));
        list.add(new RuleClass("Nice"));
        list.add(new RuleClass("Conjuring"));
        list.add(new RuleClass("World"));
        list.add(new RuleClass("Perfect"));
    }
}
