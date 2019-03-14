import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cagan Sevencan
 */
public class AnimationTester {
    public static void main(String[] args) {

        final int CAR_WIDTH = 160;

        List<MoveableShape> shapes = new ArrayList<>(); //List of moveable shapes
        shapes.add(new BoxedShape(new CompoundShape(new BoxedShape(new CarShape(200, 20, CAR_WIDTH), 3),
                new MoveableIcon("dog.png", 100, 10), new MoveableIcon("dog.png", 150, 100)), 0));
        Animation.show(shapes, new BoundedMoveStrategy(new Rectangle(0, 0, 500, 200)), 600, 200);
    }
}