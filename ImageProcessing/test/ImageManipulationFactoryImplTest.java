import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import model.ImageManipulation;
import model.ImageManipulationFactory;
import model.ImageManipulationFactoryImpl;
import utility.ImageUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is the ImageManipulation test class. This class tests all the functionalities of the
 * model.
 */
public class ImageManipulationFactoryImplTest {

  private ImageManipulationFactory imageProcessingFactory = new ImageManipulationFactoryImpl();

  // Change these variables as per your convenience.
  private final String imageSourcePath = "res/testInputs/manhattan-small.png";
  private final String imageSourcePathNature = "res/testInputs/nature.jpg";
  private final String imageSourcePathGalaxy = "res/testInputs/galaxy.png";
  private final String imageSourcePathPPMSample = "res/testInputs/nature.ppm";
  private final String imageSourcePathMask = "res/testInputs/mask.ppm";
  private final String imageSourcePathMaskGreater = "res/testInputs/maskGreater.ppm";
  private final String imageSourcePathMaskSmaller = "res/testInputs/maskSmaller.ppm";

  private ImageManipulation imageProcessing
          = imageProcessingFactory.createImageManipulation(imageSourcePath);

  private final String imageDestinationPathForSave =
          "res/testDestination/testInputImage.png";

  private final String imageDestinationPathForSepia =
          "res/testDestination/testSepia.png";
  private final String comparisionImagePathForSepia =
          "res/testInputs/manhattan-small-sepia.png";

  private final String imageDestinationPathForSharpening =
          "res/testDestination/testSharpen.png";
  private final String imageDestinationPathForSharpening2 =
          "res/testDestination/testSharpen2.png";
  private final String comparisionImagePathForSharpening =
          "res/testInputs/manhattan-small-sharper.png";
  private final String comparisionImagePathForSharpening2 =
          "res/testInputs/manhattan-small-sharpen-2.png";

  private final String imageDestinationPathForBlurring =
          "res/testDestination/testBlurring.png";
  private final String imageDestinationPathForBlurring2 =
          "res/testDestination/testBlurring2.png";
  private final String comparisionImagePathForBlurring =
          "res/testInputs/manhattan-small-blur.png";
  private final String comparisionImagePathForBlurring2 =
          "res/testInputs/manhattan-small-blur-2.png";

  private final String imageDestinationPathForHorizontalFlip =
          "res/testDestination/testHorizontalFlip.png";
  private final String comparisionImagePathForHorizontalFlip =
          "res/testInputs/manhattan-small-horizontal.png";

  private final String imageDestinationPathForVerticalFlip =
          "res/testDestination/testVerticalFlip.png";
  private final String comparisionImagePathForVerticalFlip =
          "res/testInputs/manhattan-small-vertical.png";

  private final String imageDestinationPathForFirstHorizontalThenVerticalFlip =
          "res/testDestination/testFirstHorizontalThenVerticalFlip.png";
  private final String comparisionImagePathForFirstHorizontalThenVerticalFlip =
          "res/testInputs/manhattan-small-horizontal-vertical.png";

  private final String imageDestinationPathForFirstVerticalThenHorizontalFlip =
          "res/testDestination/testFirstVerticalThenHorizontalFlip.png";
  private final String comparisionImagePathForFirstVerticalThenHorizontalFlip =
          "res/testInputs/manhattan-small-vertical-horizontal.png";

  private final String imageDestinationPathForValue =
          "res/testDestination/testValue.png";
  private final String comparisionImagePathForValue =
          "res/testInputs/manhattan-small-value-greyscale.png";

  private final String imageDestinationPathForIntensity =
          "res/testDestination/testIntensity.png";
  private final String comparisionImagePathForIntensity =
          "res/testInputs/manhattan-small-intensity-greyscale.png";

  private final String imageDestinationPathForLuma =
          "res/testDestination/testLuma.png";
  private final String comparisionImagePathForLuma =
          "res/testInputs/manhattan-small-luma-greyscale.png";

  private final String imageDestinationPathForBrighten =
          "res/testDestination/testBrighten.png";
  private final String comparisionImagePathForBrighten =
          "res/testInputs/manhattan-small-brighter-by-50.png";

  private final String imageDestinationPathForDarken =
          "res/testDestination/testDarken.png";
  private final String comparisionImagePathForDarken =
          "res/testInputs/manhattan-small-darker-by-50.png";

  private final String imageDestinationPathForRed =
          "res/testDestination/testRed.png";
  private final String comparisionImagePathForRed =
          "res/testInputs/manhattan-small-red.png";

  private final String imageDestinationPathForGreen =
          "res/testDestination/testGreen.png";
  private final String comparisionImagePathForGreen =
          "res/testInputs/manhattan-small-green.png";

  private final String imageDestinationPathForBlue =
          "res/testDestination/testBlue.png";
  private final String comparisionImagePathForBlue =
          "res/testInputs/manhattan-small-blue.png";

  private final String imageDestinationPathForCombined =
          "res/testDestination/testCombined.png";
  private final String comparisionImagePathForCombined =
          "res/testInputs/manhattan-small.png";

  private final String imageDestinationPathForCompress20 =
          "res/testDestination/testCompress20.png";
  private final String comparisionImagePathForCompress20 =
          "res/testInputs/manhattan-small-compress-20.png";

  private final String imageDestinationPathForCompress50 =
          "res/testDestination/testCompress50.png";
  private final String comparisionImagePathForCompress50 =
          "res/testInputs/manhattan-small-compress-50.png";

  private final String imageDestinationPathForCompress90 =
          "res/testDestination/testCompress90.png";
  private final String comparisionImagePathForCompress90 =
          "res/testInputs/manhattan-small-compress-90.png";

  private final String imageDestinationPathForCompress0 =
          "res/testDestination/testCompress0.png";
  private final String comparisionImagePathForCompress0 =
          "res/testInputs/manhattan-small.png";

  private final String imageDestinationPathForColorCorrection =
          "res/testDestination/testColorCorrect.png";
  private final String comparisionImagePathForColorCorrection =
          "res/testInputs/galaxy-corrected.png";

  private final String imageDestinationPathForLevelAdjustment =
          "res/testDestination/testLevelAdjust.png";
  private final String comparisionImagePathForLevelAdjustment =
          "res/testInputs/galaxy-adjusted.png";

  private final String imageDestinationPathForLevelAdjustmentColorCorrection =
          "res/testDestination/testLevelAdjustAndColorCorrect.png";
  private final String comparisionImagePathForLevelAdjustmentColorCorrection =
          "res/testInputs/galaxy-adjusted-color-corrected.png";


  private final String imageDestinationPathForSplitBlur50 =
          "res/testDestination/testSplitBlur50.png";
  private final String comparisionImagePathForSplitBlur50 =
          "res/testInputs/splitBlur50.png";

  private final String imageDestinationPathForSplitBlur0 =
          "res/testDestination/testSplitBlur0.png";
  private final String comparisionImagePathForSplitBlur0 =
          "res/testInputs/splitBlur0.png";

  private final String imageDestinationPathForSplitBlur100 =
          "res/testDestination/testSplitBlur100.png";
  private final String comparisionImagePathForSplitBlur100 =
          "res/testInputs/splitBlur100.png";

  private final String imageDestinationPathForSplitBlurNeg50 =
          "res/testDestination/testSplitBlur100.png";
  private final String comparisionImagePathForSplitBlurNeg50 =
          "res/testInputs/splitBlur100.png";


  private final String imageDestinationPathForSplitSharpen =
          "res/testDestination/testSplitSharpen.png";
  private final String comparisionImagePathForSplitSharpen =
          "res/testInputs/splitSharpen.png";

  private final String imageDestinationPathFortSplitGrayscale =
          "res/testDestination/testSplitGrayscale.png";
  private final String comparisionImagePathForSplitGrayscale =
          "res/testInputs/splitGrayscale.png";

  private final String imageDestinationPathForSplitLevelAdjustment =
          "res/testDestination/testSplitLevelAdjust.png";
  private final String comparisionImagePathForSplitLevelAdjustment =
          "res/testInputs/splitLevelAdjust.png";

  private final String imageDestinationPathForSplitColorCorrection =
          "res/testDestination/testSplitColorCorrection.png";
  private final String comparisionImagePathForSplitColorCorrection =
          "res/testInputs/splitColorCorrection.png";

  private final String imageDestinationPathForSplitSepia =
          "res/testDestination/testSplitSepia.png";
  private final String comparisionImagePathForSplitSepia =
          "res/testInputs/splitSepia.png";

  private final String imageDestinationPathForSplitValue =
          "res/testDestination/testSplitValue.png";
  private final String comparisionImagePathForSplitValue =
          "res/testInputs/splitValue.png";

  private final String imageDestinationPathForSplitLuma =
          "res/testDestination/testSplitLuma.png";
  private final String comparisionImagePathForSplitLuma =
          "res/testInputs/splitLuma.png";

