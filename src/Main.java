import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Untitled Blob Game");
        new Main().setIcon(window);

        GamePanel panel = new GamePanel();
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);

        window.setVisible(true);
    }

    public void setIcon(JFrame window) {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icons/game_icon.png"));
        window.setIconImage(icon.getImage());
    }
}