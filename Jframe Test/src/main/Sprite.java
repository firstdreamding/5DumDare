package main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

    private static BufferedImage spriteSheet;
    private int tileSize;
    
    private BufferedImage sprite = null;
    
    public Sprite(String path, int size) {
    	loadSprite(path);
    	tileSize = size;
    }

    private void loadSprite(String file) {

        try {
            sprite = ImageIO.read(this.getClass().getResourceAsStream("/res/" + file + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public BufferedImage getSprite(int xGrid, int yGrid) {
        return sprite.getSubimage(xGrid * tileSize, yGrid * tileSize, tileSize, tileSize);
    }
    
    public BufferedImage getSprite() {
    	return sprite;
    }

}