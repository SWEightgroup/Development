package it.colletta.service;
import it.colletta.model.UserModel;
import it.colletta.security.Role;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {
    private MailSender mailSender;

    private SimpleMailMessage message;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.message = templateMessage;
    }

    public void activateUserMail(UserModel user) {
        SimpleMailMessage email = new SimpleMailMessage(this.message);
        email.setTo(user.getUsername());
        StringBuilder body = new StringBuilder("Dear " + user.getFirstName() + " " + user.getLastName() + "\n"
                + "We are pleased that you want to subscribe in our system. \n");
        email.setTo();
        if(user.getRole().equals(Role.DEVELOPER)) {
           body =  body.append("You asked to subscribe as developer so you need to wait the approvation from an admin.");
        }
        else {
            body = body.append("");
        }
    }


}
