package Controller;

import MVC.Interface.ImessageForUser;
import MVC.Interface.Iencryption;
import Model.*;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.DataHandler;

public class SignInHandler implements Iencryption,ImessageForUser{

    private SQL statement;
    private Maths number;

    public SignInHandler() {
        statement = new SQL();
        number = new Maths();
    }

    public String signInManager(String error, User user, String code) {
        if (error.equals(NO)) {
            error = this.textAreasCompleted(user);
        }
        if (error.equals(NO)) {
            error = this.minimumSize(user.getPassword());
        }
        if (error.equals(NO)) {
            error = this.maximumSize(user.getPassword());
        }
        if (error.equals(NO)) {
            error = this.comparePasswords(user);
        }
        if (error.equals(NO)) {
            error = this.isEmail(user.getGmail());
        }
        if (error.equals(NO)) {
            error = this.gmailAndNicknameUsed(user);
        }
        if (error.equals(NO)) {
            error = this.gmailValidated(user.getGmail(), code);
        }
        if (error.equals(NO)) {
            statement.signInDB(user.getName(), user.getLastName(),
                    user.getNickName(), encryptedPassword(user.getPassword()), user.getGmail());
        }
        
        return error;
    }
    
    @Override
    public String encryptedPassword(String pass){
        String encryptedPass = Security.md5(pass);
        return encryptedPass;
    }

    public String minimumSize(String password) {
        if (password.length() < 6) {
            return MINIMUM_SIZE;
        }
        return NO;
    }
    
    public String maximumSize(String password) {
        if (password.length() > 15) {
            return MAXIMUM_SIZE;
        }
        return NO;
    }

    public String textAreasCompleted(User user) {
        if (user.getName().equals("") || user.getLastName().equals("") || user.getNickName().equals("")
                || user.getPassword().equals("") || user.getRePassword().equals("") || user.getMail().equals("")) {
            return TEXT_AREAS_COMPLETED;
        }
        return NO;
    }

    public String gmailAndNicknameUsed(User user) {
        String catcher = statement.nicknameOrGmailUsed(user.getNickName(), user.getGmail());
        if (!catcher.equals(NO)) {
            return THIS + catcher + CHOOSE_ANOTHER;
        }
        return NO;
    }

    public String comparePasswords(User user) {
        if (!user.getPassword().equals(user.getRePassword())) {
            return DIFFERENT_PASS;
        }
        return NO;
    }

    public String showWelcomeMessage(User user) {
        return user.showWelcome();
    }

    public String isEmail(String correo) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(correo);
        if (mather.find()) {
            return NO;
        }
        return INSERT_VALID_GMAIL;
    }

    public String gmailValidated(String gmail, String random) {
        try {
            Properties myProperties = new Properties();
            myProperties.put("mail.smtp.host", "smtp.gmail.com");
            myProperties.setProperty("mail.smtp.starttls.enable", "true");
            myProperties.setProperty("mail.smtp.port", "587");
            myProperties.setProperty("mail.smtp.user", gmail);
            myProperties.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(myProperties, null);
            String senderMail = "SpaceInvadersProyect@gmail.com";
            String passwordSenderMail = "chocolateperrito";
            String recipientMail = gmail;
            String subject = CONFIRMATION_CODE;
            String message = random;

            MimeMessage theMessage = new MimeMessage(session); //creo el mensaje
            theMessage.setFrom(new InternetAddress(senderMail));
            theMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientMail));
            theMessage.setSubject(subject);
            theMessage.setText(message);

            Transport transport = session.getTransport("smtp");
            transport.connect(senderMail, passwordSenderMail);
            transport.sendMessage(theMessage, theMessage.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (MessagingException ex) {
            return INVALID_EMAIL;
        }
        return NO;
    }
}
