package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.AbstractButton;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;


import static javax.swing.JOptionPane.showConfirmDialog;

/**
 * This is the GUI class that shows the gui for the user. This class loads, saves and takes in all
 * the operations that the user can perform using the GUI. This class provides a view for all the
 * supported Image operations.
 */
public class ExtendedImageViewController extends JFrame implements ExtendedImageView {

  private JButton loadImageButton;
  private JButton saveImageButton;
  private JButton undoImageButton;
  private final JPanel radioButtonPanel;  // Panel for radio buttons
  private final ButtonGroup radioButtonGroup;
  private final JPanel imagePanel;
  private File selectedFile;
  private JPanel panel;
  private int imageWidth;
  private int imageHeight;
  private BufferedImage image;

  /**
   * This is a public constructor used to initialize the Graphical User Interface for this
   * Application. This constructor function helps initialize all the UI components and buttons
   * for the application.
   *
   * @param caption the title of the application.
   */
  public ExtendedImageViewController(String caption) {
    super(caption);

    setSize(800, 600);
    setLocation(200, 200);

    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setResizable(false);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    JPanel loadAndSaveButtonPanel = new JPanel();

    loadImageButton(loadAndSaveButtonPanel);

    saveImageButton(loadAndSaveButtonPanel);

    addUndoButton(loadAndSaveButtonPanel);

    JPanel headingLabel = new JPanel();
    headingLabel.setLayout(new GridLayout(1, 0, 1, 1));
    headingLabel.setFont(new Font("Arial", Font.BOLD, 15));
    headingLabel.setBorder(BorderFactory.createTitledBorder("Image I/O"));

    headingLabel.add(loadAndSaveButtonPanel);
    mainPanel.add(headingLabel);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int buttonWidth = 150;
    int padding = 20;
    int columns = (screenSize.width - 2 * padding) / buttonWidth;

    JPanel operationsHeading = new JPanel();
    operationsHeading.setLayout(new GridLayout(1, columns, 1, 1));
    operationsHeading.setFont(new Font("Arial", Font.BOLD, 15));
    operationsHeading.setBorder(BorderFactory.createTitledBorder("Image Operation"));

    radioButtonPanel = new JPanel(new FlowLayout());
    radioButtonGroup = new ButtonGroup();

    addRadioButton("Red Component", false);
    addRadioButton("Green Component", false);
    addRadioButton("Blue Component", false);
    addRadioButton("Horizontal Flip", false);
    addRadioButton("Vertical Flip", false);
    addRadioButton("Luma", false);
    addRadioButton("Sharpen", false);
    addRadioButton("Blur", false);
    addRadioButton("Sepia", false);
    addRadioButton("Compress", false);
    addRadioButton("Color Correct", false);
    addRadioButton("Level Adjust", false);
    addRadioButton("Downsize", false);
    addRadioButton("Split", false);
    operationsHeading.add(radioButtonPanel);

    mainPanel.add(operationsHeading);

    imagePanel = new JPanel();
    imagePanel.setLayout(new GridLayout(1, 2, 10, 10));
    createEmptyImage(imagePanel);
    mainPanel.add(imagePanel);
    this.add(mainPanel);
    this.setVisible(true);
  }


  private void loadImageButton(JPanel mainPanel) {
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    loadImageButton = new JButton("Load Image");
    buttonPanel.add(loadImageButton);
    loadImageButton.setActionCommand("load Image");
    buttonPanel.add(loadImageButton);
    mainPanel.add(buttonPanel);
  }

  private void saveImageButton(JPanel mainPanel) {
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    saveImageButton = new JButton("Save Image");
    buttonPanel.add(saveImageButton);
    saveImageButton.setEnabled(false);
    saveImageButton.setActionCommand("save Image");
    buttonPanel.add(saveImageButton);
    mainPanel.add(buttonPanel);
  }

  private void addUndoButton(JPanel mainPanel) {
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    undoImageButton = new JButton("Undo");
    buttonPanel.add(undoImageButton);
    undoImageButton.setEnabled(false);
    undoImageButton.setActionCommand("Undo");
    buttonPanel.add(undoImageButton);
    mainPanel.add(buttonPanel);
  }

