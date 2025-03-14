package src.controller;

import javax.swing.JLabel;

public class ScoreManager {
	private int score;
	private static int highestScore;
	private boolean isNewScore = false;
	JLabel[] label = new JLabel[2];
	JLabel overScore = new JLabel();

	public void setScoreLabel(JLabel[] label) {
		this.label = label;
	}

	public void setGameOverLabel(JLabel label) {
		this.overScore = label;
	}

	public Level getLevel() {
        if (score >= 15) {
            return Level.HARD;
        } else {
            return Level.EASY;
        }
    }

	public void addScore(int score) {
		this.score += score;
		if (this.score > highestScore) {
			highestScore = this.score;
			isNewScore = true;
		}
		updateUI();
		System.out.println();
	}

	public void updateUI() {
		label[0].setText("Score: " + this.score);
		label[1].setText("High Score: " + highestScore);
		if (overScore != null) {
			updateGameOverUI();
		}
	}

	public void updateGameOverUI() {
		overScore.setText(isNewScore? "New Score: " + this.score :"Score: " + this.score);
	}

	public void clear() {
		score = 0;
		isNewScore = false;
	}

	public int getScore() {
		return this.score;
	}

	public static int getHighest() {
		return highestScore;
	}

}
