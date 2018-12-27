package com.axisdesktop.lardi.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.axisdesktop.lardi.entity.Contact;
import com.axisdesktop.lardi.helper.PageResultSet;
import com.axisdesktop.lardi.helper.PagingItem;
import com.axisdesktop.lardi.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {
  @Autowired
  private Environment env;
  @Autowired
  private ContactService contactServ;

  @Autowired
  @Qualifier("contactValidator")
  private Validator contactValidator;

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.setValidator(contactValidator);
  }

  @RequestMapping(value = {"", "/p{page}"})
  public String contactsPage(Contact contact, Model model,
      @PathVariable("page") Optional<Integer> page) {

    int pageNum = page.orElse(0);
    int limit = Integer.valueOf(env.getProperty("lardi.contact.limit", "10"));

    PageResultSet<Contact> res = contactServ.list(contact, pageNum, limit);
    model.addAttribute("contacts", res.getRows());

    if (res.getTotalPages() > 1) {
      List<PagingItem> pl = new ArrayList<>();
      String append = PagingItem.hrefAppend(contact);
      if (append.length() > 0)
        append = "?" + append;

      for (int i = 0; i < res.getTotalPages(); i++) {
        String href = "";
        if (i != 0) {
          href = "/p" + i + append;
        } else {
          href = append;
        }

        PagingItem pi = new PagingItem(i, i + 1 + "", href, pageNum == i ? true : false);
        pl.add(pi);
      }

      model.addAttribute("paging", pl);
    }

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

    return "redirect:/contacts";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable("id") int id, Principal principal) {
    contactServ.deleteByIdAndUserName(id, principal.getName());

    return "redirect:/contacts";
  }

}
