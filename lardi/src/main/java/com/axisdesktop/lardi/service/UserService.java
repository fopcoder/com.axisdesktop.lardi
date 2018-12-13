package com.axisdesktop.lardi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.axisdesktop.lardi.entity.User;
import com.axisdesktop.lardi.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepo;

  public boolean isLoginUnique(String login) {
    if (login == null)
      throw new IllegalArgumentException("login value is null");

    int c = userRepo.countByLogin(login);

    return c > 0 ? false : true;
  }

  @Transactional
  public User create(User user) {
    userRepo.save(user);

    return user;
  }

}
