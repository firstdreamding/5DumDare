package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class TileLevel {
	Tile tiles[];
	int mapX, mapY;
	int mapW = 300;
	int mapH = 170;

	public TileLevel() {
		mapX = 0;
		mapY = 0;
		int tick = 0;
		Sprite tileSheet = new Sprite("tileSheet", 32);
		tiles = new Tile[51000];
		for (int i = 0; i < mapW; i++) {
			for (int j = 0; j < mapH; j++) {
				tick++;
				System.out.println(tick);
				tiles[j * 300 + i] = new Tile(tileSheet, i * 32, j * 32, false, 1, 0, 0);
				if (i == 14) {
					tiles[j * 300 + i] = new Tile(tileSheet, i * 32, j * 32, false, 1, 2, 0);
				}
				if (i == 13) {
					tiles[j * 300 + i] = new Tile(tileSheet, i * 32, j * 32, false, 1, 1, 0);
				}
				if (i == 15) {
					tiles[j * 300 + i] = new Tile(tileSheet, i * 32, j * 32, false, 1, 3, 0);
				}
			}
		}
	}

	public void render(Graphics screen) {
		for (int i = 0; i < 31; i++) {
			for (int j = 0; j < 20; j++) {
				tiles[(j+ -(mapY/32)) * 300 + i + -(mapX/32)].render(screen, mapX, mapY);
			}
		}
	}

	public void mapX(int x) {
		//if (x < 0 && x > -(mapW - 1) * 32)
			mapX = x;
	}

	public void mapY(int y) {
		//
			mapY = y;
	}
}
