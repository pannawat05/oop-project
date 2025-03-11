package SimonGame.UI;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import SimonGame.Controller.GameController;

public class UIManager {
	public static CardLayout cardLayout = new CardLayout();
	private static JPanel cards = new JPanel(cardLayout);
	private static Page page = Page.Home;
	private JFrame frame;

	public static void switchPage(Page p) {
		page = p;
		cardLayout.show(cards, page.toString());
	}

	public UIManager(int w, int h, GameController controller) {
		frame = new JFrame("Saimon Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(w, h);
		frame.setMinimumSize(new Dimension(w, h));
		frame.setMaximumSize(new Dimension(w, h));
		frame.setResizable(false);

		Panel[] panels = new Panel[] {
			new HomePage(controller),
			new PlayPage(controller),
			new GameOverPage(controller)
		};
		for (Panel panel : panels) {
			cards.add(panel.getPage(w, h), panel.getName());
		}
		cardLayout.show(cards, page.toString());
        frame.add(cards);
		frame.setVisible(true);
	}

	// private List<JComponent> getAllJComponents(Container container) {
	// List<JComponent> components = new ArrayList<>();
	// for (Component comp : container.getComponents()) {
	// if (comp instanceof JComponent) {
	// components.add((JComponent) comp);
	// }
	// if (comp instanceof Container) {
	// components.addAll(getAllJComponents((Container) comp)); // Recursive call for
	// nested containers
	// }
	// }
	// return components;
	// }
}
