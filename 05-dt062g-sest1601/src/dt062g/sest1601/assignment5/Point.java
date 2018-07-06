/**
* <h1>Assignment 4 - Point.java</h1>
* <p>This class contains a point (x, y coordinates) With get and set functions.
* It will create a point when constructor is called.
* </p>
*
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-11-22
*/


package dt062g.sest1601.assignment5;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Point {

  @XmlElement(name = "x")
  protected double xcord;  // X coordinate
  @XmlElement(name = "y")
  protected double ycord; // Y coordinate


  public Point() { // Default constructor sets x and y of point to {0,0}
    xcord = 0;
    ycord = 0;
  } 

  public Point(double x, double y) { // Constructor sets x and y of point to
    xcord = x;
    ycord = y;
  } 



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

  public String toString() { // toString overridden

    return "(" + xcord + ", " + ycord + ")";
  }














}
