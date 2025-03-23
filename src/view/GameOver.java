package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.Controller;
import model.Score;
import utils.Loader;
import utils.Page;

public class GameOver extends PagePanel {

	private JLabel scoreLabel;

	public GameOver(Controller controller) {
		super(controller);
		setLayout(null);
		setBackground(Color.decode("#111111"));
		drawPage();
	}

	@Override
	public void drawPage() {
		// Game Over label
		JLabel gameOverLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
		gameOverLabel.setForeground(Color.RED);
		add(gameOverLabel);

		// Score label
		scoreLabel = new JLabel("Score: xxx", SwingConstants.CENTER);
		scoreLabel.setForeground(Color.YELLOW);
		add(scoreLabel);

		// Play Again button
		RoundedBTN playAgainButton = new RoundedBTN("play again");
		playAgainButton.setBackground(new Color(0, 0, 0, 50));
		playAgainButton.setOpaque(true);
		playAgainButton.setContentAreaFilled(false);
		playAgainButton.setFocusPainted(false);
		playAgainButton.setBorderPainted(true);
		playAgainButton.setHoverEffect(Color.YELLOW, new Color(255, 255, 0, 10));
		playAgainButton.addActionListener(e -> controller.switchPage(Page.GAME_PLAY));
		add(playAgainButton);

		// Back to Home button
		RoundedBTN homeButton = new RoundedBTN("back to home");
		homeButton.setBackground(new Color(0, 0, 0, 50));
		homeButton.setOpaque(true);
		homeButton.setContentAreaFilled(false);
		homeButton.setFocusPainted(false);
		homeButton.setBorderPainted(true);
		homeButton.setHoverEffect(Color.YELLOW, new Color(255, 255, 0, 10));
		homeButton.addActionListener(e -> controller.switchPage(Page.HOME));
		add(homeButton);

		// set bounds
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int w = getWidth();
				int h = getHeight();
				Font gameOver = Loader.loadCustomFont("resources/fonts/Glaure.ttf", w/10);
				Font btnFont = Loader.loadCustomFont("resources/fonts/RobotoSlab-VariableFont_wght.ttf", w/40);

				gameOverLabel.setBounds(0, 50, w, 100);
				gameOverLabel.setFont(gameOver);

				scoreLabel.setBounds(0, (int) (h / 3.5), w, 60);
				scoreLabel.setFont(btnFont);

				playAgainButton.setBounds(w / 3, h / 2, w / 3, 80);
				playAgainButton.setFont(btnFont);

				homeButton.setBounds(w / 3, (int) (h / 1.5), w / 3, 80);
				homeButton.setFont(btnFont);
			}
		});
	}

	public void updateScore(Score score) {
		scoreLabel.setText(Score.IS_NEW_HIGH_SCORE ? "New Score: " + score.getScore() : "Score: " + score.getScore());
	}

	@Override
	public void onOpen() {
		updateScore(controller.getScore());
	}

	@Override
	public void onClose() {
	}
}