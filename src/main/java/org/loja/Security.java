package org.loja;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.GrantedAuthority;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
  @Autowired
  private DataSource dataSource;

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth
        .jdbcAuthentication()
          .dataSource(dataSource)
          .usersByUsernameQuery("select username, password, enabled from usuario where username = ?")
          .authoritiesByUsernameQuery("select t1.username, t2.authority from (select u.username as username, c.nome as credencial from usuario u, usuario_credenciais uc, credencial c where u.id = uc.usuario_id and c.id = uc.credencial_id) t1 inner join (select c.nome as credencial, a.nome as authority from credencial c, credencial_autorizacoes ca, autorizacao a where c.id = ca.credencial_id and a.id = ca.autorizacoes_id) t2 on t1.credencial = t2.credencial where t1.username =  ?")
          .passwordEncoder(new BCryptPasswordEncoder(4));
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
      http
        .csrf().disable()
        .authorizeRequests()
          .antMatchers("/css/**", "/images/**", "/js/**", "/**").permitAll()
          .anyRequest().authenticated()
        .and()
        .formLogin()
          .loginPage("/login")
          .loginProcessingUrl("/doLogin").permitAll()
          .usernameParameter("login")
          .passwordParameter("senha")
        .and()
        .logout()
          .logoutUrl("/logout")
          .deleteCookies("remember-me")
        .and()
          .rememberMe();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
    handler.setPermissionEvaluator(new PermissionEvaluator() {
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
    web.expressionHandler(handler);
  }
}
