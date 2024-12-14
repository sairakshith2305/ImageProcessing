GRIME: Graphical Image Manipulation and Enhancement 

In Assignment 6, we have implemented some additional functionalities like - 
1. Partial Image manipulation with masks.
2. Down scaling an Image.
3. Graphical User Interface for the Application.
4. Supported the -text command.
5. Incorporated the changes asked for assignment 5.

Changes/Modifications done to our code :

We modified all the methods of the ImageManipulation interface to return an Image which was earlier returning a void type. This change was done because the new GUI controller expects the processed image from the model in order to send it to the view. This change does not affect any of the existing functionality present for text based interaction. 

Changes for Partial Image Manipulation:

In order to incorporate the mask image for the partial Image feature we had to make small modifications to the existing text based controller. We added a new check for the command that supports the partial Image feature. Based on the number parameters we receive and validate if it's a masking operation and we direct the user in the respective flow.

On the model side we added a used function overloading for each function. So for example if we are taking the partial Image for blur operation we first blur the whole image and then call the MaskImageManipulation class to mask the image with the desired mask image.

Changes for DownScaling image:

Here we added this functionality to both GUI as well as Text based interface.
We had to just add a new command to give the controller the understanding of the command. 
On the model side we added a new functionality incorporating the algorithm for downsizing an image. 


Note :
1. Please make sure that the paths entered to load or save an Image are not supposed to have any form of whitespace in it. Having whitespace will produce erroneous outputs.

2. We have implemented the toggle feature for split operations in a slightly different manner by giving the undo operation. All the operations done by the split command will be permanent and can be saved. The user can always roll back to the previous image by clicking the undo button.

3. We are keeping the jar file as a part of the res folder but we expect the jar file to be executed from outside the res folder while running it with the script file. This is because the Script file has a path relative to the res folder and running the jar file inside the res folder will result in a faulty execution.

4. The test Input file images have been zipped into the testInputs.zip file. Please unzip this document before running each of the test cases.


Run the GUI :
1. To run the GUI for the application.
	java -jar ImageProcessing.jar
2. To run the text based interaction
	java -jar ImageProcessing.jar -text
3. To run the Script file based program
	java -jar ImageProcessing.jar -file res/Script.txt


Our model still consists of the four packages that we had in our previous assignment:

1. ImageFilterImpl class implements the ImageFilter interface which performs the image sepia, blur, sharpen, grayscale and image compression functionality.

2. ImageImpl class implements the Image interface which represents an image and consists of the getter and setter methods for the image loaded. 


3. ImageMetricsImpl class implements the ImageMetrics interface which computes the luma, value, intensity and histogram representations of the image


4. ImageTuningImpl class implements the ImageTuning interface which brightens, darkens, color corrects, level adjusts and flips the image horizontally, vertically and downsizes the image as well.


5. MaskImageManipulation is a new class added to the model. This class supports the functionality to mask any given image provided with the actual image and the masked image.


We still follow the MVC architecture where the controller takes in the input from the user and acts as a bridge between the model and the view. 

In addition to this, we have also implemented mock tests to test the text based controller as well as the GUI based controller. 

The images directory consists of a base image nature.jpg. This directory also saves all the images after the different operations that are performed on the base image nature.jpg. The base image has been picked from the website https://www.pexels.com/search/scenic/ which offers free images to the general public.


ImageUtil class consists of all the static methods such as readPPM, writePPM, load and save image methods which make use of the computed RGB values from the model to generate the respective type of images desired.

