import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class CSVProcessorTest {
  CSVProcessor headerList;
  CSVProcessor headerListDuplicate;
  CSVProcessor supporterList;
  CSVProcessor nullList;
  ArrayList<String> headers;
  ArrayList<String> headersDuplicate;
  ArrayList<String> supporter;
  @Before
  public void setUp()  {
    headerList = new CSVProcessor("first_name\",\"last_name\",\"address\",\"city\",\"country\",\"state\","
        + "\"zip\",\"phone1\",\"phone2\",\"email\",\"web");
    headers = new ArrayList<String>();
    headers.add("first_name");
    headers.add("last_name");
    headers.add("address");
    headers.add("city");
    headers.add("country");
    headers.add("state");
    headers.add("zip");
    headers.add("phone1");
    headers.add("phone2");
    headers.add("email");
    headers.add("web");
    headerListDuplicate = new CSVProcessor("first_name\",\"last_name\",\"address\",\"city\",\"country\",\"state\","
        + "\"zip\",\"phone1\",\"phone2\",\"email\",\"web");
    headersDuplicate = new ArrayList<String>();
    headersDuplicate.add("first_name");
    headersDuplicate.add("last_name");
    headersDuplicate.add("address");
    headersDuplicate.add("city");
    headersDuplicate.add("country");
    headersDuplicate.add("state");
    headersDuplicate.add("zip");
    headersDuplicate.add("phone1");
    headersDuplicate.add("phone2");
    headersDuplicate.add("email");
    headersDuplicate.add("web");
    supporterList = new CSVProcessor("James\",\"Butt\",\"Benton, John B Jr\",\"6649 N Blue Gum St\","
        + "\"New Orleans\",\"Orleans\",\"LA\",\"70116\",\"504-621-8927\",\"504-845-1427\",\"jbutt@gmail.com\","
        + "\"http://www.bentonjohnbjr.com");
    supporter = new ArrayList<String>();
    supporter.add("James");
    supporter.add("Butt");
    supporter.add("Benton, John B Jr");
    supporter.add("6649 N Blue Gum St");
    supporter.add("New Orleans");
    supporter.add("Orleans");
    supporter.add("LA");
    supporter.add("70116");
    supporter.add("504-621-8927");
    supporter.add("504-845-1427");
    supporter.add("jbutt@gmail.com");
    supporter.add("http://www.bentonjohnbjr.com");
  }
  @Test
  public void csvLineList() {
    assertEquals(headers, headerList.csvLineList());
    assertEquals(supporter, supporterList.csvLineList());
  }
  @Test
  public void testEquals() {
    assertTrue(headerList.equals(headerList));
    assertTrue(headerList.equals(headerListDuplicate));
    assertFalse(headerList.equals(nullList));
    assertFalse(headerList.equals(supporterList));
    assertFalse(headerList.equals(""));
  }
  @Test
  public void testHashCode() {
    assertTrue(headerList.hashCode() == headerListDuplicate.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("CSVProcessor{csvRow='first_name\",\"last_name\",\"address\",\"city\",\"country\",\"state\","
            + "\"zip\",\"phone1\",\"phone2\",\"email\",\"web'}", headerList.toString());
  }
}