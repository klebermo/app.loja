package org.loja.forum.topic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("topic")
public class TopicController extends org.loja.forum.Controller<Topic> {
  public TopicController() {
    super(Topic.class);
  }
}
