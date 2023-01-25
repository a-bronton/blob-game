import java.awt.*;
import java.security.Key;
import java.util.ArrayList;

public class CenterBlob {

    ArrayList<Food> food = new ArrayList<>();

    Font counterFont;

    int x, y;
    int width, height;
    int xSpeed, ySpeed;

    public Rectangle hitbox;

    GamePanel panel;
    public CenterBlob(GamePanel panel) {
        this.panel = panel;

        xSpeed = 5;
        ySpeed = 5;

        width = 80;
        height = 80;

        x = (int) ((panel.getWidth() / 2) - (width / 2));
        y = (int) ((panel.getHeight() / 2) - (height / 2));

        hitbox = new Rectangle(x, y, (int) width - 10, (int) height - 10);

        try {
            counterFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/MaruMonica.ttf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        // TODO: DRAW ALL FOOD
        for (int i = 0; i < food.size(); i++) {
            food.get(i).draw(g2);

            // TODO: CHECK FOR COLLISION AND CONSUME
            if (food.get(i).hitbox.intersects(hitbox)) {
                consume(food.get(i));
                food.remove(i);
            }
        }

        // TODO: UPDATE VARAIBLES
        x = (int) ((panel.getWidth() / 2) - (width / 2));
        y = (int) ((panel.getHeight() / 2) - (height / 2));

        hitbox.width = (int) (width - (width / 3));
        hitbox.height = (int) (height - (height / 3));
        hitbox.x = (int) (x + (width / 6));
        hitbox.y = (int) (y + (height / 6));

        // TODO: DRAW BLOB
        GradientPaint blobGradient = new GradientPaint(x, y, Color.RED, (int) (x + width), (int) (y + height), new Color(210, 67, 67));
        g2.setPaint(blobGradient);
        g2.fillOval(x, y, (int) width, (int) height);

        // TODO: DRAW SIZE STRING
        int centerX, centerY;
        String str;
        int stringWidth, stringHeight;
        int stringX, stringY;

        g2.setColor(new Color(255, 255, 255, 100));

        centerX = (int) (x + (width / 2));
        centerY = (int) (y + (height / 2));

        str = "" + width;

        g2.setFont(new Font("DIALOG", Font.BOLD, (int) (width / 3)));

        stringWidth = (int) g2.getFontMetrics().getStringBounds(str, g2).getWidth();
        stringHeight = (int) g2.getFontMetrics().getStringBounds(str, g2).getWidth();
        stringX = centerX - (stringWidth / 2);
        stringY = centerY + (stringHeight / 4);

        g2.drawString(str, stringX, stringY);


        // DEBUG
        // g2.setColor(Color.BLACK);
        // g2.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);

        // MAKER SURE ALWAYS > 50
        if (width < 50) {
            width = 50;
            height = 50;
        }
    }

    public void increaseSize(int increment) {
        width += increment;
        height += increment;
    }

    public void addFood(int x, int y) {
        food.add(new Food(panel, x, y));
    }

    public void consume(Food food) {
        increaseSize(food.width / 3);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return (int) width;
    }

    public int getHeight() {
        return (int) height;
    }

}
