import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

// Enum สำหรับระดับความยาก
enum Level {
    EASY(1), NORMAL(2), HARD(3);

    private final int difficulty;

    Level(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }
}

// คลาสสำหรับสุ่มลำดับปุ่มที่ต้องกระพริบ
class ColorSequence {
    private final ArrayList<Color> sequence = new ArrayList<>();
    private final Random random = new Random();
    private final Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};

    public void addRandomColor() {
        sequence.add(colors[random.nextInt(colors.length)]);
    }

    public ArrayList<Color> getSequence() {
        return sequence;
    }

    public void clearSequence() {
        sequence.clear();
    }
}

// คลาสสำหรับจัดการคะแนนและเลเวล
class ScoreManager {
    private int currentScore = 0;
    private Level currentLevel = Level.EASY;
    private JLabel scoreLabel;

    public ScoreManager(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public void increaseScore() {
        currentScore += 10;
        updateLevel();
        updateScoreLabel();
    }

    private void updateLevel() {
        if (currentScore >= 50) {
            currentLevel = Level.HARD;
        } else if (currentScore >= 20) {
            currentLevel = Level.NORMAL;
        } else {
            currentLevel = Level.EASY;
        }
    }

    public void resetScore() {
        currentScore = 0;
        currentLevel = Level.EASY;
        updateScoreLabel();
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    private void updateScoreLabel() {
        SwingUtilities.invokeLater(() -> scoreLabel.setText("Score: " + currentScore));
    }
}

public class SimonGameMain {
    private static final Color[] COLORS = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
    private static ArrayList<Color> playerSequence = new ArrayList<>();
    private static ColorSequence colorSequence = new ColorSequence();
    private static JButton[] buttons = new JButton[4];
    private static ScoreManager scoreManager;
    private static int highestScore = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Simon Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);

            // ใช้ CardLayout สำหรับการเปลี่ยนหน้า
            CardLayout cardLayout = new CardLayout();
            JPanel mainPanel = new JPanel(cardLayout);

            // หน้า Home
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

            // หน้าเกม
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

            // เพิ่มหน้า Home และ Game ลงใน CardLayout
            mainPanel.add(homePanel, "home");
            mainPanel.add(gamePanel, "game");

            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }

    private static void startGame() {
        playerSequence.clear();
        colorSequence.clearSequence(); // Clear sequence at the start
        colorSequence.addRandomColor();
        playSequence();
    }

    private static void playSequence() {
        new Thread(() -> {
            try {
                for (Color color : colorSequence.getSequence()) {
                    flashButton(color);
                    Thread.sleep(500); // Reduced delay for quicker play
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void flashButton(Color color) {
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

    private static void handlePlayerInput(Color color) {
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
