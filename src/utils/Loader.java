package utils;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.ImageIcon;

public class Loader {

	public static Image loadImage(String path) {
		try {
			File imageFile = new File(path);
			FileInputStream fis = new FileInputStream(imageFile);
			byte[] bytes = fis.readAllBytes();
			fis.close();
			return new ImageIcon(bytes).getImage();
		} catch (Exception e) {
			System.err.println("Error: Image not found at " + path);
			return null;
		}
	}

	public static Font loadCustomFont(String path, float size) {
		try {
			File fontFile = new File(path);
			FileInputStream fis = new FileInputStream(fontFile);
			Font font = Font.createFont(Font.TRUETYPE_FONT, fis).deriveFont(size);
			fis.close();
			return font;
		} catch (Exception e) {
			System.err.println("Error: Font not found at " + path);
			return new Font("Arial", Font.PLAIN, (int) size);
		}
	}
}
