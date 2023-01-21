import java.awt.*;
import java.util.ArrayList;

public class CenterBlob {

    ArrayList<Food> food = new ArrayList<>();

    int x, y;
    int width, height;

    public Rectangle hitbox;

    GamePanel panel;
    public CenterBlob(GamePanel panel) {
        this.panel = panel;

        width = 80;
        height = 80;

        x = (panel.getWidth() / 2) - (width / 2);
        y = (panel.getHeight() / 2) - (height / 2);

        hitbox = new Rectangle(x, y, width - 10, height - 10);
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
        x = (panel.getWidth() / 2) - (width / 2);
        y = (panel.getHeight() / 2) - (height / 2);

        hitbox.width = width - (width / 3);
        hitbox.height = height - (height / 3);
        hitbox.x = x + (width / 6);
        hitbox.y = y + (height / 6);

        // TODO: DRAW BLOB
        GradientPaint blobGradient = new GradientPaint(x, y, Color.RED, x + width, y + height, new Color(210, 67, 67));
        g2.setPaint(blobGradient);
        g2.fillOval(x, y, width, height);

        // TODO: DRAW SIZE STRING
        int centerX, centerY;
        String str;
        int stringWidth, stringHeight;
        int stringX, stringY;

        g2.setColor(new Color(255, 255, 255, 100));

        centerX = x + (width / 2);
        centerY = y + (height / 2);

        str = "" + width;

        g2.setFont(new Font("DIALOG", Font.BOLD, width / 3));

        stringWidth = (int) g2.getFontMetrics().getStringBounds(str, g2).getWidth();
        stringHeight = (int) g2.getFontMetrics().getStringBounds(str, g2).getWidth();
        stringX = centerX - (stringWidth / 2);
        stringY = centerY + (stringHeight / 4);

        g2.drawString(str, stringX, stringY);


        // DEBUG
        // g2.setColor(Color.BLACK);
        // g2.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    public void increaseSize(int increment) {
        width += increment;
        height += increment;
    }

    public void addFood(int x, int y) {
        food.add(new Food(panel, x, y));
    }

    public void consume(Food food) {
        width += food.width / 3;
        height += food.height / 3;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
