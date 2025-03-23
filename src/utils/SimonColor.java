package utils;

import java.awt.Color;
import java.util.ArrayList;

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
		int random1 = (int) (Math.random() * DEFAULT.size());
		int random2 = (int) (Math.random() * DEFAULT.size());

		Color tempDefault = DEFAULT.get(random1);
		Color tempFlash = FLASH.get(random1);

		DEFAULT.set(random1, DEFAULT.get(random2));
		FLASH.set(random1, FLASH.get(random2));

		DEFAULT.set(random2, tempDefault);
		FLASH.set(random2, tempFlash);
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