package controller.command;

import java.io.FileNotFoundException;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;

/**
 * This class is used to get the value component of the image. The class takes in an image to
 * generate its value component and save the image to the given destination. The split operation
 * can also be performed in this function where we can specify the split percentage.
 */
public class ValueComponent implements ImageManipulationsCommand {
  private final String[] commands;

  /**
   * This is a public constructor that initializes the commands entered by the user.
   *
   * @param command The commands entered by the user.
   */
  public ValueComponent(String[] command) {
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
          imageManipulation.valueComponentOfImage(commands[1], commands[2],
                  Double.parseDouble(commands[4]));
        } else if (commands.length == 4) {
          imageManipulation.valueComponentOfImage(commands[1], commands[2], commands[3]);
        } else {
          System.out.println("Please check the command");
        }
      } else {
        imageManipulation.valueComponentOfImage(commands[1], commands[2]);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
