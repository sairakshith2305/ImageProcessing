import java.io.PrintStream;

import view.ImageView;

/**
 * This class implementation is used to simulate the working of the image view functionalities
 * which contains welcome and Thank you messages. It prints the output directly to the
 * terminal with the help of PrintStream.
 */
public class MockImageView implements ImageView {
  private final PrintStream out;

  /**
   * Constructor to initialize the output PrintStream.
   *
   * @param out the output PrintStream to which the welcome and thankyou messages are written to.
   */
  public MockImageView(PrintStream out) {
    this.out = out;
  }

  /**
   * Mock method to print the welcome message.
   */
  @Override
  public void printWelcomeMessageCLI() {
    out.println("Welcome to the Image Processing Program from MockImageView!");
  }

  /**
   * Mock method to print the thank you message.
   */
  @Override
  public void thankYouMessageCLI() {
    out.println("Thank you for using the Image Processing Program from MockImageView!");
  }
}