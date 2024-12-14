package utility;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import model.image.Image;

/**
 * This class contains utility methods to read a PPM/JPG/JPEG/PNG image from file and
 * return its contents to the Image class. This class can also be used have other methods which
 * help in Image operations.
 */
public class ImageUtil {

  /**
   * This function loads the file into the Input Directory.
   *
   * @param fileSourcePath Path of the file to be loaded.
   * @param filename       The name of the file to be addressed in the program
   * @throws FileNotFoundException if file not found in file source.
   */
  public static void loadFileFromSource(String fileSourcePath, String filename)
          throws FileNotFoundException {
    try {
      String currentDirectory = Paths.get("").toAbsolutePath().toString();
      File imageDirectory = new File(currentDirectory, "Images");
      if (imageDirectory.mkdir()) {
        System.out.println("Directory created!");
      } else {
        System.out.println("Directory already exists!");
      }
      Path imageSourcePath = Paths.get(fileSourcePath);
      Path imageSourceAbsolutePath = imageSourcePath.toAbsolutePath();
      Path currentDirectoryImageSaved = Path.of(imageDirectory + "/" + filename);
      Files.copy(imageSourceAbsolutePath, currentDirectoryImageSaved,
              StandardCopyOption.REPLACE_EXISTING);
    } catch (Exception e) {
      throw new FileNotFoundException("This file does not exist");
    }
  }

  /**
   * This function is used to save an image.
   *
   * @param filePath The file path where the image has to be saved.
   * @param red      The red component of the image.
   * @param green    The green component of the image.
   * @param blue     The blue component of an image.
   * @throws IOException throws exception when unable to read/write a file.
   */
  public static void saveImage(String filePath, int[][] red, int[][] green, int[][] blue)
          throws IOException {
    try {
      BufferedImage combine = new BufferedImage(red.length, red[0].length,
              BufferedImage.TYPE_INT_RGB);
      for (int x = 0; x < red.length; x++) {
        for (int y = 0; y < red[0].length; y++) {
          int combineRGB = (red[x][y] << 16) | (green[x][y] << 8) | (blue[x][y]);

          combine.setRGB(x, y, combineRGB);
        }
      }

      String extension = ImageUtil.getFileExtension(filePath);
      if (ImageUtil.getFileExtension(filePath).equalsIgnoreCase("ppm")) {
        ImageUtil.writePPM(filePath, red, green, blue);
      } else {
        File outputFile = new File(filePath);
        ImageIO.write(combine, extension, outputFile);
      }

    } catch (Exception e) {
      throw new IOException("Error saving the file");
    }
  }

  /**
   * This function is used to get the absolute path given a path of an Image.
   *
   * @param path The file path for which absolute path is required.
   * @return The absolute path for the given file path.
   */
  public static String getAbsolutePath(String path) {
    File file = new File(path);
    return file.getAbsolutePath();
  }

  /**
   * This function is used to get the extension of an Image. Eg:.JPG,.JPEG,.PNG,.PPM,etc.
   *
   * @param filePath The file path of the image
   * @return the extension of the image in the given file path.
   */
  public static String getFileExtension(String filePath) {
    // Find the last index of the dot (.)
    int lastIndexOfDot = filePath.lastIndexOf('.');

    // Check if the dot exists and it's not the first character
    if (lastIndexOfDot != -1 && lastIndexOfDot < filePath.length() - 1) {
      return filePath.substring(lastIndexOfDot + 1);
    }

    // If there is no extension, return an empty string
    return "";
  }


