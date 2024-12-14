package model.imagetuning;

import model.image.Image;
import model.image.ImageImpl;

/**
 * This class takes care of all the tuning operations that can be performed on the image.
 * The class helps brighten,darken, horizontal and vertically flip an Image.
 * This class can be used to add new functionalities to perform any future operations
 * that requires Image tuning.
 */
public class ImageTuningImpl implements ImageTuning {

  private int[][] red;
  private int[][] green;
  private int[][] blue;
  private int[][] newRed;
  private int[][] newGreen;
  private int[][] newBlue;

  private Image resultantImage = new ImageImpl();

  @Override
  public Image brightenImage(int increment, Image image) {

    red = image.getRed();
    green = image.getGreen();
    blue = image.getBlue();

    for (int x = 0; x < image.getImageWidth(); x++) {
      for (int y = 0; y < image.getImageHeight(); y++) {
        red[x][y] = red[x][y] + increment;

        green[x][y] = green[x][y] + increment;
        blue[x][y] = blue[x][y] + increment;

        //Upper limit
        if (red[x][y] > 255) {
          red[x][y] = 255;
        }

        if (green[x][y] > 255) {
          green[x][y] = 255;
        }

        if (blue[x][y] > 255) {
          blue[x][y] = 255;
        }

        //Lower limit
        if (red[x][y] < 0) {
          red[x][y] = 0;
        }

        if (green[x][y] < 0) {
          green[x][y] = 0;
        }

        if (blue[x][y] < 0) {
          blue[x][y] = 0;
        }
      }
    }

    resultantImage.setRed(red);
    resultantImage.setGreen(green);
    resultantImage.setBlue(blue);
    return resultantImage;
  }

  @Override
  public Image horizontalFlipImage(Image image) {
    red = image.getRed();
    green = image.getGreen();
    blue = image.getBlue();

    newRed = new int[image.getImageWidth()][image.getImageHeight()];
    newGreen = new int[image.getImageWidth()][image.getImageHeight()];
    newBlue = new int[image.getImageWidth()][image.getImageHeight()];

    for (int y = 0; y < image.getImageHeight(); y++) {
      for (int x = 0; x < image.getImageWidth(); x++) {
        int leftPixelRed = red[x][y];
        int leftPixelGreen = green[x][y];
        int leftPixelBlue = blue[x][y];

        newRed[image.getImageWidth() - x - 1][y] = leftPixelRed;
        newGreen[image.getImageWidth() - x - 1][y] = leftPixelGreen;
        newBlue[image.getImageWidth() - x - 1][y] = leftPixelBlue;
      }
    }
    resultantImage.setRed(newRed);
    resultantImage.setGreen(newGreen);
    resultantImage.setBlue(newBlue);
    return resultantImage;
  }

  @Override
  public Image verticalFlipImage(Image image) {
    int[][] red = image.getRed();
    int[][] green = image.getGreen();
    int[][] blue = image.getBlue();

    newRed = new int[image.getImageWidth()][image.getImageHeight()];
    newGreen = new int[image.getImageWidth()][image.getImageHeight()];
    newBlue = new int[image.getImageWidth()][image.getImageHeight()];

    for (int y = 0; y < image.getImageHeight(); y++) {
      for (int x = 0; x < image.getImageWidth(); x++) {
        int leftPixelRed = red[x][y];
        int leftPixelGreen = green[x][y];
        int leftPixelBlue = blue[x][y];

        newRed[x][image.getImageHeight() - y - 1] = leftPixelRed;
        newGreen[x][image.getImageHeight() - y - 1] = leftPixelGreen;
        newBlue[x][image.getImageHeight() - y - 1] = leftPixelBlue;
      }
    }
    resultantImage.setRed(newRed);
    resultantImage.setGreen(newGreen);
    resultantImage.setBlue(newBlue);
    return resultantImage;
  }


