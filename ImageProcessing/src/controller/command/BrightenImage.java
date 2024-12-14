package controller.command;

import java.io.FileNotFoundException;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;

/**
 * This is the brighten image function which is used to brighten a given image.
 * This image takes in a value by which the image has to be brightened, a filename and
 * the image name to be stored after the operation.
 */
public class BrightenImage implements ImageManipulationsCommand {

  private final int value;
  private final String fileName;
  private final String destinationImageName;

  /**
   * This is a public constructor which is used to initialize the parameters required for
   * brightning the image.
   *
   * @param value                The value by which the image has to be brightened.
   * @param fileName             The image file which has to be brightened.
   * @param destinationImageName The image name to be stored after the operation.
   */
  public BrightenImage(int value, String fileName, String destinationImageName) {
    if (fileName.isEmpty() || destinationImageName.isEmpty()) {
      throw new IllegalArgumentException("Please enter a Valid command");
    }
    this.value = value;
    this.fileName = fileName;
    this.destinationImageName = destinationImageName;
  }

  @Override
  public void returnCommand(ImageManipulation imageManipulation) throws FileNotFoundException {
    imageManipulation.brightenImage(value, fileName, destinationImageName);
  }
}
