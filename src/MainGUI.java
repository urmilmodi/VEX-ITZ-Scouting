/***********************************************
**                  Main GUI                  **
**                                            **
** Urmil, Manitha, Aiden, Gurnoor, Nived      **
** ICS4U0-A                                   **
** Ver: 4.0 - Jan. 5, 2018                    **
** Last Edit: Jan. 5, 2018                    **
***********************************************/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.ImageIcon;

public class MainGUI {
	
	// Variable Initialization
	public String DirectoryLocation;
	private String Code = "";
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void main() {
		
		// Window Size Adjustments According to Resolution (Primary Monitor)
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width; 
		int windowWidth = screenWidth/2;
		int windowHeight = screenHeight/2;
		int x = (screenWidth - windowWidth)/2;
		int y = (screenHeight - windowHeight)/2;
		
		// Application Window Initialization
		JFrame frame = new JFrame("Scouting App");
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setResizable(false);
		frame.getContentPane().setEnabled(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(x, y, windowWidth, windowHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Initialize Elements
		JLabel lblTitle = new JLabel("");
		JButton btnTeamList = new JButton("");
		JButton btnScouting = new JButton("");
		JButton btnTeamView = new JButton("");
		JButton btnSync = new JButton("");
		JButton btnSettings = new JButton("");
		
		// Visual Adjustments
		btnTeamView.setBorderPainted(false);
		btnSync.setBorderPainted(false);
		btnSettings.setBorderPainted(false);
		btnTeamList.setBorderPainted(false);
		btnScouting.setBorderPainted(false);
		btnTeamView.setBackground(Color.WHITE);
		btnSync.setBackground(Color.WHITE);
		btnSettings.setBackground(Color.WHITE);
		btnTeamList.setBackground(Color.WHITE);
		btnTeamList.setForeground(Color.WHITE);
		btnScouting.setBackground(Color.WHITE);
		
		// Positioning Elements
		lblTitle.setBounds(300, 33, 358, 132);
		btnTeamView.setBounds(373, 200, 219, 159);
		btnSync.setBounds(94, 200, 209, 159);
		btnSettings.setBounds(12, 443, 162, 56);
		btnTeamList.setBounds(780, 449, 162, 50);
		btnScouting.setBounds(652, 200, 219, 159);
		
		// Add Elements to Application Window
		frame.getContentPane().add(btnTeamList);
		frame.getContentPane().add(btnScouting);
		frame.getContentPane().add(btnTeamView);	
		frame.getContentPane().add(btnSync);
		frame.getContentPane().add(btnSettings);
		frame.getContentPane().add(lblTitle);
		frame.getContentPane().setLayout(null);
		
		// ActionEvents
		btnSync.addKeyListener(new KeyAdapter() { // Secret Admin Mode Access
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				Code += arg0.getKeyChar(); // Adds Every Char to Code String
				
				// Code Verification
				if (Code.equals("EnableAdmin")) {
					
					// Redirect to Admin
					JOptionPane.showMessageDialog(null, "Access Granted");
					frame.dispose();
					new AdminGUI().main("Main Menu", DirectoryLocation);
				}
			}
		});
		btnScouting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Redirect to Scouting
				frame.dispose();
				new ScoutingGUI().main("Main Menu", DirectoryLocation);
			}
		});
		btnTeamList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Redirect to Team List
				frame.dispose();
				new TeamListGUI().main("Main Menu", DirectoryLocation);
			}
		});
		btnTeamView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Redirect to Team View
				frame.dispose();
				new TeamViewGUI().main("Main Menu", DirectoryLocation, "");
			}
		});
		btnSync.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Redirect to Sync
				frame.dispose();
				new SyncGUI().main("Main Menu", DirectoryLocation);
			}
		});
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Redirect to Settings
				frame.dispose();
				new SettingsGUI().main("Main Menu", DirectoryLocation);
			}
		});
	
		// Set Application Window Visible
		frame.setVisible(true);
		
		 // Run Database Check & Update Screen Based on Check
		DatabaseCheck(frame, "Main Menu");
		lblTitle.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\Recon.png"));
		btnTeamView.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\team view.png"));
		btnSync.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\button faces.png"));
		btnSettings.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\settings.png"));
		btnTeamList.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\team list.png"));
		btnScouting.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\scouting.png"));
	}
	
	// Database Verification Process
	public Boolean DatabaseCheck(JFrame frame, String Back) {
		
		Boolean SettingsError = false;
		Boolean AppError = false;
		try {
			
			// Directory Location Identification Based on OS
			String OS = System.getProperty("os.name").toLowerCase();
			
			if (OS.indexOf ("win") >= 0) {
				
				DirectoryLocation = "C:\\Scouting App\\Data";
				//DirectoryLocation = "C:\\Users\\Urmil\\Documents\\EclipseProjects\\Scouting App\\Data";
				
			} else if (OS.indexOf ("mac") >= 0 & OS.startsWith("mac os x")) {
				
				DirectoryLocation = "\\Users\\userName\\Scouting App\\Data";
				
			} else if (OS.indexOf ("mac") >= 0 & !OS.startsWith("mac os x")) {
				
				DirectoryLocation = "\\Users\\userName\\Scouting App\\Data";
			}
			
			 // Creating Directories & Application Data Files
			Files.createDirectories(Paths.get(DirectoryLocation + "\\TeamData"));
			Files.createDirectories(Paths.get(DirectoryLocation + "\\AppData"));
			new File(DirectoryLocation + "\\TeamData\\Team.txt").createNewFile();
			new File(DirectoryLocation + "\\AppData\\Settings.txt").createNewFile();
			
			// Checking IF Field Image Exists
			if (!new File(DirectoryLocation + "\\AppData\\Field.png").exists()) {AppError = true;}
			
			// Hide Directory to Prevent User Interference
			Files.setAttribute(FileSystems.getDefault().getPath(DirectoryLocation), "dos:hidden", true);
			
			// Error Detection Process
			
			// Checking IF Initial Settings Have Been Established
			Scanner FileScanner = new Scanner(new File(DirectoryLocation + "\\AppData\\Settings.txt"));
			
			for (int i = 0; i < 4; i++) {
				if (!FileScanner.hasNextLine()) {
					SettingsError = true;
				}
			}
			
			// Checking IF Attendance & Competition Files Exist
			if (!new File(DirectoryLocation + "\\AppData\\Attendance.txt").exists()) {
			
				AppError = true;
				
			} else {
				
				ArrayList<String> AttendanceList = new ArrayList<String>();
				FileScanner = new Scanner(new File(DirectoryLocation + "\\AppData\\Attendance.txt"));
				
				while (FileScanner.hasNextLine()) {
					
					AttendanceList.add(FileScanner.nextLine());
				}
				if (AttendanceList.size() == 0) {
					
					BufferedWriter file = new BufferedWriter(new FileWriter(DirectoryLocation + "\\AppData\\Attendance.txt"));
					file.write(0);
					file.close();
				}
			}
			if (!new File(DirectoryLocation + "\\AppData\\Competition.txt").exists()) {
			
				AppError = true;
				
			} else {
				
				ArrayList<String> CompetitionList = new ArrayList<String>();
				FileScanner = new Scanner(new File(DirectoryLocation + "\\AppData\\Competition.txt"));
				
				while (FileScanner.hasNextLine()) {
					
					CompetitionList.add(FileScanner.nextLine());
				}
			}
			FileScanner.close();
			
			// User Alert Error Process
			
			// Application Data Loss Alert
			if (AppError) {
				
				JOptionPane.showMessageDialog(null, "Application Data Loss, ReSync or Contact Admin");
				SettingsError = false;
				return true; // Alert Other Methods  of Data Loss
			}
			
			// Settings Data Loss Alert
			if (SettingsError) {
				
				// Redirect to Settings
				frame.dispose();
				new SettingsGUI().main(Back, DirectoryLocation);
				JOptionPane.showMessageDialog(null, "Establish Initial Settings!");
				
				return true; // Prevent Access to Other Screens Prior to Submission of Settings
			} else {
				return false; // Allows Access to Other Screens
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
	}
}