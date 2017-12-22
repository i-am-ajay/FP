package email;

import globals.SenderEmailConf;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by ajay on 14-Nov-16.
 */
public class EmailSender {
	Session session;
	public EmailSender(){
		session = getSession();
	}
	
    public boolean sendMail(Message msg) {
        try {
            //Message message = createMessage(mailSession, keys, mailId);
        	Message message =msg;
            Transport.send(message);
        }
        catch(MessagingException ex){
            ex.printStackTrace();
        }
        return true;
    }

    public Session  getSession(){
        Properties prop = new Properties();
        prop.setProperty("mail.smtp.auth","true");
        prop.setProperty("mail.smtp.starttls.enable","true");
        prop.setProperty("mail.smtp.host", SenderEmailConf.host);
        prop.setProperty("mail.smtp.port", SenderEmailConf.port);

        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(SenderEmailConf.senderEmail, SenderEmailConf.senderPassword);
            }
        });
        return session;
    }

    public Message createMessage(String mailMessage,String recipients) throws MessagingException {
        String to = "";
        Message message = new MimeMessage(session);
        // set sender and recipients of the message
        message.setFrom(new InternetAddress(SenderEmailConf.senderEmail));
        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));

        // create message.
        message.setSubject("Query Keys");
        message.setText(mailMessage);
        return message;
    }
    

    public static void main(String [] args){
        EmailSender sender = new EmailSender();
        //sender.sendMail("woueroiwur","ajaychandel.1987@gmail.com");
    }

}
