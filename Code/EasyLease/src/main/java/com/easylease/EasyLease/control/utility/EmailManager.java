package com.easylease.EasyLease.control.utility;

import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.order.Order;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class defines the methods for sending mails to clients.
 *
 * @author Antonio Sarro
 * @version 0.3
 * @since 0.1
 */
public class EmailManager {

  private static final Logger logger = Logger.getLogger(
      EmailManager.class.getName());
  private static final String username = "Info.EasyLease.Unisa@gmail.com";
  private static final String password = "01Gennaio1970";

  /**
   * Method to send an E-mail to a customer when his order is validated.
   *
   * @param client {@link Client} to send the E-mail to.
   * @param order {@link Order} that has been validated.
   * @throws MessagingException if there are problems creating the message.
   * @version 0.2
   * @since 0.1
   */
  public static boolean sendOrderNotification(
      Client client, Order order) throws MessagingException {
    if (client == null || order == null) {
      throw new IllegalArgumentException("Client or Order is null!");
    }
    String clientMail = client.getEmail();
    String subjectMail = "Notifica Ordine";
    String bodyMail = String.format(
        "Gentile %s %s,\nvogliamo informarla che l'ordine #%s da"
            + " lei effettuato è stato convalidato.",
        client.getFirstName(), client.getSurname(), order.getIdOrder());
    return sendMail(subjectMail, bodyMail, clientMail);
  }

  /**
   * Method to send an email to a customer when his quote is stipulated.
   *
   * @param client {@link Client} to send the E-mail to.
   * @param estimate {@link Estimate} that has been stipulated.
   * @throws MessagingException if there are problems creating the message.
   * @version 0.2
   * @since 0.1
   */
  public static boolean sendEstimateNotification(
      Client client, Estimate estimate) throws MessagingException {
    if (client == null || estimate == null) {
      throw new IllegalArgumentException("Client or Estimate is null!");
    }
    String clientMail = client.getEmail();
    String subjectMail = "Notifica Preventivo";
    String bodyMail = String.format(
        "Gentile %s %s,\nvogliamo informarla che il "
            + "preventivo #%s da lei richiesto è stato stipulato.",
        client.getFirstName(), client.getSurname(), estimate.getIdEstimate());
    return  sendMail(subjectMail, bodyMail, clientMail);
  }

  private static boolean sendMail(
      String subjectMail, String bodyMail,
      String clientMail) throws MessagingException {
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

    Message message = prepareMessage(session, clientMail, subjectMail,
        bodyMail);
    if (message != null) {
      Transport.send(message);
      logger.log(Level.INFO, "Email successfully sent!");
      return true;
    } else {
      logger.log(Level.INFO, "Error sending the email!");
      return false;
    }
  }

  private static Message prepareMessage(
      Session session, String clientMail, String subjectMail, String bodyMail) {
    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("noreply@easylease.it"));
      message.setRecipients(Message.RecipientType.TO,
          InternetAddress.parse(clientMail));
      message.setSubject(subjectMail);
      message.setText(bodyMail);
      return message;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, ex.getMessage());
      return null;
    }
  }
}
