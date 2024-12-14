package view;

/**
 * This class is listens to the controller class and renders the suitable
 * view for the Image processing application.
 */
public class ImageViewController implements ImageView {
  @Override
  public void printWelcomeMessageCLI() {
    System.out.println("*****************************************");
    System.out.println("Welcome to Image Processing Application");
    System.out.println("*****************************************");

    System.out.println("The Following Actions can be performed with the "
            + "respective formats of input: ");
    System.out.println("*****************************************");
    System.out.println("load image_Path image_name");
    System.out.println("save image_Path image_name");
    System.out.println("red-component image-name dest-image-name");
    System.out.println("green-component image-name dest-image-name");
    System.out.println("blue-component image-name dest-image-name");
    System.out.println("value-component image-name dest-image-name");
    System.out.println("luma-component image-name dest-image-name");
    System.out.println("intensity-component image-name dest-image-name");
    System.out.println("vertical-flip image-name dest-image-name");
    System.out.println("horizontal-flip image-name dest-image-name");
    System.out.println("brighten increment image-name dest-image-name");
    System.out.println("brighten decrement image-name dest-image-name");
    System.out.println("rgb-split image-name dest-image-name-red dest-image-name-"
            + "green dest-image-name-blue");
    System.out.println("rgb-combine image-name red-image green-image blue-image");
    System.out.println("blur image-name dest-image-name");
    System.out.println("sharpen image-name dest-image-name");
    System.out.println("sepia image-name dest-image-name");
    System.out.println("compress percentage image-name dest-image-name");
    System.out.println("histogram image-name dest-image-name");
    System.out.println("color-correct image-name dest-image-name");
    System.out.println("levels-adjust b m w image-name dest-image-name");
    System.out.println("blur image-name dest-image split p");
    System.out.println("levels-adjust b m w image-name dest-image-name split p");
    System.out.println("intensity-component image-name dest-image-name split p");
    System.out.println("value-component image-name dest-image-name split p");
    System.out.println("luma-component image-name dest-image-name split p");
    System.out.println("color-correct image-name dest-image-name split p");
    System.out.println("sharpen image-name dest-image split p");
    System.out.println("sepia image-name dest-image-name split p");
    System.out.println("blur image-name mask-image dest-image-name");
    System.out.println("sharpen image-name mask-image dest-image-name");
    System.out.println("value-component image-name mask-image dest-image-name");
    System.out.println("luma-component image-name mask-image dest-image-name");
    System.out.println("intensity-component image-name mask-image dest-image-name");
    System.out.println("sepia image-name mask-image dest-image-name");
    System.out.println("red-component image-name mask-image dest-image-name");
    System.out.println("green-component image-name mask-image dest-image-name");
    System.out.println("blue-component image-name mask-image dest-image-name");
    System.out.println("downsize small-image-width small-image-height image-name dest-image-name");
    System.out.println("run script-file-path");
    System.out.println("-file script-file-path");
    System.out.println("exit");
    System.out.println("*****************************************");
  }

  @Override
  public void thankYouMessageCLI() {
    System.out.println("***************************************");
    System.out.println("Thank you for using the Image Processing Application");
  }
}
