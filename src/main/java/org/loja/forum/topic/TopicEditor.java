package org.loja.forum.topic;

import java.beans.PropertyEditorSupport;

public class TopicEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    TopicDao dao = new TopicDao();
    org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(dao);

    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      Topic topic = dao.findBy("id", id);
      setValue(topic);
    } else {
      Topic topic = new Topic();
      dao.insert(topic);
      setValue(topic);
    }
  }
}
