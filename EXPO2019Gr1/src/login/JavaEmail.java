package login;

import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class JavaEmail{
	
    Session mailSession;
 
    public static void send(String receiver, String pin) throws AddressException, MessagingException
    {
        JavaEmail javaEmail = new JavaEmail();
        javaEmail.setMailServerProperties();
        javaEmail.draftEmailMessage(receiver,pin);
        javaEmail.sendEmail(receiver,pin);
    }
 
    private void setMailServerProperties()
    {
        Properties emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", "587");
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        mailSession = Session.getDefaultInstance(emailProperties, null);
    }
 
    private MimeMessage draftEmailMessage(String receiver, String pin) throws AddressException, MessagingException
    {
        String to = receiver;
        String emailSubject = "Pinkode";
        String emailBody = "Din pinkode er: " + pin +" \n\n Hilsen EXPO";
        MimeMessage emailMessage = new MimeMessage(mailSession);
     
        emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        emailMessage.setSubject(emailSubject);
        emailMessage.setText(emailBody);
        return emailMessage;
    }
 
    private void sendEmail(String receiver, String pin) throws AddressException, MessagingException
    {
        /**
         * Sender's credentials
         * */
        String fromUser = "noreplyexpodemo@gmail.com";
        String fromUserEmailPassword = "Expo2019";
 
        String emailHost = "smtp.gmail.com";
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserEmailPassword);
        /**
         * Draft the message
         * */
        MimeMessage emailMessage = draftEmailMessage(receiver,pin);
        /**
         * Send the mail
         * */
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
    }
}