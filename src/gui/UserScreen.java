package gui;

import AddEntriesToDB.GetKeysUsingMaster;
import AddEntriesToDB.GetRuleFromDB;
import email.EmailSender;
import globals.SenderEmailConf;
import gui.conf.ProjectControls;
import gui.conf.ProjectFonts;
import gui.conf.ProjectLables;
import gui.conf.ProjectMainLayout;
import gui.conf.SearchRules;
import gui.conf.message.InfoMessage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Message;
import javax.mail.MessagingException;

/**
 * Created by ajay on 6/12/2016.
 */
public class UserScreen {
    static String subLevelTitle;
    private static TextField keyField;
    static Node masterNode;

    public static void createUserScreen(String userName){
        TableView rulesTable = DataTable.createTable(30,400);
        subLevelTitle = "User Screen";
        ProjectLables.stringForSubLabel.setValue(subLevelTitle);
        BorderPane pane = new BorderPane();

        Label keyLabel = ProjectLables.labelForFields("Key");
        keyField = new TextField();
        keyField.setPrefWidth(200);
        keyLabel.setLabelFor(keyField);

        HBox fieldBox = new HBox(10,keyLabel,keyField);
        fieldBox.setAlignment(Pos.CENTER);
        fieldBox.setPadding(new Insets(10));
        
        Button getRules = ProjectControls.createButton("Get Rules");
        // A 6 char OTP will be generated, and sent on user registered email. Control will move to OTP screen.
        getRules.setOnAction(e->{
        	GetRuleFromDB ruleFromDB = new GetRuleFromDB();
            boolean flag = ruleFromDB.readRuleQuery(userName,keyField.getText(),"");
            if(flag){
            	OTPLogic otpLogic = new OTPLogic(userName, keyField.getText(), ruleFromDB,System.currentTimeMillis());
            	otpLogic.otpScreen();
        	/*
            GetRuleFromDB ruleFromDB = new GetRuleFromDB();
            boolean flag = ruleFromDB.readRuleQuery(userName,keyField.getText(),"");
            if(flag) {
                pane.setCenter(rulesTable);
                pane.setPadding(new Insets(5));
                SearchRules searchRules = new SearchRules(ruleFromDB);
                ExecutorService service = Executors.newFixedThreadPool(1);
                service.submit(searchRules); */
            }
            else{
                new InfoMessage().messageBox("Key is not valid");
                keyField.clear();
                keyField.requestFocus();
            }
        });
        
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource("b3.jpg");
        getRules.setMaxWidth(150);
        getRules.setPrefWidth(150);
        //getRules.setWrapText(true);
        Button requestKey = ProjectControls.createButton("Request Key");
        requestKey.setMaxWidth(150);
        requestKey.setPrefWidth(150);
        Tooltip tooltip = new Tooltip();
        tooltip.setText("Enter Master Key to request your keys.");
        tooltip.setWrapText(true);
        Font tooltipFont = ProjectFonts.tooltipFont();
        tooltip.setFont(tooltipFont);
        tooltip.setStyle("-fx-background-image:url("+url+"); -fx-color:black;");
        requestKey.setTooltip(tooltip);
        
        requestKey.setOnAction(
                e -> {
                	if(keyField.getText() == null || keyField.getText().equals("")) {
                		new InfoMessage().messageBox("Enter Your Master key.");
                		keyField.requestFocus();
                	}
                	else {
	                    boolean flag = getKeyUsingMasterKey(userName,keyField.getText());
	                    if(flag){
	                        new InfoMessage().messageBox("Keys has been sent to registered email.");
	                        keyField.clear();
	                        keyField.requestFocus();
	                    }
	                    else{
	                        new InfoMessage().messageBox("Master key is not correct.");
	                        keyField.clear();
	                        keyField.requestFocus();
	                    }
                	}
                }

        );

        //requestNewKey.setWrapText(true);
        Button back = ProjectControls.createButton("Back");
        back.setOnAction(e->{
           ProjectMainLayout.mainPane().setCenter(LoginScreenForUser.getMainScene());
        });
        back.setMaxWidth(150);
        back.setPrefWidth(150);
        //back.setWrapText(true);
       
        
        HBox buttonBox = new HBox(10,requestKey,getRules,back);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(5));
        
        pane.setCenter(fieldBox);
        pane.setBottom(buttonBox);
        BorderPane.setAlignment(fieldBox, Pos.CENTER);
        masterNode = pane;
        ProjectMainLayout.mainPane().setCenter(pane);

    }

    public static boolean getKeyUsingMasterKey(String user, String masterKey){
        GetKeysUsingMaster gkum = new GetKeysUsingMaster();
        EmailSender sender = new EmailSender();
        List<String> keys = gkum.getKeyUsingMaster(user,masterKey);
        StringBuilder builder = new StringBuilder();
        for(String key : keys) {
            builder.append(key);
            builder.append(',');
        }
        builder.deleteCharAt(builder.length()-1);
        String keyString = builder.toString();
        String msg = "Hello User,\n\n\tYour Keys are " + keyString + ". \n\nWarm Regards.";
        Message mailMsg = null;
		try {
			mailMsg = sender.createMessage(msg, SenderEmailConf.email);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return sender.sendMail(mailMsg);
    }
    
    public static void getUserScreen(){
    	ProjectLables.stringForSubLabel.setValue(subLevelTitle);
    	ProjectMainLayout.mainPane().setCenter(masterNode);
    }
}
