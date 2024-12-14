package model.imagemetrics;

import java.util.List;
import java.util.Map;

import model.image.Image;

/**
 * This function is used to derive the Metrics of a color Image. By metrics we give the value,luma
 * and intensity component of an Image. These functions take in an Image object and return a new
 * Image post performing the operations.
 */
public interface ImageMetrics {
  /**
   * This function computes the Value Component of an Image.
   *
   * @param image The Image on which the value component has to be found.
   * @return The resultant Image which will have the value component of the Image.l
   */
  Image valueComponentOfImage(Image image);

  /**
   * This function computes the Luma component of an Image.
   *
   * @param image The Image for which the luma component has to be found.
   * @return Resultant Image which is the Luma component of the Image.
   */
  Image lumaComponentOfImage(Image image);

  /**
   * This function computes the intensity Component of an Image.
   * This will have the average of all the three components stored for each individual
   * channel of the Image.
   *
   * @param image The image on which intensity component has to be found.
   * @return Resultant Image which is the intensity component of the original Image.
   */
  Image intensityComponentOfImage(Image image);

  /**
   * This is the function is used to get the histogram of an image.
   *
   * @param image The image for which we need the histogram.
   * @return The resultant Image with histogram.
   */
  List<Map<Integer, Integer>> getHistogram(Image image);
}
