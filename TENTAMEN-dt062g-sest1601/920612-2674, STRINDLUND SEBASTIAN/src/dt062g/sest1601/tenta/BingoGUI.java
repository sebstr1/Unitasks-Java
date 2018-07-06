/*
* @author  Sebastian Strindlund (sest1601)
* @version 1.0
* @since   01-19-2018
*/

package dt062g.sest1601.tenta;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class BingoGUI extends JFrame implements ActionListener{

	private JMenuBar menuBar;
	private JMenu bingoMenu;
	private JMenuItem generateBingoTable, startCaller, stopCaller;
	private BingoCaller caller;
	private JScrollPane scroll;
	private GridLayout grid;
	private JTextArea resultsArea;

	ArrayList<Integer> usedNumbers;


	public BingoGUI() {
		makeFrame();
	}


	// Create the frame
	private void makeFrame() {
		setTitle("Bingo");
		setSize(650,200);
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		bingoMenu = new JMenu("Menu");


		// Create Items and add actionlisteners
		startCaller = new JMenuItem("Start");
		startCaller.addActionListener(this);

		stopCaller = new JMenuItem("Stop");
		stopCaller.addActionListener(this);
		
		generateBingoTable = new JMenuItem("Create Bingo Table");
		generateBingoTable.addActionListener(this);


		// Add items to menu
		bingoMenu.add(startCaller);
		bingoMenu.add(stopCaller);
		bingoMenu.add(generateBingoTable);
		menuBar.add(bingoMenu);
		
		
		
		
		
		grid = new GridLayout();
		setLayout(grid);
		
		caller = new BingoCaller();
		scroll = new JScrollPane(caller);
		
		
		// Adds 5 bingoballs
		caller.addBingoBall(new BingoBall(1));
		caller.addBingoBall(new BingoBall(20));
		caller.addBingoBall(new BingoBall(37));
		caller.addBingoBall(new BingoBall(50));
		caller.addBingoBall(new BingoBall(75));

		

		add(scroll); // Add scrollpane

		pack(); // Fit window to components
		setLocationRelativeTo(null); // Middle of screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == generateBingoTable) {
			generateBingotable();
		}
		else if (e.getSource() == startCaller) {
			caller.startCaller();
		}
		else if (e.getSource() == stopCaller) {
			caller.stopCaller();
		}
		
	}


	private void generateBingotable() {
		resultsArea = new JTextArea();
		usedNumbers = new ArrayList<Integer>();
		resultsArea.setText(
							"-------------------------- \n" +
							"| B  | I  | N  | G  | O  | \n" +
							"-------------------------- \n"
							);

		String[] bingo = "BINGO".split(""); // BINGO as string array

		//5 rows
		for(int i = 0; i < 5; i++) {
			// 5 letters per row
			for (String letter : bingo) {
				String number = getRandomNumber(letter);
				String space = (Integer.parseInt(number) > 9) ? " " : "  "; // Double space if number is below 10 for formatting

				resultsArea.append("|" + space + number + " ");
			}
			resultsArea.append("|\n--------------------------\n");
		}

		saveToTextFile();

	}

	private String getRandomNumber(String letter) {
		Random randGen = new Random();
		int randomInt = 0;
		int min = 0;
		int max = 0;

		if (letter.equals("B")) {
			min = 1;
			max = 16;
		}
		else if (letter.equals("I")) {
			min = 16;
			max = 31;			
		}
		else if (letter.equals("N")) {
			min = 31;
			max = 46;			
		}
		else if (letter.equals("G")) {
			min = 46;
			max = 61;			
		}
		else if (letter.equals("O")) {
			min = 61;
			max = 76;			
		}

		randomInt = randGen.nextInt(max-min) + min;	
		
		while(usedNumbers.contains(randomInt)) {        // As long as our number is not unique, randomize again.
			randomInt = randGen.nextInt(max-min) + min;	
		}
		
		usedNumbers.add(randomInt); //  Add to ArrayList
		return Integer.toString(randomInt); // Return number as string
	}
	
	
	private void saveToTextFile() {
		
		final JFileChooser SaveBingoTableAS = new JFileChooser();  // New fileChooser so user can choose where to save
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt", "text"); // Only allow .txt
		SaveBingoTableAS.setFileFilter(filter);
		SaveBingoTableAS.setAcceptAllFileFilterUsed(false);
		SaveBingoTableAS.setApproveButtonText("Save");
		int actionDialog = SaveBingoTableAS.showOpenDialog(this); // Dialog
		if (actionDialog != JFileChooser.APPROVE_OPTION) {
			return;
		}
		
		File fileName = new File(SaveBingoTableAS.getSelectedFile() + ".txt"); // Filename user selected
		BufferedWriter outFile = null;
		try {
			outFile = new BufferedWriter(new FileWriter(fileName)); 
			
			resultsArea.write(outFile); // Write file to destination user selected with JTextArea write function
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (outFile != null) {
				try {
					outFile.close();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
		}
		
	}
	
	private void exit()	{
		System.exit(0);
	}
	
	
}