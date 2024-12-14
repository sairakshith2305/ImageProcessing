package controller.command;

import java.io.FileNotFoundException;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;

/**
 * This class is used to get the combine the red, green and blue component of the image.
 * This takes the red, green and blue component images from the user and give a combined image.
 */
public class RGBCombine implements ImageManipulationsCommand {

  private final String redImage;
  private final String greenImage;
  private final String blueImage;
  private final String destinationImage;

  /**
   * This is a public constructor that is used to initialize images provided by the user.
   *
   * @param destImage  The image name to be stored after the operation.
   * @param redImage   The red image given by the user.
   * @param greenImage The green image given by the user.
   * @param blueImage  The blue image given by the user.
   */
  public RGBCombine(String destImage, String redImage, String greenImage, String blueImage) {
    if (redImage.isEmpty() || greenImage.isEmpty() || blueImage.isEmpty()) {
      throw new IllegalArgumentException(" please enter the correct command");
    }
    this.redImage = redImage;
    this.greenImage = greenImage;
    this.blueImage = blueImage;
    this.destinationImage = destImage;
  }

  @Override
  public void returnCommand(ImageManipulation imageManipulation) throws FileNotFoundException {
    imageManipulation.combineRGBImage(destinationImage, redImage, greenImage, blueImage);
  }
}
