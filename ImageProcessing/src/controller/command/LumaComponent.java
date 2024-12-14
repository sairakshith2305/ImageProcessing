package controller.command;

import java.io.FileNotFoundException;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;

/**
 * This class is used get the luma component of an image. This takes the image name
 * and the destination image name. This class also allows for the option of split operation
 * where the first half is the luma component and the second half is the original image. We can
 * specify the percentage of split required.
 */
public class LumaComponent implements ImageManipulationsCommand {

  private final String[] commands;

  /**
   * This function is used to initialize the commands specified by the user.
   *
   * @param command The commands specified by the user which has to be performed.
   */
  public LumaComponent(String[] command) {
    if (command[1].isEmpty() || command[2].isEmpty()) {
      throw new IllegalArgumentException("Please enter the correct command");
    }
    this.commands = command;
  }


  @Override
  public void returnCommand(ImageManipulation imageManipulation) throws FileNotFoundException {
    try {
      if (commands.length > 3) {
        if (commands[3].equalsIgnoreCase("split")) {
          imageManipulation.lumaComponentOfImage(commands[1], commands[2],
                  Double.parseDouble(commands[4]));
        } else if (commands.length == 4) {
          imageManipulation.lumaComponentOfImage(commands[1], commands[2], commands[3]);
        } else {
          System.out.println("Please check the command");
        }
      } else {
        imageManipulation.lumaComponentOfImage(commands[1], commands[2]);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
