package al.aldi.games.gol;

import java.awt.*;

import static al.aldi.games.gol.Main.*;

/**
 * Created by Aldi on 12.11.2015.
 */
public class Logic {
    Sprite[][] sprites;

    public Logic(Sprite[][] sprites) {
        this.sprites = sprites;
        fillRandomSprites();
    }

    public Logic() {
        this(new Sprite[WINDOW_WIDTH][WINDOW_HEIGHT]);
    }

    public void resetSprites() {
        for (int i = 0; i < sprites.length; i++) {
            for (int j = 0; j < sprites[i].length; j++) {
                sprites[i][j] = new Sprite(i, j, false);
            }
        }
    }

    public void killAllSprites() {
        for (int i = 0; i < sprites.length; i++) {
            for (int j = 0; j < sprites[i].length; j++) {
                sprites[i][j].kill();
            }
        }
    }

    public void fillRandomSprites() {
        for (int i = 0; i < sprites.length; i++) {
            for (int j = 0; j < sprites[i].length; j++) {
                sprites[i][j] = new Sprite(i, j, Math.random() < 0.5);
            }
        }
    }

    /**
     * Apply the rules of Conway's Game of Life
     * <p>
     * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
     */
    public void applyGOL() {
        for (int i = 0; i < sprites.length; i++) {
            for (int j = 0; j < sprites[i].length; j++) {
                int neighboursAlive = 0;

                if (i > 0 && sprites[i - 1][j].isAlive()) neighboursAlive++;
                if (i < WINDOW_WIDTH - 1 && sprites[i + 1][j].isAlive()) neighboursAlive++;
                if (j > 0 && sprites[i][j - 1].isAlive()) neighboursAlive++;
                if (j < WINDOW_HEIGHT - 1 && sprites[i][j + 1].isAlive()) neighboursAlive++;

                if (sprites[i][j].isAlive()) {// cell is alive
                    // Any live cell with fewer than two live neighbours dies, as if caused by under-population.
                    if (neighboursAlive < 2) sprites[i][j].kill();
                    if (neighboursAlive > 3) sprites[i][j].kill();


                } else { // cell is dead
                    if (neighboursAlive == 3) sprites[i][j].resuscitate();
                }
            }
        }
    }

    public void recalculate() {
        applyGOL();
    }

    public void drawAll(Graphics2D g2d) {
        int alive = 0;

        for (int i = 0; i < sprites.length; i++) {
            for (int j = 0; j < sprites[i].length; j++) {
                sprites[i][j].draw(g2d);
                if (sprites[i][j].isAlive()) alive++;
            }
        }

        System.out.printf("Alive: %d, Dead: %d\n", alive, WINDOW_WIDTH * WINDOW_HEIGHT - alive);
    }
}
