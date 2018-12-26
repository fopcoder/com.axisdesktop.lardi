package com.axisdesktop.lardi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.axisdesktop.lardi.entity.Contact;

@Repository
public interface ContactRepository
    extends JpaRepository<Contact, Integer>, JpaSpecificationExecutor<Contact> {
  List<Contact> findByName(String name);
}
