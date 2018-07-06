/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_13 - TestAvDjurJoin.java
 */

public class TestAvDjurJoin
{
	public static void main(String[] args)
	{
		// Skriver ut att main b�rjar
		System.out.println("TestAvDjurJoin.main() start");

		// Skapar tv� djur-objekt (tr�dar)
		Djur ko = new Djur("Ko", "muuu");
		Djur katt = new Djur("Katt", "mjau");

		// Anropar metod f�r att f� djruen att prata
		ko.prata();
		katt.prata();

		// Vi vill inte att huvudtr�den (main) ska forts�tta
		// f�rr�n b�da tr�darna ovan �r klar
		try
		{
			ko.join();
			katt.join();
		 }
		 catch (InterruptedException ie) {}

		// Skriver ut att main slutar
		System.out.println("TestAvDjurJoin.main() slut");

		// Eftersom vi nu anropar metoden join() p� de b�da tr�darna (katt och ko)
		// kommer exekveringen av main inte att forts�tta f�rr�n b�da tr�darna �r
		// klara med sina aktiviteter.
	}
}