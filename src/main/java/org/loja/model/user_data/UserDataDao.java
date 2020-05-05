package org.loja.model.user_data;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDataDao extends Dao<UserData> {
  public UserDataDao() {
    super(UserData.class);
  }
}
