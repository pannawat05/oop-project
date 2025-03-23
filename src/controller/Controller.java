package controller;

import view.UIManager;
import model.GameState;
import model.Score;
import utils.Level;
import utils.Page;
import utils.SimonColor;

import java.awt.Color;
import java.util.ArrayList;

import utils.MusicPlayer;

public class Controller {
	private UIManager ui;
	private GameState state;
	private MusicPlayer correctSound;
	MusicPlayer gameOverSound;
	private boolean isPaused = false;

	public Controller() {
		ui = new UIManager(this);
		state = new GameState();
		correctSound = new MusicPlayer("resources/sounds/correctSound.wav");
		gameOverSound = new MusicPlayer("resources/sounds/GameOver.wav");
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

	public synchronized void pauseGame() {
		isPaused = true;
	}

	public synchronized void resumeGame() {
		isPaused = false;
		notifyAll();
	}

	public synchronized void checkPause() throws InterruptedException {
		while (isPaused) {
			wait();
		}
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
		System.out.println("Over: " + !state.checkInput());
		if (!state.checkInput()) {
				gameOver();
		} else if (state.checkIsComplete()) {
			correctSound.playSound();
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
		try {
			gameOverSound.playSound();
			Thread.sleep(1000);
			switchPage(Page.GAME_OVER);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
