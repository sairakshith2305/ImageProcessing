package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.filter.ImageFilter;
import model.filter.ImageFilterImpl;
import model.image.Image;
import model.image.ImageImpl;
import model.imagemetrics.ImageMetrics;
import model.imagemetrics.ImageMetricsImpl;
import model.imagetuning.ImageTuning;
import model.imagetuning.ImageTuningImpl;

/**
 * This is an Abstract class which implements the Image Manipulation class.
 * This class has features common to both type of images,i.e, PPM as well as JPG/PNG/JPEG.
 * This class can accommodate any future change that comes up with respect to these two types of
 * images. Also, if any new image type is supported in the future having similar features can make
 * use of this class.
 */
public abstract class AbstractImageManipulation implements ImageManipulation {

  private final Map<String, Image> imageObject = new HashMap<>();
  private Image newImage;
  private ImageTuning imageTuning;
  private ImageFilter imageFilter;
  private ImageMetrics imageMetrics;
  private List<int[][]> getRGB;
  private List<Map<Integer, Integer>> histRGBIntensityValues;
  private MaskImageManipulation maskImageManipulation;


  @Override
  public Image loadImage(List<int[][]> getRGB, String imageName) {
    Image filename = new ImageImpl();
    filename.setRed(getRGB.get(0));
    filename.setGreen(getRGB.get(1));
    filename.setBlue(getRGB.get(2));
    imageObject.put(imageName, filename);
    return filename;
  }

  @Override
  public List<int[][]> saveImage(String fileName) throws FileNotFoundException {
    newImage = new ImageImpl();
    if (imageObject.get(fileName) != null) {
      getRGB = new ArrayList<>();
      newImage = imageObject.get(fileName);
      getRGB.add(newImage.getRed());
      getRGB.add(newImage.getGreen());
      getRGB.add(newImage.getBlue());
      return getRGB;
    } else {
      throw new FileNotFoundException("This file is not to be found :" + fileName);
    }
  }

  @Override
  public Image flipImageVertically(String fileName, String destinationImageName)
          throws FileNotFoundException {
    imageTuning = new ImageTuningImpl();
    newImage = new ImageImpl();
    if (imageObject.get(fileName) != null) {
      Image old = imageObject.get(fileName);
      newImage = imageTuning.verticalFlipImage(old);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not Found: " + fileName);
    }
  }

  @Override
  public Image flipImageHorizontally(String fileName, String destinationImageName)
          throws FileNotFoundException {
    imageTuning = new ImageTuningImpl();
    newImage = new ImageImpl();
    if (imageObject.get(fileName) != null) {
      Image old = imageObject.get(fileName);
      newImage = imageTuning.horizontalFlipImage(old);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not Found: " + fileName);
    }
  }

  @Override
  public Image valueComponentOfImage(String imageName, String destinationImageName)
          throws FileNotFoundException {
    ImageMetrics imageMetrics = new ImageMetricsImpl();
    newImage = new ImageImpl();
    if (imageObject.get(imageName) != null) {
      Image image = imageObject.get(imageName);
      newImage = imageMetrics.valueComponentOfImage(image);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not found: " + imageName);
    }
  }

  @Override
  public Image valueComponentOfImage(String imageName, String destinationImageName,
                                     double percentage) throws FileNotFoundException {
    ImageMetrics imageMetrics = new ImageMetricsImpl();
    newImage = new ImageImpl();
    if (imageObject.get(imageName) != null) {
      Image image = imageObject.get(imageName);
      if (percentage < 0 || percentage > 100) {
        throw new IllegalArgumentException("percentage must be between 0 and 100");
      }
      newImage = imageMetrics.valueComponentOfImage(image);
      newImage = splitImage(image, newImage, percentage);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not found: " + imageName);
    }
  }

  @Override
  public Image valueComponentOfImage(String fileName, String maskName, String destinationImageName)
          throws FileNotFoundException {
    imageMetrics = new ImageMetricsImpl();
    newImage = new ImageImpl();
    maskImageManipulation = new MaskImageManipulationImpl();
    if (imageObject.get(fileName) != null && imageObject.get(maskName) != null) {
      Image old = imageObject.get(fileName);
      Image mask = imageObject.get(maskName);

      if (mask.getImageHeight() < old.getImageHeight() || mask.getImageWidth() < old.getImageWidth()
              || mask.getImageWidth() > old.getImageWidth()
              || mask.getImageHeight() > old.getImageHeight()) {
        throw new IllegalArgumentException("Mask image and input image should be of the same size");
      }

      newImage = imageMetrics.valueComponentOfImage(old);
      newImage = maskImageManipulation.createMaskedImage(old, newImage, mask);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      if (imageObject.get(fileName) == null) {
        throw new FileNotFoundException("File not Found: " + fileName);
      }
      throw new FileNotFoundException("File not Found: " + maskName);
    }
  }

