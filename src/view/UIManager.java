package view;

import java.awt.CardLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utils.Page;
import controller.Controller;
import model.Score;

public class UIManager {

	private Page page = Page.HOME;
	private JFrame frame;
	private CardLayout cardLayout;
	private JPanel card;
	private HashMap<Page, PagePanel> pagePanels;
	// private Controller controller;

	public UIManager(Controller controller) {
		// this.controller = controller;

		this.frame = new JFrame("Simon Game");
		this.cardLayout = new CardLayout();
		this.card = new JPanel(cardLayout);
		this.pagePanels = new HashMap<>();

		Page[] pages = Page.values();
		PagePanel[] panels = {
				new Home(controller),
				new GamePlay(controller),
				new GameOver(controller),
		};
		for (int i = 0; i < pages.length; i++) {
			this.pagePanels.put(pages[i], panels[i]);
			card.add(panels[i], pages[i].toString());
		}
	}

	public void display() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 800);
		frame.setMinimumSize(frame.getSize());
		frame.setLayout(cardLayout);
		updateUI(page);
		frame.add(card);
		frame.setVisible(true);
	}

	public void playSequence() {
		((GamePlay) pagePanels.get(Page.GAME_PLAY)).playSequence();
	}

	public void nextRound() {
		((GamePlay) pagePanels.get(Page.GAME_PLAY)).nextRound();
	}

	public void updateUI(Page page) {
		this.page = page;
		cardLayout.show(card, page.toString());
		pagePanels.values().stream().forEach(panel -> panel.onClose());
		pagePanels.get(page).onOpen();
	}

	public void swapSimonBTN() {
		((GamePlay) pagePanels.get(Page.GAME_PLAY)).swapSimonBTN();
	}

	public void updateScoreUI(Score score) {
		((GamePlay) pagePanels.get(Page.GAME_PLAY)).updateScore(score);
		((GameOver) pagePanels.get(Page.GAME_OVER)).updateScore(score);
	}
}
