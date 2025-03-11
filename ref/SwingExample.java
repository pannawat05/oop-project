package ref;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Page Navigation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a CardLayout container
        CardLayout cardLayout = new CardLayout();
        JPanel cards = new JPanel(cardLayout);

        // Create two panels (pages)
        JPanel page1 = new JPanel();
        page1.add(new JLabel("This is Page 1"));
        JButton goToPage2Button = new JButton("Go to Page 2");
        goToPage2Button.addActionListener(e -> cardLayout.show(cards, "Page 2"));
        page1.add(goToPage2Button);

        JPanel page2 = new JPanel();
        page2.add(new JLabel("This is Page 2"));
        JButton goToPage1Button = new JButton("Go to Page 1");
        goToPage1Button.addActionListener(e -> cardLayout.show(cards, "Page 1"));
        page2.add(goToPage1Button);

        // Add pages to the CardLayout container
        cards.add(page1, "Page 1");
        cards.add(page2, "Page 2");

        // Show the first page by default
        cardLayout.show(cards, "Page 1");

        // Add the CardLayout container to the frame
        frame.add(cards);
        frame.setVisible(true);
    }
}
