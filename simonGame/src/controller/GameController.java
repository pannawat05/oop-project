package src.controller;

import java.awt.Color;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import src.ui.Page;
import src.ui.UIManager;

public class GameController {
	public static final Color[] COLOR = { Color.decode("#770000"), Color.decode("#007700"), Color.decode("#000077"),
			Color.decode("#777700") };
	public static final Color[] COLOR_FLASH = { Color.red, Color.green, Color.blue, Color.yellow };

	private ScoreManager score;
	private UserInputHandler userInput;
	private ColorSequence sequence;
	private boolean isGameOver = false;
	private boolean isPaused = false;
	private int delayTime = 800;
	private int flashTime = 500;
	private int pauseTime = 200;
	private JButton[] simonBTN;
	private Map<JButton, Color> buttonColors = new HashMap<>();

	public GameController() {
		userInput = new UserInputHandler();
		score = new ScoreManager();
		sequence = new ColorSequence();
	}

	public void setUIInteract(JButton[] simonBTN, JLabel[] scoreLabels) {
		this.score.setScoreLabel(scoreLabels);
		this.simonBTN = simonBTN;
		resetButtonColors();
	}

	public void setUIInteract(JLabel overLabel) {
		this.score.setGameOverLabel(overLabel);
	}

	public synchronized void pauseGame() {
		isPaused = true;
	}

	public synchronized void resumeGame() {
		isPaused = false;
		notifyAll();
	}

	private synchronized void checkPause() throws InterruptedException {
		while (isPaused) {
			wait();
		}
	}

	public void onSimonBTNClick(Color color, JButton btn) {
		if (isGameOver)
			return;
		userInput.addUserInput(color, btn);
		checkSequence();
	}

	public void startGame() {
		userInput.clear();
		sequence.clear();
		sequence.addRandomColor();
		score.clear();
		score.updateUI();
		isGameOver = false;
		resetButtonColors();
		playSequence();
	}

	private void playSequence() {
		Level level = score.getLevel();
		Stream.of(simonBTN).forEach(btn -> {
			btn.setEnabled(false);
			btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		});

		new Thread(() -> {
			try {
				Thread.sleep(delayTime);
				for (Color color : sequence.getSequence()) {
					checkPause();
					flashBTN(color);
					System.out.println(color);
					Thread.sleep(flashTime + pauseTime);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				if (level == Level.HARD) {
					swapButtonColors();
				}
				SwingUtilities.invokeLater(() -> Stream.of(simonBTN).forEach(btn -> {
					btn.setEnabled(true);
					btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}));
			}
		}).start();
	}

	private void swapButtonColors() {
		SwingUtilities.invokeLater(() -> {
			List<Color> colorList = new ArrayList<>(Arrays.asList(COLOR));
			Collections.shuffle(colorList);

			for (int i = 0; i < simonBTN.length; i++) {
				Color newColor = colorList.get(i);
				JButton btn = simonBTN[i];
				buttonColors.put(btn, newColor);
				btn.setBackground(newColor);
			}
		});
	}

	private void resetButtonColors() {
		for (int i = 0; i < simonBTN.length; i++) {
			simonBTN[i].setBackground(COLOR[i]);
			buttonColors.put(simonBTN[i], COLOR[i]);
		}
	}

	private void flashBTN(Color color) {
		for (JButton button : simonBTN) {
			if (buttonColors.get(button).equals(color)) {
				int colorIDX = Arrays.asList(COLOR).indexOf(buttonColors.get(button));
				if (colorIDX != -1) {
					Color flashColor = COLOR_FLASH[colorIDX];
					button.setBackground(flashColor);
					try {
						Thread.sleep(flashTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						button.setBackground(buttonColors.get(button));
					}
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
		try {
			JButton lastClickedBtn = userInput.getLastINP();
			if (lastClickedBtn != null) {
				Color originalColor = lastClickedBtn.getBackground();
				int idx = Arrays.asList(COLOR).indexOf(originalColor);
				System.out.println(COLOR_FLASH[idx] + " " + idx);
				lastClickedBtn.setBackground(COLOR_FLASH[idx]);
				// MusicPlayer.playSound("/src/assets/sound/GameOver.wav");
				Thread.sleep(1500);
				lastClickedBtn.setBackground(originalColor);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

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
