package org.loja.model.topic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
@RequestMapping("topic")
public class TopicController extends org.loja.model.Controller<Topic> {
  public TopicController() {
    super(Topic.class);
  }

  @RequestMapping(value="/view", method=RequestMethod.GET)
  public String view(Model model, @RequestParam("id") Integer id) {
    model.addAttribute("topic", this.serv.findBy("id", id));
    return "index";
  }

  @RequestMapping(value="/new", method=RequestMethod.GET)
  public String new_topic(Model model, @RequestParam("forum") Integer forum_id) throws InstantiationException, IllegalAccessException {
    model.addAttribute("forum_id", forum_id);
    model.addAttribute("new_topic", this.serv.newObject());
    return "index";
  }
}
