package org.loja;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;

@Controller
public class Home {
    @Autowited
    private Install install;

    @RequestMapping("/")
    public String index(Model model) {
      model.addAttribute("index", "index");
      return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
      model.addAttribute("login", "login");
      return "login";
    }

    @RequestMapping("/install")
    public String install() {
      if(install.estaInstalado())
        return "install";
      else
        return "redirect:/";
    }

    @RequestMapping("/admin")
    @PreAuthorize("hasPermission(#user, 'admin')")
    public String admin(Model model) {
      model.addAttribute("admin", "admin");
      return "admin/index";
    }

    @RequestMapping("/messages")
    @PreAuthorize("hasPermission(#user, 'admin')")
    public String messages(Model model) {
      model.addAttribute("messages", "messages");
      return "admin/index";
    }
}
