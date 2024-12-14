package controller.command;

import java.io.FileNotFoundException;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;

/**
 * This class is used to implement the level correction for an image.
 * This class supports the split function as well which splits the given image.
 * The b,m,w values are collected from the user and the user specified image is level corrected.
 */
public class LevelAdjustment implements ImageManipulationsCommand {
  private final String[] command;

  /**
   * This is a public constructor that is used to initialize the user specified commands.
   *
   * @param commands The user specified commands.
   */
  public LevelAdjustment(String[] commands) {
    if (commands[1].isEmpty() || commands[2].isEmpty()) {
      throw new IllegalArgumentException("Arguments are empty");
    }
    this.command = commands;
  }

  @Override
  public void returnCommand(ImageManipulation imageManipulation) throws FileNotFoundException {
    try {
      if (command.length > 6) {
        if (command[6].equalsIgnoreCase("split")) {
          imageManipulation.levelsAdjustmentImage(Double.parseDouble(command[1]),
                  Double.parseDouble(command[2]), Double.parseDouble(command[3]),
                  command[4], command[5], Double.parseDouble(command[7]));
        } else {
          System.out.println("Please check the command");
        }
      } else {
        imageManipulation.levelsAdjustmentImage(Double.parseDouble(command[1]),
                Double.parseDouble(command[2]), Double.parseDouble(command[3]),
                command[4], command[5]);
      }
    } catch (Exception e) {
      System.out.println("Invaild Input: " + e.getMessage());
    }
  }
}
