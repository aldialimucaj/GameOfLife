package al.aldi.games.gol;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aldi on 12.11.2015.
 */
public class Main extends JFrame {
    public static final int WINDOW_HEIGHT = 64;
    public static final int WINDOW_WIDTH = 64;
    public static final int SPRITE_SIZE = 6;
    public static final int SPRITE_PADDING = 1;


    private Board board = null;
    private Dimension dimension = null;

    public Main() {
        dimension = new Dimension(WINDOW_WIDTH * SPRITE_SIZE, WINDOW_HEIGHT * SPRITE_SIZE);
        board = new Board();
        board.setPreferredSize(dimension);
        pack();
        initUI();
    }

    private void initUI() {
        setSize(dimension);
        add(board);

        setTitle("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Runnable r = () -> {
            while (true) {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                board.revalidate();
                board.repaint();
            }
        };
        new Thread(r).start();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Main m = new Main();
            m.setVisible(true);
        });
    }
}
