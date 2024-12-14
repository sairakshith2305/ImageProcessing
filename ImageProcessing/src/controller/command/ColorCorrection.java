package controller.command;

import java.io.FileNotFoundException;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;

/**
 * This is a color correction class which is used to color correct the image. This class is used
 * to correct thw given image using the image Name, destination image name and also perform a split
 * operation is specified by the user.
 */
public class ColorCorrection implements ImageManipulationsCommand {

  private final String[] commands;

  /**
   * This is a public constructor used to initialize the commands.
   *
   * @param command The commands entered by the user.
   */
  public ColorCorrection(String[] command) {
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
          imageManipulation.colorCorrectionImage(commands[1], commands[2],
                  Double.parseDouble(commands[4]));
        } else {
          System.out.println("Please check the command");
        }
      } else {
        imageManipulation.colorCorrectionImage(commands[1], commands[2]);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
