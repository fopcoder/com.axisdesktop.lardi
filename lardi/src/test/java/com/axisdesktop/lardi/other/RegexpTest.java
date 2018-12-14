package com.axisdesktop.lardi.other;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class RegexpTest {
  @Test
  public void test() {
    assertTrue("wrong false", "kh7klhl9kj".matches("^[a-zA-Z0-9]+$"));
    assertFalse("wrong true", "kh%klhl kj".matches("^[a-zA-Z0-9]+$"));

    assertTrue("wrong false", "380665554433".matches("^380\\d{9}$"));
    assertFalse("wrong true", "380665554433".matches("^38a\\d{9}$"));
  }

  @Test
  public void test2() {
    String re = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    assertTrue("wrong false", "frfr@jj.com".matches(re));
    assertFalse("wrong true", "frfr,@jj.com".matches(re));

  }

}
