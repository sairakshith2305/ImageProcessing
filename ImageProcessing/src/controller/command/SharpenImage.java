package controller.command;

import java.io.FileNotFoundException;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;

/**
 * This class is used to sharpen the image. This takes the image name to sharpen and stores it
 * with the destination image name that is given by the user. This feature also supports the split
 * functionality where the image can be split to the desired percentage.
 */
public class SharpenImage implements ImageManipulationsCommand {

  private final String[] command;

  /**
   * This is a public constructor that is used to initialize commands entered from the user.
   *
   * @param commands The commands specified by the user.
   */
  public SharpenImage(String[] commands) {
    if (commands[1].isEmpty() || commands[2].isEmpty()) {
      throw new IllegalArgumentException("Arguments are empty");
    }
    this.command = commands;
  }


  @Override
  public void returnCommand(ImageManipulation imageManipulation) throws FileNotFoundException {
    try {
      if (command.length > 3) {
        if (command[3].equalsIgnoreCase("split")) {
          imageManipulation.sharpenImage(command[1], command[2], Double.parseDouble(command[4]));
        } else if (command.length == 4) {
          imageManipulation.sharpenImage(command[1], command[2], command[3]);
        } else {
          System.out.println("Please check the command");
        }
      } else {
        imageManipulation.sharpenImage(command[1], command[2]);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
