package abc.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import abc.controller.FontLoader;
import abc.controller.GameController;

public class HomePage implements Panel {

	private GameController controller;

	public HomePage(GameController controller) {
		this.controller = controller;
	}

	@Override
	public JPanel getPage(int w, int h) {
		BackgroundPanel panel = new BackgroundPanel("/src/assets/imgs/BG.jpg");
		panel.setLayout(null);
		panel.setBounds(0, 0, w, h);

		JLabel title = new JLabel("Simon Game", SwingConstants.LEFT);
		title.setBounds(300, h / 12, w - 350, 100);
		title.setForeground(Color.white);
		title.setFont(FontLoader.loadFont(FontPath.CormorantGaramond, 150));
		panel.add(title);

		JButton playBTN = new RoundedButton("Start");
		playBTN.setBounds(100, (int) (h / 2.5), 250, 100);
		playBTN.setFont(FontLoader.loadFont(FontPath.CormorantGaramond, 48));
		playBTN.setForeground(Color.white);
		// playBTN.setBackground(new Color(255, 255, 255, 150));
		playBTN.setOpaque(true);
		playBTN.setContentAreaFilled(true);
		playBTN.setFocusPainted(false);
		playBTN.setBorderPainted(false);
		playBTN.setCursor(new Cursor(Cursor.HAND_CURSOR));
		playBTN.addActionListener(e -> {
			UIManager.switchPage(Page.Play);
			controller.startGame();
		});
		playBTN.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				playBTN.setForeground(Color.yellow);
				playBTN.setBackground(new Color(255, 255, 0, 10));
			}

			public void mouseExited(MouseEvent e) {
				playBTN.setForeground(Color.white);;
				playBTN.setBackground(new Color(255, 255, 255, 10));
			}

			public void mousePressed(MouseEvent e) {
				playBTN.setBackground(Color.ORANGE);
			}

			public void mouseReleased(MouseEvent e) {
				playBTN.setForeground(Color.white);;
				playBTN.setBackground(new Color(255, 255, 255, 10));
			}
		});
		panel.add(playBTN);
		return panel;
	}

	@Override
	public String getName() {
		return "Home";
	}
}
