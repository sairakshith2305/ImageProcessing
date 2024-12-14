package model;

import model.image.Image;
import model.image.ImageImpl;

/**
 * This is a class used to mask the image. Any image that has to be masked can just call the
 * methods of this class and pass the mask image. This function will perform the operation and pass
 * the masked image.
 */
public class MaskImageManipulationImpl implements MaskImageManipulation {

  @Override
  public Image createMaskedImage(Image originalImage, Image resultantImage, Image maskedImage) {
    Image returnImage = new ImageImpl();

    int[][] red = originalImage.getRed();
    int[][] green = originalImage.getGreen();
    int[][] blue = originalImage.getBlue();

    int[][] maskPixels = maskedImage.getRed();

    int[][] finalRed = resultantImage.getRed();
    int[][] finalGreen = resultantImage.getGreen();
    int[][] finalBlue = resultantImage.getBlue();

    int width = originalImage.getImageWidth();
    int height = originalImage.getImageHeight();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {

        if (maskPixels[x][y] != 0) {
          finalRed[x][y] = red[x][y];
          finalGreen[x][y] = green[x][y];
          finalBlue[x][y] = blue[x][y];
        }
      }
    }

    returnImage.setRed(finalRed);
    returnImage.setGreen(finalGreen);
    returnImage.setBlue(finalBlue);
    return returnImage;
  }
}
