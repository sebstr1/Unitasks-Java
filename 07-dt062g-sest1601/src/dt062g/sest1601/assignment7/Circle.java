/**
* <h1>Assignment 7 - Circle.java</h1>
* <p>This class inherits (extends) Shape and implements suitable functions.
* Contains suitable functions for calculations of a circle.
* </p>
*
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-12-07
*/

package dt062g.sest1601.assignment7;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
class Circle extends Shape implements IDrawable {

  @XmlTransient
  private final double pi = 3.14; // Constant pi to match assignment results (Would use Math.PI otherwise.)

// Constructors

  public Circle() {}

  public Circle(double x, double y, String color) { // Calls superclass's constructor
    super(x, y, color);
  }
  
  public Circle(Point p, String color) { // Calls superclass's constructor
    super(p, color);
  }

  
  
  public double getRadius() throws EndpointNotFoundException{ // Calculate radius with given points.

    if (points.get(1) == null) {  
      throw new EndpointNotFoundException("The radius cannot be calculated, end point is missing!");

    } else {
      double dx = (points.get(1).getX() - points.get(0).getX()); // delta x
      double dy = (points.get(1).getY() - points.get(0).getY()); // delta y

      return Math.sqrt(((Math.pow(dx, 2) + Math.pow(dy, 2)))); // Retunrs the radius (Pythagoraian Theorem)
    }

  }
  
  public double getCircumference() throws EndpointNotFoundException{ // Calculate circu,ference of circle with given points.

    if (points.get(0) == null || points.get(1) == null) {
      throw new EndpointNotFoundException("The circumference cannot be calculated, end point is missing!");
    
    } else {
      return (2*pi*getRadius()); // Retunrs the circumference
    }

  }
  
  public double getArea() throws EndpointNotFoundException{ // Calculates area of circle with given points

    if (points.get(0) == null || points.get(1) == null) {
      throw new EndpointNotFoundException("The area cannot be calculated, end point is missing!");

    } else {
      return (getRadius()*getRadius()*pi); // Retunrs the area
    }

  }
  
  
  public void draw() {                 // IDrawable draw() implemented

    System.out.println(toString());

  }

   public void draw(java.awt.Graphics g) { // IDrawable draw(Graphics) to be implemented
    
    try {
      int x = (int) Math.round(points.get(0).getX());
      int y = (int) Math.round(points.get(0).getY());
      int r = (int) Math.round(getRadius()*2);

      x = x-(r/2);
      y = y-(r/2);
      Graphics2D g2d = (Graphics2D) g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g.setColor(Color.decode(getColor()));
      g.fillOval(x, y, r, r); 

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

  }



  public String toString() { // Overrides toString
     
     StringBuffer sbuff = new StringBuffer("Drawing Circle [start=" + points.get(0) + "; " + "end=" +
     ((points.get(1) == null) ? "N/A" : points.get(1) + "; " + "radius="));

    try {
      sbuff.append(getRadius());

    } catch (Exception e) {
      sbuff.append("N/A");
    }

    sbuff.append("; color=" + getColor() + "]");
    return sbuff.toString();

  }

}
