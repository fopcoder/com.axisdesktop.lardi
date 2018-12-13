package com.axisdesktop.lardi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @GetMapping("/list")
  public String list() {

    return null;
  }

  @GetMapping("/{id}")
  public String load(@PathVariable("id") String id) {
    return id;
  }

  @PostMapping("/create")
  public void create() {

  }

  @PostMapping("/update")
  public void update() {

  }

  @GetMapping("/delete/{id}")
  public void delete() {

  }
}
