package entity;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Point;
import java.awt.Point;
import java.awt.Rectangle;

import main.Sprite;

public class Entity {
	Rectangle hitbox;
	
	public Entity(int x, int y, int width, int height, Sprite sprite) {
		hitbox = new Rectangle(x, y, width, height);
	}
	
	public void checkHit(Point point) {
		if(hitbox.contains(point)) {
			System.out.println("Not setup yet");
		}
	}
	
	public boolean checkHitReturn(Point point) {
		return hitbox.contains(point);
	}
	
	public void update(int x, int y) {
		
	}
	
	public void render(Graphics screen, int x, int y) {
		
	}
}
