/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_15 - TestAvDjurPrioritet.java
 */

public class TestAvDjurPrioritet
{
	public static void main(String[] args)
	{
		// Skriver ut att main b�rjar
		System.out.println("TestAvDjurPrioritet.main() start");

		// Skapar tv� djur-objekt (tr�dar) av det nya objektet Djur2
		Djur2 ko = new Djur2("Ko", "muuu");
		Djur2 katt = new Djur2("Katt", "mjau");
		Djur2 hund = new Djur2("Hund", "voff");

		// Vi ger kon minsta prioritet och hunden den h�gsta
		ko.setPriority(Thread.MIN_PRIORITY);
		hund.setPriority(Thread.MAX_PRIORITY);
		// katt har prioritet NORM_PRIORITY

		// Anropar metod f�r att f� djruen att prata
		ko.prata();
		katt.prata();
		hund.prata();

		// Vi vill inte att huvudtr�den (main) ska forts�tta
		// f�rr�n alla tr�darna ovan �r klar
		try
		{
			ko.join();
			katt.join();
			hund.join();
		 }
		 catch (InterruptedException ie) {}

		// Skriver ut att main slutar
		System.out.println("TestAvDjurPrioritet.main() slut");

		// Eftersom hunden har getss h�gsta prioriteten kommer den att prata klart
		// innan n�got annat djur b�rjat prata, �ven fast hunden �r den som b�rjar
		// prata sist (hund.prata() sker sist). katten har inte getts n�gon prioritet
		// s� den har default NORM_PRIORITY och �r d�rf�r det djur som pratar som nr 2.
		// Tillsist har vi kon som pratar sist eftersom den getts MIN_PRIORITY.
	}
}