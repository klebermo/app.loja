package org.loja.model.user_data;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user_data")
public class UserDataController extends org.loja.model.Controller<UserData> {
  public UserDataController() {
    super(UserData.class);
  }
}
