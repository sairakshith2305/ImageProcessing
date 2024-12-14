package controller.command;

import java.io.FileNotFoundException;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;

/**
 * This is a command class that is used to take the command for DownSizing. This class takes in
 * takes the image, new height and new width of the image to which it has to be downsized.
 */
public class DownSize implements ImageManipulationsCommand {

  private final String imageName;
  private final double width;
  private final double height;
  private String destImage;

  /**
   * This is a public constructor take in commands given by the user.
   *
   * @param smallWidth      The new width of the image.
   * @param smallHeight     The new Height of the image.
   * @param image           The image which has to be downsized.
   * @param destinatioImage The destination name of the image.
   */
  public DownSize(double smallWidth, double smallHeight, String image, String destinatioImage) {
    if (image.isEmpty() || destinatioImage.isEmpty()) {
      throw new IllegalArgumentException("Image or destination image cannot be empty");
    }
    this.imageName = image;
    this.width = smallWidth;
    this.height = smallHeight;
    this.destImage = destinatioImage;
  }

  @Override
  public void returnCommand(ImageManipulation imageManipulation) throws FileNotFoundException {
    imageManipulation.downScaleImage(width, height, imageName, destImage);
  }
}
