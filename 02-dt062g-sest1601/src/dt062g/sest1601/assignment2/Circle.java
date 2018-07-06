/**
* <h1>Assignment 2 - Circle.java</h1>
* <p>This class inherits (extends) Shape and implements suitable functions.
* Contains suitable functions for calculations of a circle.
* </p>
*
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-11-08
*/

package dt062g.sest1601.assignment2;


class Circle extends Shape implements IDrawable {

  private final double pi = 3.14; // Constant pi to match assignment results (Would use Math.PI otherwise.)

// Constructors

  public Circle(double x, double y, String color) { // Calls superclass's constructor
    super(x, y, color);
  }
  
  public Circle(Point p, String color) { // Calls superclass's constructor
    super(p, color);
  }

  
  
  public double getRadius() { // Calculate radius with given points.
    if (points[1] == null) { // Retunrs -1 if endpoint not set.
      return -1;

    } else {

      double dx = (points[1].getX() - points[0].getX()); // delta x
      double dy = (points[1].getY() - points[0].getY()); // delta y

      return Math.sqrt(((Math.pow(dx, 2) + Math.pow(dy, 2)))); // Retunrs the radius (Pythagoraian Theorem)
    }

  }
  
  public double getCircumference() { // Calculate circu,ference of circle with given points.
    if (points[0] == null || points[1] == null) { // Returns -1 if not possible to calculate.

      return -1;
    } else {
      return (2*pi*getRadius()); // Retunrs the circumference
    }

  }
  
  public double getArea() { // Calculates area of circle with given points
    if (points[0] == null || points[1] == null) {
      return -1;

    } else {
      return (getRadius()*getRadius()*pi); // Retunrs the area
    }

  }
  
  
  
  
  
  
  
  
  public void draw() {                 // IDrawable draw() implemented

    System.out.println(toString());

  }

   public void draw(java.awt.Graphics g) { // IDrawable draw(Graphics) to be implemented

  }



  public String toString() { // Overrides toString

    return ("Drawing Circle start=" + points[0] + "; " + "end=" +
     ((points[1] == null) ? "N/A" : points[1]) + "; " + "radius=" + ((getRadius() == -1) ? "N/A" : getRadius())
        + "; " + "color=" + getColor());

  }

}
