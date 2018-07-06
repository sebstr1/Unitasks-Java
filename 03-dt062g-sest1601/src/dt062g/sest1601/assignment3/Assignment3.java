package dt062g.sest1601.assignment3;

// import java.util.Scanner;

/**
* <h1>Assignment 3</h1>
* This application creates different shapes and calls various
* methods to print circumference, print area and draw the 
* shapes to the standard output 
*
* @author  Sebastian Strindlund (sest1601)
* @version 1.0
* @since   11-08-2017 
*/

public class Assignment3 {

	public static void main(String[] args) {
		testRectangle();
		System.out.println(); // new line
		testCircle();
	}

	private static void testRectangle() {
		// Create a rectangle and draw it.
		Rectangle r1 = new Rectangle(new Point(0, 0),  "blue");
		System.out.println("Drawing a newly created rectangle...");
		r1.draw();
		
		// Print area of the rectangle.
		System.out.println();
		printArea(r1);
				
		// Set new end point to the rectangle by calling addPoint 
		// with a new value and then print the area again.
		Point p1 = new Point(5, 5);
		System.out.println("\nChanging end point of rectangle to " +  p1 + "...");
		r1.addPoint(p1);
		printArea(r1);
		
	}

	private static void testCircle() {
		// Create a circle and draw it.
		Circle s1 = new Circle(5, 5, "black");
		System.out.println("Drawing a newly created circle...");
		s1.draw();
		
		// Print area of the circle.
		System.out.println();
		printArea(s1);
				
		// Set new end point to the rectangle by calling addPoint 
		// with a new value and then print the area again.
		Point p1 = new Point(8, 9);
		System.out.println("\nChanging end point of circle to " +  p1 + "...");
		s1.addPoint(p1);
		printArea(s1);
	}
	
	private static void printArea(Shape shape) {
		System.out.println("Printing area of a " + shape.getClass().getSimpleName());

		// If the area cannot be calculated, print an error message instead.
		try {
			// Get area of the shape and print it.
			double area = shape.getArea();
			System.out.println("The area is: " + area);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}