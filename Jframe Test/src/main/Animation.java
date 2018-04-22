package main;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {

	private List<BufferedImage> frames;
	private int delay, frameCount, index, direction;
	private boolean stopped, loop;
	long test;

	public Animation(Sprite spriteSheet, int startX, int endX, int startY, int endY, int delay, boolean loop) {
		frames = new ArrayList<BufferedImage>();
		for (int y = startY; y <= endY; y++) {
			for (int x = startX; x <= endX; x++) {
				frames.add(spriteSheet.getSprite(x, y));
			}
		}
		this.delay = delay;
		index = 0;
		this.loop = loop;
		direction = 1;
		stopped = true;
	}

	public void update() {
		if (!stopped) {
			frameCount++;
			if (frameCount > delay) {
				test = System.currentTimeMillis();
				frameCount = 0;
				if (loop) {
					if (index + direction > frames.size() - 1) {
						direction = -1;
						index += direction;
					} else if (index + direction < 0) {
						direction = 1;
						index += direction;
					} else
						index += direction;
				} else {
					if (index + direction > frames.size() -1) {
						index = 0;
					} else if (index + direction < 0) {
						index = frames.size() - 1;
					} else
						index += direction;
				}		
			}
		}
	}
	
	public void start() {
	      if (!stopped) {
	            return;
	        }

	        if (frames.size() == 0) {
	            return;
	        }

	        stopped = false;
	        frameCount = 0;
	        for(int i = 0; i > 7; i++) {
	        	update();
	        }
	}
	
	public void stop() {
		stopped = true;
	}
	
	public void reset() {
		index = 0;
		frameCount = 0;
	}
	
	public BufferedImage getSprite() {
		return frames.get(index);
	}
}
