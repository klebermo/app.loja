package org.loja.forum.forum;

import org.springframework.stereotype.Service;

@Service
public class ForumService extends org.loja.forum.Service<Forum> {
  public ForumService() {
    super(Forum.class);
  }
}
