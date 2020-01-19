/***********************************************
**                Team View GUI               **
**                                            **
** Urmil, Manitha, Aiden, Gurnoor, Nived      **
** ICS4U0-A                                   **
** Ver: 4.0 - Jan. 5, 2018                    **
** Last Edit: Jan. 5, 2018                    **
***********************************************/

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class TeamViewGUI {
	
	// Variable Initialization
	@SuppressWarnings("rawtypes")
	private JComboBox txtTeam;
	@SuppressWarnings("rawtypes")
	private JComboBox txtCompetition;
	@SuppressWarnings("rawtypes")
	private JComboBox txtMatchNumber;
	private JTable table;
	private JLabel lblAutonomous;
	private JLabel lblAuton;

	/**
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	public void main(String Back, String DirectoryLocation, String Team) {
		
		// Import Default Team Number
		String LastOpenTeamFileLocation = DirectoryLocation + "\\TeamData\\Team.txt";
		String SettingsFileLocation = DirectoryLocation + "\\AppData\\Settings.txt";
		String[] Settings = new String[4];
		
		if (Team.equals("")) {
			
			try {
				Scanner FileScanner = new Scanner(new File(LastOpenTeamFileLocation));
				
				if (FileScanner.hasNextLine()) {
					Team = FileScanner.nextLine();
				}
				
				if (Team.equals("")) {
					new File(LastOpenTeamFileLocation).createNewFile();
					
					FileScanner = new Scanner(new File(SettingsFileLocation));
					
					for (int i = 0; i < Settings.length; i++) {
						if (FileScanner.hasNextLine()) {
							
							Settings[i] = FileScanner.nextLine();	
						}
					}
					Team = Settings[2];
				}
				FileScanner.close();
			} catch (IOException e1) {
				
				try {
					new File(LastOpenTeamFileLocation).createNewFile();
					
					Scanner FileScanner = new Scanner(new File(SettingsFileLocation));
					
					for (int i = 0; i < Settings.length; i++) {
						if (FileScanner.hasNextLine()) {
							
							Settings[i] = FileScanner.nextLine();	
						}
					}
					Team = Settings[2];
					
					FileScanner.close();
				} catch (IOException e2) {
					
					try {
						new File(SettingsFileLocation).createNewFile();
					} catch (IOException e3) {
						
						e3.printStackTrace();
					}
				}
			}
		}
		
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
		JFrame frame = new JFrame("Team View");
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(x, y, 960, 613);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		// Initialize Elements
		JButton btnBack = new JButton("");
		JButton btnMenu = new JButton("");
		JComboBox txtMenu = new JComboBox();
		JLabel lblTeamTitle = new JLabel();
		JLabel lblTeam = new JLabel("Team: ");
		txtTeam = new JComboBox();
		JLabel lblCompetition = new JLabel("Tournament/Competition: ");
		txtCompetition = new JComboBox();
		JLabel lblMatchNumber = new JLabel("Match Number:");
		txtMatchNumber = new JComboBox();
		lblAutonomous = new JLabel("Autonomous");
		lblAuton = new JLabel();
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -58, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, txtMatchNumber, -23, SpringLayout.NORTH, scrollPane);
		table = new JTable()
		{
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				
				Component comp = super.prepareRenderer(renderer, row, column);
				comp.setBackground(row % 2 == 0 ? Color.white : Color.LIGHT_GRAY);
				return comp;
			}
		};
		
		// Set Models
		txtMenu.setModel(new DefaultComboBoxModel(new String[] {"Main Menu", "Scouting", "Team List", "Sync", "Settings"}));
		txtTeam.setModel(new DefaultComboBoxModel(Teams));
		
		// Visual Adjustments
		txtMenu.setVisible(false);
		btnBack.setBorderPainted(false);
		btnMenu.setBorderPainted(false);
		btnBack.setBackground(Color.WHITE);
		btnMenu.setBackground(Color.WHITE);
		lblTeamTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamTitle.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 17));
		btnBack.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\back2.png"));
		btnMenu.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\menu2.png"));
		
		// Positioning Elements
		springLayout.putConstraint(SpringLayout.NORTH, lblTeamTitle, 0, SpringLayout.NORTH, btnBack);
		springLayout.putConstraint(SpringLayout.WEST, lblTeamTitle, 0, SpringLayout.EAST, btnBack);
		springLayout.putConstraint(SpringLayout.EAST, lblTeamTitle, 0, SpringLayout.WEST, btnMenu);
		springLayout.putConstraint(SpringLayout.SOUTH, lblAutonomous, 0, SpringLayout.SOUTH, txtCompetition);
		springLayout.putConstraint(SpringLayout.EAST, lblAutonomous, -218, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblAuton, 174, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblAuton, -67, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblTeam, -27, SpringLayout.NORTH, lblCompetition);
		springLayout.putConstraint(SpringLayout.WEST, lblCompetition, 0, SpringLayout.WEST, lblTeam);
		springLayout.putConstraint(SpringLayout.EAST, lblCompetition, -728, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnBack, 41, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnBack, 33, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnBack, 96, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnMenu, -63, SpringLayout.EAST, txtMenu);
		springLayout.putConstraint(SpringLayout.NORTH, lblTeam, 37, SpringLayout.SOUTH, btnBack);
		springLayout.putConstraint(SpringLayout.WEST, lblTeam, 70, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtTeam, 100, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtTeam, 6, SpringLayout.EAST, lblTeam);
		springLayout.putConstraint(SpringLayout.NORTH, txtCompetition, -4, SpringLayout.NORTH, lblCompetition);
		springLayout.putConstraint(SpringLayout.WEST, txtCompetition, 6, SpringLayout.EAST, lblCompetition);
		springLayout.putConstraint(SpringLayout.SOUTH, lblCompetition, -20, SpringLayout.NORTH, lblMatchNumber);
		springLayout.putConstraint(SpringLayout.WEST, lblMatchNumber, 70, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblMatchNumber, 0, SpringLayout.NORTH, txtMatchNumber);
		springLayout.putConstraint(SpringLayout.EAST, lblMatchNumber, -6, SpringLayout.WEST, txtMatchNumber);
		springLayout.putConstraint(SpringLayout.WEST, txtMatchNumber, 162, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 231, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 67, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -487, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnMenu, 6, SpringLayout.SOUTH, txtMenu);
		springLayout.putConstraint(SpringLayout.EAST, btnMenu, 0, SpringLayout.EAST, txtMenu);
		springLayout.putConstraint(SpringLayout.NORTH, txtMenu, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtMenu, -33, SpringLayout.EAST, frame.getContentPane());
		
		// Add Elements to Application Window
		frame.getContentPane().add(btnBack);
		frame.getContentPane().add(btnMenu);
		frame.getContentPane().add(txtMenu);
		frame.getContentPane().add(lblTeam);
		frame.getContentPane().add(txtTeam);
		frame.getContentPane().add(lblCompetition);
		frame.getContentPane().add(txtCompetition);
		frame.getContentPane().add(lblMatchNumber);
		frame.getContentPane().add(txtMatchNumber);
		frame.getContentPane().add(lblTeamTitle);
		frame.getContentPane().add(lblAutonomous);
		frame.getContentPane().add(lblAuton);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		// ActionEvents
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
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
					case 1:		new ScoutingGUI().main("Team View", DirectoryLocation);
								break;
					case 2:		new TeamListGUI().main("Team View", DirectoryLocation);
								break;
					case 3:		new SyncGUI().main("Team View", DirectoryLocation);
								break;
					case 4:		new SettingsGUI().main("Team View", DirectoryLocation);
								break;
				}
				
				// Save Currently Opened Team
				if (txtTeam.getItemCount() > 0) {
					
					try {
						BufferedWriter file = new BufferedWriter(new FileWriter(LastOpenTeamFileLocation));
						file.write((String) txtTeam.getSelectedItem());
						file.close();
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
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
					new ScoutingGUI().main("Team View", DirectoryLocation);
				
				} else if (Back.equals("Team List")) {
					new TeamListGUI().main("Team View", DirectoryLocation);
					
				} else if (Back.equals("Sync")) {
					new SyncGUI().main("Team View", DirectoryLocation);
					
				} else if (Back.equals("Settings")) {
					new SettingsGUI().main("Team View", DirectoryLocation);
				}
				
				// Save Currently Opened Team
				if (txtTeam.getItemCount() > 0) {
					
					try {
						BufferedWriter file = new BufferedWriter(new FileWriter(LastOpenTeamFileLocation));
						file.write((String) txtTeam.getSelectedItem());
						file.close();
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
		txtTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Update Screen Based on Selection
				lblTeamTitle.setText((String) txtTeam.getSelectedItem());
				Competitions(DirectoryLocation + "\\TeamData\\" + (String) txtTeam.getSelectedItem());
			}
		});
		txtCompetition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Update Screen Based on Selection			    
				Matches(DirectoryLocation + "\\TeamData\\" + (String) txtTeam.getSelectedItem() + "\\" + txtCompetition.getSelectedItem());
			}
		});
		txtMatchNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Update Screen Based on Selection
				Note(DirectoryLocation, (String) txtTeam.getSelectedItem(), (String) txtCompetition.getSelectedItem(), (String) txtMatchNumber.getSelectedItem());
			}
		});
		
		// Update Screen
		txtTeam.setSelectedItem(Team);
		
		Competitions(DirectoryLocation + "\\TeamData\\" + (String) txtTeam.getSelectedItem());
		
		// Set Application Window Visible
		frame.setVisible(true);
		
		// No Team Data Alert IF NO Database Error
		if (txtTeam.getItemCount() == 0 && !new MainGUI().DatabaseCheck(frame, "Team View")) {
		
			lblTeamTitle.setText("No Team Data!");
			JOptionPane.showMessageDialog(null, "No Team Data!");
			frame.dispose();
			new ScoutingGUI().main("Team View", DirectoryLocation);
		}
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
		    
		    String DirectoryLocation = TeamCompetitionMatchDataDirectory.substring(0, TeamCompetitionMatchDataDirectory.length() - 11 - ((String) txtTeam.getSelectedItem()).length() - ((String) txtCompetition.getSelectedItem()).length());
		    Note(DirectoryLocation, (String) txtTeam.getSelectedItem(), (String) txtCompetition.getSelectedItem(), (String) txtMatchNumber.getSelectedItem());
		}
	}
	
	// Update Display Based on Note Selected
	public void Note(String DirectoryLocation, String Team, String Competition, String MatchNumber) {
		
		String NoteLocation = DirectoryLocation + "\\TeamData\\" + (String) txtTeam.getSelectedItem() + "\\" + (String) txtCompetition.getSelectedItem() + "\\" + (String) txtMatchNumber.getSelectedItem();
		String[] Note = new String[22];
		String[] DefaultData = {"Scouter", "Rating", "Design", "Stalling", "Stalling Details", "Driver Skill", "Intake", "Average Cone/Sec", "Average Mobile Goal Time", "Zone",
							"Flip", "Drive Speed", "Lift Speed", "Highest Mobile Stack", "Highest Pole Stack", "Game Strategy/Playing Style", "Additional Information"};
		
		// Import Scouting Note according to Team, Competition & Match Selections
		try {
			Scanner FileScanner = new Scanner(new File(NoteLocation + ".txt"));
			
			for (int i = 0; i < Note.length; i++) {
				if (FileScanner.hasNextLine()) {
					Note[i] = FileScanner.nextLine();
				} else {
					Note[i] = "No Data";
				}
			}
			FileScanner.close();
			Note[5] = Note[2]; // Adjustment to offset the Array
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		// 2D Array for Table
		String[][] TableData = new String[DefaultData.length][2]; 
		
		for (int i = 0; i < DefaultData.length; i++) {
			
			TableData[i][0] = DefaultData[i];
			TableData[i][1] = Note[i + 5];
		}
		
		// Set Table
		table.setModel(new DefaultTableModel(TableData,
			new String[] {
				"Information", "Team Details"
			}));
		
		// Visual Adjustments
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(220);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(175);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(false);
		
		lblAuton.setIcon(new ImageIcon(NoteLocation + ".png"));
	}
}