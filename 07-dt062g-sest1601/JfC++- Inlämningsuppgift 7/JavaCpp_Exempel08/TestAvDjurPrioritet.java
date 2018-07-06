/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_15 - TestAvDjurPrioritet.java
 */

public class TestAvDjurPrioritet
{
	public static void main(String[] args)
	{
		// Skriver ut att main börjar
		System.out.println("TestAvDjurPrioritet.main() start");

		// Skapar två djur-objekt (trådar) av det nya objektet Djur2
		Djur2 ko = new Djur2("Ko", "muuu");
		Djur2 katt = new Djur2("Katt", "mjau");
		Djur2 hund = new Djur2("Hund", "voff");

		// Vi ger kon minsta prioritet och hunden den högsta
		ko.setPriority(Thread.MIN_PRIORITY);
		hund.setPriority(Thread.MAX_PRIORITY);
		// katt har prioritet NORM_PRIORITY

		// Anropar metod för att få djruen att prata
		ko.prata();
		katt.prata();
		hund.prata();

		// Vi vill inte att huvudtråden (main) ska fortsätta
		// förrän alla trådarna ovan är klar
		try
		{
			ko.join();
			katt.join();
			hund.join();
		 }
		 catch (InterruptedException ie) {}

		// Skriver ut att main slutar
		System.out.println("TestAvDjurPrioritet.main() slut");

		// Eftersom hunden har getss högsta prioriteten kommer den att prata klart
		// innan något annat djur börjat prata, även fast hunden är den som börjar
		// prata sist (hund.prata() sker sist). katten har inte getts någon prioritet
		// så den har default NORM_PRIORITY och är därför det djur som pratar som nr 2.
		// Tillsist har vi kon som pratar sist eftersom den getts MIN_PRIORITY.
	}
}