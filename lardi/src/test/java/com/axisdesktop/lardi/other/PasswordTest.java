package com.axisdesktop.lardi.other;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {

  @Test
  public void name() {
    BCryptPasswordEncoder enc = new BCryptPasswordEncoder();

    String[] a = {"fuck", "asjf aslkdj fsal;kd falkjshdf jaksd hajksdf hakjs", "5555",
        "оіап орвларавраолвір длофрл"};

    for (String str : a) {
      System.err.println(str);
      String c = enc.encode(str);
      System.err.println(c);
      System.err.println(c.length());
    }

  }
}
