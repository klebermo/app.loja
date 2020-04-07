package org.loja.model.forum;

import org.springframework.stereotype.Repository;

@Repository
public class ForumDao extends org.loja.model.Dao<Forum> {
  public ForumDao() {
    super(Forum.class);
  }
}
