/**
 * @author Robert Jonsson, ITM Östersund
 * @version 2.0
 * @file Ex03_09 - Talberäknare.java
 */

import java.util.*;
import java.io.*;

public class Talberäknare extends Thread
{
	// Instansvariabler
	private PipedWriter out;
	private PipedReader in;

	// Konstruktor
	public Talberäknare(PipedWriter out, PipedReader in)
	{
		this.out = out;
		this.in = in;
	}

	// Klassens run-metod
	public void run()
	{
		// För att läsa från tangentbordet
		Scanner tgb = new Scanner(System.in);

		// Kör så länge som inte någon bett tråden att avbryta
		while (!isInterrupted())
		{
			// Väntar på att andra tråden ska skicka ett uttryck att beräkna
			System.out.println("Väntar på nytt tal...");

			// Skapar en BufferedReader så att vi enkelt kan läsa en hel sträng
			BufferedReader br = new BufferedReader(in);
			String uttryck = null;

			try
			{
				uttryck = br.readLine();
			}
			catch (IOException fel)
			{
				System.out.println("Talberäknare: fel vid mottagning av nytt uttryck");
				fel.printStackTrace();
			}

			// Skriv ut talet på skärmen och väntar på att användaren
			// ska mata in sitt svar
			System.out.print(uttryck + " = ");
			double svar = tgb.nextDouble();

			// Beräkna talet och kontroller om användaren svarat korrekt
			PrintWriter pw = new PrintWriter(out);

			if (svar == beräkna(uttryck))
			{
				System.out.println("korrekt svar\n");
				pw.println("ok");
			}
			else
			{
				System.out.println("fel svar\n");
				pw.println("fel");
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

	// Privat metod för att beräkna ett mattetal
	private double beräkna(String uttryck)
	{
		// Dela upp uttrycket och skicka vidare för beräkning
		StringTokenizer st = new StringTokenizer(uttryck, "+-*/", true);
		return beräkna(Double.parseDouble(st.nextToken()), st.nextToken(), Double.parseDouble(st.nextToken()));
	}

	// Privat metod för att beräkna ett mattetal
	private double beräkna(double op1, String op, double op2)
	{
		// Kontrollerar räknesätt och utför beräkningen
		double resultat = Double.NaN;

		switch(op.charAt(0))
		{
			case '+':
				resultat = op1 + op2;
				break;
			case '-':
				resultat = op1 - op2;
				break;
			case '/':
				resultat = op1 / op2;
				break;
			case '*':
				resultat = op1 * op2;
				break;
		}

		return resultat;
	}
}