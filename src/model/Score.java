package model;

import utils.Level;

public class Score {

    private int score;
    private Level level;
    public static int HIGHT_SCORE = 0;
    public static boolean IS_NEW_HIGH_SCORE = false;

    public Score() {
        this.score = 0;
        IS_NEW_HIGH_SCORE = false;
    }

    public int getScore() {
        return score;
    }

    public Level getLevel() {
        return level;
    }

    public void increteScore() {
        score++;
        updateLevel();
        if (score > HIGHT_SCORE) {
            HIGHT_SCORE = score;
            IS_NEW_HIGH_SCORE = true;
        }
    }

    public void updateLevel() {
        this.level = Level.getLevel(score);
    }

    public void resetScore() {
        score = 0;
        IS_NEW_HIGH_SCORE = false;
    }
}