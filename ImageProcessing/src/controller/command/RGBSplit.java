package controller.command;

import java.io.FileNotFoundException;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;

/**
 * This class is used to split the image into red, green and blue component of an image.
 * The images split are stored into their respective channel names.
 */
public class RGBSplit implements ImageManipulationsCommand {
  private final String imageName;
  private final String destinationImageRed;
  private final String destinationImageGreen;
  private final String destinationImageBlue;

  /**
   * This is a public constructor that is used to initialize the images specified by the user.
   *
   * @param imageName             The image to be split.
   * @param destinationImageRed   The image name for the red component.
   * @param destinationImageGreen The image name for the green component.
   * @param destinationImageBlue  The image name for the blue component.
   */
  public RGBSplit(String imageName, String destinationImageRed, String destinationImageGreen,
                  String destinationImageBlue) {
    if (imageName.isEmpty() || destinationImageRed.isEmpty() || destinationImageGreen.isEmpty()
            || destinationImageBlue.isEmpty()) {
      throw new IllegalArgumentException("Please enter a valid Command");
    }

    this.imageName = imageName;
    this.destinationImageRed = destinationImageRed;
    this.destinationImageGreen = destinationImageGreen;
    this.destinationImageBlue = destinationImageBlue;
  }


  @Override
  public void returnCommand(ImageManipulation imageManipulation) throws FileNotFoundException {
    imageManipulation.splitRGBImage(imageName, destinationImageRed, destinationImageGreen,
            destinationImageBlue);
  }
}
