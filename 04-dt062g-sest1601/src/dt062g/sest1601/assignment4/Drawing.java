/**
* <h1>Assignment 4 - Drawing.java</h1>
* <p>Draws shapes.
* </p>
*
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-11-20
*/

package dt062g.sest1601.assignment4;

import java.util.ArrayList;

public class Drawing implements IDrawable{

    private String name;
    private String author;
    private ArrayList<Shape> shapes;



    public Drawing() {  // Default constructor
        shapes = new ArrayList<Shape>();
    }

    public Drawing(String name, String author) { // Constructor
        this.name = name;
        this.author = author;
    }

    public void draw() { // IDrawable draw() implemented

        System.out.println("A drawing by " + getAuthor() + " called " + getName());

        for (Shape obj : shapes) {
            System.out.println(obj);   
        }
    }

    public void draw(java.awt.Graphics g) { // IDrawable draw(Graphics) to be implemented

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