  private void createEmptyImage(JPanel imagePanel) {
    JLabel[] imageLabel = new JLabel[1];
    JScrollPane[] imageScrollPane = new JScrollPane[1];
    imageLabel[0] = new JLabel();
    imageScrollPane[0] = new JScrollPane(imageLabel[0]);
    imageLabel[0].setText("Please load an image!");
    imageLabel[0].setHorizontalAlignment(SwingConstants.CENTER); // Center the text
    imageLabel[0].setFont(new Font("Arial", Font.PLAIN, 24)); // Increase the font size
    imageScrollPane[0].setPreferredSize(new Dimension(100, 600));
    imagePanel.add(imageScrollPane[0]);
  }

  @Override
  public String getImagePath() {
    JFileChooser fileChooser = new JFileChooser(".");
    fileChooser.setDialogTitle("Choose an Image File");
    fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
      @Override
      public boolean accept(File file) {
        if (file.isDirectory()) {
          return true;
        }
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") ||
                fileName.endsWith(".png") || fileName.endsWith(".ppm");
      }

      @Override
      public String getDescription() {
        return "JPG/JPEG/PNG/PPM Format";
      }
    });
    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      selectedFile = fileChooser.getSelectedFile();
      return selectedFile.getAbsolutePath();
    }
    return null;
  }

  @Override
  public String getImagePathToSave() {
    JFileChooser fileChooser = new JFileChooser(".");
    fileChooser.setDialogTitle("Save an Image");
    int result = fileChooser.showSaveDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      selectedFile = fileChooser.getSelectedFile();
      String savePath = selectedFile.getAbsolutePath();
      return savePath;
    }
    return null;
  }

  @Override
  public void setImage(BufferedImage image) {
    this.image = image;
    imageWidth = image.getWidth();
    imageHeight = image.getHeight();
  }

  @Override
  public void setHistogramImage(BufferedImage image) {
    BufferedImage imageHistogram = image;
  }

  @Override
  public BufferedImage getImage() {
    return this.image;
  }


  private void addRadioButton(String label, boolean selected) {
    JRadioButton radioButton = new JRadioButton(label);
    radioButton.setActionCommand(label);
    radioButton.setSelected(selected);
    radioButton.setEnabled(false);
    radioButtonGroup.add(radioButton);
    radioButtonPanel.add(radioButton);
    radioButtonPanel.revalidate();
  }


  private String[] showTextDialogBoxThreeParams(String parA, String parB, String parC,
                                                String title) {

    JTextField bField = new JTextField(5);
    JTextField mField = new JTextField(5);
    JTextField wField = new JTextField(5);

    panel = new JPanel();
    panel.setLayout(new GridLayout(3, 2, 10, 10));
    panel.add(new JLabel("Enter " + parA.toUpperCase() + ":"));
    panel.add(bField);
    panel.add(new JLabel("Enter " + parB.toUpperCase() + ":"));
    panel.add(mField);
    panel.add(new JLabel("Enter " + parC.toUpperCase() + ":"));
    panel.add(wField);

    int result = showConfirmDialog(this, panel,
            title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
      String b = bField.getText();
      String m = mField.getText();
      String w = wField.getText();
      return new String[]{b, m, w};
    }
    return null;
  }

  private String[] showTextDialogTwoParams(String parA, String parB, String title) {
    JTextField smallWidth = new JTextField(5);
    JTextField smallHeight = new JTextField(5);

    panel = new JPanel();
    panel.setLayout(new GridLayout(3, 2, 10, 10));
    panel.add(new JLabel("Enter " + parA + ":"));
    panel.add(smallWidth);
    panel.add(new JLabel("Enter " + parB + ":"));
    panel.add(smallHeight);

    int result = showConfirmDialog(this, panel,
            title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
      String newWidth = smallWidth.getText();
      String newHeight = smallHeight.getText();
      return new String[]{newWidth, newHeight};
    }
    return null;
  }


  private String showTextDialogOneParam(String parA, String title) {
    JTextField field = new JTextField(5);

    panel = new JPanel();
    panel.setLayout(new GridLayout(3, 2, 10, 10));
    panel.add(new JLabel("Enter " + parA + ":"));
    panel.add(field);

    int result = showConfirmDialog(this, panel,
            title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
      String returnVal = field.getText();
      return returnVal;
    }
    return null;
  }


  @Override
  public void showImage(ArrayList<BufferedImage> image) {
    imagePanel.removeAll();
    JLabel[] imageLabel = new JLabel[image.size()];
    JScrollPane[] imageScrollPane = new JScrollPane[image.size()];
    for (int i = 0; i < image.size() - 1; i++) {
      imageLabel[i] = new JLabel(new ImageIcon(image.get(i)));
      imageScrollPane[i] = new JScrollPane(imageLabel[i]);
      imageScrollPane[i].setPreferredSize(new Dimension(100, 600));
      imagePanel.add(imageScrollPane[i]);
    }

    for (int i = image.size() - 1; i < image.size(); i++) {
      ImageIcon originalIcon = new ImageIcon(image.get(i));
      Image originalImage = originalIcon.getImage();
      Image scaledImage = originalImage.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
      imageLabel[i] = new JLabel(new ImageIcon(scaledImage));
      imageScrollPane[i] = new JScrollPane(imageLabel[i]);
      imageScrollPane[i].setPreferredSize(new Dimension(100, 600));
      imagePanel.add(imageScrollPane[i]);
    }
    imagePanel.setBackground(Color.LIGHT_GRAY);
    imagePanel.revalidate();
    imagePanel.repaint();
  }

  @Override
  public void showErrorMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Input Error",
            JOptionPane.ERROR_MESSAGE);
  }

  private String[] showSplitDialogBox(String[] operations, String input, String title) {
    JComboBox<String> comboBox = new JComboBox<>();
    for (int i = 0; i < operations.length; i++) {
      comboBox.addItem(operations[i]);
    }

    JTextField textField = new JTextField(10);

    panel = new JPanel();
    panel.setLayout(new GridLayout(2, 2, 10, 10));
    panel.add(new JLabel("Select an option:"));
    panel.add(comboBox);
    panel.add(new JLabel("Enter the " + input + ":"));
    panel.add(textField);

    while (true) {
      int result = showConfirmDialog(this, panel, title,
              JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

      if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
        return null;
      }

      String selectedOption = (String) comboBox.getSelectedItem();
      String enteredValue = textField.getText();
      return new String[]{selectedOption, enteredValue};
    }
  }

  @Override
  public void addFeatures(Features features) {
    loadImageButton.addActionListener(e -> {
      try {
        features.loadImage();
        if (getImage() != null) {
          saveImageButton.setEnabled(true);
          Enumeration<AbstractButton> elements = radioButtonGroup.getElements();
          while (elements.hasMoreElements()) {
            AbstractButton radioButton = elements.nextElement();
            radioButton.setEnabled(true);
          }
        }
      } catch (IOException ex) {
        showErrorMessage("Unable to load the image");
      }
    });
    saveImageButton.addActionListener(e -> {
      try {
        features.saveImage();
      } catch (IOException ex) {
        showErrorMessage("Unable to Save the Image.");
      }
    });

    undoImageButton.addActionListener(e -> {
      try {
        features.undoCommand();
      } catch (FileNotFoundException ex) {
        showErrorMessage("Unable to Undo Command");
      }
    });

    Enumeration<AbstractButton> elements = radioButtonGroup.getElements();
    while (elements.hasMoreElements()) {
      AbstractButton radioButton = elements.nextElement();
      switch (radioButton.getActionCommand()) {
        case "Red Component":
          redComponentImage(radioButton, features);
          break;
        case "Green Component":
          greenComponentImage(radioButton, features);
          break;
        case "Blue Component":
          blueComponentImage(radioButton, features);
          break;
        case "Horizontal Flip":
          horizontalFlip(radioButton, features);
          break;
        case "Vertical Flip":
          verticalFlip(radioButton, features);
          break;
        case "Luma":
          lumaComponent(radioButton, features);
          break;
        case "Sharpen":
          sharpenImage(radioButton, features);
          break;
        case "Blur":
          blurImage(radioButton, features);
          break;
        case "Sepia":
          sepiaImage(radioButton, features);
          break;
        case "Compress":
          compressImage(radioButton, features);
          break;
        case "Color Correct":
          colorCorrectImage(radioButton, features);
          break;
        case "Level Adjust":
          levelAdjustment(radioButton, features);
          break;
        case "Downsize":
          downSize(radioButton, features);
          break;
        case "Split":
          splitImage(radioButton, features);
          break;
        default:
          showErrorMessage("Invalid operation");
      }
    }
  }


  private void redComponentImage(AbstractButton radioButton, Features features) {
    radioButton.addActionListener(evt -> {
      try {
        features.redComponentImage();
      } catch (FileNotFoundException e) {
        showErrorMessage("Unable to process the image");
      }
    });
  }

  private void greenComponentImage(AbstractButton radioButton, Features features) {
    radioButton.addActionListener(evt -> {
      try {
        features.greenComponentImage();
      } catch (FileNotFoundException e) {
        showErrorMessage("Unable to process the image");
      }
    });
  }

  private void blueComponentImage(AbstractButton radioButton, Features features) {
    radioButton.addActionListener(evt -> {
      try {
        features.blueComponentImage();
      } catch (FileNotFoundException e) {
        showErrorMessage("Unable to process the image");
      }
    });
  }

  private void horizontalFlip(AbstractButton radioButton, Features features) {
    radioButton.addActionListener(evt -> {
      try {
        features.horizontalFlipImage();
      } catch (FileNotFoundException e) {
        showErrorMessage("Unable to process the image");
      }
    });
  }

  private void verticalFlip(AbstractButton radioButton, Features features) {
    radioButton.addActionListener(evt -> {
      try {
        features.verticalFlipImage();
      } catch (FileNotFoundException e) {
        showErrorMessage("Unable to process the image");
      }
    });
  }

  private void lumaComponent(AbstractButton radioButton, Features features) {
    radioButton.addActionListener(evt -> {
      try {
        features.lumaComponentImage();
      } catch (FileNotFoundException e) {
        showErrorMessage("Unable to process the image");
      }
    });
  }

  private void lumaComponent(Features features, double value) {
    try {
      features.lumaComponentImage(value);
    } catch (FileNotFoundException e) {
      showErrorMessage("Unable to process the image");
    }
  }

  private void sharpenImage(AbstractButton radioButton, Features features) {
    radioButton.addActionListener(evt -> {
      try {
        features.sharpenImage();
      } catch (FileNotFoundException e) {
        showErrorMessage("Unable to process the image");
      }
    });
  }

  private void sharpenImage(Features features, double value) {
    try {
      features.sharpenImage(value);
    } catch (FileNotFoundException e) {
      showErrorMessage("Unable to process the image");
    }
  }

  private void blurImage(AbstractButton radioButton, Features features) {
    radioButton.addActionListener(evt -> {
      try {
        features.blurImage();
      } catch (FileNotFoundException e) {
        showErrorMessage("Unable to process the image");
      }
    });
  }

  private void blurImage(Features features, double value) {
    try {
      features.blurImage(value);
    } catch (FileNotFoundException e) {
      showErrorMessage("Unable to process the image");
    }
  }

  private void sepiaImage(AbstractButton radioButton, Features features) {
    radioButton.addActionListener(evt -> {
      try {
        features.sepiaImage();
      } catch (FileNotFoundException e) {
        showErrorMessage("Unable to process the image");
      }
    });
  }

  private void sepiaImage(Features features, double value) {
    try {
      features.sepiaImage(value);
    } catch (FileNotFoundException e) {
      showErrorMessage("Unable to process the image");
    }
  }

  private void compressImage(AbstractButton radioButton, Features features) {
    radioButton.addActionListener(evt -> {
      String percentage = showTextDialogOneParam("compression percentage", "Compression");
      if (percentage != null) {
        try {
          double percent = Double.parseDouble(percentage);
          if (percent >= 0 && percent <= 100) {
            features.compressImage(percent);
          } else {
            showErrorMessage("Please ensure compression percentage is between 0 and 100.");
          }
        } catch (Exception e) {
          showErrorMessage("Please enter valid integer values.");
        }
      }
    });
  }

  private void colorCorrectImage(AbstractButton radioButton, Features features) {
    radioButton.addActionListener(evt -> {
      try {
        features.colorCorrectionImage();
      } catch (FileNotFoundException e) {
        showErrorMessage("Unable to process the image");
      }
    });
  }

  private void colorCorrectImage(Features features, double value) {
    try {
      features.colorCorrectionImage(value);
    } catch (FileNotFoundException e) {
      showErrorMessage("Unable to process the image");
    }
  }

  private void levelAdjustment(AbstractButton radioButton, Features features) {
    radioButton.addActionListener(evt -> {
      String[] levels = showTextDialogBoxThreeParams("b", "m", "w",
              "Level Adjust");
      if (levels != null) {  // Check if valid values were returned
        try {
          double b = Double.parseDouble(levels[0]);
          double m = Double.parseDouble(levels[1]);
          double w = Double.parseDouble(levels[2]);
          if (b >= 0 && b <= 255 && m >= 0 && m <= 255 && w >= 0 && w <= 255 && b < m && m < w) {
            features.levelAdjustmentImage(b, m, w);
          } else {
            showErrorMessage("Please ensure b < m < w and all values are in the range 0-255.");
          }
        } catch (Exception e) {
          showErrorMessage("Please enter valid Integer values.");
        }
      }
    });
  }

  private void levelAdjustment(Features features, double value) {
    String[] levels = showTextDialogBoxThreeParams("b", "m", "w",
            "Level Adjust");
    if (levels != null) {  // Check if valid values were returned
      try {
        double b = Double.parseDouble(levels[0]);
        double m = Double.parseDouble(levels[1]);
        double w = Double.parseDouble(levels[2]);
        if (b >= 0 && b <= 255 && m >= 0 && m <= 255 && w >= 0 && w <= 255
                && b < m && m < w) {
          features.levelAdjustmentImage(b, m, w, value);
        } else {
          showErrorMessage("Please ensure b < m < w and all values are in the "
                  + "range 0-255.");
        }
      } catch (Exception e) {
        showErrorMessage("Please enter valid integer values.");
      }
    }
  }

  private void downSize(AbstractButton radioButton, Features features) {
    radioButton.addActionListener(evt -> {
      String[] levels = showTextDialogTwoParams("width", "Height", "Downsize");
      if (levels != null) {  // Check if valid values were returned
        try {
          int newWidth = Integer.parseInt(levels[0]);
          int newHeight = Integer.parseInt(levels[1]);
          if (newWidth > 0 && newWidth < imageWidth && newHeight > 0 && newHeight < imageHeight) {
            features.downScaleImage(newWidth, newHeight);
          } else {
            showErrorMessage("Please ensure the width and height are greater than 0 and less than" +
                    " the image width and height");
          }
        } catch (Exception e) {
          showErrorMessage("Please enter valid Integer values.");
        }
      }
    });
  }

  private void splitImage(AbstractButton radioButton, Features features) {
    radioButton.addActionListener(evt -> {
      StringBuilder sb = new StringBuilder();
      sb.append("Blur").append(" ").append("Sharpen").append(" ").append("Sepia").append(" ")
              .append("Luma").append(" ").append("Color-Correct").append(" ")
              .append("Level-Adjust");
      String resultString = sb.toString();
      String[] resultArray = resultString.split(" ");
      String[] splitOptions = showSplitDialogBox(resultArray, "percentage",
              "Split Operations");
      if (splitOptions != null) {
        try {
          String selectedOption = splitOptions[0];
          double value = Double.parseDouble(splitOptions[1]);
          if (value >= 0 && value <= 100) {
            switch (selectedOption) {
              case "Blur":
                blurImage(features, value);
                break;
              case "Sharpen":
                sharpenImage(features, value);
                break;
              case "Sepia":
                sepiaImage(features, value);
                break;
              case "Luma":
                lumaComponent(features, value);
                break;
              case "Color-Correct":
                colorCorrectImage(features, value);
                break;
              case "Level-Adjust":
                levelAdjustment(features, value);
                break;
              default:
                throw new IllegalArgumentException("Unknown option selected: "
                        + selectedOption);
            }
          } else {
            showErrorMessage("Please enter a value between 0 and 100.");
          }
        } catch (Exception e) {
          showErrorMessage("Please enter a valid integer value for the parameter.");
        }
      }
    });
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public int checkUnsavedChanges() {
    int choice = JOptionPane.showConfirmDialog(this,
            "You have unsaved changes. Do you want to continue loading an Image?",
            "Unsaved Changes", JOptionPane.YES_NO_OPTION);
    return choice;
  }

  @Override
  public void enableUndoCommand() {
    undoImageButton.setEnabled(true);
  }

  @Override
  public void disableUndoCommand() {
    undoImageButton.setEnabled(false);
  }

}
