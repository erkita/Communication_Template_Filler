
package exceptions;

/**
 * Exception thrown if invalid arguments are provided at the command line.
 */
public class InvalidPairException extends Exception {
    public InvalidPairException(String message) {
      super("Error: " + message + System.lineSeparator() + "Usage: " + System.lineSeparator()
          + "--email Generate email messages. If this option is provided, then --email-template must also be provided." + System.lineSeparator()
          + "--email-template <path/to/file> A filename for the email template. --letter Generate letters. " + System.lineSeparator()
          + "If this option is provided, then --letter-template must also be provided." + System.lineSeparator()
          + "--letter-template <path/to/file> A filename for the letter template." + System.lineSeparator()
          + "--output-dir <path/to/folder> The folder to store all generated files. This option is required." + System.lineSeparator()
          + "--csv-file <path/to/folder> The CSV file to process. This option is required." + System.lineSeparator()
          + "Examples: " + System.lineSeparator()
          + "--email --email-template email-template.txt --output-dir emails --csv-file customer.csv" + System.lineSeparator()
          + "--letter --letter-template letter-template.txt --output-dir letters --csv-file customer.csv");
    }
  }

