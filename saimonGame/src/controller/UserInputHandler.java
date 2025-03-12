package src.controller;

import java.awt.Color;
import java.util.ArrayList;

public class UserInputHandler {
	private ArrayList<Color> input;

	public UserInputHandler() {
		input = new ArrayList<>();
	}

	public void addUserInput(Color color) {
		this.input.add(color);
	}

	public ArrayList<Color> getUserInput() {
		return input;
	}

	public void clear() {
		input.clear();
	}

	public boolean isMatching(ArrayList<Color> sequence) {
		return sequence.subList(0, input.size()).equals(input);
	}
}
