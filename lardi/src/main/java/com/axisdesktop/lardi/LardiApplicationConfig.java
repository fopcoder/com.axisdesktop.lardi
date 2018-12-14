package com.axisdesktop.lardi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Validator;
import com.axisdesktop.lardi.validator.ContactValidator;
import com.axisdesktop.lardi.validator.UserValidator;

@Configuration
public class LardiApplicationConfig {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public Validator userValidator() {
    return new UserValidator();
  }

  @Bean
  public Validator contactValidator() {
    return new ContactValidator();
  }
}
