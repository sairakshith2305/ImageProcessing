package controller.command;

import java.io.IOException;
import java.util.List;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;
import utility.ImageUtil;

/**
 * This class is used to save the image into the destination file path mentioned by the user.
 * This class takes the image name to be saved along with the file path where the image has to
 * be stored.
 */
public class SaveImage implements ImageManipulationsCommand {

  private final String filePath;
  private final String imageName;

  /**
   * This is a public constructor that is used to initialize the path and image name.
   *
   * @param path      The path where the image has to be stored.
   * @param imageName The name of the image that has to be stored.
   */
  public SaveImage(String path, String imageName) {
    if (path.isEmpty() || imageName.isEmpty()) {
      throw new IllegalArgumentException("Please enter the correct command");
    }
    this.filePath = path;
    this.imageName = imageName;
  }


  @Override
  public void returnCommand(ImageManipulation imageManipulation) throws IOException {
    String absoluteFilePath = ImageUtil.getAbsolutePath(filePath);
    List<int[][]> getRGB = imageManipulation.saveImage(imageName);
    ImageUtil.saveImage(absoluteFilePath,
            getRGB.get(0), getRGB.get(1), getRGB.get(2));
  }
}
