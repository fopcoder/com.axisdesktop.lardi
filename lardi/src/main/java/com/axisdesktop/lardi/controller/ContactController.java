package com.axisdesktop.lardi.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.axisdesktop.lardi.entity.Contact;
import com.axisdesktop.lardi.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {
  @Autowired
  private ContactService contactServ;

  @Autowired
  @Qualifier("contactValidator")
  private Validator contactValidator;

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.setValidator(contactValidator);
  }

  @RequestMapping("")
  public String contacts() {
    return "contacts";
  }

  @GetMapping("/add")
  public String add(Contact contact, Model model) {
    return "contacts-add";
  }

  @PostMapping("/add")
  public String create(@ModelAttribute @Validated Contact contact, BindingResult bindingResult,
      Principal principal, Model model) {
    if (bindingResult.hasErrors()) {
      return "contacts-add";
    }

    try {
      contactServ.create(contact, principal.getName());
    } catch (Exception e) {
      e.printStackTrace();
      model.addAttribute("globalError", "Системная ошибка");

      return "contacts-add";
    }

    return "contacts";
  }

  // @PostMapping("/registration")
  // public String registration2(@ModelAttribute @Validated User user, BindingResult bindingResult)
  // {
  // if (bindingResult.hasErrors()) {
  // return "registration";
  // }
  //
  // user.setPassword(passwordEnc.encode(user.getPassword()));
  // userServ.create(user);
  //
  // return "contacts";
  // }

  // @GetMapping("/list")
  // public String list() {
  //
  // return null;
  // }
  //
  // @GetMapping("/{id}")
  // public String load(@PathVariable("id") String id) {
  // return id;
  // }
  //
  // @PostMapping("/create")
  // public void create() {
  //
  // }
  //
  // @PostMapping("/update")
  // public void update() {
  //
  // }
  //
  // @GetMapping("/delete/{id}")
  // public void delete() {
  //
  // }
}
