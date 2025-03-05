package Element;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class ColorSequence {
    private final ArrayList<Color> sequence = new ArrayList<>();
    private final Random random = new Random();
    private final Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};

    public void addRandomColor() {
        sequence.add(colors[random.nextInt(colors.length)]);
    }

    public ArrayList<Color> getSequence() {
        return sequence;
    }

    public void clearSequence() {
        sequence.clear();
    }
}
