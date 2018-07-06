/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM �stersund
 * @version 1.0
 * @file Ex05_04 - SimpleClient.java
 */

import java.net.*;
import java.io.*;

public class SimpleClient
{
	// Kastar alla eventuella fel som kan uppst�. INTE S� SMART!
	public static void main(String[] args) throws Exception
	{
		// Skapar en socket ansuten till localhost p� port 10003
		Socket s = new Socket("localhost", 10003);

		// Skapa str�mmar f�r att skicka och ta emot data
		BufferedReader instr�m = new BufferedReader(new InputStreamReader(s.getInputStream()));
		PrintWriter utstr�m = new PrintWriter(s.getOutputStream(), true); // auto flush

		// Skickar data till servern
		utstr�m.println("Hej!");
		System.out.println("Skickat: Hej!");

		// Tar emot svaret och skriver ut p� sk�rmen
		String svar = instr�m.readLine();
		System.out.println("Tagit emot: " + svar);

		// St�nger str�mmar och socket
		instr�m.close();
		utstr�m.close();
		s.close();
	}
}