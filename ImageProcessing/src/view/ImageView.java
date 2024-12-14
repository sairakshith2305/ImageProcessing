package view;

/**
 * This is an interface to render the Image view. This takes input from the controller and
 * renders the view accordingly. This class supports all the functions related to the view of
 * the application.
 */
public interface ImageView {
  /**
   * This function prints the welcome message on the command line Interface.
   */
  void printWelcomeMessageCLI();

  /**
   * This function prints the Thankyou message on the command line Interface.
   */
  void thankYouMessageCLI();
}
