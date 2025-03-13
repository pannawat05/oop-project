package src.controller;

import java.awt.Color;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import src.ui.Page;
import src.ui.UIManager;

public class GameController {
	public static final Color[] COLOR = { Color.decode("#550000"), Color.decode("#005500"), Color.decode("#000055"),
			Color.decode("#555500") };
	public static final Color[] COLOR_FLASH = { Color.red, Color.green, Color.blue, Color.yellow };

	private ScoreManager score;
	private UserInputHandler userInput;
	private ColorSequence sequence;
	private boolean isGameOver = false;
	private int delayTime = 800; // Delay before sequence starts
	private int flashTime = 500; // Flash duration
	private int pauseTime = 200; // Pause between flashes
	private JButton[] simonBTN;

	public GameController() {
		userInput = new UserInputHandler();
		score = new ScoreManager();
		sequence = new ColorSequence();
	}

	public void setUIInteract(JButton[] simonBTN, JLabel[] scoreLabels) {
		this.score.setScoreLabel(scoreLabels);
		this.simonBTN = simonBTN;
	}

	public void setUIInteract(JLabel overLabel) {
		this.score.setGameOverLabel(overLabel);
	}

	public void onSimonBTNClick(Color color) {
		if (isGameOver)
			return;
		userInput.addUserInput(color);
		checkSequence();
	}

	public void startGame() {
		userInput.clear();
		sequence.clear();
		sequence.addRandomColor();
		score.clear();
		score.updateUI();
		isGameOver = false;
		playSequence();
	}

	private void playSequence() {
		Stream.of(simonBTN).forEach(btn -> {
			btn.setEnabled(false);
			btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		});
		new Thread(() -> {
			try {
				Thread.sleep(delayTime);

				for (Color color : sequence.getSequence()) {
					flashBTN(color);
					System.out.println(color);
					Thread.sleep(flashTime + pauseTime);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				SwingUtilities.invokeLater(() -> Stream.of(simonBTN).forEach(btn -> {
					btn.setEnabled(true);
					btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}));
			}
		}).start();
	}

	private void flashBTN(Color color) {
		for (JButton button : simonBTN) {
			if (button.getBackground().equals(color)) {
				int colorIDX = Arrays.asList(COLOR).indexOf(color);
				if (colorIDX != -1) {
					button.setBackground(COLOR_FLASH[colorIDX]);
					try {
						Thread.sleep(flashTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						button.setBackground(color);
					}
					;
				}
				break;
			}
		}
	}

	private void checkSequence() {
		if (!userInput.isMatching(sequence.getSequence())) {
			gameOver();
			return;
		}

		if (sequence.isComplete(userInput.getUserInput())) {
			score.addScore(1);
			userInput.clear();
			sequence.addRandomColor();
			score.updateUI();
			playSequence();
		}
	}

	private void gameOver() {
		isGameOver = true;
		System.out.println("Game Over!");
		UIManager.switchPage(Page.GameOver);
	}

	public int getScore() {
		return score.getScore();
	}

	public ArrayList<Color> getColorSequence() {
		return sequence.getSequence();
	}
}
