package org.loja;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.loja.settings.mail.MailService;
import org.loja.settings.mail.Mail;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;

@Configuration
public class MailConfig {
  @Autowired
  private MailService mailService;

  @Bean
  public JavaMailSender getJavaMailSender() {
      Mail mail = (Mail)mailService.get();

      JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
      mailSender.setHost(mail.getHost());
      mailSender.setPort(mail.getPort());
      mailSender.setUsername(mail.getUsername());
      mailSender.setPassword(mail.getPassword());

      Properties props = mailSender.getJavaMailProperties();
      props.put("mail.transport.protocol", "smtp");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.debug", "true");

      return mailSender;
  }
}
