package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.SimonColor;

public class ColorSequence {

	private ArrayList<Color> sequence;

	public ColorSequence() {
		sequence = new ArrayList<>();
	}

	public void addRandomColor() {
		sequence.add(SimonColor.DEFAULT.get((int) (Math.random() * SimonColor.DEFAULT.size())));
		System.out.println("Sequence: "+sequence);
	}

	public void addColor(Color color) {
		sequence.add(color);
	}

	public ArrayList<Color> getSequence() {
		return sequence;
	}

	public boolean isSubset(List<Color> subSet) {
		if (subSet.isEmpty() || subSet.size() > sequence.size()) return false;
		return sequence.subList(0, subSet.size()).equals(subSet);
	}
	public boolean isEqual(ArrayList<Color> subSet) {
		return sequence.equals(subSet);
	}

	public void clear() {
		sequence.clear();
	}
}