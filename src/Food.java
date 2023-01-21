import java.awt.*;

public class Food {

    double x, y;
    int width, height;
    double xSpeed = 0;
    double ySpeed = 0;
    double velocity = 0.3;

    Rectangle hitbox;

    GamePanel gp;

    public Food(GamePanel gp, int x, int y) {
        this.gp = gp;
        width = 12;
        height = 12;
        this.x = x;
        this.y = y;

        hitbox = new Rectangle(x, y, width, height);
    }


    public void draw(Graphics2D g2) {

        int blobX = gp.blob.getX() + (gp.blob.getWidth() / 2);
        int blobY = gp.blob.getY() + (gp.blob.getHeight() / 2);

        if (x < blobX) {
            xSpeed += velocity;
        } else if (x > blobX) {
            xSpeed -= velocity;
        }

        if (y < blobY) {
            ySpeed += velocity;
        } else if (y > blobY) {
            ySpeed -= velocity;
        }

        x += xSpeed;
        y += ySpeed;

        hitbox.x = (int) x;
        hitbox.y = (int) y;

        GradientPaint foodGradient = new GradientPaint((int) x, (int) y, Color.RED, (int) x + width, (int) y + height, new Color(210, 67, 67));
        g2.setPaint(foodGradient);
        g2.fillOval((int) x, (int) y, width, height);
    }
}
