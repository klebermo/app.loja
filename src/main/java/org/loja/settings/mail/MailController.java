package org.loja.settings.mail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mail")
public class MailController extends org.loja.settings.Controller<Mail> {
  public MailController() {
    super(Mail.class);
  }
}
