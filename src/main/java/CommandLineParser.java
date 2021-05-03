import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import exceptions.InvalidArgumentException;
import exceptions.InvalidPairException;
import static utilities.Constants.*;

/**
 * Parses and validates command line arguments from Utilities.Main.
 */
public class CommandLineParser {
  private HashMap<Option,Integer> optionIndexes;
  private Set<Option> options;
  private String[] args;
  private String emailPath, letterPath, outputPath,csvPath;
  Option email = new Option(EMAIL,false);
  Option emailTemp = new Option(EMAIL_TEMPLATE,true);
  Option letter = new Option(LETTER,false);
  Option letterTemp = new Option(LETTER_TEMPLATE,true);
  Option csv = new Option(CSV,true);
  Option output = new Option(OUTPUT,true);

  /**
   * Constructs CommandLineParser by initializing email template, letter template,
   * output location, and CSV location specified in a command line argument.
   * @param args - command line argument as a string
   * @throws InvalidArgumentException if option argument is missing or option does not have an argument.
   * @throws InvalidPairException if paired option is not provided
   */
  public CommandLineParser(String[] args) throws InvalidArgumentException, InvalidPairException {
    this.args = null;
    this.addOptions();
    this.parse(args);
    this.setFilePaths();
  }

  /**
   * Adds each command line arguments into an options map.
   */
  private void addOptions() {
    this.options = new HashSet<>();
    this.optionIndexes = new HashMap<>();
    this.options.add(email);
    this.options.add(emailTemp);
    this.options.add(letter);
    this.options.add(letterTemp);
    this.options.add(csv);
    this.options.add(output);
  }

  /**
   * Sets email and/or letter file path if it was correctly entered as a pair and
   * there exists an option value for the respective template.
   * @throws InvalidPairException if a coupled argument is missing one of required argument.
   * @throws InvalidArgumentException if invalid argument is provided.
   */
  private void setFilePaths() throws InvalidPairException, InvalidArgumentException {
    if(this.isValidInput()) {
      if (this.hasOption(this.emailTemp)) {
        this.emailPath = this.getOptionValue(this.emailTemp);
      }
      if (this.hasOption(this.letterTemp))
        this.letterPath = this.getOptionValue(this.letterTemp);
    }
  }

  /**
   * Returns path of email template file.
   * @return path of email template file.
   */
  public String getEmailTemplatePath() {
    return this.emailPath;
  }

  /**
   * Returns path of letter template file.
   * @return path of letter template file.
   */
  public String getLetterTemplatePath() {
    return this.letterPath;
  }

  /**
   * Returns path of csv file to be processed.
   * @return path of csv file to be processed.
   */
  public String getCsvPath() {
    return this.csvPath;
  }

  /**
   * Returns folder path to store all generated files.
   * @return folder path to store all generated files.
   */
  public String getOutputPath() {
    return this.outputPath;
  }

  /**
   * Parses through the command line argument.
   * @param arguments - command line argument
   */
  private void parse(String[] arguments) {
    this.args = arguments;
    for (int i = 0; i < this.args.length; i++) {
      if (this.args[i].startsWith("--")) {
        Option option = new Option(this.args[i], true);
        if (option.getOption().equals(EMAIL) || option.getOption().equals(LETTER)) {
          option.setHasArg(false);
        }
        if (this.options.contains(option)) {
          this.optionIndexes.put(option, i);
        }
      }
    }
  }

  /**
   * Returns true if option exists, false otherwise.
   * @param option - specified option to be checked for
   * @return true if option exists, false otherwise.
   */
  private boolean hasOption(Option option){
    return this.optionIndexes.containsKey(option);
  }

  /**
   * Returns existing, correctly formatted option value.
   * @param option - option to get value from
   * @return existing, correctly formatted option value.
   * @throws InvalidArgumentException if option argument is missing or option does not have an argument.
   */
  private String getOptionValue(Option option) throws InvalidArgumentException {
    if (option.hasArg()) {
      int optionIndex = this.optionIndexes.get(option);
      if (optionIndex + 1 < this.args.length) {
        if (!this.args[optionIndex + 1].startsWith("--")) {
          return this.args[optionIndex + 1];
        }
        throw new InvalidArgumentException(option.getOption() + "is missing an argument");
      }
    }
    throw new InvalidArgumentException(option.getOption() + "does not have argument");
  }

  /**
   * Ensures command line argument has minimum required arguments.
   * @return true if command line argument has minimum required arguments, false otherwise.
   */
  private boolean hasRequiredOptions(){
    return this.hasOption(output) && this.hasOption(csv);
  }

  /**
   * Returns true if the argument is expected to have a required command that follows immediately. False otherwise.
   * @param option1 - first command
   * @param option2 - second command immediately following the first command
   * @return true if the argument is expected to have a required command that follows immediately. False otherwise.
   * @throws InvalidPairException if paired option is not provided
   */
  private boolean hasPairedOption(Option option1, Option option2) throws InvalidPairException {
    if (this.hasOption(option1)) {
      if (this.hasOption(option2)) {
        return true;
      } else {
        throw new InvalidPairException(
            option1.getOption() + " provided but no" + option2.getOption() + " was given");
      }
    }
    return false;
  }

  /**
   * Ensures input has required pair of command line arguments.
   * @return true if input has required pair of command line arguments, false otherwise.
   * @throws InvalidArgumentException if required option is missing.
   * @throws InvalidPairException if one of the required pair option is missing.
   */
  private boolean isValidInput() throws InvalidArgumentException, InvalidPairException {
    if (this.hasRequiredOptions()) {
      this.csvPath = this.getOptionValue(csv);
      this.outputPath = this.getOptionValue(output);
      if (this.hasPairedOption(this.email, this.emailTemp) || this.hasPairedOption(this.letter, this.letterTemp)) {
        return true;
      }
    }
    throw new InvalidArgumentException("Required option missing!");
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
    CommandLineParser commandLineParser = (CommandLineParser) o;
    return Arrays.equals(args, commandLineParser.args) && Objects
        .equals(this.getEmailTemplatePath(), commandLineParser.getEmailTemplatePath()) && Objects
        .equals(this.getLetterTemplatePath(), commandLineParser.getLetterTemplatePath()) && this.getOutputPath()
        .equals(commandLineParser.getOutputPath()) && this.getCsvPath().equals(commandLineParser.getCsvPath());
  }


  /**
   * Return integer representation of a CommandLineParser instance.
   * @return integer representation of a CommandLineParser instance.
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(this.getEmailTemplatePath(), this.getLetterTemplatePath(), this.getOutputPath(),
        this.getCsvPath());
    return HASH_NUMBER * result + Arrays.hashCode(this.args);
  }

  /**
   * Return string representation of a CommandLineParser instance.
   * @return string representation of a CommandLineParser instance.
   */
  @Override
  public String toString() {
    return "CmdParser{" +
        "args=" + Arrays.toString(this.args) +
        '}';
  }
}