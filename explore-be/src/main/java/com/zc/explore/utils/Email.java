package com.zc.explore.utils;

import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;

@Component
public class Email {
  @Value("${app.email.server}")
  private String MAIL_SERVER;
  @Value("${app.email.from}")
  private String FROM;
  @Value("${app.email.pwd}")
  private String PWD;

  /**
   * send implements the feature of sending email
   * 
   * @param to          the email address of receiver
   * @param subject     email header
   * @param content     email content
   * @param contentType email content type, such as "text/html"
   * @throws Exception
   */
  public void send(String to, String subject, String content, String contentType) throws Exception {
    Properties properties = System.getProperties(); // Get system properties
    properties.setProperty("mail.host", MAIL_SERVER); // Setting up the Mail Server
    properties.setProperty("mail.smtp.auth", "true");

    // Create an Authenticator object to pass in Session.getInstance argument
    Authenticator auth = new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(FROM, PWD);
      }
    };

    MimeMessage message = new MimeMessage(Session.getDefaultInstance(properties, auth));
    message.setFrom(new InternetAddress(FROM)); // Set from
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); // Set to
    message.setSubject(subject); // Set Subject
    message.setContent(content, contentType); // Set Content
    Transport.send(message); // Send Email
  }
}
