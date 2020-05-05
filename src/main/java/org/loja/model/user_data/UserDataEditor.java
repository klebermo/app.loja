package org.loja.model.user_data;

import java.beans.PropertyEditorSupport;

public class UserDataEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    if (!text.equals("")) {
      Integer id = Integer.parseInt(text);
      UserDataService serv = new UserDataService();
      org.loja.AppContextHolder.getContext().getAutowireCapableBeanFactory().autowireBean(serv);
      UserData user_data = (UserData) serv.findBy("id", id);
      setValue(user_data);
    } else {
      setValue(null);
    }
  }
}
