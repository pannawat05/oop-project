package SimonGame.UI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import SimonGame.Controller.GameController;

public class HomePage implements Panel {

	private GameController controller;

	public HomePage(GameController controller) {
		this.controller = controller;
	}

	@Override
	public JPanel getPage(int w, int h) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.decode("#111111"));
		panel.setBounds(0, 0, w, h);

		JLabel title = new JLabel("Simon Game", SwingConstants.LEFT);
		title.setBounds(350, h / 12, w - 350, 100);
		title.setForeground(Color.white);
		title.setFont(new Font("Arial", Font.BOLD, 100));
		panel.add(title);

		JButton playBTN = new JButton("Start");
		playBTN.setBounds(100, (int) (h / 2.5), 250, 100);
		playBTN.setFont(new Font("Arial", Font.BOLD, 48));
		playBTN.setForeground(Color.black);
		playBTN.setBackground(Color.white);
		playBTN.setFocusPainted(false);
		playBTN.setBorderPainted(false);
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
