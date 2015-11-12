package al.aldi.games.gol;

import java.awt.*;
import java.util.Random;

import static al.aldi.games.gol.Main.*;

/**
 * Created by Aldi on 12.11.2015.
 */
public class Sprite {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean alive = false;
    private Color color = Color.black;

    public Sprite(int x, int y, boolean alive) {
        this(x, y);
        this.alive = alive;
    }

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = SPRITE_SIZE;
        this.height = SPRITE_SIZE;
        Random r = new Random();
        this.color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }

    public Sprite(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D g2d) {
        if (isAlive()) {
            Color c = g2d.getColor();
            g2d.setColor(color);
            g2d.drawRoundRect(x * SPRITE_SIZE, y * SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE, 3, 3);
            g2d.fillRect(x * SPRITE_SIZE, y * SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE);
            g2d.setColor(c);
        }
    }

    /**
     * Kill al.aldi.games.gol.Sprite
     */
    public void kill() {
        this.setAlive(false);
    }

    /**
     * Bring sprite back to life
     */
    public void resuscitate() {
        this.setAlive(true);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
