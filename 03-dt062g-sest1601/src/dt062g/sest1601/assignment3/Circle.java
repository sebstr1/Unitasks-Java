/**
* <h1>Assignment 4 - Circle.java</h1>
* <p>This class inherits (extends) Shape and implements suitable functions.
* Contains suitable functions for calculations of a circle.
* </p>
*
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-11-08
*/

package dt062g.sest1601.assignment3;


class Circle extends Shape implements IDrawable {

  private final double pi = 3.14; // Constant pi to match assignment results (Would use Math.PI otherwise.)

// Constructors

  public Circle(double x, double y, String color) { // Calls superclass's constructor
    super(x, y, color);
  }
  
  public Circle(Point p, String color) { // Calls superclass's constructor
    super(p, color);
  }

  
  
  public double getRadius() throws EndpointNotFoundException{ // Calculate radius with given points.
    if (points[1] == null) { // Retunrs -1 if endpoint not set.
      throw new EndpointNotFoundException("The area cannot be calculated, end point is missing!");

    } else {

      double dx = (points[1].getX() - points[0].getX()); // delta x
      double dy = (points[1].getY() - points[0].getY()); // delta y

      return Math.sqrt(((Math.pow(dx, 2) + Math.pow(dy, 2)))); // Retunrs the radius (Pythagoraian Theorem)
    }

  }
  
  public double getCircumference() throws EndpointNotFoundException{ // Calculate circu,ference of circle with given points.
    if (points[0] == null || points[1] == null) { // Returns -1 if not possible to calculate.

      throw new EndpointNotFoundException("The area cannot be calculated, end point is missing!");
    } else {
      return (2*pi*getRadius()); // Retunrs the circumference
    }

  }
  
  public double getArea() throws EndpointNotFoundException{ // Calculates area of circle with given points
    if (points[0] == null || points[1] == null) {
      throw new EndpointNotFoundException("The area cannot be calculated, end point is missing!");

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
     
     StringBuffer sbuff = new StringBuffer("Drawing Circle [start=" + points[0] + "; " + "end=" +
     ((points[1] == null) ? "N/A" : points[1]) + "; " + "radius=");

    try {
      sbuff.append(getRadius());

    } catch (Exception e) {
      sbuff.append("N/A");
    }

    sbuff.append("; color=" + getColor() + "]");
    return sbuff.toString();

  }

}
