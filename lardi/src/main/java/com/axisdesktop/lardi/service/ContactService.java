package com.axisdesktop.lardi.service;

import java.util.List;
import com.axisdesktop.lardi.entity.Contact;

public interface ContactService {
  public Contact create(Contact contact, String login);

  public List<Contact> list(Contact contact, int offset, int limit);
}
