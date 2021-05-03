import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import exceptions.InvalidArgumentException;
import exceptions.InvalidPairException;
import static utilities.Constants.*;

/**
 * Utilities.GenerateFiles writes new letter/email files with replaced placeholders.
 */
public class GenerateFiles {
  private static final int FIRST_LINE = 0;
  private int fileNumber;
  private String[] args;

  /**
   * Constructs Utilities.GenerateFiles with String of command line argument.
   * @param args - command line argument as a string
   */
  public GenerateFiles(String[] args) {
    this.args = args;
  }

  /**
   * Outputs new email file with placeholders replaced by appropriate fields.
   * @param parser - parser that contains individual information from the command line
   * @param csvHeaders - list of csv headers
   * @param csvLine - a single line from csv
   * @throws IOException if template file was not found or there's an IO error.
   */
  private void outputNewEmailFile(CommandLineParser parser, ArrayList<String> csvHeaders, ArrayList<String> csvLine)
      throws IOException {
    if (parser.getEmailTemplatePath() != null) {
      TemplateProcessor newEmailFile = new TemplateProcessor(csvHeaders, csvLine,
          parser.getEmailTemplatePath());
      this.outputSingleFile(newEmailFile, parser.getOutputPath());
    }
  }

  /**
   * Outputs new letter file with placeholders replaced by appropriate fields.
   * @param parser - parser that contains individual information from the command line
   * @param csvHeaders - list of csv headers
   * @param csvLine - a single line from csv
   * @throws IOException if template file was not found or there's an IO error.
   */
  private void outputNewLetterFile(CommandLineParser parser, ArrayList<String> csvHeaders, ArrayList<String> csvLine)
      throws IOException {
    if (parser.getLetterTemplatePath() != null) {
      TemplateProcessor newLetterFile = new TemplateProcessor(csvHeaders, csvLine,
          parser.getLetterTemplatePath());
      this.outputSingleFile(newLetterFile, parser.getOutputPath());
    }
  }

  /**
   * Outputs new email and/or letter file with placeholders replaced by appropriate fields.
   * @param line - a single line from csv
   * @param parser - parser that contains individual information from the command line
   * @param csvHeaders - list of csv headers
   * @throws  IOException  if template file was not found or there's an IO error..
   */
  private void outputNewEmailOrLetterFile(String line, CommandLineParser parser, ArrayList<String> csvHeaders)
      throws IOException {
    ArrayList<String> csvLine = new CSVProcessor(line).csvLineList();
    this.outputNewEmailFile(parser, csvHeaders, csvLine);
    this.outputNewLetterFile(parser, csvHeaders, csvLine);
  }

  /**
   * Writes a new email and/or letter files with placeholders replaced with appropriate fields.
   * @throws InvalidArgumentException if csv file was not found.
   * @throws IOException if there is an input output error.
   * @throws InvalidPairException if invalid pair of arguments are provided.
   */
  public void writeNewFiles() throws InvalidArgumentException, IOException, InvalidPairException {
    CommandLineParser parser = new CommandLineParser(this.args);
    BufferedReader file = new BufferedReader(new FileReader(parser.getCsvPath()));
      String line;
      int numberOfCsvLine = 0;
      ArrayList<String> csvHeaders = null;
      this.fileNumber = 0;
      // read csv line by line
      while ((line = file.readLine()) != null) {
        line = line.replaceAll("^\"|\"$", "");
        if (numberOfCsvLine == FIRST_LINE) {
          // make csv header list
          csvHeaders = new CSVProcessor(line).csvLineList();
        } else {
          this.outputNewEmailOrLetterFile(line, parser, csvHeaders);
        }
        this.fileNumber++;
        numberOfCsvLine++;
      }
    }

  /**
   * Creates a new, single file per CSV row with template placeholders replaced with appropriate field.
   * @param file - template processor file requested from command line
   * @param outputDir - output directory of new files
   * @throws IOException if input output error.
   */
  private void outputSingleFile(TemplateProcessor file, String outputDir) throws IOException {
    file.setPlaceHolderFields();
    File newFile = new File(outputDir + File.separator + "output_file_" + fileNumber + ".txt");
    newFile.getParentFile().mkdir();
    List<String> lines = file.readTemplate();
    try (BufferedWriter outputFile = new BufferedWriter(new FileWriter(newFile))){
      for(String line: lines){
        line = file.fillPlaceHolders(line);
        outputFile.write(line + System.lineSeparator());
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
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
    GenerateFiles that = (GenerateFiles) o;
    return this.fileNumber == that.fileNumber && Arrays.equals(this.args, that.args);
  }

  /**
   * Return integer representation of a Utilities.GenerateFiles instance.
   * @return integer representation of a Utilities.GenerateFiles instance.
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(this.fileNumber);
    result = HASH_NUMBER * result + Arrays.hashCode(this.args);
    return result;
  }

  /**
   * Returns the Utilities.GenerateFiles instance parameters in a string.
   * @return the Utilities.GenerateFiles instance parameters in a string.
   */
  @Override
  public String toString() {
    return "Utilities.GenerateFiles{" +
        "fileNumber=" + this.fileNumber +
        ", args=" + Arrays.toString(this.args) +
        '}';
  }
}