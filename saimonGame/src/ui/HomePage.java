package src.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import src.controller.GameController;

public class HomePage implements Panel {

	private GameController controller;

	public HomePage(GameController controller) {
		this.controller = controller;
	}

	@Override
	public JPanel getPage(int w, int h) {
		BackgroundPanel panel = new BackgroundPanel("./src/img/BG.jpg");
		panel.setLayout(null);
		// ("/home/cnzk/_Programming/Java/_KMITL/Project/oop-project/SimonGame/UI/src/img/BG.jpg"
		panel.setBounds(0, 0, w, h);

		JLabel title = new JLabel("Simon Game", SwingConstants.LEFT);
		title.setBounds(350, h / 12, w - 350, 100);
		title.setForeground(Color.white);
		title.setFont(new Font("Arial", Font.BOLD, 100));
		panel.add(title);

		JButton playBTN = new JButton("Start");
		playBTN.setBounds(100, (int) (h / 2.5), 250, 100);
		playBTN.setFont(new Font("Arial", Font.BOLD, 48));
		playBTN.setForeground(Color.white);
		// playBTN.setBackground(Color.decode("#00000000"));
		playBTN.setBackground(new Color(0, 0, 0, 0));
        playBTN.setContentAreaFilled(false);
		playBTN.setFocusPainted(false);
		playBTN.setBorderPainted(true);
		playBTN.addActionListener(e -> {
			controller.startGame();
			UIManager.switchPage(Page.Play);
		});
		panel.add(playBTN);
		return panel;
	}

	@Override
	public String getName() {
		return "Home";
	}
}