  @Override
  public Image intensityComponentOfImage(String fileName, String destinationImageName)
          throws FileNotFoundException {
    imageMetrics = new ImageMetricsImpl();
    newImage = new ImageImpl();
    if (imageObject.get(fileName) != null) {
      Image old = imageObject.get(fileName);
      newImage = imageMetrics.intensityComponentOfImage(old);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not Found: " + fileName);
    }
  }

  @Override
  public Image intensityComponentOfImage(String fileName, String destinationImageName,
                                         double percentage)
          throws FileNotFoundException {
    imageMetrics = new ImageMetricsImpl();
    newImage = new ImageImpl();
    if (imageObject.get(fileName) != null) {
      Image old = imageObject.get(fileName);
      if (percentage < 0 || percentage > 100) {
        throw new IllegalArgumentException("percentage must be between 0 and 100");
      }
      newImage = imageMetrics.intensityComponentOfImage(old);
      newImage = splitImage(old, newImage, percentage);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not Found: " + fileName);
    }
  }

  @Override
  public Image intensityComponentOfImage(String fileName, String maskName,
                                         String destinationImageName)
          throws FileNotFoundException {
    newImage = new ImageImpl();
    imageMetrics = new ImageMetricsImpl();
    maskImageManipulation = new MaskImageManipulationImpl();
    if (imageObject.get(fileName) != null && imageObject.get(maskName) != null) {
      Image old = imageObject.get(fileName);
      Image mask = imageObject.get(maskName);

      if (mask.getImageHeight() < old.getImageHeight() || mask.getImageWidth() < old.getImageWidth()
              || mask.getImageWidth() > old.getImageWidth()
              || mask.getImageHeight() > old.getImageHeight()) {
        throw new IllegalArgumentException("Mask image and input image should be of the same size");
      }

      newImage = imageMetrics.intensityComponentOfImage(old);
      newImage = maskImageManipulation.createMaskedImage(old, newImage, mask);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      if (imageObject.get(fileName) == null) {
        throw new FileNotFoundException("File not Found: " + fileName);
      }
      throw new FileNotFoundException("File not Found: " + maskName);
    }
  }

  @Override
  public Image lumaComponentOfImage(String fileName, String destinationImageName)
          throws FileNotFoundException {
    imageMetrics = new ImageMetricsImpl();
    newImage = new ImageImpl();
    if (imageObject.get(fileName) != null) {
      Image old = imageObject.get(fileName);
      newImage = imageMetrics.lumaComponentOfImage(old);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not Found: " + fileName);
    }
  }

  @Override
  public Image lumaComponentOfImage(String fileName, String destinationImageName, double percentage)
          throws FileNotFoundException {
    imageMetrics = new ImageMetricsImpl();
    newImage = new ImageImpl();
    if (imageObject.get(fileName) != null) {
      Image old = imageObject.get(fileName);
      if (percentage < 0 || percentage > 100) {
        throw new IllegalArgumentException("percentage must be between 0 and 100");
      }
      newImage = imageMetrics.lumaComponentOfImage(old);
      newImage = splitImage(old, newImage, percentage);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not Found: " + fileName);
    }
  }

  @Override
  public Image lumaComponentOfImage(String fileName, String maskName, String destinationImageName)
          throws FileNotFoundException {
    imageMetrics = new ImageMetricsImpl();
    newImage = new ImageImpl();
    maskImageManipulation = new MaskImageManipulationImpl();
    if (imageObject.get(fileName) != null && imageObject.get(maskName) != null) {
      Image old = imageObject.get(fileName);
      Image mask = imageObject.get(maskName);

      if (mask.getImageHeight() < old.getImageHeight() || mask.getImageWidth() < old.getImageWidth()
              || mask.getImageWidth() > old.getImageWidth()
              || mask.getImageHeight() > old.getImageHeight()) {
        throw new IllegalArgumentException("Mask image and input image should be of the same size");
      }

      newImage = imageMetrics.lumaComponentOfImage(old);
      newImage = maskImageManipulation.createMaskedImage(old, newImage, mask);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      if (imageObject.get(fileName) == null) {
        throw new FileNotFoundException("File not Found: " + fileName);
      }
      throw new FileNotFoundException("File not Found: " + maskName);
    }
  }

