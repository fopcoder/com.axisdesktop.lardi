package com.axisdesktop.lardi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(min = 3, max = 50)
  private String login;

  private String password;

  @Transient
  private String repassword;

  @Size(min = 5, max = 255)
  private String fio;
}

// Информация о пользователе в системе
// Логин (только английские символы, не меньше 3х, без спецсимволов)
// Пароль (минимум 5 символов)
// ФИО (минимум 5 символов)
