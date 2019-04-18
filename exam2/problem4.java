Font font = new Font("SansSerif", Font.PLAIN, 18);
FontRenderContext context = new FontRenderContext() {};
Rectangle2D bounds = font.getStringBounds(text, context);
  bounds.getWidth = 18;
  
   // bounds.getWidth() and bounds.getHeight() give the width and height
   // of the string in the given font
g2.setFont(font);
g2.drawString(string, (int) x, (int) (y - bounds.getY()));
   // draws the string with upper left corner (x, y)

   public LabeledLineEdge(String font, int size)
   {
     this.font = font;

   }
