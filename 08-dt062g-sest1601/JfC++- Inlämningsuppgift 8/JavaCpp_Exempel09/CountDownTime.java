/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM Östersund
 * @version 1.0
 * @file Ex05_09 - CountDownTime.java
 */

public class CountDownTime implements java.io.Serializable
{
	// Den tid klienten vill att serverna ska räkna ner till (06-12-24 15:00)
	// eller hur lång tid som återstår till slutdatum (04:59:59)
	private String time;

	// Vad är det som sker när slutdatum är nådd (Kalle Anka på julafton)
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