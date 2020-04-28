package org.loja;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.loja.settings.mail.MailService;
import org.loja.settings.mail.Mail;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import java.util.Properties;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import java.util.Collections;
import org.thymeleaf.templatemode.TemplateMode;

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

  @Bean
  public TemplateEngine emailTemplateEngine() {
      final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
      templateEngine.addTemplateResolver(htmlTemplateResolver());
      templateEngine.addTemplateResolver(stringTemplateResolver());
      return templateEngine;
  }

  private ITemplateResolver htmlTemplateResolver() {
      final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
      templateResolver.setOrder(Integer.valueOf(1));
      templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
      templateResolver.setPrefix("/email/");
      templateResolver.setSuffix(".html");
      templateResolver.setTemplateMode(TemplateMode.HTML);
      templateResolver.setCharacterEncoding("UTF8");
      templateResolver.setCacheable(false);
      return templateResolver;
  }

  private ITemplateResolver stringTemplateResolver() {
      final StringTemplateResolver templateResolver = new StringTemplateResolver();
      templateResolver.setOrder(Integer.valueOf(2));
      templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
      //templateResolver.setPrefix("/email/");
      //templateResolver.setSuffix(".html");
      templateResolver.setTemplateMode(TemplateMode.HTML);
      //templateResolver.setCharacterEncoding("UTF8");
      templateResolver.setCacheable(false);
      return templateResolver;
  }
}
