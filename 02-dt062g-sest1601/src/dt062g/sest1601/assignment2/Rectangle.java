/**
* <h1>Assignment 2 - Rectangle.java</h1>
* <p>This class inherits (extends) Shape and implements suitable functions.
* Contains suitable functions for calculations of a rectangle.
* </p>
*
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-11-08
*/

package dt062g.sest1601.assignment2;


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

  public double getCircumference() { // Calculates circumference of rectangle by given points
    if (points[0] == null || points[1] == null) {

      return -1;
    } else {
      double width = getWidth()*2;
      double height = getHeight()*2;
      return (width + height); // Retunrs the circumference
    }

  }

    public double getArea() { // Calculates area of rectangle by given points
    if (points[0] == null || points[1] == null) {
      return -1;
    
    } else {
      double width = getWidth();
      double height = getHeight();
      return (width * height); // Retunrs the area
    }

  }

  public double getWidth() {  // Calculates width of rectangle by given points
    if (points[1] == null) {

      return -1;
    } else {

      return (points[1].getX() - points[0].getX()); // Retunrs width
      
    }
  }

  public double getHeight() { // Calculates height of rectangle by given points
     if (points[1] == null) {

      return -1;
    } else {

      return (points[1].getY() - points[0].getY()); // Returns height

    }
  }


  public String toString() { // toString overridden
  
    return("Drawing a Rectangle start=" + points[0] + "; " + "end=" +
    ((points[1] == null) ? "N/A" : points[1]) + "; " + "width=" +
     ((getWidth() == -1) ? "N/A" : getWidth()) + "; " + "height=" +
     ((getHeight() == -1) ? "N/A" : getHeight()) + "; " + "color=" + getColor());

  }

}
