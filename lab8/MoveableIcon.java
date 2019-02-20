import javax.swing.*;
import java.awt.*;

public class MoveableIcon extends ImageIcon implements MoveableShape {

    private int x;
    private int y;

    public MoveableIcon(String filename, int x, int y)
    {
        super(filename);
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D g2) {

    }

    @Override
    public void move() {
            x++;
        }

}
