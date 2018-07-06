
/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM Östersund
 * @version 1.0
 * @file Ex04_04 - SimpleRSSParser.java
 */

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class SimpleRSSParser {
	// Innehållet får inte ändras
	private final String content;

	// Konstruktor
	public SimpleRSSParser(String content) {
		this.content = content;
	}

	// Returnera titeln på nyhetsflödet
	public String getTitle() {
		String s = getTextBetween("<title>", "</title>");

		if (s.length() == 0)
			s = "Simple RSS reader";

		return removeCDATA(s);
	}

	// Returnerar beskrivningen av nyhetsflödet
	public String getDescription() {
		String s = getTextBetween("<description>", "</description>");

		if (s.length() == 0)
			s = "Senaste nytt";

		return removeCDATA(s);
	}

	// Returnerar en eventuell bild
	public ImageIcon getImage() {
		ImageIcon icon = null;

		// Letar först efter texten mellan taggarna image
		// därefter plockar vi URL till bilden mellan taggarna url
		String s = getTextBetween("<image>", "</image>");
		s = getTextBetween("<url>", "</url>");

		if (s.length() == 0)
			return null;

		try {
			// Skapa ett URL-objekt
			URL imageURL = new URL(s);

			// Skapar en ImageIcon från URLn
			icon = new ImageIcon(imageURL);
		} catch (MalformedURLException urlFel) {
		}

		return icon;
	}

	public String getNews() {
		StringBuffer news = new StringBuffer();

		// Varje nyhet ligger i separata <item>-taggar. Vi skapar därför
		// en ny delsträng av content med början vid första taggen <item>
		String tmp = content.substring(content.indexOf("<item>"));

		String title;
		while ((title = getTextBetween(tmp, "<title>", "</title>")) != "") {
			// Titeln för en nyhet ligger mellan taggarna <title>. Så länge som
			// ovan returnerar något annat än en tom sträng finns fler nyheter

			news.append(removeCDATA(title) + "<br>");

			// Tar reda på var denna nyhet (item) slutar
			int index = tmp.indexOf("</item>");

			// Skapar en delsträng av kvarvarande innehåll med
			// början från där denna nyhet slutar. Adderar ett eftersom
			// </item> annars ligger kvar i den nya strängen vilket gör
			// att samma nyhet (title) även den finns kvar i strängen.
			tmp = tmp.substring(index + 1);
		}

		return news.toString();
	}

	// Kontrollerar om texten ligger i en CDATA-sektion och tar bort den i så
	// fall
	private String removeCDATA(String text) {
		// Vissa RSS-flöden lägger sin text i CDATA-sektioner.
		// Vi kontrollerar därför om så är fallet för denna

		String cdata = getTextBetween(text, "CDATA[", "]");
		return cdata.length() > 0 ? cdata : text;
	}

	// Returnera en delsträng från innehållet (content)
	private String getTextBetween(String start, String end) {
		return getTextBetween(content, start, end);
	}

	// Returnerar en delsträng från text som finns mellan strängarna start och
	// end
	private String getTextBetween(String text, String start, String end) {
		// Om någon av strängarna refererar till null returnera tomt
		if (start == null || end == null)
			return "";

		// Tar reda på vilka index strängarna start och end finns på
		int startindex = text.indexOf(start);
		int endindex = text.indexOf(end, startindex);

		// Om något av index är -1 eller om slutindex är mindre än starindex
		// returnera tomt
		if (startindex < 0 || endindex < 0 || endindex < startindex)
			return "";

		// Returnera strängen mellan index startindex och endindex
		return text.substring(startindex + start.length(), endindex);
	}
}