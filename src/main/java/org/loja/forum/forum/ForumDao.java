package org.loja.forum.forum;

import org.springframework.stereotype.Repository;

@Repository
public class ForumDao extends org.loja.forum.Dao<Forum> {
  public ForumDao() {
    super(Forum.class);
  }
}