  /**
   * This function is used to read a .PPM file.
   *
   * @param filename The filePath of the .PPm file Image.
   * @return The list of all the red, green and blue component of the given ppm image.
   * @throws FileNotFoundException in case of a file not found at the given destination.
   */
  public static ArrayList<int[][]> readPPM(String filename) throws FileNotFoundException {
    ArrayList<int[][]> data = new ArrayList<>();
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File not found: " + filename);
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    int[][] red = new int[width][height];
    int[][] green = new int[width][height];
    int[][] blue = new int[width][height];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        red[j][i] = r;
        green[j][i] = g;
        blue[j][i] = b;

      }
    }
    data.add(red);
    data.add(green);
    data.add(blue);
    return data;
  }

  /**
   * This function is used to load an Image other than the .PPM file.
   *
   * @param filePath The filePath of the image to be loaded.
   * @return the list of all the RGB components of an image.
   * @throws IOException when unable to read or write the file in the file path.
   */
  public static ArrayList<int[][]> loadImage(String filePath) throws IOException {
    BufferedImage image;
    ArrayList<int[][]> data = new ArrayList<>();

    image = ImageIO.read(new File(filePath));
    if (image != null) {
      int width = image.getWidth();
      int height = image.getHeight();

      int[][] red = new int[width][height];
      int[][] green = new int[width][height];
      int[][] blue = new int[width][height];

      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          int rgb = image.getRGB(i, j);
          int r = (rgb >> 16) & 0xff;
          int g = (rgb >> 8) & 0xff;
          int b = rgb & 0xff;

          red[i][j] = r;
          green[i][j] = g;
          blue[i][j] = b;
        }
      }
      data.add(red);
      data.add(green);
      data.add(blue);
    }
    return data;
  }

  /**
   * This function is used to write a PPM file.
   *
   * @param filepath The path where the .ppm file has to be created.
   * @param red      The red component of the image.
   * @param green    The green component of an image.
   * @param blue     The blue component of an image.
   * @throws IOException when unable to write to the file path.
   */
  public static void writePPM(String filepath, int[][] red, int[][] green, int[][] blue)
          throws IOException {
    // Define the file name for the PPM file
    Path fileName = Path.of(filepath);

    int height = red[0].length;
    int width = red.length;

    // Create a StringBuilder to hold the PPM data
    StringBuilder ppmContent = new StringBuilder();

    // PPM header part
    ppmContent.append("P3" + System.lineSeparator()); // Starting of PPM file
    ppmContent.append(width).append(" ").append(height).append(System.lineSeparator());
    ppmContent.append("255" + System.lineSeparator()); // Maximum color value

    // PPM body part
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {

        int r = red[j][i];
        int g = green[j][i];
        int b = blue[j][i];

        // Append RGB values to the PPM content
        ppmContent.append(r).append(" ").append(g).append(" ").append(b).append(" ");
      }
      ppmContent.append(System.lineSeparator());
    }
    try {
      // Write the PPM content to the file
      Files.writeString(fileName, ppmContent.toString());
    } catch (IOException e) {
      throw new IOException("Write PPM failed");
    }
  }

  /**
   * This function is used to plot a Histogram.
   *
   * @param red   The intensity values of red component for each pixel.
   * @param green The intensity value for green component for each pixel.
   * @param blue  The intensity values for blue component of each pixel.
   * @return A list 2D array which holds the pixel values of the histogram.
   */
  public static ArrayList<int[][]> plotHistogram(Map<Integer, Integer> red, Map<Integer,
          Integer> green, Map<Integer, Integer> blue) {

    int maxValueRed = Collections.max(red.values());
    int maxValueGreen = Collections.max(green.values());
    int maxValueBlue = Collections.max(blue.values());
    int maxValue = Math.max(maxValueRed, Math.max(maxValueGreen, maxValueBlue));


    int width = 256;
    int height = 256;

    BufferedImage histogramImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    Graphics2D g1 = histogramImage.createGraphics();

    g1.setColor(Color.WHITE);
    g1.fillRect(0, 0, width, height);
    int gridSpacing = 25;

    g1.setColor(Color.LIGHT_GRAY);
    for (int x = gridSpacing; x < width; x += gridSpacing) {
      g1.drawLine(x, 0, x, height);
    }
    for (int y = gridSpacing; y < height; y += gridSpacing) {
      g1.drawLine(0, y, width, y);
    }

    g1.setColor(Color.RED);
    for (int i = 1; i < 256; i++) {
      int x1 = i - 1;
      int x2 = i;
      int y1 = height - (int) (((double) red.getOrDefault(x1, 0) / maxValue) * height);
      int y2 = height - (int) (((double) red.getOrDefault(x2, 0) / maxValue) * height);
      g1.drawLine(x1, y1, x2, y2);
    }

    g1.setColor(Color.GREEN);
    for (int i = 1; i < 256; i++) {
      int x1 = i - 1;
      int x2 = i;
      int y1 = height - (int) (((double) green.getOrDefault(x1, 0) / maxValue) * height);
      int y2 = height - (int) (((double) green.getOrDefault(x2, 0) / maxValue) * height);
      g1.drawLine(x1, y1, x2, y2);
    }

    g1.setColor(Color.BLUE);
    for (int i = 1; i < 256; i++) {
      int x1 = i - 1;
      int x2 = i;
      int y1 = height - (int) (((double) blue.getOrDefault(x1, 0) / maxValue) * height);
      int y2 = height - (int) (((double) blue.getOrDefault(x2, 0) / maxValue) * height);
      g1.drawLine(x1, y1, x2, y2);
    }

    g1.dispose();

    ArrayList<int[][]> histogram = new ArrayList<>();

    int[][] red1 = new int[width][height];
    int[][] green1 = new int[width][height];
    int[][] blue1 = new int[width][height];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int rgb = histogramImage.getRGB(i, j);
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = rgb & 0xff;

        red1[i][j] = r;
        green1[i][j] = g;
        blue1[i][j] = b;
      }
    }
    histogram.add(red1);
    histogram.add(green1);
    histogram.add(blue1);
    return histogram;
  }

  /**
   * This function is used to return a BufferImage.
   *
   * @param imageValues The pixel values of an Image.
   * @return The buffered Image.
   */
  public static BufferedImage returnBufferImage(ArrayList<int[][]> imageValues) {
    int[][] red = imageValues.get(0);
    int[][] green = imageValues.get(1);
    int[][] blue = imageValues.get(2);
    BufferedImage combine = new BufferedImage(red.length, red[0].length,
            BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < red.length; x++) {
      for (int y = 0; y < red[0].length; y++) {
        int combineRGB = (red[x][y] << 16) | (green[x][y] << 8) | (blue[x][y]);

        combine.setRGB(x, y, combineRGB);
      }
    }
    return combine;
  }

  /**
   * This function is used to take the image input and retrun a Buffered Image.
   *
   * @param image The image for which buffered Image is requried.
   * @return The buffered Image.
   */
  public static BufferedImage returnBufferImage(Image image) {
    int[][] red = image.getRed();
    int[][] green = image.getGreen();
    int[][] blue = image.getBlue();
    BufferedImage combine = new BufferedImage(red.length, red[0].length,
            BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < image.getImageWidth(); x++) {
      for (int y = 0; y < image.getImageHeight(); y++) {
        int combineRGB = (red[x][y] << 16) | (green[x][y] << 8) | (blue[x][y]);

        combine.setRGB(x, y, combineRGB);
      }
    }
    return combine;
  }

}