  private final String imageDestinationPathForSplitIntensity =
          "res/testDestination/testSplitIntensity.png";
  private final String comparisionImagePathForSplitIntensity =
          "res/testInputs/splitIntensity.png";

  private final String imageDestinationPathForDownsizing =
          "res/testDestination/testDownsizing.png";
  private final String comparisionImagePathForDownsizing =
          "res/testInputs/downsizing.png";

  private final String imageDestinationPathForSepiaMask =
          "res/testDestination/testSepiaMask.ppm";
  private final String comparisionImagePathForSepiaMask =
          "res/testInputs/sepiaMask.ppm";

  private final String imageDestinationPathForBlurMask =
          "res/testDestination/testBlurMask.ppm";
  private final String comparisionImagePathForBlurMask =
          "res/testInputs/blurMask.ppm";

  private final String imageDestinationPathForSharpenMask =
          "res/testDestination/testSharpenaMask.ppm";
  private final String comparisionImagePathForSharpenMask =
          "res/testInputs/sharpenMask.ppm";

  private final String imageDestinationPathForValueMask =
          "res/testDestination/testValueMask.ppm";
  private final String comparisionImagePathForValueMask =
          "res/testInputs/valueMask.ppm";

  private final String imageDestinationPathForLumaMask =
          "res/testDestination/testLumaMask.ppm";
  private final String comparisionImagePathForLumaMask =
          "res/testInputs/lumaMask.ppm";

  private final String imageDestinationPathForIntensityMask =
          "res/testDestination/testIntensityMask.ppm";
  private final String comparisionImagePathForIntensityMask =
          "res/testInputs/intensityMask.ppm";

  private final String imageDestinationPathForRedComponentMask =
          "res/testDestination/testRedComponentMask.ppm";
  private final String comparisionImagePathForRedComponentMask =
          "res/testInputs/redComponentMask.ppm";

  private final String imageDestinationPathForBlueComponentMask =
          "res/testDestination/testBlueComponentMask.ppm";
  private final String comparisionImagePathForBlueComponentMask =
          "res/testInputs/blueComponentMask.ppm";

  private final String imageDestinationPathForGreenComponentMask =
          "res/testDestination/testGreenComponentMask.ppm";
  private final String comparisionImagePathForGreenComponentMask =
          "res/testInputs/greenComponentMask.ppm";

  private boolean imagesAreEqual(BufferedImage img1, BufferedImage img2) {
    // Check if dimensions are the same
    if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
      return false;
    }

    // Compare each pixel data
    for (int y = 0; y < img1.getHeight(); y++) {
      for (int x = 0; x < img1.getWidth(); x++) {
        if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean comparePPMFiles(String file1, String file2) throws IOException {
    List<String> data1 = readPPMData(file1);
    List<String> data2 = readPPMData(file2);

    return data1.equals(data2);
  }

  private List<String> readPPMData(String filePath) throws IOException {
    List<String> data = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        line = line.trim();

        // Skip comments and empty lines
        if (line.startsWith("#") || line.isEmpty()) {
          continue;
        }

        // Split the line into parts and add each part to the data list
        String[] parts = line.split("\\s+");
        for (String part : parts) {
          data.add(part);
        }
      }
    }

    return data;
  }

