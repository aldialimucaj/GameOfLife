package al.aldi.games.gol;

import java.awt.*;

import static al.aldi.games.gol.Main.*;

/**
 * Created by Aldi on 12.11.2015.
 */
public class Logic {
    Sprite[][] sprites;

    /**
     * Create matrix and fill with random sprites
     *
     * @param sprites
     */
    public Logic(Sprite[][] sprites) {
        this.sprites = sprites;
        fillRandomSprites();
    }

    /**
     * Will create matrix with Main.WINDOW_WIDTH + Main.WINDOW_HEIGHT default values and create random sprites.
     */
    public Logic() {
        this(new Sprite[WINDOW_WIDTH][WINDOW_HEIGHT]);
    }

    /**
     * Create new matrix of sprites.
     */
    public void resetSprites() {
        for (int i = 0; i < sprites.length; i++) {
            for (int j = 0; j < sprites[i].length; j++) {
                sprites[i][j] = new Sprite(i, j, false);
            }
        }
    }

    /**
     * Kill all sprites. This will result in them not being showed.
     */
    public void killAllSprites() {
        for (int i = 0; i < sprites.length; i++) {
            for (int j = 0; j < sprites[i].length; j++) {
                sprites[i][j].kill();
            }
        }
    }

    /**
     * Fill matrix with random sprites
     */
    public void fillRandomSprites() {
        for (int i = 0; i < sprites.length; i++) {
            for (int j = 0; j < sprites[i].length; j++) {
                sprites[i][j] = new Sprite(i, j, Math.random() < SPRITE_PROBABILITY);
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

                // first find all live neighbours
                if (i > 0 && sprites[i - 1][j].isAlive()) neighboursAlive++; // LEFT
                if (i < WINDOW_WIDTH - 1 && sprites[i + 1][j].isAlive()) neighboursAlive++;// RIGHT
                if (j > 0 && sprites[i][j - 1].isAlive()) neighboursAlive++; // BOTTOM
                if (j < WINDOW_HEIGHT - 1 && sprites[i][j + 1].isAlive()) neighboursAlive++; // TOP
                if (i > 0 && j > 0 && sprites[i - 1][j - 1].isAlive()) neighboursAlive++; // LEFT_BOTTOM
                if (i > 0 && j < WINDOW_HEIGHT - 1 && sprites[i - 1][j + 1].isAlive()) neighboursAlive++; // LEFT_TOP
                if (i < WINDOW_WIDTH - 1 && j > 0 && sprites[i + 1][j - 1].isAlive()) neighboursAlive++; // RIGHT_BOTTOM
                if (i < WINDOW_WIDTH - 1 && j < WINDOW_HEIGHT - 1 && sprites[i + 1][j + 1].isAlive()) neighboursAlive++; // RIGHT_TOP

                // then kill or resuscitate depending on the rules
                if (sprites[i][j].isAlive()) {// cell is alive
                    //RULE 1: Any live cell with fewer than two live neighbours dies, as if caused by under-population.
                    if (neighboursAlive < 2) sprites[i][j].kill();
                    //RULE 3: Any live cell with more than three live neighbours dies, as if by over-population.
                    if (neighboursAlive > 3) sprites[i][j].kill();
                    //RULE 2: Any live cell with two or three live neighbours lives on to the next generation.
                    // nothing to do.
                } else { // cell is dead
                    //RULE 4: Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
                    if (neighboursAlive == 3) sprites[i][j].resuscitate();
                }
            }
        }
    }

    public void recalculate() {
        applyGOL();
    }

    /**
     * Draw all sprites on the graphic.
     *
     * @param g2d
     */
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
