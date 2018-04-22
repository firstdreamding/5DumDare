package entity;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import main.Animation;
import main.Game;
import main.Sprite;

public class Player {
	KeyListener keyboard;

	Animation animation;
	Animation walkRight;
	Animation walkLeft;
	Animation walkUp;
	Animation walkDown;
	Animation standUp, standDown, standRight, standLeft;

	Sprite sprite;
	// BufferedImage currentSprite;

	boolean walkY, walkX;
	int mapX, mapY;

	private int scale, x, y, tick;
	private boolean right, left, up, down, middleOfRight, middleOfLeft, middleOfUp, middleOfDown;

	public Player(int scale) {
		initKeyboard();
		sprite = new Sprite("spriteSheet", 32);
		walkLeft = new Animation(sprite, 0, 2, 1, 1, 16, true);
		walkRight = new Animation(sprite, 0, 2, 2, 2, 16, true);
		walkUp = new Animation(sprite, 0, 2, 3, 3, 16, true);
		walkDown = new Animation(sprite, 0, 2, 0, 0, 16, true);
		standUp = new Animation(sprite, 1, 1, 3, 3, 10000, false);
		standDown = new Animation(sprite, 1, 1, 0, 0, 10000, false);
		standRight = new Animation(sprite, 1, 1, 2, 2, 10000, false);
		standLeft = new Animation(sprite, 1, 1, 1, 1, 10000, false);

		animation = standDown;
		this.scale = scale;
		x = 320;
		y = 320;
		mapX = 0;
		mapY = 0;
	}

