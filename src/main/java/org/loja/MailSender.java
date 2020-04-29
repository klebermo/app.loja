package org.loja;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import java.util.Locale;
import org.thymeleaf.context.Context;

@Component
public class MailSender {
  @Autowired
  public JavaMailSender emailSender;

  @Autowired
  private TemplateEngine templateEngine;

  final String from = "kleber-mota@uol.com.br";

  public void sendMessage(String from, String to, String subject, String text) {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom(from);
      message.setTo(to);
      message.setSubject(subject);
      message.setText(text);
      emailSender.send(message);
  }

  public void sendHTMLMessage(final String to, final String subject, final String template, final Object object, final Locale locale) throws Exception {
      final Context ctx = new Context(locale);
      ctx.setVariable("object", object);

      final MimeMessage mimeMessage = this.emailSender.createMimeMessage();

      final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
      message.setSubject(subject);
      message.setFrom(from);
      message.setTo(to);
      message.setText(this.templateEngine.process(template, ctx), true);

      this.emailSender.send(mimeMessage);

  }
}
