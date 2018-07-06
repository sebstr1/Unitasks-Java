/**
 * @author Robert Jonsson, ITM �stersund
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
		// K�r s� l�nge som inte n�gon bett tr�dan att avbryta
		while (!isInterrupted())
		{
			// B�rja med att sova en stund (max 10 sekunder). Detta g�rs enbart
			// f�r att simulera att n�gon intensiv/l�ng ber�kning g�rs f�r att f� fram
			// ett nytt tal.
			try
			{
				this.sleep((int)(Math.random() * 10000));
			}
			catch (InterruptedException ie)
			{
				// Blir tr�dan avbryten s� avbryter vi while-loopen
				// med break.
				break;
			}

			// Vi slumpar fram nya v�rden f�r talet (0-10)
			int op1 = (int)(Math.random() * 11);
			int op2 = (int)(Math.random() * 11);

			// Slumpar vilken operator som ska anv�ndas
			String op = null;

			switch ((int)(Math.random() * 5))
			{
				case 0: op = "+"; break;
				case 1: op = "-"; break;
				case 2: op = "*"; break;
				case 3: op = "/"; break;
				default: op = "+"; break;
			}

			// Kopplar p� en PrintWriter s� att vi enkelt kan skriva en hel
			// str�ng med println till utstr�mmen. Detta borde vi naturligtvis
			// l�st p� annat s�tt s� att vi slipper skapa varje g�ng vi ska
			// skicka n�got. Vi kan t.ex. som instansvariabel anv�nda PrintWriter
			// i st�llet f�r PipedWriter. I konstruktorn skapar vi sen en
			// PrintWriter av PipedWriter s� som vi g�r nedan nu.
			PrintWriter pw = new PrintWriter(out);
			pw.println(op1 + " " + op + " " + op2);

			// V�ntar p� klartecken fr�n andra tr�den innan vi forts�tter
			// Skapar en BufferedReader av PipedReader s� att vi enkelt kan
			// l�sa en hel str�ng fr�n str�mmen. Givetvis borde �ven detta
			// g�ras som beskrivits ovan.
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

		// St�ng str�mmarna
		try
		{
			out.close();
			in.close();
		}
		catch(IOException fel)
		{
			System.out.println("Talgenerator: kunde inte st�nga str�mmarna");
			fel.printStackTrace();
		}
	}
}