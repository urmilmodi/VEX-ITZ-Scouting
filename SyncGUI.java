/***********************************************
**                 Sync GUI                   **
**                                            **
** Urmil, Manitha, Aiden, Gurnoor, Nived      **
** ICS4U0-A                                   **
** Ver: 4.0 - Jan. 5, 2018                    **
** Last Edit: Jan. 5, 2018                    **
***********************************************/

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SpringLayout;

public class SyncGUI {
	
	// Variable Initialization
	private int SyncCounter = 1;
	private JProgressBar progressBar;
	private int TotalFiles = 3;
	private int FilesProcessed = 0;
	private int progressValue = 0;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void main(String Back, String DirectoryLocation) {
		
		// Import Drives
		String[] Drives = new String[File.listRoots().length];
		
		if (File.listRoots() != null && File.listRoots().length > 0) {
			int i = 0;
		    for (File Drive : File.listRoots()) {
		    	
		        Drives[i] = Drive.toString().substring(0, 2);
		        i++;
		    }
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
		JFrame frame = new JFrame("Sync");
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(x, y, 573, 308);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		// Initialize Elements
		JLabel lblSync = new JLabel("Sync");
		JButton btnBack = new JButton("");
		progressBar = new JProgressBar();
		JButton btnMenu = new JButton("");
		JComboBox txtMenu = new JComboBox();
		JLabel lblDrive = new JLabel("Drive: ");
		JComboBox txtDrive = new JComboBox();
		JLabel lblStorage = new JLabel("Storage: ");
		JButton btnSync = new JButton("");
		
		// Visual Adjustments & Setting Models
		txtMenu.setVisible(false);
		progressBar.setVisible(false);
		btnBack.setBorderPainted(false);
		btnMenu.setBorderPainted(false);
		btnSync.setBorderPainted(false);
		progressBar.setBackground(Color.WHITE);
		btnBack.setBackground(Color.WHITE);
		btnMenu.setBackground(Color.WHITE);
		btnSync.setBackground(Color.WHITE);
		progressBar.setForeground(new Color(0, 255, 0));
		btnBack.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\back2.png"));
		btnMenu.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\menu2.png"));
		btnSync.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\SYNC2.png"));
		txtMenu.setModel(new DefaultComboBoxModel(new String[] {"Main Menu", "Scouting", "Team List", "Team View", "Settings"}));
		txtDrive.setModel(new DefaultComboBoxModel(Drives));
		File Drive = new File((String) txtDrive.getSelectedItem());
		lblStorage.setText("Storage: " + new DecimalFormat("#.0").format((100.0 - 100.0*Drive.getFreeSpace()/Drive.getTotalSpace())) + "%" + " (" + Math.round((Drive.getTotalSpace() - Drive.getFreeSpace())/1073741824) + "/" + Math.round(Drive.getTotalSpace()/1073741824) + " GB)");
		
		// Positioning Elements
		springLayout.putConstraint(SpringLayout.NORTH, txtDrive, -4, SpringLayout.NORTH, lblDrive);
		springLayout.putConstraint(SpringLayout.WEST, txtDrive, 6, SpringLayout.EAST, lblDrive);
		springLayout.putConstraint(SpringLayout.NORTH, lblStorage, 181, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblStorage, 63, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnSync, 202, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnSync, 218, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnSync, 224, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSync, 301, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblDrive, 146, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblDrive, 63, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtMenu, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtMenu, 394, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnMenu, 43, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnMenu, 425, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnMenu, 69, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnMenu, 485, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnBack, 43, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnBack, 63, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnBack, 69, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnBack, 109, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, progressBar, 88, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, progressBar, 73, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, progressBar, 119, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, progressBar, 495, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblSync, 33, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblSync, 258, SpringLayout.WEST, frame.getContentPane());
		
		// Add Elements to Application Window
		frame.getContentPane().add(lblSync);
		frame.getContentPane().add(progressBar);
		frame.getContentPane().add(btnBack);
		frame.getContentPane().add(btnMenu);
		frame.getContentPane().add(txtMenu);
		frame.getContentPane().add(lblDrive);
		frame.getContentPane().add(txtDrive);
		frame.getContentPane().add(lblStorage);
		frame.getContentPane().add(btnSync);
		
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
				frame.dispose();
				switch (txtMenu.getSelectedIndex()) {
					case 0:		new MainGUI().main();
								break;
					case 1:		new ScoutingGUI().main("Sync", DirectoryLocation);
								break;
					case 2:		new TeamListGUI().main("Sync", DirectoryLocation);
								break;
					case 3:		new TeamViewGUI().main("Sync", DirectoryLocation, "");
								break;
					case 4:		new SettingsGUI().main("Sync", DirectoryLocation);
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
					new ScoutingGUI().main("Sync", DirectoryLocation);
				
				} else if (Back.equals("Team List")) {
					new TeamListGUI().main("Sync", DirectoryLocation);
					
				} else if (Back.equals("Team View")) {
					new TeamViewGUI().main("Sync", DirectoryLocation, "");
					
				} else if (Back.equals("Settings")) {
					new SettingsGUI().main("Sync", DirectoryLocation);
				}
			}
		});
		txtDrive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Displays Drive Data
				File Drive = new File((String) txtDrive.getSelectedItem());
				
				if ((100.0 - 100.0*Drive.getFreeSpace()/Drive.getTotalSpace()) > 1.0) {
					
					lblStorage.setText("Storage: " + new DecimalFormat("#.0").format((100.0 - 100.0*Drive.getFreeSpace()/Drive.getTotalSpace())) + "%" + " (" + Math.round((Drive.getTotalSpace() - Drive.getFreeSpace())/1073741824) + "/" + Math.round(Drive.getTotalSpace()/1073741824) + " GB)");
				
				} else {
					
					lblStorage.setText("Storage: 0" + new DecimalFormat("#.0").format((100.0 - 100.0*Drive.getFreeSpace()/Drive.getTotalSpace())) + "%" + " (" + Math.round((Drive.getTotalSpace() - Drive.getFreeSpace())/1073741824) + "/" + Math.round(Drive.getTotalSpace()/1073741824) + " GB)");
				}
				
				// Clear Progress Bar
				progressBar.setValue(0);
				progressBar.setVisible(false);
			}
		});
		btnSync.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Setting Source and Destination Directory Locations
				String SourceDirectoryLocation = DirectoryLocation;
				String DestinationDirectoryLocation = (String) txtDrive.getSelectedItem() + "\\Scouting App\\Data";
				
				// Display Progress Bar
				progressBar.setVisible(true);
				progressBar.setValue(0);
				progressValue = 0;

				// Sync Laptop & USB
				Sync(SourceDirectoryLocation, DestinationDirectoryLocation);
				
				// Sync Process Completion Alert
				JOptionPane.showMessageDialog(null, "Sync Process Complete");
				
				// Update Drives
				String[] Drives = new String[File.listRoots().length];
				
				if (File.listRoots() != null && File.listRoots().length > 0) {
					int i = 0;
				    for (File Drive : File.listRoots()) {
				    	
				        Drives[i] = Drive.toString().substring(0, 2);
				        i++;
				    }
				}
				
				// Reset Drive Selection
				String DriveSelected = (String) txtDrive.getSelectedItem();
				txtDrive.setModel(new DefaultComboBoxModel(Drives));
				txtDrive.setSelectedItem(DriveSelected);
			}
		});
		
		// Set Application Window Visible
		frame.setVisible(true);
		
		new MainGUI().DatabaseCheck(frame, "Sync");
	}
	
	// File Sync Algorithm Manager
	public void Sync(String SourceDirectoryLocation, String DestinationDirectoryLocation) {
		
		// While Loop to Repeat Sync Code (Between the Source to Destination and the Destination to Source)
		while (SyncCounter < 3) {
			
			// Import Teams List
			String SourceTeamDirectory = SourceDirectoryLocation + "\\TeamData";
		    
		    File[] directoryListAsFile = new File(SourceTeamDirectory).listFiles(new FileFilter() {
		        public boolean accept(File file) {
		            return file.isDirectory();
		        }
		    });
		    
		    String [] Teams = new String[directoryListAsFile.length];
		    
		    for (int i = 0; i < directoryListAsFile.length; i++) {
		    	
		    	Teams[i] = directoryListAsFile[i].getName();
		    }
		    
		    // For Loop to Repeat Through All Competitions & Matches for Each Team
		    for (int i = 0; i < Teams.length; i++) {
		    	
		    	// Destination Team Directory
		    	String DestinationTeamDirectory = DestinationDirectoryLocation + "\\TeamData\\" + Teams[i];
		    	
		    	try {
					Files.createDirectories(Paths.get(DestinationTeamDirectory));
					Files.setAttribute(FileSystems.getDefault().getPath(DestinationDirectoryLocation.substring(0, DestinationDirectoryLocation.length() - 5)), "dos:hidden", true);
					Files.setAttribute(FileSystems.getDefault().getPath(DestinationDirectoryLocation), "dos:hidden", true);
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		    	
		    	// Import Competitions List
		    	String SourceTeamCompetitionDataDirectory = SourceDirectoryLocation + "\\TeamData\\" + Teams[i];
			    
				directoryListAsFile = new File(SourceTeamCompetitionDataDirectory).listFiles(new FileFilter() {
			        public boolean accept(File file) {
			            return file.isDirectory();
			        }
			    });
			    
			    List<String> TeamCompetitionList = new ArrayList<String>();
			    
			    for (int j = 0; j < directoryListAsFile.length; j++) {
			    	
			    	if (new File(SourceTeamCompetitionDataDirectory + "\\" + directoryListAsFile[j].getName()).list().length > 0) {
			    		
			    		TeamCompetitionList.add(directoryListAsFile[j].getName());
			    	}
			    }
				
				// For Loop to Repeat Through All Matches for Each Team at Each Competition
				for (int k = 0; k < TeamCompetitionList.size(); k++) {
					
					// Import Matches
					String SourceTeamCompetitionMatchDirectory = SourceDirectoryLocation + "\\TeamData\\" + Teams[i] + "\\" + TeamCompetitionList.get(k);
				    directoryListAsFile = new File(SourceTeamCompetitionMatchDirectory).listFiles();
				    
				    // File Calculation for Progress Bar 
				    TotalFiles = 3;
				    for (File afile : directoryListAsFile) {if (afile.isFile() && afile.getName().endsWith("txt")) {TotalFiles++;}}
				    FilesProcessed = 0;
				    
				    for (File file : directoryListAsFile) {
				        if (file.isFile()) {
				        	
				        	String DestinationFilePath = DestinationDirectoryLocation + "\\TeamData\\" + Teams[i] + "\\" + TeamCompetitionList.get(k);
				        	
				        	// Create Directory & Sync File
							try {
								Files.createDirectories(Paths.get(DestinationFilePath));
								if (file.getName().endsWith("txt")) {
									
									// Sync File Using Sync Method
									SyncFile("Scouting Note", file.getAbsolutePath(), DestinationFilePath + "\\" + file.getName());
									
									// Update Progress Bar Values According to Files Processed
									FilesProcessed++;
									progressBar.setValue(50*FilesProcessed/TotalFiles + progressValue);
								}
							} catch (IOException e1) {
								
								e1.printStackTrace();
							}
				        }
				    }
				}
		    }
		    
		    // Sync Application Data Files (Important Files)
		    try {
		    	String SourceAppDataDirectory = SourceDirectoryLocation + "\\AppData";
		    	String DestinationAppDataDirectory = DestinationDirectoryLocation + "\\AppData";
		    	String[] AppData = {"\\Attendance.txt", "\\Competition.txt", "\\Field.png"};
		    	
		    	// Create Directory & Sync File
		    	Files.createDirectories(Paths.get(DestinationAppDataDirectory));
		    	for (int i = 0; i < AppData.length; i++) {
		    		
		    		// Sync File Using Sync Method
					SyncFile("AppData", SourceAppDataDirectory + AppData[i], DestinationAppDataDirectory + AppData[i]);
					
					// Update Progress Bar Values According to Files Processed
					FilesProcessed++;
					progressBar.setValue(50*FilesProcessed/TotalFiles + progressValue);
		    	}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		    
			SyncCounter++; // Counter
			
			progressValue = 50; // Set ProgressValue to 50, as Sync Process is Half Complete
			
			// Swap Source with Destination To Repeat Process in Reverse Direction and Complete Sync Process
			DestinationDirectoryLocation += SourceDirectoryLocation;
			SourceDirectoryLocation = DestinationDirectoryLocation.substring(0, DestinationDirectoryLocation.length() - SourceDirectoryLocation.length());
			DestinationDirectoryLocation = DestinationDirectoryLocation.substring(SourceDirectoryLocation.length());
		}
		SyncCounter = 1; // Reset Counter to Sync Multiple Times
	}
	
	// File Sync Algorithm
	public void SyncFile(String Type, String SourceFilePath, String DestinationFilePath) throws IOException {
		
		// IF File Missing on Source or Destination Side
		if (!new File(SourceFilePath).exists() || !new File(DestinationFilePath).exists()) {
			
			// IF Destination File missing and Source Available, copy Source File to Destination
			if (new File(SourceFilePath).exists() && !new File(DestinationFilePath).exists()) {
				
				Files.copy(new File(SourceFilePath).toPath(), new File(DestinationFilePath).toPath());
				if (Type.equals("Scouting Note")) {Files.copy(new File(SourceFilePath.substring(0, SourceFilePath.length() - 3) + "png").toPath(), new File(DestinationFilePath.substring(0, DestinationFilePath.length() - 3) + "png").toPath());}
				
			} else if (!new File(SourceFilePath).exists() && new File(DestinationFilePath).exists()) {
				
				// IF Source File missing and Destination Available, Do NOTHING
				// When process is repeated in the Reverse Direction, will lead to case above as Source and Destination swap
				
			// IF Both Files are missing, delete Metadata files
			} else if (!new File(SourceFilePath).exists() && !new File(DestinationFilePath).exists()) {
				
				while (Type.equals("Scouting Note") && new File(SourceFilePath.substring(0, SourceFilePath.length() - 3) + "png").exists() || new File(DestinationFilePath.substring(0, DestinationFilePath.length() - 3) + "png").exists()) {
					
					Delete(SourceFilePath.substring(0, SourceFilePath.length() - 3) + "png");
					Delete(DestinationFilePath.substring(0, DestinationFilePath.length() - 3) + "png");
					Delay(1);
				}
			}
		} else {
			
			// Delete Source as USB will contain updated version IF exists, next round will get copied from USB to Source
			if (SyncCounter == 1 && SourceFilePath.endsWith("png") && DestinationFilePath.endsWith("png")) {
					
				while (new File(SourceFilePath).exists()) {
				
					Delete(SourceFilePath);
					Delay(1);
				}
				
			} else if (SourceFilePath.endsWith("txt") && DestinationFilePath.endsWith("txt")) {
				
				// IF issues with Metadata, delete Source as USB will contain updated version IF exists, next round will get copied from USB to Source
				if (!new File(SourceFilePath.substring(0, SourceFilePath.length() - 3) + "png").exists() && new File(DestinationFilePath.substring(0, DestinationFilePath.length() - 3) + "png").exists()) {
					
					while (new File(SourceFilePath).exists()) {
						
						Delete(SourceFilePath);
						Delay(1);
					}
					
				} else { // Process for IF both Files Exist & Are Valid
					
					// Source & Destination ArrayList Initializations
					ArrayList<String> SourceFileData = new ArrayList<String>();
					ArrayList<String> DestinationFileData = new ArrayList<String>();
					
					// Import Data into ArrayLists
					Scanner FileScanner = new Scanner(new File(SourceFilePath));
					
					while (FileScanner.hasNextLine()) {
						
						SourceFileData.add(FileScanner.nextLine());
					}
					
					FileScanner = new Scanner(new File(DestinationFilePath));
					
					while (FileScanner.hasNextLine()) {
						
						DestinationFileData.add(FileScanner.nextLine());
					}
					FileScanner.close();
					
					// IF Source File is more recent, delete Destination File and repeat Sync method until so Source file is copied to Destination (Recursion)
					if (Long.parseLong(SourceFileData.get(0)) > Long.parseLong(DestinationFileData.get(0))) {
						
						Delete(DestinationFilePath);
						if (Type.equals("Scouting Note")) { // IF Scouting File, delete Scouting Metadata as Well
							
							Delete(DestinationFilePath.substring(0, DestinationFilePath.length() - 3) + "png");
							while (new File(DestinationFilePath.substring(0, DestinationFilePath.length() - 3) + "png").exists() && !new File(DestinationFilePath).exists()) {
								
								Delete(DestinationFilePath.substring(0, DestinationFilePath.length() - 3) + "png");
								Delay(1);
							}
						}
						
						/*new File(DestinationFilePath).delete();
						if (Type.equals("Scouting Note")) {new File(DestinationFilePath.substring(0, DestinationFilePath.length() - 3) + "png").delete();}*/
						
						SyncFile(Type, SourceFilePath, DestinationFilePath); // Repeat Sync Function, as Destination File is missing so case above will handle it :)
					
					// IF Destination File is more recent, delete Source File
					// IMPORTANT: No need to repeat function, as next round file will be copied from Desination to Source
					} else if (Long.parseLong(SourceFileData.get(0)) < Long.parseLong(DestinationFileData.get(0))) {
						
						Delete(SourceFilePath);
						if (Type.equals("Scouting Note") && !new File(SourceFilePath).exists()) { // IF Scouting File, delete Scouting Metadata as well
							
							while (new File(SourceFilePath.substring(0, SourceFilePath.length() - 3) + "png").exists()) {
								
								Delete(SourceFilePath.substring(0, SourceFilePath.length() - 3) + "png");
								Delay(1);
							}
						}
						
						/*new File(SourceFilePath).delete();
						if (Type.equals("Scouting Note")) {new File(SourceFilePath.substring(0, SourceFilePath.length() - 3) + "png").delete();}*/
						
					// IF Both Files Created Exact Same Milisecond (Will Occur 1 in a Billion Times)
					} else if (Long.parseLong(SourceFileData.get(0)) == Long.parseLong(DestinationFileData.get(0))) {
						
						// Only Possible IF Files Already In Sync, So Don't Do Anything :)
					}
				}
			}
		}
	}
	
	// Delete Method (Files Accessed When Delete Requests Are Made)
	public static void Delete(String Location) {
		
		if (new File(Location).exists()) {new File(Location).delete();}
	}
	
	// Delay Method to Prevent StackOverFlow
	public static void Delay(int Seconds) {
		
		try {
			Thread.sleep(Seconds);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
}