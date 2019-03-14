import java.awt.*;
import java.util.List;


public class BoundedMoveStrategy implements MoveStrategy {


    private Rectangle bounds;

    /**
     * Initializes bounds
     * @param bounds the rect to bound the shapes
     */
    public BoundedMoveStrategy(Rectangle bounds) {
        this.bounds = bounds;
    }

    @Override
    public void process(List<MoveableShape> shapes) {
        for (MoveableShape s: shapes) {
            if (bounds.contains(s.getBounds()))
                s.move();
        }
    }
}
