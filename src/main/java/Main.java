import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Merging Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new GamePanel(game));
            frame.setSize(400, 440);
            frame.setVisible(true);
        });
    }
}
