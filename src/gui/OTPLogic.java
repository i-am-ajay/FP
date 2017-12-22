package gui;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Message;
import javax.mail.MessagingException;

import AddEntriesToDB.GetRuleFromDB;
import email.EmailSender;
import generators.CreateOTP;
import globals.SenderEmailConf;
import gui.conf.ProjectControls;
import gui.conf.ProjectLables;
import gui.conf.ProjectMainLayout;
import gui.conf.SearchRules;
import gui.conf.message.InfoMessage;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OTPLogic {
	// Previous screen
	private Node previousScreenNode;
	GetRuleFromDB getRuleFromDB;
	private String user;
	private String otp;
	private String userKey;
	private Random rand;
	private TextField otpTextField;
	private BorderPane masterNode;
	private EmailSender mailSender;
	private long otpGenerationTime;
	Label otpInstructionMsg;
	SimpleStringProperty stringProp = new SimpleStringProperty();
	
	int [] otpIndex = new int[2];
	int keyRange;
	
	// constructor
	public OTPLogic(String temp_user, String key, GetRuleFromDB ruleFromDB, long time){
		mailSender = new EmailSender();
		rand = new Random();
		userKey = key;
		user = temp_user;
		getRuleFromDB = ruleFromDB;
		otpGenerationTime = time;
	}
	
	// crate an OTP
	public String generateOTP(){
		otp = CreateOTP.getOTP();
		try{
			mailOTPToUser(otp);
		}
		catch(MessagingException ex){
			ex.printStackTrace();
		}	
		otpIndex[0] = rand.nextInt(6);
		while(true){
			otpIndex[1] = rand.nextInt(6);
			if (otpIndex[0] != otpIndex[1]){
				break;
			}
		}
		keyRange = rand.nextInt(userKey.length()-3);
		String userMessage = "Type "+(otpIndex[0]+1) + " and "+(otpIndex[1]+1)+" number character from your OTP. And "
							+(keyRange+1)+"-"+((keyRange+1)+3)+" number characters from your key.";
		return userMessage;
	}
	
	// create final otp using combination of OTP chars and key chars.
	public String finalOTP(){
		StringBuilder finalOTP = new StringBuilder();
		finalOTP.append(otp.charAt(otpIndex[0]));
		finalOTP.append(otp.charAt(otpIndex[1]));
		
		finalOTP.append(userKey.substring(keyRange, keyRange+4));
		return finalOTP.toString();
	}
	
	// send otp on users registered mail.
	public void mailOTPToUser(String otp) throws MessagingException{
		String msg = "Hello User,\n\n Your OTP is "+ otp +". This will be valid only for 5 mins.";
		Message otpMsg = mailSender.createMessage(msg, SenderEmailConf.email);
		mailSender.sendMail(otpMsg);
		
	}
	
	private void otpAction(){
		String sysOTP = finalOTP();
   	 	String userOTP = otpTextField.getText();
   	 	long otpSubmissionTime = System.currentTimeMillis();
   	 	long timeSpan = otpSubmissionTime - otpGenerationTime;
   	 	if(timeSpan <= 300000){
	    	 if (compareOTP(sysOTP, userOTP)){
	                /*pane.setCenter(rulesTable);
	                pane.setPadding(new Insets(5));
	                SearchRules searchRules = new SearchRules(getRuleFromDB);
	                ExecutorService service = Executors.newFixedThreadPool(1);
	                service.submit(searchRules);*/
	    		 DisplayRule dr = new DisplayRule();
	    		 dr.displayRulesScreen(getRuleFromDB);
	    	 }
           else{
               new InfoMessage().messageBox("Wrong OTP combination.");
               otpTextField.clear();
               otpTextField.requestFocus();
           }
   	 	}
   	 	else{
   	 		new InfoMessage().messageBox("OTP Expired.");
   	 		otpTextField.clear();
   	 		otpTextField.requestFocus();
   	 	}
	}
	
	private boolean compareOTP(String sysOTP, String userOTP) {
		boolean compareFlag = false;
		int sysOTPHash = sysOTP.hashCode();
		int userOTPHash = userOTP.hashCode();
		if(sysOTPHash == userOTPHash){
			compareFlag = true;
		}
		// TODO Auto-generated method stub
		return compareFlag;
	}
	/**
	 * Screen that will appear to enter OTP after entering key for rule.
	 * @param node : Pass calling class node, so this node can be set on back button use.
	 */
	public void otpScreen(){
		TableView rulesTable = DataTable.createTable(30,400);
		String title = "Enter OTP";
		ProjectLables.stringForSubLabel.setValue(title);
		
		BorderPane pane = new BorderPane();
		 Label otpLabel = ProjectLables.labelForFields("Enter OTP");
	     otpTextField = new TextField();
	     otpTextField.setPrefWidth(200);
	     otpLabel.setLabelFor(otpTextField);

	     HBox fieldBox = new HBox(10, otpLabel, otpTextField);
	     fieldBox.setAlignment(Pos.CENTER);
	     fieldBox.setPadding(new Insets(10));
	     
	     // action on submit button
	     Button submitButton = ProjectControls.createButton("Submit OTP");
	     submitButton.setOnAction(e ->{
	    	 otpAction();
	     });
	     submitButton.setMaxWidth(150);
	     submitButton.setPrefWidth(150);
	     
	     // action on regenerate button
	     Button regenrateOTP = ProjectControls.createButton("Regenrate");
	     regenrateOTP.setOnAction(e ->{
	    	 otpGenerationTime = System.currentTimeMillis();
	    	 stringProp.set(generateOTP());
	    	 otpInstructionMsg = ProjectLables.message(stringProp.get());
	    	 otpInstructionMsg.textProperty().bind(stringProp);
	    	 //otpAction();
	     });
	     regenrateOTP.setMaxWidth(150);
	     regenrateOTP.setPrefWidth(150);
	     
	     // action on back button
	     Button backButton = ProjectControls.createButton("Back");
	     backButton.setOnAction(e->{
	    	 UserScreen.getUserScreen();
	     });
	     backButton.setMaxWidth(150);
	     backButton.setPrefWidth(150);
	     
	     // button placment.
	     HBox buttonBox = new HBox(10,submitButton,regenrateOTP,backButton);
	     buttonBox.setAlignment(Pos.CENTER);
	     buttonBox.setPadding(new Insets(5));
	     
	     // fields and button placment in the scene.
	     VBox centerContainer = new VBox(10, fieldBox, buttonBox);
	     centerContainer.setAlignment(Pos.CENTER);
	     
	     pane.setCenter(centerContainer);
	     BorderPane.setAlignment(centerContainer, Pos.CENTER);
	     
	     // OTP msg
	     stringProp.set(this.generateOTP());
	     otpInstructionMsg = ProjectLables.message(stringProp.get());
	     otpInstructionMsg.textProperty().bind(stringProp);
	     otpInstructionMsg.setWrapText(true);
	     pane.setBottom(otpInstructionMsg);
	     BorderPane.setAlignment(otpInstructionMsg, Pos.TOP_CENTER);
	     BorderPane.setMargin(otpInstructionMsg, new Insets(10));
	     masterNode = pane;
	     // screen placement on main project frame.
	     ProjectMainLayout.mainPane().setCenter(pane);
	     System.out.println(this.finalOTP());
	}
	
}
