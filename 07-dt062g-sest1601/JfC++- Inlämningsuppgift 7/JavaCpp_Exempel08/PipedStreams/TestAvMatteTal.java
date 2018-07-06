/**
 * @author Robert Jonsson, ITM �stersund
 * @version 2.0
 * @file Ex03_10 - TestAvMatteTal.java
 */

import java.io.*;

public class TestAvMatteTal
{
	public static void main(String[] args)
	{
		try
		{
			// Skapar de str�mmar som beh�vs
			PipedWriter utTr�d1 = new PipedWriter();
			PipedReader inTr�d2 = new PipedReader(utTr�d1);
			PipedWriter utTr�d2 = new PipedWriter();
			PipedReader inTr�d1 = new PipedReader(utTr�d2);

			// Skapar tr�dar
			Talgenerator generator = new Talgenerator(utTr�d1, inTr�d1);
			Talber�knare ber�knare = new Talber�knare(utTr�d2, inTr�d2);

			// Startar tr�darna
			generator.start();
			ber�knare.start();

		}
		catch (IOException fel)
		{
			System.out.println("Kunde inte skapa str�mmar...");
			fel.printStackTrace();
		}
	}
}
