package org.loja.forum.forum;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ForumController extends org.loja.forum.Controller<Forum> {
  public ForumController() {
    super(Forum.class);
  }

  @Override
  @RequestMapping("/view_forum/{item_id}")
  public String view(Model model, @PathVariable("item_id") Integer item_id) {
    model.addAttribute("forum", this.serv.getData(item_id));
    return "index";
  }
}
