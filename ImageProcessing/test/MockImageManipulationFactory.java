import model.ImageManipulation;
import model.ImageManipulationFactory;

/**
 * This is a mock factory class that creates an object for the MockImageManipulationClass. It
 * simulates the working of the factory class in the model. It returns an object of the
 * MockImageManipulation class.
 */
public class MockImageManipulationFactory implements ImageManipulationFactory {
  protected StringBuilder log = new StringBuilder();

  /**
   * Constructor to initialize the log.
   *
   * @param log to log the mock behaviour of each functionality.
   */
  public MockImageManipulationFactory(StringBuilder log) {
    this.log = log;
  }

  /**
   * Mock method to create an object of the MockImageManipulation class.
   *
   * @param filePath The file path of the source image.
   * @return a MockImageManipulation object.
   */
  @Override
  public ImageManipulation createImageManipulation(String filePath) {
    return new MockImageManipulation(log);
  }
}