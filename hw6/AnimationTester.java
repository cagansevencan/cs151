import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 This program implements an animation that moves
 a car shape.
 */
public class AnimationTester
{
    private static final int ICON_WIDTH = 400;
    private static final int ICON_HEIGHT = 100;
    private static final int CAR_WIDTH = 100;
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();

         MoveableIcon dogFile = new MoveableIcon("dog.png",100,0);

        final MoveableShape shape
                = new CarShape(200, 0, CAR_WIDTH);

        final MoveableShape shape2
                = new CarShape(0, 0, CAR_WIDTH);


        final MoveableShape shape3 = new MoveableIcon("dog.png", 0, 0);
        ShapeIcon icon3 = new ShapeIcon(shape3,ICON_WIDTH, ICON_HEIGHT);

        final JLabel label3 = new JLabel(icon3);
        frame.add(label3);


        ShapeIcon icon = new ShapeIcon(shape,
            ICON_WIDTH, ICON_HEIGHT);
        ShapeIcon icon2 = new ShapeIcon(shape2,
                ICON_WIDTH, ICON_HEIGHT);





        final JLabel label = new JLabel(icon);
        frame.setLayout(new FlowLayout());
        frame.add(label);

        final JLabel label2 = new JLabel(icon2);
        frame.setLayout(new FlowLayout());
        frame.add(label2);



        final int DELAY = 100;
        // Milliseconds between timer ticks
        Timer t = new Timer(DELAY, event ->
        {


            shape.move();
            shape2.move();
            label.repaint();
            label2.repaint();

            shape3.move();
            label3.repaint();




        });
        t.start();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
