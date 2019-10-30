package org.loja.forum.topic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TopicController extends org.loja.forum.Controller<Topic> {
  public TopicController() {
    super(Topic.class);
  }

  @Override
  @RequestMapping("/view_topic/{item_id}")
  public String view(Model model, @PathVariable("item_id") Integer item_id) {
    model.addAttribute("topic", this.serv.getData(item_id));
    return "index";
  }
}