  @Override
  public Image blurImage(String imageName, String destinationImageName)
          throws FileNotFoundException {
    newImage = new ImageImpl();
    imageFilter = new ImageFilterImpl();
    if (imageObject.get(imageName) != null) {
      Image old = imageObject.get(imageName);
      newImage = imageFilter.blurImage(old);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not found: " + imageName);
    }

  }

  @Override
  public Image blurImage(String imageName, String destinationImageName, double percentage) throws
          FileNotFoundException {
    newImage = new ImageImpl();
    imageFilter = new ImageFilterImpl();
    if (imageObject.get(imageName) != null) {
      Image old = imageObject.get(imageName);
      if (percentage < 0 || percentage > 100) {
        throw new IllegalArgumentException("percentage must be between 0 and 100");
      }
      newImage = imageFilter.blurImage(old);
      newImage = splitImage(old, newImage, percentage);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not found: " + imageName);
    }
  }

  @Override
  public Image blurImage(String imageName, String maskName, String destinationImageName)
          throws FileNotFoundException {
    newImage = new ImageImpl();
    imageFilter = new ImageFilterImpl();
    maskImageManipulation = new MaskImageManipulationImpl();
    if (imageObject.get(imageName) != null && imageObject.get(maskName) != null) {
      Image old = imageObject.get(imageName);
      Image mask = imageObject.get(maskName);

      if (mask.getImageHeight() < old.getImageHeight() || mask.getImageWidth() < old.getImageWidth()
              || mask.getImageWidth() > old.getImageWidth()
              || mask.getImageHeight() > old.getImageHeight()) {
        throw new IllegalArgumentException("Mask image and input image should be of the same size");
      }

      newImage = imageFilter.blurImage(old);
      newImage = maskImageManipulation.createMaskedImage(old, newImage, mask);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      if (imageObject.get(imageName) == null) {
        throw new FileNotFoundException("File not Found: " + imageName);
      }
      throw new FileNotFoundException("File not Found: " + maskName);
    }
  }

  @Override
  public Image sharpenImage(String imageName, String destinationImageName)
          throws FileNotFoundException {
    newImage = new ImageImpl();
    imageFilter = new ImageFilterImpl();
    if (imageObject.get(imageName) != null) {
      Image old = imageObject.get(imageName);
      newImage = imageFilter.sharpenImage(old);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not found: " + imageName);
    }
  }

  @Override
  public Image sharpenImage(String imageName, String destinationImageName, double percentage)
          throws FileNotFoundException {
    newImage = new ImageImpl();
    imageFilter = new ImageFilterImpl();
    if (imageObject.get(imageName) != null) {
      Image old = imageObject.get(imageName);
      if (percentage < 0 || percentage > 100) {
        throw new IllegalArgumentException("percentage must be between 0 and 100");
      }
      newImage = imageFilter.sharpenImage(old);
      newImage = splitImage(old, newImage, percentage);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not found: " + imageName);
    }
  }

  @Override
  public Image sharpenImage(String imageName, String maskName, String destinationImageName)
          throws FileNotFoundException {
    newImage = new ImageImpl();
    imageFilter = new ImageFilterImpl();
    maskImageManipulation = new MaskImageManipulationImpl();
    if (imageObject.get(imageName) != null && imageObject.get(maskName) != null) {
      Image old = imageObject.get(imageName);
      Image mask = imageObject.get(maskName);

      if (mask.getImageHeight() < old.getImageHeight() || mask.getImageWidth() < old.getImageWidth()
              || mask.getImageWidth() > old.getImageWidth()
              || mask.getImageHeight() > old.getImageHeight()) {
        throw new IllegalArgumentException("Mask image and input image should be of the same size");
      }

      newImage = imageFilter.sharpenImage(old);
      newImage = maskImageManipulation.createMaskedImage(old, newImage, mask);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      if (imageObject.get(imageName) == null) {
        throw new FileNotFoundException("File not Found: " + imageName);
      }
      throw new FileNotFoundException("File not Found: " + maskName);
    }
  }

