package com.axisdesktop.lardi.entity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ContactSpecification implements Specification<Contact> {
  private static final long serialVersionUID = 1L;
  private Contact contact;

  public ContactSpecification(Contact contact) {
    this.contact = contact;
  }

  @Override
  public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> query,
      CriteriaBuilder criteriaBuilder) {
    // TODO Auto-generated method stub
    return null;
  }

}
