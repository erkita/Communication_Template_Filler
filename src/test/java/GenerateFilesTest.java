import static org.junit.Assert.*;
import static utilities.Constants.*;

import java.io.IOException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidPairException;
import org.junit.Before;
import org.junit.Test;

public class GenerateFilesTest {
  GenerateFiles emails;
  GenerateFiles letters;
  GenerateFiles emailsDuplicate;
  GenerateFiles nullGenerateFiles;
  String csvFile = "nonprofit-supporters.csv";
  String emailTemplateFile = "email-template.txt";
  String letterTemplateFile = "letter-template.txt";
  String folder1 = "emails";
  String folder2 = "letters";
  String[] argsEmail = new String[] {EMAIL, EMAIL_TEMPLATE, emailTemplateFile, CSV, csvFile, OUTPUT, folder1};
  String[] argsLetter = new String[] {LETTER, LETTER_TEMPLATE, letterTemplateFile, CSV, csvFile, OUTPUT, folder2};

  @Before
  public void setUp() {
    emails = new GenerateFiles(argsEmail);
    letters = new GenerateFiles(argsLetter);
    emailsDuplicate = new GenerateFiles(argsEmail);
  }

  @Test
  public void writeFiles() throws InvalidPairException, IOException, InvalidArgumentException {
    emails.writeNewFiles();
    letters.writeNewFiles();
  }

  @Test
  public void testEquals() {
    assertTrue(emails.equals(emails));
    assertTrue(emails.equals(emailsDuplicate));
    assertFalse(emails.equals(nullGenerateFiles));
    assertFalse(emails.equals(letters));
    assertFalse(emails.equals(""));
  }

  @Test
  public void testHashCode() {
    assertTrue(emails.hashCode() == emailsDuplicate.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("Utilities.GenerateFiles{fileNumber=0, args=[--email, --email-template, email-template.txt, --csv-file,"
        + " nonprofit-supporters.csv, --output-dir, emails]}", emails.toString());
  }
}