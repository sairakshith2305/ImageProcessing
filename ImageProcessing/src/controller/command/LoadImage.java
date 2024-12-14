package controller.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;
import utility.ImageUtil;

/**
 * This class is used to load the image. The loaded image is then sent to the model to store
 * the image with name given by the user.
 */
public class LoadImage implements ImageManipulationsCommand {

  private final String filePath;
  private final String imageName;

  /**
   * This is a public constructor which used to initialize the image path and destination image
   * name.
   *
   * @param imagePath the file path where the source image is present.
   * @param fileName  The file name used to store the source image.
   */
  public LoadImage(String imagePath, String fileName) {
    if (imagePath.isEmpty() || fileName.isEmpty()) {
      throw new IllegalArgumentException("please check the command");
    }
    this.filePath = imagePath;
    this.imageName = fileName;
  }


  @Override
  public void returnCommand(ImageManipulation imageManipulation) throws IOException {

    List<int[][]> getRGB = new ArrayList<>();
    String file = ImageUtil.getAbsolutePath(filePath);
    if (ImageUtil.getFileExtension(file).equals("ppm")) {
      getRGB = ImageUtil.readPPM(file);
    } else {
      getRGB = ImageUtil.loadImage(file);
    }
    imageManipulation.loadImage(getRGB, imageName);
  }
}
