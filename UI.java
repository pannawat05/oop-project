import java.util.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
public class UI {

public static void main(String[] args) {
    JFrame frame = new JFrame("CS30 KMITL");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);
    frame.getContentPane().setBackground(Color.ORANGE);

    JLabel label = new JLabel("CS30 KMITL", SwingConstants.CENTER);
    label.setFont(new Font("Serif", Font.BOLD, 24));
    frame.add(label);

    frame.setVisible(true);
}


    }

    

