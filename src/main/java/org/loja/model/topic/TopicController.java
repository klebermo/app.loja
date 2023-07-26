package org.loja.model.topic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.lang.reflect.InvocationTargetException;

import org.loja.model.resposta.Resposta;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("topic")
public class TopicController extends org.loja.model.Controller<Topic> {
  public TopicController() {
    super(Topic.class);
  }

  @Autowired
  private TopicService serv;

  @RequestMapping(value="/view", method=RequestMethod.GET)
  public String view(Model model, @RequestParam("id") Integer id) {
    model.addAttribute("topic", this.serv.findBy("id", id));
    return "index";
  }

  @RequestMapping(value="/new", method=RequestMethod.GET)
  public String new_topic(Model model, @RequestParam("forum") Integer forum_id) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException {
    model.addAttribute("forum_id", forum_id);
    model.addAttribute("new_topic", this.serv.newObject());
    return "index";
  }

  @RequestMapping(value="/add_resposta", method=RequestMethod.POST)
  @ResponseBody
  public void add_resposta(@RequestParam("topic") Integer topic_id, @ModelAttribute("resposta") Resposta resposta) {
    this.serv.add_resposta(topic_id, resposta);
  }
}
