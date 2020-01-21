/***********************************************
**                Scouting GUI                **
**                                            **
** Urmil, Manitha, Aiden, Gurnoor, Nived      **
** ICS4U0-A                                   **
** Ver: 4.0 - Jan. 5, 2018                    **
** Last Edit: Jan. 5, 2018                    **
***********************************************/

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.SpringLayout;

public class Scouting {
	public JFrame Autonframe = new JFrame("Autonomous Recorder");
	public	AutonomousRecorderGUI Auton = new AutonomousRecorderGUI();
	
	/**
	 * @wbp.parser.entryPoint
	 */
public void main(String Back, String DirectoryLocation) {
		
		// Import Settings
		String SettingsFileLocation = DirectoryLocation + "\\AppData\\Settings.txt";
		String[] Settings = new String[4];
		
		try {
			Scanner FileScanner = new Scanner(new File(SettingsFileLocation));
			
			for (int i = 0; i < Settings.length; i++) {
				if (FileScanner.hasNextLine()) {
					
					Settings[i] = FileScanner.nextLine();
				}
			}
			FileScanner.close();
		} catch (FileNotFoundException e1) {
			
			try {
				new File(SettingsFileLocation).createNewFile();
			} catch (IOException e2) {
				
				e2.printStackTrace();
			}
		}
	
		// Import Teams
		String TeamDataDirectory = DirectoryLocation + "\\TeamData";
	    
	    File[] directoryListAsFile = new File(TeamDataDirectory).listFiles(new FileFilter() {
	        public boolean accept(File file) {
	            return file.isDirectory();
	        }
	    });
	    
	    String [] Teams = new String[directoryListAsFile.length];
	    
	    for (int i = 0; i < directoryListAsFile.length; i++) {
	    	
	    	Teams[i] = directoryListAsFile[i].getName();
	    }
	    
		// Window Size Adjustments According to Resolution (Primary Monitor)
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		int windowWidth = screenWidth/2;
		int windowHeight = screenHeight/2;
		int x = (screenWidth - windowWidth)/2;
		int y = (screenHeight - windowHeight)/2;
		
		// Application Window Initialization
		JFrame frame = new JFrame("Scouting");
		frame.setBounds(x, y, windowWidth, windowHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
	}
}