  // Color correction
  @Override
  public Image colorCorrection(Image image) {
    int[][] red = image.getRed();
    int[][] green = image.getGreen();
    int[][] blue = image.getBlue();

    newRed = new int[image.getImageWidth()][image.getImageHeight()];
    newGreen = new int[image.getImageWidth()][image.getImageHeight()];
    newBlue = new int[image.getImageWidth()][image.getImageHeight()];

    int[] redArray = new int[256];
    int[] greenArray = new int[256];
    int[] blueArray = new int[256];

    for (int y = 0; y < image.getImageHeight(); y++) {
      for (int x = 0; x < image.getImageWidth(); x++) {
        if (red[x][y] > 10 && red[x][y] < 245) {
          redArray[red[x][y]]++;
        }
        if (green[x][y] > 10 && green[x][y] < 245) {
          greenArray[green[x][y]]++;
        }
        if (blue[x][y] > 10 && blue[x][y] < 245) {
          blueArray[blue[x][y]]++;
        }
      }
    }

    int peakRedIntensity = findMax(redArray);
    int peakGreenIntensity = findMax(greenArray);
    int peakBlueIntensity = findMax(blueArray);

    int avgPeakIntensity =
            (int) ((peakRedIntensity + peakGreenIntensity + peakBlueIntensity) / 3.0);

    int offsetRed = avgPeakIntensity - peakRedIntensity;
    int offsetGreen = avgPeakIntensity - peakGreenIntensity;
    int offsetBlue = avgPeakIntensity - peakBlueIntensity;

    for (int y = 0; y < image.getImageHeight(); y++) {
      for (int x = 0; x < image.getImageWidth(); x++) {
        int newPixelRed = red[x][y] + offsetRed;
        int newPixelGreen = green[x][y] + offsetGreen;
        int newPixelBlue = blue[x][y] + offsetBlue;

        if (newPixelRed < 0) {
          newPixelRed = 0;
        }

        if (newPixelRed > 255) {
          newPixelRed = 255;
        }

        if (newPixelGreen < 0) {
          newPixelGreen = 0;
        }

        if (newPixelGreen > 255) {
          newPixelGreen = 255;
        }

        if (newPixelBlue < 0) {
          newPixelBlue = 0;
        }

        if (newPixelBlue > 255) {
          newPixelBlue = 255;
        }

        newRed[x][y] = newPixelRed;
        newGreen[x][y] = newPixelGreen;
        newBlue[x][y] = newPixelBlue;
      }
    }
    resultantImage.setRed(newRed);
    resultantImage.setGreen(newGreen);
    resultantImage.setBlue(newBlue);
    return resultantImage;
  }

  private int findMax(int[] channel) {
    int max = channel[0];
    int index = 0;

    for (int i = 1; i < channel.length; i++) {
      if (channel[i] > max) {
        max = channel[i];
        index = i;
      }
    }
    return index;
  }


  @Override
  public Image levelsAdjustment(double b, double m, double w, Image image) {
    int[][] red = image.getRed();
    int[][] green = image.getGreen();
    int[][] blue = image.getBlue();

    newRed = new int[image.getImageWidth()][image.getImageHeight()];
    newGreen = new int[image.getImageWidth()][image.getImageHeight()];
    newBlue = new int[image.getImageWidth()][image.getImageHeight()];

    double a = (b * b) * (m - w) - (b * ((m * m) - (w * w))) + (w * (m * m) - m * (w * w));
    double aA = (127 * b) + (128 * w) - (255 * m);
    double aB = -127 * (b * b) + 255 * (m * m) - 128 * (w * w);
    double aC = (b * b) * (255 * m - 128 * w) - b * (255 * (m * m) - 128 * (w * w));
    double aEq = aA / a;
    double bEq = aB / a;
    double cEq = aC / a;

    for (int x = 0; x < image.getImageWidth(); x++) {
      for (int y = 0; y < image.getImageHeight(); y++) {
        int adjustedIntensityRed = substitute(aEq, bEq, cEq, b, w, red[x][y]);
        int adjustedIntensityGreen = substitute(aEq, bEq, cEq, b, w, green[x][y]);
        int adjustedIntensityBlue = substitute(aEq, bEq, cEq, b, w, blue[x][y]);

        if (adjustedIntensityRed > 255) {
          adjustedIntensityRed = 255;
        }

        if (adjustedIntensityGreen > 255) {
          adjustedIntensityGreen = 255;
        }

        if (adjustedIntensityBlue > 255) {
          adjustedIntensityBlue = 255;
        }

        if (adjustedIntensityRed < 0) {
          adjustedIntensityRed = 0;
        }

        if (adjustedIntensityGreen < 0) {
          adjustedIntensityGreen = 0;
        }

        if (adjustedIntensityBlue < 0) {
          adjustedIntensityBlue = 0;
        }

        newRed[x][y] = adjustedIntensityRed;
        newGreen[x][y] = adjustedIntensityGreen;
        newBlue[x][y] = adjustedIntensityBlue;
      }
    }

    resultantImage.setRed(newRed);
    resultantImage.setGreen(newGreen);
    resultantImage.setBlue(newBlue);
    return resultantImage;
  }

