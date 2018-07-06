

/**
* <h1>Assignment 4</h1>
* This application creates a <code>Drawing</code> with a name,
* author and different shapes in it. It then calls various
* methods to print total circumference and area. 
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-11-20
*/

package dt062g.sest1601.assignment4;

public class Assignment4 {

	public static void main(String[] args) {
		testDrawing();
	}


	private static void testDrawing() {
		// Create an empty drawing and print info about it.
		System.out.println("Creating a new empty drawing...");
		
		Drawing d1 = new Drawing();
		
		System.out.println(d1);
		
		// Set name and author.
		System.out.println("\nSetting name and author...");
		
		d1.setName("Mona Lisa");
		d1.setAuthor("L. da Vincis");
		
		System.out.println(d1);
		
		// Add five shapes with no end points
		System.out.println("\nAdding 5 shapes with no end points...");
		
		Shape face = new Circle(100,100, "ffe0bd"); // RGB(255,224,189)
		Shape leftEye = new Circle(75, 75, "0000ff"); // RGB(0, 0, 255)
		Shape rightEye = new Circle(125, 75, "0000ff"); // RGB(0, 0, 255)
		Shape nose = new Rectangle(95, 100, "000000"); // RGB(0, 0, 0)
		Shape mouth = new Rectangle(55, 130, "ff0000"); // RGB(255, 0, 0)
		


		d1.addShape(face);
		d1.addShape(leftEye);
		d1.addShape(rightEye);
		d1.addShape(nose);
		d1.addShape(mouth);

		System.out.println(d1);
		
		// Add a null shape (size should not increase!).
		System.out.println("\nSize is: " + d1.getSize());
		System.out.println("Adding a null shape...");
		
		d1.addShape(null);
		
		System.out.println("Size is: " + d1.getSize());
		
		// Add end point to all shapes
		System.out.println("\nAdding end point to all shapes...");
		face.addPoint(175, 100);
		leftEye.addPoint(85, 75);
		rightEye.addPoint(135, 75);
		nose.addPoint(105, 115);
		mouth.addPoint(145, 140);
		System.out.println("Total circumference is: " + d1.getTotalCircumference());
		System.out.println("Total area is: " + d1.getTotalArea());
		
		// Draw the entire drawing
		System.out.println("\nDraw the drawing...");
		d1.draw();		
	}
}