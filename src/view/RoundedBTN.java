package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class RoundedBTN extends JButton {

	public RoundedBTN(String text) {
		super(text);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

		super.paintComponent(g);
		g2.dispose();
	}

	@Override
	protected void paintBorder(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(getForeground());
		g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);

		g2.dispose();
	}

	public void setHoverEffect(Color foreground, Color background) {
		addMouseListener(new java.awt.event.MouseAdapter() {
			Color default_BG = getBackground();
			Color default_FG = getForeground();
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				setBackground(background);
				setForeground(foreground);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				setBackground(default_BG);
				setForeground(default_FG);
			}
		});
	}
}