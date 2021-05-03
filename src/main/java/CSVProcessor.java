import java.util.ArrayList;

import java.util.Arrays;
import java.util.Objects;

/**
 * CSVProcessor makes an input line of CSV row into a list.
 */
public class CSVProcessor {
  private String csvRow;

  /**
   * Constructs CSVProcessor by initializing csvRow.
   * @param csvRow - a single row of csv file as a String
   */
  public CSVProcessor(String csvRow) {
    this.csvRow = csvRow;
  }

  /**
   * Converts string of csv row as a list.
   * Ex. [first_name, last_name, address, ...]
   * @return a list of csv row.
   */
  public ArrayList<String> csvLineList() {
    this.csvRow = this.csvRow.replaceAll("^\"|\"$", "");
    String[] items = this.csvRow.split("\",\"");
    return new ArrayList<>(Arrays.asList(items));
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
    CSVProcessor that = (CSVProcessor) o;
    return Objects.equals(this.csvRow, that.csvRow);
  }

  /**
   * Return integer representation of a CSVProcessor instance.
   * @return integer representation of a CSVProcessor instance.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.csvRow);
  }

  /**
   * Returns the CSVProcessor instance parameters in a string.
   * @return the CSVProcessor instance parameters in a string.
   */
  @Override
  public String toString() {
    return "CSVProcessor{" +
        "csvRow='" + this.csvRow + '\'' +
        '}';
  }
}