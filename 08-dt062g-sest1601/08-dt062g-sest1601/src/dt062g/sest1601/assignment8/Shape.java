/**
* <h1>Assignment 8 - Shape.java</h1>
* <p>Abstract base class. Different class (shapes such as circle and rectangle) inherits from this class
* and implements functions in their class.
* </p>
*
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-12-07
*/

package dt062g.sest1601.assignment8;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({ Rectangle.class, Circle.class })
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public abstract class Shape implements IDrawable{

  @XmlElement(name="color")
  protected String color;   // Instance variable for color
  @XmlElement(name = "point")
  protected ArrayList<Point> points; // Instance variable for points.


  // Constructors
  public Shape() {}


  public Shape(double x, double y, String color) { // Constructor sets point and color
    this(new Point(x, y), color);
  }

  public Shape(Point p, String color) { // Constructor sets point and color
    points = new ArrayList<Point>();
    points.add(0, p);
    this.color = color;
  }

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
    points.add(1, p);
  }

  public void addPoint(double x, double y) { // Another method for adding a point.
    addPoint(new Point(x,y));
  }
  
  



















}
