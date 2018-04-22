package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tile {
	
	Sprite sprite;
	boolean can;
	Rectangle hitbox;
	int x, y, scale;
	BufferedImage bi;
	
	public Tile(String path,int x, int y, boolean canColide, int scale) {
		sprite = new Sprite(path, 32);
		can = canColide;
		this.x = x;
		this.y = y;
		bi = sprite.getSprite();
	}
	
	public Tile(Sprite sprite,int x, int y, boolean canColide, int scale) {
		this.sprite = sprite;
		can = canColide;
		this.x = x;
		this.y = y;
		bi = sprite.getSprite();
	}
	
	public Tile(Sprite sprite,int x, int y, boolean canColide, int scale, int spriteX, int spriteY) {
		this.sprite = sprite;
		can = canColide;
		this.x = x;
		this.y = y;
		bi = sprite.getSprite(spriteX, spriteY);
	}
	
	public void render(Graphics screen, int x, int y) {
		screen.drawImage(bi,this.x + x, this.y + y, null);
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean check(Rectangle other) {
		return can ? hitbox.intersects(other) : false;
	}

}
