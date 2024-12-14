import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.ImageManipulation;
import model.image.Image;
import model.image.ImageImpl;
import model.imagemetrics.ImageMetrics;
import model.imagemetrics.ImageMetricsImpl;
import utility.ImageUtil;

/**
 * This is an implementation class for mocking image manipulation functions. This class provides
 * stubbed versions of image processing methods and simulates the operations of actual image
 * manipulation functions, allowing for testing and verification without affecting real images.
 */
public class MockImageManipulation implements ImageManipulation {
  private StringBuilder log;
  private List<int[][]> getRGBAllFunctions;

  /**
   * Constructor to initialize the log.
   *
   * @param log to log the mock behaviour of each functionality.
   */
  MockImageManipulation(StringBuilder log) {
    this.log = log;
  }

  /**
   * Mocks the behaviour of the functionality to load the image.
   *
   * @param getRGB    the R,G and B components of the image.
   * @param imageName the image that needs to be loaded into the program.
   * @return the mock image to simulate the loaded image.
   */
  @Override
  public Image loadImage(List<int[][]> getRGB, String imageName) {
    log.append("Parameters received for load, ").append(imageName);

    Image filename = new ImageImpl();
    filename.setRed(getRGB.get(0));
    filename.setGreen(getRGB.get(1));
    filename.setBlue(getRGB.get(2));

    getRGBAllFunctions = new ArrayList<>();

    getRGBAllFunctions.add(getRGB.get(0));
    getRGBAllFunctions.add(getRGB.get(1));
    getRGBAllFunctions.add(getRGB.get(2));

    return filename;
  }

  /**
   * Mocks the behaviour of the functionality to save the image.
   *
   * @param filePath the file path in which the image is stored.
   * @return the mock image to simulate the saved image.
   */
  @Override
  public List<int[][]> saveImage(String filePath) {
    log.append("\nParameters received for saveImage ").append(filePath);

    List<int[][]> getRGBSave;

    getRGBSave = new ArrayList<>();

    getRGBSave.add(getRGBAllFunctions.get(0));
    getRGBSave.add(getRGBAllFunctions.get(1));
    getRGBSave.add(getRGBAllFunctions.get(2));
    return getRGBSave;
  }

  /**
   * Mocks the behaviour of the functionality to vertically flip the image.
   *
   * @param fileName             The name of the file to be flipped.
   * @param destinationImageName The name of the file to be stored after flip.
   * @return the mock image to simulate the vertically flipped image.
   */
  @Override
  public Image flipImageVertically(String fileName, String destinationImageName) {
    log.append("\nParameters received for flipImageVertically ").append(fileName).append(", ")
            .append(destinationImageName);

    Image filename = new ImageImpl();
    filename.setRed(getRGBAllFunctions.get(0));
    filename.setGreen(getRGBAllFunctions.get(1));
    filename.setBlue(getRGBAllFunctions.get(2));
    return filename;
  }

  /**
   * Mocks the behaviour of the functionality to horizontally flip the image.
   *
   * @param fileName             The name of the file to be flipped.
   * @param destinationImageName The name of the file to be stored after flip.
   * @return the mock image to simulate the horizontally flipped image.
   */
  @Override
  public Image flipImageHorizontally(String fileName, String destinationImageName) {
    log.append("\nParameters received for flipImageHorizontally ").append(fileName).append(", ")
            .append(destinationImageName);

    Image filename = new ImageImpl();
    filename.setRed(getRGBAllFunctions.get(0));
    filename.setGreen(getRGBAllFunctions.get(1));
    filename.setBlue(getRGBAllFunctions.get(2));
    return filename;
  }

  /**
   * Mocks the behaviour of the functionality to find the value component of the image.
   *
   * @param imageName            The name of the image for which value component has to be found.
   * @param destinationImageName The final image name to be stored after finding value Component.
   * @return null as it is not a functionality in the view.
   */
  @Override
  public Image valueComponentOfImage(String imageName, String destinationImageName) {
    log.append("\nParameters received for valueComponent ").append(imageName).append(", ")
            .append(destinationImageName);
    return null;
  }

