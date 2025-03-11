package ref;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class AbsoluteLayoutExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Absolute Layout with Responsiveness");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(null); // No layout manager, Absolute positioning

        // Create components
        JLabel label1 = new JLabel("Responsive Label 1");
        label1.setBounds(50, 50, 200, 30); // Set initial position and size
        frame.add(label1);

        JButton button1 = new JButton("Responsive Button");
        button1.setBounds(50, 100, 200, 30); // Initial position and size
        frame.add(button1);

        // Add a component listener to adjust positions on resize
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Adjust label size and position on resize
                int width = frame.getWidth();
                int height = frame.getHeight();

                // Example of responsiveness: Dynamically adjust size/position based on window size
                label1.setBounds(width / 4, height / 4, width / 2, 30);
                button1.setBounds(width / 4, height / 2, width / 2, 30);
            }
        });

        frame.setVisible(true);
    }
}
