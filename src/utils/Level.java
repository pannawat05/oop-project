package utils;

public enum Level {
	EASY,
	HARD;

	public static Level getLevel(int score) {
		if (score >= 7) {
			return HARD;
		}
		return EASY;
	}
}
