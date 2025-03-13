package src.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JLayeredPane;

import src.controller.GameController;
import src.controller.ScoreManager;

public class PlayPage implements Panel {
	private GameController controller;
	private JButton[] simonBTN = new JButton[4];
	private JLabel[] scoreLabels = new JLabel[2];
	private JPanel pausePanel;

	public PlayPage(GameController controller) {
		this.controller = controller;
		for (int i = 0; i < simonBTN.length; i++) {
			simonBTN[i] = new JButton();
		}
		for (int i = 0; i < scoreLabels.length; i++) {
			scoreLabels[i] = new JLabel();
		}
	}

	@Override
	public JPanel getPage(int w, int h) {
		JPanel gamePanel = new JPanel(null);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, w, h);

		// Main game panel
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, w, h);
		panel.setBackground(Color.decode("#111111"));

		// Pause button
		JButton pause = new JButton("❚❚");
		pause.setBounds(0, 0, 100, 100);
		pause.setFont(new Font("Arial", Font.BOLD, 30));
		pause.setForeground(Color.WHITE);
		pause.setBackground(new Color(0, 0, 0, 0));
		pause.setFocusPainted(false);
		pause.setBorderPainted(false);
		pause.setContentAreaFilled(false);
		pause.addActionListener(e -> pauseGame(layeredPane));
		panel.add(pause);

		// Score labels
		scoreLabels[0] = new JLabel("Score: " + controller.getScore());
		scoreLabels[0].setFont(FontLoader.loadFont(FontPath.CormorantGaramond, 40));
		scoreLabels[0].setForeground(Color.WHITE);
		scoreLabels[0].setBounds((int) (w / 1.5), (int) (h / 16), 300, 50);
		panel.add(scoreLabels[0]);

		scoreLabels[1] = new JLabel("Highest: " + ScoreManager.getHighest());
		scoreLabels[1].setFont(FontLoader.loadFont(FontPath.CormorantGaramond, 40));
		scoreLabels[1].setForeground(Color.WHITE);
		scoreLabels[1].setBounds((int) (w / 1.5), (int) (h / 1.35), 300, 50);
		panel.add(scoreLabels[1]);

		// Game grid
		int gridSize = 400;
		int gridX = (int) ((w - gridSize) / 2.25);
		int gridY = (int) ((h - gridSize) / 2.5);

		JPanel gridPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		gridPanel.setBounds(gridX, gridY, gridSize, gridSize);
		gridPanel.setBackground(Color.BLACK);

		for (int i = 0; i < simonBTN.length; i++) {
			Color color = GameController.COLOR[i];
			simonBTN[i].setBackground(color);
			simonBTN[i].setOpaque(true);
			simonBTN[i].setContentAreaFilled(true);
			simonBTN[i].setBorderPainted(true);
			simonBTN[i].setFocusPainted(false);
			simonBTN[i].addActionListener(e -> controller.onSimonBTNClick(color));
			gridPanel.add(simonBTN[i]);
		}
		panel.add(gridPanel);

		controller.setUIInteract(simonBTN, scoreLabels);

		// Add panels to the layered pane
		layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
		gamePanel.add(layeredPane);

		return gamePanel;
	}

	@Override
	public String getName() {
		return "Play";
	}

	private void pauseGame(JLayeredPane layeredPane) {
		controller.pauseGame();

		Stream.of(simonBTN).forEach(btn -> btn.setEnabled(false));
		if (pausePanel != null) {
			layeredPane.remove(pausePanel);
		}
		pausePanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(new Color(0, 0, 0, 128));
				g2d.fillRect(0, 0, getWidth(), getHeight());
			}
		};

		pausePanel.setLayout(null);
		pausePanel.setOpaque(false);
		pausePanel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());

		JLabel pauseLabel = new JLabel("Paused", JLabel.CENTER);
		pauseLabel.setFont(FontLoader.loadFont(FontPath.CormorantGaramond, 100));
		pauseLabel.setForeground(Color.blue);
		pauseLabel.setBounds(0, 50, layeredPane.getWidth(), 100);

		JButton continueBtn = new RoundedButton("Continue");
		continueBtn.setFont(FontLoader.loadFont(FontPath.CormorantGaramond, 50));
		continueBtn.setBounds((int)(layeredPane.getWidth() / 3.5), 250, 500, 80);
		continueBtn.setForeground(Color.white);
		continueBtn.setBackground(new Color(255, 255, 255, 10));
		continueBtn.setOpaque(false);
		continueBtn.setContentAreaFilled(false);
		continueBtn.setFocusPainted(false);
		continueBtn.setBorderPainted(true);
		continueBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		continueBtn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				continueBtn.setForeground(Color.yellow);
				continueBtn.setBackground(new Color(255, 255, 0, 10));
			}

			public void mouseExited(MouseEvent e) {
				continueBtn.setForeground(Color.white);
				;
				continueBtn.setBackground(new Color(255, 255, 255, 10));
			}

			public void mousePressed(MouseEvent e) {
				continueBtn.setBackground(Color.ORANGE);
			}

			public void mouseReleased(MouseEvent e) {
				continueBtn.setForeground(Color.white);
				;
				continueBtn.setBackground(new Color(255, 255, 255, 10));
			}
		});
		continueBtn.addActionListener(e -> {
			Stream.of(simonBTN).forEach(btn -> btn.setEnabled(true));
			controller.resumeGame();
			layeredPane.remove(pausePanel);
			layeredPane.revalidate();
			layeredPane.repaint();
		});

		JButton homeBtn = new RoundedButton("Back to Home");
		homeBtn.setFont(FontLoader.loadFont(FontPath.CormorantGaramond, 50));
		homeBtn.setBounds((int)(layeredPane.getWidth() / 3.5), 400, 500, 80);
		homeBtn.setForeground(Color.white);
		homeBtn.setBackground(new Color(255, 255, 255, 10));
		homeBtn.setOpaque(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setFocusPainted(false);
		homeBtn.setBorderPainted(true);
		homeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		homeBtn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				homeBtn.setForeground(Color.yellow);
				homeBtn.setBackground(new Color(255, 255, 0, 10));
			}

			public void mouseExited(MouseEvent e) {
				homeBtn.setForeground(Color.white);
				homeBtn.setBackground(new Color(255, 255, 255, 10));
			}

			public void mousePressed(MouseEvent e) {
				homeBtn.setBackground(Color.ORANGE);
			}

			public void mouseReleased(MouseEvent e) {
				homeBtn.setForeground(Color.white);
				;
				homeBtn.setBackground(new Color(255, 255, 255, 10));
			}
		});
		homeBtn.addActionListener(e -> {
			layeredPane.remove(pausePanel);
			layeredPane.revalidate();
			layeredPane.repaint();
			UIManager.switchPage(Page.Home);
		});

		pausePanel.add(pauseLabel);
		pausePanel.add(continueBtn);
		pausePanel.add(homeBtn);

		layeredPane.add(pausePanel, JLayeredPane.POPUP_LAYER);
		layeredPane.revalidate();
		layeredPane.repaint();

	}
}