  /**
   * Mocks the behaviour of the functionality to find the value component of the image which
   * can be split by a certain percentage.
   *
   * @param imageName            the image whose value component has to be found.
   * @param destinationImageName the output image with the value component till a certain
   *                             percentage of the width of the image.
   * @param percentage           the percentage of the width of the image to which the value
   *                             component is found.
   * @return null as it is not a functionality in the view.
   */
  @Override
  public Image valueComponentOfImage(String imageName, String destinationImageName,
                                    double percentage) {
    log.append("\nParameters received for valueComponent ").append(imageName).append(", ")
            .append(destinationImageName).append(", ").append(percentage);
    return null;
  }

  /**
   * Mocks the behaviour of the functionality to find the value component when mask image is
   * present.
   * @param fileName             The name of the image for which value component has to be found.
   * @param maskName             The mask image to be applied.
   * @param destinationImageName The final image name to be stored after finding value Component.
   * @return null as this is not available in the view.
   */
  @Override
  public Image valueComponentOfImage(String fileName, String maskName,
                                     String destinationImageName) {
    log.append("\nParameters received for convertIntoValueWithMask ")
            .append(fileName).append(", ").append(maskName).append(", ")
            .append(destinationImageName);
    return null;
  }

  /**
   * Mocks the behaviour of the functionality to find the intensity component of the image.
   *
   * @param fileName             The name of the image for which intensity component h
   *                             as to be found.
   * @param destinationImageName The final image name to be stored after finding
   *                             intensity Component.
   * @return null as it is not a functionality in the view.
   */
  @Override
  public Image intensityComponentOfImage(String fileName, String destinationImageName) {
    log.append("\nParameters received for intensityComponent ").append(fileName).append(", ")
            .append(destinationImageName);
    return null;
  }

  /**
   * Mocks the behaviour of the functionality to find the intensity component of the image which
   * can be split by a certain percentage.
   *
   * @param fileName             the image whose intensity component has to be found.
   * @param destinationImageName the output image with the intensity component till a certain
   *                             percentage of the width of the image.
   * @param percentage           the percentage of the width of the image to which the intensity
   *                             component is found.
   * @return null as it is not a functionality in the view.
   */
  @Override
  public Image intensityComponentOfImage(String fileName, String destinationImageName,
                                        double percentage) {
    log.append("\nParameters received for intensityComponent ").append(fileName).append(", ")
            .append(destinationImageName).append(", ").append(percentage);
    return null;
  }

  /**
   * Mocks the behaviour of the functionality to find the intensity component when mask image is
   * present.
   * @param fileName             The name of the image for which intensity component has to be
   *                             found.
   * @param maskName             The mask image to be applied.
   * @param destinationImageName The final image name to be stored after finding the intensity
   *                             component.
   * @return null as this is not available in the view.
   */
  @Override
  public Image intensityComponentOfImage(String fileName, String maskName,
                                         String destinationImageName) {
    log.append("\nParameters received for convertIntoIntensityWithMask ")
            .append(fileName).append(", ").append(maskName).append(", ")
            .append(destinationImageName);
    return null;
  }

  /**
   * Mocks the behaviour of the functionality to find the luma component of the image.
   *
   * @param fileName             The name of the image for which luma component has to be found.
   * @param destinationImageName The final image name to be stored after finding luma Component.
   * @return the mock image to simulate the luma component of the image.
   */
  @Override
  public Image lumaComponentOfImage(String fileName, String destinationImageName) {
    log.append("\nParameters received for lumaComponent ").append(fileName).append(", ")
            .append(destinationImageName);

    Image filename = new ImageImpl();
    filename.setRed(getRGBAllFunctions.get(0));
    filename.setGreen(getRGBAllFunctions.get(1));
    filename.setBlue(getRGBAllFunctions.get(2));
    return filename;
  }

  /**
   * Mocks the behaviour of the functionality to find the value component of the image which
   * can be split by a certain percentage.
   *
   * @param fileName             the image whose luma component has to be found.
   * @param destinationImageName the output image with the luma component till a certain
   *                             percentage of the width of the width of the image.
   * @param percentage           the percentage of the width of the image to which the luma
   *                             component is found.
   * @return the mock image to simulate the luma component of the image when split.
   */
  @Override
  public Image lumaComponentOfImage(String fileName, String destinationImageName,
                                   double percentage) {
    log.append("\nParameters received for lumaComponent ").append(fileName).append(", ")
            .append(destinationImageName).append(", ").append(percentage);

    Image filename = new ImageImpl();
    filename.setRed(getRGBAllFunctions.get(0));
    filename.setGreen(getRGBAllFunctions.get(1));
    filename.setBlue(getRGBAllFunctions.get(2));
    return filename;
  }

