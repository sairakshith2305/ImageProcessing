package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import model.image.Image;

/**
 * This is the Image Manipulation interface that defines all the functionalities of this interface.
 * This is the interface that talks with controller and further delegates the operations to its
 * other classes. It takes file name and destination file name as input from the controller and
 * returns the desired result to the controller.
 */
public interface ImageManipulation {

  /**
   * This is a function to load the image and store with the image with the given name
   * in the image directory. This is the main interface handling all the requests from the
   * controller. All the functions in this interface take in the image name and destination
   * image and perform operation on them and store with the destination image name.
   *
   * @param getRGB    The RGB values of the image.
   * @param imageName The name of the image to be stored.
   * @return The image which was loaded.
   * @throws IOException When there is a failure in read and write of Image.
   */
  Image loadImage(List<int[][]> getRGB, String imageName) throws IOException;

  /**
   * This function is used to save the image in the destination path with a given
   * name.
   *
   * @param fileName The image to be saved.
   * @return A list of 2D integer array which has the pixel values for the image.
   * @throws FileNotFoundException When the image is not to be found.
   */
  List<int[][]> saveImage(String fileName) throws FileNotFoundException;

  /**
   * This function is used to flip the image vertically.
   *
   * @param fileName             The name of the file to be flipped.
   * @param destinationImageName The name of the file to be stored after flip.
   * @return The vertically flipped Image.
   * @throws FileNotFoundException if the image to be flipped vertically does not exist.
   */
  Image flipImageVertically(String fileName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is used to flip the image vertically.
   *
   * @param fileName             The name of the file to be flipped.
   * @param destinationImageName The name of the file to be stored after flip.
   * @return The Horizontally flipped Image.
   * @throws FileNotFoundException if the image to be flipped horizontally does not exist.
   */
  Image flipImageHorizontally(String fileName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is used to find the value component of the image.
   *
   * @param imageName            The name of the image for which value component has to be found.
   * @param destinationImageName The final image name to be stored after finding value Component.
   * @return The value component of an image.
   * @throws FileNotFoundException if the image value component has to be found does not exist.
   */
  Image valueComponentOfImage(String imageName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is used to find the value component of the image.
   *
   * @param imageName            The name of the image for which value component has to be found.
   * @param destinationImageName The final image name to be stored after finding value Component.
   * @param percentage           The split percentage that has to be done on the image.
   * @return The value component of an Image with the split operation.
   * @throws FileNotFoundException if the image value component has to be found does notexist.
   */
  Image valueComponentOfImage(String imageName, String destinationImageName, double percentage)
          throws FileNotFoundException;

  /**
   * This function is used to get the value component of an image by applying a mask.
   *
   * @param fileName             The name of the image for which value component has to be found.
   * @param maskName             The mask image to be applied.
   * @param destinationImageName The final image name to be stored after finding value Component.
   * @return The masked value component image.
   * @throws FileNotFoundException When image or mask image is not found.
   */
  Image valueComponentOfImage(String fileName, String maskName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is used to find the intensity component of the image.
   *
   * @param fileName             The name of the image for which intensity component
   *                             has to be found.
   * @param destinationImageName The final image name to be stored after finding
   *                             intensity Component.
   * @return The intensity component of an Image.
   * @throws FileNotFoundException if the image whose intensity component has to be found does not
   *                               exist.
   */
  Image intensityComponentOfImage(String fileName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is used to find the intensity component of the image.
   *
   * @param fileName             The name of the image for which intensity component
   *                             has to be found.
   * @param destinationImageName The final image name to be stored after finding
   *                             intensity Component.
   * @param percentage           The percentage of split that is required to be done on the image.
   * @return The intensity component of an Image after the split operation.
   * @throws FileNotFoundException if the image whose intensity component has to be found does not
   *                               exist.
   */
  Image intensityComponentOfImage(String fileName, String destinationImageName, double percentage)
          throws FileNotFoundException;

  /**
   * This function is used to find the intensity component of the image.
   *
   * @param fileName             The name of the image for which intensity component has to be
   *                             found.
   * @param maskName             The mask image to be applied.
   * @param destinationImageName The final image name to be stored after finding the intensity
   *                             component.
   * @return The masked intensity component of the image.
   * @throws FileNotFoundException When the image and mask image is not to be found.
   */
  Image intensityComponentOfImage(String fileName, String maskName, String destinationImageName)
          throws FileNotFoundException;


  /**
   * This function is to find the luma component of the image.
   *
   * @param fileName             The name of the image for which luma component has to be found.
   * @param destinationImageName The final image name to be stored after finding luma Component.
   * @return The luma component of an Image.
   * @throws FileNotFoundException if the image whose luma component has to be found does not exist.
   */
  Image lumaComponentOfImage(String fileName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is to find the luma component of the image.
   *
   * @param fileName             The name of the image for which luma component has to be found.
   * @param destinationImageName The final image name to be stored after finding luma Component.
   * @param percentage           The percentage of split that has to be performed on the image.
   * @return The luma Component of an Image after the split operation.
   * @throws FileNotFoundException if the image whose luma component has to be found does not exist.
   */
  Image lumaComponentOfImage(String fileName, String destinationImageName, double percentage)
          throws FileNotFoundException;

  /**
   * This function is to find the luma component of the image.
   *
   * @param fileName             The name of the image for which luma component has to be found.
   * @param maskName             The mask image to be applied for the luma component.
   * @param destinationImageName The image name to be stored after the masking operation.
   * @return The masked luma component of an image.
   * @throws FileNotFoundException When the image and the mask image is not to be found.
   */
  Image lumaComponentOfImage(String fileName, String maskName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is used to blur an image.
   *
   * @param imageName            The name of the image which has to be blurred.
   * @param destinationImageName The final image name to be stored after finding blurred Image.
   * @return The blurred Image.
   * @throws FileNotFoundException if the image to be blurred does not exist.
   */
  Image blurImage(String imageName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is used to blur an image.
   *
   * @param imageName            The name of the image which has to be blurred.
   * @param destinationImageName The final image name to be stored after finding blurred Image.
   * @param percentage           The percentage of split that is required to be done on the image.
   * @return The blurred Image with the split operation performed.
   * @throws FileNotFoundException if the image to be blurred does not exist.
   */
  Image blurImage(String imageName, String destinationImageName, double percentage) throws
          FileNotFoundException;

  /**
   * This function is used to blur the image with respect to the mask Image.
   *
   * @param imageName            The Image which needs to be blurred.
   * @param maskName             The Image used for masking.
   * @param destinationImageName The image name to be saved after masking.
   * @return The blurred image with mask.
   * @throws FileNotFoundException When the image and mask image is not found.
   */
  Image blurImage(String imageName, String maskName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is used to sharpen the image.
   *
   * @param imageName            The name of the image which has to be Sharpened.
   * @param destinationImageName The final image name to be stored after finding Sharpened Image.
   * @return The sharpened Image.
   * @throws FileNotFoundException if the image to be sharpened does not exist.
   */
  Image sharpenImage(String imageName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is used to sharpen the image.
   *
   * @param imageName            The name of the image which has to be Sharpened.
   * @param destinationImageName The final image name to be stored after finding Sharpened Image.
   * @param percentage           The percentage to split the image.
   * @return The Sharpened Image after split operation.
   * @throws FileNotFoundException if the image to be sharpened does not exist.
   */
  Image sharpenImage(String imageName, String destinationImageName, double percentage)
          throws FileNotFoundException;

  /**
   * This function is used to sharpen the image by applying the mask operation.
   *
   * @param imageName            The image to be sharpened.
   * @param maskName             The mask image to be applied.
   * @param destinationImageName The final name of the sharpened masked image to be stored.
   * @return The masked sharpened image.
   * @throws FileNotFoundException When the image or mask is not found.
   */
  Image sharpenImage(String imageName, String maskName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is used to convert into Sepia filtered Image.
   *
   * @param fileName             The image to which the filter has to be applied.
   * @param destinationImageName The image to which the filtered image has to be stored.
   * @return The sepia Image.
   * @throws FileNotFoundException if the image to which the filter is applied does not exist.
   */
  Image convertIntoSepia(String fileName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is used to convert into Sepia filtered Image.
   *
   * @param imageName            The image to which the filter has to be applied.
   * @param destinationImageName The image to which the filtered image has to be stored.
   * @param percentage           The percentage by which the image has to be split.
   * @return The sepia Image after the split operation is performed.
   * @throws FileNotFoundException if the image to which the filter is applied does not exist.
   */
  Image convertIntoSepia(String imageName, String destinationImageName, double percentage)
          throws FileNotFoundException;

  /**
   * This function masks the image with the given mask image.
   *
   * @param fileName             The image to be masked.
   * @param maskName             The mask image.
   * @param destinationImageName The final Image name after masking.
   * @return The masked sepia Image.
   * @throws FileNotFoundException When the desired image and mask image are not found.
   */
  Image convertIntoSepia(String fileName, String maskName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function converts the image into a grey scale image.
   *
   * @param fileName             The image to be converted into a grey scale.
   * @param destinationImageName The destination image name after the finding the grey scale image.
   * @return The grey Scaled Image.
   * @throws FileNotFoundException if the image that is going to be converted to grayscale does not
   *                               exist.
   */
  Image convertIntoGreyScaleImage(String fileName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function converts the image into a grey scale image.
   *
   * @param imageName            The image to be converted into a grey scale.
   * @param destinationImageName The destination image name after the finding the grey scale image.
   * @param percentage           The percentage by which the image has to be split.
   * @return The grey Scaled Image with split operation performed.
   * @throws FileNotFoundException if the image that is going to be converted to grayscale does not
   *                               exist.
   */
  Image convertIntoGreyScaleImage(String imageName, String destinationImageName, double percentage)
          throws FileNotFoundException;


  /**
   * This function is used to brighten the image.
   *
   * @param fileName             The image to be brightened.
   * @param destinationImageName The destination image name after brightening.
   * @return The Brightened Image.
   * @throws FileNotFoundException if the image to be brightened does not exist.
   */
  Image brightenImage(int value, String fileName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is to split the RGB Image into red, green and blue component.
   *
   * @param imageName                 The name of the image on which split has to be performed.
   * @param destinationImageNameRed   The destination image name for red component.
   * @param destinationImageNameGreen The destination image name for green component.
   * @param destinationImageNameBlue  The destination image name for Blue component.
   * @return The red, green and blue component of an Image.
   * @throws FileNotFoundException if the RGB image that has to be split does not exist.
   */
  List<Image> splitRGBImage(String imageName, String destinationImageNameRed,
                      String destinationImageNameGreen, String destinationImageNameBlue)
          throws FileNotFoundException;

  /**
   * This function is used to combine the RGB components to form a color image.
   *
   * @param destinationImageName The destination image name of the image.
   * @param imageRed             The red component image.
   * @param imageGreen           The green component image.
   * @param imageBlue            The blue component image.
   * @return The combined red,green and blue component of an Image.
   * @throws FileNotFoundException if the red, green or blue component image does not exist.
   */
  Image combineRGBImage(String destinationImageName, String imageRed,
                        String imageGreen, String imageBlue) throws FileNotFoundException;

  /**
   * This function is used to get the red component of the image.
   *
   * @param imageName            the image for which red component has to be found.
   * @param destinationImageName The destination name of the red component image.
   * @return The red component of an Image.
   * @throws FileNotFoundException if the image for which red component has to be found does not
   *                               exist.
   */
  Image redComponentImage(String imageName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is used to get the red component of the image.
   *
   * @param imageName            the image for which red component has to be found.
   * @param maskName             The mask image to be applied.
   * @param destinationImageName The destination name of the red component image.
   * @return The masked red component Image.
   * @throws FileNotFoundException When the image and the mask image are not found or does not
   *                               exist.
   */
  Image redComponentImage(String imageName, String maskName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is used to get the green component of the image.
   *
   * @param imageName            the image for which green component has to be found.
   * @param destinationImageName The destination name of the green component image.
   * @return The green component Image.
   * @throws FileNotFoundException if the image for which green component has to be found does not
   *                               exist.
   */
  Image greenComponentImage(String imageName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is used to get the green component of the image.
   *
   * @param imageName            the image for which green component has to be found.
   * @param maskName             The mask image to be applied as a filter for the green component.
   * @param destinationImageName The destination name of the green component image.
   * @return The masked green component image.
   * @throws FileNotFoundException When image and Mask image is not to be found.
   */
  Image greenComponentImage(String imageName, String maskName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is used to get the blue component of the image.
   *
   * @param imageName            the image for which blue component has to be found.
   * @param destinationImageName The destination name of the blue component image.
   * @return The blue component Image.
   * @throws FileNotFoundException if the image for which blue component has to be found does not
   *                               exist.
   */
  Image blueComponentImage(String imageName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is used to get the blue component of an Image.
   *
   * @param imageName            The image for which the blue component has to be found.
   * @param maskName             The mask Image which has to be applied on the blue component Image.
   * @param destinationImageName The final Image name to be saved.
   * @return The masked blue component of an Image.
   * @throws FileNotFoundException When the image or mask image is not to be found.
   */
  Image blueComponentImage(String imageName, String maskName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is used to color correct the image.
   *
   * @param imageName            the image for which the color correction has to be done.
   * @param destinationImageName the destination name of the color corrected image.
   * @return The color corrected Image.
   * @throws FileNotFoundException if the image that has to be color corrected does not exist.
   */
  Image colorCorrectionImage(String imageName, String destinationImageName)
          throws FileNotFoundException;

  /**
   * This function is used to color correct the image.
   *
   * @param imageName            the image for which the color correction has to be done.
   * @param destinationImageName the destination name of the color corrected image.
   * @param percentage           The percentage by which the image has to be split.
   * @return The color corrected Image with split operation performed.
   * @throws FileNotFoundException if the image that has to be color corrected does not exist.
   */
  Image colorCorrectionImage(String imageName, String destinationImageName, double percentage)
          throws FileNotFoundException;

  /**
   * This function is used to adjust the intensity levels of the colors in the image.
   *
   * @param b                    position of the black (shadow) in the 0-255 scale.
   * @param m                    position of a mid-intensity on a 0-255 scale.
   * @param w                    position of the white (highlight) in the 0-255 scale.
   * @param imageName            the image on which level adjustment needs to be done.
   * @param destinationImageName the level adjusted image.
   * @return The level adjusted Image.
   * @throws FileNotFoundException if the image that has to be level adjusted does not exist.
   */
  Image levelsAdjustmentImage(double b, double m, double w, String imageName,
                              String destinationImageName) throws FileNotFoundException;

  /**
   * This function is used to adjust the intensity levels of the colors in the image.
   *
   * @param b                    position of the black (shadow) in the 0-255 scale.
   * @param m                    position of a mid-intensity on a 0-255 scale.
   * @param w                    position of the white (highlight) in the 0-255 scale.
   * @param imageName            the image on which level adjustment needs to be done.
   * @param destinationImageName the level adjusted image.
   * @param percentage           The percentage by which the image has to be split.
   * @return The level adjusted Image with split operation performed.
   * @throws FileNotFoundException if the image that has to be level adjusted does not exist.
   */
  Image levelsAdjustmentImage(double b, double m, double w, String imageName,
                              String destinationImageName, double percentage)
          throws FileNotFoundException;

  /**
   * This is a function that caters all the required information to plot a histogram of an image.
   *
   * @param imageName The image for which histogram has to be plotted.
   * @return a List of a Map of red, green and blue intensity values for each pixel.
   * @throws FileNotFoundException when the image is not found.
   */
  List<Map<Integer, Integer>> histogram(String imageName) throws FileNotFoundException;

  /**
   * This function is used to compress an Image.
   *
   * @param percentage           the percentage of compress required.
   * @param imageName            The image which has to be compressed.
   * @param destinationImageName The nam by which the compressed image has to be stored.
   * @return The compressed image.
   * @throws FileNotFoundException When the image is not found.
   */
  Image compressImage(double percentage, String imageName,
                      String destinationImageName) throws FileNotFoundException;

  /**
   * This function is used to downsize the image.
   *
   * @param newSmallWidth        The new width to which the image has to be downsized.
   * @param newSmallHeight       The new height to which the image has to be downsized.
   * @param fileName             The image which has to be downsized.
   * @param destinationImageName The final downsized image name.
   * @return The Downsized Image.
   * @throws FileNotFoundException When the image is not found.
   */
  Image downScaleImage(double newSmallWidth, double newSmallHeight, String fileName,
                       String destinationImageName) throws FileNotFoundException;

  /**
   * This function is used to undo an operation performed on an image.
   *
   * @param name The image which has to be undone.
   * @return The final Image after the undo operation has been performed.
   */
  Image undoImage(String name);

}
