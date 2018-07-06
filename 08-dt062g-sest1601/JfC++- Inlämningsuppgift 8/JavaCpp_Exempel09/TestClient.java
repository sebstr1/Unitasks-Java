import java.net.*;
import java.io.*;
import java.util.*;

public class TestClient
{
	public static void main(String[] args)
	{
		Socket s = null;
		//ObjectInputStream in = null;
		ObjectOutputStream out = null;

		System.out.print("Creating streams... ");
 		try
		{
			// Skapar en socket ansuten till localhost på port 10003
			s = new Socket("localhost", 10013);

			// Skapa strömmar för att skicka och ta emot data
			//in = new ObjectInputStream(s.getInputStream());
			out = new ObjectOutputStream(s.getOutputStream());
		}
		catch(UnknownHostException uhe)
		{
			System.out.println("Kan inte finna localhost\n" + uhe);
			System.exit(1);
		}
		catch(IOException ioe)
		{
			System.out.println("Kan inte skapa förbindelse till localhost\n" + ioe);
			System.exit(1);
		}
		System.out.println("done");

		try
		{
			// Skickar data till servern
			out.writeObject(new Date());

			// tar emot svaret och skriver ut på skärmen
			//Date d = (Date)in.readObject();
			//System.out.println(d);
		}
		/*catch (ClassNotFoundException fel)
		{
			System.out.println("Kunde inte hitta klass: " + fel.getMessage());
		}*/
		catch (IOException ioe)
		{
			System.out.println("Kunde inte skicka eller ta emot data\n" + ioe);
		}

		try
		{
			// Stänger strömmar och socket
			//in.close();
			out.close();
			s.close();
		}
		catch (IOException ioe)
		{
			System.out.println("Kunde inte stänga strömmar och/eller socket\n" + ioe);
		}
	}
}