  @Override
  public Image convertIntoSepia(String fileName, String destinationImageName)
          throws FileNotFoundException {
    imageFilter = new ImageFilterImpl();
    newImage = new ImageImpl();
    if (imageObject.get(fileName) != null) {
      Image old = imageObject.get(fileName);
      newImage = imageFilter.convertImageToSepia(old);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not Found: " + fileName);
    }
  }

  @Override
  public Image convertIntoSepia(String imageName, String destinationImageName, double percentage)
          throws FileNotFoundException {
    imageFilter = new ImageFilterImpl();
    newImage = new ImageImpl();
    if (imageObject.get(imageName) != null) {
      Image old = imageObject.get(imageName);
      if (percentage < 0 || percentage > 100) {
        throw new IllegalArgumentException("percentage must be between 0 and 100");
      }
      newImage = imageFilter.convertImageToSepia(old);
      newImage = splitImage(old, newImage, percentage);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not Found: " + imageName);
    }
  }

  @Override
  public Image convertIntoSepia(String fileName, String maskName, String destinationImageName)
          throws FileNotFoundException {
    imageFilter = new ImageFilterImpl();
    newImage = new ImageImpl();
    maskImageManipulation = new MaskImageManipulationImpl();
    if (imageObject.get(fileName) != null && imageObject.get(maskName) != null) {
      Image old = imageObject.get(fileName);
      Image mask = imageObject.get(maskName);

      if (mask.getImageHeight() < old.getImageHeight() || mask.getImageWidth() < old.getImageWidth()
              || mask.getImageWidth() > old.getImageWidth()
              || mask.getImageHeight() > old.getImageHeight()) {
        throw new IllegalArgumentException("Mask image and input image should be of the same size");
      }

      newImage = imageFilter.convertImageToSepia(old);
      newImage = maskImageManipulation.createMaskedImage(old, newImage, mask);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      if (imageObject.get(fileName) == null) {
        throw new FileNotFoundException("File not Found: " + fileName);
      }
      throw new FileNotFoundException("File not Found: " + maskName);
    }
  }

  @Override
  public Image convertIntoGreyScaleImage(String fileName, String destinationImageName)
          throws FileNotFoundException {
    imageFilter = new ImageFilterImpl();
    newImage = new ImageImpl();
    if (imageObject.get(fileName) != null) {
      Image old = imageObject.get(fileName);
      newImage = imageFilter.greyScaleImage(old);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not Found: " + fileName);
    }
  }

  @Override
  public Image convertIntoGreyScaleImage(String imageName, String destinationImageName,
                                         double percentage) throws FileNotFoundException {
    newImage = new ImageImpl();
    imageFilter = new ImageFilterImpl();
    if (imageObject.get(imageName) != null) {
      Image old = imageObject.get(imageName);
      if (percentage < 0 || percentage > 100) {
        throw new IllegalArgumentException("percentage must be between 0 and 100");
      }
      newImage = imageFilter.greyScaleImage(old);
      newImage = splitImage(old, newImage, percentage);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not found: " + imageName);
    }
  }

  @Override
  public Image brightenImage(int value, String fileName, String destinationImageName)
          throws FileNotFoundException {
    imageTuning = new ImageTuningImpl();
    newImage = new ImageImpl();
    if (imageObject.get(fileName) != null) {
      Image old = imageObject.get(fileName);
      newImage = imageTuning.brightenImage(value, old);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not Found: " + fileName);
    }
  }

  @Override
  public List<Image> splitRGBImage(String imageName, String destinationImageNameRed,
                             String destinationImageNameGreen, String destinationImageNameBlue)
          throws FileNotFoundException {

    Image destinationImageRed = new ImageImpl();
    Image destinationImageGreen = new ImageImpl();
    Image destinationImageBlue = new ImageImpl();
    newImage = new ImageImpl();
    List<Image> returnList = new ArrayList<>();

    if (imageObject.get(imageName) != null) {

      newImage = imageObject.get(imageName);
      int[][] red = newImage.getRed();
      int[][] green = newImage.getGreen();
      int[][] blue = newImage.getBlue();

      destinationImageRed.setRed(red);
      destinationImageRed.setGreen(red);
      destinationImageRed.setBlue(red);

      destinationImageGreen.setRed(green);
      destinationImageGreen.setGreen(green);
      destinationImageGreen.setBlue(green);

      destinationImageBlue.setRed(blue);
      destinationImageBlue.setGreen(blue);
      destinationImageBlue.setBlue(blue);

      imageObject.put(destinationImageNameRed, destinationImageRed);
      imageObject.put(destinationImageNameGreen, destinationImageGreen);
      imageObject.put(destinationImageNameBlue, destinationImageBlue);

      returnList.add(destinationImageRed);
      returnList.add(destinationImageGreen);
      returnList.add(destinationImageBlue);
      return returnList;
    } else {
      throw new FileNotFoundException("File not found: " + imageName);
    }
  }

