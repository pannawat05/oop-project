package abc.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class BackgroundPanel extends JPanel {
	private Image backgroundImage;

	public BackgroundPanel(String imagePath) {
		loadImage(imagePath);
	}

	private void loadImage(String imagePath) {
		java.net.URL imageUrl = getClass().getResource(imagePath);
		if (imageUrl != null) {
			ImageIcon icon = new ImageIcon(imageUrl);
			this.backgroundImage = icon.getImage();
		} else {
			System.out.println("Error: Background image not found at " + imagePath);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Draw image scaled to panel size
	}
}
