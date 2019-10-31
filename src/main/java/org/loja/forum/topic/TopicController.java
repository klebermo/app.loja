package org.loja.forum.topic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("topic")
public class TopicController extends org.loja.forum.Controller<Topic> {
  @Autowired
  private TopicService serv;

  public TopicController() {
    super(Topic.class);
  }

  @RequestMapping("/new")
  public String new_topic(Model model) {
    model.addAttribute("topic", "new");
    return "index";
  }

  @RequestMapping(value="/save", method=RequestMethod.POST)
  public String save_topic(Model model, @Valid Topic object, BindingResult result) {
    model.addAttribute("topic", this.serv.save(object));
    return "index";
  }
}
