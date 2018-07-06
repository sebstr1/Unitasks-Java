/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_10 - TestAvDjur.java
 */

public class TestAvDjur
{
	public static void main(String[] args)
	{
		// Skriver ut att main b�rjar
		System.out.println("TestAvDjur.main() start");

		// Skapar tv� djur-objekt (tr�dar)
		Djur ko = new Djur("Ko", "muuu");
		Djur katt = new Djur("Katt", "mjau");

		// Anropar metod f�r att f� djruen att prata
		ko.prata();
		katt.prata();

		// Skriver ut att main slutar
		System.out.println("TestAvDjur.main() slut\n");

		// Observera att programmet inte avslutas trots att main �r slut
		// l�ngt innan varje djur har pratat klart. Ett program avsultas
		// f�rst n�r programmets alla tr�dar �r f�rdiga.
	}
}