	public void update(List<Entity> entities) {
		animation.update();
		// System.out.println((x % 32 == 0 )+ " " + (!right && animation != walkUp &&
		// animation != walkDown));
		if (right || middleOfRight || animation == walkRight) {

			if (x > 831) {
				System.out.println(-(300 - 1) * 32);
				if (mapX > -(300 - 1) * 32)
					mapX -= 2;
			} else
				x += 2;
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).checkHitReturn(new Point(x + 30, y))) {
					if (x > 831)
						mapX += 2;
					else
						x -= 2;
				}
			}
			if (x % 32 == 0 && mapX % 32 == 0) {
				middleOfRight = false;
				if (!right && animation != walkUp && animation != walkDown) {
					animation = standRight;
					System.out.println("test");
				}
			} else
				middleOfRight = true;
		} else if (left || middleOfLeft || animation == walkLeft) {
			if (x < 129) {
				if (mapX < 0)
					mapX += 2;
			} else
				x -= 2;
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).checkHitReturn(new Point(x, y))) {
					if (x < 129)
						mapX -= 2;
					else
						x += 2;
				}
			}
			if (x % 32 == 0 && mapX % 32 == 0) {
				middleOfLeft = false;
				if (!left && animation != walkUp && animation != walkDown) {
					animation = standLeft;
					System.out.println("test");
				}
			} else
				middleOfLeft = true;
		}
		if (up || middleOfUp || animation == walkUp) {
			if (y <= 128) {
				if (mapY < 0)
					mapY += 2;
			} else
				y -= 2;
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).checkHitReturn(new Point(x, y))) {
					if (y <= 128)
						mapY -= 2;
					else
						y += 2;
				}
			}
			if (y % 32 == 0) {
				middleOfUp = false;
				if (!up && animation != walkRight && animation != walkLeft) {
					animation = standUp;
				}
			} else
				middleOfUp = true;
		} else if (down || middleOfDown || animation == walkDown) {
			if (y >= 416) {
				if (mapY > -(170 - 1) * 32)
					mapY -= 2;
			} else {
				y += 2;
			}

			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).checkHitReturn(new Point(x, y + 30))) {
					if (y >= 416)
						mapY += 2;
					else
						y -= 2;
				}
			}

			if (y % 32 == 0) {
				middleOfDown = false;
				if (!down && animation != walkRight && animation != walkLeft) {
					animation = standDown;
				}
			} else
				middleOfDown = true;
		}

		tick++;
	}

	public void render(Graphics screen) {
		screen.drawImage(animation.getSprite(), x, y, 32 * scale, 32 * scale, null);
	}

	public KeyListener getKeyboard() {
		return keyboard;
	}

	private void initKeyboard() {
		keyboard = new MyKeyListener();
	}

	public int getMapX() {
		return mapX;
	}

	public int getMapY() {
		return mapY;
	}

	class MyKeyListener extends KeyAdapter {
		private boolean[] keysX = { false, false };
		private boolean[] keysY = { false, false };

		public void keyPressed(KeyEvent e) {
			System.out.println("test");
			int key = e.getKeyCode();
			switch (key) {
			// case (KeyEvent.VK_RIGHT):
			case (KeyEvent.VK_D):
				right = true;
				left = false;
				middleOfLeft = false;
				animation = walkRight;
				animation.start();
				tick = 0;
				break;
			// case (KeyEvent.VK_LEFT):
			case (KeyEvent.VK_A):
				right = false;
				left = true;
				middleOfRight = false;
				animation = walkLeft;
				animation.start();
				tick = 0;
				break;
			// case (KeyEvent.VK_DOWN):
			case (KeyEvent.VK_S):
				up = false;
				down = true;
				middleOfUp = false;
				if (animation != walkLeft && animation != walkRight) {
					animation = walkDown;
					animation.start();
				}
				break;
			// case (KeyEvent.VK_UP):
			case (KeyEvent.VK_W):
				down = false;
				up = true;
				middleOfDown = false;
				if (animation != walkLeft && animation != walkRight) {
					animation = walkUp;
					animation.start();
				}
				break;
			}

		}

		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();

			switch (key) {
			case (KeyEvent.VK_W):
				up = false;
				break;
			case (KeyEvent.VK_S):
				down = false;

				break;
			case (KeyEvent.VK_A):
				left = false;
				if (up) {
					animation = walkUp;
					animation.start();
				} else if (down) {
					animation = walkDown;
					animation.start();
				}
				break;
			case (KeyEvent.VK_D):
				right = false;
				if (up) {
					animation = walkUp;
					animation.start();
				} else if (down) {
					animation = walkDown;
					animation.start();
				}
				break;
			}
		}
	}

	public void setUpInput(JFrame frame) {
		JPanel pane = (JPanel) frame.getContentPane();

		pane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("D"), "move right");
		pane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("released D"), "release right");
		pane.getActionMap().put("move right", new MoveAction("right", false));
		pane.getActionMap().put("release right", new MoveAction("right", true));

		pane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("A"), "move left");
		pane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("released A"), "release left");
		pane.getActionMap().put("move left", new MoveAction("left", false));
		pane.getActionMap().put("release left", new MoveAction("left", true));

		pane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("W"), "move up");
		pane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("released W"), "release up");
		pane.getActionMap().put("move up", new MoveAction("up", false));
		pane.getActionMap().put("release up", new MoveAction("up", true));

		pane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("S"), "move down");
		pane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("released S"), "release down");
		pane.getActionMap().put("move down", new MoveAction("down", false));
		pane.getActionMap().put("release down", new MoveAction("down", true));
		
		pane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("E"), "interact");
		pane.getActionMap().put("interact", new Talk());

	}

	private class Talk extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (animation == walkUp || animation == standUp) {
				for (int i = 0; i < Game.game.getLevel().getEntities().size(); i++) {
					Game.game.getLevel().getEntities().get(i).checkHit(new Point(x + 10, y - 10));
				}
			} else if (animation == walkDown || animation == standDown) {
				for (int i = 0; i < Game.game.getLevel().getEntities().size(); i++) {
					Game.game.getLevel().getEntities().get(i).checkHit(new Point(x + 10, y + 42));
				}
			} else if (animation == walkRight || animation == standRight) {
				for (int i = 0; i < Game.game.getLevel().getEntities().size(); i++) {
					Game.game.getLevel().getEntities().get(i).checkHit(new Point(x + 42, y + 10));
				}
			} else if (animation == walkLeft || animation == standLeft) {
				for (int i = 0; i < Game.game.getLevel().getEntities().size(); i++) {
					Game.game.getLevel().getEntities().get(i).checkHit(new Point(x - 10, y + 10));
				}
			}
		}

	}

	private class MoveAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		String direction;
		boolean released;

		MoveAction(String direction, boolean released) {
			this.direction = direction;
			this.released = released;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!released) {
				switch (direction) {
				// case (KeyEvent.VK_RIGHT):
				case ("right"):
					right = true;
					left = false;
					middleOfLeft = false;
					animation = walkRight;
					animation.start();
					tick = 0;
					break;
				// case (KeyEvent.VK_LEFT):
				case ("left"):
					right = false;
					left = true;
					middleOfRight = false;
					animation = walkLeft;
					animation.start();
					tick = 0;
					break;
				// case (KeyEvent.VK_DOWN):
				case ("down"):
					up = false;
					down = true;
					middleOfUp = false;
					if (animation != walkLeft && animation != walkRight) {
						animation = walkDown;
						animation.start();
					}
					break;
				// case (KeyEvent.VK_UP):
				case ("up"):
					down = false;
					up = true;
					middleOfDown = false;
					if (animation != walkLeft && animation != walkRight) {
						animation = walkUp;
						animation.start();
					}
					break;
				}
			} else {
				switch (direction) {
				case ("up"):
					up = false;
					break;
				case ("down"):
					down = false;
					break;
				case ("left"):
					left = false;
					if (up) {
						animation = walkUp;
						animation.start();
					} else if (down) {
						animation = walkDown;
						animation.start();
					}
					break;
				case ("right"):
					right = false;
					if (up) {
						animation = walkUp;
						animation.start();
					} else if (down) {
						animation = walkDown;
						animation.start();
					}
					break;
				}
			}
		}
	}
}
