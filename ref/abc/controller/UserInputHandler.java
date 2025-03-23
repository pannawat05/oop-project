package abc.controller;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;

public class UserInputHandler {
	private ArrayList<Color> input;
	private JButton lastBtn;

	public UserInputHandler() {
		input = new ArrayList<>();
	}

	public void addUserInput(Color color, JButton lasButton) {
		this.input.add(color);
		this.lastBtn = lasButton;
	}

	public ArrayList<Color> getUserInput() {
		return input;
	}

	public void clear() {
		input.clear();
	}

	public boolean isMatching(ArrayList<Color> sequence) {
		// System.out.println("\n Is Match \n" + sequence.subList(0, input.size()) + "\n" + input + "\n" + sequence.subList(0, input.size()).equals(input));
		return sequence.subList(0, input.size()).equals(input);
	}

	public JButton getLastINP() {
		return lastBtn;
	}
}
