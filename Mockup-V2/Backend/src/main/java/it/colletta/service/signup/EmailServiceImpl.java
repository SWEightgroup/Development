package it.colletta.service.signup;

import it.colletta.model.UserModel;
import it.colletta.security.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl {

  private JavaMailSender sender;

  @Autowired
  public EmailServiceImpl(JavaMailSender sender) {
    this.sender = sender;
  }


  /**
   * activateUserMail.
   *
   * @param user User user to be activated
   */
  public void activateUserMail(UserModel user, String link) throws Exception {
    MimeMessage message = sender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);
    helper.setTo(user.getUsername());
    StringBuilder body = new StringBuilder("Dear " + user.getFirstName() + " " + user.getLastName()
        + "\n" + "We are pleased that you want to subscribe in our system. \n");
    if (user.getRole().equals(Role.DEVELOPER)) {
      body = body.append("You asked to subscribe as developer "
          + "so you need to wait the approval from an admin. \n");
    } else {
      body = body.append("Please follow the link to activate your account: " + link + "\n");
    }

    body.append("Thank you so much." + "\nRegards, Colletta team");

    helper.setSubject("Activate your account in Colletta");
    helper.setText(body.toString());
    sender.send(message);
  }

  public void forgotPasswordMail(UserModel user, String link) throws Exception {
    MimeMessage message = sender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);
    helper.setTo(user.getUsername());
    StringBuilder body = new StringBuilder("Dear " + user.getFirstName() + " " + user.getLastName()
        + "\n" + "Follow the link to check change the password. \n");
    body.append("Recover password: ").append(link).append("\n");
    body.append("Thank you so much." + "\nRegards, Colletta team");
    helper.setSubject("Recover your account in Colletta");
    helper.setText(body.toString());
    sender.send(message);
  }

}
