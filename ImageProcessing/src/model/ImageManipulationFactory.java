package model;

/**
 * This interface is used to create a factory that tightly couples the object of the various
 * classes that the program has to offer.
 */
public interface ImageManipulationFactory {
  /**
   * This function takes the file from the controller and returns an object of the
   * ImageManipulation class based on the extension of the image that is trying to be loaded.
   *
   * @param filename The file path of the source image.
   * @return a new object of ImageManipulation class,i.e,PPM or JPGImageManipulation class.
   */
  ImageManipulation createImageManipulation(String filename);
}
