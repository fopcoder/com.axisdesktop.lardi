package com.axisdesktop.lardi.helper;

import java.util.ArrayList;
import java.util.List;
import com.axisdesktop.lardi.entity.Contact;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingItem {
  private int page;
  private String text;
  private String href;
  private boolean isActive;

  public PagingItem(int page, String text, String href, boolean isActive) {
    this.page = page;
    this.text = text;
    this.href = href;
    this.isActive = isActive;
  }

  public static String hrefAppend(Contact contact) {
    List<String> l = new ArrayList<>();

    if (contact.getName() != null && contact.getName().length() > 0) {
      l.add("name=" + contact.getName());
    }

    if (contact.getSurname() != null && contact.getSurname().length() > 0) {
      l.add("surname=" + contact.getSurname());
    }

    if (contact.getPhone() != null && contact.getPhone().length() > 0) {
      l.add("phone=" + contact.getPhone());
    }

    return String.join("&", l);
  }

}
