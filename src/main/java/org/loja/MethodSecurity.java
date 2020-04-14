package org.loja.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.GrantedAuthority;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class MethodSecurity extends GlobalMethodSecurityConfiguration {
  @Override
  public MethodSecurityExpressionHandler createExpressionHandler() {
    DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
    expressionHandler.setPermissionEvaluator(new PermissionEvaluator() {
      public boolean hasPermission(org.springframework.security.core.Authentication authentication, java.io.Serializable targetId, java.lang.String targetType, java.lang.Object permission) {
        if (authentication == null || !authentication.isAuthenticated())
          return false;
        else
          for(GrantedAuthority authority: authentication.getAuthorities())
            if(authority.getAuthority().equals(permission))
              return true;
        return false;
      }

      public boolean hasPermission(org.springframework.security.core.Authentication authentication, java.lang.Object targetDomainObject, java.lang.Object permission) {
        if (authentication == null || !authentication.isAuthenticated())
          return false;
        else
          for(GrantedAuthority authority: authentication.getAuthorities())
            if(authority.getAuthority().equals(permission))
              return true;
        return false;
      }
    });
    return expressionHandler;
  }
}
