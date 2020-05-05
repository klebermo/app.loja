package org.loja.model.user_data;

import org.springframework.stereotype.Service;

@Service
public class UserDataService extends org.loja.model.Service<UserData> {
  public UserDataService() {
    super(UserData.class);
  }
}
