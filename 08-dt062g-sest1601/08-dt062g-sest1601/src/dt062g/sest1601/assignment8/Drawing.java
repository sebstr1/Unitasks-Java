/**
* <h1>Assignment 8 - Drawing.java</h1>
* <p>Draws shapes.
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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Drawing implements IDrawable{
    
    @XmlElement(name = "name")
    private String name;
    
    @XmlElement(name = "author")
    private String author;
    
    @XmlElement(name = "shape")
    private ArrayList<Shape> shapes;



    public Drawing() {  // Default constructor
        this("", "");
    }
    
    public Drawing(String name, String author) { // Constructor
        this.name = name;
        this.author = author;
        shapes = new ArrayList<Shape>();
    }

    public void draw() { // IDrawable draw() implemented

        System.out.println("A drawing by " + getAuthor() + " called " + getName());

        for (Shape obj : shapes) {
            System.out.println(obj);   
        }
    }

    public void draw(java.awt.Graphics g) { // IDrawable draw(Graphics) to be implemented
        for (Shape x : shapes) {
            x.draw(g);
        }
        System.out.println("Drawing.java - draw(Graphics G) LOOPed shapes array");
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    // Ads Shape object to list if object does not refer to null
    public void addShape(Shape obj) {
        if (obj != null) {
            shapes.add(obj);
        } 
    }

    /**
     * @return the size of shape list
     */    
    public int getSize() {
        return shapes.size();
    }

    /**
     * @return the shapes
     */
    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    /**
     * @return the sum of all figures circumference.
     */
    public double getTotalCircumference() {

        double tot = 0;
        for (Shape obj : shapes) { // If no exception is thrown add circumference from figure to total
            try {
                double shapeCircum = obj.getCircumference();
                tot += shapeCircum;
            } catch (Exception e) {
            }
        }
        return tot;
    }
    
    /**
    * @return the sum of all figures Area.
    */
    public double getTotalArea() {
        double tot = 0; 
        for (Shape obj : shapes) {  // If no exception is thrown add are from figure to total
            try {
                double shapeArea = obj.getArea();
                tot += shapeArea;
            } catch (Exception e) {
            }
        }
        return tot;
    }


    public String toString() { // toString overridden

        StringBuffer sbuff = new StringBuffer("Drawing[name=" + ((getName() == null) ? "" : getName()) 
        + "; author=" + ((getAuthor() == null) ? "" : getAuthor()) + "; size=" + getSize());
        
        try {
            sbuff.append("; circumference=");
            sbuff.append(getTotalCircumference());
            sbuff.append("; area=");
            sbuff.append(getTotalArea());

        } catch (Exception e) {
            sbuff.append(0);
        }

        sbuff.append("]");
        return sbuff.toString();
    }
}