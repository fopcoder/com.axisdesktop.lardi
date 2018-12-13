package com.axisdesktop.lardi.other;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class RegexpTest {
  @Test
  public void test() {
    assertTrue("wrong false", "kh7klhl9kj".matches("^[a-zA-Z0-9]+$"));
    assertFalse("wrong true", "kh%klhl kj".matches("^[a-zA-Z0-9]+$"));
  }
}
