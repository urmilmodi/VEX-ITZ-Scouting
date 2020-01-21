/***********************************************
**                Team List GUI               **
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
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class TeamListGUI {
	
	// Variable Initialization
	private String[][] TeamListArray;
	private JTable table;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "serial"})
	public void main(String Back, String DirectoryLocation) {
		
		// Import Teams List
		String TeamDataDirectory = DirectoryLocation + "\\TeamData";
	    
	    File[] directoryListAsFile = new File(TeamDataDirectory).listFiles(new FileFilter() {
	        public boolean accept(File file) {
	            return file.isDirectory();
	        }
	    });
	    
	    List<List<String>> TeamList = new ArrayList<List<String>>();
	    
	    for (int i = 0; i < directoryListAsFile.length; i++) {
		    
	    	if (new File(TeamDataDirectory + "\\" + directoryListAsFile[i].getName()).list().length > 0) {
	    		
	    		TeamList.add(new ArrayList(Arrays.asList(directoryListAsFile[i].getName().split(" – ")[0], directoryListAsFile[i].getName().split(" – ")[1])));
	    	}
	    }

	    // Adjusting for Table
	    TeamListArray = new String[TeamList.size()][2];
	    
	    for (int i = 0; i < TeamList.size(); i++) {
	    	
	    	TeamListArray[i][0] = TeamList.get(i).get(0);
	    	TeamListArray[i][1] = TeamList.get(i).get(1);
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
		JFrame frame = new JFrame("Team List");
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(x, y, 668, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		// Initialize Elements
		JButton btnBack = new JButton("");
		JButton btnMenu = new JButton("");
		JComboBox txtMenu = new JComboBox();
		JLabel lblTeamList = new JLabel("Team List");
		JTextField txtSearch = new JTextField();
		JButton btnSearch = new JButton("");
		JScrollPane scrollPane = new JScrollPane();
		table = new JTable()
		{
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				
				Component comp = super.prepareRenderer(renderer, row, column);
				comp.setBackground(row % 2 == 0 ? Color.white : Color.LIGHT_GRAY);
				return comp;
			}
		};
		txtMenu.setModel(new DefaultComboBoxModel(new String[] {"Main Menu", "Scouting", "Team View", "Sync", "Settings"}));
		table.setModel(new DefaultTableModel(TeamListArray,
				new String[] {
					"Team Number", "Team Name"
				}));

		// Visual Adjustments
		txtSearch.setColumns(10);
		txtMenu.setVisible(false);
		btnMenu.setBorderPainted(false);
		btnBack.setBorderPainted(false);
		btnSearch.setBorderPainted(false);
		btnMenu.setBackground(Color.WHITE);
		btnBack.setBackground(Color.WHITE);
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\search3.png"));
		btnMenu.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\menu2.png"));
		btnBack.setIcon(new ImageIcon(DirectoryLocation + "\\GUI\\back2.png"));
		lblTeamList.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 17));
		table.setAutoCreateRowSorter(true);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(77);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(225);
		table.setFillsViewportHeight(true);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
				
		// Positioning Elements
		springLayout.putConstraint(SpringLayout.WEST, btnBack, 35, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnBack, 103, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnMenu, 557, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnMenu, 612, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, txtSearch, 98, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtSearch, 235, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblTeamList, 39, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblTeamList, 287, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnBack, -1, SpringLayout.NORTH, lblTeamList);
		springLayout.putConstraint(SpringLayout.NORTH, txtMenu, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtMenu, 521, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtMenu, -33, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnMenu, 6, SpringLayout.SOUTH, txtMenu);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 149, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 99, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -58, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, txtSearch, -31, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.NORTH, btnSearch, 0, SpringLayout.NORTH, txtSearch);
		springLayout.putConstraint(SpringLayout.WEST, btnSearch, 26, SpringLayout.EAST, txtSearch);
		springLayout.putConstraint(SpringLayout.SOUTH, btnSearch, 3, SpringLayout.SOUTH, txtSearch);
		springLayout.putConstraint(SpringLayout.EAST, btnSearch, 102, SpringLayout.EAST, txtSearch);
		
		// Add Elements to Application Window
		frame.getContentPane().add(btnBack);
		frame.getContentPane().add(lblTeamList);
		frame.getContentPane().add(txtSearch);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		frame.getContentPane().add(btnMenu);
		frame.getContentPane().add(txtMenu);
		frame.getContentPane().add(btnSearch);
		
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
					case 1:		new ScoutingGUI().main("Team List", DirectoryLocation);
								break;
					case 2:		new TeamViewGUI().main("Team List", DirectoryLocation, "");
								break;
					case 3:		new SyncGUI().main("Team List", DirectoryLocation);
								break;
					case 4:		new SettingsGUI().main("Team List", DirectoryLocation);
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
					new ScoutingGUI().main("Team List", DirectoryLocation);
					
				} else if (Back.equals("Team View")) {
					new TeamViewGUI().main("Team List", DirectoryLocation, "");
					
				} else if (Back.equals("Sync")) {
					new SyncGUI().main("Team List", DirectoryLocation);
					
				} else if (Back.equals("Settings")) {
					new SettingsGUI().main("Team List", DirectoryLocation);
				}
			}
		});
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent event) {
	        	
	        	// Redirect to Team View Upon Team Selection
	        	String Team = table.getValueAt(table.getSelectedRow(), 0).toString() + " – " + table.getValueAt(table.getSelectedRow(), 1).toString();
	        	
	        	frame.dispose();
	        	new TeamViewGUI().main("Team List", DirectoryLocation, Team);
	        }
	    });
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				// Search When ENTER is pressed
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					
					Search(txtSearch.getText());
				}
			}
		});
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Search(txtSearch.getText()); // Search
			}
		});
		
		// Set Application Window Visible
		frame.setVisible(true);
		
		// No Team Data Alert IF NO Database Error
		if (TeamListArray.length == 0 && !new MainGUI().DatabaseCheck(frame, "Team List")) {
		
			JOptionPane.showMessageDialog(null, "No Team Data!");
			frame.dispose();
			new ScoutingGUI().main("Team List", DirectoryLocation);
		}
	}
	
	// Search Method
	public void Search(String Search) {
		
		List<List<String>> SearchResultList = new ArrayList<List<String>>();
		
		// Filtering Table According to Search Request
	    for (int i = 0; i < TeamListArray.length; i++) {
	    	
	    	if (TeamListArray[i][0].toLowerCase().contains(Search.toLowerCase()) || TeamListArray[i][1].toLowerCase().contains(Search.toLowerCase())) {
	    		
	    		SearchResultList.add(new ArrayList<String>(Arrays.asList(TeamListArray[i][0], TeamListArray[i][1])));
	    	}
	    }
		
	    // Adjusting for Table
	    String[][] SearchResults = new String[SearchResultList.size()][2];
	    
	    for (int i = 0; i < SearchResultList.size(); i++) {
	    	
	    	SearchResults[i][0] = SearchResultList.get(i).get(0);
	    	SearchResults[i][1] = SearchResultList.get(i).get(1);
	    }
	    
	    // Displaying Search Results
	    table.setModel(new DefaultTableModel(SearchResults,
				new String[] {
					"Team Number", "Team Name"
				}));
		
		// Visual Adjustments
		table.setAutoCreateRowSorter(true);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(77);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(225);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
	}
}