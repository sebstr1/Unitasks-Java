
/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM �stersund
 * @version 1.0
 * @file Ex04_04 - SimpleRSSParser.java
 */

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class SimpleRSSParser {
	// Inneh�llet f�r inte �ndras
	private final String content;

	// Konstruktor
	public SimpleRSSParser(String content) {
		this.content = content;
	}

	// Returnera titeln p� nyhetsfl�det
	public String getTitle() {
		String s = getTextBetween("<title>", "</title>");

		if (s.length() == 0)
			s = "Simple RSS reader";

		return removeCDATA(s);
	}

	// Returnerar beskrivningen av nyhetsfl�det
	public String getDescription() {
		String s = getTextBetween("<description>", "</description>");

		if (s.length() == 0)
			s = "Senaste nytt";

		return removeCDATA(s);
	}

	// Returnerar en eventuell bild
	public ImageIcon getImage() {
		ImageIcon icon = null;

		// Letar f�rst efter texten mellan taggarna image
		// d�refter plockar vi URL till bilden mellan taggarna url
		String s = getTextBetween("<image>", "</image>");
		s = getTextBetween("<url>", "</url>");

		if (s.length() == 0)
			return null;

		try {
			// Skapa ett URL-objekt
			URL imageURL = new URL(s);

			// Skapar en ImageIcon fr�n URLn
			icon = new ImageIcon(imageURL);
		} catch (MalformedURLException urlFel) {
		}

		return icon;
	}

	public String getNews() {
		StringBuffer news = new StringBuffer();

		// Varje nyhet ligger i separata <item>-taggar. Vi skapar d�rf�r
		// en ny delstr�ng av content med b�rjan vid f�rsta taggen <item>
		String tmp = content.substring(content.indexOf("<item>"));

		String title;
		while ((title = getTextBetween(tmp, "<title>", "</title>")) != "") {
			// Titeln f�r en nyhet ligger mellan taggarna <title>. S� l�nge som
			// ovan returnerar n�got annat �n en tom str�ng finns fler nyheter

			news.append(removeCDATA(title) + "<br>");

			// Tar reda p� var denna nyhet (item) slutar
			int index = tmp.indexOf("</item>");

			// Skapar en delstr�ng av kvarvarande inneh�ll med
			// b�rjan fr�n d�r denna nyhet slutar. Adderar ett eftersom
			// </item> annars ligger kvar i den nya str�ngen vilket g�r
			// att samma nyhet (title) �ven den finns kvar i str�ngen.
			tmp = tmp.substring(index + 1);
		}

		return news.toString();
	}

	// Kontrollerar om texten ligger i en CDATA-sektion och tar bort den i s�
	// fall
	private String removeCDATA(String text) {
		// Vissa RSS-fl�den l�gger sin text i CDATA-sektioner.
		// Vi kontrollerar d�rf�r om s� �r fallet f�r denna

		String cdata = getTextBetween(text, "CDATA[", "]");
		return cdata.length() > 0 ? cdata : text;
	}

	// Returnera en delstr�ng fr�n inneh�llet (content)
	private String getTextBetween(String start, String end) {
		return getTextBetween(content, start, end);
	}

	// Returnerar en delstr�ng fr�n text som finns mellan str�ngarna start och
	// end
	private String getTextBetween(String text, String start, String end) {
		// Om n�gon av str�ngarna refererar till null returnera tomt
		if (start == null || end == null)
			return "";

		// Tar reda p� vilka index str�ngarna start och end finns p�
		int startindex = text.indexOf(start);
		int endindex = text.indexOf(end, startindex);

		// Om n�got av index �r -1 eller om slutindex �r mindre �n starindex
		// returnera tomt
		if (startindex < 0 || endindex < 0 || endindex < startindex)
			return "";

		// Returnera str�ngen mellan index startindex och endindex
		return text.substring(startindex + start.length(), endindex);
	}
}