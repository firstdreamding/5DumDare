package entity;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import main.Animation;
import main.Game;
import main.Sprite;

public class TestSubject extends Entity {

	int x, y;
	Sprite sprite;
	Rectangle hitbox;
	int tick;
	int dir, nextTime;
	Animation animation, walkLeft, walkRight, walkUp, walkDown, standUp, standDown, standRight, standLeft;

	public TestSubject(int x, int y, int width, int height) {
		super(x, y, width, height, null);
		Sprite sprite = new Sprite("greenMan", 32);
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		hitbox = new Rectangle(x, y, width, height);
		tick = 0;
		dir = 1;
		walkLeft = new Animation(sprite, 0, 2, 1, 1, 16, true);
		walkRight = new Animation(sprite, 0, 2, 2, 2, 16, true);
		walkUp = new Animation(sprite, 0, 2, 3, 3, 16, true);
		walkDown = new Animation(sprite, 0, 2, 0, 0, 16, true);
		standUp = new Animation(sprite, 1, 1, 3, 3, 10000, false);
		standDown = new Animation(sprite, 1, 1, 0, 0, 10000, false);
		standRight = new Animation(sprite, 1, 1, 2, 2, 10000, false);
		standLeft = new Animation(sprite, 1, 1, 1, 1, 10000, false);
		animation = standLeft;
		nextTime = 240;

	}

	public void update(int xx, int yy) {
		hitbox.x = this.x + xx;
		hitbox.y = this.y + yy;
		animation.update();
		// System.out.println(hitbox.x + " " + hitbox.y + " " + x + " " + y);
		if (tick % nextTime == 0) {
			dir = (int) (Math.random() * 3);
			nextTime = (int) (Math.random() * 50 + 480);
			switch (dir) {
			case 0:
				animation = walkRight;
				animation.start();
				break;
			case 1:
				animation = walkLeft;
				animation.start();
				break;
			case 2:
				animation = walkDown;
				animation.start();
				break;
			case 3:
				animation = walkUp;
				animation.start();
				break;

			}
		}
		if (dir == -1 || tick % 240 == 0) {
			tick++;
		}
		if (dir != -1) {
			switch (dir) {
			case 0:
				x += 2;
				break;
			case 1:
				x -= 2;
				break;
			case 2:
				y += 2;
				break;
			case 3:
				y -= 2;
				break;
			}
			if (dir == 0 || dir == 1) {
				if (x % 32 == 0) {
					switch (dir) {
					case 0:
						animation = standRight;
						animation.start();
						break;
					case 1:
						animation = standLeft;
						animation.start();
						break;
					}
					dir = -1;
				}
			} else {
				System.out.println(y % 32 == 0);
				if (y % 32 == 0) {
					switch (dir) {
					case 2:
						animation = standDown;
						animation.start();
						break;
					case 3:
						animation = standUp;
						animation.start();
						break;
					}
					dir = -1;
				}
			}
		}
	}

	public void render(Graphics screen, int x, int y) {
		screen.drawImage(animation.getSprite(), this.x + x, this.y + y, 32, 32, null);
		screen.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
	}

	public void interact() {
		System.out.println("lool");
		Game.game.dialogAdd("Hello, its me. I was wondering if all these times", new Sprite("mage", 1041), 1);
		Game.game.dialogAdd("I love you Geoffrey", new Sprite("mage", 1041), 2);
		Game.game.dialogAdd("JK I FUCKING HATE U", new Sprite("mage", 1041), 3);
		Game.game.dialogNext();
		Game.game.dialogStart();
	}

	public void checkHit(Point point) {
		if (hitbox.contains(point)) {
			interact();
		}
	}

	public boolean checkHitReturn(Point point) {
		return hitbox.contains(point);
	}
}
