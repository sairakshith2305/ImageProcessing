package controller;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import model.ImageManipulation;
import model.ImageManipulationFactory;
import model.image.Image;
import utility.ImageUtil;
import view.ExtendedImageView;
import view.Features;

/**
 * This is the class that listens to the GUI View. This class acts as a callback function for all
 * the action listeners present on the view. Any click or request done on the view by the user will
 * bring the control to this class. This class further calls the model operations and sends the
 * desired result to be rendered back to the view.
 */
public class GeneralCommandCallbacksGUI implements Features {

  private ExtendedImageView viewGUI;
  private ImageManipulationFactory imageManipulationFactory;
  private ImageManipulation imageManipulation;
  private Image image;
  private Stack<String> operationStack = new Stack<>();
  private List<int[][]> imageValues;
  private List<Map<Integer, Integer>> histValues;

  /**
   * This is a public constructor used to initialize the model for image operation.
   *
   * @param imageManipulationFactory This is the object of to initialize the model.
   */
  public GeneralCommandCallbacksGUI(ImageManipulationFactory imageManipulationFactory) {
    this.imageManipulationFactory = imageManipulationFactory;
  }

  /**
   * This is a public constructor used to initialize the view for the application.
   *
   * @param view The view object to setup the GUI.
   */
  public void setView(ExtendedImageView view) {
    this.viewGUI = view;
    view.addFeatures(this);
  }

