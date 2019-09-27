package org.loja.settings.email;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("email")
public class EmailController extends org.loja.settings.Controller<Email> {
  public EmailController() {
    super(Email.class);
  }
}
