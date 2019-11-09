package org.loja.model;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import java.util.Locale;
import javax.mail.MessagingException;
import org.thymeleaf.TemplateEngine;

public abstract class Service<E> {
  @Autowired
  private TemplateEngine templateEngine;

  @Autowired
  private JavaMailSender mailSender;

  private Class<E> clazz;

  @Autowired
  protected Dao<E> dao;

  public Service(Class<E> clazz) {
    this.clazz = clazz;
  }

  public void insert(E object) {
    dao.insert(object);
  }

  public void update(E object) {
    dao.update(object);
  }

  public void delete(E object) {
    dao.delete(object);
  }

  public List<E> select() {
    return dao.select();
  }

  public E findBy(String key, Object value) {
    return dao.findBy(key, value);
  }

  public E newObject() throws InstantiationException, IllegalAccessException {
    return clazz.newInstance();
  }

  public void send_mail(String from, String nome, String sobrenome, String subject, String content, Locale locale) throws MessagingException {
      // Prepare the evaluation context
      Context ctx = new Context(locale);
      ctx.setVariable("name", nome + " " + sobrenome);

      // Prepare message using a Spring helper
      MimeMessage mimeMessage = this.mailSender.createMimeMessage();
      MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
      message.setSubject(subject);
      message.setFrom("sender@mail.com");
      message.setTo(from);

      // Create the HTML body using Thymeleaf
      String htmlContent = this.templateEngine.process(content, ctx);
      message.setText(htmlContent, true); // true = isHtml

      // Send mail
      this.mailSender.send(mimeMessage);
  }
}
