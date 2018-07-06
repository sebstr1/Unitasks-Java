import java.net.*;
import java.io.*;
import java.util.*;

public class TestServer
{
	public static void main(String[] args)
	{
		try
		{
			// Skapar en ServerSocket som ligger och lyssnar
			// på port 10003 efter klienter som vill ansluta
			ServerSocket ss = new ServerSocket(10013);

			System.out.print("Waiting for client... ");
			Socket s = ss.accept();
			System.out.println("client connected\n");

			System.out.print("Creating streams... ");
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			System.out.println("done");

			System.out.print("Waiting for message... ");
			Date d = (Date)in.readObject();
			System.out.println("\nReceived " + d);

			System.out.print("Sending response... ");
			d = new Date();
			out.writeObject(d);
			System.out.println("\nSent " + d );

			in.close();
			out.close();
			s.close();
			ss.close();
		}
		catch (ClassNotFoundException fel)
		{
			System.out.println("Kunde inte hitta klass: " + fel.getMessage());
		}
		catch (IOException iofel)
		{
			System.out.println("Kunde inte lyssna på port: 10003\n" + iofel.getMessage());
		}
	}
}