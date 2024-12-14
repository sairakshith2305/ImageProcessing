package model.image;

/**
 * This function is an Image implementation function that is used to get and set the red, green
 * and blue components of an Image. This class also gets the height and width of the image.
 */
public class ImageImpl implements Image {
  private int[][] red;
  private int[][] green;
  private int[][] blue;

  @Override
  public int[][] getRed() {
    return this.red;
  }

  @Override
  public int[][] getGreen() {
    return this.green;
  }

  @Override
  public int[][] getBlue() {
    return this.blue;
  }

  @Override
  public int getImageWidth() {
    return this.red.length;
  }

  @Override
  public int getImageHeight() {
    return this.red[0].length;
  }

  @Override
  public void setRed(int[][] red) {
    this.red = new int[red.length][red[0].length];
    for (int i = 0; i < red.length; i++) {
      for (int j = 0; j < red[0].length; j++) {
        this.red[i][j] = red[i][j];
      }
    }
  }

  @Override
  public void setGreen(int[][] green) {
    this.green = new int[green.length][green[0].length];
    for (int i = 0; i < green.length; i++) {
      for (int j = 0; j < green[0].length; j++) {
        this.green[i][j] = green[i][j];
      }
    }
  }

  @Override
  public void setBlue(int[][] blue) {
    this.blue = new int[blue.length][blue[0].length];
    for (int i = 0; i < blue.length; i++) {
      for (int j = 0; j < blue[0].length; j++) {
        this.blue[i][j] = blue[i][j];
      }
    }
  }
}
