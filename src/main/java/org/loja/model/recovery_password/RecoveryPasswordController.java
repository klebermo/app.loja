package org.loja.model.recovery_password;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("recover")
public class RecoveryPasswordController extends org.loja.model.Controller<RecoveryPassword> {
  public RecoveryPasswordController() {
    super(RecoveryPassword.class);
  }

  @Autowired
  private RecoveryPasswordService serv;

  @RequestMapping(value = "/password", method=RequestMethod.GET)
  public String formRecover(Model model, @RequestParam(value="token", required=false) String token) {
    if(token == null) {
      model.addAttribute("recoverPassword", "recoverPassword");
      return "recoverPassword";
    } else {
      model.addAttribute("changePassword", "changePassword");
      model.addAttribute("token", token);
      return "changePassword";
    }
  }

  @RequestMapping(value = "/password", method=RequestMethod.POST)
  @ResponseBody
  public void doRecoverPassword(@RequestParam("email") String email) throws Exception {
    this.serv.recoverPassword(email);
  }

  @RequestMapping(value = "/change", method=RequestMethod.POST)
  @ResponseBody
  public void doChangePassword(@RequestParam("token") String token, @RequestParam("senha") String senha) throws Exception {
    this.serv.changePassword(token, senha);
  }
}
