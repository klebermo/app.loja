package org.loja.model.user_agent;

import org.springframework.stereotype.Service;

@Service
public class UserAgentService extends org.loja.model.Service<UserAgent> {
  public UserAgentService() {
    super(UserAgent.class);
  }
}
