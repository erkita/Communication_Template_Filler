import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OptionTest {

  Option email, emailTemp, letter, letterTemp, emailDuplicate;
  String emailTemplate, letterTemplate;


  @Before
  public void setUp() throws Exception {
    emailTemplate = "email-template.txt";
    letterTemplate = "letter-template.txt";
    email = new Option("myEmail@gmail.com", false);
    emailDuplicate = new Option("myEmail@gmail.com", false);
    emailTemp = new Option(emailTemplate, true);
    letter = new Option("someLetter", false);
    letterTemp = new Option(letterTemplate, true);
  }

  @Test
  public void getOpt() {
    assertEquals(emailTemplate, emailTemp.getOption());
  }

  @Test
  public void hasArg() {
    assertTrue(letterTemp.hasArg());
    assertFalse(email.hasArg());
  }

  @Test
  public void setHasArg() {
    letterTemp.setHasArg(false);
    assertEquals(letter.hasArg(), letterTemp.hasArg());
  }

  @Test
  public void testToString() {
    assertEquals("Option{opt='myEmail@gmail.com', hasArg=false}", email.toString());
  }

  @Test
  public void testEquals() {
    assertTrue(email.equals(email));
    assertTrue(email.equals(emailDuplicate));
    assertFalse(email.equals(emailTemp));
    assertFalse(letter.equals(""));

  }

  @Test
  public void testHashCode() {
    assertTrue(email.hashCode() == emailDuplicate.hashCode());
  }
}
