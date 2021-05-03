import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reads template and finds and replaces placeholders with appropriate csv field.
 */
public class TemplateProcessor {
  private static final String BRACKETS_REGEX = "\\[\\[(.*?)\\]";
  private static final String OPEN_BRACKETS = "[[";
  private static final String CLOSE_BRACKETS = "]]";
  private static final String NO_BRACKETS = "";
  private static final int FIELD_GROUP = 1;
  private List<String> csvHeaderList;
  private List<String> csvLine;
  private File template;
  private List<String> placeHolders = new ArrayList<>();

  /**
   * Constructs Utilities.TemplateProcessor by initiating csv and template information.
   * @param csvHeaders a collection of headers
   * @param csvLine a collection of csv line data
   * @param template template to process
   */
  public TemplateProcessor(List<String> csvHeaders, List<String> csvLine, String template) {
    this.csvHeaderList = csvHeaders;
    this.csvLine = csvLine;
    this.template = new File(template);
  }

  /**
   * Returns the CSV header list.
   *
   * @return the CSV header list.
   */
  public List<String> getCsvHeaders() {
    return this.csvHeaderList;
  }

  /**
   * Returns a line from CSV file.
   *
   * @return a line
   */
  public List<String> getCsvLine() {
    return this.csvLine;
  }

  /**
   * Returns the user specified template.
   *
   * @return the user specified template.
   */
  public File getTemplate() {
    return this.template;
  }


  /**
   * Reads template file and adds template's bracketed place holders in a list.
   *
   * @throws IOException if template file was not found or there's an IO error.
   */
  public void setPlaceHolderFields() throws IOException {
    BufferedReader inputFile = new BufferedReader(new FileReader(this.template));
    String line;
    while ((line = inputFile.readLine()) != null) {
      Pattern brackets = Pattern.compile(BRACKETS_REGEX);
      Matcher match = brackets.matcher(line);
      while (match.find()) {
        this.placeHolders.add(match.group(FIELD_GROUP));
      }
    }
  }
  /**
   * Read template and store it as list of string
   * @return list of string
   * @throws IOException if file not found
   */
  public List<String> readTemplate() throws IOException {
    List<String> lines = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(this.template));
    String line = "";
    while ((line = reader.readLine()) != null)
      lines.add(line);
    return lines;
  }

  /**
   * Fills place holders with the matching CSV field.
   *
   * @param line - a line from the template.
   * @return  string with replaced value
   */
  public String fillPlaceHolders(String line) {
    Pattern placeHolderBrackets = Pattern.compile(BRACKETS_REGEX);
    Matcher matching = placeHolderBrackets.matcher(line);
    while (matching.find()) {
      for (int i = 0; i < this.csvLine.size(); i++) {
        if (this.csvHeaderList.get(i).equals(matching.group(FIELD_GROUP))) {
          line = line.replace(matching.group(FIELD_GROUP), this.csvLine.get(i));
        }
      }
      line = line.replace(OPEN_BRACKETS, NO_BRACKETS);
      line = line.replace(CLOSE_BRACKETS, NO_BRACKETS);
    }
    return line;
  }

  /**
   * Returns boolean for current object is equivalent to parameter object.
   * @param o - the object to be determined equal.
   * @return boolean for current object is equivalent to parameter object.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TemplateProcessor that = (TemplateProcessor) o;
    return Objects.equals(this.csvHeaderList, that.csvHeaderList) && Objects.equals(this.csvLine, that.csvLine)
        && Objects.equals(this.template, that.template) && Objects.equals(this.placeHolders, that.placeHolders);
  }

  /**
   * Return integer representation of a Utilities.TemplateProcessor instance.
   * @return integer representation of a Utilities.TemplateProcessor instance.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.csvHeaderList, this.csvLine, this.template, this.placeHolders);
  }

  /**
   * Returns the Utilities.TemplateProcessor instance parameters in a string.
   * @return the Utilities.TemplateProcessor instance parameters in a string.
   */
  @Override
  public String toString() {
    return "Utilities.TemplateProcessor{" +
        "csvHeaderList=" + this.csvHeaderList +
        ", csvLine=" + this.csvLine +
        ", template=" + this.template +
        ", placeHolders=" + this.placeHolders +
        '}';
  }
}
