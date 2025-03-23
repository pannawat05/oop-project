package utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimonColor {

	private static Color dfRED = Color.decode("#912121");
	private static Color dfGREEN = Color.decode("#41682e");
	private static Color dfBLUE = Color.decode("#284074");
	private static Color dfYELLOW = Color.decode("#74662e");

	private static Color flRED = Color.decode("#ff3131");
	private static Color flGREEN = Color.decode("#89d165");
	private static Color flBLUE = Color.decode("#4bd1fb");
	private static Color flYELLO = Color.decode("#ffde59");

	public static ArrayList<Color> DEFAULT = new ArrayList<Color>() {
		{
			add(dfRED);
			add(dfGREEN);
			add(dfBLUE);
			add(dfYELLOW);
		}
	};

	public static ArrayList<Color> FLASH = new ArrayList<Color>() {
		{
			add(flRED);
			add(flGREEN);
			add(flBLUE);
			add(flYELLO);
		}
	};

	public static void swapCOLOR() {
        int size = DEFAULT.size();
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            indices.add(i);
        }

        Random rand = new Random();
        do {
            Collections.shuffle(indices, rand);
        } while (!isValidShuffle(indices));

        List<Color> newDefault = new ArrayList<>(DEFAULT);
        List<Color> newFlash = new ArrayList<>(FLASH);

        for (int i = 0; i < size; i++) {
            newDefault.set(i, DEFAULT.get(indices.get(i)));
            newFlash.set(i, FLASH.get(indices.get(i)));
        }

        for (int i = 0; i < size; i++) {
            DEFAULT.set(i, newDefault.get(i));
            FLASH.set(i, newFlash.get(i));
        }
    }

	private static boolean isValidShuffle(List<Integer> indices) {
        for (int i = 0; i < indices.size(); i++) {
            if (indices.get(i) == i) {
                return false;
            }
        }
        return true;
    }

	public static void reset() {
		DEFAULT = new ArrayList<Color>() {
			{
				add(dfRED);
				add(dfGREEN);
				add(dfBLUE);
				add(dfYELLOW);
			}
		};

		FLASH = new ArrayList<Color>() {
			{
				add(flRED);
				add(flGREEN);
				add(flBLUE);
				add(flYELLO);
			}
		};
	}
}
