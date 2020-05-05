package org.loja.model.user_agent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user_agent")
public class UserAgentController extends org.loja.model.Controller<UserAgent> {
  public UserAgentController() {
    super(UserAgent.class);
  }
}
