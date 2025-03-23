package controller;

import view.UIManager;
import model.GameState;
import model.Score;
import utils.Level;
import utils.Page;
import utils.SimonColor;

import java.awt.Color;
import java.util.ArrayList;

public class Controller {
	private UIManager ui;
	private GameState state;

	public Controller() {
		ui = new UIManager(this);
		state = new GameState();
	}

	public void switchPage(Page page) {
		ui.updateUI(page);
	}

	public void run() {
		ui.display();
	}

	public void startGame() {
		state.startGame();
		ui.playSequence();
	}

	public void pauseGame() {

	}

	public void resumeGame() {

	}

	public void onUserInput(Color Color) {
		state.onUserInput(Color);
		checkSequence();
	}

	public Score getScore() {
		return state.getScore();
	}

	public ArrayList<Color> getSequence() {
		return state.getSequence().getSequence();
	}

	public void checkSequence() {
		if (!state.checkInput()) {
			try {
				Thread.sleep(1000);
				gameOver();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (state.checkIsComplete()) {
			state.increteScore();
			state.nextRound();
			ui.updateScoreUI(state.getScore());
			if (state.getScore().getLevel() == Level.HARD) {
				SimonColor.swapCOLOR();
				ui.swapSimonBTN();
			}
			ui.nextRound();
		}
	}

	public void gameOver() {
		switchPage(Page.GAME_OVER);
	}
}
