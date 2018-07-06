
/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM Östersund
 * @version 1.0
 * @file Ex04_05 - SimpleRSS.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class SimpleRSS extends JFrame implements ActionListener {
	// Komponenter m.m.
	private URL url;
	private SimpleRSSParser parser;
	private JLabel description;
	private JLabel status;
	private JButton update;
	private JEditorPane text;
	private JScrollPane scroll;
	private JMenuBar menu;
	private JMenu rss;

	// Rubrik och address till nyhetsflöden
	private final String[][] rssFeeds= new String[][] {
			{"IDG", "http://www.idg.se/tjanster/rss/rss.xml"},
			{"Ny Teknik", "http://www.nyteknik.se/rss.xml"},
			{"DN - Nyheter", "http://www.dn.se/toppnyheter-rss"},
			{"DN - Ekonomi", "http://www.dn.se/ekonomi-rss"},
			{"DN - Sport", "http://www.dn.se/sport-rss"},
			{"Expressen - Nyheter", "http://expressen.se/rss/nyheter"},
			{"Expressen - Nöje", "http://expressen.se/rss/noje"},
			{"Expressen - Sport", "http://expressen.se/rss/sport"},
			{"Swedroid", "http://feeds.feedburner.com/swedroid-se"}
		};

	// Menyalternativ för nyhetsflöden
	private JMenuItem[] menuItems;
	private JMenuItem quitMenuItem;

	public SimpleRSS() {
		// Sätter titel på fönstret
		setTitle("Simple RSS Reader");

		// Sätter storlek och placering av fönstret
		setSize(400, 480);
		setLocationRelativeTo(null);

		setIconImage(new ImageIcon("15px-Feed-icon.png").getImage());

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Skapar menyn
		menu = new JMenuBar();
		rss = new JMenu("Nyhetsflöden");
		rss.setMnemonic(KeyEvent.VK_N);
		menu.add(rss);

		// Skapar menyalternativen för nyhetsflöden
		menuItems = new JMenuItem[rssFeeds.length];

		for(int i = 0; i < rssFeeds.length; i++) {
			menuItems[i] = new JMenuItem(rssFeeds[i][0].toString());
			menuItems[i].addActionListener(this);
			rss.add(menuItems[i]);
		}

		// Separera nyhetsflöden från menyalternativet avsluta
		rss.addSeparator();
		quitMenuItem = new JMenuItem("Avsluta");
		quitMenuItem.addActionListener(this);
		rss.add(quitMenuItem);

		setJMenuBar(menu);

		// Skapar JButton för uppdatering
		// Sätter border painted och content area filled till false
		// så att endast bilden från RSS-flödet visas
		update = new JButton();
		update.setToolTipText("Klicka för att uppdatera nyheterna");
		update.addActionListener(this);
		update.setBorderPainted(false);
		update.setContentAreaFilled(false);

		// Skapar JLabel för beskrivning
		description = new JLabel();

		// Panel i vilken vi lägger knapp och beskrivning
		JPanel north = new JPanel();
		north.add(update);
		north.add(description);

		// Skapar JEditorPane. Anledningen till att vi använder
		// en JEditorPane i stället för en JTextArea är att
		// JEditorPane kan visa html-kod.
		text = new JEditorPane();

		// Sätter vilken typ av dokument som ska visas. Detta gör
		// så att html-koder som &#246; (bokstaven ö) och <br> översätts korrekt
		text.setContentType("text/html");
		text.setEditable(false);
		scroll = new JScrollPane(text);

		// Status
		status = new JLabel("Välj nyhetsflöde från menyn");

		add(north, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(status, BorderLayout.SOUTH);

		// Visar fönstret
		setVisible(true);
	}

	// ***************************************************************
	// Ta hand om ActionEvent från knapp eller meny
	// ***************************************************************
	public void actionPerformed(ActionEvent ae) {
		// Kontrollera vilket menyalternativ vi valt,
		// och skapa en URL till aktuellt nyhetsflöde
		for (int i = 0; i < menuItems.length; i++) {
			if (ae.getSource() == menuItems[i]) {
				createURL(rssFeeds[i][1]);
			}

		}

		// Ska vi avsluta?
		if (ae.getSource() == quitMenuItem) {
			System.exit(0);
		}

		// Oavsett om vi valt en meny eller inte (tryckt på uppdatera)
		// skapar vi en parser och uppdaterar innehållet
		createParser();
		update();
	}

	// ***************************************************************
	// Skapar en ny URL till valt nyhetsflöde
	// ***************************************************************
	private void createURL(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException fel) {
			text.setText("<b>Kunde inte skapa URL:</b>" + fel.toString());
		}
	}

	// ***************************************************************
	// Läser innehållet från URLn och sparar i en sträng. Skapar sen
	// en parser av strängen.
	// ***************************************************************
	private void createParser() {
		// Nollställer den gamla parser
		parser = null;

		// Ta reda på vilken charset filen vi läser ifrån använder
		// Detta är viktigt så att när vi läser filens innehåll
		// anger korrekt charset. Blandar vi ihop dessa kan tecken som
		// åäö m.fl. visas på felaktigt sätt.
		String charset = null;
		try {
			URLConnection uc = url.openConnection();
			String contentType = uc.getContentType(); // contenttype;charset=charset
			charset = contentType.substring(contentType.lastIndexOf('=') + 1);
		} catch (IOException e) {
			text.setText("<b>Kunde inte avgöra charset:</b><br>"
					+ e.toString());
			return;
		}

		// Läs innehållet till en sträng och skapa en parser
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), charset));
			StringBuffer sb = new StringBuffer();
			String s;

			while ((s = in.readLine()) != null) {
				sb.append(s);
			}

			in.close();

			parser = new SimpleRSSParser(sb.toString());
		} catch (IOException e) {
			text.setText("<b>Fel vid läsning från URL:</b><br>"
					+ e.toString());
		}
	}

	// ***************************************************************
	// Uppdaterar fönstret (fönstrets titel, bild, beskrivning m.m.
	// med data från aktuell parser
	// ***************************************************************
	private void update() {
		// Finns ingen parser, uppdatera inte
		if (parser == null) return;

		setTitle(parser.getTitle());
		description.setText(parser.getDescription());
		update.setIcon(parser.getImage());

		text.setText(parser.getNews());

		// Sätter status till när uppdateringen gjordes
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		status.setText("Senast uppdaterad: "
				+ df.format(new Date(System.currentTimeMillis())));

	}

	public static void main(String[] args) {
		new SimpleRSS();
	}
}