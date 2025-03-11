package SimonGame;

import SimonGame.Controller.GameController;
import SimonGame.UI.UIManager;

public class SimonGame {
    public static void run() {
        GameController controller = new GameController();
        new UIManager(1200, 800, controller);
    }
}