  /**
   * Mocks the behaviour of the functionality to find the luma component when mask image is present.
   * @param fileName             The name of the image for which luma component has to be found.
   * @param maskName             The mask image to be applied for the luma component.
   * @param destinationImageName The image name to be stored after the masking operation.
   * @return null as this is not available in the view.
   */
  @Override
  public Image lumaComponentOfImage(String fileName, String maskName,
                                    String destinationImageName) {
    log.append("\nParameters received for convertIntoLumaWithMask ")
            .append(fileName).append(", ").append(maskName).append(", ")
            .append(destinationImageName);
    return null;
  }

  /**
   * Mocks the behaviour of the blur image functionality.
   *
   * @param imageName            The name of the image which has to be blurred.
   * @param destinationImageName The final image name to be stored after finding blurred Image.
   * @return the mock image to simulate blurring of an image.
   */
  @Override
  public Image blurImage(String imageName, String destinationImageName) {
    log.append("\nParameters received for blurImage ").append(imageName).append(", ")
            .append(destinationImageName);

    Image filename = new ImageImpl();
    filename.setRed(getRGBAllFunctions.get(0));
    filename.setGreen(getRGBAllFunctions.get(1));
    filename.setBlue(getRGBAllFunctions.get(2));
    return filename;
  }

  /**
   * Mocks the behaviour of the functionality to apply the blur filter to the image which can be
   * split by a certain percentage.
   *
   * @param imageName            the image on which the blur filter needs to be applied.
   * @param destinationImageName the output image with the blur filter till a certain percentage of
   *                             the width of the image.
   * @param percentage           the percentage of the width of the image to which the blur filter
   *                             is applied.
   * @return the mock image to simulate blurring of an image when split.
   */
  @Override
  public Image blurImage(String imageName, String destinationImageName, double percentage) {
    log.append("\nParameters received for blurImage ").append(imageName).append(", ")
            .append(destinationImageName).append(", ").append(percentage);

    Image filename = new ImageImpl();
    filename.setRed(getRGBAllFunctions.get(0));
    filename.setGreen(getRGBAllFunctions.get(1));
    filename.setBlue(getRGBAllFunctions.get(2));
    return filename;
  }

  /**
   * Mocks the behaviour of the functionality of the blur filter when mask image is present.
   * @param imageName            The Image which needs to be blurred.
   * @param maskName             The Image used for masking.
   * @param destinationImageName The image name to be saved after masking.
   * @return null as this is not available in the view.
   */
  @Override
  public Image blurImage(String imageName, String maskName, String destinationImageName) {
    log.append("\nParameters received for blurImageWithMask ")
            .append(imageName).append(", ").append(maskName).append(", ")
            .append(destinationImageName);
    return null;
  }

  /**
   * Mocks the behaviour of the sharpen image functionality.
   *
   * @param imageName            The name of the image which has to be Sharpened.
   * @param destinationImageName The final image name to be stored after finding Sharpened Image.
   * @return the mock image to simulate sharpening of an image.
   */
  @Override
  public Image sharpenImage(String imageName, String destinationImageName) {
    log.append("\nParameters received for sharpenImage ").append(imageName).append(", ")
            .append(destinationImageName);

    Image filename = new ImageImpl();
    filename.setRed(getRGBAllFunctions.get(0));
    filename.setGreen(getRGBAllFunctions.get(1));
    filename.setBlue(getRGBAllFunctions.get(2));
    return filename;
  }

  /**
   * Mocks the behaviour of the functionality to apply the sharpen filter to the image which can be
   * split by a certain percentage.
   *
   * @param imageName            the image on which the sharpen filter needs to be applied.
   * @param destinationImageName the output image with the sharpen filter till a certain percentage
   *                             of the width of the image.
   * @param percentage           the percentage of the width of the image to which the sharpen
   *                             filter is applied.
   * @return the mock image to simulate sharpening of an image when split.
   */
  @Override
  public Image sharpenImage(String imageName, String destinationImageName, double percentage) {
    log.append("\nParameters received for sharpen ").append(imageName).append(", ")
            .append(destinationImageName).append(", ").append(percentage);

    Image filename = new ImageImpl();
    filename.setRed(getRGBAllFunctions.get(0));
    filename.setGreen(getRGBAllFunctions.get(1));
    filename.setBlue(getRGBAllFunctions.get(2));
    return filename;
  }

