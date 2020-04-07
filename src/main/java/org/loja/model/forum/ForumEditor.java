package org.loja.model.forum;

import java.beans.PropertyEditorSupport;
import org.loja.model.forum.Forum;
import org.loja.model.forum.ForumService;

public class ForumEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      ForumService serv = new ForumService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Forum forum = (Forum) serv.findBy("id", id);
      setValue(forum);
    } else {
      Forum forum = new Forum();
      ForumService serv = new ForumService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      serv.insert(forum);
      setValue(forum);
    }
  }
}
