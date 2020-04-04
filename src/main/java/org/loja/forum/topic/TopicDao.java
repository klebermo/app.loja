package org.loja.forum.topic;

import org.springframework.stereotype.Repository;

@Repository
public class TopicDao extends org.loja.forum.Dao<Topic> {
  public TopicDao() {
    super(Topic.class);
  }
}
