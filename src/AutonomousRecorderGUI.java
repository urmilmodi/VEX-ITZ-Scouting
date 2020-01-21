/***********************************************
**          Autonomous Recorder GUI           **
**                                            **
** Urmil, Manitha, Aiden, Gurnoor, Nived      **
** ICS4U0-A                                   **
** Ver: 4.0 - Jan. 5, 2018                    **
** Last Edit: Jan. 5, 2018                    **
***********************************************/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class AutonomousRecorderGUI extends JComponent {
	
	// Variable Initialization
	
	// Field Image
	private BufferedImage Image;
	
	// Graphics2D Object
	private Graphics2D G2;
	
	// Mouse Coordinates
	private int currentX, currentY, oldX, oldY;
	
	// DirectoryLocation
	private String DirectoryLocation;
	
	// Autonomous Recording Method (Mouse Tracker)
	public AutonomousRecorderGUI() {
		
		setDoubleBuffered(false); // Paint Without Buffer, Directly on Screen
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
				// Save Mouse Coordinates When Mouse Pressed
				oldX = e.getX();
		        oldY = e.getY();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				
				// Save Mouse Coordinates When Mouse Dragged
				currentX = e.getX();
				currentY = e.getY();
				
				// Draw Mouse IF G2 NOT Null
				if (G2 != null) {
					
					G2.drawLine(oldX, oldY, currentX, currentY);
					repaint(); // Refresh Area
					
					// Store Current Mouse Coordinates as Old Mouse Coordinates
					oldX = currentX;
					oldY = currentY;
				}
			}
		});
	}
	
	// Load & Update Image
	public void paintComponent(Graphics G) {
		
		// Import Image if Null
		if (Image == null) {
			
			// Load Default Image
			try {
				Image = ImageIO.read(new File(DirectoryLocation + "\\AppData\\Field.png"));
				G2 = (Graphics2D) Image.getGraphics();
				G2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Enable Anti-Aliasing (Smoother Lines)
				repaint(); // Reload Field
				G2.setPaint(Color.green); // Set Paint to Green
			} catch (IOException e) {
				
				JOptionPane.showMessageDialog(null, "Application Data Loss, ReSync or Contact Admin");
			}
		}
		G.drawImage(Image, 0, 0, null); // Display Image on Screen
	}
	
	// Reload Original Image
	public void Clear(String Location) {
		
		DirectoryLocation = Location; // Set Image Default Location
		
		// Reload Default Image
		try {
			Image = ImageIO.read(new File(DirectoryLocation + "\\AppData\\Field.png"));
			G2 = (Graphics2D) Image.getGraphics();
			G2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Enable Anti-Aliasing (Smoother Lines)
			G2.setPaint(Color.green); // Set Paint to Green
		} catch (IOException e) {
			
			JOptionPane.showMessageDialog(null, "Application Data Loss, ReSync or Contact Admin");
		}
		repaint(); // Reload Field
	}
	
	// Save Image
	public void Save(String Location) {
		
		// Save Image as PNG in Specified Location
		try {
			ImageIO.write(Image, "PNG", new File(Location));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}