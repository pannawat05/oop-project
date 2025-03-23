package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import controller.Controller;
import utils.Loader;
import utils.Page;

public class Pause extends PagePanel {
	JLayeredPane layeredPane;

	public Pause(Controller controller, JLayeredPane layeredPane) {
		super(controller);
		this.layeredPane = layeredPane;
		setLayout(null);
		setOpaque(false);
		// setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
		addMouseListener(new MouseAdapter() {
		});
		drawPage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Graphics2D g2d = (Graphics2D) g;
		// g2d.setColor(new Color(0, 0, 0, 100));
		// g2d.fillRect(0, 0, getWidth(), getHeight());
	}

	@Override
	public void drawPage() {
		JLabel pauseLabel = new JLabel("Paused", JLabel.CENTER);
		pauseLabel.setForeground(Color.blue);
		add(pauseLabel);

		RoundedBTN continueBtn = new RoundedBTN("Continue");
		continueBtn.setForeground(Color.black);
		continueBtn.setBackground(new Color(255, 255, 255, 50));
		continueBtn.setOpaque(true);
		continueBtn.setContentAreaFilled(false);
		continueBtn.setFocusPainted(false);
		continueBtn.setBorderPainted(true);
		continueBtn.setHoverEffect(Color.YELLOW, new Color(255, 255, 0, 10));
		continueBtn.addActionListener(e -> {
			controller.resumeGame();
			layeredPane.remove(this);
			layeredPane.revalidate();
			layeredPane.repaint();
		});
		add(continueBtn);

		RoundedBTN restart = new RoundedBTN("Restart");
		restart.setForeground(Color.black);
		restart.setBackground(new Color(255, 255, 255, 50));
		restart.setOpaque(false);
		restart.setContentAreaFilled(false);
		restart.setFocusPainted(false);
		restart.setBorderPainted(true);
		restart.setHoverEffect(Color.YELLOW, new Color(255, 255, 0, 10));
		restart.addActionListener(e -> {
			controller.resumeGame();
			controller.startGame();
			layeredPane.remove(this);
			layeredPane.revalidate();
			layeredPane.repaint();
		});
		add(restart);

		RoundedBTN homeBtn = new RoundedBTN("Back to Home");
		homeBtn.setForeground(Color.black);
		homeBtn.setBackground(new Color(255, 255, 255, 50));
		homeBtn.setOpaque(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setFocusPainted(false);
		homeBtn.setBorderPainted(true);
		homeBtn.setHoverEffect(Color.YELLOW, new Color(255, 255, 0, 10));
		homeBtn.addActionListener(e -> {
			controller.resumeGame();
			layeredPane.remove(this);
			layeredPane.revalidate();
			layeredPane.repaint();
			controller.switchPage(Page.HOME);
		});
		add(homeBtn);

		System.out.println("HelloWorld");
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int w = getWidth();
				int h = getHeight();
				System.out.println(w + " " + h);
				Font font = Loader.loadCustomFont("/resources/fonts/RobotoSlab-VariableFont_wght.ttf", w / 40);

				pauseLabel.setFont(font);
				pauseLabel.setBounds(0, 50, w - 80, 100);

				continueBtn.setBounds((int) (w / 3.75), 250, 500, 80);
				continueBtn.setFont(font);

				restart.setBounds((int) (w / 3.75), 350, 500, 80);
				restart.setFont(font);

				homeBtn.setBounds((int) (w / 3.75), 450, 500, 80);
				homeBtn.setFont(font);
			}
		});

	}

	@Override
	public void onOpen() {

	}

	@Override
	public void onClose() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'onClose'");
	}

}
