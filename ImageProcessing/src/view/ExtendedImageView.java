package view;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * This is the interface for the extended view Image. This interface holds for all the GUI
 * components of an Image. This interface helps the controller take inputs, set the resultant
 * image and show appropriate error messages when required.
 */
public interface ExtendedImageView {
  /**
   * This function is used to get the Image path from the user through GUI.
   *
   * @return The filep path.
   */
  String getImagePath();

  /**
   * This function is used to show the image onto the GUI.
   *
   * @param image The images that has to be rendered to the user.
   */
  void showImage(ArrayList<BufferedImage> image);

  /**
   * The dialog box to throw out all the errors.
   *
   * @param message The error message to be shown for the user.
   */
  void showErrorMessage(String message);

  /**
   * To add new functionalities and provide call backs to the GUI controller.
   *
   * @param features The features to be performed.
   */
  void addFeatures(Features features);

  /**
   * This function is used to reset the focus of the GUI frame.
   */
  void resetFocus();

  /**
   * This function is ued to get the image path for save operation.
   *
   * @return The image path where the image has t be saved.
   */
  String getImagePathToSave();

  /**
   * This function is used to set the latest image to the GUI.
   *
   * @param image The image to be set.
   */
  void setImage(BufferedImage image);

  /**
   * This function is used to set the Histogram for that image.
   *
   * @param image The histogram image.
   */
  void setHistogramImage(BufferedImage image);

  /**
   * This returns the current image present on the view.
   *
   * @return The image present in the view.
   */
  BufferedImage getImage();

  /**
   * This function gives a dialog box when unsaved commands are present.
   *
   * @return The option selected by the user.
   */
  int checkUnsavedChanges();

  /**
   * This function is used to enable the undo Command.
   */
  void enableUndoCommand();

  /**
   * This function is used to disable the undo command.
   */
  void disableUndoCommand();
}
