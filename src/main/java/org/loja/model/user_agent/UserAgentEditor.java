package org.loja.model.user_agent;

import java.beans.PropertyEditorSupport;

public class UserAgentEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      UserAgentService serv = new UserAgentService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      UserAgent user_agent = (UserAgent) serv.findBy("id", id);
      setValue(user_agent);
    } else {
      setValue(null);
    }
  }
}
