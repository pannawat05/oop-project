package src.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import src.controller.GameController;

public class GameOverPage implements Panel {

	private GameController controller;
	private JLabel scoreLabel;

	public GameOverPage(GameController controller) {
		this.controller = controller;
	}

	@Override
	public JPanel getPage(int w, int h) {
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);
		panel.setBounds(0, 0, w, h);

		System.out.println("Hell");
		// Game Over label
		JLabel gameOverLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
		gameOverLabel.setFont(new Font("Arial", Font.BOLD, 64));
		gameOverLabel.setForeground(Color.RED);
		gameOverLabel.setBounds(0, 50, w, 80);
		panel.add(gameOverLabel);

		// Score label
		scoreLabel = new JLabel("Score: xxx", SwingConstants.CENTER);
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 36));
		scoreLabel.setForeground(Color.YELLOW);
		scoreLabel.setBounds(0, 150, w, 50);
		controller.setUIInteract(scoreLabel);
		panel.add(scoreLabel);

		// Play Again button
		JButton playAgainButton = createTextButton("play again");
		playAgainButton.setBounds(w / 3, 250, w / 3, 50);
		playAgainButton.addActionListener(e -> playAgain());
		panel.add(playAgainButton);

		// Back to Home button
		JButton homeButton = createTextButton("back to home");
		homeButton.setBounds(w / 3, 320, w / 3, 50);
		homeButton.addActionListener(e -> UIManager.switchPage(Page.Home));
		panel.add(homeButton);
		return panel;
	}

	private JButton createTextButton(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.PLAIN, 32));
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLACK);
		button.setBorder(null);
		button.setFocusPainted(false);
		return button;
	}

	@Override
	public String getName() {
		return "GameOver";
	}

	private void playAgain() {
		controller.startGame();
		UIManager.switchPage(Page.Play);
	}

	private void backToHomePage() {

	}
}
