package src.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.controller.GameController;
import src.controller.ScoreManager;

public class PlayPage implements Panel {
	private GameController controller;
	private JButton[] saimonBTN = new JButton[4];
	private JLabel[] scoreLabels = new JLabel[2];

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
        pause.setFont(CustomFontLoader.loadFont("", 36));
        pause.setForeground(Color.WHITE);
        pause.setBackground(new Color(0, 0, 0, 0));
        pause.setFocusPainted(false);
        pause.setBorderPainted(false);
        pause.setContentAreaFilled(false);
		pause.addActionListener(e -> {System.out.println("Pause");});
        panel.add(pause);

        // Score label
        scoreLabels[0] = new JLabel("Score: " + controller.getScore());
        scoreLabels[0].setFont(new Font("Arial", Font.BOLD, 32));
        scoreLabels[0].setForeground(Color.WHITE);
        scoreLabels[0].setBounds((int)(w /1.5), (int)(h /16), 300, 30);

        panel.add(scoreLabels[0]);

        // Highest score label
        scoreLabels[1] = new JLabel("highest: " + ScoreManager.getHighest());
        scoreLabels[1].setFont(new Font("Arial", Font.BOLD, 32));
        scoreLabels[1].setForeground(Color.WHITE);
        scoreLabels[1].setBounds((int)(w /1.5), (int)(h /1.25), 300, 40);
        panel.add(scoreLabels[1]);

        // Game grid (2x2 layout for colored buttons)
        int gridSize = 400;
        int gridX = (int)((w - gridSize) / 2.25);
        int gridY = (int)((h - gridSize) / 2.5);

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(2, 2, 10, 10)); // 2x2 grid with gaps
        gridPanel.setBounds(gridX, gridY, gridSize, gridSize);
        gridPanel.setBackground(Color.BLACK);

        // Add the 4 colored panels
        for (int i = 0; i < saimonBTN.length; i++) {
			saimonBTN[i].setBackground(GameController.COLOR[i]);
			int index = i;
			saimonBTN[i].addActionListener(e -> controller.onSaimonBTNClick(GameController.COLOR[index]));
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

	private void pauseGame() {

	}
}