  @Override
  public void loadImage() throws IOException {
    if (viewGUI.getImage() != null) {
      String previousOperation = operationStack.peek();
      if (!previousOperation.equalsIgnoreCase("save") && !previousOperation.
              equalsIgnoreCase("load")) {
        int choice = viewGUI.checkUnsavedChanges();
        if (choice == 1 || choice == -1) {
          return;
        }
      }
    }
    String filePath = viewGUI.getImagePath();
    if (filePath != null) {
      imageManipulation = imageManipulationFactory.createImageManipulation(filePath);
      if (ImageUtil.getFileExtension(filePath).equals("ppm")) {
        imageValues = ImageUtil.readPPM(filePath);
      } else {
        imageValues = ImageUtil.loadImage(filePath);
      }
      image = imageManipulation.loadImage(imageValues, "load");
      BufferedImage newImage = ImageUtil.returnBufferImage(image);
      histValues = imageManipulation.histogram("load");
      ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
              histValues.get(2));
      BufferedImage histImage = ImageUtil.returnBufferImage(hist);
      ArrayList<BufferedImage> imageList = new ArrayList<>();
      imageList.add(newImage);
      viewGUI.setImage(newImage);
      imageList.add(histImage);
      operationStack.push("load");
      viewGUI.disableUndoCommand();
      viewGUI.setHistogramImage(histImage);
      viewGUI.resetFocus();
      viewGUI.showImage(imageList);
      viewGUI.resetFocus();
    }
  }

  @Override
  public void saveImage() throws IOException {
    String filePath = viewGUI.getImagePathToSave();
    if (imageManipulation != null) {
      if (filePath != null) {
        String imageName = operationStack.peek();
        imageValues = imageManipulation.saveImage(imageName);
        ImageUtil.saveImage(filePath, imageValues.get(0), imageValues.get(1), imageValues.get(2));
        operationStack.push("save");
        operationStack.push(imageName);
        viewGUI.disableUndoCommand();
      }
    }
  }

  @Override
  public void blurImage() throws FileNotFoundException {

    String imageName = operationStack.peek();
    String destinationImageName = getImageTimeStamp();
    destinationImageName = "blur-" + destinationImageName;
    image = imageManipulation.blurImage(imageName, destinationImageName);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    viewGUI.setImage(newImage);
    histValues = imageManipulation.histogram(destinationImageName);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);
    viewGUI.setHistogramImage(histImage);
    ArrayList<BufferedImage> imageList = new ArrayList<>();
    imageList.add(newImage);
    imageList.add(histImage);
    operationStack.push(destinationImageName);
    viewGUI.enableUndoCommand();
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();
  }

  @Override
  public void blurImage(double value) throws FileNotFoundException {
    String imageName = operationStack.peek();
    String destinationImageName = getImageTimeStamp();
    destinationImageName = "blur_" + value + "-" + destinationImageName;
    image = imageManipulation.blurImage(imageName, destinationImageName, value);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    histValues = imageManipulation.histogram(destinationImageName);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);

    ArrayList<BufferedImage> imageList = new ArrayList<>();
    imageList.add(newImage);
    imageList.add(histImage);
    operationStack.push(destinationImageName);
    viewGUI.enableUndoCommand();
    viewGUI.setImage(newImage);
    viewGUI.setHistogramImage(histImage);
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();
  }

  @Override
  public void sepiaImage() throws FileNotFoundException {
    String imageName = operationStack.peek();
    String destinationImageName = getImageTimeStamp();
    destinationImageName = "sepia-" + destinationImageName;
    image = imageManipulation.convertIntoSepia(imageName, destinationImageName);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    histValues = imageManipulation.histogram(destinationImageName);
    viewGUI.setImage(newImage);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);
    viewGUI.setHistogramImage(histImage);
    ArrayList<BufferedImage> imageList = new ArrayList<>();
    imageList.add(newImage);
    imageList.add(histImage);
    operationStack.push(destinationImageName);
    viewGUI.enableUndoCommand();
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();
  }

  @Override
  public void sepiaImage(double value) throws FileNotFoundException {
    String imageName = operationStack.peek();
    String destinationImageName = getImageTimeStamp();
    destinationImageName = "sepia_" + value + "-" + destinationImageName;
    image = imageManipulation.convertIntoSepia(imageName, destinationImageName, value);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    histValues = imageManipulation.histogram(destinationImageName);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);

    ArrayList<BufferedImage> imageList = new ArrayList<>();
    imageList.add(newImage);
    imageList.add(histImage);
    operationStack.push(destinationImageName);
    viewGUI.enableUndoCommand();
    viewGUI.setImage(newImage);
    viewGUI.setHistogramImage(histImage);
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();
  }

  @Override
  public void sharpenImage() throws FileNotFoundException {
    String imageName = operationStack.peek();
    String destinationImageName = getImageTimeStamp();
    destinationImageName = "sharpen-" + destinationImageName;
    image = imageManipulation.sharpenImage(imageName, destinationImageName);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    viewGUI.setImage(newImage);
    histValues = imageManipulation.histogram(destinationImageName);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);
    viewGUI.setHistogramImage(histImage);
    ArrayList<BufferedImage> imageList = new ArrayList<>();
    imageList.add(newImage);
    imageList.add(histImage);
    operationStack.push(destinationImageName);
    viewGUI.enableUndoCommand();
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();
  }

  @Override
  public void sharpenImage(double value) throws FileNotFoundException {
    String imageName = operationStack.peek();
    String destinationImageName = getImageTimeStamp();
    destinationImageName = "sharpen_" + value + "-" + destinationImageName;
    image = imageManipulation.sharpenImage(imageName, destinationImageName, value);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    histValues = imageManipulation.histogram(destinationImageName);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);

    ArrayList<BufferedImage> imageList = new ArrayList<>();
    imageList.add(newImage);
    imageList.add(histImage);
    operationStack.push(destinationImageName);
    viewGUI.enableUndoCommand();
    viewGUI.setImage(newImage);
    viewGUI.setHistogramImage(histImage);
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();
  }

  @Override
  public void lumaComponentImage() throws FileNotFoundException {
    String imageName = operationStack.peek();
    String destinationImageName = getImageTimeStamp();
    destinationImageName = "luma-" + destinationImageName;
    image = imageManipulation.lumaComponentOfImage(imageName, destinationImageName);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    viewGUI.setImage(newImage);
    histValues = imageManipulation.histogram(destinationImageName);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);
    viewGUI.setHistogramImage(histImage);
    ArrayList<BufferedImage> imageList = new ArrayList<>();
    imageList.add(newImage);
    imageList.add(histImage);
    operationStack.push(destinationImageName);
    viewGUI.enableUndoCommand();
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();
  }

  @Override
  public void lumaComponentImage(double value) throws FileNotFoundException {
    String imageName = operationStack.peek();
    String destinationImageName = getImageTimeStamp();
    destinationImageName = "luma_" + value + "-" + destinationImageName;
    image = imageManipulation.lumaComponentOfImage(imageName, destinationImageName, value);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    histValues = imageManipulation.histogram(destinationImageName);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);

    ArrayList<BufferedImage> imageList = new ArrayList<>();
    imageList.add(newImage);
    imageList.add(histImage);
    operationStack.push(destinationImageName);
    viewGUI.enableUndoCommand();
    viewGUI.setImage(newImage);
    viewGUI.setHistogramImage(histImage);
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();
  }

  @Override
  public void colorCorrectionImage() throws FileNotFoundException {
    String imageName = operationStack.peek();
    String destinationImageName = getImageTimeStamp();
    destinationImageName = "color-correction-" + destinationImageName;
    image = imageManipulation.colorCorrectionImage(imageName, destinationImageName);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    viewGUI.setImage(newImage);
    histValues = imageManipulation.histogram(destinationImageName);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);
    viewGUI.setHistogramImage(histImage);
    ArrayList<BufferedImage> imageList = new ArrayList<>();
    imageList.add(newImage);
    imageList.add(histImage);
    operationStack.push(destinationImageName);
    viewGUI.enableUndoCommand();
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();
  }

  @Override
  public void colorCorrectionImage(double value) throws FileNotFoundException {
    String imageName = operationStack.peek();
    String destinationImageName = getImageTimeStamp();
    destinationImageName = "color-correction_" + value + "-" + destinationImageName;
    image = imageManipulation.colorCorrectionImage(imageName, destinationImageName, value);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    histValues = imageManipulation.histogram(destinationImageName);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);

    ArrayList<BufferedImage> imageList = new ArrayList<>();
    imageList.add(newImage);
    imageList.add(histImage);
    operationStack.push(destinationImageName);
    viewGUI.enableUndoCommand();
    viewGUI.setImage(newImage);
    viewGUI.setHistogramImage(histImage);
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();
  }

  @Override
  public void compressImage(double percentage) throws FileNotFoundException {
    String imageName = operationStack.peek();
    String destinationImageName = getImageTimeStamp();
    destinationImageName = "compress_" + percentage + "-" + destinationImageName;
    image = imageManipulation.compressImage(percentage, imageName, destinationImageName);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    viewGUI.setImage(newImage);
    histValues = imageManipulation.histogram(destinationImageName);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);
    viewGUI.setHistogramImage(histImage);
    ArrayList<BufferedImage> imageList = new ArrayList<>();
    imageList.add(newImage);
    imageList.add(histImage);
    operationStack.push(destinationImageName);
    viewGUI.enableUndoCommand();
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();
  }

  @Override
  public void levelAdjustmentImage(double b, double m, double w) throws FileNotFoundException {
    String imageName = operationStack.peek();
    String destinationImageName = getImageTimeStamp();
    destinationImageName = "level-adjustment_" + b + "_" + m + "_" + w + "-" + destinationImageName;
    image = imageManipulation.levelsAdjustmentImage(b, m, w, imageName, destinationImageName);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    viewGUI.setImage(newImage);
    histValues = imageManipulation.histogram(destinationImageName);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);
    ArrayList<BufferedImage> imageList = new ArrayList<>();
    viewGUI.setHistogramImage(histImage);
    imageList.add(newImage);
    imageList.add(histImage);
    operationStack.push(destinationImageName);
    viewGUI.enableUndoCommand();
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();
  }

  @Override
  public void levelAdjustmentImage(double b, double m, double w, double value)
          throws FileNotFoundException {
    String imageName = operationStack.peek();
    String destinationImageName = getImageTimeStamp();
    destinationImageName = "level-adjustment_" + b + "_" + m + "_" + w + "_" + value + "-" +
            destinationImageName;
    image = imageManipulation.levelsAdjustmentImage(b, m, w, imageName, destinationImageName,
            value);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    histValues = imageManipulation.histogram(destinationImageName);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);

    ArrayList<BufferedImage> imageList = new ArrayList<>();
    imageList.add(newImage);
    imageList.add(histImage);
    operationStack.push(destinationImageName);
    viewGUI.enableUndoCommand();
    viewGUI.setImage(newImage);
    viewGUI.setHistogramImage(histImage);
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();
  }

  @Override
  public void horizontalFlipImage() throws FileNotFoundException {
    String imageName = operationStack.peek();
    String destinationImageName = getImageTimeStamp();
    destinationImageName = "horizontal-flip-" + destinationImageName;
    image = imageManipulation.flipImageHorizontally(imageName, destinationImageName);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    histValues = imageManipulation.histogram(destinationImageName);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);

    ArrayList<BufferedImage> imageList = new ArrayList<>();
    imageList.add(newImage);
    imageList.add(histImage);
    operationStack.push(destinationImageName);
    viewGUI.enableUndoCommand();
    viewGUI.setImage(newImage);
    viewGUI.setHistogramImage(histImage);
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();
  }

  @Override
  public void verticalFlipImage() throws FileNotFoundException {
    String imageName = operationStack.peek();
    String destinationImageName = getImageTimeStamp();
    destinationImageName = "vertical-flip-" + destinationImageName;
    image = imageManipulation.flipImageVertically(imageName, destinationImageName);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    histValues = imageManipulation.histogram(destinationImageName);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);

    ArrayList<BufferedImage> imageList = new ArrayList<>();
    imageList.add(newImage);
    imageList.add(histImage);
    operationStack.push(destinationImageName);
    viewGUI.enableUndoCommand();
    viewGUI.setImage(newImage);
    viewGUI.setHistogramImage(histImage);
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();
  }

  @Override
  public void redComponentImage() throws FileNotFoundException {
    String imageName = operationStack.peek();
    String destinationImageName = getImageTimeStamp();
    destinationImageName = "red-component-" + destinationImageName;
    image = imageManipulation.redComponentImage(imageName, destinationImageName);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    histValues = imageManipulation.histogram(destinationImageName);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);

    ArrayList<BufferedImage> imageList = new ArrayList<>();
    imageList.add(newImage);
    imageList.add(histImage);
    operationStack.push(destinationImageName);
    viewGUI.enableUndoCommand();
    viewGUI.setImage(newImage);
    viewGUI.setHistogramImage(histImage);
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();
  }

  @Override
  public void blueComponentImage() throws FileNotFoundException {
    String imageName = operationStack.peek();
    String destinationImageName = getImageTimeStamp();
    destinationImageName = "blue-component-" + destinationImageName;
    image = imageManipulation.blueComponentImage(imageName, destinationImageName);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    histValues = imageManipulation.histogram(destinationImageName);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);

    ArrayList<BufferedImage> imageList = new ArrayList<>();
    imageList.add(newImage);
    imageList.add(histImage);
    operationStack.push(destinationImageName);
    viewGUI.enableUndoCommand();
    viewGUI.setImage(newImage);
    viewGUI.setHistogramImage(histImage);
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();
  }

  @Override
  public void greenComponentImage() throws FileNotFoundException {
    String imageName = operationStack.peek();
    String destinationImageName = getImageTimeStamp();
    destinationImageName = "green-component-" + destinationImageName;
    image = imageManipulation.greenComponentImage(imageName, destinationImageName);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    histValues = imageManipulation.histogram(destinationImageName);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);

    ArrayList<BufferedImage> imageList = new ArrayList<>();
    imageList.add(newImage);
    imageList.add(histImage);
    operationStack.push(destinationImageName);
    viewGUI.enableUndoCommand();
    viewGUI.setImage(newImage);
    viewGUI.setHistogramImage(histImage);
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();
  }

  @Override
  public void downScaleImage(int newSmallWidth, int newSmallHeight) throws FileNotFoundException {
    String imageName = operationStack.peek();
    String destinationImageName = getImageTimeStamp();
    destinationImageName = "down-scale_" + newSmallWidth + "_" + newSmallHeight + "-" +
            destinationImageName;
    image = imageManipulation.downScaleImage(newSmallWidth, newSmallHeight, imageName,
            destinationImageName);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    histValues = imageManipulation.histogram(destinationImageName);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);

    ArrayList<BufferedImage> imageList = new ArrayList<>();
    imageList.add(newImage);
    imageList.add(histImage);
    operationStack.push(destinationImageName);
    viewGUI.enableUndoCommand();
    viewGUI.setImage(newImage);
    viewGUI.setHistogramImage(histImage);
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();
  }

  @Override
  public void undoCommand() throws FileNotFoundException {
    String name1 = operationStack.pop();
    String name = operationStack.peek();
    image = imageManipulation.undoImage(name1);
    BufferedImage newImage = ImageUtil.returnBufferImage(image);
    histValues = imageManipulation.histogram(name);
    ArrayList<int[][]> hist = ImageUtil.plotHistogram(histValues.get(0), histValues.get(1),
            histValues.get(2));
    BufferedImage histImage = ImageUtil.returnBufferImage(hist);

    ArrayList<BufferedImage> imageList = new ArrayList<>();
    imageList.add(newImage);
    imageList.add(histImage);

    viewGUI.setImage(newImage);
    viewGUI.setHistogramImage(histImage);
    viewGUI.resetFocus();
    viewGUI.showImage(imageList);
    viewGUI.resetFocus();

    if (operationStack.peek().equalsIgnoreCase("load")) {
      viewGUI.disableUndoCommand();
    }
  }


  private String getImageTimeStamp() {
    return String.valueOf(new Date().getTime());
  }
}