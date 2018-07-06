/**
 * @author Robert Jonsson, ITM �stersund
 * @version 2.0
 * @file Ex03_09 - Talber�knare.java
 */

import java.util.*;
import java.io.*;

public class Talber�knare extends Thread
{
	// Instansvariabler
	private PipedWriter out;
	private PipedReader in;

	// Konstruktor
	public Talber�knare(PipedWriter out, PipedReader in)
	{
		this.out = out;
		this.in = in;
	}

	// Klassens run-metod
	public void run()
	{
		// F�r att l�sa fr�n tangentbordet
		Scanner tgb = new Scanner(System.in);

		// K�r s� l�nge som inte n�gon bett tr�den att avbryta
		while (!isInterrupted())
		{
			// V�ntar p� att andra tr�den ska skicka ett uttryck att ber�kna
			System.out.println("V�ntar p� nytt tal...");

			// Skapar en BufferedReader s� att vi enkelt kan l�sa en hel str�ng
			BufferedReader br = new BufferedReader(in);
			String uttryck = null;

			try
			{
				uttryck = br.readLine();
			}
			catch (IOException fel)
			{
				System.out.println("Talber�knare: fel vid mottagning av nytt uttryck");
				fel.printStackTrace();
			}

			// Skriv ut talet p� sk�rmen och v�ntar p� att anv�ndaren
			// ska mata in sitt svar
			System.out.print(uttryck + " = ");
			double svar = tgb.nextDouble();

			// Ber�kna talet och kontroller om anv�ndaren svarat korrekt
			PrintWriter pw = new PrintWriter(out);

			if (svar == ber�kna(uttryck))
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

	// Privat metod f�r att ber�kna ett mattetal
	private double ber�kna(String uttryck)
	{
		// Dela upp uttrycket och skicka vidare f�r ber�kning
		StringTokenizer st = new StringTokenizer(uttryck, "+-*/", true);
		return ber�kna(Double.parseDouble(st.nextToken()), st.nextToken(), Double.parseDouble(st.nextToken()));
	}

	// Privat metod f�r att ber�kna ett mattetal
	private double ber�kna(double op1, String op, double op2)
	{
		// Kontrollerar r�knes�tt och utf�r ber�kningen
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