/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM Östersund
 * @version 1.0
 * @file Ex05_03 - SimpleServer.java
 */

import java.net.*;
import java.io.*;

public class SimpleServer
{
	// Kastar alla eventuella fel som kan uppstå. INTE SÅ SMART!
	public static void main(String[] args) throws Exception
	{
		// Skapar en ServerSocket som ligger och lyssnar
		// på port 10003 efter klienter som vill ansluta
		ServerSocket ss = new ServerSocket(10003);

		// Väntar på att en klient ska ansluta
		System.out.print("Väntar på anslutning... ");
		Socket s = ss.accept();
		System.out.println("klient ansluten\n");

		// Skapar in- och utströmmar
		System.out.print("Skapar strömmar för kommunikation... ");
		BufferedReader inström = new BufferedReader(new InputStreamReader(s.getInputStream()));
		PrintWriter utström = new PrintWriter(s.getOutputStream(), true);
		System.out.println("klart");

		// Väntar på ett meddelande från klienten
		System.out.print("Väntar på meddelande från klienten... ");
		String meddelande = inström.readLine();
		System.out.println(meddelande);

		// Skickar ett svar till klienten
		System.out.print("Skickar svar... ");
		utström.println(meddelande + " " + meddelande);
		System.out.println(meddelande + " " + meddelande);

		// Stänger strömmar och socket
		// Viktigt att strömmarna stängs innan den socket (varifrån strömmarna hämtades) stängs
		inström.close();
		utström.close();
		s.close();
		ss.close();
	}
}