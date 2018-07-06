/**
* <h1>Assignment 8 - MainFrame.java</h1>
* <p>This class inherits JFrame and Implements ActionListener, MouseMotionListener, MouseListener.</p>
* <p>Creates a graphical interface for our painting program with a frame, menus, drawingarea and
* statusbar. Adds a bunch of eventlisteners that is being handled correctly when according to event
* triggered.</p>
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-12-11
*/

package dt062g.sest1601.assignment8;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import dt062g.sest1601.assignment8.client.Client;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.concurrent.ExecutionException;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingWorker;


public class MainFrame extends JFrame implements ActionListener, MouseMotionListener, MouseListener{
    private final String programName = "JPaintPro";
    private String drawingName = "";
    private String author = "";
    private JLabel mouseLocation;
    private JPanel selectedColorBox; 
    private DrawingPanel drawingArea;
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, serverMenu;
    private static final long serialVersionUID = 1;
    private static Client client;

    private JOptionPane serverFiles;
    private String[] availableFiles;
    
    
    MainFrame(Client c) {

        client = c;
        //Add regular components to the window, using the default BorderLayout.
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(Color.WHITE);

        //Create the menu bar.
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        

        //Build the file menu --------------------------------------------------------------------------------
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        //add group of JMenuItems
        fileMenu.add(new JMenuItem("New..."));
        fileMenu.add(new JMenuItem("Save as..."));
        fileMenu.add(new JMenuItem("Load..."));
        fileMenu.add(new JMenuItem("Info"));
        fileMenu.addSeparator();
        fileMenu.add(new JMenuItem("Exit"));      


        //Build Edit menu. -----------------------------------------------------------------
        editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        //add group of JMenuItems
        editMenu.add(new JMenuItem("Author..."));
        editMenu.add(new JMenuItem("Name..."));
       //--------------------------------------------------------------------------------

       serverMenu = new JMenu("Server");
       menuBar.add(serverMenu);

       serverMenu.add(new JMenuItem("Load from server..."));
       serverMenu.add(new JMenuItem("Save on server as..."));
 
        //Add the Event listeners for menuitems.
        addActionListeners(fileMenu);
        addActionListeners(editMenu);
        addActionListeners(serverMenu);

        
        // JPanel that contains both colorpanel and JcomboBox for shape selector
        JPanel colorShapePanel = new JPanel(new BorderLayout());

        // JPanel for colorboxes
        JPanel colorpanel = new JPanel();
        colorpanel.setLayout(new GridLayout(0, 5));
        JPanel black = new JPanel();
        JPanel blue = new JPanel();
        JPanel green = new JPanel();
        JPanel red = new JPanel();
        JPanel yellow = new JPanel();

              
        // Setting backgroundcolor for the colorboxes
        black.setBackground(Color.BLACK);
        blue.setBackground(Color.BLUE); 
        green.setBackground(Color.GREEN);
        red.setBackground(Color.RED);
        yellow.setBackground(Color.YELLOW); 

    
        // Adding all colors to the colorPanel
        colorpanel.add(black); //myPanel is the JPanel where I want to put the JTextFields
        colorpanel.add(blue); //myPanel is the JPanel where I want to put the JTextFields
        colorpanel.add(green); //myPanel is the JPanel where I want to put the JTextFields
        colorpanel.add(red); //myPanel is the JPanel where I want to put the JTextFields
        colorpanel.add(yellow); //myPanel is the JPanel where I want to put the JTextFields

        // Adds mouselistener to the color boxes by looping through them.
        if (colorpanel.getComponentCount() != 0) {
            for (int i = 0; i < colorpanel.getComponentCount(); i++) {
                if (colorpanel.getComponent(i) != null) {
                    colorpanel.getComponent(i).addMouseListener(this);
                }
            }
        }
        
        // Creating JComboBox
        String[] shapes = new String[] { "Rectangle", "Circle" };
        JComboBox<String> shapeSelector = new JComboBox<String>(shapes);

        // Adding colorPanel and shapeSelector to the combined panel.
        colorShapePanel.add(colorpanel, BorderLayout.CENTER);
        colorShapePanel.add(shapeSelector, BorderLayout.LINE_END);

        //Adding the combined panel to the contentpane (PAGE_START)
        contentPane.add(colorShapePanel, BorderLayout.PAGE_START);
        

        // Creates drawingArea, adds mousemotionlistener, sets background to white and ads it to contentpane.
        drawingArea = new DrawingPanel();
        drawingArea.addMouseMotionListener(this);
        drawingArea.addMouseListener(this);
        contentPane.add(drawingArea, BorderLayout.CENTER);
        
        //Adding Statusbar
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBackground(Color.LIGHT_GRAY);
        
        // Label for mouse cooordinates
        mouseLocation = new JLabel("Coordinates: 0,0");
        statusBar.add(mouseLocation, BorderLayout.LINE_START);

        // Label for selected color
        JPanel selectedColorPanel = new JPanel();
        selectedColorPanel.setBackground(Color.LIGHT_GRAY);

        // Adding selected color label text
        JLabel selectedColor = new JLabel("Selected Color: ");
        selectedColorPanel.add(selectedColor, BorderLayout.CENTER);

        // Adding selected color box
        selectedColorBox = new JPanel();
        selectedColorPanel.add(selectedColorBox, BorderLayout.LINE_END);
        statusBar.add(selectedColorPanel, BorderLayout.LINE_END);

        // Add statusbar to contentpane.
        contentPane.add(statusBar, BorderLayout.PAGE_END);



        //Frame setup
        setMinimumSize(new Dimension(300, 200));
        setTitle(programName);
    	setSize(new Dimension(800,600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/icon.png")).getImage());
        setLocationRelativeTo(null);
    }

 

    //method to add action listener to all menu items under a menu
    public void addActionListeners(JMenu menu) {
        
        if (menu.getItemCount() != 0) {
            for (int i = 0; i < menu.getItemCount(); i++) {
                if (menu.getItem(i) != null) {
                    (menu.getItem(i)).addActionListener(this);
                }       
            }
        }
    }
    
    // Reacts to what actionlistener user clicked on.
    public void actionPerformed(ActionEvent e) { 
        switch (e.getActionCommand()) {
            case "New...":
                drawingArea.setDrawing(new Drawing());                
                inputAction("Name...", "Enter name of the drawing:");
                inputAction("Author...", "Enter author of the drawing:");                
                break;
            case "Save as...":
                inputAction(e.getActionCommand(), "Save drawing to:");
                break;
            case "Load...":
                inputAction("Load...", "Load drawing from:");
                break;
            case "Info":
                Drawing theDrawing = drawingArea.getDrawing();
                JOptionPane.showMessageDialog(
                    this, getTitle().substring(getTitle().lastIndexOf("-") + 2, getTitle().length()) + "\n" +
                    "Number of shapes: " + theDrawing.getSize() + "\n" +
                    "Total area: " + theDrawing.getTotalArea() + "\n" + 
                    "Total circumference: " + theDrawing.getTotalCircumference()
                    );
                break;
            case "Exit":
                System.exit(0);
                break;
            case "Name...":
                inputAction(e.getActionCommand(), "Enter name of the drawing:");
                break;
            case "Author...":
                inputAction(e.getActionCommand(), "Enter author of the drawing:");
                break;
            case "Load from server...":
                loadFileFromServer();
                break;
            case "Save on server as...":
                saveXMLtoServer();
                break;
            default:
                break;
        }
    }
    
	

	// Takes input and sets author and name in title.
    public void inputAction(String event, String dialog) { 
        
        if (event == "Save as...") {
            String suggestion = "";
           
            if (drawingName == "" && author == "") { //If none of drawingname or author is set
                suggestion = ".xml";
            
            }
            else if (drawingName == "") { // If drawingname is not set
                suggestion = author + ".xml";
                
            }
            else if (author == "") { // if author is not set
                suggestion = drawingName + ".xml";
            }
            else { // else both is set
                suggestion = drawingName + " by " + author + ".xml";
            }

            String input = JOptionPane.showInputDialog(this, dialog, suggestion); // Dialog popup with suggestion for drawingname
            FileHandler.saveToXML(drawingArea.getDrawing(), input);
        }

        else { // If not "Save as..." is selected we dont need input suggestion.
        
            String input = JOptionPane.showInputDialog(this, dialog); // Dialog popup
                 
            if (input == null || input.isEmpty()) { // If user press cancel or leaves empty input field just exit method.
                return;
            }

            if (event == "Author...") {
                author = input;
                if (drawingName != "") { // If drawing name is set
                    setTitle(programName + " - " + drawingName + " by " + input);

                } else { // If name of drawing not has been set
                    setTitle(programName + " - " + input);
                }
                drawingArea.getDrawing().setAuthor(author);

            } else if (event == "Name...") {
                drawingName = input;
                if (author != "") { // If author has been set.
                    setTitle(programName + " - " + input + " by " + author);

                } else { // If author not has been set.
                    setTitle(programName + " - " + input);
                }
                drawingArea.getDrawing().setName(drawingName);
            }
            else if (event == "Load...") {
                try { // Tries to load drawing from xml file
                    Drawing loadedDrawing = FileHandler.loadFromXML(input);
                    if (drawingArea.getDrawing().getShapes().size() != 0) { // If there already is shapes, add drawing
                        drawingArea.addDrawing(loadedDrawing);
                    } else {
                        if (drawingName != "" && author != "") {
                            drawingArea.addDrawing(loadedDrawing); // Keep drawingnames
                        } else {
                            drawingArea.setDrawing(loadedDrawing); // If there is not already shapes, just set Drawing
                        }
                    }
                } catch (Exception e) { // If file could not be loaded
                    System.err.println("File could not be loaded");
                    return;
                }
                if (author == "" && drawingName == "") { // Updates title
                    updateFrameTitle();
                }
                
                // drawingArea.paintComponent(drawingArea.getGraphics()); // Updates graphics
                    drawingArea.repaint(); 
            }
        }  
    }

    public void updateFrameTitle() { // Updates title and private variable name, drawingname
        
        String newAuthor = drawingArea.getDrawing().getAuthor();
        String newDrawingName = drawingArea.getDrawing().getName();
        if (newAuthor != "" && newDrawingName != "") {
            setTitle(programName + " - " + newDrawingName + " by " + newAuthor);
            drawingName = newDrawingName;
            author = newAuthor;
        } else if (newDrawingName != "") { // If drawing name is set
            setTitle(programName + " - " + newDrawingName);
            author = newAuthor;
        } else if (newAuthor != "") { // If name of drawing not has been set
            setTitle(programName + " - " + newAuthor);
            drawingName = newDrawingName;
        }
        
    }
    
	@Override // Updates the mouse coordiantes to actual location in the drawingarea.
	public void mouseMoved(MouseEvent e) {
        mouseLocation.setText("Coordinates: " + e.getX() + ", " + e.getY() );
	}
    
    @Override // Sets the color box to color clicked.
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(drawingArea)) {

        } else {
            selectedColorBox.setBackground(e.getComponent().getBackground());     
        }
    }
    
