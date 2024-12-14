package controller.command;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import controller.ImageManipulationsCommand;
import model.ImageManipulation;
import utility.ImageUtil;

/**
 * This class is used to get the histogram for a specified image.
 * We take the image name to generate the histogram and store it back in the form of an image.
 */
public class Histogram implements ImageManipulationsCommand {

  private final String imageName;
  private final String destinationImageName;

  /**
   * This is a public constructor used to initialize image name and the destination image name
   * specified by the user.
   *
   * @param image            The name of the image for which histogram has to be generated.
   * @param destinationImage The destination image name post generation of histogram.
   */
  public Histogram(String image, String destinationImage) {
    if (image.isEmpty() || destinationImage.isEmpty()) {
      throw new IllegalArgumentException("Image and destination image cannot be empty");
    }
    this.imageName = image;
    this.destinationImageName = destinationImage;
  }

  @Override
  public void returnCommand(ImageManipulation imageManipulation) throws IOException {
    List<Map<Integer, Integer>> getRGBIntensityValues;
    List<int[][]> histogramPixels;
    getRGBIntensityValues = imageManipulation.histogram(imageName);
    histogramPixels = ImageUtil.plotHistogram(getRGBIntensityValues.get(0),
            getRGBIntensityValues.get(1), getRGBIntensityValues.get(2));
    imageManipulation.loadImage(histogramPixels, destinationImageName);
  }
}
