package org.loja.forum.forum;

import java.beans.PropertyEditorSupport;

public class ForumEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    ForumDao dao = new ForumDao();
    org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(dao);

    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      Forum forum = dao.findBy("id", id);
      setValue(forum);
    } else {
      Forum forum = new Forum();
      dao.insert(forum);
      setValue(forum);
    }
  }
}
