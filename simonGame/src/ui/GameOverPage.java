package src.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import src.controller.FontLoader;
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

		// Game Over label
		JLabel gameOverLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
		gameOverLabel.setFont(FontLoader.loadFont(FontPath.CormorantGaramond, 150));
		gameOverLabel.setForeground(Color.RED);
		gameOverLabel.setBounds(0, 50, w, 100);
		panel.add(gameOverLabel);

		// Score label
		scoreLabel = new JLabel("Score: xxx", SwingConstants.CENTER);
		scoreLabel.setFont(FontLoader.loadFont(FontPath.CormorantGaramond, 50));
		scoreLabel.setForeground(Color.YELLOW);
		scoreLabel.setBounds(0, (int)(h/3.5), w, 60);
		controller.setUIInteract(scoreLabel);
		panel.add(scoreLabel);

		// Play Again button
		JButton playAgainButton = createTextButton("play again");
		playAgainButton.setBounds(w / 3, h/2, w / 3, 80);
		playAgainButton.addActionListener(e -> playAgain());
		panel.add(playAgainButton);

		// Back to Home button
		JButton homeButton = createTextButton("back to home");
		homeButton.setBounds(w / 3, (int)(h/1.5), w / 3, 80);
		homeButton.addActionListener(e -> UIManager.switchPage(Page.Home));
		panel.add(homeButton);
		return panel;
	}

	private JButton createTextButton(String text) {
		JButton button = new RoundedButton(text);
		button.setFont(FontLoader.loadFont(FontPath.CormorantGaramond, 36));
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLACK);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBorderPainted(true);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				button.setForeground(Color.yellow);
				button.setBackground(new Color(255, 255, 0, 10));
			}

			public void mouseExited(MouseEvent e) {
				button.setForeground(Color.white);;
				button.setBackground(new Color(255, 255, 255, 10));
			}

			public void mousePressed(MouseEvent e) {
				button.setBackground(Color.ORANGE);
			}

			public void mouseReleased(MouseEvent e) {
				button.setForeground(Color.white);;
				button.setBackground(new Color(255, 255, 255, 10));
			}
		});
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