  @Override
  public Image combineRGBImage(String destinationImageName, String imageRed, String imageGreen,
                               String imageBlue) throws FileNotFoundException {
    newImage = new ImageImpl();

    if (imageObject.get(imageRed) != null && imageObject.get(imageGreen) != null
            && imageObject.get(imageBlue) != null) {

      Image red = imageObject.get(imageRed);
      Image green = imageObject.get(imageGreen);
      Image blue = imageObject.get(imageBlue);
      newImage.setRed(red.getRed());
      newImage.setGreen(green.getGreen());
      newImage.setBlue(blue.getBlue());
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      if (imageObject.get(imageRed) != null) {
        throw new FileNotFoundException("This file is not to be found: " + imageRed);
      } else if (imageObject.get(imageBlue) != null) {
        throw new FileNotFoundException("This file is not to be found: " + imageBlue);
      } else {
        throw new FileNotFoundException("This file is not to be found: " + imageGreen);
      }
    }
  }

  @Override
  public Image redComponentImage(String imageName, String destinationImageName)
          throws FileNotFoundException {
    newImage = new ImageImpl();
    if (imageObject.get(imageName) != null) {
      Image old = imageObject.get(imageName);
      newImage.setRed(old.getRed());
      newImage.setGreen(old.getRed());
      newImage.setBlue(old.getRed());
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not Found: " + imageName);
    }
  }

  @Override
  public Image redComponentImage(String imageName, String maskName, String destinationImageName)
          throws FileNotFoundException {
    newImage = new ImageImpl();
    maskImageManipulation = new MaskImageManipulationImpl();
    if (imageObject.get(imageName) != null && imageObject.get(maskName) != null) {
      Image old = imageObject.get(imageName);
      Image mask = imageObject.get(maskName);

      if (mask.getImageHeight() < old.getImageHeight() || mask.getImageWidth() < old.getImageWidth()
              || mask.getImageWidth() > old.getImageWidth()
              || mask.getImageHeight() > old.getImageHeight()) {
        throw new IllegalArgumentException("Mask image and input image should be of the same size");
      }

      newImage.setRed(old.getRed());
      newImage.setGreen(old.getRed());
      newImage.setBlue(old.getRed());
      newImage = maskImageManipulation.createMaskedImage(old, newImage, mask);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      if (imageObject.get(imageName) == null) {
        throw new FileNotFoundException("File not Found: " + imageName);
      }
      throw new FileNotFoundException("File not Found: " + maskName);
    }
  }

  @Override
  public Image greenComponentImage(String imageName, String destinationImageName)
          throws FileNotFoundException {
    newImage = new ImageImpl();
    if (imageObject.get(imageName) != null) {
      Image old = imageObject.get(imageName);
      newImage.setRed(old.getGreen());
      newImage.setGreen(old.getGreen());
      newImage.setBlue(old.getGreen());
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not Found: " + imageName);
    }
  }

  @Override
  public Image greenComponentImage(String imageName, String maskName, String destinationImageName)
          throws FileNotFoundException {
    newImage = new ImageImpl();
    maskImageManipulation = new MaskImageManipulationImpl();
    if (imageObject.get(imageName) != null && imageObject.get(maskName) != null) {
      Image old = imageObject.get(imageName);
      Image mask = imageObject.get(maskName);

      if (mask.getImageHeight() < old.getImageHeight() || mask.getImageWidth() < old.getImageWidth()
              || mask.getImageWidth() > old.getImageWidth()
              || mask.getImageHeight() > old.getImageHeight()) {
        throw new IllegalArgumentException("Mask image and input image should be of the same size");
      }

      newImage.setRed(old.getGreen());
      newImage.setGreen(old.getGreen());
      newImage.setBlue(old.getGreen());
      newImage = maskImageManipulation.createMaskedImage(old, newImage, mask);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      if (imageObject.get(imageName) == null) {
        throw new FileNotFoundException("File not Found: " + imageName);
      }
      throw new FileNotFoundException("File not Found: " + maskName);
    }
  }

