package src.controller;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {
	public static Font loadFont(String fontPath, int size) {
		try (InputStream is = FontLoader.class.getResourceAsStream(fontPath)) {
			if (is == null) {
				System.out.println("Font file not found: " + fontPath);
				return new Font("Arial", Font.PLAIN, size);
			}

			Font font = Font.createFont(Font.TRUETYPE_FONT, is);

			// Register the font in the graphics environment
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);

			// Debug: Check if font can display basic characters
			if (font.canDisplay('A')) {
				System.out.println("Font loaded and can display characters!");
			} else {
				System.out.println("Font loaded, but cannot display characters.");
			}

			return font.deriveFont((float) size);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
			System.out.println("Failed to load font. Falling back to Arial.");
			return new Font("Arial", Font.PLAIN, size);
		}
	}
}