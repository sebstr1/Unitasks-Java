/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_22 - Talgenerator.java
 */

public class Talgenerator extends Thread
{
	// Instansvariabler
	private Mattetal tal;

	// Konstruktor
	public Talgenerator(Mattetal tal)
	{
		this.tal = tal;
	}

	// Klassens run-metod
	public void run()
	{
		// K�r s� l�nge som inte n�gon bett tr�dan att avbryta
		while (!isInterrupted())
		{
			// B�rja med att sova en stund (max 10 sekunder). Detta g�rs enbart
			// f�r att simulera att n�gon intensiv/l�ng ber�kning g�rs f�r att f� fram
			// ett nytt tal.
			try
			{
				this.sleep((int)(Math.random() * 10000));
			}
			catch (InterruptedException ie)
			{
				// Blir tr�dan avbryten s� avbryter vi while-loopen
				// med break.
				break;
			}

			// Vi slumpar fram nya v�rden f�r talet (0-10)
			int op1 = (int)(Math.random() * 11);
			int op2 = (int)(Math.random() * 11);

			// Slumpar vilken operator som ska anv�ndas
			String op = null;

			switch ((int)(Math.random() * 5))
			{
				case 0: op = "+"; break;
				case 1: op = "-"; break;
				case 2: op = "*"; break;
				case 3: op = "/"; break;
				default: op = "+"; break;
			}

			// Anropar metoden nyttTal f�r att s�tta det nya talet
			// Observera att om det befintliga talet i Mattetal inte
			// hunnit h�mtas s� kommer denna tr�d att f� v�nta (wait)
			tal.nyttTal(op1, op, op2);
		}
	}
}