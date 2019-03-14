import java.util.List;

public interface MoveStrategy {
    /**
     * Moves the shapes according to this method
     * @param shapes shapes to be moved
     */
    void process(List<MoveableShape> shapes);
}
