/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_14 - Djur2.java
 */

// Vi ska kunna skapa aktiva objekt av denna klass
// så därför ärver vi från Thread
public class Djur2 extends Thread
{
	private String typ;  // Vilken typ av djur det är
	private String tal;  // Hur djuret låter när det pratar

	// I konstruktorn anger vi vilken typ av djur det är
	// och hur djuret låter när det pratar
	public Djur2(String typ, String tal)
	{
		this.typ = typ;
		this.tal = tal;
	}

	// Anropas när vi vill att djuret ska börja prata
	public void prata()
	{
		// Anropar start (som ärvs från Thread) för att starta tråden.
		// Start anropar i sin tur run.
		start();
	}

	// Klassens run-metod. Det är denna kod som körs när vi startar en ny tråd.
	public void run()
	{

		for (int i = 0; i < 5; i++)
		{
			// Skriver ut djurets läte
			System.out.println(tal + " " + (i+1) + " från " + typ);
		}
	}
}