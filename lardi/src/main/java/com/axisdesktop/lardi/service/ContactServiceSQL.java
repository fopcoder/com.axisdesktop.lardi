package com.axisdesktop.lardi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.axisdesktop.lardi.entity.Contact;
import com.axisdesktop.lardi.entity.User;
import com.axisdesktop.lardi.repository.ContactRepository;
import com.axisdesktop.lardi.repository.UserRepository;

@Service
public class ContactServiceSQL implements ContactService {
  @Autowired
  private UserRepository userRepo;

  @Autowired
  private ContactRepository contactRepo;

  @Transactional
  @Override
  public Contact create(Contact contact, String login) {
    if (contact == null)
      throw new IllegalArgumentException("contact is null");
    if (login == null)
      throw new IllegalArgumentException("login is null");

    User user = userRepo.getByLogin(login);

    if (user == null)
      throw new IllegalStateException("user not found");

    contact.setUser(user);

    return contactRepo.save(contact);
  }
}
