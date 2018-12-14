package com.axisdesktop.lardi.service;

import com.axisdesktop.lardi.entity.User;

public interface UserService {
  public boolean isLoginUnique(String login);

  public User create(User user);
}
