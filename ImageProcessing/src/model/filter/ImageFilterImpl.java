package model.filter;

import java.util.Arrays;

import model.image.Image;
import model.image.ImageImpl;

/**
 * This is an implementation class for the Image filters. This class stores the kernels for each
 * function required in the class and performs the necessary operations to give the desired filter
 * outputs.
 */
public class ImageFilterImpl implements ImageFilter {

  private double[][] gaussiankernel = {
          {1.0 / 16, 2.0 / 16, 1.0 / 16},
          {2.0 / 16, 4.0 / 16, 2.0 / 16},
          {1.0 / 16, 2.0 / 16, 1.0 / 16}
  };

  private double[][] sharpeningFilter = {
          {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
  };

  private Image resultantImage = new ImageImpl();
  private int[][] red;
  private int[][] green;
  private int[][] blue;

  @Override
  public Image convertImageToSepia(Image image) {
    red = image.getRed();
    green = image.getGreen();
    blue = image.getBlue();

    int[][] spehiaRed = new int[image.getImageWidth()][image.getImageHeight()];
    int[][] spehiaGreen = new int[image.getImageWidth()][image.getImageHeight()];
    int[][] spehiaBlue = new int[image.getImageWidth()][image.getImageHeight()];

    for (int x = 0; x < image.getImageWidth(); x++) {
      for (int y = 0; y < image.getImageHeight(); y++) {
        spehiaRed[x][y] = (int) (0.393 * red[x][y] + 0.769 * green[x][y]
                + 0.189 * blue[x][y]);
        spehiaGreen[x][y] = (int) (0.349 * red[x][y] + 0.686 * green[x][y]
                + 0.168 * blue[x][y]);
        spehiaBlue[x][y] = (int) (0.272 * red[x][y] + 0.534 * green[x][y]
                + 0.131 * blue[x][y]);

        if (spehiaRed[x][y] > 255) {
          spehiaRed[x][y] = 255;
        }
        if (spehiaGreen[x][y] > 255) {
          spehiaGreen[x][y] = 255;
        }
        if (spehiaBlue[x][y] > 255) {
          spehiaBlue[x][y] = 255;
        }
      }
    }
    resultantImage.setRed(spehiaRed);
    resultantImage.setGreen(spehiaGreen);
    resultantImage.setBlue(spehiaBlue);
    return resultantImage;
  }

  @Override
  public Image blurImage(Image image) {
    int height = image.getImageHeight();
    int width = image.getImageWidth();

    red = image.getRed();
    green = image.getGreen();
    blue = image.getBlue();

    red = padImage(1, height, width, red);
    green = padImage(1, height, width, green);
    blue = padImage(1, height, width, blue);

    int widthPadded = red.length;
    int heightPadded = red[0].length;

    red = filterComponent(red, heightPadded, widthPadded, gaussiankernel, 1);
    green = filterComponent(green, heightPadded, widthPadded, gaussiankernel, 1);
    blue = filterComponent(blue, heightPadded, widthPadded, gaussiankernel, 1);

    red = removePadding(1, height, width, red);
    green = removePadding(1, height, width, green);
    blue = removePadding(1, height, width, blue);

    resultantImage.setRed(red);
    resultantImage.setGreen(green);
    resultantImage.setBlue(blue);
    return resultantImage;
  }

  @Override
  public Image sharpenImage(Image image) {
    int width = image.getImageWidth();
    int height = image.getImageHeight();

    red = image.getRed();
    green = image.getGreen();
    blue = image.getBlue();

    red = padImage(2, height, width, red);
    green = padImage(2, height, width, green);
    blue = padImage(2, height, width, blue);

    int widthPadded = red.length;
    int heightPadded = red[0].length;

    red = filterComponent(red, heightPadded, widthPadded, sharpeningFilter, 2);
    green = filterComponent(green, heightPadded, widthPadded, sharpeningFilter, 2);
    blue = filterComponent(blue, heightPadded, widthPadded, sharpeningFilter, 2);

    red = removePadding(2, height, width, red);
    green = removePadding(2, height, width, green);
    blue = removePadding(2, height, width, blue);

    resultantImage.setRed(red);
    resultantImage.setGreen(green);
    resultantImage.setBlue(blue);
    return resultantImage;
  }


  @Override
  public Image greyScaleImage(Image image) {
    red = image.getRed();
    green = image.getGreen();
    blue = image.getBlue();

    int[][] greyRed = new int[image.getImageWidth()][image.getImageHeight()];
    int[][] greyGreen = new int[image.getImageWidth()][image.getImageHeight()];
    int[][] greyBlue = new int[image.getImageWidth()][image.getImageHeight()];

    for (int x = 0; x < image.getImageWidth(); x++) {
      for (int y = 0; y < image.getImageHeight(); y++) {
        int greyValue = (int) (0.2126 * red[x][y] + 0.7152 * green[x][y] + 0.0722 * blue[x][y]);

        greyRed[x][y] = greyValue;
        greyGreen[x][y] = greyValue;
        greyBlue[x][y] = greyValue;

        if (greyRed[x][y] > 255) {
          greyRed[x][y] = 255;
        }
        if (greyGreen[x][y] > 255) {
          greyGreen[x][y] = 255;
        }
        if (greyBlue[x][y] > 255) {
          greyBlue[x][y] = 255;
        }
      }
    }
    resultantImage.setRed(greyRed);
    resultantImage.setGreen(greyGreen);
    resultantImage.setBlue(greyBlue);
    return resultantImage;
  }

  @Override
  public Image compressImage(double percentage, Image image) {
    red = image.getRed();
    green = image.getGreen();
    blue = image.getBlue();

    int originalRows = image.getImageWidth();
    int originalCols = image.getImageHeight();

    int[] paddedDimensions = padImageDimensions(originalRows, originalCols);

    int maxDimension = Math.max(paddedDimensions[0], paddedDimensions[1]);

    //Pad the image to the nearest power of two of the longest side to make it a square image.
    int[][] paddedMatrixRed = squareImage(red, maxDimension, maxDimension);
    int[][] paddedMatrixGreen = squareImage(green, maxDimension, maxDimension);
    int[][] paddedMatrixBlue = squareImage(blue, maxDimension, maxDimension);

    // Apply Haar transform on each color channel
    int[][] transformedRed = haarTransform(paddedMatrixRed, maxDimension);
    int[][] transformedGreen = haarTransform(paddedMatrixGreen, maxDimension);
    int[][] transformedBlue = haarTransform(paddedMatrixBlue, maxDimension);

    // Apply thresholding for compression
    int[][] thresholdedRed = threshold(transformedRed, percentage);
    int[][] thresholdedGreen = threshold(transformedGreen, percentage);
    int[][] thresholdedBlue = threshold(transformedBlue, percentage);

    // Inverse Haar transform to reconstruct the compressed image
    int[][] compressedRed = inverseHaarTransform(thresholdedRed, maxDimension);
    int[][] compressedGreen = inverseHaarTransform(thresholdedGreen, maxDimension);
    int[][] compressedBlue = inverseHaarTransform(thresholdedBlue, maxDimension);

    //Remove the padding
    int[][] paddingRemovedRed = removeSquareImage(compressedRed, originalRows, originalCols);
    int[][] paddingRemoveGreen = removeSquareImage(compressedGreen, originalRows, originalCols);
    int[][] paddingRemoveBlue = removeSquareImage(compressedBlue, originalRows, originalCols);

    for (int i = 0; i < paddingRemovedRed.length; i++) {
      for (int j = 0; j < paddingRemovedRed[0].length; j++) {
        if (paddingRemovedRed[i][j] < 0) {
          paddingRemovedRed[i][j] = 0;
        }

        if (paddingRemoveGreen[i][j] < 0) {
          paddingRemoveGreen[i][j] = 0;
        }

        if (paddingRemoveBlue[i][j] < 0) {
          paddingRemoveBlue[i][j] = 0;
        }

        if (paddingRemovedRed[i][j] > 255) {
          paddingRemovedRed[i][j] = 255;
        }

        if (paddingRemoveGreen[i][j] > 255) {
          paddingRemoveGreen[i][j] = 255;
        }

        if (paddingRemoveBlue[i][j] > 255) {
          paddingRemoveBlue[i][j] = 255;
        }
      }
    }

    resultantImage.setRed(paddingRemovedRed);
    resultantImage.setGreen(paddingRemoveGreen);
    resultantImage.setBlue(paddingRemoveBlue);
    return resultantImage;
  }

  private int[][] haarTransform(int[][] x, int s) {
    int[][] transformedMatrix = new int[s][s];

    // Copy input matrix x to transformedMatrix
    for (int i = 0; i < s; i++) {
      System.arraycopy(x[i], 0, transformedMatrix[i], 0, s);
    }

    int c = s;

    while (c > 1) {
      // Process each row in the current c x c section
      for (int i = 0; i < c; i++) {
        int[] rowSection = new int[c];
        System.arraycopy(transformedMatrix[i], 0, rowSection, 0, c);
        int[] transformedRow = transform1D(rowSection, c);
        System.arraycopy(transformedRow, 0, transformedMatrix[i], 0, c);
      }

      // Process each column in the current c x c section
      for (int j = 0; j < c; j++) {
        int[] colSection = new int[c];
        for (int i = 0; i < c; i++) {
          colSection[i] = transformedMatrix[i][j];
        }

        int[] transformedCol = transform1D(colSection, c);
        for (int i = 0; i < c; i++) {
          transformedMatrix[i][j] = transformedCol[i];
        }
      }

      c /= 2;
    }
    return transformedMatrix;
  }

  private int[] transform1D(int[] array, int length) {
    int half = length / 2;
    int[] avg = new int[half];
    int[] diff = new int[half];
    double sqrt2 = Math.sqrt(2);

    // Calculate avg and diff based on consecutive pairs (a, b)
    for (int i = 0; i < half; i++) {
      int a = array[2 * i];
      int b = array[2 * i + 1];
      avg[i] = (int) Math.round((a + b) / sqrt2);
      diff[i] = (int) Math.round((a - b) / sqrt2);
    }

    // Concatenate avg and diff into a new array
    int[] result = new int[length];
    System.arraycopy(avg, 0, result, 0, half);
    System.arraycopy(diff, 0, result, half, half);
    return result;
  }

  private int[][] threshold(int[][] matrix, double percentage) {
    int rows = matrix.length;
    int cols = matrix[0].length;

    // Flatten only non-zero elements of the matrix
    int[] flatArray = Arrays.stream(matrix)
            .flatMapToInt(Arrays::stream)
            .filter(value -> value != 0)
            .distinct()
            .toArray();

    Arrays.sort(flatArray);

    double thresholdValue = flatArray[(int) ((percentage / 100.0) * (flatArray.length - 1))];

    // Zero out elements below the threshold value
    int[][] thresholdedMatrix = deepCopy(matrix);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (Math.abs(thresholdedMatrix[i][j]) <= thresholdValue) {
          thresholdedMatrix[i][j] = 0;
        }
      }
    }
    return thresholdedMatrix;
  }

  private int[][] inverseHaarTransform(int[][] x, int s) {
    int c = 2;

    while (c <= s) {
      // Process each column in the current c x c section
      for (int j = 0; j < c; j++) {
        int[] colSection = new int[c];
        for (int i = 0; i < c; i++) {
          colSection[i] = x[i][j];
        }

        int[] transformedCol = inverseTransform1D(colSection);
        for (int i = 0; i < c; i++) {
          x[i][j] = transformedCol[i];
        }
      }

      // Process each row in the current c x c section
      for (int i = 0; i < c; i++) {
        int[] rowSection = new int[c];
        System.arraycopy(x[i], 0, rowSection, 0, c);

        int[] transformedRow = inverseTransform1D(rowSection);
        System.arraycopy(transformedRow, 0, x[i], 0, c);
      }

      c *= 2;
    }

    return x;
  }


  private int[] inverseTransform1D(int[] array) {
    int half = array.length / 2;
    int[] avg = new int[half];
    int[] diff = new int[half];
    double sqrt2 = Math.sqrt(2);

    // Split array into avg and diff, reconstruct each original value pair
    for (int i = 0; i < half; i++) {
      avg[i] = array[i];
      diff[i] = array[half + i];
    }

    // Interleave avg and diff to form the reconstructed array
    int[] result = new int[array.length];
    for (int i = 0; i < half; i++) {
      int a = avg[i];
      int b = diff[i];
      result[2 * i] = (int) Math.round((a + b) / sqrt2);
      result[2 * i + 1] = (int) Math.round((a - b) / sqrt2);
    }

    return result;
  }

  private int[][] removeSquareImage(int[][] paddedImage, int originalRows, int originalCols) {
    int[][] originalImage = new int[originalRows][originalCols];

    // Copy the relevant portion of the padded image
    for (int i = 0; i < originalRows; i++) {
      for (int j = 0; j < originalCols; j++) {
        originalImage[i][j] = paddedImage[i][j];
      }
    }

    return originalImage;
  }

  private int nextPowerOfTwo(int n) {
    if ((n & (n - 1)) == 0) {
      return n; // n is already a power of 2
    }
    int power = 1;
    while (power < n) {
      power *= 2;
    }
    return power;
  }

  private int[] padImageDimensions(int rows, int cols) {
    int paddedRows = nextPowerOfTwo(rows);
    int paddedCols = nextPowerOfTwo(cols);
    return new int[]{paddedRows, paddedCols};
  }

  private int[][] squareImage(int[][] image, int rows, int cols) {
    int[][] paddedImage = new int[rows][cols];
    for (int i = 0; i < image.length; i++) {
      System.arraycopy(image[i], 0, paddedImage[i], 0, image[i].length);
    }
    return paddedImage;
  }

  private int[][] deepCopy(int[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;

    // Create a new 2D array to hold the deep copy
    int[][] transformedMatrix = new int[rows][cols];

    // Copy each element using nested for loops
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        transformedMatrix[i][j] = matrix[i][j];
      }
    }
    return transformedMatrix;
  }

  protected int[][] filterComponent(int[][] component, int height, int width,
                                    double[][] kernelFilter, int loopingVariable) {

    int[][] filteredImage = new int[width][height];

    // Apply the Gaussian kernel
    for (int i = loopingVariable; i < width - loopingVariable; i++) {
      for (int j = loopingVariable; j < height - loopingVariable; j++) {
        double sum = 0.0;

        // Convolve the kernel with the image
        for (int k = -loopingVariable; k <= loopingVariable; k++) {
          for (int l = -loopingVariable; l <= loopingVariable; l++) {
            sum += component[i + k][j + l] * kernelFilter[k + loopingVariable][l + loopingVariable];
          }
        }

        // Assign the blurred value
        filteredImage[i][j] = (int) (sum);

        // Clamp the value to the 0-255 range
        filteredImage[i][j] = Math.max(0, Math.min(255, filteredImage[i][j]));
      }
    }
    return filteredImage;

  }

  protected int[][] padImage(int paddingWidth, int height, int width, int[][] color) {
    int newWidth = width + 2 * paddingWidth;
    int newHeight = height + 2 * paddingWidth;

    // Create new padded arrays for red, green, and blue components
    int[][] paddedColor = new int[newWidth][newHeight];

    // Copy original image pixels to the padded arrays
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        paddedColor[i + paddingWidth][j + paddingWidth] = color[i][j];
      }
    }
    return paddedColor;
  }

  protected int[][] removePadding(int paddingWidth, int originalHeight, int originalWidth,
                                  int[][] color) {

    // Create new arrays for the original image without padding
    int[][] unpaddedColor = new int[originalWidth][originalHeight];

    // Copy the pixel values back to the original size, skipping the padding
    for (int i = 0; i < originalWidth; i++) {
      for (int j = 0; j < originalHeight; j++) {

        unpaddedColor[i][j] = color[i + paddingWidth][j + paddingWidth];
      }
    }
    return unpaddedColor;
  }
}
