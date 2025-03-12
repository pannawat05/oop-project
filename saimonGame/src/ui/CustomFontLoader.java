package src.ui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class CustomFontLoader {
    public static Font loadFont(String fontPath, float size) {
        System.out.println(CustomFontLoader.class.getResourceAsStream(fontPath));
        try (InputStream is = CustomFontLoader.class.getResourceAsStream(fontPath)) {
            if (is == null) {
                System.out.println("Font file not found: " + fontPath);
                return new Font("Arial", Font.PLAIN, 24); // Fallback font
            }
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(size);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, 24); // Fallback font
        }
    }
}
