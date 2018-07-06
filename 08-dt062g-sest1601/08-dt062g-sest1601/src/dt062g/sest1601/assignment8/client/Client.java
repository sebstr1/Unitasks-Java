/**
* <h1>Assignment 8 - Client.java</h1>
* <p>Client that can connect to server.
* </p>
*
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2018-01-12
*/

package dt062g.sest1601.assignment8.client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.nio.file.Files;


public class Client {

    public static final int DEFAULT_PORT = 10000;
	public static final String DEFAULT_ADDRESS = "localhost";
	private String serverAddress;
    private int serverPort;

    private static Socket socket = null;
    private static DataInputStream instream = null;
    private static DataOutputStream outstream = null;
    private static ObjectInputStream objstream = null; 

    public Client() {  // Default constructor
        this("localhost", 10000);
    }
    
    public Client(String ip, int port) {  // Default constructor
        serverAddress = ip;
        serverPort = port;
    }

    public boolean connect(String command) { // Tries to connect to server

        // Creates socket and streams according to command (which decides what streams we need)
        try {
            if (socket == null) {
            socket = new Socket(serverAddress, serverPort);
            socket.setSoTimeout(10000);
            outstream = new DataOutputStream(socket.getOutputStream());
            }

            outstream.writeUTF(command); // Sends command to clienthandler
            outstream.flush();
            if (command.equals("list")) {
                objstream = new ObjectInputStream(socket.getInputStream());
            } 
            else if (command.equals("loadfile")) {
                instream = new DataInputStream(socket.getInputStream());
            }
            return true;
        } catch (IOException ioe) {
            System.out.println("Could not connect to server:\n" + ioe);
            disconnect(command);
            return false;
        }

    }

    // Close Streams according to command (Which decided what streams are opened)
    public void disconnect(String command) {
        try {
            outstream.close();
            outstream = null;
            socket.close();
            socket = null;

            if (command.equals("list")) {
                objstream.close();
                objstream = null;
            }

            else if (command.equals("loadfile")) {
                instream.close();
                instream = null;
            }
            
        } catch (IOException iofel) {
        }

    }

    // Gets a stringArray with files that are on the server
	public String[] getFilenamesFromServer() {
        if (connect("list")) {

            String[] fileNames;
            try {
                fileNames = (String[]) objstream.readObject(); // Read object from objectstream
                disconnect("list");
                return fileNames; // Return the filearray
                
            } catch (IOException ioe) {
                System.out.println(serverAddress + ": Could not read filenames from server");
                System.out.println(serverAddress + ": Disconnecting");
                disconnect("list");
                return null;
            }
            catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        disconnect("list");
        return null; // Returns 
    }
        else {System.out.println("Could not connect to server");}
		return null;
	}
    
    public String getFileFromServer(String fileName) {
        if(connect("loadfile")) { // Tries to connect

            try {
                outstream.writeUTF(fileName); // Sends filename to load
                outstream.flush();
                int length = instream.readInt(); // Recives length of byteArray
                
                if (length == 0) {  // Disconnects if size is 0
                    disconnect("loadfile");
                    return "";
                }
                
                else { // Size was not 0 so we got a file. 

                    byte[] recievedBytes = new byte[length];
                    instream.readFully(recievedBytes, 0, length); // Recives bytes
                    File filePath = new File("src" + File.separator + "clientHDD" + File.separator + fileName); // Creates new file
                    FileOutputStream fos = new FileOutputStream(filePath); // Creates Fileoutputstream
                    fos.write(recievedBytes); // Writes file
                    fos.close();
                    disconnect("loadfile");
                    return (filePath.getAbsolutePath()); // Returns filePath
                } 

            } catch (IOException ioe) {
                ioe.printStackTrace();
                System.out.println(serverAddress + ": Could not get " + fileName + " from server");
                System.out.println(serverAddress + ": Disconnecting");
                disconnect("loadfile");
                return null;
            }
        }
        else {
            System.out.println("Cound not connect to server");
            return fileName;
        }
    }

    public boolean saveAsFileToServer(String clientFilename, String serverFilename) { // Saves file on Server
       
        if (connect("saveAs")) {
            try {

                if (!(serverFilename.endsWith(".xml"))) {
                    serverFilename = serverFilename + ".xml"; // Fixes so that filename we want to save ends with .xml
                }
                if (!(clientFilename.endsWith(".xml"))) {
                    clientFilename = clientFilename + ".xml"; // Fixes so that filename we want to save ends with .xml
                }

                outstream.writeUTF(serverFilename);  // Sends filename that server will use to save file.
                File file = new File("src" + File.separator + "clientHDD" + File.separator + clientFilename); // Creates path
                byte[] fileBytes = Files.readAllBytes(file.toPath());  // reads file to bytearray
                outstream.writeInt(fileBytes.length); // Sends length of bytearray
                outstream.flush();
                outstream.write(fileBytes);  // Sends ByteArray
                outstream.flush();
                disconnect("saveAs");
                return true;   // Returns true if all good
            } catch (Exception e) {
                disconnect("saveAs");
                System.err.println(e.getMessage());
                return false;
            }
        }
        else {
            return false;
        }
    }

    public void saveFileToServer(String clientFileName) {
        saveAsFileToServer(clientFileName, clientFileName);
    }
}