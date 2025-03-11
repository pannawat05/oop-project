package SimonGame.Controller;

import javax.swing.JLabel;

public class ScoreManager {
	private int score;
	private static int highestScore;
	JLabel[] label = new JLabel[2];
	JLabel overScore = new JLabel();

	public void setScoreLabel(JLabel[] label) {
		this.label = label;
	}

	public void setGameOverLabel(JLabel label) {
		this.overScore = label;
	}

	public void addScore(int score) {
		this.score += score;
		if (this.score > highestScore) {
			highestScore = this.score;
		}
		updateUI();
		System.out.println();
	}

	public void updateUI() {
		label[0].setText("Score: " + this.score);
		label[1].setText("High Score: " + highestScore);
		if (overScore != null) {
			updateGameOverUI();
			System.out.println("Hello World");
		}
	}

	public void updateGameOverUI() {
		boolean isNewScore = score > highestScore;
		overScore.setText(isNewScore? "New Score: " + this.score :"Score: " + this.score);
	}

	public void clear() {
		score = 0;
	}

	public int getScore() {
		return this.score;
	}

	public static int getHighest() {
		return highestScore;
	}

}
