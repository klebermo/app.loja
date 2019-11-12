package org.loja;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import java.util.Locale;
import javax.mail.MessagingException;
import org.thymeleaf.TemplateEngine;

@Component
public class MailSender {
  @Autowired
  private HttpServletRequest httpServletRequest;

  @Autowired
  private TemplateEngine templateEngine;

  @Autowired
  private JavaMailSender sendMail;

  public void send_mail(String from, String nome, String sobrenome, String subject, String content) throws MessagingException {
      // Prepare the evaluation context
      Locale locale = httpServletRequest.getLocale();
      Context ctx = new Context(locale);
      ctx.setVariable("name", nome + " " + sobrenome);

      // Prepare message using a Spring helper
      MimeMessage mimeMessage = this.sendMail.createMimeMessage();
      MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
      message.setSubject(subject);
      message.setFrom("sender@mail.com");
      message.setTo(from);

      // Create the HTML body using Thymeleaf
      String htmlContent = this.templateEngine.process(content, ctx);
      message.setText(htmlContent, true); // true = isHtml

      // Send mail
      this.sendMail.send(mimeMessage);
  }
}
