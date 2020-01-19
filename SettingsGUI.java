/***********************************************
**                Settings GUI                **
**                                            **
** Urmil, Manitha, Aiden, Gurnoor, Nived      **
** ICS4U0-A                                   **
** Ver: 1.0 - Sept. 27, 2017                  **
** Last Edit: Sept. 27, 2017                  **
***********************************************/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class SettingsGUI {
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void main(String Back, String DirectoryLocation) {
		
		// Import Settings Data
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
		} catch (FileNotFoundException e2) {
			try {
				new File(SettingsFileLocation).createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		// Import Application Data
		String AttendanceFileLocation = DirectoryLocation + "\\AppData\\Attendance.txt";
		String CompetitionFileLocation = DirectoryLocation + "\\AppData\\Competition.txt";
		ArrayList<String> AttendanceList = new ArrayList<String>();
		ArrayList<String> CompetitionList = new ArrayList<String>();
		
		try {
			Scanner FileScanner = new Scanner(new File(AttendanceFileLocation));
			
			while (FileScanner.hasNextLine()) {
				
				AttendanceList.add(FileScanner.nextLine());
			}
			
			AttendanceList.remove(0);
			
			FileScanner.close();
		} catch (FileNotFoundException e2) {
			
			try {
				new File(AttendanceFileLocation).createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		try {
			Scanner FileScanner = new Scanner(new File(CompetitionFileLocation));
			
			while (FileScanner.hasNextLine()) {
				
				CompetitionList.add(FileScanner.nextLine());
			}
			
			CompetitionList.remove(0);
			
			FileScanner.close();
		} catch (FileNotFoundException e2) {
			
			try {
				new File(CompetitionFileLocation).createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
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
		JFrame frame = new JFrame("Settings");
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(x, y, 647, 323);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		// Initialize Elements
		JLabel lblSettings = new JLabel("Settings");
		JLabel lblScouterName = new JLabel("Scouter Name:");
		JComboBox txtScouterName = new JComboBox();
		JLabel lblTeamNumber = new JLabel("Team Number:");
		JTextField txtTeamNumber = new JTextField("");
		JLabel lblCompetition = new JLabel("Tournament/Competition:");
		JComboBox txtCompetition = new JComboBox();
		JButton btnSave = new JButton("");
		JComboBox txtMenu = new JComboBox();
		JButton btnMenu = new JButton("");
		JButton btnBack = new JButton("");
		
		// Setting Models
		txtScouterName.setModel(new DefaultComboBoxModel(AttendanceList.toArray(new String[0])));
		txtCompetition.setModel(new DefaultComboBoxModel(CompetitionList.toArray(new String[0])));
		txtMenu.setModel(new DefaultComboBoxModel(new String[] {"Main Menu", "Scouting", "Team List", "Team View", "Sync"}));
		
		// Visual Adjustments
		txtMenu.setVisible(false);
		txtTeamNumber.setColumns(10);
		btnSave.setBorderPainted(false);
		btnBack.setBorderPainted(false);
		btnMenu.setBorderPainted(false);
		lblSettings.setForeground(Color.WHITE);
		btnBack.setBackground(Color.WHITE);
		btnSave.setBackground(Color.WHITE);
		btnMenu.setBackground(Color.WHITE);
		lblScouterName.setForeground(Color.BLACK);
		lblTeamNumber.setForeground(Color.BLACK);
		lblCompetition.setForeground(Color.BLACK);
		lblSettings.setFont(new Font("Cambria", Font.BOLD, 17));
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		btnBack.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\back2.png"));
		btnSave.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\save.png"));
		btnMenu.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\menu2.png"));
		
		// Positioning Elements
		springLayout.putConstraint(SpringLayout.WEST, lblSettings, 328, SpringLayout.EAST, btnBack);
		springLayout.putConstraint(SpringLayout.SOUTH, btnMenu, 0, SpringLayout.SOUTH, btnBack);
		springLayout.putConstraint(SpringLayout.NORTH, btnBack, 30, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnBack, -40, SpringLayout.NORTH, lblScouterName);
		springLayout.putConstraint(SpringLayout.WEST, btnBack, 29, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnBack, -26, SpringLayout.EAST, lblScouterName);
		springLayout.putConstraint(SpringLayout.NORTH, lblSettings, 30, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblScouterName, 30, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, txtCompetition, -23, SpringLayout.NORTH, btnSave);
		springLayout.putConstraint(SpringLayout.SOUTH, lblCompetition, -32, SpringLayout.NORTH, btnSave);
		springLayout.putConstraint(SpringLayout.SOUTH, txtTeamNumber, -58, SpringLayout.NORTH, btnSave);
		springLayout.putConstraint(SpringLayout.SOUTH, lblTeamNumber, -62, SpringLayout.NORTH, btnSave);
		springLayout.putConstraint(SpringLayout.SOUTH, txtScouterName, -88, SpringLayout.NORTH, btnSave);
		springLayout.putConstraint(SpringLayout.SOUTH, lblScouterName, -97, SpringLayout.NORTH, btnSave);
		springLayout.putConstraint(SpringLayout.WEST, btnSave, 213, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnSave, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblSettings, -187, SpringLayout.WEST, btnMenu);
		springLayout.putConstraint(SpringLayout.WEST, btnMenu, 356, SpringLayout.WEST, txtCompetition);
		springLayout.putConstraint(SpringLayout.EAST, btnMenu, -33, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtTeamNumber, 0, SpringLayout.WEST, txtScouterName);
		springLayout.putConstraint(SpringLayout.NORTH, txtMenu, -6, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtMenu, -33, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCompetition, 0, SpringLayout.WEST, lblScouterName);
		springLayout.putConstraint(SpringLayout.WEST, lblTeamNumber, 0, SpringLayout.WEST, lblScouterName);
		springLayout.putConstraint(SpringLayout.WEST, txtScouterName, 10, SpringLayout.EAST, lblScouterName);
		springLayout.putConstraint(SpringLayout.NORTH, lblScouterName, 0, SpringLayout.NORTH, txtScouterName);
		springLayout.putConstraint(SpringLayout.NORTH, lblTeamNumber, 0, SpringLayout.NORTH, txtTeamNumber);
		springLayout.putConstraint(SpringLayout.NORTH, txtTeamNumber, 10, SpringLayout.SOUTH, txtScouterName);
		springLayout.putConstraint(SpringLayout.WEST, txtCompetition, 10, SpringLayout.EAST, lblCompetition);
		springLayout.putConstraint(SpringLayout.NORTH, lblCompetition, 0, SpringLayout.NORTH, txtCompetition);
		springLayout.putConstraint(SpringLayout.NORTH, txtCompetition, 10, SpringLayout.SOUTH, txtTeamNumber);
		
		// Add Elements to Application Window
		frame.getContentPane().add(lblSettings);
		frame.getContentPane().add(lblScouterName);
		frame.getContentPane().add(txtScouterName);
		frame.getContentPane().add(lblTeamNumber);
		frame.getContentPane().add(txtTeamNumber);
		frame.getContentPane().add(lblCompetition);
		frame.getContentPane().add(txtCompetition);
		frame.getContentPane().add(btnSave);
		frame.getContentPane().add(txtMenu);
		frame.getContentPane().add(btnMenu);
		frame.getContentPane().add(btnBack);

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
			public void actionPerformed(ActionEvent arg0) {
				
				// Redirect to Selected Screen
				frame.dispose();
				switch (txtMenu.getSelectedIndex()) {
					case 0:		new MainGUI().main();
								break;
					case 1:		new ScoutingGUI().main("Settings", DirectoryLocation);
								break;
					case 2:		new TeamListGUI().main("Settings", DirectoryLocation);
								break;
					case 3:		new TeamViewGUI().main("Settings", DirectoryLocation, "");
								break;
					case 4:		new SyncGUI().main("Settings", DirectoryLocation);
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
					new ScoutingGUI().main("Settings", DirectoryLocation);
				
				} else if (Back.equals("Team List")) {
					new TeamListGUI().main("Settings", DirectoryLocation);
					
				} else if (Back.equals("Team View")) {
					new TeamViewGUI().main("Settings", DirectoryLocation, "");
					
				} else if (Back.equals("Sync")) {
					new SyncGUI().main("Settings", DirectoryLocation);
				}
			}
		});
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Storing Variables in Array
				String[] Settings = {new SimpleDateFormat("dd/MM/YYYY").format(new Date()), (String) txtScouterName.getSelectedItem(), txtTeamNumber.getText(), (String) txtCompetition.getSelectedItem()};
				
				// Saving Settings Data
				try {
					BufferedWriter file = new BufferedWriter(new FileWriter(SettingsFileLocation));
					
					for (int i = 0; i < Settings.length; i++) {
						if(Settings[i] != null) {
							file.write(Settings[i]);
							
							if(i < Settings.length - 1) {
								file.newLine();
							}
						}
					}
					file.close();
					
					// Settings Saved Alert
					JOptionPane.showMessageDialog(null, "Your Settings Have Been Saved!");
				} catch (IOException e1) {
					
					try {
						new File(SettingsFileLocation).createNewFile();
					} catch (IOException e2) {
						
						e2.printStackTrace();
					}
				}
				
				// Refresh Screen
				frame.dispose();
				new SettingsGUI().main(Back, DirectoryLocation);
			}
		});
		
		// Restore Settings Data
		txtScouterName.setSelectedItem(Settings[1]);
		txtTeamNumber.setText(Settings[2]);
		txtCompetition.setSelectedItem(Settings[3]);
		
		// Set Application Window Visible
		frame.setVisible(true);
		
		Boolean SettingsError = false;
		try {
			Scanner FileScanner = new Scanner(new File(DirectoryLocation + "\\AppData\\Settings.txt"));
			
			for (int i = 0; i < Settings.length; i++) {
				if (!FileScanner.hasNextLine()) {
					SettingsError = true;
				}
			}
			FileScanner.close();
			if (!SettingsError) {new MainGUI().DatabaseCheck(frame, Back);}
		} catch (FileNotFoundException e1) {
			
			try {
				new File(SettingsFileLocation).createNewFile();
			} catch (IOException e2) {
				
				e2.printStackTrace();
			}
		}
	}
}