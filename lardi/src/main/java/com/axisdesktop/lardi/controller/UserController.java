package com.axisdesktop.lardi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.axisdesktop.lardi.entity.User;
import com.axisdesktop.lardi.service.UserService;

@Controller
public class UserController {
  @Autowired
  private PasswordEncoder passwordEnc;
  @Autowired
  private UserService userServ;

  @Autowired
  @Qualifier("userValidator")
  private Validator userValidator;

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.setValidator(userValidator);
  }

  @ResponseBody
  @RequestMapping("")
  public String index() {
    return "kjhkljhlkj";
  }

  @RequestMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/registration")
  public String registration(User user) {
    return "registration";
  }

  @PostMapping("/registration")
  public String registration2(@ModelAttribute @Validated User user, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "registration";
    }

    user.setPassword(passwordEnc.encode(user.getPassword()));
    userServ.create(user);

    return "contacts";
  }
}
