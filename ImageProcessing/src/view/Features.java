package view;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This is the features interface which adds the event listeners to each button present on the GUI.
 * This class gives a call back to the controller and helps the controller take input given by the
 * user.
 */
public interface Features {
  /**
   * This is the callback function used for loading an Image.
   *
   * @throws IOException When image is unable to be read.
   */
  void loadImage() throws IOException;

  /**
   * This is the save Image callback from GUI to the controller.
   *
   * @throws IOException When unable to save the file to the given path.
   */
  void saveImage() throws IOException;

  /**
   * This is the function call back for the blur Image. This triggers when the blur button is
   * clicked.
   *
   * @throws FileNotFoundException When the image to be operated is missing.
   */
  void blurImage() throws FileNotFoundException;

  /**
   * This is a function callback to the controller to blur an image by the split percentage.
   *
   * @param value The percentage of split to be performed.
   * @throws FileNotFoundException When the file to be processed is not found.
   */
  void blurImage(double value) throws FileNotFoundException;

  /**
   * This is the function callback to controller to perform the sepia operation.
   *
   * @throws FileNotFoundException When the file to perform sepia is not to be found.
   */
  void sepiaImage() throws FileNotFoundException;

  /**
   * This is the function callback to controller to perform the sepia with a split operation.
   *
   * @param value The value by which we have tp perform split.
   * @throws FileNotFoundException When the file to perform sepia is missing.
   */
  void sepiaImage(double value) throws FileNotFoundException;

  /**
   * This is a function callback to the controller to sharpen an Image.
   *
   * @throws FileNotFoundException When the image to be sharpened is not to be found.
   */
  void sharpenImage() throws FileNotFoundException;

  /**
   * This is a function callback to the controller to sharpen an Image with split operation.
   *
   * @param value The split percentage.
   * @throws FileNotFoundException When the image is not found.
   */
  void sharpenImage(double value) throws FileNotFoundException;

  /**
   * This is a function callback to perform the luma component of an Image.
   *
   * @throws FileNotFoundException When the image is file is not to be found.
   */
  void lumaComponentImage() throws FileNotFoundException;

  /**
   * This is a function callback to perform the luma operation with split.
   *
   * @param value The percentage of split to be performed.
   * @throws FileNotFoundException When the image file is not to be found.
   */
  void lumaComponentImage(double value) throws FileNotFoundException;

  /**
   * This is a function callback to the color correct function of the controller.
   *
   * @throws FileNotFoundException When the image file is not to be found.
   */
  void colorCorrectionImage() throws FileNotFoundException;

  /**
   * This is a function callback to perform the color correctness of an Image along with split.
   *
   * @param value The percentage to be split.
   * @throws FileNotFoundException When the image file is not to be found.
   */
  void colorCorrectionImage(double value) throws FileNotFoundException;

  /**
   * This is a function callback to controller to perform compress.
   *
   * @param percentage The percentage of compression required.
   * @throws FileNotFoundException When the image file is not to be found.
   */
  void compressImage(double percentage) throws FileNotFoundException;

  /**
   * This is a function callback to controller to perform the level adjust for an image.
   *
   * @param b position of the black (shadow) in the 0-255 scale.
   * @param m position of a mid-intensity on a 0-255 scale.
   * @param w position of the white (highlight) in the 0-255 scale.
   * @throws FileNotFoundException When the image file is not to be found.
   */
  void levelAdjustmentImage(double b, double m, double w) throws FileNotFoundException;

  /**
   * This is a function callback to controller to perform the level adjust for an image with split.
   *
   * @param b     position of the black (shadow) in the 0-255 scale.
   * @param m     position of a mid-intensity on a 0-255 scale.
   * @param w     position of the white (highlight) in the 0-255 scale.
   * @param value The percentage of split required.
   * @throws FileNotFoundException When the image file is not to be found.
   */
  void levelAdjustmentImage(double b, double m, double w, double value)
          throws FileNotFoundException;

  /**
   * This is a function callback to the controller to perform horizontal flip of an Image.
   *
   * @throws FileNotFoundException When the image file is not to be found.
   */
  void horizontalFlipImage() throws FileNotFoundException;

  /**
   * This is a function callback to the controller to perform vertical flip.
   *
   * @throws FileNotFoundException When the image file is not to be found.
   */
  void verticalFlipImage() throws FileNotFoundException;

  /**
   * This is a function callback to the controller to perform red component of an Image.
   *
   * @throws FileNotFoundException When the image is not to be found.
   */
  void redComponentImage() throws FileNotFoundException;

  /**
   * This is a function callback to the controller to perform blue component of an Image.
   *
   * @throws FileNotFoundException When the image is not to be found.
   */
  void blueComponentImage() throws FileNotFoundException;

  /**
   * This is a function callback to the controller to perform green component of an Image.
   *
   * @throws FileNotFoundException When the image file is not to be found.
   */
  void greenComponentImage() throws FileNotFoundException;

  /**
   * This is a function callback to the controller to downscale an Image.
   *
   * @param newSmallWidth  The new width.
   * @param newSmallHeight The new Height.
   * @throws FileNotFoundException When the image is not to be found.
   */
  void downScaleImage(int newSmallWidth, int newSmallHeight) throws FileNotFoundException;

  /**
   * This is a function callback to perform the undo of a command.
   *
   * @throws FileNotFoundException When the image file is not present.
   */
  void undoCommand() throws FileNotFoundException;

}
