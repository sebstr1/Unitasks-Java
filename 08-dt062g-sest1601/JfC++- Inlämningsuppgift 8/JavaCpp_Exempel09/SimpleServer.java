/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM �stersund
 * @version 1.0
 * @file Ex05_03 - SimpleServer.java
 */

import java.net.*;
import java.io.*;

public class SimpleServer
{
	// Kastar alla eventuella fel som kan uppst�. INTE S� SMART!
	public static void main(String[] args) throws Exception
	{
		// Skapar en ServerSocket som ligger och lyssnar
		// p� port 10003 efter klienter som vill ansluta
		ServerSocket ss = new ServerSocket(10003);

		// V�ntar p� att en klient ska ansluta
		System.out.print("V�ntar p� anslutning... ");
		Socket s = ss.accept();
		System.out.println("klient ansluten\n");

		// Skapar in- och utstr�mmar
		System.out.print("Skapar str�mmar f�r kommunikation... ");
		BufferedReader instr�m = new BufferedReader(new InputStreamReader(s.getInputStream()));
		PrintWriter utstr�m = new PrintWriter(s.getOutputStream(), true);
		System.out.println("klart");

		// V�ntar p� ett meddelande fr�n klienten
		System.out.print("V�ntar p� meddelande fr�n klienten... ");
		String meddelande = instr�m.readLine();
		System.out.println(meddelande);

		// Skickar ett svar till klienten
		System.out.print("Skickar svar... ");
		utstr�m.println(meddelande + " " + meddelande);
		System.out.println(meddelande + " " + meddelande);

		// St�nger str�mmar och socket
		// Viktigt att str�mmarna st�ngs innan den socket (varifr�n str�mmarna h�mtades) st�ngs
		instr�m.close();
		utstr�m.close();
		s.close();
		ss.close();
	}
}