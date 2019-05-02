import java.awt.*;
import java.awt.geom.*;

/**
   A diamond-shaped node.
*/
public class DiamondNode implements Node
{
   /**
      Construct a circle node with a given size and color.
      @param aColor the fill color
   */
   public DiamondNode()
   {
      size = DEFAULT_SIZE;
      x = 0;
      y = 0;
   }

   public Object clone()
   {
      try
      {
         return super.clone();
      }
      catch (CloneNotSupportedException exception)
      {
         return null;
      }
   }

   public void draw(Graphics2D g2)
   {
      g2.draw(new Line2D.Double(x + size / 2, y, x + size, y + size / 2));
      g2.draw(new Line2D.Double(x + size / 2, y, x, y + size / 2));
      g2.draw(new Line2D.Double(x + size / 2, y + size, x + size, y + size / 2));
      g2.draw(new Line2D.Double(x + size / 2, y + size, x, y + size / 2));
   }

   public void translate(double dx, double dy)
   {
      x += dx;
      y += dy;
   }

   public Rectangle2D getBounds()
   {
      return new Rectangle2D.Double(
            x, y, size, size);
   }

   public Point2D getConnectionPoint(Point2D other)
   {
      double centerX = x + size / 2;
      double centerY = y + size / 2;
      double dx = other.getX() - centerX;
      double dy = other.getY() - centerY;
      if (dx >= dy && dx >= -dy) return new Point2D.Double(x + size, centerY);
      if (dx < dy && dx >= -dy) return new Point2D.Double(centerX, y + size);
      if (dx >= dy && dx < -dy) return new Point2D.Double(centerX, y);
      return new Point2D.Double(x, centerY);
   }

   /**
    * Checks whether a point is to the right of a given line
    * @param p1 The first point on the line
    * @param p2 The second point on the line
    * @param p the point to test
    * @return true if p lies to the right of the line traversing p1, then p2
    */
   public static boolean toRightOf(Point2D p1, Point2D p2, Point2D p)
   {
      double nx = p2.getY() - p1.getY();
      double ny = p1.getX() - p2.getX(); 
      double vx = p.getX() - p1.getX();
      double vy = p.getY() - p1.getY();
      double s = nx * vx + ny * vy;
      System.out.println(p1 + " " + p2 + " " + p + s);
      return s < 0; // Java2D y coordinate points downward
   }

   public boolean contains(Point2D aPoint)
   {
      Point2D top = new Point2D.Double(x + size / 2, y);
      Point2D right = new Point2D.Double(x + size, y + size / 2);
      Point2D bottom = new Point2D.Double(x + size / 2, y + size);
      Point2D left = new Point2D.Double(x, y + size / 2);

      return toRightOf(top, right, aPoint)
         && toRightOf(right, bottom, aPoint)
         && toRightOf(bottom, left, aPoint)
         && toRightOf(left, top, aPoint);
   }

   private double x;
   private double y;
   private double size;
   private Color color;  
   private static final int DEFAULT_SIZE = 20;
}
