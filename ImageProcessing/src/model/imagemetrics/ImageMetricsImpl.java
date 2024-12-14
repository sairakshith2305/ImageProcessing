package model.imagemetrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.image.Image;
import model.image.ImageImpl;

/**
 * This class is used to calculate the color representation of an Image.
 * We calculate the value,luma and Intensity color representation with the respective
 * formulas.
 */
public class ImageMetricsImpl implements ImageMetrics {

  private int[][] red;
  private int[][] green;
  private int[][] blue;
  private int[][] combinedArrayRed;
  private int[][] combinedArrayGreen;
  private int[][] combinedArrayBlue;

  private Image resultantImage = new ImageImpl();

  @Override
  public Image valueComponentOfImage(Image image) {
    red = image.getRed();
    green = image.getGreen();
    blue = image.getBlue();

    combinedArrayRed = new int[image.getImageWidth()][image.getImageHeight()];
    combinedArrayGreen = new int[image.getImageWidth()][image.getImageHeight()];
    combinedArrayBlue = new int[image.getImageWidth()][image.getImageHeight()];
    for (int x = 0; x < image.getImageWidth(); x++) {
      for (int y = 0; y < image.getImageHeight(); y++) {
        int getRed = red[x][y];
        int getGreen = green[x][y];
        int getBlue = blue[x][y];

        int max = Math.max(Math.max(getRed, getGreen), getBlue);
        combinedArrayRed[x][y] = max;
        combinedArrayGreen[x][y] = max;
        combinedArrayBlue[x][y] = max;
      }
    }

    resultantImage.setRed(combinedArrayRed);
    resultantImage.setGreen(combinedArrayGreen);
    resultantImage.setBlue(combinedArrayBlue);
    return resultantImage;
  }

  @Override
  public Image intensityComponentOfImage(Image image) {
    red = image.getRed();
    green = image.getGreen();
    blue = image.getBlue();

    combinedArrayRed = new int[image.getImageWidth()][image.getImageHeight()];
    combinedArrayGreen = new int[image.getImageWidth()][image.getImageHeight()];
    combinedArrayBlue = new int[image.getImageWidth()][image.getImageHeight()];

    for (int x = 0; x < image.getImageWidth(); x++) {
      for (int y = 0; y < image.getImageHeight(); y++) {
        int getRed = red[x][y];
        int getGreen = green[x][y];
        int getBlue = blue[x][y];

        int average = (int) ((getRed + getGreen + getBlue) / 3.0);

        combinedArrayRed[x][y] = average;
        combinedArrayGreen[x][y] = average;
        combinedArrayBlue[x][y] = average;
      }
    }
    resultantImage.setRed(combinedArrayRed);
    resultantImage.setGreen(combinedArrayGreen);
    resultantImage.setBlue(combinedArrayBlue);
    return resultantImage;
  }

  @Override
  public Image lumaComponentOfImage(Image image) {
    red = image.getRed();
    green = image.getGreen();
    blue = image.getBlue();

    combinedArrayRed = new int[image.getImageWidth()][image.getImageHeight()];
    combinedArrayGreen = new int[image.getImageWidth()][image.getImageHeight()];
    combinedArrayBlue = new int[image.getImageWidth()][image.getImageHeight()];

    for (int x = 0; x < image.getImageWidth(); x++) {
      for (int y = 0; y < image.getImageHeight(); y++) {

        int getRed = (int) Math.round(0.2126 * red[x][y]);
        int getGreen = (int) Math.round(0.7152 * green[x][y]);
        int getBlue = (int) Math.round(0.0722 * blue[x][y]);

        int sum = getRed + getGreen + getBlue;

        if (sum > 255) {
          sum = 255;
        }

        combinedArrayRed[x][y] = sum;
        combinedArrayGreen[x][y] = sum;
        combinedArrayBlue[x][y] = sum;
      }
    }
    resultantImage.setRed(combinedArrayRed);
    resultantImage.setGreen(combinedArrayGreen);
    resultantImage.setBlue(combinedArrayBlue);
    return resultantImage;
  }

  @Override
  public List<Map<Integer, Integer>> getHistogram(Image image) {
    List<Map<Integer, Integer>> getRGB;

    Map<Integer, Integer> redintensity = new HashMap<>();
    Map<Integer, Integer> greenintensity = new HashMap<>();
    Map<Integer, Integer> blueintensity = new HashMap<>();

    red = image.getRed();
    green = image.getGreen();
    blue = image.getBlue();

    redintensity = getIntensityValues(red);
    greenintensity = getIntensityValues(green);
    blueintensity = getIntensityValues(blue);

    getRGB = new ArrayList<>();

    getRGB.add(redintensity);
    getRGB.add(greenintensity);
    getRGB.add(blueintensity);
    return getRGB;
  }

  private HashMap<Integer, Integer> getIntensityValues(int[][] component) {
    HashMap<Integer, Integer> intensity = new HashMap<>();

    for (int x = 0; x < component.length; x++) {
      for (int y = 0; y < component[0].length; y++) {
        intensity.put(component[x][y], intensity.getOrDefault(component[x][y], 0) + 1);
      }
    }
    return intensity;
  }
}
