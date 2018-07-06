/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_10 - TestAvDjur.java
 */

public class TestAvDjur
{
	public static void main(String[] args)
	{
		// Skriver ut att main börjar
		System.out.println("TestAvDjur.main() start");

		// Skapar två djur-objekt (trådar)
		Djur ko = new Djur("Ko", "muuu");
		Djur katt = new Djur("Katt", "mjau");

		// Anropar metod för att få djruen att prata
		ko.prata();
		katt.prata();

		// Skriver ut att main slutar
		System.out.println("TestAvDjur.main() slut\n");

		// Observera att programmet inte avslutas trots att main är slut
		// långt innan varje djur har pratat klart. Ett program avsultas
		// först när programmets alla trådar är fördiga.
	}
}