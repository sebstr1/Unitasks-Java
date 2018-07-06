/**
* <h1>Assignment 8 - Server.java</h1>
* <p>Server that client can connect to
* </p>
*
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2018-01-12
*/

package dt062g.sest1601.assignment8.server;
import java.net.*;
import java.io.*;
import java.util.*;

public class Server {


    public static void main(String[] args) {
        
        ServerSocket srvSocket = null;
        Vector<Socket> connections = new Vector<Socket>();
        // ArrayList<Socket> connections = new ArrayList<Socket>();
        try {
            // If more than 0 args, set listenport to args[0] else port 10000
            if (args.length > 0) {
                srvSocket = new ServerSocket(Integer.parseInt(args[0]));
            } else {
                srvSocket = new ServerSocket(10000);
            }
            System.out.println("Server started! Listening on port" + srvSocket);
            System.out.print("Waiting for client... ");

     
            while (true) {
                try {
                    // Waits for clients
                    Socket s = srvSocket.accept();
                    connections.add(s);
                    // New clienthandler created as clients connect.
                    System.out.println("\nClient connected with address: " + s.getInetAddress().getHostAddress());
                    new ClientHandler(s).run();

                    // Close sockets
                    for (int i = 0; i < connections.size(); i++) {
                        if (connections.elementAt(i).isClosed()) {
                            System.out.println("\nClient " + connections.elementAt(i).getInetAddress().getHostAddress() + " disconnected!");
                            connections.elementAt(i).close();
                            connections.remove(i);

                        }

                    }


                } catch (IOException ioe) {
                    System.out.println("Error connecting to client:\n" + ioe);
                }
            }

           

           
        } catch (IOException iofel) {
            System.out.println("Could not listen on port: " + "10000 " + iofel.getMessage());
        }
    }
}