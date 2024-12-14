package controller.command;

import java.io.IOException;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;

/**
 * This class is used to compress an image. This class takes in user inputs like the image name and
 * destination image name which is used to search a file and perform compress on it and store it
 * with the given name of the file.
 */
public class Compress implements ImageManipulationsCommand {
  private final double percentage;
  private final String imageName;
  private final String destImage;

  /**
   * This is a public constructor which is used to initialize the values.
   *
   * @param percentage The percentage of compress required.
   * @param imageName  The image on which compress has to be performed.
   * @param destImage  The image name to be stored after the compress operation has been performed.
   */
  public Compress(double percentage, String imageName, String destImage) {
    if (imageName.isEmpty() || destImage.isEmpty()) {
      throw new IllegalArgumentException("Please check the command");
    }
    this.percentage = percentage;
    this.imageName = imageName;
    this.destImage = destImage;

  }

  @Override
  public void returnCommand(ImageManipulation imageManipulation) throws IOException {
    imageManipulation.compressImage(percentage, imageName, destImage);
  }
}
