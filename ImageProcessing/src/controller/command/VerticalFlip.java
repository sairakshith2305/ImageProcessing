package controller.command;

import java.io.FileNotFoundException;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;

/**
 * This class is used to vertically flip an image. We take in the image which has to be flipped
 * vertically and store it in the destination image name.
 */
public class VerticalFlip implements ImageManipulationsCommand {

  private final String sourceImage;
  private final String destinationImage;

  /**
   * This is a public constructor that is used to initialize the user specified commands.
   *
   * @param sourceImage      The source image which has to be flipped vertically.
   * @param destinationImage The destination image name after the operation is performed.
   */
  public VerticalFlip(String sourceImage, String destinationImage) {
    if (sourceImage.isEmpty() || destinationImage.isEmpty()) {
      throw new IllegalArgumentException("Please enter the correct command");
    }
    this.sourceImage = sourceImage;
    this.destinationImage = destinationImage;
  }

  @Override
  public void returnCommand(ImageManipulation imageManipulation) throws FileNotFoundException {
    imageManipulation.flipImageVertically(sourceImage, destinationImage);
  }
}
