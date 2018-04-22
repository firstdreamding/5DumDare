package entity;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import main.Sprite;

public class TestSubject extends Entity{

	int x, y;
	Sprite sprite;
	Rectangle hitbox;
	
	
	public TestSubject(int x, int y, int width, int height, Sprite sprite) {
		super(x, y, width, height, sprite);
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		hitbox = new Rectangle(x, y, width, height);
		
	}
	
	public void update() {
		
	}
	
	public void render(Graphics screen) {
		screen.drawImage(sprite.getSprite(1,1), x, y, 32, 32, null);
	}
	
	public void interact() {
		System.out.println("Hello its me");
	}
	
	public void checkHit(Point point) {
		if(hitbox.contains(point)) {
			interact();
		}
	}
}
