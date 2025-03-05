package Element;

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
