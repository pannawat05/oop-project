package utils;

public enum Level {
	EASY,
	HARD;

	public static Level getLevel(int score) {
		if (score >= 1) {
			System.out.println("Hard");
			return HARD;
		}
		return EASY;
	}
}
