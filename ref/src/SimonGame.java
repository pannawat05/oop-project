package src;

import src.controller.GameController;
import src.ui.UIManager;

public class SimonGame {
	public void run() {
		GameController controller = new GameController();
		new UIManager(1200, 800, controller);
	}
}
