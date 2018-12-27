package com.axisdesktop.lardi.helper;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import com.axisdesktop.lardi.entity.Contact;

public class ContactSpecification implements Specification<Contact> {
  private static final long serialVersionUID = 1L;
  private Contact contact;

  public ContactSpecification(Contact contact) {
    this.contact = contact;
  }

  @Override
  public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
    if (contact == null)
      return null;

    List<Predicate> predicates = new ArrayList<>();

    if (contact.getSurname() != null && contact.getSurname().length() > 0) {
      Predicate pr =
          cb.like(cb.lower(root.get("surname")), contact.getSurname().toLowerCase() + "%");
      predicates.add(pr);
    }

    if (contact.getName() != null && contact.getName().length() > 0) {
      Predicate pr = cb.like(cb.lower(root.get("name")), contact.getName().toLowerCase() + "%");
      predicates.add(pr);
    }

    if (contact.getPhone() != null && contact.getPhone().length() > 0) {
      List<Predicate> prList = new ArrayList<>();
      Predicate pr = cb.like(root.get("phone"), contact.getPhone() + "%");
      prList.add(pr);

      pr = cb.like(root.get("cellular"), contact.getPhone() + "%");
      prList.add(pr);

      predicates.add(cb.or(prList.toArray(new Predicate[prList.size()])));
    }

    return predicates.size() > 0 ? cb.and(predicates.toArray(new Predicate[predicates.size()]))
        : null;
  }
}
