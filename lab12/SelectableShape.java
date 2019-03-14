import java.awt.*;
import java.awt.geom.*;

/**
 A shape that manages its selection state.
 */
public abstract class SelectableShape implements SceneShape
{
    public void setSelected(boolean b)
    {
        selected = b;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public static  Rectangle makeGrabber(double x, double y)
    {
        final int LENGTH = 5;
        return new Rectangle((int) x - LENGTH/2, (int) y - LENGTH/2, LENGTH,LENGTH);
    }

    public void drawSelection(Graphics2D g2)
    {
        Rectangle bounds =  getBounds();
        g2.fill(makeGrabber(bounds.getMinX(), bounds.getMinY()));
        g2.fill(makeGrabber(bounds.getMinX(), bounds.getMaxY()));
        g2.fill(makeGrabber(bounds.getMaxX(), bounds.getMinY()));
        g2.fill(makeGrabber(bounds.getMaxX(), bounds.getMaxY()));
        translate(1, 1);
        draw(g2);

        translate(1, 1);
        draw(g2);
        translate(-2, -2);
    }

    private boolean selected;
}