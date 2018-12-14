package com.axisdesktop.lardi.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "contact")
public class Contact {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @NotNull
  private User user;

  @NotBlank
  private String surname;

  @NotBlank
  private String name;

  @NotBlank
  private String patronymic;

  @NotBlank
  private String cellular;

  private String phone;
  private String address;
  private String email;

}

// Хранимая информация (одна запись)
// Фамилия (обязательный, минимум 4 символа)
// Имя (обязательный, минимум 4 символа)
// Отчество (обязательный, минимум 4 символа)
// Телефон мобильный (обязательный)
// Телефон домашний (не обязательный)
// Адрес (не обязательный)
// e-mail (не обязательный, общепринятая валидация)