  /**
   * Mocks the behaviour of the functionality of the sharpen filter when mask image is present.
   * @param imageName            The image to be sharpened.
   * @param maskName             The mask image to be applied.
   * @param destinationImageName The final name of the sharpened masked image to be stored.
   * @return null as this is not available in the view.
   */
  @Override
  public Image sharpenImage(String imageName, String maskName,
                            String destinationImageName) {
    log.append("\nParameters received for sharpenImageWithMask ")
            .append(imageName).append(", ").append(maskName).append(", ")
            .append(destinationImageName);
    return null;
  }

  /**
   * Mocks the behaviour of the sepia filter functionality.
   *
   * @param fileName             The image to which the filter has to be applied.
   * @param destinationImageName The image to which the filtered image has to be stored.
   * @return the mock image to simulate sepia of an image.
   */
  @Override
  public Image convertIntoSepia(String fileName, String destinationImageName) {
    log.append("\nParameters received for sepia ").append(fileName).append(", ")
            .append(destinationImageName);

    Image filename = new ImageImpl();
    filename.setRed(getRGBAllFunctions.get(0));
    filename.setGreen(getRGBAllFunctions.get(1));
    filename.setBlue(getRGBAllFunctions.get(2));
    return filename;
  }

  /**
   * Mocks the behaviour of the functionality to apply the sepia filter to the image which can be
   * split by a certain percentage.
   *
   * @param imageName            the image on which the sepia filter needs to be applied.
   * @param destinationImageName the output image with the sepia filter till a certain percentage
   *                             of the width of the image.
   * @param percentage           the percentage of the width of the image to which the sepia filter
   *                             is applied.
   * @return the mock image to simulate sepia of an image when split.
   */
  @Override
  public Image convertIntoSepia(String imageName, String destinationImageName, double percentage) {
    log.append("\nParameters received for sepia ").append(imageName).append(", ")
            .append(destinationImageName).append(", ").append(percentage);

    Image filename = new ImageImpl();
    filename.setRed(getRGBAllFunctions.get(0));
    filename.setGreen(getRGBAllFunctions.get(1));
    filename.setBlue(getRGBAllFunctions.get(2));
    return filename;
  }

  /**
   * Mocks the behaviour of the functionality of the sepia filter when mask image is present.
   * @param fileName             The image to be masked.
   * @param maskName             The mask image.
   * @param destinationImageName The final Image name after masking.
   * @return null as this is not available in the view.
   */
  @Override
  public Image convertIntoSepia(String fileName, String maskName,
                                String destinationImageName) {
    log.append("\nParameters received for convertIntoSepiaWithMask ")
            .append(fileName).append(", ").append(maskName).append(", ")
            .append(destinationImageName);
    return null;
  }

  /**
   * Mocks the behaviour of the grayscale functionality.
   *
   * @param fileName             The image to be converted into a grey scale.
   * @param destinationImageName The destination image name after the finding the grey scale image.
   * @return null as it is not a functionality in the view.
   */
  @Override
  public Image convertIntoGreyScaleImage(String fileName, String destinationImageName) {
    log.append("\nParameters received for grayscale ").append(fileName).append(", ")
            .append(destinationImageName);
    return null;
  }

  /**
   * Mocks the behaviour of the functionality to apply the grayscale filter to the image which
   * can be split by a certain percentage.
   *
   * @param imageName            the image on which the grayscale filter needs to be applied.
   * @param destinationImageName the output image with the grayscale filter till a certain
   *                             percentage of the width of the image.
   * @param percentage           the percentage of the width of the image to which the grayscale
   *                             filter is applied.
   * @return null as it is not a functionality in the view.
   */
  @Override
  public Image convertIntoGreyScaleImage(String imageName, String destinationImageName,
                                        double percentage) {
    log.append("\nParameters received for grayscale ").append(imageName).append(", ")
            .append(destinationImageName).append(", ").append(percentage);
    return null;
  }

