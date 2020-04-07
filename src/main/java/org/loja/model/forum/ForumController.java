package org.loja.model.forum;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
@RequestMapping("forum")
public class ForumController extends org.loja.model.Controller<Forum> {
  public ForumController() {
    super(Forum.class);
  }

  @RequestMapping(value="/view", method=RequestMethod.GET)
  public String view(Model model, @RequestParam("id") Integer id) {
    model.addAttribute("forum", this.serv.findBy("id", id));
    return "index";
  }
}
