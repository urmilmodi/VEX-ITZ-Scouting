/***********************************************
**                Scouting GUI                **
**                                            **
** Urmil, Manitha, Aiden, Gurnoor, Nived      **
** ICS4U0-A                                   **
** Ver: 4.0 - Jan. 5, 2018                    **
** Last Edit: Jan. 5, 2018                    **
***********************************************/

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SpringLayout;

public class ScoutingGUI {
	
	// Variable Initialization
	private JFrame Autonframe = new JFrame("Autonomous Recorder");
	private	AutonomousRecorderGUI Auton = new AutonomousRecorderGUI();
	
	/**
	 * @wbp.parser.entryPoint
	 */
@SuppressWarnings({ "unchecked", "rawtypes" })
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
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(x, y, windowWidth, windowHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		// Initialize Elements
		JButton btnBack = new JButton("");
		JButton btnMenu = new JButton("");
		JComboBox txtMenu = new JComboBox();
		JButton btnSubmit = new JButton("");
		JComboBox txtTeam = new JComboBox();
		JLabel lblDesign = new JLabel("Design: ");
		JComboBox txtDesign = new JComboBox();
		JTextField txtOtherDesign = new JTextField("");
		JLabel lblMaxMobileStack = new JLabel("Max. Stack on Mobile Goal: ");
		JLabel lblMaxPoleStack = new JLabel("Max. Stack on Pole: ");
		JComboBox txtMaxMobileStack = new JComboBox();
		JComboBox txtMaxPoleStack = new JComboBox();
		JLabel lblDriveSpeed = new JLabel("Drive Speed: ");
		JLabel lblLiftSpeed = new JLabel("Lift Speed: ");
		JLabel lblIntakeStyle = new JLabel("Intake Style: ");
		JComboBox txtDriveSpeed = new JComboBox();
		JComboBox txtLiftSpeed = new JComboBox();
		JComboBox txtIntake = new JComboBox();
		JLabel lblAvgConesec = new JLabel("Avg. Cone/Sec: ");
		JComboBox txtAvgConeSpeed = new JComboBox();
		JLabel lblAvgMobomin = new JLabel("Avg. Mobile Goal Time: ");
		JComboBox txtAvgMoboTime = new JComboBox();
		JLabel lblStalling = new JLabel("Stalling: ");
		JComboBox txtStalling = new JComboBox();
		JTextField txtStallingDetail = new JTextField("");
		JLabel lblFlipping = new JLabel("Can Flip: ");
		JComboBox txtFlip = new JComboBox();
		JLabel lblMatch = new JLabel("Match #: ");
		JLabel lblTeam = new JLabel("Team: ");
		JLabel lblStrategy = new JLabel("Game Strategy/Playing Style: ");
		JTextField txtStrategy = new JTextField("");
		JLabel lblDriverSkill = new JLabel("Driver Skill: ");
		JComboBox txtDriverSkill = new JComboBox();
		JComboBox txtZone = new JComboBox();
		JLabel lblZone = new JLabel("Zone: ");
		JLabel lblInfo = new JLabel("Additional Information: ");
		JTextField txtInfo = new JTextField("");
		JComboBox txtMatchNumber = new JComboBox();
		JButton btnAutonomous = new JButton("");
		JLabel lblRating = new JLabel("Rating: ");
		JComboBox txtRating = new JComboBox();
		
		// Setting Models
		txtMenu.setModel(new DefaultComboBoxModel(new String[] {"Main Menu", "Team List", "Team View", "Sync", "Settings"}));
		txtDesign.setModel(new DefaultComboBoxModel(new String[] {"DR4B", "Scissor Lift", "Mobile Goal", "Other"}));
		txtMaxMobileStack.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
		txtMaxPoleStack.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
		txtDriveSpeed.setModel(new DefaultComboBoxModel(new String[] {"Slow (Torque)", "Avg. (High Speed)", "Fast (Turbo)"}));
		txtDriveSpeed.setSelectedIndex(1);
		txtLiftSpeed.setModel(new DefaultComboBoxModel(new String[] {"Slow (Torque)", "Avg. (High Speed)", "Fast (Turbo)"}));
		txtLiftSpeed.setSelectedIndex(1);
		txtIntake.setModel(new DefaultComboBoxModel(new String[] {"Goliath & 4-Bar", "Claw & 4-Bar", "Goliath & Chain Bar", "Claw & Chain Bar"}));
		txtAvgConeSpeed.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		txtAvgMoboTime.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "N/A"}));
		txtStalling.setModel(new DefaultComboBoxModel(new String[] {"Yes, Explain \u2192", "No"}));
		txtTeam.setModel(new DefaultComboBoxModel(Teams));
		txtDriverSkill.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		txtZone.setModel(new DefaultComboBoxModel(new String[] {"5", "10", "20"}));
		txtFlip.setModel(new DefaultComboBoxModel(new String[] {"None", "Cones", "Mobile Goal", "Both"}));
		txtMatchNumber.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114"}));
		txtRating.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		txtRating.setSelectedIndex(4);
		
		// Visual Adjustments
		txtInfo.setColumns(10);
		txtMenu.setVisible(false);
		txtStrategy.setColumns(10);
		txtOtherDesign.setColumns(10);
		txtStallingDetail.setColumns(10);
		txtOtherDesign.setVisible(false);
		btnBack.setBorderPainted(false);
		btnMenu.setBorderPainted(false);
		btnSubmit.setBorderPainted(false);
		btnAutonomous.setBorderPainted(false);
		btnBack.setBackground(Color.WHITE);
		btnMenu.setBackground(Color.WHITE);
		btnSubmit.setBackground(Color.WHITE);
		btnAutonomous.setBackground(Color.WHITE);
		btnAutonomous.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\auton.png"));
		btnBack.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\back2.png"));
		btnMenu.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\menu2.png"));
		btnSubmit.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\Submit2.png"));
		
		// Positioning Elements
		springLayout.putConstraint(SpringLayout.WEST, lblMatch, 327, SpringLayout.EAST, lblTeam);
		springLayout.putConstraint(SpringLayout.NORTH, txtRating, -4, SpringLayout.NORTH, lblMaxPoleStack);
		springLayout.putConstraint(SpringLayout.WEST, txtRating, 4, SpringLayout.EAST, lblRating);
		springLayout.putConstraint(SpringLayout.NORTH, lblRating, 0, SpringLayout.NORTH, lblMaxPoleStack);
		springLayout.putConstraint(SpringLayout.WEST, lblRating, 0, SpringLayout.WEST, lblMatch);
		springLayout.putConstraint(SpringLayout.NORTH, txtInfo, -2, SpringLayout.NORTH, lblDriveSpeed);
		springLayout.putConstraint(SpringLayout.WEST, txtInfo, 6, SpringLayout.EAST, lblInfo);
		springLayout.putConstraint(SpringLayout.NORTH, lblInfo, 0, SpringLayout.NORTH, lblDriveSpeed);
		springLayout.putConstraint(SpringLayout.WEST, lblInfo, 0, SpringLayout.WEST, lblMatch);
		springLayout.putConstraint(SpringLayout.WEST, txtMatchNumber, 15, SpringLayout.EAST, lblMatch);
		springLayout.putConstraint(SpringLayout.NORTH, txtDriverSkill, -4, SpringLayout.NORTH, lblMaxMobileStack);
		springLayout.putConstraint(SpringLayout.WEST, txtDriverSkill, 6, SpringLayout.EAST, lblDriverSkill);
		springLayout.putConstraint(SpringLayout.NORTH, lblDriverSkill, 0, SpringLayout.NORTH, lblMaxMobileStack);
		springLayout.putConstraint(SpringLayout.WEST, lblDriverSkill, 0, SpringLayout.WEST, lblMatch);
		springLayout.putConstraint(SpringLayout.NORTH, txtStrategy, -2, SpringLayout.NORTH, lblDesign);
		springLayout.putConstraint(SpringLayout.WEST, txtStrategy, 6, SpringLayout.EAST, lblStrategy);
		springLayout.putConstraint(SpringLayout.NORTH, lblStrategy, 0, SpringLayout.NORTH, lblDesign);
		springLayout.putConstraint(SpringLayout.WEST, lblStrategy, 0, SpringLayout.WEST, lblMatch);
		springLayout.putConstraint(SpringLayout.NORTH, txtMatchNumber, -4, SpringLayout.NORTH, lblMatch);
		springLayout.putConstraint(SpringLayout.NORTH, lblMatch, 0, SpringLayout.NORTH, lblTeam);
		springLayout.putConstraint(SpringLayout.NORTH, txtTeam, -4, SpringLayout.NORTH, lblTeam);
		springLayout.putConstraint(SpringLayout.WEST, txtTeam, 6, SpringLayout.EAST, lblTeam);
		springLayout.putConstraint(SpringLayout.NORTH, lblTeam, 6, SpringLayout.SOUTH, btnBack);
		springLayout.putConstraint(SpringLayout.WEST, lblTeam, 0, SpringLayout.WEST, lblDesign);
		springLayout.putConstraint(SpringLayout.NORTH, txtOtherDesign, -2, SpringLayout.NORTH, lblDesign);
		springLayout.putConstraint(SpringLayout.WEST, txtOtherDesign, 13, SpringLayout.EAST, txtDesign);
		springLayout.putConstraint(SpringLayout.NORTH, btnAutonomous, 454, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnAutonomous, 769, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnAutonomous, 480, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnAutonomous, 843, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtFlip, 455, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtFlip, 105, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblZone, 373, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblZone, 258, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtZone, 369, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtZone, 298, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblFlipping, 459, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblFlipping, 44, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtStallingDetail, 411, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtStallingDetail, 224, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtStallingDetail, 361, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtStalling, 409, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtStalling, 101, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblStalling, 413, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblStalling, 41, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtAvgMoboTime, 369, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtAvgMoboTime, 181, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblAvgMobomin, 373, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblAvgMobomin, 44, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtAvgConeSpeed, 327, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtAvgConeSpeed, 150, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblAvgConesec, 331, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblAvgConesec, 44, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtIntake, 283, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtIntake, 120, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtLiftSpeed, 238, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtLiftSpeed, 125, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtDriveSpeed, 173, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtDriveSpeed, 120, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblIntakeStyle, 287, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblIntakeStyle, 46, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblLiftSpeed, 245, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblLiftSpeed, 44, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblDriveSpeed, 177, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblDriveSpeed, 44, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtMaxPoleStack, 206, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtMaxPoleStack, 181, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtMaxMobileStack, 137, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtMaxMobileStack, 205, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblMaxPoleStack, 210, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblMaxPoleStack, 44, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblMaxMobileStack, 141, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblMaxMobileStack, 44, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtDesign, 101, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtDesign, 92, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblDesign, 105, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblDesign, 44, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnSubmit, 454, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnSubmit, 846, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnSubmit, 480, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSubmit, 920, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtMenu, 12, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtMenu, 824, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnMenu, 29, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnMenu, 834, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnMenu, 60, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnMenu, 915, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnBack, 29, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnBack, 12, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnBack, 60, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnBack, 75, SpringLayout.WEST, frame.getContentPane());
		
		// Add Elements to Application Window
		frame.getContentPane().add(btnBack);
		frame.getContentPane().add(btnMenu);
		frame.getContentPane().add(txtMenu);
		frame.getContentPane().add(btnSubmit);
		frame.getContentPane().add(lblDesign);
		frame.getContentPane().add(txtDesign);
		frame.getContentPane().add(txtOtherDesign);
		frame.getContentPane().add(lblMaxMobileStack);
		frame.getContentPane().add(lblMaxPoleStack);
		frame.getContentPane().add(txtMaxMobileStack);
		frame.getContentPane().add(txtMaxPoleStack);
		frame.getContentPane().add(lblDriveSpeed);
		frame.getContentPane().add(lblLiftSpeed);
		frame.getContentPane().add(lblIntakeStyle);
		frame.getContentPane().add(txtDriveSpeed);
		frame.getContentPane().add(txtLiftSpeed);
		frame.getContentPane().add(txtIntake);
		frame.getContentPane().add(lblAvgConesec);
		frame.getContentPane().add(txtAvgConeSpeed);
		frame.getContentPane().add(lblAvgMobomin);
		frame.getContentPane().add(txtAvgMoboTime);
		frame.getContentPane().add(lblStalling);
		frame.getContentPane().add(txtStalling);
		frame.getContentPane().add(txtStallingDetail);
		frame.getContentPane().add(lblFlipping);
		frame.getContentPane().add(lblMatch);
		frame.getContentPane().add(lblTeam);
		frame.getContentPane().add(lblStrategy);
		frame.getContentPane().add(txtStrategy);
		frame.getContentPane().add(lblDriverSkill);
		frame.getContentPane().add(txtDriverSkill);
		frame.getContentPane().add(txtZone);
		frame.getContentPane().add(lblZone);
		frame.getContentPane().add(lblInfo);
		frame.getContentPane().add(txtInfo);
		frame.getContentPane().add(txtFlip);
		frame.getContentPane().add(txtMatchNumber);
		frame.getContentPane().add(btnAutonomous);
		frame.getContentPane().add(lblRating);
		frame.getContentPane().add(txtRating);
		frame.getContentPane().add(txtTeam);
		
		// ActionEvents
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Display Options
				txtMenu.setVisible(true);
				txtMenu.showPopup();
				txtMenu.setVisible(false);
			}
		});
		txtMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Redirect to Selected Screen
				Autonframe.dispose();
				frame.dispose();
				switch (txtMenu.getSelectedIndex()) {
					case 0:		new MainGUI().main();
								break;
					case 1:		new TeamListGUI().main("Scouting", DirectoryLocation);
								break;
					case 2:		new TeamViewGUI().main("Scouting", DirectoryLocation, "");
								break;
					case 3:		new SyncGUI().main("Scouting", DirectoryLocation);
								break;
					case 4:		new SettingsGUI().main("Scouting", DirectoryLocation);
								break;
				}
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Redirect to Last Screen
				Autonframe.dispose();
				frame.dispose();
				if (Back.equals("Main Menu")) {
					
					new MainGUI().main();
				} else if (Back.equals("Team List")) {
					
					new TeamListGUI().main("Scouting", DirectoryLocation);
				} else if (Back.equals("Team View")) {
					
					new TeamViewGUI().main("Scouting", DirectoryLocation, "");
				} else if (Back.equals("Sync")) {
					
					new SyncGUI().main("Scouting", DirectoryLocation);
				} else if (Back.equals("Settings")) {
					
					new SettingsGUI().main("Scouting", DirectoryLocation);
				}
			}
		});
		txtDesign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Update Screen Based on Selection 
				if (txtDesign.getSelectedIndex() == 3) {
					
					// Display TextBox & Description if NOT Visible
					txtDesign.setModel(new DefaultComboBoxModel(new String[] {"DR4B", "Scissor Lift", "Mobile Goal", "Explain Other Design \u2192"}));
					txtDesign.setSelectedItem("Explain Other Design \u2192");
					txtOtherDesign.setVisible(true);
					
				} else if (txtOtherDesign.isVisible()) {
					
					// Hide TextBox & Description if Visible
					String SelectedDesign = (String) txtDesign.getSelectedItem();
					txtDesign.setModel(new DefaultComboBoxModel(new String[] {"DR4B", "Scissor Lift", "Mobile Goal", "Other"}));
					txtDesign.setSelectedItem(SelectedDesign);
					txtOtherDesign.setVisible(false);
				}
			}
		});
		txtStalling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Update Screen Based on Selection 
				if (txtStalling.getSelectedIndex() == 0) {
					
					// Display TextBox & Description if NOT Visible
					txtStalling.setModel(new DefaultComboBoxModel(new String[] {"Yes, Explain \u2192", "No"}));
					txtStalling.setSelectedItem("Yes, Explain \u2192");
					txtStallingDetail.setVisible(true);
					
				} else {
					
					// Hide TextBox & Description if Visible
					txtStalling.setModel(new DefaultComboBoxModel(new String[] {"Yes", "No"}));
					txtStalling.setSelectedItem("No");
					txtStallingDetail.setVisible(false);
				}
			}
		});
		txtAvgMoboTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Update Screen Based on Selection 
				if (txtAvgMoboTime.getSelectedIndex() != 60) {
					
					// Display ComboBox & Description if NOT Visible
					txtZone.setVisible(true);
					lblZone.setVisible(true);
				} else {
					
					// Hide ComboBox & Description if Visible
					txtZone.setVisible(false);
					lblZone.setVisible(false);
				}
			}
		});
		btnAutonomous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Run Database Check
				if(!new MainGUI().DatabaseCheck(frame, "Scouting")) {
				
					Auton(DirectoryLocation); // Launch Autonomous Recorder
				}
			}
		});
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Team Directory Location Based on Selections
				String TeamDirectoryLocation = DirectoryLocation + "\\TeamData\\" + (String) txtTeam.getSelectedItem() + "\\" + Settings[3];
				
				/*
				* Order: SyncValue, Date, Team, Competition, Match Number, Rating, Design, Stalling (Yes/No), Stalling Details-, Driver Skill, Intake, \\ 11th Element
				* Avg. Cone Speed, Avg. Mobile Goal Time, Zone-, Flip, Drive Speed, Lift Speed, Max. Mobile Stack, Max. Pole Stack, Strategy, Summary \\ 21st Element
				*/
				
				// Storing Variables in Array
				String[] Note = {Long.toString(System.currentTimeMillis()), new SimpleDateFormat("dd/MM/YYYY").format(new Date()), Settings[1], (String) txtTeam.getSelectedItem(), Settings[3],
								(String) txtMatchNumber.getSelectedItem(), (String) txtRating.getSelectedItem(), (String) txtDesign.getSelectedItem(), (String) txtStalling.getSelectedItem(),
								txtStallingDetail.getText(), (String) txtDriverSkill.getSelectedItem(), (String) txtIntake.getSelectedItem(), (String) txtAvgConeSpeed.getSelectedItem(),
								(String) txtAvgMoboTime.getSelectedItem() + " Second", (String) txtZone.getSelectedItem(), (String) txtFlip.getSelectedItem(), (String) txtDriveSpeed.getSelectedItem(),
								(String) txtLiftSpeed.getSelectedItem(), (String) txtMaxMobileStack.getSelectedItem(), (String) txtMaxPoleStack.getSelectedItem(), txtStrategy.getText(), txtInfo.getText()};
				
				// Adjustments to Data
				if (Note[7].equals(txtDesign.getItemAt(3))) {
					Note[7] = txtOtherDesign.getText();
				}
				if (Note[8].equals("No")) {
					Note[9] = "N/A";
				} else {
					Note[8].equals("Yes");
				}
				if (Note[13].equals("N/A")) {
					Note[14] = "N/A";
				}
				if (Note[7].equals(txtDesign.getItemAt(2))) {
					Note[11] = "N/A";
					Note[12] = "N/A";
					Note[17] = "N/A";
					Note[18] = "N/A";
					Note[19] = "N/A";
				}
				if (!((String) txtAvgMoboTime.getSelectedItem()).equals("1")) {
					Note[13] += "s";
				}
				for (int i = 0; i < Note.length; i++) {
					
					if (Note[i].equals("") || Note[i].equals(null)) {Note[i] = "No Data";}
				}
				
				// Saving Scouting Note
				try {
					Files.createDirectories(Paths.get(TeamDirectoryLocation)); // Creating Directory
					Auton.Save(TeamDirectoryLocation + "\\" + (String) txtMatchNumber.getSelectedItem() + ".png"); // Save Auton Path
					Autonframe.dispose(); // Close Auton Frame
					
					BufferedWriter file = new BufferedWriter(new FileWriter(TeamDirectoryLocation + "\\" + (String) txtMatchNumber.getSelectedItem() + ".txt"));
					
					for (int i = 0; i < Note.length; i++) {
						if (Note[i] != null) {
							file.write(Note[i]);
							
							if (i < Note.length - 1) {
								file.newLine();
							}
						}
					}
					file.close();
					
					// Note Saved Alert
					JOptionPane.showMessageDialog(null, "Note Has Been Saved!");
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				
				// Refresh Screen
				frame.dispose();
				new ScoutingGUI().main(Back, DirectoryLocation);
			}
		});
		
		// Set Application Window Visible
		frame.setVisible(true);
		
		// Update Tournament/Competition IF Needed
		if (!Settings[0].equals(new SimpleDateFormat("dd/MM/YYYY").format(new Date()))) {
			
			// Redirect to Settings
			frame.dispose();
			new SettingsGUI().main("Scouting", DirectoryLocation);
			JOptionPane.showMessageDialog(null, "Update Tournament/Competition Selected!");
			
		} else {
			
			// Edit(Edit, NoteLocation);
			
			// Run Database Check
			if(!new MainGUI().DatabaseCheck(frame, "Scouting")) {
			
				Auton(DirectoryLocation); // Launch Autonomous Recorder
			}
		}
	}
/*
	public void Edit(Boolean Edit, String NoteDetails) {
		
		if (Edit) {
			
			String Team = NoteDetails.split("\\")[0];
			String Competition = NoteDetails.split("\\")[1];
			String Match = NoteDetails.split("\\")[2];
			
			// Load Note from location
			// adjust to return to scouting values
			// post the scouting page, change the code so Auton refers to image
			
		}
	}*/

	public void Auton(String DirectoryLocation) {
		
		// Autonomous Recorder Window Initialization
		Autonframe.setSize(368, 423);
		Autonframe.setVisible(true);
		Autonframe.setResizable(true);
		
		// Initialize Elements
		Container content = Autonframe.getContentPane();
		JPanel controls = new JPanel();
		JButton btnClear = new JButton("Clear");
		
		// Elements to Application Window
		content.setLayout(new BorderLayout());
		content.add(Auton, BorderLayout.CENTER);
		controls.add(btnClear);
		content.add(controls, BorderLayout.NORTH);
		Auton.Clear(DirectoryLocation);
		
		// ActionEvents
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Auton.Clear(DirectoryLocation);
			}
		});
	}
}