/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_09 - Djur.java
 */

// Vi ska kunna skapa aktiva objekt av denna klass
// så därför ärver vi från Thread
public class Djur extends Thread
{
	private String typ;  // Vilken typ av djur det är
	private String tal;  // Hur djuret låter när det pratar

	// I konstruktorn anger vi vilken typ av djur det är
	// och hur djuret låter när det pratar
	public Djur(String typ, String tal)
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

	// Anropas efter varje gång djuret har pratat
	public void sov()
	{
		// Slumpar ett tal mellan 0 och 1000 (antal millisekunder)
		int millisekunder = (int)(Math.random() * 1000);

		try
		{
			// Anropar metoden sleep (som ärvs från Thread)
			// Sleep gör att tråden "sover" i det antal millisekunder vi anger
			sleep(millisekunder * 4);  // sover max 4 sekunder
		}
		catch (InterruptedException avbryten)
		{
			// En tråd kan bli avbryten när den sover (sleep)
			System.out.println("Djuret blev väckt");
		}
	}

	// Klassens run-metod. Det är denna kod som körs när vi startar en ny tråd.
	public void run()
	{
		for (int i = 0; i < 10; i++)
		{
			// Anropar sov för att djuret ska "vila" en stund
			sov();

			// Skriver ut djurets läte
			System.out.println(tal + " " + (i+1) + " från " + typ);
		}
	}
}