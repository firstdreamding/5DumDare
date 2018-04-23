package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.PriorityQueue;

public class Dialog {

	private class TextDia implements Comparable<TextDia>{
		
		char[] text;
		Sprite sprite;
		int tick;
		String render;
		int times;
		int sort;
		Font font;
		
		public TextDia(String text, Sprite sprite, int sort) {
			this.text = text.toCharArray();
			this.sprite = sprite;
			tick = 0;
			times = 0;
			render = "";
			this.sort = sort;
			font = new Font("Courier New", Font.BOLD, 24);
		}
		
		public void update() {
			System.out.println("test");
			tick++;
			if(tick%4 == 0) {
				times++;
				if(times < text.length + 1) {
					render += text[times-1];
				}
				//if(tick > 360)
				//	Game.game.state = 0;
			}
		}
		
		public void render(Graphics screen) {
			screen.setColor(Color.WHITE);
			screen.fillRect(0, 390, 960, 200);
			screen.setColor(Color.BLACK);
			screen.setFont(font);
			screen.drawString(render, 30, 430);
			screen.drawImage(sprite.getSprite(), 0, 190,200,200, null);
		}

		@Override
		public int compareTo(TextDia other) {
			
			return sort > other.sort ? 1 : sort < other.sort ? -1 : 0;
		}
	}

	PriorityQueue<TextDia> queue;
	TextDia current;

	public Dialog() {
		queue = new PriorityQueue<TextDia>();
	}

	public void update() {
		current.update();
	}

	public void render(Graphics screen) {
		current.render(screen);
	}

	public void add(String string, Sprite sprite, int priority) {
		queue.add(new TextDia(string, sprite, priority));
	}

	public void remove() {

	}
	
	public void next() {
		if(queue.peek() != null)
			current = queue.poll();
		else {
			Game.game.state = 0;
		}
	}
}
