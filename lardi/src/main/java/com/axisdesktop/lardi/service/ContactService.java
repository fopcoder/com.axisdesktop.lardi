package com.axisdesktop.lardi.service;

import com.axisdesktop.lardi.entity.Contact;
import com.axisdesktop.lardi.helper.PageResultSet;

public interface ContactService {
  public Contact create(Contact contact, String login);

  public PageResultSet<Contact> list(Contact contact, int offset, int limit);

  public Contact get(int id, String login);

  public Contact update(Contact contact, String login);

  public boolean deleteByIdAndUserName(int id, String login);

}
