/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM �stersund
 * @version 1.0
 * @file Ex05_09 - CountDownTime.java
 */

public class CountDownTime implements java.io.Serializable
{
	// Den tid klienten vill att serverna ska r�kna ner till (06-12-24 15:00)
	// eller hur l�ng tid som �terst�r till slutdatum (04:59:59)
	private String time;

	// Vad �r det som sker n�r slutdatum �r n�dd (Kalle Anka p� julafton)
	private String event;

	// Konstruktor
	public CountDownTime(String time, String event)
	{
		this.time = time;
		this.event = event;
	}

	public String getTime()
	{
		return time;
	}

	public String getEvent()
	{
		return event;
	}

	public String toString()
	{
		return time + " kvar till " + event;
	}
}