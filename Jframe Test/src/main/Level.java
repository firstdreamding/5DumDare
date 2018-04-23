package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import entity.Entity;
import entity.Player;
import entity.TestSubject;

public class Level {
	Player player;
	TileLevel tileLevel;
	private List<Entity> entities;
	long counter;
	int tick, fps;

	public Level(int scale) {
		player = new Player(scale);
		tileLevel = new TileLevel();
		entities = new ArrayList<Entity>();
		entities.add(new TestSubject(640, 320, 32, 32));
		counter = System.currentTimeMillis();
		tick = 0;
	}

	public void update() {
		tick++;
		player.update(entities);
		tileLevel.mapX(player.getMapX());
		tileLevel.mapY(player.getMapY());
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).update(player.getMapX(), player.getMapY());
		}	
		if(System.currentTimeMillis()-counter > 1000) {
			counter = System.currentTimeMillis();
			fps = tick;
			tick = 0;
		}
	}

	public void render(Graphics screen) {
		tileLevel.render(screen);
		
		for (int i = 0; i < 31; i++) {
			for (int j = 0; j < 20; j++) {
				screen.setColor(Color.black);
				screen.drawRect(i * 32 + player.getMapX() % 32, j*32 + player.getMapY() % 32, 32, 32);
			}
		}
		
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen, player.getMapX(), player.getMapY());
		}
		
		player.render(screen);
		
		
			screen.setColor(Color.BLACK);
			screen.drawString(fps + "", 40, 40);
	}

	public Player getPlayer() {
		return player;
	}

	public KeyListener getKeyboard() {
		return player.getKeyboard();
	}
	
	public void setUpInput(JFrame frame) {
		player.setUpInput(frame);
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
}
