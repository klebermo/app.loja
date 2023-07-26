package org.loja;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;

@Controller
public class Home {
    @Autowired
    private Install install;

    @RequestMapping("/")
    public String index(Model model) {
      if(install.estaInstalado()) {
        model.addAttribute("index", "index");
        return "index";
      } else {
        return "redirect:/install";
      }
    }

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
    @ResponseBody
    public void install(@RequestParam("server") String server, @RequestParam("user") String user, @RequestParam("pass") String pass, @RequestParam("admin_user") String admin_user, @RequestParam("admin_pass") String admin_pass, @RequestParam("admin_nome") String admin_nome, @RequestParam("admin_sobrenome") String admin_sobrenome, @RequestParam("admin_email") String admin_email) throws Exception {
      install.processaInstalacao(server, user, pass, admin_user, admin_pass, admin_nome, admin_sobrenome, admin_email);
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
      java.util.List<org.loja.model.topic.Topic> result = new java.util.ArrayList<org.loja.model.topic.Topic>();

      org.loja.model.topic.TopicService topicServ = new org.loja.model.topic.TopicService();
      AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(topicServ);
      java.util.List<?> lista = topicServ.select();

      for(Object t : lista)
        if(((org.loja.model.topic.Topic)t).getResposta() == null)
          result.add((org.loja.model.topic.Topic)t);

      model.addAttribute("messages", "messages");
      model.addAttribute("unread", result);
      return "admin/index";
    }

    @RequestMapping("/message_count")
    @ResponseBody
    public Integer message_count() {
      org.loja.model.topic.TopicService topicServ = new org.loja.model.topic.TopicService();
      AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(topicServ);
      java.util.List<?> lista = topicServ.select();

      return lista.size();
    }
}
