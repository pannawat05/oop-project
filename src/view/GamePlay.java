package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
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
import utils.Page;

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

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.decode("#111111"));

		JPanel pausePanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(new Color(0, 0, 0, 200));
				g2d.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		pausePanel.setLayout(null);
		pausePanel.setOpaque(false);
		pausePanel.addMouseListener(new MouseAdapter() {
		});

		// pause button ---------------------
		JButton pause = new JButton("❚❚");
		pause.setForeground(Color.WHITE);
		pause.setFocusPainted(false);
		pause.setBorderPainted(false);
		pause.setContentAreaFilled(false);
		pause.setOpaque(false);
		pause.addActionListener(e -> {
			layeredPane.add(pausePanel, JLayeredPane.POPUP_LAYER);
			layeredPane.revalidate();
			layeredPane.repaint();
			controller.pauseGame();
		});
		panel.add(pause);
		// -----------------------------------

		// Score labels ---------------------
		scoreLabel = new JLabel("Score: xxx");
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setBackground(Color.RED);
		panel.add(scoreLabel);
		// -----------------------------------

		// Hight score labels ---------------------
		hightScoreLabel = new JLabel("Hight Score: xxx");
		hightScoreLabel.setForeground(Color.WHITE);
		hightScoreLabel.setBackground(Color.RED);
		panel.add(hightScoreLabel);
		// -----------------------------------

		// Simon buttons ---------------------
		JPanel gridPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		gridPanel.setBackground(Color.BLACK);
		panel.add(gridPanel);
		// -----------------------------------

		layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
		add(layeredPane);

		for (int i = 0; i < SimonColor.DEFAULT.size(); i++) {
			SimonBTN btn = new SimonBTN(SimonColor.DEFAULT.get(i), SimonColor.FLASH.get(i), FLASH_TIME);
			btn.addActionListener(e -> {
				controller.onUserInput(btn.getDefaultColor());
				SimonBTN.setDisableByFlashTime(simonBTN);
			});
			simonBTN.add(btn);
			gridPanel.add(btn);
		}

		// pause panel
		JLabel pauseLabel = new JLabel("Paused", JLabel.CENTER);
		pauseLabel.setForeground(Color.blue);
		pausePanel.add(pauseLabel);

		RoundedBTN continueBtn = new RoundedBTN("Continue");
		continueBtn.setForeground(Color.black);
		continueBtn.setBackground(new Color(255, 255, 255, 50));
		continueBtn.setOpaque(true);
		continueBtn.setBorderPainted(true);
		continueBtn.setHoverEffect(Color.YELLOW, new Color(255, 255, 0, 10));
		continueBtn.addActionListener(e -> {
			controller.resumeGame();
			layeredPane.remove(pausePanel);
			layeredPane.revalidate();
			layeredPane.repaint();
		});
		pausePanel.add(continueBtn);

		RoundedBTN restart = new RoundedBTN("Restart");
		restart.setForeground(Color.black);
		restart.setBackground(new Color(255, 255, 255, 50));
		restart.setOpaque(true);
		restart.setBorderPainted(true);
		restart.setHoverEffect(Color.YELLOW, new Color(255, 255, 0, 10));
		restart.addActionListener(e -> {
			controller.resumeGame();
			controller.startGame();
			layeredPane.remove(pausePanel);
			layeredPane.revalidate();
			layeredPane.repaint();
		});
		pausePanel.add(restart);

		RoundedBTN homeBtn = new RoundedBTN("Back to Home");
		homeBtn.setForeground(Color.black);
		homeBtn.setBackground(new Color(255, 255, 255, 50));
		homeBtn.setOpaque(true);
		homeBtn.setBorderPainted(true);
		homeBtn.setHoverEffect(Color.YELLOW, new Color(255, 255, 0, 10));
		homeBtn.addActionListener(e -> {
			controller.resumeGame();
			layeredPane.remove(pausePanel);
			layeredPane.revalidate();
			layeredPane.repaint();
			controller.switchPage(Page.HOME);
		});
		pausePanel.add(homeBtn);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int w = getWidth();
				int h = getHeight();
				Font font = Loader.loadCustomFont("resources/fonts/RobotoSlab-VariableFont_wght.ttf", w / 40f);
				Font pauseTitleFont = Loader.loadCustomFont("resources/fonts/RobotoSlab-VariableFont_wght.ttf",
						w / 10f);

				layeredPane.setBounds(0, 0, w, h);

				panel.setBounds(0, 0, w, h);

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

				pausePanel.setBounds(0, 0, w, h);

				pauseLabel.setBounds(0, 50, w, 100);
				pauseLabel.setFont(pauseTitleFont);

				continueBtn.setBounds((int) (w / 2.6), (int) (h / 3), (w / 4), (h / 10));
				continueBtn.setFont(font);

				restart.setBounds((int) (w / 2.6), (int) (h / 2), (w / 4), (h / 10));
				restart.setFont(font);

				homeBtn.setBounds((int) (w / 2.6), (int) (h / 1.5), (w / 4), (h / 10));
				homeBtn.setFont(font);
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
					controller.checkPause();
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
		roundCountDown.stopSound();
	}
}