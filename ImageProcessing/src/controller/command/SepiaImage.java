package controller.command;

import java.io.FileNotFoundException;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;

/**
 * This class is used to generate the sepia of a image. This class takes the image name and the
 * destination image with which the generated image has to be finally stored. The split function
 * is present where we can the split the image for the desired percentage.
 */
public class SepiaImage implements ImageManipulationsCommand {

  private final String[] command;

  /**
   * This is a public constructor used to initialize the user specified parameters.
   *
   * @param command The commands entered by the user.
   */
  public SepiaImage(String[] command) {
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
          imageManipulation.convertIntoSepia(command[1], command[2],
                  Double.parseDouble(command[4]));
        } else if (command.length == 4) {
          imageManipulation.convertIntoSepia(command[1], command[2], command[3]);
        } else {
          System.out.println("Please check the command");
        }
      } else {
        imageManipulation.convertIntoSepia(command[1], command[2]);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
