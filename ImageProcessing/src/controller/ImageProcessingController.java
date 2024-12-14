package controller;

import java.io.IOException;

/**
 * This interface is for the controller of the Program.
 * This takes input from the user and delegates it to the respective
 * ImageManipulation class to process the given input.
 */
public interface ImageProcessingController {
  /**
   * This function is used to run the script file given by the user.
   *
   * @param inputFileCommands The input file which has the list of commands to be executed.
   * @throws IOException Throws an error in case of file read/write operations.
   */
  void runScriptFile(String inputFileCommands) throws IOException;

  /**
   * This function is used to execute the commands given by the user from the CLI as well as the
   * script file.
   *
   * @param command an array of commands to be executed.
   */
  void executeCommand(String[] command);

  /**
   * This function gives control to the controller and starts the execution of the program.
   *
   * @throws IOException when file read/write operation fails.
   */
  void startCommandExecution() throws IOException;
}
