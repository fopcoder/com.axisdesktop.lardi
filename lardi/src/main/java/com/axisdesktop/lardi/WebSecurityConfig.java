package com.axisdesktop.lardi;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private Environment env;

  @Autowired
  private PasswordEncoder passwordEnc;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http //
        .csrf().disable() //
        .authorizeRequests()//
        .antMatchers("/webjars/**").permitAll()//
        .antMatchers("/api/**").permitAll()//
        .antMatchers("/registration").permitAll()//
        .anyRequest().authenticated()//
        // .and().httpBasic()//
        .and()//
        .formLogin()//
        .loginPage("/login")//
        .usernameParameter("login") //
        .defaultSuccessUrl("/contacts")//
        .permitAll()//
        .and().logout().permitAll();
  }

  @Autowired
  private DataSource dataSource;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    // auth.inMemoryAuthentication()//
    // .withUser("user")//
    // .password(passwordEnc.encode("password"))//
    // .roles("ADMIN");

    auth.jdbcAuthentication() //
        .usersByUsernameQuery("SELECT login, password, 1 FROM user WHERE login = ?") //
        .authoritiesByUsernameQuery("SELECT login, 'ADMIN' FROM user WHERE login = ?") //
        .dataSource(dataSource) //
        .passwordEncoder(passwordEnc);

  }
}
