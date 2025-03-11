package ref;
import javax.swing.*;

import ref.Element.*;

import java.awt.*;
import java.util.ArrayList;

public class SimonGameMain {
	private final Color[] COLORS = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
	private ArrayList<Color> playerSequence = new ArrayList<>();
	private ColorSequence colorSequence = new ColorSequence();
	private JButton[] buttons = new JButton[4];
	private ScoreManager scoreManager;
	private int highestScore = 0;

	public void run(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Simon Game");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(400, 400);

			CardLayout cardLayout = new CardLayout();
			JPanel mainPanel = new JPanel(cardLayout);

			JPanel homePanel = new JPanel();
			homePanel.setLayout(new GridLayout(3, 1));

			JLabel homeLabel = new JLabel("Welcome to Simon Game");
			homeLabel.setHorizontalAlignment(SwingConstants.CENTER);

			JButton startButton = new JButton("Start");
			startButton.addActionListener(e -> {
				cardLayout.show(mainPanel, "game"); // เปลี่ยนหน้าไปที่เกมเมื่อกด start
			});

			JButton highestScoreButton = new JButton("Highest Score: " + highestScore);
			highestScoreButton.setEnabled(false); // ปิดปุ่ม Highest Score

			homePanel.add(homeLabel);
			homePanel.add(startButton);
			homePanel.add(highestScoreButton);

			JPanel gamePanel = new JPanel();
			gamePanel.setLayout(new GridLayout(3, 2));

			JLabel scoreLabel = new JLabel("Score: 0");
			scoreManager = new ScoreManager(scoreLabel);

			// สร้างปุ่มสำหรับเล่นเกม
			for (int i = 0; i < COLORS.length; i++) {
				JButton button = new JButton();
				button.setBackground(COLORS[i]);
				button.setOpaque(true);
				button.setBorderPainted(false);
				int index = i;
				button.addActionListener(e -> handlePlayerInput(COLORS[index]));
				buttons[i] = button;
				gamePanel.add(button);
			}

			JButton gameStartButton = new JButton("Start");
			gameStartButton.addActionListener(e -> startGame());
			gamePanel.add(gameStartButton);
			gamePanel.add(scoreLabel);


			mainPanel.add(homePanel, "home");
			mainPanel.add(gamePanel, "game");

			frame.add(mainPanel);
			frame.setVisible(true);
		});
	}

	private void startGame() {
		playerSequence.clear();
		colorSequence.clearSequence();
		colorSequence.addRandomColor();
		playSequence();
	}

	private void playSequence() {
		new Thread(() -> {
			try {
				for (Color color : colorSequence.getSequence()) {
					flashButton(color);
					Thread.sleep(500);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void flashButton(Color color) {
		for (JButton button : buttons) {
			if (button.getBackground().equals(color)) {
				SwingUtilities.invokeLater(() -> {
					button.setBackground(Color.WHITE);
					Timer timer = new Timer(150, e -> button.setBackground(color)); // Reduced flash time
					timer.setRepeats(false);
					timer.start();
				});
				break;
			}
		}
	}

	private void handlePlayerInput(Color color) {
		playerSequence.add(color);

		if (!colorSequence.getSequence().subList(0, playerSequence.size()).equals(playerSequence)) {
			JOptionPane.showMessageDialog(null, "Game Over! Your score: " + scoreManager.getCurrentScore());
			highestScore = Math.max(highestScore, scoreManager.getCurrentScore()); // Update highest score
			scoreManager.resetScore();
			colorSequence.clearSequence();
			playerSequence.clear();
		} else if (playerSequence.size() == colorSequence.getSequence().size()) {
			scoreManager.increaseScore();
			playerSequence.clear();
			colorSequence.clearSequence();  // Clear the old sequence
			colorSequence.addRandomColor(); // Generate a new random sequence
			playSequence();  // Play the new sequence immediately
		}
	}
}
