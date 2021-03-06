package com.axisdesktop.lardi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.axisdesktop.lardi.entity.Contact;
import com.axisdesktop.lardi.entity.User;
import com.axisdesktop.lardi.helper.ContactSpecification;
import com.axisdesktop.lardi.helper.PageResultSet;
import com.axisdesktop.lardi.repository.ContactRepository;
import com.axisdesktop.lardi.repository.UserRepository;

@Service
@Profile("sql")
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
  public PageResultSet<Contact> list(Contact contact, int offset, int limit) {
    ContactSpecification spec = new ContactSpecification(contact);
    Page<Contact> page =
        contactRepo.findAll(spec, PageRequest.of(offset, limit, Sort.Direction.DESC, "id"));
    PageResultSet<Contact> res = new PageResultSet<>(page, offset, limit);

    return res;
  }

  @Override
  @Transactional
  public boolean deleteByIdAndUserName(int id, String login) {
    User user = userRepo.getByLogin(login);
    Contact contact = contactRepo.getByIdAndUserId(id, user.getId());

    if (contact != null) {
      contactRepo.deleteById(contact.getId());
      return true;
    }

    return false;
  }

  @Override
  public Contact get(int id, String login) {
    User user = userRepo.getByLogin(login);
    Contact contact = contactRepo.getByIdAndUserId(id, user.getId());

    return contact;
  }

  @Override
  @Transactional
  public Contact update(Contact contact, String login) {
    User user = userRepo.getByLogin(login);
    Contact test = contactRepo.getByIdAndUserId(contact.getId(), user.getId());

    if (test == null)
      return null;

    contact.setUser(user);
    contactRepo.save(contact);

    return contact;
  }

}
