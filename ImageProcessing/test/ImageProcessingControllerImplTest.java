import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;

import model.ImageManipulationFactory;
import view.ImageView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is a test class that is used to test the text based controller. This tests all the
 * functionalities offered by the text based controller.
 */
public class ImageProcessingControllerImplTest {
  private StringBuilder log;

  @Before
  public void setup() {
    // Initialize the log and mock controller
    log = new StringBuilder();
  }

  @Test
  public void testLoad() throws IOException {
    String input = "Load res/ScriptImage/nature.jpg image\nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    ImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image", log.toString());
  }

  @Test
  public void testSave() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "save res/ScriptImage/natureSave.jpg image\nexit\n";

    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    ImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for saveImage, image", log.toString());
  }

  @Test
  public void testFlipVertical() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "vertical-flip image image-verticalFlipped\nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n"
                    + "Parameters received for flipImageVertically image, image-verticalFlipped",
            log.toString());
  }

  @Test
  public void testFlipHorizontal() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "horizontal-flip image image-horizontalFlipped\nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for flipImageHorizontally image, image-horizontalFlipped",
            log.toString());
  }

  @Test
  public void testValueComponent() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "value-component image image-valueComponent \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for valueComponent image, image-valueComponent",
            log.toString());
  }

  @Test
  public void testIntensityComponent() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "intensity-component image image-intensityComponent \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for intensityComponent image, image-intensityComponent",
            log.toString());
  }

  @Test
  public void testLumaComponent() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "luma-component image image-lumaComponent \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for lumaComponent image, image-lumaComponent",
            log.toString());
  }

  @Test
  public void testBlurImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "blur image image-blurImage \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for blurImage image, image-blurImage",
            log.toString());
  }

  @Test
  public void testSharpenImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "sharpen image image-sharpenImage \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for sharpenImage image, image-sharpenImage",
            log.toString());
  }

  @Test
  public void testSepiaImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "sepia image image-sepia \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for sepia image, image-sepia",
            log.toString());
  }

  @Test
  public void testGrayscaleImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "grey image image-grayscale \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for grayscale image, image-grayscale",
            log.toString());
  }

  @Test
  public void testBrightenImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "brighten 50 image image-brighten \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for brighten 50, image, image-brighten",
            log.toString());
  }

  @Test
  public void testSplitRGBImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "rgb-split image image-red image-green image-blue \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
            "Parameters received for splitRGBImage image, image-red, image-green, " +
            "image-blue", log.toString());
  }

  @Test
  public void testCombineRGBImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "rgb-combine image image-red image-green image-blue \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
            "Parameters received for combineRGBImage image, image-red, image-green, " +
            "image-blue", log.toString());
  }

  @Test
  public void testRedComponentImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "red-component image image-red \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
            "Parameters received for redComponentImage image, image-red", log.toString());
  }

  @Test
  public void testGreenComponentImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "green-component image image-green \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
            "Parameters received for greenComponentImage image, image-green", log.toString());
  }

  @Test
  public void testBlueComponentImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "blue-component image image-blue \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
            "Parameters received for blueComponentImage image, image-blue", log.toString());
  }

  @Test
  public void testColorCorrectionImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "color-correct image image-colorCorrected \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for colorCorrectionImage image, image-colorCorrected",
            log.toString());
  }

  @Test
  public void testLevelAdjustmentImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "levels-adjust 20 100 255 image image-levelsAdjusted \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for levelsAdjustmentImage 20.0, 100.0, 255.0, image, " +
                    "image-levelsAdjusted",
            log.toString());
  }

  @Test
  public void testHistogramImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "histogram image image-histogram \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for histogram image",
            log.toString());
  }

  @Test
  public void testBlurSplitImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "blur image image-blurImage split 50 \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for blurImage image, image-blurImage, 50.0",
            log.toString());
  }

  @Test
  public void testSepiaSplitImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "sepia image image-sepia split 50 \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for sepia image, image-sepia, 50.0",
            log.toString());
  }

  @Test
  public void testSharpenSplitImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "sharpen image image-sharpen split 50 \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for sharpen image, image-sharpen, 50.0",
            log.toString());
  }

  @Test
  public void testGrayscaleSplitImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "grey image image-grayscale split 50 \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for grayscale image, image-grayscale, 50.0",
            log.toString());
  }

  @Test
  public void testColorCorrectSplitImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "color-correct image image-colorCorrect split 50 \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for colorCorrectionImage image, image-colorCorrect, 50.0",
            log.toString());
  }

  @Test
  public void testLevelAdjustSplitImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "levels-adjust 20 100 255 image image-levelsAdjust split 50 \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for levelsAdjustmentImage image, image-levelsAdjust, 50.0",
            log.toString());
  }

  @Test
  public void testCompressImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "compress 50 image image-compressImage\nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for compressImage image, image-compressImage, 50.0",
            log.toString());
  }

  @Test
  public void testIntensitySplitImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "intensity-component image image-intensityComponent split 50\nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
            "Parameters received for intensityComponent image, image-intensityComponent, " +
            "50.0", log.toString());
  }

  @Test
  public void testValueSplitImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "value-component image image-valueComponent split 50\nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for valueComponent image, image-valueComponent, 50.0",
            log.toString());
  }

  @Test
  public void testLumaSplitImage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "luma-component image image-lumaComponent split 50\nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image\n" +
                    "Parameters received for lumaComponent image, image-lumaComponent, 50.0",
            log.toString());
  }

  @Test
  public void testSepiaMaskImage() throws IOException {
    String loadImage = "Load res/ScriptImage/nature.jpg image\n";
    String loadMask = "Load res/ScriptImage/mask.jpg mask\n";
    String input = loadImage + loadMask + "sepia image mask image-sepiaMask \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image" +
                    "Parameters received for load, mask\n" +
                    "Parameters received for convertIntoSepiaWithMask image, mask, image-sepiaMask",
            log.toString());

  }

  @Test
  public void testBlurMaskImage() throws IOException {
    String loadImage = "Load res/ScriptImage/nature.jpg image\n";
    String loadMask = "Load res/ScriptImage/mask.jpg mask\n";
    String input = loadImage + loadMask + "blur image mask image-blurMask \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image" +
                    "Parameters received for load, mask\n" +
                    "Parameters received for blurImageWithMask image, mask, image-blurMask",
            log.toString());

  }

  @Test
  public void testSharpenMaskImage() throws IOException {
    String loadImage = "Load res/ScriptImage/nature.jpg image\n";
    String loadMask = "Load res/ScriptImage/mask.jpg mask\n";
    String input = loadImage + loadMask + "sharpen image mask image-sharpenMask \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image" +
                    "Parameters received for load, mask\n" +
                    "Parameters received for sharpenImageWithMask image, mask, image-sharpenMask",
            log.toString());
  }

  @Test
  public void testValueMaskImage() throws IOException {
    String loadImage = "Load res/ScriptImage/nature.jpg image\n";
    String loadMask = "Load res/ScriptImage/mask.jpg mask\n";
    String input = loadImage + loadMask + "value-component image mask image-valueMask \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image" +
                    "Parameters received for load, mask\n" +
                    "Parameters received for convertIntoValueWithMask image, mask, image-valueMask",
            log.toString());
  }

  @Test
  public void testIntensityMaskImage() throws IOException {
    String loadImage = "Load res/ScriptImage/nature.jpg image\n";
    String loadMask = "Load res/ScriptImage/mask.jpg mask\n";
    String input = loadImage + loadMask + "intensity-component image mask image-intensityMask " +
            "\nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image" +
                    "Parameters received for load, mask\n" +
                    "Parameters received for convertIntoIntensityWithMask image, mask, " +
                    "image-intensityMask",
            log.toString());
  }

  @Test
  public void testLumaMaskImage() throws IOException {
    String loadImage = "Load res/ScriptImage/nature.jpg image\n";
    String loadMask = "Load res/ScriptImage/mask.jpg mask\n";
    String input = loadImage + loadMask + "luma-component image mask image-lumaMask \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image" +
                    "Parameters received for load, mask\n" +
                    "Parameters received for convertIntoLumaWithMask image, mask, image-lumaMask",
            log.toString());
  }

  @Test
  public void testRedComponentMaskImage() throws IOException {
    String loadImage = "Load res/ScriptImage/nature.jpg image\n";
    String loadMask = "Load res/ScriptImage/mask.jpg mask\n";
    String input = loadImage + loadMask + "red-component image mask image-redMask \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image" +
                    "Parameters received for load, mask\n" +
                    "Parameters received for redComponentImageWithMask image, mask, image-redMask",
            log.toString());
  }

  @Test
  public void testGreenCompoentMaskImage() throws IOException {
    String loadImage = "Load res/ScriptImage/nature.jpg image\n";
    String loadMask = "Load res/ScriptImage/mask.jpg mask\n";
    String input = loadImage + loadMask + "green-component image mask image-greenMask \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image" +
                    "Parameters received for load, mask\n" +
                    "Parameters received for greenComponentImageWithMask image, mask, " +
                    "image-greenMask",
            log.toString());
  }

  @Test
  public void testBlueComponentMaskImage() throws IOException {
    String loadImage = "Load res/ScriptImage/nature.jpg image\n";
    String loadMask = "Load res/ScriptImage/mask.jpg mask\n";
    String input = loadImage + loadMask + "blue-component image mask image-blueMask \nexit\n";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    PrintStream out = System.out;

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    assertEquals("Parameters received for load, image" +
                    "Parameters received for load, mask\n" +
                    "Parameters received for blueComponentImageWithMask image, mask, " +
                    "image-blueMask",
            log.toString());
  }

  @Test
  public void testWelcomeMessage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "exit\n"; // Simulate user input, ending with an exit command
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(outputStream);

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    String capturedOutput = outputStream.toString();

    // Verify that the welcome and thank you messages are in the output
    assertTrue(capturedOutput.contains("Welcome to the Image Processing Program from " +
            "MockImageView!"));
  }

  @Test
  public void testThankYouMessage() throws IOException {
    String load = "Load res/ScriptImage/nature.jpg image\n";
    String input = load + "exit\n"; // Simulate user input, ending with an exit command
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(outputStream);

    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    MockImageView view = new MockImageView(out);
    ImageProcessingController controller = new ImageProcessingControllerImpl(
            mockFactory, view, in, out);

    controller.startCommandExecution();

    String capturedOutput = outputStream.toString();

    // Verify that the welcome and thank you messages are in the output
    assertTrue(capturedOutput.contains("Thank you for using the Image Processing Program from " +
            "MockImageView!"));
  }
}