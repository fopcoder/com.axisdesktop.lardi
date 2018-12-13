package com.axisdesktop.lardi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private Environment env;

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
        .defaultSuccessUrl("/contacts")//
        .permitAll()//
        .and().logout().permitAll();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()//
        .withUser("user")//
        .password("{noop}password")//
        .roles("ADMIN");
  }
}
