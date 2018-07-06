/**
* <h1>Assignment 1</h1>
* <p>This application allows the user to enter data for a circle
* or rectangle. The circumference and area are then calculated
* and the result is displayed to the standard output.
* </p>
*
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-11-06
*/

package dt062g.sest1601.assignment1;

import java.util.Scanner;

public class GeoCalc {
  private static Scanner userInput = new Scanner(System.in);
  public static void main(String[] args) {
    
    UserInterface();
    userInput.close();

  }



  private static void UserInterface() {


    String input = "korv";
      while(!input.equalsIgnoreCase("exit")) {

        
        
        System.out.print("Do you want to calculate area of (circle), (rectangle) or (exit) : ");
        input = userInput.next();
        userInput.nextLine();
        

        if (input.equalsIgnoreCase("circle")) {
          circle();
         
        
        } else if (input.equalsIgnoreCase("rectangle")) {
          rectangle();

        } else if (input.equalsIgnoreCase("exit")) {
          System.out.println("Terminating program...");

        } else {
          System.out.println("Error. Enter 'circle', 'rectangle or 'exit'");      
        }

        
        
    }
  }

  private static void circle() {
    System.out.print("Radius: ");
    double radius = userInput.nextDouble();
    userInput.nextLine();

    System.out.println("Cirkumference = " + (2*Math.PI*radius));
    System.out.println("Area = " + (radius*radius*Math.PI));
    
  }

  private static void rectangle() {
    System.out.print("Width: ");
    double width = userInput.nextDouble();
    userInput.nextLine();
    
    System.out.print("Height: ");
    double height = userInput.nextDouble();
    userInput.nextLine();

    System.out.println("Cirkumference = " + (width*2 + height*2));
    System.out.println("Area = " + width*height);
  }




}

