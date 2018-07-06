
/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM �stersund
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

	// Rubrik och address till nyhetsfl�den
	private final String[][] rssFeeds= new String[][] {
			{"IDG", "http://www.idg.se/tjanster/rss/rss.xml"},
			{"Ny Teknik", "http://www.nyteknik.se/rss.xml"},
			{"DN - Nyheter", "http://www.dn.se/toppnyheter-rss"},
			{"DN - Ekonomi", "http://www.dn.se/ekonomi-rss"},
			{"DN - Sport", "http://www.dn.se/sport-rss"},
			{"Expressen - Nyheter", "http://expressen.se/rss/nyheter"},
			{"Expressen - N�je", "http://expressen.se/rss/noje"},
			{"Expressen - Sport", "http://expressen.se/rss/sport"},
			{"Swedroid", "http://feeds.feedburner.com/swedroid-se"}
		};

	// Menyalternativ f�r nyhetsfl�den
	private JMenuItem[] menuItems;
	private JMenuItem quitMenuItem;

	public SimpleRSS() {
		// S�tter titel p� f�nstret
		setTitle("Simple RSS Reader");

		// S�tter storlek och placering av f�nstret
		setSize(400, 480);
		setLocationRelativeTo(null);

		setIconImage(new ImageIcon("15px-Feed-icon.png").getImage());

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Skapar menyn
		menu = new JMenuBar();
		rss = new JMenu("Nyhetsfl�den");
		rss.setMnemonic(KeyEvent.VK_N);
		menu.add(rss);

		// Skapar menyalternativen f�r nyhetsfl�den
		menuItems = new JMenuItem[rssFeeds.length];

		for(int i = 0; i < rssFeeds.length; i++) {
			menuItems[i] = new JMenuItem(rssFeeds[i][0].toString());
			menuItems[i].addActionListener(this);
			rss.add(menuItems[i]);
		}

		// Separera nyhetsfl�den fr�n menyalternativet avsluta
		rss.addSeparator();
		quitMenuItem = new JMenuItem("Avsluta");
		quitMenuItem.addActionListener(this);
		rss.add(quitMenuItem);

		setJMenuBar(menu);

		// Skapar JButton f�r uppdatering
		// S�tter border painted och content area filled till false
		// s� att endast bilden fr�n RSS-fl�det visas
		update = new JButton();
		update.setToolTipText("Klicka f�r att uppdatera nyheterna");
		update.addActionListener(this);
		update.setBorderPainted(false);
		update.setContentAreaFilled(false);

		// Skapar JLabel f�r beskrivning
		description = new JLabel();

		// Panel i vilken vi l�gger knapp och beskrivning
		JPanel north = new JPanel();
		north.add(update);
		north.add(description);

		// Skapar JEditorPane. Anledningen till att vi anv�nder
		// en JEditorPane i st�llet f�r en JTextArea �r att
		// JEditorPane kan visa html-kod.
		text = new JEditorPane();

		// S�tter vilken typ av dokument som ska visas. Detta g�r
		// s� att html-koder som &#246; (bokstaven �) och <br> �vers�tts korrekt
		text.setContentType("text/html");
		text.setEditable(false);
		scroll = new JScrollPane(text);

		// Status
		status = new JLabel("V�lj nyhetsfl�de fr�n menyn");

		add(north, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(status, BorderLayout.SOUTH);

		// Visar f�nstret
		setVisible(true);
	}

	// ***************************************************************
	// Ta hand om ActionEvent fr�n knapp eller meny
	// ***************************************************************
	public void actionPerformed(ActionEvent ae) {
		// Kontrollera vilket menyalternativ vi valt,
		// och skapa en URL till aktuellt nyhetsfl�de
		for (int i = 0; i < menuItems.length; i++) {
			if (ae.getSource() == menuItems[i]) {
				createURL(rssFeeds[i][1]);
			}

		}

		// Ska vi avsluta?
		if (ae.getSource() == quitMenuItem) {
			System.exit(0);
		}

		// Oavsett om vi valt en meny eller inte (tryckt p� uppdatera)
		// skapar vi en parser och uppdaterar inneh�llet
		createParser();
		update();
	}

	// ***************************************************************
	// Skapar en ny URL till valt nyhetsfl�de
	// ***************************************************************
	private void createURL(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException fel) {
			text.setText("<b>Kunde inte skapa URL:</b>" + fel.toString());
		}
	}

	// ***************************************************************
	// L�ser inneh�llet fr�n URLn och sparar i en str�ng. Skapar sen
	// en parser av str�ngen.
	// ***************************************************************
	private void createParser() {
		// Nollst�ller den gamla parser
		parser = null;

		// Ta reda p� vilken charset filen vi l�ser ifr�n anv�nder
		// Detta �r viktigt s� att n�r vi l�ser filens inneh�ll
		// anger korrekt charset. Blandar vi ihop dessa kan tecken som
		// ��� m.fl. visas p� felaktigt s�tt.
		String charset = null;
		try {
			URLConnection uc = url.openConnection();
			String contentType = uc.getContentType(); // contenttype;charset=charset
			charset = contentType.substring(contentType.lastIndexOf('=') + 1);
		} catch (IOException e) {
			text.setText("<b>Kunde inte avg�ra charset:</b><br>"
					+ e.toString());
			return;
		}

		// L�s inneh�llet till en str�ng och skapa en parser
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
			text.setText("<b>Fel vid l�sning fr�n URL:</b><br>"
					+ e.toString());
		}
	}

	// ***************************************************************
	// Uppdaterar f�nstret (f�nstrets titel, bild, beskrivning m.m.
	// med data fr�n aktuell parser
	// ***************************************************************
	private void update() {
		// Finns ingen parser, uppdatera inte
		if (parser == null) return;

		setTitle(parser.getTitle());
		description.setText(parser.getDescription());
		update.setIcon(parser.getImage());

		text.setText(parser.getNews());

		// S�tter status till n�r uppdateringen gjordes
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		status.setText("Senast uppdaterad: "
				+ df.format(new Date(System.currentTimeMillis())));

	}

	public static void main(String[] args) {
		new SimpleRSS();
	}
}