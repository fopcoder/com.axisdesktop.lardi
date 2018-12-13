package com.axisdesktop.lardi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.axisdesktop.lardi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Integer countByLogin(String login);
}
