/**
 * @author Robert Jonsson, ITM Östersund
 * @version 2.0
 * @file Ex03_08 - Talgenerator.java
 */

import java.io.*;

public class Talgenerator extends Thread
{
	// Instansvariabler
	private PipedWriter out;
	private PipedReader in;

	// Konstruktor
	public Talgenerator(PipedWriter out, PipedReader in)
	{
		this.out = out;
		this.in = in;
	}

	// Klassens run-metod
	public void run()
	{
		// Kör så länge som inte någon bett trådan att avbryta
		while (!isInterrupted())
		{
			// Börja med att sova en stund (max 10 sekunder). Detta görs enbart
			// för att simulera att någon intensiv/lång beräkning görs för att få fram
			// ett nytt tal.
			try
			{
				this.sleep((int)(Math.random() * 10000));
			}
			catch (InterruptedException ie)
			{
				// Blir trådan avbryten så avbryter vi while-loopen
				// med break.
				break;
			}

			// Vi slumpar fram nya värden för talet (0-10)
			int op1 = (int)(Math.random() * 11);
			int op2 = (int)(Math.random() * 11);

			// Slumpar vilken operator som ska användas
			String op = null;

			switch ((int)(Math.random() * 5))
			{
				case 0: op = "+"; break;
				case 1: op = "-"; break;
				case 2: op = "*"; break;
				case 3: op = "/"; break;
				default: op = "+"; break;
			}

			// Kopplar på en PrintWriter så att vi enkelt kan skriva en hel
			// sträng med println till utströmmen. Detta borde vi naturligtvis
			// löst på annat sätt så att vi slipper skapa varje gång vi ska
			// skicka något. Vi kan t.ex. som instansvariabel använda PrintWriter
			// i stället för PipedWriter. I konstruktorn skapar vi sen en
			// PrintWriter av PipedWriter så som vi gör nedan nu.
			PrintWriter pw = new PrintWriter(out);
			pw.println(op1 + " " + op + " " + op2);

			// Väntar på klartecken från andra tråden innan vi fortsätter
			// Skapar en BufferedReader av PipedReader så att vi enkelt kan
			// läsa en hel sträng från strömmen. Givetvis borde även detta
			// göras som beskrivits ovan.
			BufferedReader br = new BufferedReader(in);
			try
			{
				String svar = br.readLine();
			}
			catch (IOException fel)
			{
				System.out.println("Talgenerator: fel vid mottagning av svar");
				fel.printStackTrace();
			}
		}

		// Stäng strömmarna
		try
		{
			out.close();
			in.close();
		}
		catch(IOException fel)
		{
			System.out.println("Talgenerator: kunde inte stänga strömmarna");
			fel.printStackTrace();
		}
	}
}