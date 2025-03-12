package src.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import src.ui.Page;
import src.ui.UIManager;

public class GameController {
	public static final Color[] COLOR = { Color.decode("#550000"), Color.decode("#005500"), Color.decode("#000055"),
			Color.decode("#555500") };
	public static final Color[] COLOR_FLASH = { Color.red, Color.green, Color.blue, Color.yellow };
	private ScoreManager score;
	private UserInputHandler userInput;
	private ColorSequence sequence;
	private boolean isGameOver;
	private JButton[] saimonBTN;

	public GameController() {
		userInput = new UserInputHandler();
		score = new ScoreManager();
		sequence = new ColorSequence();
	}

	public void setUIInteract(JButton[] saimonBTN, JLabel[] scorLabels) {
		this.score.setScoreLabel(scorLabels);
		this.saimonBTN = saimonBTN;
	}

	public void setUIInteract(JLabel overLabel) {
		this.score.setGameOverLabel(overLabel);
	}

	public void onSaimonBTNClick(Color color) {
		userInput.addUserInput(color);
		checkSequence();
	}

	public void startGame() {
		userInput.clear();
		sequence.clear();
		sequence.addRandomColor();
		score.clear();
		score.updateUI();
		playSequence();
	}

	private void playSequence() {
		new Thread(() -> {
			try {
				Thread.sleep(500);
				System.out.println();
				sequence.getSequence().stream().forEach(System.out::println);
				for (Color color : sequence.getSequence()) {
					flashBTN(color);
					Thread.sleep(750);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void flashBTN(Color color) {
		for (JButton button : saimonBTN) {
			if (button.getBackground().equals(color)) {
				SwingUtilities.invokeLater(() -> {
					int colorIDX = Arrays.asList(COLOR).indexOf(color);
					button.setBackground(COLOR_FLASH[colorIDX]);
					Timer timer = new Timer(500, e -> button.setBackground(color));
					timer.setRepeats(false);
					timer.start();
				});
				break;
			}
		}
	}

	public ArrayList<Color> getColorSequence() {
		return null;
	}

	public int getScore() {
		return score.getScore();
	}

	private void checkSequence() {
		isGameOver = !userInput.isMatching(sequence.getSequence());
		boolean checkAll = sequence.isComplete(userInput.getUserInput());
		if (checkAll) {
			score.addScore(1);
			userInput.clear();
			sequence.addRandomColor();
			playSequence();
			return;
		}
		gameOver();
	}

	private void gameOver() {
		System.out.println("GameOver: " + isGameOver);
		if (isGameOver) {
			UIManager.switchPage(Page.GameOver);
		}
	}

}
