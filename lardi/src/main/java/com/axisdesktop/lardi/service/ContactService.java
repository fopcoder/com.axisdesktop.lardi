package com.axisdesktop.lardi.service;

import com.axisdesktop.lardi.entity.Contact;

public interface ContactService {
  public Contact create(Contact contact, String login);

  // public List<Contact> list(Pageable pageable);
}
