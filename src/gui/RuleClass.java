package gui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by gaa8664 on 6/2/16.
 */
public class RuleClass {
    static int count = 0;
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty rule = new SimpleStringProperty();

    private RuleClass(int id, String rule){
        count += 1;
        setId(count);
        setRule(rule);
    }
    public RuleClass(String rule){
        this(0,rule);
    }
    public IntegerProperty idProperty(){
        return id;
    }
    public StringProperty ruleProperty(){
        return rule;
    }

    public void setId(int i){
        id.set(i);
    }
    public Integer getId(){
        return id.get();
    }

    public void setRule(String rule){
        this.rule.set(rule);
    }
    public String getRule(){
        return rule.get();
    }
}
