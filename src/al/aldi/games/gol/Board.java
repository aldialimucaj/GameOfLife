package al.aldi.games.gol;

import javax.swing.*;
import java.awt.*;

import static al.aldi.games.gol.Main.*;

/**
 * Created by Aldi on 12.11.2015.
 */
public class Board extends JPanel {
    Logic l = new Logic();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintSprites(g);
    }

    /**
     * Called every time the panel gets repainted. it Calculates lifes next step and redraws.
     *
     * @param g
     */
    private void paintSprites(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // reset the canvas
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, WINDOW_WIDTH * SPRITE_SIZE, WINDOW_HEIGHT * SPRITE_SIZE);

        // recalc and redraw
        l.recalculate();
        l.drawAll(g2d);
    }
}