  @Override
  public Image blueComponentImage(String imageName, String destinationImageName)
          throws FileNotFoundException {
    newImage = new ImageImpl();
    if (imageObject.get(imageName) != null) {
      Image old = imageObject.get(imageName);
      newImage.setRed(old.getBlue());
      newImage.setGreen(old.getBlue());
      newImage.setBlue(old.getBlue());
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not Found: " + imageName);
    }
  }

  @Override
  public Image blueComponentImage(String imageName, String maskName, String destinationImageName)
          throws FileNotFoundException {
    newImage = new ImageImpl();
    maskImageManipulation = new MaskImageManipulationImpl();
    if (imageObject.get(imageName) != null && imageObject.get(maskName) != null) {
      Image old = imageObject.get(imageName);
      Image mask = imageObject.get(maskName);

      if (mask.getImageHeight() < old.getImageHeight() || mask.getImageWidth() < old.getImageWidth()
              || mask.getImageWidth() > old.getImageWidth()
              || mask.getImageHeight() > old.getImageHeight()) {
        throw new IllegalArgumentException("Mask image and input image should be of the same size");
      }

      newImage.setRed(old.getBlue());
      newImage.setGreen(old.getBlue());
      newImage.setBlue(old.getBlue());
      newImage = maskImageManipulation.createMaskedImage(old, newImage, mask);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      if (imageObject.get(imageName) == null) {
        throw new FileNotFoundException("File not Found: " + imageName);
      }
      throw new FileNotFoundException("File not Found: " + maskName);
    }
  }

  @Override
  public Image colorCorrectionImage(String imageName, String destinationImageName)
          throws FileNotFoundException {
    imageTuning = new ImageTuningImpl();
    newImage = new ImageImpl();
    if (imageObject.get(imageName) != null) {
      Image old = imageObject.get(imageName);
      newImage = imageTuning.colorCorrection(old);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not Found: " + imageName);
    }
  }

  @Override
  public Image colorCorrectionImage(String imageName, String destinationImageName,
                                    double percentage) throws FileNotFoundException {
    imageTuning = new ImageTuningImpl();
    newImage = new ImageImpl();
    if (imageObject.get(imageName) != null) {
      Image old = imageObject.get(imageName);
      if (percentage < 0 || percentage > 100) {
        throw new IllegalArgumentException("percentage must be between 0 and 100");
      }
      newImage = imageTuning.colorCorrection(old);
      newImage = splitImage(old, newImage, percentage);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not Found: " + imageName);
    }
  }

  @Override
  public Image levelsAdjustmentImage(double b, double m, double w, String imageName,
                                     String destinationImageName) throws FileNotFoundException {
    imageTuning = new ImageTuningImpl();
    newImage = new ImageImpl();
    if (imageObject.get(imageName) != null) {
      Image old = imageObject.get(imageName);
      if (!(b < m && m < w)) {
        throw new IllegalArgumentException("b must be less than m and m must be less than w");
      }
      if (b < 0 || b > 255) {
        throw new IllegalArgumentException("b must be between 0 and 255");
      }
      if (m < 0 || m > 255) {
        throw new IllegalArgumentException("m must be between 0 and 255");
      }
      if (w < 0 || w > 255) {
        throw new IllegalArgumentException("w must be between 0 and 255");
      }
      newImage = imageTuning.levelsAdjustment(b, m, w, old);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not Found: " + imageName);
    }
  }

  @Override
  public Image levelsAdjustmentImage(double b, double m, double w, String imageName,
                                     String destinationImageName, double percentage)
          throws FileNotFoundException {
    imageTuning = new ImageTuningImpl();
    newImage = new ImageImpl();
    if (imageObject.get(imageName) != null) {
      Image old = imageObject.get(imageName);
      if (percentage < 0 || percentage > 100) {
        throw new IllegalArgumentException("percentage must be between 0 and 100");
      }
      if (b < 0 || b > 255) {
        throw new IllegalArgumentException("b must be between 0 and 255");
      }
      if (m < 0 || m > 255) {
        throw new IllegalArgumentException("m must be between 0 and 255");
      }
      if (w < 0 || w > 255) {
        throw new IllegalArgumentException("w must be between 0 and 255");
      }
      if (!(b < m && m < w)) {
        throw new IllegalArgumentException("b must be less than m and m must be less than w");
      }

      newImage = imageTuning.levelsAdjustment(b, m, w, old);
      newImage = splitImage(old, newImage, percentage);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not Found: " + imageName);
    }
  }


