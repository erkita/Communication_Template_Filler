import java.io.FileNotFoundException;
import java.io.IOException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidPairException;

/**
 * Calls processing of user input, file reading, and writing.
 */
public class Main {

 /**
  * Handles command line arguments to write new email and/or letter files.
  *
  * @param args command line arguments from user
  * @throws IOException if input output error
  * @throws InvalidPairException if required pair option is not provided
  * @throws InvalidArgumentException if input is not valid
  */
 public static void main(String[] args) {
  try {
   GenerateFiles files = new GenerateFiles(args);
   files.writeNewFiles();
  } catch (FileNotFoundException fnfe) {
   System.out.println("Given file was not found: " + fnfe.getMessage());
  } catch (Exception ioe) {
   System.out.println("Input output error " + ioe.getMessage());
  }
 }
}

