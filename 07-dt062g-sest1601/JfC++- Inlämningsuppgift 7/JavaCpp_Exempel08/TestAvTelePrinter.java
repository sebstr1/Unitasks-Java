/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_18 - TestAvTelePrinter.java
 */

public class TestAvTelePrinter
{
	public static void main(String[] args)
	{
		// Skapar en teleprinter
		TelePrinter printer = new TelePrinter();

		// Skapar tv� PrintThread som anv�nder samma teleprinter f�r utskriften
		// men som tv� olika str�ngar som ska skrivas ut
		PrintThread pt1 = new PrintThread(printer, "abcdefghijklmnopqrstauvxz���");
		PrintThread pt2 = new PrintThread(printer, "0123456789012345678901234567");

		// Startar de b�da PrintThread
		pt1.start();
		pt2.start();

		// Anv�nder direkt det gemensamma TelePrinter-objektet s� totalt
		// �r det tre tr�dar som samtidigt vill g�ra en utskrift med TelePrinter
		printer.print("Detta �r main som skriver");
	}
}

// Utskriften p� sk�rmen kommer att bli v�ldigt r�rigt d� tre olika texter skrivs ut samtidigt
// �ndra nu i klassen TelePrinter s� att metoden print deklareras som synchronized