  private int substitute(double aEq, double bEq, double cEq, double b, double w, int pixel) {
    if (pixel <= b) {
      return (int) b;
    } else if (pixel >= w) {
      return (int) w;
    }
    return (int) (aEq * Math.pow(pixel, 2) + (bEq * pixel) + cEq);
  }

  @Override
  public Image downscaleImage(Image sourceImage, int newWidth, int newHeight) {
    int originalWidth = sourceImage.getImageWidth();
    int originalHeight = sourceImage.getImageHeight();

    red = sourceImage.getRed();
    green = sourceImage.getGreen();
    blue = sourceImage.getBlue();

    newRed = new int[newWidth][newHeight];
    newGreen = new int[newWidth][newHeight];
    newBlue = new int[newWidth][newHeight];

    double xRatio = (double) originalWidth / newWidth;
    double yRatio = (double) originalHeight / newHeight;

    for (int x = 0; x < newWidth; x++) {
      for (int y = 0; y < newHeight; y++) {
        double srcX = x * xRatio;
        double srcY = y * yRatio;

        int xFloor = (int) Math.floor(srcX);
        int yFloor = (int) Math.floor(srcY);
        int xCeil = Math.min(ceil(srcX), originalWidth);
        int yCeil = Math.min(ceil(srcY), originalHeight);

        int[] surroundingRed = getSurroundingPixels(red, xFloor, yFloor, xCeil, yCeil);
        int[] surroundingGreen = getSurroundingPixels(green, xFloor, yFloor, xCeil, yCeil);
        int[] surroundingBlue = getSurroundingPixels(blue, xFloor, yFloor, xCeil, yCeil);

        newRed[x][y] = interpolateColor(surroundingRed[0], surroundingRed[1],
                surroundingRed[2], surroundingRed[3], srcX, srcY, xFloor, yFloor);

        newGreen[x][y] = interpolateColor(surroundingGreen[0], surroundingGreen[1],
                surroundingGreen[2], surroundingGreen[3], srcX, srcY, xFloor, yFloor);

        newBlue[x][y] = interpolateColor(surroundingBlue[0], surroundingBlue[1],
                surroundingBlue[2], surroundingBlue[3], srcX, srcY, xFloor, yFloor);
      }
    }
    resultantImage.setRed(newRed);
    resultantImage.setGreen(newGreen);
    resultantImage.setBlue(newBlue);
    return resultantImage;
  }

  private int[] getSurroundingPixels(int[][] sourceChannel, int xFloor, int yFloor, int xCeil,
                                     int yCeil) {
    return new int[] {
            sourceChannel[xFloor][yFloor],
            sourceChannel[xCeil][yFloor],
            sourceChannel[xFloor][yCeil],
            sourceChannel[xCeil][yCeil]
    };
  }

  private int interpolateColor(int ca, int cb, int cc, int cd, double x, double y, int xFloor,
                               int yFloor) {
    double m = (cb * (x - xFloor)) + (ca * (ceil(x) - x));
    double n = (cd * (x - xFloor)) + (cc * (ceil(x) - x));
    return (int) ((n * (y - yFloor)) + (m * (ceil(y) - y)));
  }


  private int ceil(double x) {
    return (int) x + 1;
  }
}
