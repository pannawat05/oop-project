package view;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;
import javax.swing.Timer;

import utils.MusicPlayer;

public class SimonBTN extends JButton {

	private Color defaultColor;
	private Color flash;
	private static int flashTime = 500;
	private MusicPlayer buttonSound;

	public SimonBTN(Color defaultColor, Color flash) {
		super();
		this.defaultColor = defaultColor;
		this.flash = flash;
		drawBTN();
	}

	public void setButtonSound(String path) {
		this.buttonSound = new MusicPlayer(path);
	}

	public SimonBTN(Color defaultColor, Color flash, int flashTime, MusicPlayer sound) {
		super();
		this.defaultColor = defaultColor;
		this.flash = flash;
		this.buttonSound = sound;
		SimonBTN.flashTime = flashTime;
		drawBTN();
	}

	public void drawBTN() {
		setBackground(defaultColor);
		setFocusPainted(false);
		setBorderPainted(false);
		setContentAreaFilled(true);
		setOpaque(true);
		setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		addEvent();
	}

	private void addEvent() {
		addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				if (isEnabled()) {
					flashing();
				}
			}
		});
	}

	public void flashing() {
		buttonSound.playSound();
		setBackground(flash);
		Timer timer = new javax.swing.Timer(flashTime, e -> {
			setBackground(defaultColor);
			buttonSound.stopSound();
		});
		timer.setRepeats(false);
		timer.start();
	}

	public Color getDefaultColor() {
		return defaultColor;
	}

	public Color getFlash() {
		return flash;
	}

	public void setDefaultColor(Color defaultColor) {
		this.defaultColor = defaultColor;
	}

	public void setFlash(Color flash) {
		this.flash = flash;
	}

	public static void setDisableByFlashTime(List<SimonBTN> buttons) {
		for (SimonBTN btn : buttons) {
			btn.setEnabled(false);
		}
		Timer timer = new Timer(flashTime , e -> {
			for (SimonBTN btn : buttons) {
				btn.setEnabled(true);
			}
		});
		timer.setRepeats(false);
		timer.start();
	}
}