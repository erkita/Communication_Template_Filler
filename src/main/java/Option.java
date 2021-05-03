import java.util.Objects;

/**
 * Option class to represent the command line arguments.
 */
public class Option {
  private String opt;
  private boolean hasArg;

  /**
   * Constructor for Option class.
   *
   * @param opt    the option provided in the command line.
   * @param hasArg boolean flag indicating if the command line has arguments.
   */
  public Option(String opt, boolean hasArg) {
    this.opt = opt;
    this.hasArg = hasArg;
  }

  /**
   * Gets the option from the command line.
   *
   * @return the option from the command line.
   */
  public String getOption() {
    return this.opt;
  }

  /**
   * Checks if the command line has arguments.
   *
   * @return true if command line has arguments, false otherwise.
   */
  public boolean hasArg() {
    return this.hasArg;
  }

  /**
   * Sets the command line arguments.
   *
   * @param hasArg boolean flag indicating if the command line has arguments.
   */
  public void setHasArg(boolean hasArg) {
    this.hasArg = hasArg;
  }

  /**
   * Returns the Option instance parameters in a string.
   *
   * @return the Option instance parameters in a string.
   */
  @Override
  public String toString() {
    return "Option{" +
        "opt='" + this.opt + '\'' +
        ", hasArg=" + this.hasArg +
        '}';
  }

  /**
   * Returns boolean for current object is equivalent to parameter object.
   *
   * @param o - The object to be determined equal.
   * @return boolean flag that returns true if the current object is equivalent to the parameter object.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Option option = (Option) o;
    return this.hasArg == option.hasArg && this.getOption().equals(option.getOption());
  }

  /**
   * Return integer representation of an Option instance.
   *
   * @return the integer representation of an Option instance.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getOption(), this.hasArg);
  }
}
