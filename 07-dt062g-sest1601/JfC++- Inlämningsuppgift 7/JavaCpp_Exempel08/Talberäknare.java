/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_23 - Talberäknare.java
 */

import java.util.*;

public class Talberäknare extends Thread
{
	// INstansvariabler
	private Mattetal tal;

	// Konstruktor
	public Talberäknare(Mattetal tal)
	{
		this.tal = tal;
	}

	// Klassens run-metod
	public void run()
	{
		// För att läsa från tangentbordet
		Scanner in = new Scanner(System.in);

		// Kör så länge som inte någon bett tråden att avbryta
		while (!isInterrupted())
		{
			// Försöker hämta ett nytt tal
			// Observera att tråden kan få vänta (wait) om det inte
			// finns något mattetal att hämta.
			String uttryck = tal.hämtaTal();

			// Skriv ut talet på skärmen och väntar på att användaren
			// ska mata in sitt svar
			System.out.print(uttryck + " = ");
			double svar = in.nextDouble();

			// Beräkna talet och kontroller om användaren svarat korrekt
			if (svar == beräkna(uttryck))
			{
				System.out.println("korrekt svar\n");
			}
			else
			{
				System.out.println("fel svar\n");
			}
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