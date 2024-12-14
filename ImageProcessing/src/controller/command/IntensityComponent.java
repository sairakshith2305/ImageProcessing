package controller.command;

import java.io.FileNotFoundException;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;

/**
 * This class is used get the intensity component of an image. This takes the image name
 * and the destination image name. This class also allows for the option of split operation
 * where the first half is the intensity component and the second half is the original image.We can
 * specify the percentage of split required.
 */
public class IntensityComponent implements ImageManipulationsCommand {

  private final String[] command;

  /**
   * This is a public constructor used to initialize the commands specified by the user.
   *
   * @param commands The user specified commands.
   */
  public IntensityComponent(String[] commands) {
    if (commands[1].isEmpty() || commands[2].isEmpty()) {
      throw new IllegalArgumentException(" Please enter the correct command");
    }
    this.command = commands;
  }

  @Override
  public void returnCommand(ImageManipulation imageManipulation) throws FileNotFoundException {
    try {
      if (command.length > 3) {
        if (command[3].equalsIgnoreCase("split")) {
          imageManipulation.intensityComponentOfImage(command[1], command[2],
                  Double.parseDouble(command[4]));
        } else if (command.length == 4) {
          imageManipulation.intensityComponentOfImage(command[1], command[2], command[3]);
        } else {
          System.out.println("Please check the command");
        }
      } else {
        imageManipulation.intensityComponentOfImage(command[1], command[2]);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
