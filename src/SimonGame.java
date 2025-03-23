
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import controller.Controller;

public class SimonGame {

	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

		SwingUtilities.invokeLater(()->{
			Controller controller = new Controller();
			controller.run();
		});
	}
}