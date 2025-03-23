
import javax.swing.SwingUtilities;

import controller.Controller;

public class SimonGame {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{
			Controller controller = new Controller();
			controller.run();
		});
	}
}