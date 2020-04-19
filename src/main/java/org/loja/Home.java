package org.loja;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;

@Controller
public class Home {
    @Autowired
    private Install install;

    @RequestMapping("/")
    public String index(Model model) {
      model.addAttribute("index", "index");
      return "index";
    }

    /*@RequestMapping("/")
    public String index(Model model) {
      if(install.estaInstalado()) {
        model.addAttribute("index", "index");
        return "index";
      } else {
        return "redirect:/install";
      }
    }*/

    @RequestMapping("/login")
    public String login(Model model) {
      model.addAttribute("login", "login");
      return "login";
    }

    @RequestMapping(value="/install", method=RequestMethod.GET)
    public String install(Model model) {
      model.addAttribute("install", "install");
      return "install";
    }

    @RequestMapping(value="/install", method=RequestMethod.POST)
    public String install(@RequestParam("server") String server, @RequestParam("user") String user, @RequestParam("pass") String pass, @RequestParam("admin_user") String admin_user, @RequestParam("admin_pass") String admin_pass) {
      install.processaInstalacao(server, user, pass, admin_user, admin_pass);
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
