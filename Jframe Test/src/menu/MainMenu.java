package menu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import main.Game;

public class MainMenu extends Menu {
	KeyListener keyboard;
	
	private int currentSelection;
	
	private boolean showUnimplemented;
	private boolean showOptions;
	private boolean showControls;
	private boolean showDumb;
	
	private Image unimplemented;
	private Image mainmenu;
	private Image selector;
	//private Image controlsscreen;
	//private Image optionsscreen;
	private Image dumb;
	
	private int x, y;
	private final int X0, Y0;
	private final int X1, Y1;
	private final int X2, Y2;
	private final int X3, Y3;
	private final int X4, Y4;
	
	public MainMenu() {
		currentSelection = 0;
		showUnimplemented = false;
		showOptions = false;
		showControls = false;
		showDumb = false;
		
		try {
			unimplemented = ImageIO.read(this.getClass().getResource("/res/unimplemented.png"));
			mainmenu = ImageIO.read(this.getClass().getResourceAsStream("/res/mainmenu.png"));
			selector = ImageIO.read(this.getClass().getResource("/res/heartselector.png"));
			
			//controlsscreen = ImageIO.read(this.getClass().getResourceAsStream("/res/controls.png"));
			//optionsscreen = ImageIO.read(this.getClass().getResourceAsStream("/res/options.png"));
			
			dumb = ImageIO.read(this.getClass().getResourceAsStream("/res/dumb.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		X0 = 720;
		Y0 = 40;
		X1 = 740;
		Y1 = 90;
		X2 = 760;
		Y2 = 140;
		X3 = 750;
		Y3 = 190;
		X4 = 720;
		Y4 = 240;
		
		x = X0;
		y = Y0;
	}
	
	public void render(Graphics g) {		
		g.drawImage(mainmenu, 0, 0, null);
		g.drawImage(selector, x, y, null);
		
		if (showControls) g.drawImage(unimplemented, 0, 0, null);
		if (showOptions) g.drawImage(unimplemented, 0, 0, null);
		if (showUnimplemented) g.drawImage(unimplemented, 0, 0, null);
		if (showDumb) g.drawImage(dumb, 0, 0, null);
		
		g.dispose();
	}
	public void update() {
		//System.out.println(x + "," + y);
	}
	
	public void setUpInput(JFrame frame) {
		JPanel pane = (JPanel) frame.getContentPane();
		//up
		pane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("UP"), "move up");
		pane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("released UP"), "release up");
		pane.getActionMap().put("move up", new PressAction("up", false));
		pane.getActionMap().put("release up", new PressAction("up", true));
		//down
		pane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("DOWN"), "move down");
		pane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("released DOWN"), "release down");
		pane.getActionMap().put("move down", new PressAction("down", false));
		pane.getActionMap().put("release down", new PressAction("down", true));
		//enter
		pane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"), "press enter");
		pane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("released ENTER"), "release enter");
		pane.getActionMap().put("press enter", new PressAction("enter", false));
		pane.getActionMap().put("release enter", new PressAction("enter", true));
		//escape
		pane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ESCAPE"), "press escape");
		pane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("released ESCAPE"), "release escape");
		pane.getActionMap().put("press escape", new PressAction("escape", false));
		pane.getActionMap().put("release escape", new PressAction("escape", true));
	}
	
	private class PressAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		String key;
		boolean released;
		
		public PressAction(String key, boolean released) {
			this.key = key;
			this.released = released;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!released) {
				switch (key) {
				case("up"):
					up();
					break;
				case("down"):
					down();
					break;
				case("enter"):
					enter();
					break;
				case("escape"):
					escape();
					break;
				}
			}
		}	
	}
	
	
	private void selection() {
		switch(currentSelection) {
			case 0:
				x = X0;
				y = Y0;
				break;
			case 1:
				x = X1;
				y = Y1;
				break;
			case 2:
				x = X2;
				y = Y2;
				break;
			case 3:
				x = X3;
				y = Y3;
				break;
			case 4:
				x = X4;
				y = Y4;
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
				Game.game.state = 0;
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
				showDumb = true;
				break;
		}
	}
	private void escape() {
		if (showOptions || showControls || showUnimplemented || showDumb) {
			showOptions = false;
			showControls = false;
			showUnimplemented = false;
			showDumb = false;
		} else {
			currentSelection = 4;
		}
		if (currentSelection == 4) {
			showDumb = true;
		}
	}
}
