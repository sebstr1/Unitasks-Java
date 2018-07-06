/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_18 - TestAvTelePrinter.java
 */

public class TestAvTelePrinter
{
	public static void main(String[] args)
	{
		// Skapar en teleprinter
		TelePrinter printer = new TelePrinter();

		// Skapar två PrintThread som använder samma teleprinter för utskriften
		// men som två olika strängar som ska skrivas ut
		PrintThread pt1 = new PrintThread(printer, "abcdefghijklmnopqrstauvxzåäö");
		PrintThread pt2 = new PrintThread(printer, "0123456789012345678901234567");

		// Startar de båda PrintThread
		pt1.start();
		pt2.start();

		// Använder direkt det gemensamma TelePrinter-objektet så totalt
		// är det tre trådar som samtidigt vill göra en utskrift med TelePrinter
		printer.print("Detta är main som skriver");
	}
}

// Utskriften på skärmen kommer att bli väldigt rörigt då tre olika texter skrivs ut samtidigt
// Ändra nu i klassen TelePrinter så att metoden print deklareras som synchronized