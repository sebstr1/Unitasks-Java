/**
* <h1>Assignment 2 - Shape.java</h1>
* <p>Abstract base class. Different class (shapes such as circle and rectangle) inherits from this class
* and implements functions in their class.
* </p>
*
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-11-08
*/

package dt062g.sest1601.assignment2;

public abstract class Shape implements IDrawable{


  protected String color;   // Instance variable for color
  protected Point[] points; // Instance variable for points.


// Methods
  public String getColor() { // Getter for color
    return color;         
  }

  public void setColor(String clr) { // Setter for color

    color = clr;
  }

  public abstract double getCircumference(); // Abstract method for circumference
  
  public abstract double getArea();         // Abstract method for Area

  public void addPoint(Point p) { // Method for adding point

    points[1] = p;

  }

  public void addPoint(double x, double y) { // Another method for adding a point.

    points[1] = new Point(x, y);
  }
  
  

// Constructors

  public Shape(double x, double y, String color) { // Constructor sets point and color
    
     points = new Point[2];
     points[0] = new Point(x, y);
     this.color = color;
  } 

  public Shape(Point p, String color) { // Constructor sets point and color
    points = new Point[2];
    points[0] = p;
    this.color = color;
  }


















}
