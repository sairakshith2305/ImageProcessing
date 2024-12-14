package model.filter;

import model.image.Image;

/**
 * This is an interface for the filters applied on an Image. This class has sepia,blur, sharpen,
 * greyScale and compress Image functions. These functions take in an Image object as the parameter
 * and return a new Image.
 */
public interface ImageFilter {
  /**
   * This function is used to apply the sepia filter on the image.
   *
   * @param image The on which the sepia filter has to be added.
   * @return The resultant image for which sepia filter is applied.
   */
  Image convertImageToSepia(Image image);

  /**
   * This function is used to blur an Image.
   *
   * @param image The image which has to be blurred.
   * @return The resultant blur image.
   */
  Image blurImage(Image image);

  /**
   * This function is used to sharpen the image.
   *
   * @param image The image which has to be sharpened.
   * @return The resultant Image after sharpening.
   */
  Image sharpenImage(Image image);

  /**
   * This function is used to get the greyscale of an Image.
   *
   * @param image The image for which grey scale has to be found.
   * @return The resultant grey scale image.
   */
  Image greyScaleImage(Image image);

  /**
   * This function is used to compress an Image to the given percentage.
   *
   * @param percentage The percentage of compress required.
   * @param image      The image on which compression has to be done.
   * @return A new Image which is compressed.
   */
  Image compressImage(double percentage, Image image);
}
