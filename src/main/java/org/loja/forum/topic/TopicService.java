package org.loja.forum.topic;

import org.springframework.stereotype.Service;

@Service
public class TopicService extends org.loja.forum.Service<Topic> {
  public TopicService() {
    super(Topic.class);
  }
}
