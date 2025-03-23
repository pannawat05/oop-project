package model;

import java.awt.Color;

import utils.SimonColor;

public class GameState {

	private Score score;
	private ColorSequence input;
	private ColorSequence sequence;

	public GameState() {
		score = new Score();
		input = new ColorSequence();
		sequence = new ColorSequence();
	}

	public void startGame() {
		score.resetScore();
		sequence.clear();
		nextRound();
	}

	public Score getScore() {
		return score;
	}

	public ColorSequence getSequence() {
		return sequence;
	}

	public void nextRound() {
		sequence.addRandomColor();
		input.clear();
	}

	public void onUserInput(Color color) {
		input.addColor(color);
	}

	public void increteScore() {
		score.increteScore();
	}

	public boolean checkInput() {
		return sequence.isSubset(input.getSequence());
	}

	public boolean checkIsComplete() {
		return sequence.isEqual(input.getSequence());
	}
}