  /**
   * Test to test the load function.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testLoad() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile");
    ImageUtil.saveImage(imageDestinationPathForSave, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));
    //image gets dumped in this path. This is proof that loadImage is working as expected.
    BufferedImage image2 = ImageIO.read(new File(imageSourcePath));
    BufferedImage image3 = ImageIO.read(new File(imageDestinationPathForSave));
    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test to test the save function.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testSave() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile");
    ImageUtil.saveImage(imageDestinationPathForSave, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));
    BufferedImage image2 = ImageIO.read(new File(imageSourcePath));
    BufferedImage image3 = ImageIO.read(new File(imageDestinationPathForSave));
    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test to test the load and save function when load has empty file name.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testLoadFileNameEmptyAndSave() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile");
    ImageUtil.saveImage(imageDestinationPathForSave, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));
    BufferedImage image2 = ImageIO.read(new File(imageSourcePath));
    BufferedImage image3 = ImageIO.read(new File(imageDestinationPathForSave));
    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test to test the load and save function when save has empty file name.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testLoadAndSaveFileNameEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    List<int[][]> getRGBSave = imageProcessing.saveImage("");
    ImageUtil.saveImage(imageDestinationPathForSave, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));
    BufferedImage image2 = ImageIO.read(new File(imageSourcePath));
    BufferedImage image3 = ImageIO.read(new File(imageDestinationPathForSave));
    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test to test the load and save function when both have empty file name.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testLoadFileNameEmptyAndSaveFileNameEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("");
    ImageUtil.saveImage(imageDestinationPathForSave, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));
    BufferedImage image2 = ImageIO.read(new File(imageSourcePath));
    BufferedImage image3 = ImageIO.read(new File(imageDestinationPathForSave));
    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test to test the load and save function when both have space file name.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testLoadFileNameEmptyAndSaveFileNameSpace() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, " ");
    List<int[][]> getRGBSave = imageProcessing.saveImage(" ");
    ImageUtil.saveImage(imageDestinationPathForSave, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));
    BufferedImage image2 = ImageIO.read(new File(imageSourcePath));
    BufferedImage image3 = ImageIO.read(new File(imageDestinationPathForSave));
    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  //----------------------------------------------------------------------------------------------

  /**
   * Test the sepia filter functionality.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testSepia() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "sepia");
    imageProcessing.convertIntoSepia("sepia", "testFile-sepia");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-sepia");
    ImageUtil.saveImage(imageDestinationPathForSepia, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForSepia));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForSepia));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the sepia filter functionality when source image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testSepiaSourceEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.convertIntoSepia("", "testFile-sepia");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-sepia");
    ImageUtil.saveImage(imageDestinationPathForSepia, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForSepia));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForSepia));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the sepia filter functionality when destination image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testSepiaDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.convertIntoSepia("testFile", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-sepia");
    ImageUtil.saveImage(imageDestinationPathForSepia, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForSepia));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForSepia));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the sepia filter functionality when both images names are empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class) //Both io and filenotfound work.
  public void testSepiaSourceAndDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.convertIntoSepia("", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-sepia");
    ImageUtil.saveImage(imageDestinationPathForSepia, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForSepia));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForSepia));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the sepia filter functionality when wrong image is taken by sepia.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)  // FileNotFoundException??. Even IOException works.
  public void testSepiaSourceInLoadEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.convertIntoSepia("testFile-notExisting",
            "testFile-sepia");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-sepia");
    ImageUtil.saveImage(imageDestinationPathForSepia, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForSepia));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForSepia));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // ---------------------------------------------------------------------------------------------

  /**
   * Test the image sharpening functionality.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testSharpening() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.sharpenImage("testFile", "testFile-sharpen");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-sharpen");
    ImageUtil.saveImage(imageDestinationPathForSharpening, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForSharpening));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForSharpening));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image sharpening functionality when source image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testSharpeningSourceEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.sharpenImage("", "testFile-sharpen");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-sharpen");
    ImageUtil.saveImage(imageDestinationPathForSharpening, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForSharpening));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForSharpening));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image sharpening functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testSharpeningDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.sharpenImage("testFile", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-sharpen");
    ImageUtil.saveImage(imageDestinationPathForSharpening, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForSharpening));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForSharpening));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image sharpening functionality when when wrong image is taken by sharpen..
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testSharpeningSourceInLoadEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.sharpenImage("testFile-notExisting",
            "testFile-sharpen");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-sharpen");
    ImageUtil.saveImage(imageDestinationPathForSharpening, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForSharpening));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForSharpening));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image sharpening functionality when both source and dest image is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testSharpeningSourceEmptyAndDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.sharpenImage("", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-sharpen");
    ImageUtil.saveImage(imageDestinationPathForSharpening, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForSharpening));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForSharpening));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image sharpening functionality twice.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testSharpeningTwice() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.sharpenImage("testFile", "testFile-sharpen");
    imageProcessing.sharpenImage("testFile-sharpen",
            "testFile-sharpen-sharpen");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-sharpen-sharpen");
    ImageUtil.saveImage(imageDestinationPathForSharpening2, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForSharpening2));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForSharpening2));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // ---------------------------------------------------------------------------------------------

  /**
   * Test the image blurring functionality.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testBlurring() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.blurImage("testFile", "testFile-blur");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-blur");
    ImageUtil.saveImage(imageDestinationPathForBlurring, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForBlurring));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForBlurring));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image blurring functionality when source image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testBlurringingSourceEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.blurImage("", "testFile-blur");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-blur");
    ImageUtil.saveImage(imageDestinationPathForBlurring, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForBlurring));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForBlurring));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image blurring functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testBlurringDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.blurImage("testFile", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-blur");
    ImageUtil.saveImage(imageDestinationPathForBlurring, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForBlurring));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForBlurring));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image blurring functionality when a non-existing image is taken by blur.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testBlurringSourceInLoadEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.blurImage("testFile-notExisting",
            "testFile-blur");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-blur");
    ImageUtil.saveImage(imageDestinationPathForBlurring, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForBlurring));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForBlurring));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image blurring functionality when both source and dest image is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testBlurringSourceEmptyAndDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.blurImage("", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-blur");
    ImageUtil.saveImage(imageDestinationPathForBlurring, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForBlurring));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForBlurring));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image blurring twice functionality when both source and dest image is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testBlurringTwice() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.blurImage("testFile", "testFile-blur");
    imageProcessing.blurImage("testFile-blur", "testFile-blur-blur");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-blur");
    ImageUtil.saveImage(imageDestinationPathForBlurring2, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForBlurring2));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForBlurring2));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // ---------------------------------------------------------------------------------------------

  /**
   * Test image horizontal flip functionality.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testFlipImageHorizontally() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.flipImageHorizontally("testFile",
            "testFile-horizontalFlip");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-horizontalFlip");
    ImageUtil.saveImage(imageDestinationPathForHorizontalFlip, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForHorizontalFlip));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForHorizontalFlip));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image horizontal flip functionality when source image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testFlipImageHorizontallySourceEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.flipImageHorizontally("",
            "testFile-horizontalFlip");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-horizontalFlip");
    ImageUtil.saveImage(imageDestinationPathForHorizontalFlip, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForHorizontalFlip));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForHorizontalFlip));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image horizontal flip functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testFlipImageHorizontallyDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.flipImageHorizontally("testFile", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-horizontalFlip");
    ImageUtil.saveImage(imageDestinationPathForHorizontalFlip, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForHorizontalFlip));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForHorizontalFlip));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image horizontal flip functionality when a non-existing image is taken by horizontal
   * flip.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testFlipImageHorizontallySourceInLoadEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.flipImageHorizontally("testFile-notExisting",
            "testFile-horizontalFlip");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-horizontalFlip");
    ImageUtil.saveImage(imageDestinationPathForHorizontalFlip, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForHorizontalFlip));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForHorizontalFlip));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image horizontal flip functionality both source and dest is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testFlipImageHorizontallySourceEmptyAndDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.flipImageHorizontally("", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-horizontalFlip");
    ImageUtil.saveImage(imageDestinationPathForHorizontalFlip, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForHorizontalFlip));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForHorizontalFlip));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // ---------------------------------------------------------------------------------------------

  /**
   * Test image vertical flip functionality.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testFlipImageVertically() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.flipImageVertically("testFile",
            "testFile-verticalFlip");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-verticalFlip");
    ImageUtil.saveImage(imageDestinationPathForVerticalFlip, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForVerticalFlip));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForVerticalFlip));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image horizontal flip functionality when source image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testFlipImageVerticallySourceEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.flipImageVertically("", "testFile-verticalFlip");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-verticalFlip");
    ImageUtil.saveImage(imageDestinationPathForVerticalFlip, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForVerticalFlip));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForVerticalFlip));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image horizontal flip functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testFlipImageVerticallyDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.flipImageVertically("testFile", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-verticalFlip");
    ImageUtil.saveImage(imageDestinationPathForVerticalFlip, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForVerticalFlip));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForVerticalFlip));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image horizontal flip functionality when a non-existing image is taken by
   * vertical-flip.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testFlipImageVerticallySourceInLoadEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.flipImageVertically("testFile-notExisting",
            "testFile-verticalFlip");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-verticalFlip");
    ImageUtil.saveImage(imageDestinationPathForVerticalFlip, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForVerticalFlip));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForVerticalFlip));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image horizontal flip functionality when both source and dest is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testFlipImageVerticallySourceEmptyAndDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.flipImageVertically("", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-verticalFlip");
    ImageUtil.saveImage(imageDestinationPathForVerticalFlip, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForVerticalFlip));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForVerticalFlip));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // ---------------------------------------------------------------------------------------------

  /**
   * First flip Horizontally and then flip vertically.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testFlipImageHorizontallyVertically() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.flipImageHorizontally("testFile",
            "testFile-flipImageHorizontally");
    imageProcessing.flipImageVertically("testFile-flipImageHorizontally",
            "testFile-flipImageHorizontally-flipImageVertically");
    List<int[][]> getRGBSave = imageProcessing.saveImage(
            "testFile-flipImageHorizontally-flipImageVertically");
    ImageUtil.saveImage(imageDestinationPathForFirstHorizontalThenVerticalFlip, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(
            new File(imageDestinationPathForFirstHorizontalThenVerticalFlip));

    BufferedImage image3 =
            ImageIO.read(new File(comparisionImagePathForFirstHorizontalThenVerticalFlip));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // ---------------------------------------------------------------------------------------------

  /**
   * First flip the image vertically then flip it horizontally.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testFlipImageVerticallyHorizontally() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.flipImageVertically("testFile",
            "testFile-flipImageVertically");
    imageProcessing.flipImageHorizontally("testFile-flipImageVertically",
            "testFile-flipImageVertically-flipImageHorizontally");
    List<int[][]> getRGBSave = imageProcessing.saveImage(
            "testFile-flipImageVertically-flipImageHorizontally");
    ImageUtil.saveImage(imageDestinationPathForFirstVerticalThenHorizontalFlip, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(
            new File(imageDestinationPathForFirstVerticalThenHorizontalFlip));

    BufferedImage image3 = ImageIO.read(
            new File(comparisionImagePathForFirstVerticalThenHorizontalFlip));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // ---------------------------------------------------------------------------------------------

  /**
   * Test the value component of the image.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testValueComponentOfImage() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.valueComponentOfImage("testFile",
            "testFile-valueComponent");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-valueComponent");
    ImageUtil.saveImage(imageDestinationPathForValue, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForValue));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForValue));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image value functionality when source image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testValueComponentSourceEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.valueComponentOfImage("",
            "testFile-valueComponent");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-valueComponent");
    ImageUtil.saveImage(imageDestinationPathForValue, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForValue));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForValue));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image value functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testValueComponentDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.valueComponentOfImage("testFile", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-valueComponent");
    ImageUtil.saveImage(imageDestinationPathForValue, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForValue));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForValue));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image value functionality when non-existing image is taken by value.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testValueComponentSourceInLoadEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.valueComponentOfImage("testFile-notExisting",
            "testFile-valueComponent");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-valueComponent");
    ImageUtil.saveImage(imageDestinationPathForValue, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForValue));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForValue));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image horizontal flip functionality when both source and dest is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testValueComponentSourceEmptyAndDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.valueComponentOfImage("", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-valueComponent");
    ImageUtil.saveImage(imageDestinationPathForValue, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForValue));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForValue));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // ---------------------------------------------------------------------------------------------

  /**
   * Test the intensity component of the image.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testIntensityComponentOfImage() throws IOException {
    // Result off by one pixel. Eg. In place of getting 177, we are getting 178 for pixel (2,2).
    // We discussed this with Prof. Amit Shesh and he said it's okay.
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.intensityComponentOfImage("testFile",
            "testFile-intensityComponent");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-intensityComponent");
    ImageUtil.saveImage(imageDestinationPathForIntensity, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(
            new File(imageDestinationPathForIntensity));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForIntensity));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image intensity functionality when source image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testIntensityComponentSourceEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.intensityComponentOfImage("",
            "testFile-intensityComponent");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-intensityComponent");
    ImageUtil.saveImage(imageDestinationPathForIntensity, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForIntensity));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForIntensity));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image intensity functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testIntensityComponentDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.intensityComponentOfImage("testFile", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-intensityComponent");
    ImageUtil.saveImage(imageDestinationPathForIntensity, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForIntensity));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForIntensity));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image intensity functionality when a non-existing image is taken by intensity.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testIntensityComponentSourceInLoadEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.intensityComponentOfImage("testFile-notExisting",
            "testFile-intensityComponent");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-intensityComponent");
    ImageUtil.saveImage(imageDestinationPathForIntensity, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForIntensity));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForIntensity));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image horizontal flip functionality when both source and dest is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testIntensityComponentSourceEmptyAndDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.intensityComponentOfImage("", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-intensityComponent");
    ImageUtil.saveImage(imageDestinationPathForIntensity, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForIntensity));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForIntensity));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // ---------------------------------------------------------------------------------------------

  /**
   * Test the luma component of the image.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testLumaComponentOfImage() throws IOException {
    // Result off by one pixel. Eg. In place of getting 177, we are getting 178 for pixel (2,2).
    // We discussed this with Prof. Amit Shesh and he said it's okay.
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.lumaComponentOfImage("testFile",
            "testFile-lumaComponent");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-lumaComponent");
    ImageUtil.saveImage(imageDestinationPathForLuma, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForLuma));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForLuma));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image luma functionality when source image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testLumaComponentSourceEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.lumaComponentOfImage("", "testFile-lumaComponent");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-lumaComponent");
    ImageUtil.saveImage(imageDestinationPathForLuma, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForLuma));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForLuma));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image luma functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testLumaComponentDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.lumaComponentOfImage("testFile", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-lumaComponent");
    ImageUtil.saveImage(imageDestinationPathForLuma, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForLuma));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForLuma));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image luma functionality when a non-existing image is taken by luma.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testLumaComponentSourceInLoadEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.lumaComponentOfImage("testFile-notExisting",
            "testFile-lumaComponent");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-lumaComponent");
    ImageUtil.saveImage(imageDestinationPathForLuma, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForLuma));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForLuma));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image luma functionality when both source and dest is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testLumaComponentSourceEmptyAndDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.lumaComponentOfImage("", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-lumaComponent");
    ImageUtil.saveImage(imageDestinationPathForLuma, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForLuma));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForLuma));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // ---------------------------------------------------------------------------------------------

  /**
   * Test the image brightening functionality.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testBrightenImage() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.brightenImage(50, "testFile",
            "testFile-brighter");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-brighter");
    ImageUtil.saveImage(imageDestinationPathForBrighten, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForBrighten));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForBrighten));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image brightening functionality when source image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testBrightenImageSourceEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.brightenImage(50, "", "testFile-brighter");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-brighter");
    ImageUtil.saveImage(imageDestinationPathForBrighten, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForBrighten));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForBrighten));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image brightening functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testBrightenImageDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.brightenImage(50, "testFile", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-brighter");
    ImageUtil.saveImage(imageDestinationPathForBrighten, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForBrighten));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForBrighten));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image brightening functionality when when a non-existing image is taken by brighten.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testBrightenImageSourceInLoadEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.brightenImage(50, "testFile-notExisting",
            "testFile-brighter");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-brighter");
    ImageUtil.saveImage(imageDestinationPathForBrighten, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForBrighten));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForBrighten));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image brightening functionality when both source and dest is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testBrightenImageSourceEmptyAndDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.brightenImage(50, "", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-brighter");
    ImageUtil.saveImage(imageDestinationPathForBrighten, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForBrighten));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForBrighten));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image brightening functionality when brightness is 0. It matches with the original
   * image.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testBrightenImageZero() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.brightenImage(0, "testFile",
            "testFile-brighter");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-brighter");
    ImageUtil.saveImage(imageDestinationPathForBrighten, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForBrighten));

    BufferedImage image3 = ImageIO.read(new File(imageSourcePath));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // ---------------------------------------------------------------------------------------------

  /**
   * Test image darkening functionality.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testDarkenImage() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.brightenImage(-50, "testFile",
            "testFile-darker");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-darker");
    ImageUtil.saveImage(imageDestinationPathForDarken, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForDarken));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForDarken));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image darkening functionality when source image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testDarkenImageSourceEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.brightenImage(-50, "", "testFile-darker");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-darker");
    ImageUtil.saveImage(imageDestinationPathForDarken, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForDarken));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForDarken));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image darkening functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testDarkenImageDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.brightenImage(-50, "testFile", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-darker");
    ImageUtil.saveImage(imageDestinationPathForDarken, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForDarken));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForDarken));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image darkening functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testDarkenImageSourceInLoadEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.brightenImage(-50, "testFile-notExisting",
            "testFile-darker");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-darker");
    ImageUtil.saveImage(imageDestinationPathForDarken, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForDarken));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForDarken));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image darkening functionality when when wrong image is taken by sharpening.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testDarkenImageSourceEmptyAndDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.brightenImage(-50, "", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-darker");
    ImageUtil.saveImage(imageDestinationPathForDarken, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForDarken));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForDarken));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image darkening functionality when brightness is 0. It matches with the original
   * image.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testDarkenImageZero() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.brightenImage(0, "testFile",
            "testFile-darker");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-darker");
    ImageUtil.saveImage(imageDestinationPathForDarken, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForDarken));

    BufferedImage image3 = ImageIO.read(new File(imageSourcePath));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // ---------------------------------------------------------------------------------------------

  /**
   * Test to check if the RGB split is working correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testSplitRGBImage() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.splitRGBImage("testFile", "testFile-red",
            "testFile-green", "testFile-blue");

    List<int[][]> getRGBSaveRed = imageProcessing.saveImage("testFile-red");
    ImageUtil.saveImage(imageDestinationPathForRed, getRGBSaveRed.get(0), getRGBSaveRed.get(1),
            getRGBSaveRed.get(2));
    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForRed));

    List<int[][]> getRGBSaveGreen = imageProcessing.saveImage("testFile-green");
    ImageUtil.saveImage(imageDestinationPathForGreen, getRGBSaveGreen.get(0),
            getRGBSaveGreen.get(1), getRGBSaveGreen.get(2));
    BufferedImage image3 = ImageIO.read(new File(imageDestinationPathForGreen));

    List<int[][]> getRGBSaveBlue = imageProcessing.saveImage("testFile-blue");
    ImageUtil.saveImage(imageDestinationPathForBlue, getRGBSaveBlue.get(0), getRGBSaveBlue.get(1),
            getRGBSaveBlue.get(2));
    BufferedImage image4 = ImageIO.read(new File(imageDestinationPathForBlue));

    BufferedImage image5 = ImageIO.read(new File(comparisionImagePathForRed));
    BufferedImage image6 = ImageIO.read(new File(comparisionImagePathForGreen));
    BufferedImage image7 = ImageIO.read(new File(comparisionImagePathForBlue));

    assertTrue("Images should be equal", imagesAreEqual(image2, image5));
    assertTrue("Images should be equal", imagesAreEqual(image3, image6));
    assertTrue("Images should be equal", imagesAreEqual(image4, image7));
  }

  /**
   * Test to check if the RGB split is working correctly when source is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testSplitRGBImageSourceEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.splitRGBImage("", "testFile-red",
            "testFile-green", "testFile-blue");

    List<int[][]> getRGBSaveRed = imageProcessing.saveImage("testFile-red");
    ImageUtil.saveImage(imageDestinationPathForRed, getRGBSaveRed.get(0), getRGBSaveRed.get(1),
            getRGBSaveRed.get(2));
    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForRed));

    List<int[][]> getRGBSaveGreen = imageProcessing.saveImage("testFile-green");
    ImageUtil.saveImage(imageDestinationPathForGreen, getRGBSaveGreen.get(0),
            getRGBSaveGreen.get(1), getRGBSaveGreen.get(2));
    BufferedImage image3 = ImageIO.read(new File(imageDestinationPathForGreen));

    List<int[][]> getRGBSaveBlue = imageProcessing.saveImage("testFile-blue");
    ImageUtil.saveImage(imageDestinationPathForBlue, getRGBSaveBlue.get(0), getRGBSaveBlue.get(1),
            getRGBSaveBlue.get(2));
    BufferedImage image4 = ImageIO.read(new File(imageDestinationPathForBlue));

    BufferedImage image5 = ImageIO.read(new File(comparisionImagePathForRed));
    BufferedImage image6 = ImageIO.read(new File(comparisionImagePathForGreen));
    BufferedImage image7 = ImageIO.read(new File(comparisionImagePathForBlue));

    assertTrue("Images should be equal", imagesAreEqual(image2, image5));
    assertTrue("Images should be equal", imagesAreEqual(image3, image6));
    assertTrue("Images should be equal", imagesAreEqual(image4, image7));
  }

  /**
   * Test to check if the RGB split is working correctly when destination is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testSplitRGBImageDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.splitRGBImage("testFile", "",
            "", "");

    List<int[][]> getRGBSaveRed = imageProcessing.saveImage("testFile-red");
    ImageUtil.saveImage(imageDestinationPathForRed, getRGBSaveRed.get(0), getRGBSaveRed.get(1),
            getRGBSaveRed.get(2));
    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForRed));

    List<int[][]> getRGBSaveGreen = imageProcessing.saveImage("testFile-green");
    ImageUtil.saveImage(imageDestinationPathForGreen, getRGBSaveGreen.get(0),
            getRGBSaveGreen.get(1), getRGBSaveGreen.get(2));
    BufferedImage image3 = ImageIO.read(new File(imageDestinationPathForGreen));

    List<int[][]> getRGBSaveBlue = imageProcessing.saveImage("testFile-blue");
    ImageUtil.saveImage(imageDestinationPathForBlue, getRGBSaveBlue.get(0), getRGBSaveBlue.get(1),
            getRGBSaveBlue.get(2));
    BufferedImage image4 = ImageIO.read(new File(imageDestinationPathForBlue));

    BufferedImage image5 = ImageIO.read(new File(comparisionImagePathForRed));
    BufferedImage image6 = ImageIO.read(new File(comparisionImagePathForGreen));
    BufferedImage image7 = ImageIO.read(new File(comparisionImagePathForBlue));

    assertTrue("Images should be equal", imagesAreEqual(image2, image5));
    assertTrue("Images should be equal", imagesAreEqual(image3, image6));
    assertTrue("Images should be equal", imagesAreEqual(image4, image7));
  }

  /**
   * Test the image horizontal flip functionality when source image name is non-existent.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testSplitRGBSourceInLoadEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.splitRGBImage("testFile-notExist",
            "testFile-red",
            "testFile-green", "testFile-blue");

    List<int[][]> getRGBSaveRed = imageProcessing.saveImage("testFile-red");
    ImageUtil.saveImage(imageDestinationPathForRed, getRGBSaveRed.get(0), getRGBSaveRed.get(1),
            getRGBSaveRed.get(2));
    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForRed));

    List<int[][]> getRGBSaveGreen = imageProcessing.saveImage("testFile-green");
    ImageUtil.saveImage(imageDestinationPathForGreen, getRGBSaveGreen.get(0),
            getRGBSaveGreen.get(1), getRGBSaveGreen.get(2));
    BufferedImage image3 = ImageIO.read(new File(imageDestinationPathForGreen));

    List<int[][]> getRGBSaveBlue = imageProcessing.saveImage("testFile-blue");
    ImageUtil.saveImage(imageDestinationPathForBlue, getRGBSaveBlue.get(0), getRGBSaveBlue.get(1),
            getRGBSaveBlue.get(2));
    BufferedImage image4 = ImageIO.read(new File(imageDestinationPathForBlue));

    BufferedImage image5 = ImageIO.read(new File(comparisionImagePathForRed));
    BufferedImage image6 = ImageIO.read(new File(comparisionImagePathForGreen));
    BufferedImage image7 = ImageIO.read(new File(comparisionImagePathForBlue));

    assertTrue("Images should be equal", imagesAreEqual(image2, image5));
    assertTrue("Images should be equal", imagesAreEqual(image3, image6));
    assertTrue("Images should be equal", imagesAreEqual(image4, image7));
  }

  /**
   * Test the image horizontal flip functionality when source and dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testSplitRGBImageSourceEmptyAndDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.splitRGBImage("", "",
            "", "");

    List<int[][]> getRGBSaveRed = imageProcessing.saveImage("testFile-red");
    ImageUtil.saveImage(imageDestinationPathForRed, getRGBSaveRed.get(0), getRGBSaveRed.get(1),
            getRGBSaveRed.get(2));
    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForRed));

    List<int[][]> getRGBSaveGreen = imageProcessing.saveImage("testFile-green");
    ImageUtil.saveImage(imageDestinationPathForGreen, getRGBSaveGreen.get(0),
            getRGBSaveGreen.get(1), getRGBSaveGreen.get(2));
    BufferedImage image3 = ImageIO.read(new File(imageDestinationPathForGreen));

    List<int[][]> getRGBSaveBlue = imageProcessing.saveImage("testFile-blue");
    ImageUtil.saveImage(imageDestinationPathForBlue, getRGBSaveBlue.get(0), getRGBSaveBlue.get(1),
            getRGBSaveBlue.get(2));
    BufferedImage image4 = ImageIO.read(new File(imageDestinationPathForBlue));

    BufferedImage image5 = ImageIO.read(new File(comparisionImagePathForRed));
    BufferedImage image6 = ImageIO.read(new File(comparisionImagePathForGreen));
    BufferedImage image7 = ImageIO.read(new File(comparisionImagePathForBlue));

    assertTrue("Images should be equal", imagesAreEqual(image2, image5));
    assertTrue("Images should be equal", imagesAreEqual(image3, image6));
    assertTrue("Images should be equal", imagesAreEqual(image4, image7));
  }


  // ---------------------------------------------------------------------------------------------

  /**
   * Test to check if the RGB combine is working correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testCombineRGBImage() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.splitRGBImage("testFile", "testFile-red",
            "testFile-green", "testFile-blue");
    imageProcessing.combineRGBImage("testFile-combined",
            "testFile-red",
            "testFile-green", "testFile-blue");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-combined");
    ImageUtil.saveImage(imageDestinationPathForCombined, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForCombined));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForCombined));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test to check if the RGB combine is working correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testCombineRGBImageSourceEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.splitRGBImage("testFile", "testFile-red",
            "testFile-green", "testFile-blue");
    imageProcessing.combineRGBImage("testFile-combined", "",
            "", "");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-combined");
    ImageUtil.saveImage(imageDestinationPathForCombined, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForCombined));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForCombined));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test to check if the RGB combine is working correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testCombineRGBImageDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.splitRGBImage("testFile", "testFile-red",
            "testFile-green", "testFile-blue");
    imageProcessing.combineRGBImage("", "testFile-red",
            "testFile-green", "testFile-blue");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-combined");
    ImageUtil.saveImage(imageDestinationPathForCombined, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForCombined));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForCombined));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test to check if the RGB combine is working correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testCombineRGBImageSourceInLoadEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.splitRGBImage("testFile", "testFile-red",
            "testFile-green", "testFile-blue");
    imageProcessing.combineRGBImage("testFile-combined",
            "testFile-red-notExist", "testFile-green-notExist",
            "testFile-blue-notExist");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-combined");
    ImageUtil.saveImage(imageDestinationPathForCombined, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForCombined));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForCombined));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test to check if the RGB combine is working correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testCombineRGBImageSourceEmptyAndDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.splitRGBImage("testFile", "testFile-red",
            "testFile-green", "testFile-blue");
    imageProcessing.combineRGBImage("", "",
            "", "");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-combined");
    ImageUtil.saveImage(imageDestinationPathForCombined, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForCombined));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForCombined));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // ---------------------------------------------------------------------------------------------

  /**
   * Test to check if red-component is working as expected.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testRedComponentImage() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.redComponentImage("testFile",
            "testFile-redComponent");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-redComponent");
    ImageUtil.saveImage(imageDestinationPathForRed, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForRed));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForRed));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image red-component functionality when source image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testRedComponentImageSourceEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.sharpenImage("", "testFile-redComponent");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-redComponent");
    ImageUtil.saveImage(imageDestinationPathForRed, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForRed));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForRed));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image red-component functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testRedComponentImageDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.sharpenImage("testFile", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-redComponent");
    ImageUtil.saveImage(imageDestinationPathForRed, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForRed));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForRed));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image red-component functionality when source is non-existent.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testRedComponentImageSourceInLoadEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.sharpenImage("testFile-notExisting",
            "testFile-redComponent");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-redComponent");
    ImageUtil.saveImage(imageDestinationPathForRed, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForRed));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForRed));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image red-component functionality when source and dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testRedComponentImageSourceEmptyAndDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.sharpenImage("", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-redComponent");
    ImageUtil.saveImage(imageDestinationPathForRed, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForRed));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForRed));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // ---------------------------------------------------------------------------------------------

  /**
   * Test to check if green-component is working as expected.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testGreenComponentImage() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.greenComponentImage("testFile",
            "testFile-greenComponent");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-greenComponent");
    ImageUtil.saveImage(imageDestinationPathForGreen, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForGreen));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForGreen));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image green-component functionality when source image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testGreenComponentImageSourceEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.sharpenImage("", "testFile-greenComponent");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-greenComponent");
    ImageUtil.saveImage(imageDestinationPathForGreen, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForGreen));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForGreen));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image green-component functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testGreenComponentImageDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.sharpenImage("testFile", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-greenComponent");
    ImageUtil.saveImage(imageDestinationPathForGreen, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForGreen));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForGreen));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image green-component functionality when source image does not exist.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testGreenComponentImageSourceInLoadEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.sharpenImage("testFile-notExisting",
            "testFile-greenComponent");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-greenComponent");
    ImageUtil.saveImage(imageDestinationPathForGreen, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForGreen));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForGreen));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image green-component functionality when both source and destination is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testGreenComponentImageSourceEmptyAndDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.sharpenImage("", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-greenComponent");
    ImageUtil.saveImage(imageDestinationPathForGreen, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForGreen));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForGreen));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // ---------------------------------------------------------------------------------------------

  /**
   * Test to check if blue-component is working as expected.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testBlueComponentImage() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.blueComponentImage("testFile",
            "testFile-blueComponent");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-blueComponent");
    ImageUtil.saveImage(imageDestinationPathForBlue, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForBlue));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForBlue));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image blue-component functionality when source image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testBlueComponentImageSourceEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.sharpenImage("", "testFile-blueComponent");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-blueComponent");
    ImageUtil.saveImage(imageDestinationPathForBlue, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForBlue));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForBlue));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image blue-component functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testBlueComponentImageDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.sharpenImage("testFile", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-blueComponent");
    ImageUtil.saveImage(imageDestinationPathForBlue, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForBlue));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForBlue));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image blue-component functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testBlueComponentImageSourceInLoadEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.sharpenImage("testFile-notExisting",
            "testFile-blueComponent");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-blueComponent");
    ImageUtil.saveImage(imageDestinationPathForBlue, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForBlue));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForBlue));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image blue-component functionality when wrong image is taken by sharpening.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testBlueComponentImageSourceEmptyAndDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.sharpenImage("", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-blueComponent");
    ImageUtil.saveImage(imageDestinationPathForBlue, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForBlue));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForBlue));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // ---------------------------------------------------------------------------------------------

  /**
   * Test to check if image compression is working as expected.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testCompressImage20() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.compressImage(20, "testFile",
            "testFile-compress20");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-compress20");
    ImageUtil.saveImage(imageDestinationPathForCompress20, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForCompress20));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForCompress20));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test to check if image compression is working as expected.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testCompressImage50() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.compressImage(50, "testFile",
            "testFile-compress50");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-compress50");
    ImageUtil.saveImage(imageDestinationPathForCompress50, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForCompress50));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForCompress50));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test to check if image compression is working as expected.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testCompressImage90() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.compressImage(90, "testFile",
            "testFile-compress90");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-compress90");
    ImageUtil.saveImage(imageDestinationPathForCompress90, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForCompress90));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForCompress90));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test to check if image compression is working as expected.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testCompressImage0() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.compressImage(0, "testFile",
            "testFile-compress0");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-compress0");
    ImageUtil.saveImage(imageDestinationPathForCompress0, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForCompress0));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForCompress0));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test to check if image compression is working as expected.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCompressImageNeg() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.compressImage(-10, "testFile",
            "testFile-compress0");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-compress0");
    ImageUtil.saveImage(imageDestinationPathForCompress0, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForCompress0));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForCompress0));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test to check if image compression is working as expected.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCompressImageMoreThan100() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.compressImage(200, "testFile",
            "testFile-compress0");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-compress0");
    ImageUtil.saveImage(imageDestinationPathForCompress0, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForCompress0));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForCompress0));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image compression functionality when source image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testCompressImageSourceEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.compressImage(90, "",
            "testFile-compress90");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-compress90");
    ImageUtil.saveImage(imageDestinationPathForCompress90, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForCompress90));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForCompress90));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image compression functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testCompressImageDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.compressImage(90, "testFile", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-compress90");
    ImageUtil.saveImage(imageDestinationPathForCompress90, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForCompress90));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForCompress90));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image compression functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testCompressImageSourceInLoadEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.compressImage(90, "testFile-notExisting",
            "testFile-compress90");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-compress90");
    ImageUtil.saveImage(imageDestinationPathForCompress90, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForCompress90));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForCompress90));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the image compression functionality when wrong image is taken by compression.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testCompressImageSourceEmptyAndDestEmpty() throws IOException {
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.compressImage(90, "", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-compress90");
    ImageUtil.saveImage(imageDestinationPathForCompress90, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForCompress90));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForCompress90));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // --------------------------------------------------------------------------------------------

  /**
   * Test to check if color correction is working as expected.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testColorCorrectionImage() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathGalaxy);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePathGalaxy);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.colorCorrectionImage("testFile",
            "testFile-colorCorrection");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-colorCorrection");
    ImageUtil.saveImage(imageDestinationPathForColorCorrection, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForColorCorrection));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForColorCorrection));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the color correction functionality when source image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testColorCorrectionImageSourceEmpty() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathGalaxy);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePathGalaxy);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.colorCorrectionImage("",
            "testFile-colorCorrection");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-colorCorrection");
    ImageUtil.saveImage(imageDestinationPathForColorCorrection, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForColorCorrection));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForColorCorrection));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the color correction functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testColorCorrectionImageDestEmpty() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathGalaxy);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePathGalaxy);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.colorCorrectionImage("testFile", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-colorCorrection");
    ImageUtil.saveImage(imageDestinationPathForColorCorrection, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForColorCorrection));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForColorCorrection));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the color correction functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testColorCorrectionImageSourceInLoadEmpty() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathGalaxy);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePathGalaxy);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.colorCorrectionImage("testFile-notExisting",
            "testFile-greenComponent");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-colorCorrection");
    ImageUtil.saveImage(imageDestinationPathForColorCorrection, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForColorCorrection));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForColorCorrection));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the color correction functionality when wrong image is taken by color correction.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testColorCorrectionImageSourceEmptyAndDestEmpty() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathGalaxy);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePathGalaxy);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.colorCorrectionImage("", "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-colorCorrection");
    ImageUtil.saveImage(imageDestinationPathForColorCorrection, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForColorCorrection));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForColorCorrection));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // --------------------------------------------------------------------------------------------

  /**
   * Test to check if level adjustment is working as expected.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testLevelAdjustmentImage() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathGalaxy);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePathGalaxy);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.levelsAdjustmentImage(20, 100, 255, "testFile",
            "testFile-levelAdjustment");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-levelAdjustment");
    ImageUtil.saveImage(imageDestinationPathForLevelAdjustment, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));
    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForLevelAdjustment));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForLevelAdjustment));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the level adjustment functionality when source image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testLevelAdjustmentImageSourceEmpty() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathGalaxy);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePathGalaxy);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.levelsAdjustmentImage(20, 100, 255, "",
            "testFile-levelAdjustment");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-levelAdjustment");
    ImageUtil.saveImage(imageDestinationPathForLevelAdjustment, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForLevelAdjustment));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForLevelAdjustment));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the level adjustment functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IOException.class)
  public void testLevelAdjustmentImageDestEmpty() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathGalaxy);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePathGalaxy);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.levelsAdjustmentImage(20, 100, 255, "testFile",
            "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-levelAdjustment");
    ImageUtil.saveImage(imageDestinationPathForLevelAdjustment, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForLevelAdjustment));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForLevelAdjustment));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the level adjustment functionality when dest image name is empty.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testLevelAdjustmentImageSourceInLoadEmpty() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathGalaxy);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePathGalaxy);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.levelsAdjustmentImage(20, 100, 255, "testFile-notExisting",
            "testFile-levelAdjustment");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-levelAdjustment");
    ImageUtil.saveImage(imageDestinationPathForLevelAdjustment, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForLevelAdjustment));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForLevelAdjustment));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  /**
   * Test the level adjustment functionality when wrong image is taken by level adjustment.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = FileNotFoundException.class)
  public void testLevelAdjustmentImageSourceEmptyAndDestEmpty() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathGalaxy);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePathGalaxy);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.levelsAdjustmentImage(20, 100, 255, "",
            "");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-levelAdjustment");
    ImageUtil.saveImage(imageDestinationPathForLevelAdjustment, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForLevelAdjustment));

    BufferedImage image3 = ImageIO.read(new File(comparisionImagePathForLevelAdjustment));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // ---------------------------------------------------------------------------------------------

  /**
   * Test to check if level adjustment and color correction is working as expected.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testLevelAdjustmentAndColorCorrectedImage() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathGalaxy);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePathGalaxy);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.levelsAdjustmentImage(20, 100, 255, "testFile",
            "testFile-levelAdjustment");
    imageProcessing.colorCorrectionImage("testFile-levelAdjustment",
            "testFile-levelAdjustment-colorCorrection");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-levelAdjustment");
    ImageUtil.saveImage(imageDestinationPathForLevelAdjustmentColorCorrection, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 =
            ImageIO.read(new File(imageDestinationPathForLevelAdjustmentColorCorrection));

    BufferedImage image3 =
            ImageIO.read(new File(comparisionImagePathForLevelAdjustmentColorCorrection));

    assertTrue("Images should be equal", imagesAreEqual(image2, image3));
  }

  // ---------------------------------------------------------------------------------------------

  /**
   * Test to check if the split blur images are generating correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testSplitBlurImage50() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.blurImage("testFile", "testFile-splitBlur",
            50);
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-splitBlur");
    ImageUtil.saveImage(imageDestinationPathForSplitBlur50, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForSplitBlur50, comparisionImagePathForSplitBlur50));
  }

  /**
   * Test to check if the split blur images are generating correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testSplitBlurImage0() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.blurImage("testFile", "testFile-splitBlur",
            0);
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-splitBlur");
    ImageUtil.saveImage(imageDestinationPathForSplitBlur0, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(imageDestinationPathForSplitBlur0,
            comparisionImagePathForSplitBlur0));
  }

  /**
   * Test to check if the split blur images are generating correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testSplitBlurImage100() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.blurImage("testFile", "testFile-splitBlur",
            100);
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-splitBlur");
    ImageUtil.saveImage(imageDestinationPathForSplitBlur100, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForSplitBlur100, comparisionImagePathForSplitBlur100));
  }

  /**
   * Test to check if the split blur images are generating correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSplitBlurImageNeg50() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.blurImage("testFile", "testFile-splitBlur",
            -50);
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-splitBlur");
    ImageUtil.saveImage(imageDestinationPathForSplitBlur100, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForSplitBlur100, comparisionImagePathForSplitBlur100));
  }

  // --------------------------------------------------------------------------------------------

  /**
   * Test to check if the split sharpen images are generating correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testSplitSharpenImage() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.sharpenImage("testFile", "testFile-splitSharpen",
            50);
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-splitSharpen");
    ImageUtil.saveImage(imageDestinationPathForSplitSharpen, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForSplitSharpen, comparisionImagePathForSplitSharpen));
  }

  // --------------------------------------------------------------------------------------------

  /**
   * Test to check if the split grayscale images are generating correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testSplitGrayscaleImage() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.convertIntoGreyScaleImage("testFile",
            "testFile-splitGrayscale", 50);
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-splitGrayscale");
    ImageUtil.saveImage(imageDestinationPathFortSplitGrayscale, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathFortSplitGrayscale, comparisionImagePathForSplitGrayscale));
  }

  // --------------------------------------------------------------------------------------------

  /**
   * Test to check if the level adjusted images are generating correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testSplitLevelAdjustmentImage() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.levelsAdjustmentImage(20, 100, 255, "testFile",
            "testFile-splitLevelAdjust", 50);
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-splitLevelAdjust");
    ImageUtil.saveImage(imageDestinationPathForSplitLevelAdjustment, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForSplitLevelAdjustment,
            comparisionImagePathForSplitLevelAdjustment));
  }

  // --------------------------------------------------------------------------------------------

  /**
   * Test to check if the color corrected images are generating correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testSplitColorCorrectionImage() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.colorCorrectionImage("testFile",
            "testFile-splitColorCorrection",
            50);
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-splitColorCorrection");
    ImageUtil.saveImage(imageDestinationPathForSplitColorCorrection, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForSplitColorCorrection,
            comparisionImagePathForSplitColorCorrection));
  }

  // --------------------------------------------------------------------------------------------

  /**
   * Test to check if the sepia images are generating correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testSplitSepiaImage() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.convertIntoSepia("testFile",
            "testFile-splitSepia", 50);
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-splitSepia");
    ImageUtil.saveImage(imageDestinationPathForSplitSepia, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForSplitSepia, comparisionImagePathForSplitSepia));
  }

  // --------------------------------------------------------------------------------------------

  /**
   * Test to check if the value images are generating correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testSplitValueImage() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.valueComponentOfImage("testFile",
            "testFile-splitValue", 50);
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-splitValue");
    ImageUtil.saveImage(imageDestinationPathForSplitValue, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));


    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForSplitValue, comparisionImagePathForSplitValue));
  }

  // --------------------------------------------------------------------------------------------

  /**
   * Test to check if the luma images are generating correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testSplitLumaImage() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.lumaComponentOfImage("testFile",
            "testFile-splitLuma", 50);
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-splitLuma");
    ImageUtil.saveImage(imageDestinationPathForSplitLuma, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForSplitLuma, comparisionImagePathForSplitLuma));
  }

  // --------------------------------------------------------------------------------------------

  /**
   * Test to check if the intensity images are generating correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test
  public void testSplitIntensityImage() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.intensityComponentOfImage("testFile",
            "testFile-splitIntensity", 50);
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-splitIntensity");
    ImageUtil.saveImage(imageDestinationPathForSplitIntensity, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));


    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForSplitIntensity, comparisionImagePathForSplitIntensity));
  }

  /**
   * Test to check if the intensity images are generating correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSplitIntensityImageNegPercentage() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.intensityComponentOfImage("testFile",
            "testFile-splitIntensity", -50);
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-splitIntensity");
    ImageUtil.saveImage(imageDestinationPathForSplitIntensity, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));


    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForSplitIntensity, comparisionImagePathForSplitIntensity));
  }

  /**
   * Test to check if the intensity images are generating correctly.
   *
   * @throws IOException when unable to find the image to save.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSplitIntensityImagePercentageGreaterThan100() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.intensityComponentOfImage("testFile",
            "testFile-splitIntensity", 150);
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-splitIntensity");
    ImageUtil.saveImage(imageDestinationPathForSplitIntensity, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));


    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForSplitIntensity, comparisionImagePathForSplitIntensity));
  }

  // ----------------------------------------------------------------------------------------------

  @Test
  public void testDownSizeWidthAndHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePath);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.downScaleImage(300, 100,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForDownsizing));

    assertEquals(300, image2.getWidth());
    assertEquals(100, image2.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeNegativeWidthAndHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePath);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.downScaleImage(-300, 100,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForDownsizing));

    assertEquals(300, image2.getWidth());
    assertEquals(100, image2.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeWidthAndNegativeHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePath);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.downScaleImage(300, -100,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForDownsizing));

    assertEquals(300, image2.getWidth());
    assertEquals(100, image2.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeNegativeWidthAndNegativeHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePath);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.downScaleImage(-300, -100,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForDownsizing));

    assertEquals(300, image2.getWidth());
    assertEquals(100, image2.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeZeroWidthAndHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePath);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.downScaleImage(0, 100,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForDownsizing));

    assertEquals(300, image2.getWidth());
    assertEquals(100, image2.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeWidthAndZeroHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePath);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.downScaleImage(300, 0,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForDownsizing));

    assertEquals(300, image2.getWidth());
    assertEquals(100, image2.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeZeroWidthAndZeroHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePath);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.downScaleImage(0, 0,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForDownsizing));

    assertEquals(300, image2.getWidth());
    assertEquals(100, image2.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeGreaterWidthAndHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePath);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");

    // Width and height of original image in "imageSourcePath" is 500 and 200 respectively.
    imageProcessing.downScaleImage(600, 100,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForDownsizing));

    assertEquals(300, image2.getWidth());
    assertEquals(100, image2.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeWidthAndGreaterHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePath);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");

    // Width and height of original image in "imageSourcePath" is 500 and 200 respectively.
    imageProcessing.downScaleImage(300, 500,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForDownsizing));

    assertEquals(300, image2.getWidth());
    assertEquals(100, image2.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeGreaterWidthAndGreaterHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePath);
    List<int[][]> getRGB = ImageUtil.loadImage(imageSourcePath);
    imageProcessing.loadImage(getRGB, "testFile");

    // Width and height of original image in "imageSourcePath" is 500 and 200 respectively.
    imageProcessing.downScaleImage(800, 500,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    BufferedImage image2 = ImageIO.read(new File(imageDestinationPathForDownsizing));

    assertEquals(300, image2.getWidth());
    assertEquals(100, image2.getHeight());
  }

  @Test
  public void testDownSizeImageContent() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.downScaleImage(3, 3,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForDownsizing, comparisionImagePathForDownsizing));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeImageContentNegWidthAndHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.downScaleImage(-3, 3,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForDownsizing, comparisionImagePathForDownsizing));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeImageContentWidthAndNegHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.downScaleImage(3, -3,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForDownsizing, comparisionImagePathForDownsizing));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeImageContentNegWidthAndNegHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.downScaleImage(-3, -3,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForDownsizing, comparisionImagePathForDownsizing));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeImageContentZeroWidthAndHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.downScaleImage(0, 3,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForDownsizing, comparisionImagePathForDownsizing));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeImageContentWidthAndZeroHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.downScaleImage(3, 0,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForDownsizing, comparisionImagePathForDownsizing));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeImageContentZeroWidthAndZeroHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.downScaleImage(0, 0,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForDownsizing, comparisionImagePathForDownsizing));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeImageContentGreaterWidthAndHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.downScaleImage(5, 3,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForDownsizing, comparisionImagePathForDownsizing));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeImageContentWidthAndGreaterHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.downScaleImage(3, 5,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForDownsizing, comparisionImagePathForDownsizing));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeImageContentGreaterWidthAndGreaterHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.downScaleImage(5, 5,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForDownsizing, comparisionImagePathForDownsizing));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeImageContentZeroWidthAndGreaterHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.downScaleImage(0, 5,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForDownsizing, comparisionImagePathForDownsizing));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDownSizeImageContentGreaterWidthAndZeroHeight() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGB = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGB, "testFile");
    imageProcessing.downScaleImage(5, 0,"testFile",
            "testFile-downSized");
    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-downSized");
    ImageUtil.saveImage(imageDestinationPathForDownsizing, getRGBSave.get(0), getRGBSave.get(1),
            getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForDownsizing, comparisionImagePathForDownsizing));
  }

  //-----------------------------------------------------------------------------------------------

  @Test
  public void testMaskImageSepia() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGBimage = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGBimage, "testFile");

    List<int[][]> getRGBmask = ImageUtil.readPPM(imageSourcePathMask);
    imageProcessing.loadImage(getRGBmask, "testFileMask");

    imageProcessing.convertIntoSepia("testFile", "testFileMask",
            "testFile-sepiaMask");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-sepiaMask");
    ImageUtil.saveImage(imageDestinationPathForSepiaMask, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(imageDestinationPathForSepiaMask,
            comparisionImagePathForSepiaMask));
  }

  @Test
  public void testMaskImageBlur() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGBimage = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGBimage, "testFile");

    List<int[][]> getRGBmask = ImageUtil.readPPM(imageSourcePathMask);
    imageProcessing.loadImage(getRGBmask, "testFileMask");

    imageProcessing.blurImage("testFile", "testFileMask",
            "testFile-blurMask");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-blurMask");
    ImageUtil.saveImage(imageDestinationPathForBlurMask, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(imageDestinationPathForBlurMask,
            comparisionImagePathForBlurMask));
  }

  @Test
  public void testMaskImageSharpen() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGBimage = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGBimage, "testFile");

    List<int[][]> getRGBmask = ImageUtil.readPPM(imageSourcePathMask);
    imageProcessing.loadImage(getRGBmask, "testFileMask");

    imageProcessing.sharpenImage("testFile", "testFileMask",
            "testFile-sharpenMask");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-sharpenMask");
    ImageUtil.saveImage(imageDestinationPathForSharpenMask, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForSharpenMask, comparisionImagePathForSharpenMask));
  }

  @Test
  public void testMaskImageValue() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGBimage = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGBimage, "testFile");

    List<int[][]> getRGBmask = ImageUtil.readPPM(imageSourcePathMask);
    imageProcessing.loadImage(getRGBmask, "testFileMask");

    imageProcessing.valueComponentOfImage("testFile", "testFileMask",
            "testFile-valueMask");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-valueMask");
    ImageUtil.saveImage(imageDestinationPathForValueMask, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForValueMask, comparisionImagePathForValueMask));
  }

  @Test
  public void testMaskImageLuma() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGBimage = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGBimage, "testFile");

    List<int[][]> getRGBmask = ImageUtil.readPPM(imageSourcePathMask);
    imageProcessing.loadImage(getRGBmask, "testFileMask");

    imageProcessing.lumaComponentOfImage("testFile", "testFileMask",
            "testFile-lumaMask");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-lumaMask");
    ImageUtil.saveImage(imageDestinationPathForLumaMask, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForLumaMask, comparisionImagePathForLumaMask));
  }

  @Test
  public void testMaskImageIntensity() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGBimage = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGBimage, "testFile");

    List<int[][]> getRGBmask = ImageUtil.readPPM(imageSourcePathMask);
    imageProcessing.loadImage(getRGBmask, "testFileMask");

    imageProcessing.intensityComponentOfImage("testFile", "testFileMask",
            "testFile-intensityMask");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-intensityMask");
    ImageUtil.saveImage(imageDestinationPathForIntensityMask, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForIntensityMask, comparisionImagePathForIntensityMask));
  }

  @Test
  public void testMaskImageRedComponent() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGBimage = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGBimage, "testFile");

    List<int[][]> getRGBmask = ImageUtil.readPPM(imageSourcePathMask);
    imageProcessing.loadImage(getRGBmask, "testFileMask");

    imageProcessing.redComponentImage("testFile", "testFileMask",
            "testFile-redComponentMask");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-redComponentMask");
    ImageUtil.saveImage(imageDestinationPathForRedComponentMask, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForRedComponentMask, comparisionImagePathForRedComponentMask));
  }

  @Test
  public void testMaskImageGreenComponent() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGBimage = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGBimage, "testFile");

    List<int[][]> getRGBmask = ImageUtil.readPPM(imageSourcePathMask);
    imageProcessing.loadImage(getRGBmask, "testFileMask");

    imageProcessing.greenComponentImage("testFile", "testFileMask",
            "testFile-greenComponentMask");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-greenComponentMask");
    ImageUtil.saveImage(imageDestinationPathForGreenComponentMask, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForGreenComponentMask, comparisionImagePathForGreenComponentMask));
  }

  @Test
  public void testMaskImageBlueComponent() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGBimage = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGBimage, "testFile");

    List<int[][]> getRGBmask = ImageUtil.readPPM(imageSourcePathMask);
    imageProcessing.loadImage(getRGBmask, "testFileMask");

    imageProcessing.blueComponentImage("testFile", "testFileMask",
            "testFile-blueComponentMask");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-blueComponentMask");
    ImageUtil.saveImage(imageDestinationPathForBlueComponentMask, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForBlueComponentMask, comparisionImagePathForBlueComponentMask));
  }

  @Test(expected = FileNotFoundException.class)
  public void testMaskAbsentImageBlueComponent() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGBimage = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGBimage, "testFile");

    List<int[][]> getRGBmask = ImageUtil.readPPM(imageSourcePathMask);
    imageProcessing.loadImage(getRGBmask, "testFileMask");

    imageProcessing.blueComponentImage("testFile", "",
            "testFile-blueComponentMask");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-blueComponentMask");
    ImageUtil.saveImage(imageDestinationPathForBlueComponentMask, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForBlueComponentMask, comparisionImagePathForBlueComponentMask));
  }

  @Test(expected = FileNotFoundException.class)
  public void testMaskImageBlueComponentInputImageAbsent() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGBimage = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGBimage, "testFile");

    List<int[][]> getRGBmask = ImageUtil.readPPM(imageSourcePathMask);
    imageProcessing.loadImage(getRGBmask, "testFileMask");

    imageProcessing.blueComponentImage("", "testFileMask",
            "testFile-blueComponentMask");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-blueComponentMask");
    ImageUtil.saveImage(imageDestinationPathForBlueComponentMask, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForBlueComponentMask, comparisionImagePathForBlueComponentMask));
  }

  @Test(expected = FileNotFoundException.class)
  public void testMaskImageBlueComponentDestImageAbsent() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGBimage = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGBimage, "testFile");

    List<int[][]> getRGBmask = ImageUtil.readPPM(imageSourcePathMask);
    imageProcessing.loadImage(getRGBmask, "testFileMask");

    imageProcessing.blueComponentImage("testFile", "testFileMask",
            "");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-blueComponentMask");
    ImageUtil.saveImage(imageDestinationPathForBlueComponentMask, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForBlueComponentMask, comparisionImagePathForBlueComponentMask));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMaskGreaterImageBlueComponent() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGBimage = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGBimage, "testFile");

    //Loading a larger mask image than the original image.
    List<int[][]> getRGBmask = ImageUtil.readPPM(imageSourcePathMaskGreater);
    imageProcessing.loadImage(getRGBmask, "testFileMask");

    imageProcessing.blueComponentImage("testFile", "testFileMask",
            "testFile-blueComponentMask");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-blueComponentMask");
    ImageUtil.saveImage(imageDestinationPathForBlueComponentMask, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForBlueComponentMask, comparisionImagePathForBlueComponentMask));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMaskSmallerImageBlueComponent() throws IOException {
    imageProcessing = imageProcessingFactory.createImageManipulation(imageSourcePathPPMSample);
    List<int[][]> getRGBimage = ImageUtil.readPPM(imageSourcePathPPMSample);
    imageProcessing.loadImage(getRGBimage, "testFile");

    //loading a smaller mask image than the original image.
    List<int[][]> getRGBmask = ImageUtil.readPPM(imageSourcePathMaskSmaller);
    imageProcessing.loadImage(getRGBmask, "testFileMask");

    imageProcessing.blueComponentImage("testFile", "testFileMask",
            "testFile-blueComponentMask");

    List<int[][]> getRGBSave = imageProcessing.saveImage("testFile-blueComponentMask");
    ImageUtil.saveImage(imageDestinationPathForBlueComponentMask, getRGBSave.get(0),
            getRGBSave.get(1), getRGBSave.get(2));

    assertTrue("Images should be equal", comparePPMFiles(
            imageDestinationPathForBlueComponentMask, comparisionImagePathForBlueComponentMask));
  }
}