package com.axisdesktop.lardi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.axisdesktop.lardi.entity.Contact;
import com.axisdesktop.lardi.entity.ContactSpecification;
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

  @Override
  public List<Contact> list(Contact contact, int offset, int limit) {
    ContactSpecification spec = new ContactSpecification(contact);

    // List<Contact> contacts = contactRepo.findByName(null);
    Page<Contact> page = contactRepo.findAll(spec, PageRequest.of(offset, limit));

    System.err.println(page.getContent());

    return page.getContent();
  }
}
