package controller.command;

import java.io.FileNotFoundException;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;

/**
 * This is the blue Component class. It takes in image name and the destination image name.
 * This function calls the model with the given parameters to get the blue component of an Image.
 */
public class BlueComponent implements ImageManipulationsCommand {

  private final String[] command;

  /**
   * This is a public constructors that takes an Array of commands given by the user.
   *
   * @param commands The commands to be performed.
   */
  public BlueComponent(String[] commands) {
    this.command = commands;
  }

  @Override
  public void returnCommand(ImageManipulation imageManipulation) throws FileNotFoundException {
    if (command.length == 4) {
      imageManipulation.blueComponentImage(command[1], command[2], command[3]);
    } else {
      imageManipulation.blueComponentImage(command[1], command[2]);
    }
  }
}
