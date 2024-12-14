package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.command.BlueComponent;
import controller.command.BlurImage;
import controller.command.BrightenImage;
import controller.command.ColorCorrection;
import controller.command.Compress;
import controller.command.DownSize;
import controller.command.GreenComponent;
import controller.command.GreyComponent;
import controller.command.Histogram;
import controller.command.HorizontalFlip;
import controller.command.IntensityComponent;
import controller.command.LevelAdjustment;
import controller.command.LoadImage;
import controller.command.LumaComponent;
import controller.command.RGBCombine;
import controller.command.RGBSplit;
import controller.command.RedComponent;
import controller.command.SaveImage;
import controller.command.SepiaImage;
import controller.command.SharpenImage;
import controller.command.ValueComponent;
import controller.command.VerticalFlip;
import model.ImageManipulation;
import model.ImageManipulationFactory;
import utility.ImageUtil;
import view.ImageView;

/**
 * This is the controller class which takes input from the user.
 * This class acts as a bridge between the Model and the view.
 */
public class ImageProcessingControllerImpl implements ImageProcessingController {

  private ImageManipulationFactory imageProcessingFactory;
  private ImageManipulation imageProcessing;
  private ImageManipulationFactory imageManipulationFactory;
  private ImageView view;
  private String filePath;

  Map<String, Function<String[], ImageManipulationsCommand>> knownCommand;

  InputStream in;
  PrintStream out;

  /**
   * This is a public constructor used to initialize the model and view of the image.
   *
   * @param imageManipulationFactory The object of the model class which
   *                                 handles all functionalities.
   * @param view                     The object of the view class which handles
   *                                 all view functionalities.
   */
  public ImageProcessingControllerImpl(ImageManipulationFactory imageManipulationFactory,
                                       ImageView view, InputStream in, PrintStream out) {
    this.imageManipulationFactory = imageManipulationFactory;
    this.view = view;
    this.in = in;
    this.out = out;
  }

  private StringBuilder readFromFile(String fileName) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(fileName));
    } catch (FileNotFoundException e) {
      out.println("File " + fileName + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();

    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.isBlank()) {
        continue;
      }
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    return builder;
  }

  @Override
  public void runScriptFile(String filePath) throws IOException {
    String file = ImageUtil.getAbsolutePath(filePath);
    StringBuilder fileCommands = readFromFile(file);
    Scanner sc = new Scanner(fileCommands.toString());
    while (sc.hasNextLine()) {
      String command = sc.nextLine();
      if (command.length() == 0) {
        continue;
      }

      String[] commandParts = command.split(" ");
      this.executeCommand(commandParts);
    }
  }

  @Override
  public void executeCommand(String[] command) {
    try {
      knownCommand = new HashMap<>();
      knownCommand.put("horizontal-flip", s -> new HorizontalFlip(s[1], s[2]));
      knownCommand.put("vertical-flip", s -> new VerticalFlip(s[1], s[2]));
      knownCommand.put("brighten", s -> new BrightenImage(Integer.parseInt(s[1]), s[2], s[3]));
      knownCommand.put("blur", s -> new BlurImage(s));
      knownCommand.put("sharpen", s -> new SharpenImage(s));
      knownCommand.put("sepia", s -> new SepiaImage(s));
      knownCommand.put("rgb-split", s -> new RGBSplit(s[1], s[2], s[3], s[4]));
      knownCommand.put("rgb-combine", s -> new RGBCombine(s[1], s[2], s[3], s[4]));
      knownCommand.put("value-component", s -> new ValueComponent(s));
      knownCommand.put("green-component", s -> new GreenComponent(s));
      knownCommand.put("blue-component", s -> new BlueComponent(s));
      knownCommand.put("red-component", s -> new RedComponent(s));
      knownCommand.put("color-correct", s -> new ColorCorrection(s));
      knownCommand.put("intensity-component", s -> new IntensityComponent(s));
      knownCommand.put("luma-component", s -> new LumaComponent(s));
      knownCommand.put("histogram", s -> new Histogram(s[1], s[2]));
      knownCommand.put("levels-adjust", s -> new LevelAdjustment(s));
      knownCommand.put("grey", s -> new GreyComponent(s));
      knownCommand.put("compress", s -> new Compress(Double.parseDouble(s[1]), s[2], s[3]));
      knownCommand.put("downsize", s -> new DownSize(Double.parseDouble(s[1]),
              Double.parseDouble(s[2]),
              s[3], s[4]));
      knownCommand.put("save", s -> new SaveImage(s[1], s[2]));
      knownCommand.put("load", s -> {
        filePath = s[1];
        return new LoadImage(s[1], s[2]);
      });
      command[0] = command[0].toLowerCase();

      ImageManipulationsCommand cmd;


      Function<String[], ImageManipulationsCommand> knownCommandFunction =
              knownCommand.getOrDefault(command[0], null);

      if (knownCommandFunction != null) {
        cmd = knownCommandFunction.apply(command);
        if (imageProcessingFactory == null) {
          imageProcessingFactory = imageManipulationFactory;
          imageProcessing = imageProcessingFactory.createImageManipulation(filePath);
        }

        if (command[0].equalsIgnoreCase("exit")) {
          view.thankYouMessageCLI();
          System.exit(0);
        }
        try {
          cmd.returnCommand(imageProcessing);
        } catch (IOException e) {
          if (e.getMessage().matches("Index \\d+ out of bounds for length \\d+")) {
            out.println("Error : Please check the command format: " + command[0]);
          } else {
            out.println(e.getMessage());
          }
        }
      } else if (command[0].equalsIgnoreCase("exit")) {
        view.thankYouMessageCLI();
        System.exit(0);
      } else {
        out.println("Unknown command: " + command[0]);
      }
    } catch (Exception e) {
      if (e.getMessage().matches("Index \\d+ out of bounds for length \\d+")) {
        out.println("Error : Please check the command format: " + command[0]);
      } else {
        out.println(e.getMessage());
      }
    }
  }

  @Override
  public void startCommandExecution() throws IOException {
    view.printWelcomeMessageCLI();
    imageProcessingFactory = null;
    Scanner scanner = new Scanner(this.in);
    out.println("Please enter the command");
    String command = scanner.nextLine();
    if (command.equalsIgnoreCase("exit")) {
      view.thankYouMessageCLI();
      return;
    }
    while (!(command.equalsIgnoreCase("exit"))) {
      String[] commandParts = command.split(" ");
      if (commandParts[0].equalsIgnoreCase("run")) {
        runScriptFile(commandParts[1]);
      } else {
        executeCommand(commandParts);
      }
      out.println("Please enter the next command");
      command = scanner.nextLine();
    }
    scanner.close();
    view.thankYouMessageCLI();
  }
}
