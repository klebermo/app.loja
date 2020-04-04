package org.loja.model.produto;

import java.beans.PropertyEditorSupport;
import org.loja.forum.forum.Forum;
import org.loja.forum.forum.ForumService;

public class ForumEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      ForumService serv = new ForumService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      Forum forum = (Forum) serv.get(id);
      setValue(forum);
    } else {
      Forum forum = new Forum();
      ForumService serv = new ForumService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      serv.set(forum);
      setValue(forum);
    }
  }
}
