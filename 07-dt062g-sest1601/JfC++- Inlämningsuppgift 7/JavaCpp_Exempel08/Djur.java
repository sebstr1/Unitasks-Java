/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_09 - Djur.java
 */

// Vi ska kunna skapa aktiva objekt av denna klass
// s� d�rf�r �rver vi fr�n Thread
public class Djur extends Thread
{
	private String typ;  // Vilken typ av djur det �r
	private String tal;  // Hur djuret l�ter n�r det pratar

	// I konstruktorn anger vi vilken typ av djur det �r
	// och hur djuret l�ter n�r det pratar
	public Djur(String typ, String tal)
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

	// Anropas efter varje g�ng djuret har pratat
	public void sov()
	{
		// Slumpar ett tal mellan 0 och 1000 (antal millisekunder)
		int millisekunder = (int)(Math.random() * 1000);

		try
		{
			// Anropar metoden sleep (som �rvs fr�n Thread)
			// Sleep g�r att tr�den "sover" i det antal millisekunder vi anger
			sleep(millisekunder * 4);  // sover max 4 sekunder
		}
		catch (InterruptedException avbryten)
		{
			// En tr�d kan bli avbryten n�r den sover (sleep)
			System.out.println("Djuret blev v�ckt");
		}
	}

	// Klassens run-metod. Det �r denna kod som k�rs n�r vi startar en ny tr�d.
	public void run()
	{
		for (int i = 0; i < 10; i++)
		{
			// Anropar sov f�r att djuret ska "vila" en stund
			sov();

			// Skriver ut djurets l�te
			System.out.println(tal + " " + (i+1) + " fr�n " + typ);
		}
	}
}