import java.awt.image.BufferedImage;
import java.util.ArrayList;

import view.ExtendedImageView;
import view.Features;

/**
 * This is an implementation class for mocking image view functions. This class provides
 * stubbed versions of image view methods and simulates the operations of actual image
 * view functions, allowing for testing and verification without affecting real images.
 */
public class MockGUIImageView implements ExtendedImageView {
  private StringBuilder log;

  MockGUIImageView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public String getImagePath() {
    return "res/ScriptImage/nature.jpg";
  }

  @Override
  public void showImage(ArrayList<BufferedImage> image) {
    log.append("\nshowImage of MockGUI");
  }

  @Override
  public void showErrorMessage(String message) {
    log.append("\nshowErrorMessage of MockGUI");
  }

  @Override
  public void addFeatures(Features features) {
    log.append("addFeatures of MockGUI\n");
  }

  @Override
  public void resetFocus() {
    log.append("\nresetFocus of MockGUI");
  }

  @Override
  public String getImagePathToSave() {
    return "";
  }

  @Override
  public void setImage(BufferedImage image) {
    log.append("\nsetImage of MockGUI");
  }

  @Override
  public void setHistogramImage(BufferedImage image) {
    log.append("\nsetHistogramImage of MockGUI");
  }

  @Override
  public BufferedImage getImage() {
    return null;
  }

  @Override
  public int checkUnsavedChanges() {
    return 0;
  }

  @Override
  public void enableUndoCommand() {
    log.append("\nenableUndoCommand of MockGUI");
  }

  @Override
  public void disableUndoCommand() {
    log.append("\ndisableUndoCommand of MockGUI");
  }
}
