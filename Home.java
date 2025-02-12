import java.util.*;
import java.lang.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.color.*;
public class Home {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.decode("#f256"));
        frame.setVisible(true);
        frame.setTitle("hello world");
        JLabel label = new JLabel("Hello World");
        label.setBounds(100, 100, 100, 100);
        label.setForeground(Color.BLUE);
        frame.add(label);
    }
}