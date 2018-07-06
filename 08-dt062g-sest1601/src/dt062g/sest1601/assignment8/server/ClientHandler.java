/**
* <h1>Assignment 8 - ClientHandler.java</h1>
* <p>Helps the server to communicate with client, send and recive files, communicates with commmands.
* </p>
*
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2018-01-12
*/

package dt062g.sest1601.assignment8.server;
import java.net.*;
import java.nio.file.Files;
import java.io.*;


public class ClientHandler extends Thread {
    // Communication socket
    private Socket socket;

    // Client address
    private String address;
    private int port;

    // Streams
    private static DataInputStream instream = null;
    private static DataOutputStream outstream = null;

    // Constructor
    public ClientHandler(Socket socket) {
        this.socket = socket;
        port = socket.getPort();
        address = socket.getInetAddress().getHostAddress();
    }

    public void commandIssued (String command) { // Prints issued command
        System.out.println("Command " + command + " recieved from " + address + ":" + port );
    }

    public void disconnect() {  // Closes stream and socket.
        try {
            instream.close();
            outstream.close();
            socket.close();
        } catch (IOException ioe) {
            System.err.println(address + port + " Could not close streams/sockets");
        }
    }

    public void run() {  // Main function which recives commands from client.
        String commando;
        
        try { // Opens needed streams depending on command
            instream = new DataInputStream(socket.getInputStream());
            commando = instream.readUTF();

            if (commando.equals("list") || commando.equals("loadfile")) {
                outstream = new DataOutputStream(socket.getOutputStream());
            }            
        } catch (IOException ioe) {
            System.out.println(address + ":" + port + ": Could not create streams");
            disconnect();
            return;
        }

        // List commando was sent
        if (commando.equals("list")) {
            commandIssued("list");
            
            File folder = new File("src" + File.separator + "xml"); // create path to server folder.
            
            File[] listOfFiles = folder.listFiles(new FilenameFilter() {  // Sort out all non xml files
                public boolean accept(File dir, String fileName) {
                    return fileName.toLowerCase().endsWith(".xml");
                }
            });
            
            String[] fileNames = new String[listOfFiles.length]; 
            
            for (int i = 0; i < listOfFiles.length; i++) {  // Add all filenames to stringarray
                fileNames[i] = listOfFiles[i].getName();
            }

            try {
                System.out.println("Sending list of files to " + address + ":" + port);
                ObjectOutputStream objectstream = new ObjectOutputStream(socket.getOutputStream());
                objectstream.flush();
                objectstream.writeObject(fileNames);  // Sends stringarray to client
                
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            disconnect();
        }

        // Loadfile command was recived
        else if (commando.equals("loadfile")) {
            commandIssued("loadfile");
            
            try {
                String fileName = instream.readUTF();  //reads what file to load
                File file = new File("src" + File.separator + "xml" + File.separator + fileName); // Creates path
                byte[] fileBytes = Files.readAllBytes(file.toPath()); // Reads file from path to bytearray
                outstream.writeInt(fileBytes.length); // Send length of bytearray to client
                outstream.flush();
                outstream.write(fileBytes);     // Send filearray to client
                outstream.flush();
                disconnect();
            } catch (Exception e) {
                disconnect();
                System.err.println(e.getMessage());
            }

        }
            // Saveas command recieved
            else if (commando.equals("saveAs")) {
            commandIssued("saveAs");
            
            try {
                String saveFileAs = instream.readUTF();  // Read filename to save as
                int length = instream.readInt();         // Read length of byteArray
                byte[] recievedBytes = new byte[length];  
                instream.readFully(recievedBytes, 0, length); // Recives bytes
                File filePath = new File("src" + File.separator + "xml" + File.separator + saveFileAs); // Creates new file
                FileOutputStream fos = new FileOutputStream(filePath); // Creates Fileoutputstream
                fos.write(recievedBytes); // Writes file
                fos.close();
                disconnect();
            } catch (Exception e) {
                disconnect();
                System.err.println(e.getMessage());
            }
        }
    } 
}