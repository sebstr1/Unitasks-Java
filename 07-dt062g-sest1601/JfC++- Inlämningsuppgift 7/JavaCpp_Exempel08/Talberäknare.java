/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_23 - Talber�knare.java
 */

import java.util.*;

public class Talber�knare extends Thread
{
	// INstansvariabler
	private Mattetal tal;

	// Konstruktor
	public Talber�knare(Mattetal tal)
	{
		this.tal = tal;
	}

	// Klassens run-metod
	public void run()
	{
		// F�r att l�sa fr�n tangentbordet
		Scanner in = new Scanner(System.in);

		// K�r s� l�nge som inte n�gon bett tr�den att avbryta
		while (!isInterrupted())
		{
			// F�rs�ker h�mta ett nytt tal
			// Observera att tr�den kan f� v�nta (wait) om det inte
			// finns n�got mattetal att h�mta.
			String uttryck = tal.h�mtaTal();

			// Skriv ut talet p� sk�rmen och v�ntar p� att anv�ndaren
			// ska mata in sitt svar
			System.out.print(uttryck + " = ");
			double svar = in.nextDouble();

			// Ber�kna talet och kontroller om anv�ndaren svarat korrekt
			if (svar == ber�kna(uttryck))
			{
				System.out.println("korrekt svar\n");
			}
			else
			{
				System.out.println("fel svar\n");
			}
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