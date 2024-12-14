import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import controller.GeneralCommandCallbacksGUI;
import view.ExtendedImageView;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class that is used to test the GUI controller. This tests all the functionalities
 * offered by the GUI controller.
 */
public class ImageProcessingViewImplTest {
  private StringBuilder log;

  @Before
  public void setup() {
    // Initialize the log and mock controller
    log = new StringBuilder();
  }

  @Test
  public void testLoad() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            log.toString());
  }

  @Test
  public void testSave() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.saveImage();

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for saveImage load\n" +
                    "disableUndoCommand of MockGUI",
            log.toString());
  }

  @Test
  public void testFlipVertical() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.verticalFlipImage();

    String processedLog = log.toString().replaceAll("-\\d+", "");

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for flipImageVertically load, vertical-flip\n" +
                    "Parameters received for histogram vertical-flip\n" +
                    "enableUndoCommand of MockGUI\n" +
                    "setImage of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            processedLog);

  }

  @Test
  public void testFlipHorizontally() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.horizontalFlipImage();

    String processedLog = log.toString().replaceAll("-\\d+", "");

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for flipImageHorizontally load, horizontal-flip\n" +
                    "Parameters received for histogram horizontal-flip\n" +
                    "enableUndoCommand of MockGUI\n" +
                    "setImage of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            processedLog);

  }

  @Test
  public void testLumaComponent() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.lumaComponentImage();

    String processedLog = log.toString().replaceAll("-\\d+", "");

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for lumaComponent load, luma\n" +
                    "setImage of MockGUI\n" +
                    "Parameters received for histogram luma\n" +
                    "setHistogramImage of MockGUI\n" +
                    "enableUndoCommand of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            processedLog);
  }

  @Test
  public void testBlurImage() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.blurImage();

    String processedLog = log.toString().replaceAll("-\\d+", "");

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for blurImage load, blur\n" +
                    "setImage of MockGUI\n" +
                    "Parameters received for histogram blur\n" +
                    "setHistogramImage of MockGUI\n" +
                    "enableUndoCommand of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            processedLog);
  }

  @Test
  public void testSharpenImage() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.sharpenImage();

    String processedLog = log.toString().replaceAll("-\\d+", "");

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for sharpenImage load, sharpen\n" +
                    "setImage of MockGUI\n" +
                    "Parameters received for histogram sharpen\n" +
                    "setHistogramImage of MockGUI\n" +
                    "enableUndoCommand of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            processedLog);
  }

  @Test
  public void testSepiaImage() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.sepiaImage();

    String processedLog = log.toString().replaceAll("-\\d+", "");

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for sepia load, sepia\n" +
                    "Parameters received for histogram sepia\n" +
                    "setImage of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "enableUndoCommand of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            processedLog);
  }

  @Test
  public void testRedComponentImage() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.redComponentImage();

    String processedLog = log.toString().replaceAll("-\\d+", "");

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for redComponentImage load, red-component\n" +
                    "Parameters received for histogram red-component\n" +
                    "enableUndoCommand of MockGUI\n" +
                    "setImage of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            processedLog);

  }

  @Test
  public void testGreenComponentImage() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.greenComponentImage();

    String processedLog = log.toString().replaceAll("-\\d+", "");

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for greenComponentImage load, green-component\n" +
                    "Parameters received for histogram green-component\n" +
                    "enableUndoCommand of MockGUI\n" +
                    "setImage of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            processedLog);

  }

  @Test
  public void testBlueComponentImage() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.blueComponentImage();

    String processedLog = log.toString().replaceAll("-\\d+", "");

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for blueComponentImage load, blue-component\n" +
                    "Parameters received for histogram blue-component\n" +
                    "enableUndoCommand of MockGUI\n" +
                    "setImage of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            processedLog);
  }

  @Test
  public void testColorCorrectionImage() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.colorCorrectionImage();

    String processedLog = log.toString().replaceAll("-\\d+", "");

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for colorCorrectionImage load, color-correction\n" +
                    "setImage of MockGUI\n" +
                    "Parameters received for histogram color-correction\n" +
                    "setHistogramImage of MockGUI\n" +
                    "enableUndoCommand of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            processedLog);

  }

  @Test
  public void testLevelAdjustmentImage() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.levelAdjustmentImage(20, 100, 255);

    String processedLog = log.toString().replaceAll("-\\d+", "");

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for levelsAdjustmentImage 20.0, 100.0, 255.0, load, " +
                    "level-adjustment_20.0_100.0_255.0\n" +
                    "setImage of MockGUI\n" +
                    "Parameters received for histogram level-adjustment_20.0_100.0_255.0\n" +
                    "setHistogramImage of MockGUI\n" +
                    "enableUndoCommand of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            processedLog);

  }

  @Test
  public void testCompressImage() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.compressImage(50);

    String processedLog = log.toString().replaceAll("-\\d+", "");

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for compressImage load, compress_50.0, 50.0\n" +
                    "setImage of MockGUI\n" +
                    "Parameters received for histogram compress_50.0\n" +
                    "setHistogramImage of MockGUI\n" +
                    "enableUndoCommand of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            processedLog);
  }

  @Test
  public void testDownSizeImage() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.downScaleImage(200, 100);

    String processedLog = log.toString().replaceAll("-\\d+", "");

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for downScaleImage 200.0, 100.0, load, " +
                    "down-scale_200_100\n"
                    + "Parameters received for histogram down-scale_200_100\n" +
                    "enableUndoCommand of MockGUI\n" +
                    "setImage of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            processedLog);
  }

  @Test
  public void testLumaComponentSplitImage() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.lumaComponentImage(50);

    String processedLog = log.toString().replaceAll("-\\d+", "");

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for lumaComponent load, luma_50.0, 50.0\n" +
                    "Parameters received for histogram luma_50.0\n" +
                    "enableUndoCommand of MockGUI\n" +
                    "setImage of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            processedLog);

  }

  @Test
  public void testBlurSplitImage() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.blurImage(50);

    String processedLog = log.toString().replaceAll("-\\d+", "");

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for blurImage load, blur_50.0, 50.0\n" +
                    "Parameters received for histogram blur_50.0\n" +
                    "enableUndoCommand of MockGUI\n" +
                    "setImage of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            processedLog);

  }

  @Test
  public void testSharpenSplitImage() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.sharpenImage(50);

    String processedLog = log.toString().replaceAll("-\\d+", "");

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for sharpen load, sharpen_50.0, 50.0\n" +
                    "Parameters received for histogram sharpen_50.0\n" +
                    "enableUndoCommand of MockGUI\n" +
                    "setImage of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            processedLog);

  }

  @Test
  public void testSepiaSplitImage() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.sepiaImage(50);

    String processedLog = log.toString().replaceAll("-\\d+", "");

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for sepia load, sepia_50.0, 50.0\n" +
                    "Parameters received for histogram sepia_50.0\n" +
                    "enableUndoCommand of MockGUI\n" +
                    "setImage of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            processedLog);
  }

  @Test
  public void testColorCorrectSplitImage() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.colorCorrectionImage(50);

    String processedLog = log.toString().replaceAll("-\\d+", "");

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for colorCorrectionImage load," +
                    " color-correction_50.0, 50.0\n" +
                    "Parameters received for histogram color-correction_50.0\n" +
                    "enableUndoCommand of MockGUI\n" +
                    "setImage of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            processedLog);
  }

  @Test
  public void testLevelAdjustSplitImage() throws IOException {
    MockImageManipulationFactory mockFactory = new MockImageManipulationFactory(log);
    ExtendedImageView view = new MockGUIImageView(log);
    GeneralCommandCallbacksGUI gui = new GeneralCommandCallbacksGUI(mockFactory);
    gui.setView(view);
    gui.loadImage();
    gui.levelAdjustmentImage(20,100, 255, 50);

    String processedLog = log.toString().replaceAll("-\\d+", "");

    assertEquals("addFeatures of MockGUI\n" +
                    "Parameters received for load, load\n" +
                    "Parameters received for histogram load\n" +
                    "setImage of MockGUI\n" +
                    "disableUndoCommand of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "Parameters received for levelsAdjustmentImage load, " +
                    "level-adjustment_20.0_100.0_255.0_50.0, 50.0\n" +
                    "Parameters received for histogram level-adjustment_20.0_100.0_255.0_50.0\n" +
                    "enableUndoCommand of MockGUI\n" +
                    "setImage of MockGUI\n" +
                    "setHistogramImage of MockGUI\n" +
                    "resetFocus of MockGUI\n" +
                    "showImage of MockGUI\n" +
                    "resetFocus of MockGUI",
            processedLog);
  }
}
