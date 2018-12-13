package com.axisdesktop.lardi.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.axisdesktop.lardi.entity.User;
import com.axisdesktop.lardi.service.UserService;

public class UserValidator implements Validator {
  @Autowired
  private UserService userServ;

  @Override
  public boolean supports(Class<?> clazz) {
    return User.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "user.login.empty",
        "Field login is empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.empty",
        "Field password is empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fio", "user.fio.empty",
        "Field FIO is empty");

    User user = (User) target;

    if (user.getLogin().length() < 3) {
      errors.rejectValue("login", "user.login.short", "Field login must be minimum 3 symbols");
    }

    if (!user.getLogin().matches("^[a-zA-Z0-9]+$")) {
      errors.rejectValue("login", "user.login.bad", "Field login must contains only a-zA-Z0-9");
    }

    if (!userServ.isLoginUnique(user.getLogin())) {
      errors.rejectValue("login", "user.login.exists", "Login already exists");
    }

    if (user.getPassword().length() < 5) {
      errors.rejectValue("password", "user.password.short",
          "Field password must be minimum 5 symbols");
    }

    if (!user.getPassword().equals(user.getRepassword())) {
      errors.rejectValue("password", "user.password.nonequal", "Fields password non equal");
    }

    if (user.getFio().length() < 5) {
      errors.rejectValue("fio", "user.fio.short", "Field FIO must be minimum 5 symbols");
    }

  }
}

// Информация о пользователе в системе
// Логин (только английские символы, не меньше 3х, без спецсимволов)
// Пароль (минимум 5 символов)
// ФИО (минимум 5 символов)