  /**
   * Mocks the behaviour of the brighten image functionality.
   *
   * @param value                The amount by which the image is brightened.
   * @param fileName             The image to be brightened.
   * @param destinationImageName The destination image name after brightening.
   * @return null as it is not a functionality in the view.
   */
  @Override
  public Image brightenImage(int value, String fileName, String destinationImageName) {
    log.append("\nParameters received for brighten ").append(value).append(", ").append(fileName)
            .append(", ").append(destinationImageName);
    return null;
  }

  /**
   * Mocks the behaviour of the splitRGB image functionality.
   *
   * @param imageName                 The name of the image on which split has to be performed.
   * @param destinationImageNameRed   The destination image name for red component.
   * @param destinationImageNameGreen The destination image name for green component.
   * @param destinationImageNameBlue  The destination image name for Blue component.
   * @return null as it is not a functionality in the view.
   */
  @Override
  public List<Image> splitRGBImage(String imageName, String destinationImageNameRed,
                            String destinationImageNameGreen, String destinationImageNameBlue) {
    log.append("\nParameters received for splitRGBImage ").append(imageName).append(", ")
            .append(destinationImageNameRed).append(", ").append(destinationImageNameGreen)
            .append(", ").append(destinationImageNameBlue);
    return null;
  }

  /**
   * Mocks the behaviour of the combineRGB image functionality.
   *
   * @param destinationImageName The destination image name of the image.
   * @param imageRed             The red component image.
   * @param imageGreen           The green component image.
   * @param imageBlue            The blue component image.
   * @return null as it is not a functionality in the view.
   */
  @Override
  public Image combineRGBImage(String destinationImageName, String imageRed, String imageGreen,
                              String imageBlue) {
    log.append("\nParameters received for combineRGBImage ").append(destinationImageName)
            .append(", ").append(imageRed).append(", ").append(imageGreen).append(", ")
            .append(imageBlue);
    return null;
  }

  /**
   * Mocks the behaviour of the functionality to find the red component of the image.
   *
   * @param imageName            the image for which red component has to be found.
   * @param destinationImageName The destination name of the red component image.
   * @return a mock image to simulate the red component.
   */
  @Override
  public Image redComponentImage(String imageName, String destinationImageName) {
    log.append("\nParameters received for redComponentImage ").append(imageName).append(", ")
            .append(destinationImageName);

    Image filename = new ImageImpl();
    filename.setRed(getRGBAllFunctions.get(0));
    filename.setGreen(getRGBAllFunctions.get(1));
    filename.setBlue(getRGBAllFunctions.get(2));
    return filename;
  }

  /**
   * Mocks the behaviour to find the red-component of the image when the mask image is present.
   * @param imageName            the image for which red component has to be found.
   * @param maskName             The mask image to be applied.
   * @param destinationImageName The destination name of the red component image.
   * @return null as this is not available in the view.
   */
  @Override
  public Image redComponentImage(String imageName, String maskName,
                                 String destinationImageName) {
    log.append("\nParameters received for redComponentImageWithMask ")
            .append(imageName).append(", ").append(maskName).append(", ")
            .append(destinationImageName);
    return null;
  }

  /**
   * Mocks the behaviour of the functionality to find the green component of the image.
   *
   * @param imageName            the image for which green component has to be found.
   * @param destinationImageName The destination name of the green component image.
   * @return a mock image to simulate the green component.
   */
  @Override
  public Image greenComponentImage(String imageName, String destinationImageName) {
    log.append("\nParameters received for greenComponentImage ").append(imageName).append(", ")
            .append(destinationImageName);

    Image filename = new ImageImpl();
    filename.setRed(getRGBAllFunctions.get(0));
    filename.setGreen(getRGBAllFunctions.get(1));
    filename.setBlue(getRGBAllFunctions.get(2));
    return filename;
  }

  /**
   * Mocks the behaviour to find the green-component of the image when the mask image is present.
   * @param imageName            the image for which green component has to be found.
   * @param maskName             The mask image to be applied as a filter for the green component.
   * @param destinationImageName The destination name of the green component image.
   * @return null as this is not available in the view.
   */
  @Override
  public Image greenComponentImage(String imageName, String maskName,
                                   String destinationImageName) {
    log.append("\nParameters received for greenComponentImageWithMask ")
            .append(imageName).append(", ").append(maskName).append(", ")
            .append(destinationImageName);
    return null;
  }