  @Override
  public List<Map<Integer, Integer>> histogram(String imageName) throws
          FileNotFoundException {
    newImage = new ImageImpl();
    imageMetrics = new ImageMetricsImpl();
    if (imageObject.get(imageName) != null) {
      Image old = imageObject.get(imageName);
      histRGBIntensityValues = imageMetrics.getHistogram(old);
      return histRGBIntensityValues;
    } else {
      throw new FileNotFoundException("File not found: " + imageName);
    }
  }

  @Override
  public Image compressImage(double percentage, String imageName,
                             String destinationImageName) throws FileNotFoundException {
    imageFilter = new ImageFilterImpl();
    newImage = new ImageImpl();
    if (imageObject.get(imageName) != null) {
      Image old = imageObject.get(imageName);
      if (percentage < 0 || percentage > 100) {
        throw new IllegalArgumentException("Percentage cannot be less than 0");
      }
      newImage = imageFilter.compressImage(percentage, old);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not Found: " + imageName);
    }
  }


  private Image splitImage(Image old, Image updatedImage, double percentage) {
    newImage = new ImageImpl();

    int[][] red = old.getRed();
    int[][] green = old.getGreen();
    int[][] blue = old.getBlue();

    int[][] updatedRed = updatedImage.getRed();
    int[][] updatedGreen = updatedImage.getGreen();
    int[][] updatedBlue = updatedImage.getBlue();

    int[][] newRed = new int[old.getImageWidth()][old.getImageHeight()];
    int[][] newGreen = new int[old.getImageWidth()][old.getImageHeight()];
    int[][] newBlue = new int[old.getImageWidth()][old.getImageHeight()];

    double percent = ((double) percentage) / 100;
    int splitLine = (int) (old.getImageWidth() * percent);
    for (int i = 0; i < splitLine; i++) {
      for (int j = 0; j < updatedImage.getImageHeight(); j++) {
        newRed[i][j] = updatedRed[i][j];
        newGreen[i][j] = updatedGreen[i][j];
        newBlue[i][j] = updatedBlue[i][j];
      }
    }
    for (int i = splitLine; i < old.getImageWidth(); i++) {
      for (int j = 0; j < old.getImageHeight(); j++) {
        newRed[i][j] = red[i][j];
        newGreen[i][j] = green[i][j];
        newBlue[i][j] = blue[i][j];
      }
    }
    newImage.setRed(newRed);
    newImage.setGreen(newGreen);
    newImage.setBlue(newBlue);

    return newImage;
  }

  @Override
  public Image downScaleImage(double newSmallWidth, double newSmallHeight, String fileName,
                              String destinationImageName) throws FileNotFoundException {
    newImage = new ImageImpl();
    imageTuning = new ImageTuningImpl();
    if (imageObject.get(fileName) != null) {
      Image old = imageObject.get(fileName);

      if (newSmallWidth <= 0 || newSmallWidth >= old.getImageWidth()) {
        throw new IllegalArgumentException("Small image width must be greater than 0 and less than "
                + "original image width");
      }

      if (newSmallHeight <= 0 || newSmallHeight >= old.getImageHeight()) {
        throw new IllegalArgumentException("Small image height must be greater than 0 and less "
                + "than original image height");
      }

      newImage = imageTuning.downscaleImage(old, (int) newSmallWidth, (int) newSmallHeight);
      imageObject.put(destinationImageName, newImage);
      return newImage;
    } else {
      throw new FileNotFoundException("File not Found: " + fileName);
    }
  }

  @Override
  public Image undoImage(String name) {
    newImage = new ImageImpl();
    imageObject.remove(name);

    Map.Entry<String, Image> lastEntry = null;
    for (Map.Entry<String, Image> entry : imageObject.entrySet()) {
      lastEntry = entry;
    }
    newImage = imageObject.get(lastEntry.getKey());
    return newImage;
  }
}
