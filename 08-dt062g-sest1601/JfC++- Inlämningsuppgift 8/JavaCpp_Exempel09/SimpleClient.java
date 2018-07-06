/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM Östersund
 * @version 1.0
 * @file Ex05_04 - SimpleClient.java
 */

import java.net.*;
import java.io.*;

public class SimpleClient
{
	// Kastar alla eventuella fel som kan uppstå. INTE SÅ SMART!
	public static void main(String[] args) throws Exception
	{
		// Skapar en socket ansuten till localhost på port 10003
		Socket s = new Socket("localhost", 10003);

		// Skapa strömmar för att skicka och ta emot data
		BufferedReader inström = new BufferedReader(new InputStreamReader(s.getInputStream()));
		PrintWriter utström = new PrintWriter(s.getOutputStream(), true); // auto flush

		// Skickar data till servern
		utström.println("Hej!");
		System.out.println("Skickat: Hej!");

		// Tar emot svaret och skriver ut på skärmen
		String svar = inström.readLine();
		System.out.println("Tagit emot: " + svar);

		// Stänger strömmar och socket
		inström.close();
		utström.close();
		s.close();
	}
}