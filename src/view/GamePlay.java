package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.Controller;
import model.Score;
import utils.Loader;
import utils.SimonColor;
import utils.MusicPlayer;

public class GamePlay extends PagePanel {

	private ArrayList<SimonBTN> simonBTN;
	private JLabel scoreLabel;
	private JLabel hightScoreLabel;
	private MusicPlayer roundCountDown;
	private ArrayList<MusicPlayer> clickBTN;
	private final int FLASH_TIME = 1000;

	public GamePlay(Controller controller) {
		super(controller);
		this.simonBTN = new ArrayList<>();
		this.roundCountDown = new MusicPlayer("resources/sounds/round_coutdown.wav");
		this.clickBTN = new ArrayList<>();
		setLayout(null);
		setBackground(Color.decode("#111111"));
		drawPage();
	}

	@Override
	protected void drawPage() {
		JLayeredPane layeredPane = new JLayeredPane();

		// pause button ---------------------
		JButton pause = new JButton("❚❚");
		// pause.setBounds(0, 0, 100, 100);
		pause.setForeground(Color.WHITE);
		pause.setFocusPainted(false);
		pause.setBorderPainted(false);
		pause.setContentAreaFilled(false);
		pause.setOpaque(false);
		add(pause);
		// -----------------------------------

		// Score labels ---------------------
		scoreLabel = new JLabel("Score: xxx");
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setBackground(Color.RED);
		add(scoreLabel);
		// -----------------------------------

		// Hight score labels ---------------------
		hightScoreLabel = new JLabel("Hight Score: xxx");
		hightScoreLabel.setForeground(Color.WHITE);
		hightScoreLabel.setBackground(Color.RED);
		add(hightScoreLabel);
		// -----------------------------------

		// Simon buttons ---------------------
		JPanel gridPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		gridPanel.setBackground(Color.BLACK);
		add(gridPanel);

		for (int i = 0; i < SimonColor.DEFAULT.size(); i++) {
			SimonBTN btn = new SimonBTN(SimonColor.DEFAULT.get(i), SimonColor.FLASH.get(i), FLASH_TIME);
			btn.addActionListener(e -> {
				controller.onUserInput(btn.getDefaultColor());
				SimonBTN.setDisableByFlashTime(simonBTN);
			});
			simonBTN.add(btn);
			gridPanel.add(btn);
		}

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int w = getWidth();
				int h = getHeight();
				Font font = Loader.loadCustomFont("resources/fonts/RobotoSlab-VariableFont_wght.ttf", w / 40f);

				pause.setBounds(40, 30, w / 10, h / 10);
				pause.setFont(getFont().deriveFont(w / 40f));

				scoreLabel.setBounds((int) (w / 1.4), (int) (h / 20), w / 4, h / 10);
				scoreLabel.setFont(font);

				hightScoreLabel.setBounds((int) (w / 1.4), (int) (h / 1.3), w / 4, h / 10);
				hightScoreLabel.setFont(font);

				int gridWidth = (int) (w / 2.5);
				int gridHeight = (int) (w / 2.5);
				int gridX = (int) ((w - gridWidth) / 2);
				int gridY = (int) ((h - gridHeight) / 2.5);

				gridPanel.setBounds(gridX, gridY, gridWidth, gridHeight);
			}
		});
	}

	public void swapSimonBTN() {
		for (int i = 0; i < SimonColor.DEFAULT.size(); i++) {
			SimonBTN btn = simonBTN.get(i);
			btn.setDefaultColor(SimonColor.DEFAULT.get(i));
			btn.setFlash(SimonColor.FLASH.get(i));
			btn.drawBTN();
		}
	}

	public void setEnableBTN(boolean enable) {
		System.out.println("setEnableBTN: " + enable);
		SwingUtilities.invokeLater(() -> simonBTN.forEach(btn -> btn.setEnabled(enable)));
	}

	public void nextRound() {
		new Thread(() -> {
			try {
				Thread.sleep(FLASH_TIME + 1);
				System.out.println("Next round");
				playSequence();
			} catch (Exception e) {
				Thread.currentThread().interrupt();
			}
		}).start();
	}

	public void playSequence() {
		setEnableBTN(false);

		new Thread(() -> {
			try {
				roundCountDown.playSound();
				Thread.sleep(4200);

				for (Color color : controller.getSequence()) {
					SimonBTN matchingButton = simonBTN.stream()
							.filter(btn -> btn.getDefaultColor().equals(color))
							.findFirst()
							.orElse(null);

					if (matchingButton != null) {
						SwingUtilities.invokeLater(() -> matchingButton.flashing());

						Thread.sleep(FLASH_TIME + 1000);
					}
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			} finally {
				SwingUtilities.invokeLater(() -> setEnableBTN(true));
			}
		}).start();
	}

	public void updateScore(Score score) {
		scoreLabel.setText("Score: " + score.getScore());
		hightScoreLabel.setText("Hight Score: " + Score.HIGHT_SCORE);
	}

	@Override
	public void onOpen() {
		controller.startGame();
		updateScore(controller.getScore());
	}

	@Override
	public void onClose() {
	}
}