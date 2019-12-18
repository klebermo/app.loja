package org.loja.forum.topic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

  @RequestMapping(value="/save", method=RequestMethod.POST)
  @ResponseBody
  public Integer save_topic(@Valid Topic object, BindingResult result) {
    this.serv.save(object);
    return object.getId();
  }

  @RequestMapping(value="/add_resposta", method=RequestMethod.POST)
  @ResponseBody
  public void add_resposta(@RequestParam("topic") Integer topic_id, @RequestParam("resposta") Integer resposta_id) {
    this.serv.add_resposta(topic_id, resposta_id);
  }

  @RequestMapping("/unread")
  @ResponseBody
  public Integer unread_topics() {
    return this.serv.unread();
  }
}
