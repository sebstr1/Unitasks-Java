/**
* <h1>Assignment 6 - FileHandler.java</h1>
* <p>Loads Drawings from XML and also Saves drawings to XML.</p>
*
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-11-22
*/
package dt062g.sest1601.assignment6;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class FileHandler {

    FileHandler() {} // Constructor

    public static void saveToXML(Drawing drawing, String fileName){ // Saves to XML
        
        if (!(fileName.endsWith(".xml"))) { // If filename does not end with .xml fix so that it does.
            fileName = fileName + ".xml";
        }

        try { 
            JAXBContext context = JAXBContext.newInstance(Drawing.class); // Try creating JAXB marshaller to write object to file.
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // Set output to be indented.

            marshaller.marshal(drawing, new File(fileName)); // Writes object to xml file.
            
        } catch (Exception e) {
            System.err.println("Error saving to XML");
        }

    }

   public static void saveToXML(Drawing drawing) { // auto generate filename and call the other method to write to file.
        String fileName = drawing.getName() + "by" + drawing.getAuthor() + ".xml";
        saveToXML(drawing, fileName);
    }

    public static Drawing loadFromXML(String fileName){ // Load drawing object from file
        if (!(fileName.endsWith(".xml"))) {
            fileName = fileName + ".xml";          // Fixes so that filename we want to load ends with .xml
        }

        Drawing loadedDrawing = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Drawing.class); // Creates JAXB umarshaller (reader) for Drawing class.
            Unmarshaller unmarshaller = context.createUnmarshaller();
            loadedDrawing = (Drawing) unmarshaller.unmarshal(new File(fileName)); // Reads Drawing object from XML file.

        } catch (Exception e) {
            System.err.println("Error loading xml");
        }

        return loadedDrawing;
    }
    
}