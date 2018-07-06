/**
 * @author Robert Jonsson, ITM Östersund
 * @version 1.0
 * @file Ex01_21 - Mattetal.java
 */

public class Mattetal
{
	// Instansvariabler
	private double operand1;
	private double operand2;
	private String operator = null;

	// Metod för att sätta nytt mattetal att beräkna
	public synchronized void nyttTal(double op1, String op, double op2)
	{
		// Så länge som operator refererar till null så vet vi att det inte
		// finns något mattetal satt och vi kan då utan risk sätta ett nytt.
		// Om det finns ett mattetal redan satt så kommer operator att referera
		// till något annat än null. Vi kan då inte sätta nytt utan ber tråden
		// att vänta med wait. Detta görs i en while-loop så när tråden vaknar
		// till igen kontrolleras det först om det är ok att sätta nytt tal eller
		// om tråden måste vänta ytterligare ett tag
		while (operator != null)
		{
			try
			{
				// Det finns redan ett mattetal. Vänta tills att det befintliga talet har
				// hämtats innan vi sätter det nya.
				wait();

			}
			catch (InterruptedException ie) {}
		}

		// Det är ok att sätta nytt tal
		this.operand1 = op1;
		this.operator = op;
		this.operand2 = op2;

		// Väcker eventuella trådar som väntar på att hämta talet
		notify();

	}

	// Metod som returnerar aktuellt mattetal som en sträng
	public synchronized String hämtaTal()
	{
		// Så länge som operator refererar till null finns inget tal att hämta
		// Tråden som vill hämta tal måste då vänta genom att anrop till wait.
		while (operator == null)
		{
			try
			{
				System.out.print("Väntar på nytt tal");
				wait();
				System.out.println();
			}
			catch (InterruptedException ie) {}
		}

		// Formaterar den sträng som returneras och som innehåller talet
		// Vi sätter även operator att referera till null (motsvarar att inget tal längre är satt)
		String uttryck = Double.toString(operand1) + " " +  operator + " " + Double.toString(operand2);
		operator = null;

		// Väcker eventuella trådar som väntar på att få sätta nytt tal
		notify();

		// Returnerar talet
		return uttryck;
	}
}