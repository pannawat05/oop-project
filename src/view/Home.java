package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.Controller;
import utils.MusicPlayer;
import utils.Page;
import utils.Loader;
import utils.SimonColor;

public class Home extends PagePanel {

	private Image backgroundImage;
	private MusicPlayer musicPlayer;
	private MusicPlayer buttonSound;

	public Home(Controller controller) {
		super(controller);
		this.backgroundImage = Loader.loadImage("resources/images/BG.jpg");
		this.musicPlayer = new MusicPlayer("resources/sounds/bgm.wav");
		this.buttonSound = new MusicPlayer("resources/sounds/click-start.wav");
		setLayout(null);
		drawPage();
	}

	@Override
	public void drawPage() {
		ArrayList<Color> colors = SimonColor.FLASH;

		JPanel simon = new JPanel();
		simon.setLayout(new FlowLayout(FlowLayout.LEFT));
		simon.setOpaque(false);
		add(simon);

		JPanel game = new JPanel();
		game.setLayout(new FlowLayout(FlowLayout.LEFT));
		game.setOpaque(false);
		add(game);

		// SIMON GAME -------------------------------
		JLabel si = new JLabel("SI");
		si.setForeground(colors.get(0));
		simon.add(si);

		JLabel mon = new JLabel("MON");
		mon.setForeground(colors.get(1));
		simon.add(mon);

		JLabel ga = new JLabel("GA");
		ga.setForeground(colors.get(2));
		game.add(ga);

		JLabel me = new JLabel("ME");
		me.setForeground(colors.get(3));
		game.add(me);
		// ---------------------------------------------

		// Button Start -------------------------------
		RoundedBTN startBTN = new RoundedBTN("Start");
		startBTN.setForeground(Color.WHITE);
		startBTN.setBackground(new Color(0, 0, 0));
		startBTN.setOpaque(false);
		startBTN.setContentAreaFilled(false);
		startBTN.setFocusPainted(false);
		startBTN.setBorderPainted(true);
		startBTN.addActionListener(e -> {
			controller.switchPage(Page.GAME_PLAY);
			buttonSound.playSound();
		});
		startBTN.setHoverEffect(Color.YELLOW, new Color(255, 255, 0, 10));
		add(startBTN);
		// ---------------------------------------------

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int titleSize = getWidth() / 8;
				int width = getWidth();
				int height = getHeight();
				int simonX = 60;
				int simonY = 40;

				Font simonFont = Loader.loadCustomFont("resources/fonts/Glaure.ttf", titleSize);
				Font gameFont = Loader.loadCustomFont("resources/fonts/Glaure.ttf", (int) (titleSize / 1.25));
				Font startFont = Loader.loadCustomFont("resources/fonts/static/RobotoSlab-Bold.ttf", getWidth() / 25);

				simon.setBounds(simonX, simonY, width, titleSize + 5);
				game.setBounds(simonX, simonY + titleSize + 5, width, titleSize + 5);

				// SIMON
				si.setFont(simonFont);
				mon.setFont(simonFont);

				// GAME
				ga.setFont(gameFont);
				me.setFont(gameFont);

				// Start BTN
				startBTN.setBounds(80, (int) (height / 1.8), (int) (width / 4.5), startFont.getSize() + 40);
				startBTN.setFont(startFont);
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}

	@Override
	public void onOpen() {
		this.musicPlayer.loopSound();
	}

	@Override
	public void onClose() {
		this.musicPlayer.stopSound();
	}
}
