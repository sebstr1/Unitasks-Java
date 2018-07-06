/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM �stersund
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

			// Erh�ller en InputStream f�r att l�sa inneh�llet URLn refererar till
			InputStream is = java.openStream();

			// L�s data fr�n str�mmen och skriv ut p� sk�rmen
			int c;
			while ((c = is.read()) != -1)
			{
				System.out.print((char)c);
			}

			// St�nger str�mmen
			is.close();

			// Vi kan �ven koppla p� en BufferedReader och l�sa radvis med readLine()
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
			// Fel vid l�sning fr�n URL
			io.printStackTrace();
		}

    }
}