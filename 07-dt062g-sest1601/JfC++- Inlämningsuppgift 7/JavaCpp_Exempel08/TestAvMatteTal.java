/**
 * @author Robert Jonsson, ITM �stersund
 * @version 1.0
 * @file Ex01_24 - TestAvMatteTal.java
 */

public class TestAvMatteTal
{
	public static void main(String[] args)
	{
		Mattetal tal = new Mattetal();
		Talgenerator generator = new Talgenerator(tal);
		Talber�knare ber�knare = new Talber�knare(tal);
		generator.start();
		ber�knare.start();
	}
}
