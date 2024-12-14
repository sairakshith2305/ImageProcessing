package controller.command;

import java.io.FileNotFoundException;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;

/**
 * This is the blur Image class of the controller which is used to blur the image.
 * This takes in an array of string commands which has the image name, destination image name.
 * This function also provides the functionality to split a command where the controller
 * understands on seeing the split command that split operation has to be called.
 */
public class BlurImage implements ImageManipulationsCommand {

  private final String[] command;

  /**
   * This is a public constructor that is used to initialize the commands.
   *
   * @param command The commands for performing the blur operation.
   */
  public BlurImage(String[] command) {
    if (command[1].isEmpty() || command[2].isEmpty()) {
      throw new IllegalArgumentException("Arguments are empty");
    }
    this.command = command;
  }


  @Override
  public void returnCommand(ImageManipulation imageManipulation) throws FileNotFoundException {
    try {
      if (command.length > 3) {
        if (command[3].equalsIgnoreCase("split")) {
          imageManipulation.blurImage(command[1], command[2], Double.parseDouble(command[4]));
        } else if (command.length == 4) {
          imageManipulation.blurImage(command[1], command[2], command[3]);
        } else {
          System.out.println("Please check the command");
        }
      } else {
        imageManipulation.blurImage(command[1], command[2]);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
