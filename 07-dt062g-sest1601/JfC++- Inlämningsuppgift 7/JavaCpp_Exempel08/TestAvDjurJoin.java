/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_13 - TestAvDjurJoin.java
 */

public class TestAvDjurJoin
{
	public static void main(String[] args)
	{
		// Skriver ut att main börjar
		System.out.println("TestAvDjurJoin.main() start");

		// Skapar två djur-objekt (trådar)
		Djur ko = new Djur("Ko", "muuu");
		Djur katt = new Djur("Katt", "mjau");

		// Anropar metod för att få djruen att prata
		ko.prata();
		katt.prata();

		// Vi vill inte att huvudtråden (main) ska fortsätta
		// förrän båda trådarna ovan är klar
		try
		{
			ko.join();
			katt.join();
		 }
		 catch (InterruptedException ie) {}

		// Skriver ut att main slutar
		System.out.println("TestAvDjurJoin.main() slut");

		// Eftersom vi nu anropar metoden join() på de båda trådarna (katt och ko)
		// kommer exekveringen av main inte att fortsätta förrän båda trådarna är
		// klara med sina aktiviteter.
	}
}