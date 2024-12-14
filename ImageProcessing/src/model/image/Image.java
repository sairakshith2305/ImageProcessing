package model.image;

/**
 * This is an Image class. This class is the crux of the Program holding all the red, green
 * and blue component of the image loaded and generated. There are getter and setter methods in
 * this class which can help access or update the values of an Image.
 */
public interface Image {

  /**
   * This is to get the red component of the image.
   *
   * @return This will return a 2-Dimensional Array with red component of the Image.
   */
  int[][] getRed();

  /**
   * This is to get the Green component of the color image.
   *
   * @return his will return a 2-Dimensional Array with green component of the Image.
   */
  int[][] getGreen();

  /**
   * This is to get the Blue component of the color image.
   *
   * @return This will return a 2-Dimensional Array with blue component of the Image.
   */
  int[][] getBlue();

  /**
   * This function is used to get the image width.
   *
   * @return This will return the integer width of the Image.
   */
  int getImageWidth();

  /**
   * This function is used to get the image height.
   *
   * @return This will return an integer Height of the Image.
   */
  int getImageHeight();

  /**
   * This is to set the red Component of an Image.
   *
   * @param red this is 2-Dimensional array to be set as the red component.
   */
  void setRed(int[][] red);

  /**
   * This is to set the green component of the image.
   *
   * @param green this is the 2-Dimensional array to be set as the green component.
   */
  void setGreen(int[][] green);

  /**
   * This is to set the blue component of the image.
   *
   * @param blue this is the 2-Dimensional array to be set as the blue component.
   */
  void setBlue(int[][] blue);

}
