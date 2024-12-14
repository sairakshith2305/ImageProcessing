package controller.command;

import java.io.FileNotFoundException;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;

/**
 * This class is used to get the green component of an image. This class gets the image name for
 * which green component has to be found and stored in the destination image name.
 */
public class GreenComponent implements ImageManipulationsCommand {

  private final String[] commands;

  /**
   * This is a public constructor used to take in the commands enterd by the user.
   *
   * @param command The Array of commands enetered by the user.
   */
  public GreenComponent(String[] command) {
    this.commands = command;
  }

  @Override
  public void returnCommand(ImageManipulation imageManipulation) throws FileNotFoundException {
    if (commands.length == 4) {
      imageManipulation.greenComponentImage(commands[1], commands[2], commands[3]);
    } else {
      imageManipulation.greenComponentImage(commands[1], commands[2]);
    }
  }
}
