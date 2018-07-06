/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_14 - Djur2.java
 */

// Vi ska kunna skapa aktiva objekt av denna klass
// s� d�rf�r �rver vi fr�n Thread
public class Djur2 extends Thread
{
	private String typ;  // Vilken typ av djur det �r
	private String tal;  // Hur djuret l�ter n�r det pratar

	// I konstruktorn anger vi vilken typ av djur det �r
	// och hur djuret l�ter n�r det pratar
	public Djur2(String typ, String tal)
	{
		this.typ = typ;
		this.tal = tal;
	}

	// Anropas n�r vi vill att djuret ska b�rja prata
	public void prata()
	{
		// Anropar start (som �rvs fr�n Thread) f�r att starta tr�den.
		// Start anropar i sin tur run.
		start();
	}

	// Klassens run-metod. Det �r denna kod som k�rs n�r vi startar en ny tr�d.
	public void run()
	{

		for (int i = 0; i < 5; i++)
		{
			// Skriver ut djurets l�te
			System.out.println(tal + " " + (i+1) + " fr�n " + typ);
		}
	}
}