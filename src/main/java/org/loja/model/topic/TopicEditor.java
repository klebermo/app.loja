package org.loja.model.topic;

import java.beans.PropertyEditorSupport;
import org.loja.model.topic.Topic;
import org.loja.model.topic.TopicService;

public class TopicEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      TopicService serv = new TopicService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Topic topic = (Topic) serv.findBy("id", id);
      setValue(topic);
    } else {
      Topic topic = new Topic();
      TopicService serv = new TopicService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      serv.insert(topic);
      setValue(topic);
    }
  }
}
