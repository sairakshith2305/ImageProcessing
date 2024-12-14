import java.io.IOException;

import controller.GeneralCommandCallbacksGUI;
import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageManipulationFactory;
import model.ImageManipulationFactoryImpl;
import view.ExtendedImageView;
import view.ExtendedImageViewController;
import view.ImageView;
import view.ImageViewController;

/**
 * This is the main class of the program. The control of the Image processing application
 * starts from here.
 */
public class Main {
  /**
   * This is a static main method that initializes the program by giving controller the control
   * to the Model as well as the View of the application.
   *
   * @param args This is the array of inputs given for the main program to execute.
   * @throws IOException Throws a file read/write exception as there involves file reading and
   *                     writing.
   */
  public static void main(String[] args) throws IOException {


    ImageView view = new ImageViewController();
    ImageManipulationFactory image = new ImageManipulationFactoryImpl();
    ImageProcessingController imageController = new ImageProcessingControllerImpl(image, view,
            System.in, System.out);

    if (args.length == 0) {
      ExtendedImageView viewGUI = new ExtendedImageViewController("Image Processing Application");
      new GeneralCommandCallbacksGUI(image).setView(viewGUI);
    } else if (args.length == 1) {
      if (args[0].equals("-text")) {
        imageController.startCommandExecution();
      } else {
        System.out.println("Please check the command: " + args[0]);
      }
    } else if (args.length == 2) {
      if (args[0].equalsIgnoreCase("-file")) {
        if (args[1].isEmpty()) {
          System.out.println("Please enter the correct file name ");
        } else {
          imageController.runScriptFile(args[1]);
          System.out.println("Script executed successfully");
        }
      } else {
        System.out.println("Wrong Command: " + args[0]);
        view.thankYouMessageCLI();
      }
    } else {
      System.out.println("Please enter the correct command");
    }
  }
}

