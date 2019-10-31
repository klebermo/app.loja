package org.loja.forum.forum;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("forum")
public class ForumController extends org.loja.forum.Controller<Forum> {
  public ForumController() {
    super(Forum.class);
  }
}
