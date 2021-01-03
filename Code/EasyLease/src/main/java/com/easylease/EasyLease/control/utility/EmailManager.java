package com.easylease.EasyLease.control.utility;

import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.order.Order;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class defines the methods for sending mails to clients.
 *
 * @since 0.1
 * @author Antonio Sarro
 * @version 0.1
 */
public class EmailManager {

  private static final Logger logger = Logger.getLogger(EmailManager.class.getName());
  private final String username = ""; //TODO: Create a group mail
  private final String password = ""; //TODO: Password of the mail
  private String hostname;

  /**
   * Initialization of the EmailManager for sending the email.
   *
   * @param hostname of the server on which the service is located
   */
  public EmailManager(String hostname) {
    this.hostname = hostname;
  }

  /**
   *
   * @param client
   */
  public void sendOrderNotification(Client client, Order order) {
    /*String clientMail = client.getMail();
    String subjectMail = "Notification Estimate";
    String bodyMail = String.format("Gentile %s %s,\nvogliamo informarla che l'ordine #%s da lei effettuato è stato convalidato", client.getFirstname(), client.getSurname(), order.getId());
    sendMail(subjectMail, bodyMail, clientMail);
    */
  }

  private void sendMail(String subjectMail, String bodyMail, String clientMail) {
    Properties properties = new Properties();
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");

    Session session = Session.getInstance(properties, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    });

    new Thread(() -> {
      try {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("noreply@easylease.it"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(clientMail));
        message.setSubject(subjectMail);
        message.setText(bodyMail, "UTF-8", "html");

        logger.log(Level.INFO, "Sending an email to " + clientMail);
        Transport.send(message);
        logger.log(Level.INFO, "Email sent");
      } catch (MessagingException ex) {
        logger.log(Level.SEVERE, "Message: {0}\nCause: {1}", new Object[]{ex.getMessage(),
            ex.getCause()});
      }
    }).start();
  }
}
