package controller.command;

import java.io.FileNotFoundException;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;

/**
 * This class is used to get the grey component of an image. This class takes the image name and
 * destination image name along with an additional parameter to split the image.
 */
public class GreyComponent implements ImageManipulationsCommand {
  private final String[] commands;

  /**
   * This is a public constructor used to initialise the commands specified by the user.
   * @param command The set of grey commands entered by the user.
   */
  public GreyComponent(String[] command) {
    if (command[1].isEmpty() || command[2].isEmpty()) {
      throw new IllegalArgumentException("Arguments are empty");
    }
    this.commands = command;
  }

  @Override
  public void returnCommand(ImageManipulation imageManipulation) throws FileNotFoundException {
    try {
      if (commands.length > 3) {
        if (commands[3].equalsIgnoreCase("split")) {
          imageManipulation.convertIntoGreyScaleImage(commands[1], commands[2],
                  Double.parseDouble(commands[4]));
        }
      } else {
        imageManipulation.convertIntoGreyScaleImage(commands[1], commands[2]);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
