package menu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class MainMenu extends Menu {
	private int currentSelection;
	
	private boolean showOptions;
	private boolean showControls;
	
	private Image unimplemented;
	private Image mainmenu;
	private Image selector;
	private Image controlsscreen;
	private Image optionsscreen;
	
	private int x, y;
	private int x0, y0;
	private int x1, y1;
	private int x2, y2;
	private int x3, y3;
	private int x4, y4;
	
	public MainMenu() {
		currentSelection = 0;
		showOptions = false;
		showControls = false;
		
		unimplemented = Toolkit.getDefaultToolkit().getImage("/res/unimplemented.png");
		mainmenu = Toolkit.getDefaultToolkit().getImage("/res/mainmenu.png");
		selector = Toolkit.getDefaultToolkit().getImage("/res/heartselector.png");
		controlsscreen = Toolkit.getDefaultToolkit().getImage("/res/controls.png");
		optionsscreen = Toolkit.getDefaultToolkit().getImage("/res/options.png");
		
		x0 = 720;
		y0 = 70;
		x1 = 760;
		y1 = 120;
		x2 = 780;
		y2 = 170;
		x3 = 770;
		y3 = 220;
		x4 = 740;
		y4 = 270;
	}
	
	public void render(Graphics g) {		
		g.drawImage(mainmenu, 0, 0, null);
		g.drawImage(selector, x, y, null);
		
		if (showControls == true) {
			g.drawImage(unimplemented, 0, 0, null);
		}
		if (showOptions == true) {
			g.drawImage(unimplemented, 0, 0, null);
		}
		
		g.dispose();
	}
	public void update() {
		
	}
	
	private void selection() {
		switch(currentSelection) {
			case 0:
				x = x0;
				y = y0;
				break;
			case 1:
				x = x1;
				y = y1;
				break;
			case 2:
				x = x2;
				y = y2;
				break;
			case 3:
				x = x3;
				y = y3;
				break;
			case 4:
				x = x4;
				y = y4;
				break;
		}
	}
	
	private void down() {
		if (currentSelection < 4) {
			currentSelection++;
			selection();
		} else {
			currentSelection = 0;
			selection();
		}
	}
	private void up() {
		if (currentSelection > 0) {
			currentSelection--;
			selection();
		} else {
			currentSelection = 4;
			selection();
		}
	}
	private void enter() {
		switch(currentSelection) {
			// new game
			case 0:
				
				break;
			// continue game
			case 1:
				
				break;
			// options
			case 2:
				showOptions = true;
				break;
			// controls
			case 3:
				showControls = true;
				break;
			// exit game
			case 4:
				//Game.stop();
				break;
		}
	}
	private void escape() {
		if (showOptions || showControls) {
			showOptions = false;
			showControls = false;
		} else {
			currentSelection = 4;
		}
	}
	
	public void keyPressed(int code) {
		switch(code) {
			case KeyEvent.VK_UP:
				up();
				break;
			case KeyEvent.VK_DOWN:
				down();
				break;
				
			case KeyEvent.VK_ENTER:
				enter();
				break;
			case KeyEvent.VK_ESCAPE:
				escape();
		}
	}
}
