package model;

import model.image.Image;

/**
 * This is the mask Manipulation interface which is used to perform the masking operation for an
 * Image. This interface provides with the masking functionality which can be applied for any on
 * image desired.
 */
public interface MaskImageManipulation {
  /**
   * This function is used to perform the masking operation of an Image.
   *
   * @param originalImage  The original image which has to be masked.
   * @param resultantImage The resultant Image after the operation is performed.
   * @param maskedImage    The mask image to be applied.
   * @return The resultant image after masking.
   */
  Image createMaskedImage(Image originalImage, Image resultantImage, Image maskedImage);
}