    public void mousePressed(MouseEvent e) {
    }
    
    public void mouseDragged(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
    }
    

    public void mouseExited(MouseEvent e) {
    }
    

    public void mouseReleased(MouseEvent e) {
    }




    private void loadFileFromServer() {

        SwingWorker<String[], Void> loadFiles = new SwingWorker<String[], Void>() {

            @Override
            protected String[] doInBackground() {

                return client.getFilenamesFromServer();
            }

            @Override
            protected void done() {
                try {
                    xmlLoaded(get());

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        };

        loadFiles.execute();

    }

    public void xmlLoaded(String[] loadedFiles) {
        String selectedFile = (String) JOptionPane.showInputDialog(this, "Files available", "Load Drawing From Server",
                JOptionPane.PLAIN_MESSAGE, null, loadedFiles, "No files found in server");

        if (selectedFile == null) {return;}

        SwingWorker<String, Void> loadPainting = new SwingWorker<String, Void>() {

            @Override
            protected String doInBackground() {

                return client.getFileFromServer(selectedFile);
            }

            @Override
            protected void done() {
                try {
                    loadPaintingFromServer(get());

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        };

        loadPainting.execute();

    }

    public void loadPaintingFromServer(String file) {
        try { // Tries to load drawing from xml file
            Drawing loadedDrawing = FileHandler.loadFromXML(file);
            if (drawingArea.getDrawing().getShapes().size() != 0) { // If there already is shapes, add drawing
                drawingArea.addDrawing(loadedDrawing);
            } else {
                if (drawingName != "" && author != "") {
                    drawingArea.addDrawing(loadedDrawing); // Keep drawingnames
                } else {
                    drawingArea.setDrawing(loadedDrawing); // If there is not already shapes, just set Drawing
                }
            }
        } catch (Exception e) { // If file could not be loaded
            System.err.println("File could not be loaded");
            return;
        }
        if (author == "" && drawingName == "") { // Updates title
            updateFrameTitle();
        }
        // drawingArea.paintComponent(drawingArea.getGraphics()); // Updates graphics
        drawingArea.repaint();
    }


    public void saveXMLtoServer() {

        String suggestion = "";

        if (drawingName == "" && author == "") { //If none of drawingname or author is set
            suggestion = ".xml";

        } else if (drawingName == "") { // If drawingname is not set
            suggestion = author + ".xml";

        } else if (author == "") { // if author is not set
            suggestion = drawingName + ".xml";
        } else { // else both is set
            suggestion = drawingName + " by " + author + ".xml";
        }
        
        String desiredFilename = JOptionPane.showInputDialog(this, "Save Drawing on Server as", suggestion); // Dialog popup with suggestion for drawingname
        String filePath = "src" + File.separator + "clientHDD" + File.separator + desiredFilename; // Needed to save in clientHDD
    
        FileHandler.saveToXML(drawingArea.getDrawing(), filePath);


        SwingWorker<Boolean, Void> saveXml = new SwingWorker<Boolean, Void>() {           

            @Override
            protected Boolean doInBackground() {

                return client.saveAsFileToServer(desiredFilename, desiredFilename);
            }

            @Override
            protected void done() {
                try {
                    saveActionCompleted(get());

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        };

        saveXml.execute();
    }

    public void saveActionCompleted(Boolean success){

        String dialog = (success) ? "Successfully saved" : "Failed to save file";

        JOptionPane.showMessageDialog(this, dialog);
        
    }

}