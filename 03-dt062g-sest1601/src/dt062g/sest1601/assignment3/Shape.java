/**
* <h1>Assignment 3 - Shape.java</h1>
* <p>Abstract base class. Different class (shapes such as circle and rectangle) inherits from this class
* and implements functions in their class.
* </p>
*
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-11-08
*/

package dt062g.sest1601.assignment3;

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

  public abstract double getCircumference() throws EndpointNotFoundException; // Abstract method for circumference
  
  public abstract double getArea() throws EndpointNotFoundException;         // Abstract method for Area

  public void addPoint(Point p) { // Method for adding point
    addPoint(p.getX(), p.getY());
  }

  public void addPoint(double x, double y) { // Another method for adding a point.
    points[1] = new Point(x, y);
  }
  
  

// Constructors

  public Shape(double x, double y, String color) { // Constructor sets point and color
     this(new Point(x, y), color);
  } 

  public Shape(Point p, String color) { // Constructor sets point and color
    points = new Point[2];
    points[0] = p;
    this.color = color;
  }


















}
