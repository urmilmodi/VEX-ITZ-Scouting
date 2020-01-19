/***********************************************
**                 Admin GUI                  **
**                                            **
** Urmil, Manitha, Aiden, Gurnoor, Nived      **
** ICS4U0-A                                   **
** Ver: 1.0 - Jan. 5, 2018                    **
** Last Edit: Jan. 5, 2018                    **
***********************************************/

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;

public class AdminGUI {
	
	// Variable Initialization
	@SuppressWarnings("rawtypes")
	private JComboBox txtTeam;
	@SuppressWarnings("rawtypes")
	private JComboBox txtCompetition;
	@SuppressWarnings("rawtypes")
	private JComboBox txtMatchNumber;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void main(String Back, String DirectoryLocation) {
		
		// Import Teams
		String TeamDataDirectory = DirectoryLocation + "\\TeamData";
	    
	    File[] directoryListAsFile = new File(TeamDataDirectory).listFiles(new FileFilter() {
	        public boolean accept(File file) {
	            return file.isDirectory();
	        }
	    });
	    
	    List<String> TeamList = new ArrayList<String>();
	    
	    for (int i = 0; i < directoryListAsFile.length; i++) {
	    	
	    	if (new File(TeamDataDirectory + "\\" + directoryListAsFile[i].getName()).list().length > 0) {
	    		
	    		TeamList.add(directoryListAsFile[i].getName());
	    	}
	    }
	    String[] Teams = TeamList.toArray(new String[TeamList.size()]);
		
		// Window Size Adjustments According to Resolution (Primary Monitor)
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		int windowWidth = screenWidth/2;
		int windowHeight = screenHeight/2;
		int x = (screenWidth - windowWidth)/2;
		int y = (screenHeight - windowHeight)/2;
		
		// Application Window Initialization
		JFrame frame = new JFrame("Admin");
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(x, y, windowWidth, windowHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		// Initialize Elements
		JLabel lblAdmin = new JLabel("Admin");
		JButton btnBack = new JButton("\u2190");
		JButton btnMenu = new JButton("\u2261");
		JComboBox txtMenu = new JComboBox();
		JLabel lblAddTeam = new JLabel("Add Team");
		JLabel lblTeamNumber = new JLabel("Team Number: ");
		JLabel lblTeamName = new JLabel("Team Name: ");
		JTextField txtTeamNumber = new JTextField();
		JTextField txtTeamName = new JTextField();
		JButton btnAddTeam = new JButton("Add Team");
		JButton btnEditAttendanceList = new JButton("Edit Attendance List");
		JButton btnEditCompetitionList = new JButton("Edit Competition List");
		JLabel lblEditScoutingNote = new JLabel("Edit Scouting Note");
		JLabel lblTeam = new JLabel("Team: ");
		txtTeam = new JComboBox();
		JLabel lblCompetition = new JLabel("Tournament/Competition: ");
		txtCompetition = new JComboBox();
		JLabel lblMatch = new JLabel("Match: ");
		txtMatchNumber = new JComboBox();
		JButton btnEdit = new JButton("Edit");
		
		// Setting Models
		txtMenu.setModel(new DefaultComboBoxModel(new String[] {"Main Menu", "Scouting", "Team List", "Team View", "Sync", "Settings"}));
		txtTeam.setModel(new DefaultComboBoxModel(Teams));
		
		// Visual Adjustments
		txtMenu.setVisible(false);
		txtTeamNumber.setColumns(10);
		txtTeamName.setColumns(10);
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		if (true) {
			btnBack.setBorderPainted(false);
			btnMenu.setBorderPainted(false);
			btnBack.setBackground(Color.WHITE);
			btnMenu.setBackground(Color.WHITE);
			btnBack.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\back2.png"));
			btnMenu.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\menu2.png"));
			btnBack.setText("");
			btnMenu.setText("");
		}
		
		// Positioning Elements
		springLayout.putConstraint(SpringLayout.NORTH, btnBack, 0, SpringLayout.NORTH, btnMenu);
		springLayout.putConstraint(SpringLayout.NORTH, lblTeam, 0, SpringLayout.NORTH, lblTeamName);
		springLayout.putConstraint(SpringLayout.NORTH, txtTeam, -4, SpringLayout.NORTH, lblTeamName);
		springLayout.putConstraint(SpringLayout.WEST, txtTeam, 6, SpringLayout.EAST, lblTeam);
		springLayout.putConstraint(SpringLayout.EAST, lblCompetition, -301, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblTeam, 0, SpringLayout.WEST, lblCompetition);
		springLayout.putConstraint(SpringLayout.WEST, txtCompetition, 649, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblMatch, 15, SpringLayout.SOUTH, lblCompetition);
		springLayout.putConstraint(SpringLayout.WEST, lblMatch, 0, SpringLayout.WEST, lblTeam);
		springLayout.putConstraint(SpringLayout.NORTH, txtMatchNumber, -4, SpringLayout.NORTH, lblMatch);
		springLayout.putConstraint(SpringLayout.WEST, txtMatchNumber, 6, SpringLayout.EAST, lblMatch);
		springLayout.putConstraint(SpringLayout.WEST, btnBack, 24, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnMenu, 6, SpringLayout.SOUTH, txtMenu);
		springLayout.putConstraint(SpringLayout.EAST, btnMenu, 0, SpringLayout.EAST, txtMenu);
		springLayout.putConstraint(SpringLayout.NORTH, txtMenu, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtMenu, -22, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblAddTeam, 104, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblTeamNumber, 134, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblTeamNumber, 55, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblTeamName, 6, SpringLayout.SOUTH, lblTeamNumber);
		springLayout.putConstraint(SpringLayout.EAST, lblTeamName, -8, SpringLayout.EAST, lblTeamNumber);
		springLayout.putConstraint(SpringLayout.SOUTH, txtTeamNumber, -351, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblAddTeam, -6, SpringLayout.NORTH, txtTeamNumber);
		springLayout.putConstraint(SpringLayout.WEST, txtTeamNumber, 6, SpringLayout.EAST, lblTeamNumber);
		springLayout.putConstraint(SpringLayout.NORTH, txtTeamName, 4, SpringLayout.SOUTH, txtTeamNumber);
		springLayout.putConstraint(SpringLayout.EAST, txtTeamName, 0, SpringLayout.EAST, txtTeamNumber);
		springLayout.putConstraint(SpringLayout.WEST, btnEditAttendanceList, 81, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnEditCompetitionList, 6, SpringLayout.SOUTH, btnEditAttendanceList);
		springLayout.putConstraint(SpringLayout.WEST, btnEditCompetitionList, 0, SpringLayout.WEST, btnEditAttendanceList);
		springLayout.putConstraint(SpringLayout.NORTH, lblEditScoutingNote, 0, SpringLayout.NORTH, lblAddTeam);
		springLayout.putConstraint(SpringLayout.EAST, lblEditScoutingNote, -173, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblCompetition, -295, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtCompetition, -4, SpringLayout.NORTH, lblCompetition);
		springLayout.putConstraint(SpringLayout.NORTH, btnEdit, 0, SpringLayout.NORTH, btnEditCompetitionList);
		springLayout.putConstraint(SpringLayout.EAST, btnEdit, -191, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnAddTeam, 8, SpringLayout.SOUTH, txtTeamName);
		springLayout.putConstraint(SpringLayout.NORTH, btnEditAttendanceList, 38, SpringLayout.SOUTH, btnAddTeam);
		springLayout.putConstraint(SpringLayout.WEST, btnAddTeam, 0, SpringLayout.WEST, lblAddTeam);
		springLayout.putConstraint(SpringLayout.NORTH, lblAdmin, 0, SpringLayout.NORTH, btnMenu);
		springLayout.putConstraint(SpringLayout.WEST, lblAdmin, 448, SpringLayout.WEST, frame.getContentPane());

		// Add Elements to Application Window
		frame.getContentPane().add(lblAdmin);
		frame.getContentPane().add(btnBack);
		frame.getContentPane().add(btnMenu);
		frame.getContentPane().add(txtMenu);
		frame.getContentPane().add(btnAddTeam);
		frame.getContentPane().add(lblAddTeam);
		frame.getContentPane().add(lblTeamNumber);
		frame.getContentPane().add(lblTeamName);
		frame.getContentPane().add(txtTeamNumber);
		frame.getContentPane().add(txtTeamName);
		frame.getContentPane().add(btnEditAttendanceList);
		frame.getContentPane().add(btnEditCompetitionList);
		frame.getContentPane().add(lblEditScoutingNote);
		frame.getContentPane().add(lblTeam);
		frame.getContentPane().add(txtTeam);
		frame.getContentPane().add(lblCompetition);
		frame.getContentPane().add(txtCompetition);
		frame.getContentPane().add(lblMatch);
		frame.getContentPane().add(btnEdit);
		frame.getContentPane().add(txtMatchNumber);
		
		// Button ActionEvents
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
				frame.dispose();
				switch (txtMenu.getSelectedIndex()) {
					case 0:		new MainGUI().main();
								break;
					case 1:		new ScoutingGUI().main("Main Menu", DirectoryLocation);
								break;
					case 2:		new TeamListGUI().main("Main Menu", DirectoryLocation);
								break;
					case 3:		new TeamViewGUI().main("Main Menu", DirectoryLocation, "");
								break;
					case 4:		new SyncGUI().main("Main Menu", DirectoryLocation);
								break;
					case 5:		new SettingsGUI().main("Main Menu", DirectoryLocation);
								break;
				}
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Redirect to Last Screen
				frame.dispose();
				if (Back.equals("Main Menu")) {
					new MainGUI().main();
					
				} else if (Back.equals("Scouting")) {
					new ScoutingGUI().main("Main Menu", DirectoryLocation);
				
				} else if (Back.equals("Team List")) {
					new TeamListGUI().main("Main Menu", DirectoryLocation);
					
				} else if (Back.equals("Team View")) {
					new TeamViewGUI().main("Main Menu", DirectoryLocation, "");
					
				} else if (Back.equals("Sync")) {
					new SyncGUI().main("Main Menu", DirectoryLocation);

				} else if (Back.equals("Settings")) {
					new SettingsGUI().main("Main Menu", DirectoryLocation);
				}
			}
		});
		btnAddTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Files.createDirectories(Paths.get(DirectoryLocation + "\\TeamData\\" + txtTeamNumber.getText() + " – " + txtTeamName.getText()));
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnEditAttendanceList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EditFile(DirectoryLocation + "\\AppData\\Attendance.txt", false);
			}
		});
		btnEditCompetitionList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EditFile(DirectoryLocation + "\\AppData\\Competition.txt", false);
			}
		});
		txtTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Update Screen Based on Selection
				Competitions(DirectoryLocation + "\\TeamData\\" + (String) txtTeam.getSelectedItem());
			}
		});
		txtCompetition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Update Screen Based on Selection
				Matches(DirectoryLocation + "\\TeamData\\" + (String) txtTeam.getSelectedItem() + "\\" + txtCompetition.getSelectedItem());
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Edit Scouting Note
				new ScoutingGUI().main(Back, DirectoryLocation);
				//new ScoutingGUI().main(Back, DirectoryLocation, true, (String) txtTeam.getSelectedItem() + "\\" + (String) txtCompetition.getSelectedItem() + "\\" + (String) txtMatchNumber.getSelectedItem());
			}
		});
		
		// Update Screen
		Competitions(DirectoryLocation + "\\TeamData\\" + (String) txtTeam.getSelectedItem());
		
		// Set Application Window Visible
		frame.setVisible(true);
	}
	
	// Update Competitions List Based on Team Selected
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void Competitions(String TeamCompetitionDataDirectory) {
		
		if (txtTeam.getItemCount() > 0) {
			
			File[] directoryListAsFile = new File(TeamCompetitionDataDirectory).listFiles(new FileFilter() {
		        public boolean accept(File file) {
		            return file.isDirectory();
		        }
		    });
		    
		    List<String> TeamCompetitionList = new ArrayList<String>();
		    
		    for (int i = 0; i < directoryListAsFile.length; i++) {
		    	
		    	if (new File(TeamCompetitionDataDirectory + "\\" + directoryListAsFile[i].getName()).list().length > 0) {
		    		
		    		TeamCompetitionList.add(directoryListAsFile[i].getName());
		    	}
		    }
		    txtCompetition.setModel(new DefaultComboBoxModel(TeamCompetitionList.toArray(new String[TeamCompetitionList.size()])));
		    Matches(TeamCompetitionDataDirectory + "\\" + txtCompetition.getSelectedItem());
		}
	}
	
	// Update Matches List Based on Competition Selected
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void Matches(String TeamCompetitionMatchDataDirectory) {
		
		if (txtCompetition.getItemCount() > 0) {
			
		    File[] directoryListAsFile = new File(TeamCompetitionMatchDataDirectory).listFiles();
		    List<String> TeamCompetitionMatchList = new ArrayList<String>();
			
		    for (File file : directoryListAsFile) {
		        if (file.isFile() && file.getName().contains("txt")) {
		        	TeamCompetitionMatchList.add(file.getName().substring(0, file.getName().length() - 4));
		        }
		    }
		    txtMatchNumber.setModel(new DefaultComboBoxModel(TeamCompetitionMatchList.toArray(new String[TeamCompetitionMatchList.size()])));
		}
	}
	
	// Open File to Edit
	public static void EditFile(String Location, Boolean Sort) {
		
		try {
			ArrayList<String> FileData = new ArrayList<String>();
			
			// Import Data, Remove Sync Value & Export Data into File to Hide Sync Value
			Scanner FileScanner = new Scanner(new File(Location));
			
			while (FileScanner.hasNextLine()) {
				
				FileData.add(FileScanner.nextLine());
			}
			FileScanner.close();
			FileData.remove(0); // Remove Sync Value
			
			BufferedWriter file = new BufferedWriter(new FileWriter(Location));
			
			for (int i = 0; i < FileData.size(); i++) {
				if(FileData.get(i) != null) {
					file.write(FileData.get(i));
					
					if(i < FileData.size() - 1) {
						file.newLine();
					}
				}
			}
			file.close();
			FileData.clear();
			
			// Launch File Edit
			ProcessBuilder Notepad = new ProcessBuilder("Notepad.exe", Location);
			Process EditFile = Notepad.start();
			
			// Monitor IF File Closed
			while (true) {
				
				// Once Closed Add Sync Value
				if (!EditFile.isAlive()) {
					
					// Import Data into ArrayLists
					FileScanner = new Scanner(new File(Location));
					
					while (FileScanner.hasNextLine()) {
						
						FileData.add(FileScanner.nextLine());
					}
					FileScanner.close();
					
					if(Sort) {Collections.sort(FileData);} // Sort in Alphabetical Order IF Requested
					
					// Remove Sync Value & Export Data into File
					FileData.add(0, Long.toString(System.currentTimeMillis()));
					
					file = new BufferedWriter(new FileWriter(Location));
					
					for (int i = 0; i < FileData.size(); i++) {
						if(FileData.get(i) != null) {
							file.write(FileData.get(i));
							
							if(i < FileData.size() - 1) {
								file.newLine();
							}
						}
					}
					file.close();
					break;
				}
			}
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
	}
}