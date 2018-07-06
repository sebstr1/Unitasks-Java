/**
 * @author Robert Jonsson (robert.jonsson@miun.se), ITM Östersund
 * @version 1.0
 * @file Ex04_05 - TestAvInetAddress.java
 */

import java.net.*;

public class TestAvInetAddress
{

	public static void main(String[] args)
	{
		try
		{
			InetAddress idgIP1 = InetAddress.getByName("www.idg.se");
			InetAddress idgIP2 = InetAddress.getByName("213.132.123.119");
			InetAddress local = InetAddress.getLocalHost();

			System.out.println("idgIP1:");
			System.out.println("Host name:     " + idgIP1.getHostName());
			System.out.println("Host address:  " + idgIP1.getHostAddress());
			System.out.println("Is multicast:  " + idgIP1.isMulticastAddress());
			System.out.println("Is local site: " + idgIP1.isSiteLocalAddress());

			System.out.println("\nidgIP2:");
			System.out.println("Host name:     " + idgIP2.getHostName());
			System.out.println("Host address:  " + idgIP2.getHostAddress());
			System.out.println("Is multicast:  " + idgIP2.isMulticastAddress());
			System.out.println("Is local site: " + idgIP2.isSiteLocalAddress());

			System.out.println("\nlocal:");
			System.out.println("Host name:     " + local.getHostName());
			System.out.println("Host address:  " + local.getHostAddress());
			System.out.println("Is multicast:  " + idgIP1.isMulticastAddress());
			System.out.println("Is local site: " + local.isSiteLocalAddress());

			System.out.println("\nidgIP1 equals idgIP2: " + idgIP1.equals(idgIP2));
			System.out.println("idgIP1 equals local:  " + idgIP1.equals(local));

		}
		catch (UnknownHostException uhe)
		{
			// Kunde inte skapa InetAddress
			uhe.printStackTrace();
		}
    }
}