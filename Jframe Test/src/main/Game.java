package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

import menu.MainMenu;
import menu.Menu;

public class Game extends Canvas implements Runnable {

	/**
	 * Test Game Created by Firstdream
	 */
	private static final long serialVersionUID = 1L;

	public final static int WIDTH = 960;
	public final static int HEIGHT = WIDTH / 16 * 9;
	public final static int SCALE = 1;
	public final static String GAME_NAME = "TEST";

	private Thread thread;
	private boolean running = false;
	private Dialog dialog;

	private JFrame frame;

	private boolean isPause;
	private Graphics screen;
	private Level level;
	private Menu menu;
	public static Game game;
	
	public int state;

	public Game() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);

		frame = new JFrame();
	}

	private void init(Game game) {
		game.frame.setResizable(false);
		game.frame.setTitle(GAME_NAME);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setVisible(true);
		game.frame.setLocationRelativeTo(null);
		
		
		running = true;
		createBufferStrategy(3);
		level = new Level(SCALE);
		//game.addKeyListener(level.getKeyboard());
		level.setUpInput(frame);
		menu = new MainMenu();
		if (!MainMenu.disableHotkeys) menu.setUpInput(frame);
		state = 1;
		dialog = new Dialog();
		
		start();
	}

	public synchronized void start() {
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		if(state == 0)
			level.update();
		else if(state == 1) {
			menu.update();
		}
		else if(state == 2){
			dialog.update();
		}
	}

	public void render() {
		screen = getBufferStrategy().getDrawGraphics();
		screen.setColor(Color.CYAN);
		screen.fillRect(0, 0, 960, 540);
		if(state == 0 || state == 2)
			level.render(screen);
		if(state == 2) {
			int alpha = 140; // 50% transparent
			screen.setColor(new Color(0, 0, 0, alpha));
			screen.fillRect(0, 0, 960, 540);
			dialog.render(screen);
		}
		if(state == 1) {
			menu.render(screen);
		}
		screen.dispose();
		getBufferStrategy().show();
	}

	public void run() {

		final double HERTZ = 60.0;
		final double TIME_BETWEEN_UPDATES = 1000000000 / HERTZ;
		final int MAX_UPDATES_BEFORE_RENDER = 2;
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime = System.nanoTime();
		final double TARGET_FPS = 60;
		final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);

		while (running) {
			double now = System.nanoTime();
			int updateCount = 0;

			if (!isPause) {
				while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
					update();
					lastUpdateTime += TIME_BETWEEN_UPDATES;
					updateCount++;
				}

				if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
					lastUpdateTime = now - TIME_BETWEEN_UPDATES;
				}

				render();

				lastRenderTime = now;

				int thisSecond = (int) (lastUpdateTime / 1000000000);
				if (thisSecond > lastSecondTime) {
					lastSecondTime = thisSecond;
				}
				while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS
						&& now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
					Thread.yield();
					try {
						Thread.sleep(1);
					} catch (Exception e) {
					}
					now = System.nanoTime();
				}
			}
		}
	}

	public static void main(String[] args) {
		game = new Game();
		game.init(game);
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	public void dialogAdd(String string, Sprite sprite, int x) {
		dialog.add(string, sprite, x);
	}
	
	public void dialogStart() {
		state = 2;
	}
	
	public void dialogNext() {
		dialog.next();
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
