import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {

    CenterBlob blob;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setFocusable(true);

        setBackground(Color.BLACK);

        blob = new CenterBlob(this);
        addKeyListener(new KeyHandler());
        addMouseListener(new MouseHandler(this));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        blob.draw(g2);

        if (KeyHandler.spacePressed && !KeyHandler.ePressed) {
            blob.increaseSize(5);
        }
        if (KeyHandler.ePressed && !KeyHandler.spacePressed) {
            if (blob.width > 50) {
                blob.increaseSize(-5);
            }
        }

        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }

        repaint();
    }

    public int getWidth() {
        return super.getWidth();
    }

    public int getHeight() {
        return super.getHeight();
    }
}
