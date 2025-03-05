package Element;
import javax.swing.*;
public class ScoreManager {
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
