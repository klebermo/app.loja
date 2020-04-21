package org.loja;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

@Component
public class MailSender {
  @Autowired
  public JavaMailSender emailSender;

  public void sendMessage(String from, String to, String subject, String text) {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom(from);
      message.setTo(to);
      message.setSubject(subject);
      message.setText(text);
      emailSender.send(message);
  }
}
