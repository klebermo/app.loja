package org.loja.model.topic;

import org.springframework.stereotype.Service;

@Service
public class TopicService extends org.loja.model.Service<Topic> {
  public TopicService() {
    super(Topic.class);
  }
}
