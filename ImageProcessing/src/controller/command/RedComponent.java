package controller.command;

import java.io.FileNotFoundException;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;

/**
 * This is the Red Component class. It takes in image name and the destination image name.
 * This function calls the model with the given parameters to get the blue component of an Image.
 */
public class RedComponent implements ImageManipulationsCommand {

  private final String[] commands;

  /**
   * This is a public constructor that is used to take in commands eneterd by the user.
   *
   * @param command The array of commands entered by the user.
   */
  public RedComponent(String[] command) {
    this.commands = command;
  }


  @Override
  public void returnCommand(ImageManipulation imageManipulation) throws FileNotFoundException {
    if (commands.length == 4) {
      imageManipulation.redComponentImage(commands[1], commands[2], commands[3]);
    } else {
      imageManipulation.redComponentImage(commands[1], commands[2]);
    }

  }
}
