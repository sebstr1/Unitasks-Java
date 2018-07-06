/**
* <h1>Assignment 5</h1>
* This application creates a <code>Drawing</code> with a name,
* author and different shapes in it. It then saves the drawing
* to XML, clear the drawing and then loads a drawing from XML. 
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-11-20
*/

package dt062g.sest1601.assignment5;


public class Assignment5 {

	public static void main(String[] args) {
		testDrawing();
	}

	private static void testDrawing() {
		// Create a drawing with a name and author.
		System.out.println("Create a drawing...\n");
		Drawing d1 = new Drawing();

		d1.setAuthor("sest1601");
		d1.setName("Skriet");
			
		// Create at least 5 shapes with end points
		Shape bg = new Circle(100, 100, "black");
		bg.addPoint(200, 200);
		Shape sky = new Circle(75, 75, "blue");
		sky.addPoint(100, 100); 
		Shape focus = new Circle(125, 75, "red");
		focus.addPoint(160, 130); 
		Shape epic = new Rectangle(95, 100, "orange");
		epic.addPoint(105, 110);
		Shape shit = new Rectangle(55, 130, "brown");
		shit.addPoint(80, 200);

		// Add shapes to the drawing
		d1.addShape(bg);
		d1.addShape(sky);
		d1.addShape(focus);
		d1.addShape(epic);
		d1.addShape(shit);

		// Print the drawing
		d1.draw();
		
		// Save the drawing to XML
		final String fileName = "sest1601EpicArt.xml";
		System.out.println("\nSave the drawing to " + fileName + "...");
		FileHandler.saveToXML(d1, fileName);
		
		// Clear and print
		System.out.println("\nClearing the drawing and then draw it....");
		d1.clear();
		d1.draw();
		
		// Load drawing from XML
		System.out.println("\nLoad drawing from " + fileName + "...\n");
		d1 = FileHandler.loadFromXML(fileName);

		d1.draw();
	}
}