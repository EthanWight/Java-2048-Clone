import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private Game game;

    public GamePanel(Game game) {
        this.game = game;
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        game.slideLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        game.slideRight();
                        break;
                    case KeyEvent.VK_UP:
                        game.slideUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        game.slideDown();
                        break;
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTiles(g);
        drawGrid(g);
        drawScore(g);
    }

    private void drawGrid(Graphics g) {
        int tileSize = getWidth() / game.getGrid().length;

        // Draw grid lines
        g.setColor(Color.BLACK); // Set color for grid lines

        // Draw vertical lines
        for (int i = 1; i < game.getGrid().length; i++) {
            g.drawLine(i * tileSize, 20, i * tileSize, getHeight());
        }

        // Draw horizontal lines
        for (int i = 1; i < game.getGrid().length; i++) {
            g.drawLine(0, i * tileSize + 20, getWidth(), i * tileSize + 20);
        }
    }

    private void drawTiles(Graphics g) {
        int[][] grid = game.getGrid();
        int tileSize = getWidth() / grid.length;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(j * tileSize, 20 + i * tileSize, tileSize, tileSize);

                if (grid[i][j] != 0) {
                    g.setColor(Color.BLACK);
                    g.drawString(String.valueOf(grid[i][j]),
                            j * tileSize + tileSize / 2,
                            20 + i * tileSize + tileSize / 2);
                }
            }
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("Score: " + game.getScore(), 10, 15); // Display score
        g.drawLine(0,20,400,20);
    }
}
