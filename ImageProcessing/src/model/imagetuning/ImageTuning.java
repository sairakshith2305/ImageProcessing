package model.imagetuning;

import model.image.Image;

/**
 * This interface defines the Tuning parameters for an Image. We can brighten, darken, flip image
 * horizontally and vertically, level adjust and color correct an image. The functions take in an
 * image and return a new Image with the desired output.
 */
public interface ImageTuning {
  /**
   * This function is used to brighten or darken the color Image.
   *
   * @param increment The brightness value to be incremented ro decremented.
   * @param image     The image on which the brightness operation has to be performed.
   * @return The resultant Image after brightening or darkening the image.
   */
  Image brightenImage(int increment, Image image);

  /**
   * This function horizontally flips an Image.
   *
   * @param image The Image which has to be flipped horizontally.
   * @return Resultant Horizontally flipped Image.
   */
  Image horizontalFlipImage(Image image);

  /**
   * This function vertically flips an Image.
   *
   * @param image The Image which has to be flipped horizontally.
   * @return Resultant Horizontally flipped Image.
   */
  Image verticalFlipImage(Image image);

  /**
   * This function color corrects the image.
   *
   * @param image The image that has to be color corrected.
   * @return the color corrected image.
   */
  Image colorCorrection(Image image);

  /**
   * The function is used to adjust the levels of colors of an Image.
   *
   * @param b     position of black in the range of 0-255 for tonal adjustments.
   * @param m     position of midtone in the range of 0-255 for tonal adjustments.
   * @param w     position of white in the range of 0-255 for tonal adjustments.
   * @param image The image for which the level has to be adjusted.
   * @return A new image with levels adjusted.
   */
  Image levelsAdjustment(double b, double m, double w, Image image);

  /**
   * This function is used to downsize an image to the given width and height.
   * @param sourceImage The image to be downsized.
   * @param newWidth The new width for the image.
   * @param newHeight The new height for the image.
   * @return The downsized image.
   */
  Image downscaleImage(Image sourceImage, int newWidth, int newHeight);
}
