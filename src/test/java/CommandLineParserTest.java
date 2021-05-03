import static org.junit.Assert.*;
import static utilities.Constants.*;

import exceptions.InvalidArgumentException;
import exceptions.InvalidPairException;
import org.junit.Before;
import org.junit.Test;

public class CommandLineParserTest {
  String[] missingRequired, noTemplate,validArgs,missingArg,arguments,noArg;
  CommandLineParser parser,copy,parser1,nullParser;
  String csvFile,templateFile,folder;

  @Before
  public void setUp() throws Exception {
    csvFile = "supporter.csv";
    templateFile = "template.txt";
    folder = "emails";
    validArgs =  new String[]{EMAIL,EMAIL_TEMPLATE,templateFile,CSV, csvFile,OUTPUT,folder};
    arguments = new String[]{LETTER,LETTER_TEMPLATE,templateFile,CSV,csvFile,OUTPUT,folder};
    parser = new CommandLineParser(validArgs);
    copy = new CommandLineParser(validArgs);
    parser1 = new CommandLineParser(arguments);

  }
  @Test (expected = InvalidArgumentException.class)
  public void missingRequired() throws InvalidArgumentException, InvalidPairException {
    missingRequired = new String[]{EMAIL,EMAIL_TEMPLATE,CSV};
   new CommandLineParser(missingRequired);
  }

  @Test (expected = InvalidPairException.class)
  public void noEmailTemplate() throws InvalidArgumentException, InvalidPairException {
    noTemplate = new String[]{CSV,csvFile,OUTPUT,folder,EMAIL};
    new CommandLineParser(noTemplate);
  }

  @Test (expected = InvalidArgumentException.class)
  public void missingArg() throws InvalidArgumentException, InvalidPairException {
    missingArg = new String[]{EMAIL,EMAIL_TEMPLATE,templateFile,CSV,OUTPUT};
    new CommandLineParser(missingArg);
  }

  @Test (expected = InvalidArgumentException.class)
  public void noArg() throws InvalidArgumentException, InvalidPairException {
    noArg = new String[]{EMAIL,EMAIL_TEMPLATE,templateFile,CSV,csvFile,OUTPUT};
    new CommandLineParser(noArg);
  }

  @Test
  public void testEquals() {
    assertTrue(parser.equals(parser));
    assertFalse(parser.equals(parser1));
    assertFalse(parser.equals(""));
    assertFalse(parser.equals(nullParser));
    assertTrue(parser.equals(copy));

  }

  @Test
  public void testHashCode() {
    assertTrue(parser.hashCode()==copy.hashCode());
  }

  @Test
  public void testToString() {
    String expected ="CmdParser{args=[--email, --email-template, template.txt, --csv-file, supporter.csv, --output-dir, emails]}";
    assertEquals(parser.toString(),expected);
  }

  @Test
  public void getEmailPath() {
    assertEquals(templateFile,parser.getEmailTemplatePath());
  }

  @Test
  public void getLetterPath() {
    assertEquals(templateFile,parser1.getLetterTemplatePath());
  }

  @Test
  public void getCsvPath() {
    assertEquals(csvFile,parser.getCsvPath());
  }

  @Test
  public void getOutputPath() {
    assertEquals(folder,parser.getOutputPath());
  }
}