  /**
   * Mocks the behaviour of the functionality to find the blue component of the image.
   *
   * @param imageName            the image for which blue component has to be found.
   * @param destinationImageName The destination name of the blue component image.
   * @return a mock image to simulate the blue component.
   */
  @Override
  public Image blueComponentImage(String imageName, String destinationImageName) {
    log.append("\nParameters received for blueComponentImage ").append(imageName).append(", ")
            .append(destinationImageName);

    Image filename = new ImageImpl();
    filename.setRed(getRGBAllFunctions.get(0));
    filename.setGreen(getRGBAllFunctions.get(1));
    filename.setBlue(getRGBAllFunctions.get(2));
    return filename;
  }

  /**
   * Mocks the behaviour to find the blue-component of the image when the mask image is present.
   * @param imageName            The image for which the blue component has to be found.
   * @param maskName             The mask Image which has to be applied on the blue component Image.
   * @param destinationImageName The final Image name to be saved.
   * @return null as this is not available in the view.
   */
  @Override
  public Image blueComponentImage(String imageName, String maskName,
                                  String destinationImageName) {
    log.append("\nParameters received for blueComponentImageWithMask ")
            .append(imageName).append(", ").append(maskName).append(", ")
            .append(destinationImageName);
    return null;
  }

  /**
   * Mocks the behaviour of the image color correction functionality.
   *
   * @param imageName            the image for which the color correction has to be done.
   * @param destinationImageName the destination name of the color corrected image.
   * @return                     a mock image to simulate color correction.
   */
  @Override
  public Image colorCorrectionImage(String imageName, String destinationImageName) {
    log.append("\nParameters received for colorCorrectionImage ").append(imageName).append(", ")
            .append(destinationImageName);

    Image filename = new ImageImpl();
    filename.setRed(getRGBAllFunctions.get(0));
    filename.setGreen(getRGBAllFunctions.get(1));
    filename.setBlue(getRGBAllFunctions.get(2));
    return filename;
  }

  /**
   * Mocks the behaviour of the functionality to color correct the image which can be split by a
   * certain percentage.
   *
   * @param imageName            the image that is to be color corrected.
   * @param destinationImageName the output image which is color corrected till a certain
   *                             percentage of the width of the image.
   * @param percentage           the percentage of the width of the image to which the color
   *                             correction is done.
   * @return                     a mock image to simulate color correction with split.
   */
  @Override
  public Image colorCorrectionImage(String imageName, String destinationImageName,
                                   double percentage) {
    log.append("\nParameters received for colorCorrectionImage ").append(imageName).append(", ")
            .append(destinationImageName).append(", ").append(percentage);

    Image filename = new ImageImpl();
    filename.setRed(getRGBAllFunctions.get(0));
    filename.setGreen(getRGBAllFunctions.get(1));
    filename.setBlue(getRGBAllFunctions.get(2));
    return filename;
  }

  /**
   * Mocks the behaviour of the image level adjustment functionality.
   *
   * @param b                    position of the black (shadow) in the 0-255 scale.
   * @param m                    position of a mid-intensity on a 0-255 scale.
   * @param w                    position of the white (highlight) in the 0-255 scale.
   * @param imageName            the image on which level adjustment needs to be done.
   * @param destinationImageName the level adjusted image.
   * @return                     a mock image to simulate level adjustment.
   */
  @Override
  public Image levelsAdjustmentImage(double b, double m, double w, String imageName,
                                    String destinationImageName) {
    log.append("\nParameters received for levelsAdjustmentImage ").append(b).append(", ").append(m)
            .append(", ").append(w).append(", ").append(imageName).append(", ")
            .append(destinationImageName);

    Image filename = new ImageImpl();
    filename.setRed(getRGBAllFunctions.get(0));
    filename.setGreen(getRGBAllFunctions.get(1));
    filename.setBlue(getRGBAllFunctions.get(2));
    return filename;
  }

