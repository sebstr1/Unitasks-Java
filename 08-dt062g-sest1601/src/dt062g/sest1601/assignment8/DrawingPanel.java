/**
* <h1>Assignment 8 - DrawingPanel.java</h1>
* <p>This class extends JPanel.</p>
* <p> The drawingpanel on which the MainFrame uses as drawingArea to draw shapes on
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-12-11
*/


package dt062g.sest1601.assignment8;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class DrawingPanel extends JPanel{

    
    private Drawing activeDrawing;


    // Constructors
    public DrawingPanel() {
        setBackground(Color.white);
        activeDrawing = new Drawing();
        System.out.println("DrawingPanel Standard Constructor");
    }
    public DrawingPanel(Drawing drawing) {
        setBackground(Color.white);
        activeDrawing = drawing;
        paintComponent(getGraphics());
        System.out.println("DrawingPanel Second Constructor with repaint");
    }



    public void addDrawing(Drawing drawing) { // Adds shapes from drawing to the existing drawing
        if (drawing != null) {
            for (Shape x : drawing.getShapes()) {
                activeDrawing.addShape(x);      
            }  
        }
    }


    public void paintComponent(Graphics g) {   // Paints component
        super.paintComponent(g);
        if(activeDrawing != null) {
            activeDrawing.draw(g);
        }
    }



    /**
     * @param drawing the drawing to set
     */
    public void setDrawing(Drawing drawing) {
        activeDrawing = drawing;
        
        // paintComponent(getGraphics());
        repaint();
    }

    /**
     * @return the drawing
     */
    public Drawing getDrawing() {
        System.out.println("DrawingPanel - getDrawing");
        return activeDrawing;
    }

}