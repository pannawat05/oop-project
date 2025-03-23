package abc.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class ColorSequence {
	private ArrayList<Color> sequence;
	private Color[] color = GameController.COLOR;
	private Random random = new Random();

	public ColorSequence() {
		sequence = new ArrayList<>();
	}

	public void addRandomColor() {
        sequence.add(color[random.nextInt(color.length)]);
	}

	public ArrayList<Color> getSequence() {
		return sequence;
	}

	public void clear() {
		sequence.clear();
	}

	public boolean isComplete(ArrayList<Color> inp) {
		return inp.size() == sequence.size();
	}

	@Override
	public String toString() {
		return "ColorSequence [sequence=" + sequence + "]";
	}
}
