package org.loja.model.topic;

import org.springframework.stereotype.Repository;

@Repository
public class TopicDao extends org.loja.model.Dao<Topic> {
  public TopicDao() {
    super(Topic.class);
  }
}
