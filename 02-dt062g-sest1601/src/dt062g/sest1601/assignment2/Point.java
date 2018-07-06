/**
* <h1>Assignment 2 - Point.java</h1>
* <p>This class contains a point (x, y coordinates) With get and set functions.
* It will create a point when constructor is called.
* </p>
*
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-11-08
*/

package dt062g.sest1601.assignment2;


public class Point {

  protected double xcord;  // X coordinate
  protected double ycord; // Y coordinate

  public void setX(double x) { // Setter for X coordinate
    xcord = x; }

  public void setY(double y) { // Setter for Y coordinate
    ycord = y; }

  public double getX() {  // Getter for X coordinate
    return xcord;
  }

  public double getY() {  // Getter for Y coordinate
    return ycord;
  }
  
  public Point() { xcord = 0; ycord = 0;} // Default constructor sets x and y of point to {0,0}

  public Point(double x, double y) { xcord = x; ycord = y; } // Constructor sets x and y of point to

  public String toString() { // toString overridden

    return "(" + xcord + ", " + ycord + ")";
  }














}
