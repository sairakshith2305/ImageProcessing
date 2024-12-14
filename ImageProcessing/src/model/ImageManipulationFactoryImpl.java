package model;

import utility.ImageUtil;

/**
 * This is the implementation of the imageManipulationFactory interface which returns
 * the object of either PPMImageManipulation class or JPGImageManipulation class.
 * The function in this class takes in a filename and based on the extension of the file
 * we return the respective object of the class.
 */
public class ImageManipulationFactoryImpl implements ImageManipulationFactory {

  @Override
  public ImageManipulation createImageManipulation(String filename) {
    if (ImageUtil.getFileExtension(filename).equalsIgnoreCase("ppm")) {
      return new PPMImageManipulation();
    } else {
      return new JPGImageManipulation();
    }
  }
}
