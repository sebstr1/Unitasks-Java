/**
* <h1>Assignment 6 - MainFrame.java</h1>
* <p>This class inherits JFrame and Implements ActionListener, MouseMotionListener, MouseListener.</p>
* <p>Creates a graphical interface for our painting program with a frame, menus, drawingarea and
* statusbar. Adds a bunch of eventlisteners that is being handled correctly when according to event
* triggered.</p>
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-11-28
*/

package dt062g.sest1601.assignment6;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener, MouseMotionListener, MouseListener{

    JTextArea output;
    JScrollPane scrollPane;
    private final String programName = "JPaintPro";
    private String drawingName = "";
    private String author = "";
    private JLabel mouseLocation;
    private JPanel selectedColorBox; 
    static final long serialVersionUID = 1;

    
    MainFrame() {
        JMenuBar menuBar;
        JMenu fileMenu, editMenu;

        //Add regular components to the window, using the default BorderLayout.
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

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
        fileMenu.addSeparator();
        fileMenu.add(new JMenuItem("Exit"));      


        //Build Edit menu. -----------------------------------------------------------------
        editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        //add group of JMenuItems
        editMenu.add(new JMenuItem("Undo"));
        editMenu.add(new JMenuItem("Author..."));
        editMenu.add(new JMenuItem("Name..."));
       //--------------------------------------------------------------------------------
 
        //Add the Event listeners for menuitems.
        addActionListeners(fileMenu);
        addActionListeners(editMenu);

        
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
        JPanel drawingArea = new JPanel();
        drawingArea.addMouseMotionListener(this);
        drawingArea.setBackground(Color.WHITE);
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
        pack();
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
                inputAction("Name...", "Enter author of the drawing:");
                inputAction("Author...", "Enter author of the drawing:");
                break;
            case "Save as...":
                inputAction(e.getActionCommand(), "Save drawing to:");
                break;
            case "Load...":
                inputAction("Load...", "Load drawing from:");
                break;
            case "Exit":
                System.exit(0);
                break;
            case "Undo":
                System.out.println("UNDO SELECTED");
                break;
            case "Name...":
                inputAction(e.getActionCommand(), "Enter name of the drawing:");
                break;
            case "Author...":
                inputAction(e.getActionCommand(), "Enter author of the drawing:");
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
            System.out.println(input);
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

            } 
            else if (event == "Name...") {
                drawingName = input;
                if (author != "") { // If author has been set.
                    setTitle(programName + " - " + input + " by " + author);

                } else { // If author not has been set.
                    setTitle(programName + " - " + input);   
                }
            }
            else if (event == "Load...") {
                System.out.println("User entered filename to load... ( " + input + " )");
            }
        }  
    }

    
    
	@Override // Updates the mouse coordiantes to actual location in the drawingarea.
	public void mouseMoved(MouseEvent e) {
        mouseLocation.setText("Coordinates: " + e.getX() + ", " + e.getY() );
	}
    
    @Override // Sets the color box to color clicked.
    public void mouseClicked(MouseEvent e) {
        selectedColorBox.setBackground(e.getComponent().getBackground());     
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

}