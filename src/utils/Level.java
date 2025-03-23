package utils;

public enum Level {
	EASY,
	HARD;

	public static Level getLevel(int score) {
		if (score > 1) {
			return HARD;
		}
		return EASY;
	}
}
