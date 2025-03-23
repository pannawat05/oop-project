package utils;

public enum Level {
	EASY,
	HARD;

	public static Level getLevel(int score) {
		if (score >= 5) {
			System.out.println("Hard");
			return HARD;
		}
		return EASY;
	}
}
