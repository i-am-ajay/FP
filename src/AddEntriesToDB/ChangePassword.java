package AddEntriesToDB;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;

import email.EmailSender;
import globals.SenderEmailConf;
import gui.conf.message.InfoMessage;
import retrieval.GeneratePassword;
import static generatehash.ConvertToHash.convertToHash;

public class ChangePassword{
	public static Connection con;
	public ChangePassword(){
        con = DBConnection.getConnection();
    }
    public void changePassword(String user){
        CallableStatement callback = null;
        GeneratePassword passowrd = new GeneratePassword();
        String newPassword = passowrd.generatePassowrd();
        try {
            callback = con.prepareCall("call change_password (?,?,?,?)");
            callback.setString(1, user);
            callback.setString(2, convertToHash(newPassword));
            callback.registerOutParameter(3, Types.BOOLEAN);
            callback.registerOutParameter(4, Types.VARCHAR);
            callback.executeUpdate();
            //System.out.println("98398379483"+numOfResultSets);
            boolean flag = callback.getBoolean(3);
            String email = callback.getString(4).trim();
            //resultSet = callback.getResultSet();
            //keys = getKeys(resultSet);
            String passwordMessage = "Your New Password : "+newPassword;
            if(flag == true){
            	EmailSender sender = new EmailSender();
            	Message msg = sender.createMessage(passwordMessage, email);
            	if(sender.sendMail(msg)){
            		new InfoMessage().messageBox("Password Sent to your registered mail.");
            	}
            }
        }
        catch(MessagingException|SQLException ex){
        	ex.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
        