  /**
   * Mocks the behaviour of the functionality to level adjust the image which can be split by a
   * certain percentage.
   *
   * @param b                    the position of black (shadow) on the 0-255 scale.
   * @param m                    the position of mid on the 0-255 scale.
   * @param w                    the position of while (highlight) on the 0-255 scale.
   * @param imageName            the image that is to be level adjusted.
   * @param destinationImageName the output image which is level adjusted till a certain percentage
   *                             of the width.
   * @param percentage           the percentage of the width of the image to which the level
   *                             adjustment is done.
   * @return                     a mock image to simulate level adjustment with split.
   */
  @Override
  public Image levelsAdjustmentImage(double b, double m, double w, String imageName,
                                    String destinationImageName, double percentage) {
    log.append("\nParameters received for levelsAdjustmentImage ").append(imageName).append(", ")
            .append(destinationImageName).append(", ").append(percentage);

    Image filename = new ImageImpl();
    filename.setRed(getRGBAllFunctions.get(0));
    filename.setGreen(getRGBAllFunctions.get(1));
    filename.setBlue(getRGBAllFunctions.get(2));
    return filename;
  }

  /**
   * Mocks the behaviour of the functionality to find the histogram of the image.
   *
   * @param imageName the image on which the histogram needs to be found.
   * @return a mock image to simulate the histogram of an image.
   */
  @Override
  public List<Map<Integer, Integer>> histogram(String imageName) {
    log.append("\nParameters received for histogram ").append(imageName);

    ImageMetrics imageMetrics;
    List<Map<Integer, Integer>> histRGBIntensityValues;

    imageMetrics = new ImageMetricsImpl();
    List<int[][]> getRGBHist;

    getRGBHist = new ArrayList<>();

    String filePath = "res/ScriptImage/nature.jpg"; //dummy image
    if (ImageUtil.getFileExtension(filePath).equals("ppm")) {
      try {
        getRGBHist = ImageUtil.readPPM(filePath);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    } else {
      try {
        getRGBHist = ImageUtil.loadImage(filePath);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    Image filename = new ImageImpl();
    filename.setRed(getRGBHist.get(0));
    filename.setGreen(getRGBHist.get(1));
    filename.setBlue(getRGBHist.get(2));

    histRGBIntensityValues = imageMetrics.getHistogram(filename);
    return histRGBIntensityValues;
  }

  /**
   * Mocks the behaviour of the functionality to compress the image.
   *
   * @param percentage           the percentage by which image is compressed. Higher the percentage,
   *                             higher the compression.
   * @param imageName            the source image on which the compression needs to be done.
   * @param destinationImageName the output image which is compressed by the given percentage.
   * @return a mock image to simulate compression.
   */
  @Override
  public Image compressImage(double percentage, String imageName, String destinationImageName) {
    log.append("\nParameters received for compressImage ").append(imageName).append(", ")
            .append(destinationImageName).append(", ").append(percentage);

    Image filename = new ImageImpl();
    filename.setRed(getRGBAllFunctions.get(0));
    filename.setGreen(getRGBAllFunctions.get(1));
    filename.setBlue(getRGBAllFunctions.get(2));
    return filename;
  }

  /**
   * Mocks the behaviour to downscale the image.
   * @param newSmallWidth        The new width to which the image has to be downsized.
   * @param newSmallHeight       The new height to which the image has to be downsized.
   * @param fileName             The image which has to be downsized.
   * @param destinationImageName The final downsized image name.
   * @return a mock image to simulate the downsizing.
   */
  @Override
  public Image downScaleImage(double newSmallWidth, double newSmallHeight, String fileName,
                              String destinationImageName) {
    log.append("\nParameters received for downScaleImage ").append(newSmallWidth).append(", ")
            .append(newSmallHeight).append(", ").append(fileName).append(", ")
            .append(destinationImageName);

    Image filename = new ImageImpl();
    filename.setRed(getRGBAllFunctions.get(0));
    filename.setGreen(getRGBAllFunctions.get(1));
    filename.setBlue(getRGBAllFunctions.get(2));
    return filename;
  }

  /**
   * Mocks the behaviour to undo the operation.
   * @param name The image which has to be undone.
   * @return null as this needs only loggers.
   */
  @Override
  public Image undoImage(String name) {
    log.append("\nParameters received for undo ").append(name);
    return null;
  }
}