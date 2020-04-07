package org.loja.model.forum;

import org.springframework.stereotype.Service;

@Service
public class ForumService extends org.loja.model.Service<Forum> {
  public ForumService() {
    super(Forum.class);
  }
}
