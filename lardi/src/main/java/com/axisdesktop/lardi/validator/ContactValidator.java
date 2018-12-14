package com.axisdesktop.lardi.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.axisdesktop.lardi.entity.Contact;

public class ContactValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return Contact.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "contact.surname.empty",
        "Surname is empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "contact.name.empty",
        "Name is empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "patronymic", "contact.patronymic.empty",
        "Patronymic is empty");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cellular", "contact.cellular.empty",
        "Cellular is empty");

    Contact contact = (Contact) target;

    if (contact.getSurname().length() < 4) {
      errors.rejectValue("surname", "contact.surname.short", "Surname must be minimum 4 symols");
    }

    if (contact.getName().length() < 4) {
      errors.rejectValue("name", "contact.name.short", "Name must be minimum 4 symols");
    }

    if (contact.getPatronymic().length() < 4) {
      errors.rejectValue("patronymic", "contact.patronymic.short",
          "Patronymic must be minimum 4 symols");
    }

    if (!isPhoneValid(contact.getCellular())) {
      errors.rejectValue("cellular", "contact.cellular.invalid", "Invalid cellular format");
    }

    if (contact.getCellular() != null && contact.getEmail().length() > 0
        && !isEmailValid(contact.getEmail())) {
      errors.rejectValue("email", "contact.email.invalid", "Invalid email format");
    }

    if (contact.getCellular() != null && contact.getPhone().length() > 0
        && !isPhoneValid(contact.getPhone())) {
      errors.rejectValue("phone", "contact.phone.invalid", "Invalid phone format");
    }

  }

  public boolean isPhoneValid(String phone) {
    return phone.matches("^380\\d{9}$");
  }

  public boolean isEmailValid(String email) {
    String re = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    return email.matches(re);
  }

}

// Хранимая информация (одна запись)
// Фамилия (обязательный, минимум 4 символа)
// Имя (обязательный, минимум 4 символа)
// Отчество (обязательный, минимум 4 символа)
// Телефон мобильный (обязательный)
// Телефон домашний (не обязательный)
// Адрес (не обязательный)
// e-mail (не обязательный, общепринятая валидация)
