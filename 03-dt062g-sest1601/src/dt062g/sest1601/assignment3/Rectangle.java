/**
* <h1>Assignment 3 - Rectangle.java</h1>
* <p>This class inherits (extends) Shape and implements suitable functions.
* Contains suitable functions for calculations of a rectangle.
* </p>
*
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-11-08
*/

package dt062g.sest1601.assignment3;


class Rectangle extends Shape implements IDrawable {


  // Constructors
  public Rectangle(double x, double y, String color) { // Calls superclass's constructor
    super(x, y, color);
  }
  
  public Rectangle(Point p, String color) { // Calls superclass's constructor
    super(p, color);
  }




  public void draw() {                 // IDrawable draw() implemented

    System.out.println(toString());

  }

   public void draw(java.awt.Graphics g) { // IDrawable draw(Graphics) to be implemented

  }

  public double getCircumference() throws EndpointNotFoundException{ // Calculates circumference of rectangle by given points
    if (points[0] == null || points[1] == null) {

      throw new EndpointNotFoundException("The area cannot be calculated, end point is missing!");
    } else {
      double width = getWidth()*2;
      double height = getHeight()*2;
      return (width + height); // Retunrs the circumference
    }
  }

    public double getArea() throws EndpointNotFoundException{ // Calculates area of rectangle by given points
    if (points[0] == null || points[1] == null) {
      throw new EndpointNotFoundException("The area cannot be calculated, end point is missing!");
    
    } else {
      double width = getWidth();
      double height = getHeight();
      return (width * height); // Retunrs the area
    }

  }

  public double getWidth() throws EndpointNotFoundException{  // Calculates width of rectangle by given points
    if (points[1] == null) {
      throw new EndpointNotFoundException("The area cannot be calculated, end point is missing!");
    } else {

      return (Math.abs(points[1].getX() - points[0].getX())); // Retunrs width
      
    }
  }

  public double getHeight() throws EndpointNotFoundException{ // Calculates height of rectangle by given points
     if (points[1] == null) {

      throw new EndpointNotFoundException("The area cannot be calculated, end point is missing!");
    } else {

      return (Math.abs(points[1].getY() - points[0].getY())); // Returns height

    }
  }


  public String toString() { // toString overridden
    
     StringBuffer sbuff = new StringBuffer("Drawing a Rectangle [start=" + points[0] + "; " + "end=" +
      ((points[1] == null) ? "N/A" : points[1]) + "; ");
    
     try {
       sbuff.append("width=");
       sbuff.append(getWidth());
       sbuff.append("; height=");
       sbuff.append(getHeight());
       
     } catch (Exception e) {
       sbuff.append("N/A");
     }

     sbuff.append("; color=" + getColor() + "]");
     return sbuff.toString();
  }

}
