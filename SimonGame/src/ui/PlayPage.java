package src.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import src.controller.GameController;
import src.controller.ScoreManager;

public class PlayPage implements Panel {
	private GameController controller;
	private JButton[] saimonBTN = new JButton[4];
	private JLabel[] scoreLabels = new JLabel[2];
	private JPanel pausePanel;

	public PlayPage(GameController controller) {
		this.controller = controller;
		for (int i = 0; i < saimonBTN.length; i++) {
			saimonBTN[i] = new JButton();
		}
		for (int i = 0; i < scoreLabels.length; i++) {
			scoreLabels[i] = new JLabel();
		}
	}

	@Override
	public JPanel getPage(int w, int h) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.decode("#111111"));
		panel.setBounds(0, 0, w, h);

		// Pause button
		JButton pause = new JButton("❚❚");
		pause.setBounds(0, 0, 100, 100);
		pause.setFont(new Font("Arial", Font.BOLD, 30));
		pause.setForeground(Color.WHITE);
		pause.setBackground(new Color(0, 0, 0, 0));
		pause.setFocusPainted(false);
		pause.setBorderPainted(false);
		pause.setContentAreaFilled(false);
		pause.addActionListener(e -> pauseGame(panel));
		panel.add(pause);

		// Score label
		scoreLabels[0] = new JLabel("Score: " + controller.getScore());
		scoreLabels[0].setFont(FontLoader.loadFont(FontPath.CormorantGaramond, 40));
		scoreLabels[0].setForeground(Color.WHITE);
		scoreLabels[0].setBounds((int) (w / 1.5), (int) (h / 16), 300, 50);
		panel.add(scoreLabels[0]);

		// Highest score label
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

		for (int i = 0; i < saimonBTN.length; i++) {
			Color color = GameController.COLOR[i];
			saimonBTN[i].setBackground(color);
			saimonBTN[i].setOpaque(true);
			saimonBTN[i].setContentAreaFilled(true);
			saimonBTN[i].setBorderPainted(true);
			saimonBTN[i].addActionListener(e -> controller.onSimonBTNClick(color));
			gridPanel.add(saimonBTN[i]);
		}

		panel.add(gridPanel);
		controller.setUIInteract(saimonBTN, scoreLabels);
		return panel;
	}

	@Override
	public String getName() {
		return "Play";
	}

	private void pauseGame(JPanel parentPanel) {
		if (pausePanel != null) {
			parentPanel.remove(pausePanel);
		}

		pausePanel = new JPanel();
		pausePanel.setLayout(null);
		pausePanel.setBounds(0, 0, parentPanel.getWidth(), parentPanel.getHeight());
		pausePanel.setBackground(new Color(0, 0, 0)); // Semi-transparent dark background

		JLabel pauseLabel = new JLabel("Paused", JLabel.CENTER);
		pauseLabel.setFont(FontLoader.loadFont(FontPath.CormorantGaramond, 50));
		pauseLabel.setForeground(Color.WHITE);
		pauseLabel.setBounds(0, 100, parentPanel.getWidth(), 60);

		JButton continueBtn = new JButton("Continue");
		continueBtn.setBounds((parentPanel.getWidth() / 2) - 100, 200, 200, 50);
		continueBtn.addActionListener(e -> {
			parentPanel.remove(pausePanel); // Remove the pause panel
			parentPanel.revalidate();
			parentPanel.repaint();
		});

		JButton homeBtn = new JButton("Back to Home");
		homeBtn.setBounds((parentPanel.getWidth() / 2) - 100, 300, 200, 50);
		homeBtn.addActionListener(e -> {
			parentPanel.remove(pausePanel);
			parentPanel.revalidate();
			parentPanel.repaint();
			UIManager.switchPage(Page.Home);
		});

		pausePanel.add(pauseLabel);
		pausePanel.add(continueBtn);
		pausePanel.add(homeBtn);

		parentPanel.add(pausePanel, 0); // Add panel at the top layer
		parentPanel.revalidate();
		parentPanel.repaint();
	}
}

// Let me know if you want me to add animations or improve the layout! 🚀
