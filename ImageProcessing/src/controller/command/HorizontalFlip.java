package controller.command;

import java.io.FileNotFoundException;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;

/**
 * This class is used to horizontally flip an image. This takes the image name and
 * destination image from the user to flip the image horizontally.
 */
public class HorizontalFlip implements ImageManipulationsCommand {

  private final String sourceImage;
  private final String destinationImage;

  /**
   * This is a public constructor that is used to initialize the image name and the destination
   * image specified by the user.
   *
   * @param sourceImage      The name of the image which has to be flipped horizontally.
   * @param destinationImage The destination name of image post operation performed.
   */
  public HorizontalFlip(String sourceImage, String destinationImage) {
    if (sourceImage.isEmpty() || destinationImage.isEmpty()) {
      throw new IllegalArgumentException("Please enter the correct Input");
    }
    this.sourceImage = sourceImage;
    this.destinationImage = destinationImage;
  }

  @Override
  public void returnCommand(ImageManipulation imageManipulation) throws FileNotFoundException {
    imageManipulation.flipImageHorizontally(sourceImage, destinationImage);
  }
}
