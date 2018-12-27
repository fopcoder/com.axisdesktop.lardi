package com.axisdesktop.lardi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.axisdesktop.lardi.entity.Contact;

@Repository
public interface ContactRepository
    extends JpaRepository<Contact, Integer>, JpaSpecificationExecutor<Contact> {

  Contact getByIdAndUserId(int id, int userId);
}
