package controller;

import java.io.IOException;
import model.ImageManipulation;

/**
 * This class is used to define each command and parse the user specified command to the
 * model. This creates an object of this command and uses it as a part of the controller to call
 * the respective model functions.
 */
public interface ImageManipulationsCommand {
  /**
   * This function is used to parse the user specified commands to the model.
   * This function calls the respective function associated with the model for the
   * specified functionality.
   *
   * @param imageManipulation Object of the image Manipulation class to perform the specified
   *                          operation.
   * @throws IOException when file or image specified is not found.
   */
  void returnCommand(ImageManipulation imageManipulation) throws IOException;
}
