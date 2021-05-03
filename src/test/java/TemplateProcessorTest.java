import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class TemplateProcessorTest {
  ArrayList<String> csvHeaders;
  ArrayList<String> csvLine;
  TemplateProcessor tempProcessorEmail;
  TemplateProcessor templateProcessorEmailDuplicate;
  TemplateProcessor tempProcessorLetter;
  TemplateProcessor tempProcessorInvalidTemp;
  TemplateProcessor nullTemplateProcessor;
  String emailTemplate;
  String letterTemplate;
  String invalidTemplate;

  @Before
  public void setUp() {
    csvHeaders = new ArrayList<String>();
    emailTemplate = "email-template.txt";
    letterTemplate = "letter-template.txt";
    invalidTemplate = "dne.txt";
    // csv headers list
    csvHeaders.add("first_name");
    csvHeaders.add("last_name");
    csvHeaders.add("company_name");
    csvHeaders.add("address");
    csvHeaders.add("city");
    csvHeaders.add("county");
    csvHeaders.add("state");
    csvHeaders.add("zip");
    csvHeaders.add("phone1");
    csvHeaders.add("phone2");
    csvHeaders.add("email");
    csvHeaders.add("web");
    // create a supporter
    csvLine = new ArrayList<String>();
    csvLine.add("James");
    csvLine.add("Butt");
    csvLine.add("Benton, John B Jr");
    csvLine.add("6649 N Blue Gum St");
    csvLine.add("New Orleans");
    csvLine.add("Orleans");
    csvLine.add("LA");
    csvLine.add("70116");
    csvLine.add("504-621-8927");
    csvLine.add("504-845-1427");
    csvLine.add("jbutt@gmail.com");
    csvLine.add("http://www.bentonjohnbjr.com");
    // create Utilities.TemplateProcessor instance
    tempProcessorEmail = new TemplateProcessor(csvHeaders, csvLine, emailTemplate);
    tempProcessorLetter = new TemplateProcessor(csvHeaders, csvLine, letterTemplate);
    tempProcessorInvalidTemp = new TemplateProcessor(csvHeaders, csvLine, invalidTemplate);
    templateProcessorEmailDuplicate = new TemplateProcessor(csvHeaders, csvLine, emailTemplate);
  }

  @Test
  public void getCsvHeaders() {
    assertEquals(csvHeaders, tempProcessorEmail.getCsvHeaders());
  }

  @Test
  public void getCsvLine() {
    assertEquals(csvLine, tempProcessorEmail.getCsvLine());
  }

  @Test
  public void getTemplate() {
    assertEquals(new File(emailTemplate), tempProcessorEmail.getTemplate());
  }

  @Test
  public void testEquals() {
    assertTrue(tempProcessorEmail.equals(tempProcessorEmail));
    assertTrue(tempProcessorEmail.equals(templateProcessorEmailDuplicate));
    assertFalse(tempProcessorEmail.equals(nullTemplateProcessor));
    assertFalse(tempProcessorEmail.equals(tempProcessorLetter));
    assertFalse(tempProcessorEmail.equals(""));
  }

  @Test
  public void testHashCode() {
    assertTrue(tempProcessorEmail.hashCode() == templateProcessorEmailDuplicate.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("Utilities.TemplateProcessor{csvHeaderList=[first_name, last_name, company_name, address, city, "
        + "county, state, zip, phone1, phone2, email, web], csvLine=[James, Butt, Benton, John B Jr, 6649 N Blue "
        + "Gum St, New Orleans, Orleans, LA, 70116, 504-621-8927, 504-845-1427, jbutt@gmail.com, "
        + "http://www.bentonjohnbjr.com], template=email-template.txt, placeHolders=[]}",
        tempProcessorEmail.toString());
  }
}