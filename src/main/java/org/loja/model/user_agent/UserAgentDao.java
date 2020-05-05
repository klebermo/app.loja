package org.loja.model.user_agent;

import org.loja.model.Dao;
import org.springframework.stereotype.Repository;

@Repository
public class UserAgentDao extends Dao<UserAgent> {
  public UserAgentDao() {
    super(UserAgent.class);
  }
}
