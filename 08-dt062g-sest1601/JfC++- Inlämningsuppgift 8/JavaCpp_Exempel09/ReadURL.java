/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM Östersund
 * @version 1.0
 * @file Ex04_02 - ReadURL.java
 */

import java.net.*;
import java.io.*;

public class ReadURL
{

	public static void main(String[] args)
	{
		try
		{
			// Skapar URL
			URL java = new URL("http://www.java.com/sv/index.jsp");

			// Erhåller en InputStream för att läsa innehållet URLn refererar till
			InputStream is = java.openStream();

			// Läs data från strömmen och skriv ut på skärmen
			int c;
			while ((c = is.read()) != -1)
			{
				System.out.print((char)c);
			}

			// Stänger strömmen
			is.close();

			// Vi kan även koppla på en BufferedReader och läsa radvis med readLine()
			/*BufferedReader in = new BufferedReader(new InputStreamReader(java.openStream()));
			String s;

			while ((s = in.readLine()) != null)
			{
				System.out.println(s);
			}

			in.close();*/

		}
		catch (MalformedURLException fel)
		{
			// Fel vid skapande av URL
			fel.printStackTrace();
		}
		catch (IOException io)
		{
			// Fel vid läsning från URL
			io.printStackTrace();
		}

    }
}