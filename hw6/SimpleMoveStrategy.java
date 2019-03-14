import java.util.List;

public class SimpleMoveStrategy implements MoveStrategy {
    @Override
    public void process(List<MoveableShape> shapes) {
        for (MoveableShape s: shapes)
            s.move();
    }
}
